/**
 * Project Name:java-basic-se
 * File Name:MyClassLoader.java
 * Package Name:com.liuyu.java.basic.se.classloader
 * Date:2014年7月29日下午8:31:05
 * Copyright (c) 2014, com.liuyu@gmail.com All Rights Reserved.
 */
/**
 * Project Name:java-basic-se
 * File Name:MyClassLoader.java
 * Package Name:com.liuyu.java.basic.se.classloader
 * Date:2014年7月29日下午8:31:05
 * Copyright (c) 2014, com.liuyu@gmail.com All Rights Reserved.
 *
 */

package com.liuyu.java.basic.se.classloader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * description: 自定义ClassLoader. <br/>
 * date: 2014年7月29日 下午8:31:57 <br/>
 *
 * @author pengyao
 * @version 
 * @since JDK 1.7
 */
public class MyClassLoader extends ClassLoader {
    
    /** main logger */
    private static Logger logger = LoggerFactory.getLogger(MyClassLoader.class);
    
    private static final String SUFFIX = ".class";
    
    /**
     * 类加载路径
     */
    private String loadPath;
    
    /**
     * <p>根据指定的路径构造类加载器 </p>
     */
    public MyClassLoader(String path) {
        if(path == null || path.length() == 0){
            throw new IllegalArgumentException();
        }
        this.loadPath = path;
    }
    
    /**
     * 从指定路径加载class文件.
     * @see java.lang.ClassLoader#findClass(java.lang.String)
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        logger.info("load class {} ." , name);
        if(name != null && name.length() > 0 ){
            byte[] bytes= loadClassBytes(buildClassPath(name));
            if(bytes != null){
                return defineClass(name, bytes, 0, bytes.length);
            }
        }
        return super.findClass(name);
    }
    
    private byte[] loadClassBytes(String filePath){
        InputStream input = null;
        try {
            input = new BufferedInputStream(new FileInputStream(new File(filePath)));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buff = new byte[1024*4];
            int length = -1;
            if((length = input.read(buff)) != -1){
                baos.write(buff, 0, length);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(input != null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    private String buildClassPath(String className){
        return loadPath + className.replace('.', '/') + SUFFIX;
    }
}

