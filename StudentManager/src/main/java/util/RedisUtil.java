package util;

import redis.clients.jedis.Jedis;

public class RedisUtil {

	public Jedis RedisUtil() {
		Jedis jedis = new Jedis("101.132.147.78",6379);
	    jedis.auth("admin");
	    return jedis;
	}
    
}
