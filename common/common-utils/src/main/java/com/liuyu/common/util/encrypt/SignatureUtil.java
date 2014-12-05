/*
 * Copyright (C), 2013-2013, 上海汽车集团股份有限公司
 * FileName: SignatureUtil.java
 * Author:   pengyao
 * Date:     2014年5月7日 下午3:17:35
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.liuyu.common.util.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author pengyao
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SignatureUtil {
    
    public static void main(String[] args) {
        String str1 = "IDLMConnLeadsInboundServiceinboundLeadsappKey=key-carformat=jsontimestamp=20140507152732signatureMethod=md5version=1skey-car";
                //+"key-car";
        String str2 = "IDLMConnLeadsInboundServiceinboundLeads{'leads':[]}appKey=key-carformat=jsontimestamp=20140612152432signatureMethod=md5version=1skey-car";
        System.out.println(hex(getMD5Str(str1)).toUpperCase());
        System.out.println(hex(getMD5Str(str2)).toUpperCase());
    }
    
    /** 
     * MD5 加密 
     */  
    private static byte[] getMD5Str(String str) {  
        MessageDigest messageDigest = null;  
  
        try {  
            messageDigest = MessageDigest.getInstance("MD5");  
  
            messageDigest.reset();  
  
            messageDigest.update(str.getBytes("UTF-8"));  
        } catch (NoSuchAlgorithmException e) {  
            System.out.println("NoSuchAlgorithmException caught!");  
            System.exit(-1);  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
  
        byte[] byteArray = messageDigest.digest();  
  /**
        StringBuffer md5StrBuff = new StringBuffer();  
        
        for (int i = 0; i < byteArray.length; i++) {              
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
            else  
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
        }  
  */
        return byteArray;  
    }  
    
    public static String hex(byte[] array)  {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

}
