package myAPI;

import java.io.*;

public class MathDemo {
    public static void main(String[] args) throws IOException {
        File src = new File("D:\\project\\javaBasic");
        File dest = new File("D:\\project\\destination");
        copyFile(src, dest);

    }

    public static void copyFile(File src, File dest) throws IOException {
        dest.mkdirs();
        File[] files = src.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                FileInputStream fis = new FileInputStream(file);
                FileOutputStream fos = new FileOutputStream(new File(dest,file.getName()));
                byte[] bytes = new byte[1024];
                int len;
                while ((len = fis.read(bytes)) != -1) {
                    fos.write(bytes, 0, len);
                }
                fos.close();
                fis.close();
            }else {
                //说明找到的是文件夹，递归，把新找到的这个文件夹当作新的src，继续找
                copyFile(file,new File(dest,file.getName()));
            }
        }
    }

}