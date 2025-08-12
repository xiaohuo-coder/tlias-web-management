package myAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Properties;
import java.util.concurrent.ExecutionException;


public class receive {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, ExecutionException {
        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        /**
         * 使用.getResourceAsStream()方法，参数是properties文件路径，返回值是输入流
         * 在类加载器的方法中，路径如果写相对路径，properties要放在src下
         * 如果是maven项目要放在src/main/resources下
         */
        InputStream stream = systemClassLoader.getResourceAsStream("prop.properties");
        //创建空map
        Properties prop = new Properties();
        //将输入流加载到prop中
        prop.load(stream);
        System.out.println(prop);
        stream.close();

    }
}