package com.memshell.generic;

import sun.misc.BASE64Encoder;
import java.io.FileInputStream;
import java.io.IOException;

public class Printer {

    public static void main(String[] args) throws IOException {

        String path = "G:\\code\\java\\memShell\\target\\classes\\com\\memshell\\generic\\DynamicControllerTemplate.class";
        FileInputStream in = new FileInputStream(path);
        byte[] bytes = new byte[in.available()];
        in.read(bytes);

        BASE64Encoder encoder = new BASE64Encoder();
        String base64String = encoder.encode(bytes).replaceAll("\r\n|\r|\n","");
        System.out.println(base64String);
        in.close();
    }
}
