package org.yangmin.launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ym
 * @Title: ClassFileLoader
 * @Package org.yangmin.launcher
 * @Description: class文件加载器，根据类的全限定名加载class物理文件，并解密文件内容
 * @date 2021/9/17 15:10
 **/
public class XlassFileLoader {

    private static final Logger LOGGER = Logger.getLogger(XlassFileLoader.class.getName());

    /**
     * 获取文件字节数组
     * @param xlassName 类全限定名
     * @return
     */
    public static byte[] getClassByte(String xlassName){
        byte[] buffer = getFileByte(xlassName);
        if(null == buffer)
            return null;

        buffer = decode(buffer);

        return buffer;
    }

    /**
     * 读取文件的字节数组
     * @param xlassName  类全限定名
     * @return
     */
    public static byte[] getFileByte(String xlassName){
        String fileName = "Hello.xlass";
        ClassLoader classLoader = XlassFileLoader.class.getClassLoader();

        try(InputStream  classIn = classLoader.getResourceAsStream(fileName) ){
            //读取文件大小，创建文件大小的缓冲区用于存放文件字节数组
            URL resource = classLoader.getResource(fileName);
            File xlassFile = new File(resource.toURI());

            byte[] buffer = new byte[(int)xlassFile.length()];

            assert classIn != null;

            classIn.read(buffer,0 , buffer.length);

            return buffer;
        }catch (IOException | URISyntaxException e){
            LOGGER.log(Level.SEVERE,"Class '{}' not found in  directory '{}' .",new String[]{xlassName,"resource"});
        }
        return null;
    }


    /**
     * 解密文件内容
     * @param bytes
     */
    public static byte[] decode(byte[] bytes){
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ~bytes[i]  ;
        }
        return bytes;
    }



}
