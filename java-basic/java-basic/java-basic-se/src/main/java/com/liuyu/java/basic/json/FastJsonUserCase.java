package com.liuyu.java.basic.json;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meidusa.fastjson.JSON;
import com.meidusa.fastmark.feature.SerializerFeature;

import junit.framework.TestCase;

/**
 * fastjson 使用
 * @author pengyao
 *
 */
public class FastJsonUserCase extends TestCase{
	
	private static Logger logger = LoggerFactory.getLogger(FastJsonUserCase.class);

	@Test
	public void testToJson(){
		JsonObject mockObj = mockJsonObject();
		String jsonStrDefault = JSON.toJSONString(mockObj);
		String jsonStrWithNull = JSON.toJSONString(mockObj , SerializerFeature.WriteMapNullValue);
		String jsonStrWithISODateFormat = JSON.toJSONString(mockObj , SerializerFeature.UseISO8601DateFormat);
		
		Assert.assertNotNull(jsonStrDefault);
		Assert.assertNotEquals(jsonStrDefault, jsonStrWithNull);
		Assert.assertNotEquals(jsonStrDefault, jsonStrWithISODateFormat);
		Assert.assertNotEquals(jsonStrWithNull, jsonStrWithISODateFormat);
		
		logger.info("jsonStrDefault : {}" , jsonStrDefault);
		logger.info("jsonStrWithNull : {}" , jsonStrWithNull);
		logger.info("jsonStrWithISODateFormat : {}" , jsonStrWithISODateFormat);
	}

	private JsonObject mockJsonObject(){
		JsonObject object = new JsonObject();
		object.setDateNotNull(new Date());
		object.setStrNotNull("str");
		
		return object;
	}
}
