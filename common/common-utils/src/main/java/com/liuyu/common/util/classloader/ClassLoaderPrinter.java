package com.liuyu.common.util.classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * description: 打印类加载器信息. <br/>
 * date: 2014年7月26日 下午11:41:17 <br/>
 *
 * @author pengyao
 * @version
 * @since JDK 1.7
 */
public class ClassLoaderPrinter {
    
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(ClassLoaderPrinter.class);

    /**
     * 递归打印类加载器信息
     * 
     * @description: 递归打印类所属加载器. app classloader -> ext classloader -> bootstrap classloader
     * @param clazz 需要被打印类加载器的原始类
     */
    public <T> void printClassLoaderRecursive(Class<T> clazz) {
        if (clazz != null) {
            ClassLoader classLoader = clazz.getClassLoader();
            while (null != classLoader) {
                logger.info(classLoader.toString());
                classLoader = classLoader.getParent();
            }
        }
    }

    /**
     * 打印类文件所属加载器
     * @description: 打印clazz所属加载器名称
     * @param clazz 需要被打印类加载器的原始类
     */
    public <T> void printClassLoader(Class<T> clazz) {
        if(null != clazz){
            logger.info(clazz.getClassLoader().toString());
        }
    }
}
