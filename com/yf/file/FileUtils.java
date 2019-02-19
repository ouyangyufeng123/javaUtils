package com.yf.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author ouyangyufeng
 * @date 2019/2/16
 * 文件流工具类
 */
public class FileUtils {

    /**
     * 文件路径
     */
    private static final String FILEURL = "E:\\file\\test.txt";


    /**
     * 创建文件
     */
    public static File createFile(String fileUrl) {
        File file = new File(fileUrl);
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("创建文件成功!");
            }
        } catch (IOException ex) {
            System.out.println("创建文件失败!" + ex);
        }
        return file;
    }

    /**
     * 字节流写入内容
     */
    public static void writeByteDoc(String fileUrl,byte[] content) throws IOException {
        OutputStream outputStream = null;
        try {
            File file = createFile(fileUrl);
            if (file.isFile()) {
                outputStream = new FileOutputStream(file);
                outputStream.write(content);
                System.out.println("字节写入文件成功!");
            } else if (file.isDirectory()) {
                System.out.println("文件夹类型!");
            } else {
                System.out.println("其它文件类型!");
            }
        } catch (IOException ex) {
            System.out.println("字节写入文件失败!" + ex);
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }

    /**
     * 字节流读取内容
     */
    public static void readByteDoc(String fileUrl) throws Exception{
        InputStream inputStream = null;
        try {
            File file = createFile(fileUrl);
            inputStream = new FileInputStream(file);
            byte[] b = new byte[1024];
            int len;// 每次读入到byte中的字节的长度
            while ((len = inputStream.read(b)) != -1) {
                String str = new String(b, 0, len);
                System.out.print(str);
            }
        } catch (IOException ex) {
            System.out.println("字节写入文件失败!" + ex);
        } finally {
            inputStream.close();
        }
    }

    public static void main(String[] args) {
        try {
            //createFile(FILEURL, "test.txt");
            writeByteDoc(FILEURL, new String("哈哈111").getBytes());
            readByteDoc(FILEURL);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
