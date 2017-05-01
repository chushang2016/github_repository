
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * @author Perfect Is Shit
 */
public final class RedisUtil {
	private static String ipAddress="127.0.0.1";
	private static int port=6379;
	private static String password="123456";
	private static int max_active=1024;
	private static int max_idle=200;
	private static int max_wait=10000;
	private static int timeout=1000;
	
	 private static boolean TEST_ON_BORROW = true;
	private static JedisPool jedisPool=null;
	
	static{
		JedisPoolConfig config=new JedisPoolConfig();
		config.setMaxTotal(max_active);
		config.setMaxIdle(max_idle);
		config.setMaxWaitMillis(max_wait);
		config.setTestOnBorrow(TEST_ON_BORROW);
		jedisPool=new JedisPool(config, ipAddress, port,timeout,password);
	}
	
	/**
	 */
	public synchronized static  Jedis getRedisConnection(){
		if(jedisPool!=null){
			Jedis jedis=jedisPool.getResource();
			return jedis;
		}
		return null;
	}
	
	/**
	 */
	public static void returnResource(final Jedis jedis){
		if(jedis!=null){
			jedisPool.returnResourceObject(jedis);
		}
	}
	
}
