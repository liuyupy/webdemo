package com.liuyu.learn.apache;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParseException
    {
        System.out.println( "Hello World!" );
        
        /**
        boolean flag = false;  //可选  Entity en = null;
        do{
            if(doSome1()) break;
            doSome2();
            if(doSome3()) break;
            doSome4();                
            flag = true;   //可选    en = doSome4();
        }while(false);
        
        if(!flag){            // if(en == null)
            doLog() // or doException();
        }
        
        doOther();
        */
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = sdf.parse("2014-02-08 16:30:00");
        System.out.println(d.getTime());
    }
}
