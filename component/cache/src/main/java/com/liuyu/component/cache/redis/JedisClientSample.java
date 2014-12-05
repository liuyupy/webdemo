package com.liuyu.component.cache.redis;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

public class JedisClientSample {
	
	private String masterHost = "192.168.22.135"; //"192.168.26.128";
	private int masterPort = 6380;//6379;
	private int slavePort = 6380;
	
	public void singleServerSampleCase(){
		Jedis jedis = new Jedis(masterHost, masterPort);
		jedis.set("user", "pass");
		System.out.println(jedis.get("user"));
		for(int i = 0 ; i < 300 ; i ++){
			jedis.set("user"+i, "pass"+i);
		}
		for(int i = 0 ; i < 300 ; i ++){
			System.out.println(jedis.get("user"+i));
		}
		jedis.close();
	}
	
	public void singleServerPoolCase(){
		JedisPool pool = new JedisPool(new JedisPoolConfig(), masterHost , masterPort);
		try (Jedis jedis = pool.getResource()) {
		  jedis.set("foo", "bar");
		  String foobar = jedis.get("foo");
		  System.out.println(foobar);
		  jedis.zadd("sose", 0, "car"); jedis.zadd("sose", 0, "bike"); 
		  Set<String> sose = jedis.zrange("sose", 0, -1);
		  System.out.println(Arrays.toString(sose.toArray()));
		}
		pool.close();
		pool.destroy();
	}
	

	/**
	 * jedis 对主从 redis server 支持不够
	 */
	public void masterSalvePoolCase() throws InterruptedException{
		Set<String> sentinels = new HashSet<String>();
		sentinels.add(masterHost + ":" + slavePort);
		JedisSentinelPool pool = new JedisSentinelPool("192.168.22.135:6379", sentinels);
		try{
			Jedis redis = pool.getResource();
			System.out.println(redis.get("foo"));
		}finally{
			pool.close();
			pool.destroy();
		}
	}
	
	//TODO cluster sample

	public static void main(String[] args) throws InterruptedException {
		JedisClientSample sample = new JedisClientSample();
		//sample.singleServerSampleCase();
		//sample.singleServerPoolCase();
		sample.masterSalvePoolCase();
	}
}
