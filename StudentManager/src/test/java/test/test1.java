package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import entity.Student;
import redis.clients.jedis.Jedis;

public class test1 {

	
	@Test
	public void test1() {
		List<Student> list = new ArrayList<Student>();
	    Jedis jedis = new Jedis("localhost");
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String date = sdf.format(new Date());
		String s = null;
		for(int i=0; i<100; i++){
			s="s" + i;
			//System.out.println(s);
			jedis.lpush(s, "1");
	        jedis.lpush(s, "zhangsan");
	        jedis.lpush(s, "2018-7-19");
	        jedis.lpush(s, "hao");
	        jedis.lpush(s, "80");
	        
		}
		List<String> student = jedis.lrange(s, 0 ,10);
        for(int j=0; j<list.size(); j++) {
            System.out.println("列表项为: "+list.get(j));
        }
		
	}

}
