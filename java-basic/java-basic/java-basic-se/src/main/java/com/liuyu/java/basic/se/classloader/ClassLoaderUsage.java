/**
 * Project Name:java-basic-se
 * File Name:ClassLoaderUsage.java
 * Package Name:com.liuyu.java.basic.se.classloader
 * Date:2014年7月27日上午9:33:36
 * Copyright (c) 2014, com.liuyu@gmail.com All Rights Reserved.
 */
/**
 * Project Name:java-basic-se
 * File Name:ClassLoaderUsage.java
 * Package Name:com.liuyu.java.basic.se.classloader
 * Date:2014年7月27日上午9:33:36
 * Copyright (c) 2014, com.liuyu@gmail.com All Rights Reserved.
 *
 */

package com.liuyu.java.basic.se.classloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import com.liuyu.common.util.classloader.ClassLoaderPrinter;
import com.liuyu.common.util.excel.ExcelReader;
import com.liuyu.common.util.string.StringUtils;

/**
 * 
 * description: 学习classLoader的使用. <br/>
 * date: 2014年7月27日 上午10:51:02 <br/>
 *
 * @author pengyao
 * @version 
 * @since JDK 1.7
 */
public class ClassLoaderUsage {
    
    private static String[] existedClasses =  new String[]{"com.liuyu.common.util.classloader.ClassLoaderPrinter" , 
        "com.liuyu.common.util.excel.ExcelReader" , "com.liuyu.common.util.string.StringUtils"};
    
    private static String[] otherClasses = new String[]{"com.liuyu.learn.apache.attributes.AttributesCode" , 
        "com.liuyu.learn.apache.bcel.BcelCode" , "com.liuyu.learn.clazz.SignatureUtil"};
    
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        testDifferentClassLoads();
    }
    
    protected static void testDifferentClassLoads() throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
     // URLClassLoader 中 URL 必须以 "/"结尾 , URLClassPath#getLoader(URL)时,有file#endWith("/")的判断,来决定是FileLoader or JarLoader
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL("file:E:/Java/workspace/webdemo/java-basic/java-basic/java-basic-se/src/main/resources/classes/")});
        
        ClassLoaderPrinter printer = new ClassLoaderPrinter();
        
        //双亲委托,即使 url对应的目录下有类class文件,也会使用parentClassloader -- AppClassLoader(classPath下) 去加载
        print(printer , urlClassLoader , existedClasses);
        
        print(printer , new Object[]{new ClassLoaderPrinter() , new ExcelReader() , new StringUtils()});
        
        //无pom(classPath)引用,使用urlClassLoader去url对应的路径下加载
        print(printer , urlClassLoader , otherClasses);
        
        MyClassLoader myClassLoader = new MyClassLoader("E:/Java/workspace/webdemo/java-basic/java-basic/java-basic-se/src/main/resources/classes/");
        print(printer , myClassLoader , existedClasses);
        print(printer , myClassLoader , otherClasses);
        //不会再重新加载
        print(printer , myClassLoader , otherClasses);
    }
    
    @SuppressWarnings("all")
    public static void print(ClassLoaderPrinter printer , ClassLoader classLoader , String... clazzPath ) throws ClassNotFoundException{
        if(null != clazzPath && clazzPath.length > 0){
            for(String path : clazzPath){
                Class clazz = classLoader.loadClass(path);
                printer.printClassLoader(clazz);
            }
        }
    }
    
    public static void print(ClassLoaderPrinter printer , Object... objs){
        if(null != objs && objs.length > 0){
            for(Object obj : objs){
                printer.printClassLoader(obj.getClass());
            }
        }
    }

}

