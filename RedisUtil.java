package cn.redis.demo1;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * @author Perfect Is Shit
 *	Redis的工具类，用来获取redis的连接
 */
public final class RedisUtil {
	//redis服务器的ip
	private static String ipAddress="127.0.0.1";
	//redis服务器的端口
	private static int port=6379;
	//redis的访问密码
	private static String password="123456";
	//redis连接池可连接实例的最大数目
	private static int max_active=1024;
	//redis连接池最大可空闲的数目
	private static int max_idle=200;
	//获取redis连接的最大等待时间，超过这个事件就会抛异常
	private static int max_wait=10000;
	private static int timeout=1000;
	
	 private static boolean TEST_ON_BORROW = true;
	//redis连接池
	private static JedisPool jedisPool=null;
	
	//初始化连接池
	static{
		JedisPoolConfig config=new JedisPoolConfig();
		config.setMaxTotal(max_active);
		config.setMaxIdle(max_idle);
		config.setMaxWaitMillis(max_wait);
		config.setTestOnBorrow(TEST_ON_BORROW);
		jedisPool=new JedisPool(config, ipAddress, port,timeout,password);
	}
	
	/**
	 * 获取jedis连接的方法
	 */
	public synchronized static  Jedis getRedisConnection(){
		if(jedisPool!=null){
			Jedis jedis=jedisPool.getResource();
			return jedis;
		}
		return null;
	}
	
	/**
	 * 释放jedis连接
	 */
	public static void returnResource(final Jedis jedis){
		if(jedis!=null){
			jedisPool.returnResourceObject(jedis);
		}
	}
	
}
