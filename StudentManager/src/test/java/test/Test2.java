package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import dao.StudentDao;
import entity.Student;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import util.SerializeUtil;

public class Test2 {

	Map<String, String> map = null;
	@Test
    public void connection() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("101.132.147.78",6379);
        jedis.auth("admin");
        System.out.println("连接成功");
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
    }
	
	@Test
	public void test2(){
		//List<Student> list = new ArrayList<Student>();
	    Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    System.out.println();
	    Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //存储数据到列表中
        jedis.lpush("student1", "id"+1);
        jedis.lpush("student1", "name"+1);
        jedis.lpush("student1", "Taobao"+1);
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("student1", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i)); 
        }
        
	}
	
//	@Test
//	public void Aoo(){
//		Jedis jedis = new Jedis("101.132.147.78",6379);
//	    jedis.auth("admin");
//	    System.out.println("连接成功");
//		List<Student> list = new ArrayList<Student>();
//		StudentDao dao = new  StudentDao();
//		for(int i=0; i<10; i++){
//			Student student = dao.getStudent(i);
//			System.out.println(student.getId());
//			System.out.println(student.getName());
//			System.out.println(student.getBirthday());
//			System.out.println(student.getDescription());
//			System.out.println(student.getAvgscore());
//			list.add(student);
//		}
//	}
	
//	@Test
//	public void  test3(){
//		int length =100;
//		Jedis jedis = new Jedis("101.132.147.78",6379);
//		
//		for(int i=0; i<length; i++){
//			int avgscore = (int)Math.random()*100;
//			Date date = new Date();
//			Student student = new Student(String.valueOf(i), "zhangsan"+i,date,"good student"+i,i);
//			jedis.set(("student:"+i).getBytes(), SerializeUtil.serialize(student));
//			//list.add(student);
//		}
//	}
	
	
	@Test
	public void set() {
		Jedis jedis = new Jedis("101.132.147.78",6379);
	    jedis.auth("admin");
	    for(int i=0; i<50; i++){
	    	//String id = new Random().nextInt(70) + "";
		    Date date = new Date();
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		    String str = format.format(date); 
			map = new HashMap<String, String>();
			map.put("id", i+"");
			map.put("name", "狗蛋"+new Random().nextInt(70) + "");
			map.put("birthday",str);
			map.put("description", "帅气的小伙子"+new Random().nextInt(100) + "");
			int avgscore = new Random().nextInt(100);
			map.put("avgscore",avgscore + "");
			//jedis.rpush("personid", i+"");		// 保存用户id
			jedis.hmset("person:" + i, map);	// 保存用户信息
			jedis.zadd("avgscore",avgscore, "person:" + i+"");
			System.out.println(jedis.hgetAll("person:" + i+""));
			
			
	    }
	}
	
	@Test
	public void findAll() {
		Jedis jedis = new Jedis("101.132.147.78",6379);
	    jedis.auth("admin");
	    Long count = jedis.llen("personid");		// 用户总数
	    for(int i = 0; i<count; i++){
	    	
	    }
	}
	
	@Test
	public void getUserList(){
		Jedis jedis = new Jedis("101.132.147.78",6379);
	    jedis.auth("admin");
		Long count = jedis.llen("personid");		// 用户总数
		List<Student> list = new ArrayList<Student>();
		List<String> idList = jedis.lrange("personid", 0, count);
		for(String personid : idList){
			//System.out.println(personid);
				String id = jedis.hget("person:"+personid, "id");
				//System.out.println(id);
				String name = jedis.hget("person:"+personid, "name");
				String birthday = jedis.hget("person:"+personid, "birthday");
				String description = jedis.hget("person:"+personid, "description");
				String avgscore = jedis.hget("person:"+personid, "avgscore");
				Student student = new Student();
				student.setId(id);
				student.setName(name);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				   Date date = null; 
				   try { 
				    date = sdf.parse(birthday); 
				   } catch (ParseException e) { 
				    e.printStackTrace(); 
				   } 
				student.setBirthday(birthday);
				student.setDescription(description);
				student.setAvgscore(Integer.valueOf(avgscore));
				list.add(student);
			}
        System.out.println("设置成功");
	}
	
	@Test
	public void delete(){
		Jedis jedis = new Jedis("101.132.147.78",6379);
	    jedis.auth("admin");
		jedis.del("person:" + 0);
		jedis.del("personid"+ 0);
		System.out.println("删除成功");

	}
	
	@Test
	public void find() {
		Jedis jedis = new Jedis("101.132.147.78",6379);
	    jedis.auth("admin");
	    //System.out.println(jedis.zrange("student", 0, -1));
	    //System.out.println(jedis.zcard("student"));
	    Set<String> set = jedis.zrevrange("avgscore",0, -1);
	    List<Student> list  = new ArrayList<Student>();
	    Iterator<String> it1 = set.iterator();
        while(it1.hasNext()){
        	String key = it1.next();
            String id = jedis.hget(key, "id");
            String name = jedis.hget(key, "name");
            
            System.out.println(id);
            System.out.println(name);
            
            String birthday = jedis.hget(key, "birthday");
            //System.out.println(birthday);
            String description = jedis.hget(key, "description");
			String avgscore = jedis.hget(key, "avgscore");
			Student student = new Student();
			student.setId(id);
			student.setName(name);
			student.setBirthday(birthday);
			student.setDescription(description);
			student.setAvgscore(Integer.valueOf(avgscore));
			list.add(student);
			for(Student s : list) {
				System.out.println(s.getBirthday());
			}
        }
        long count = jedis.zcard("avgscore");
        System.out.println(count);
        System.out.println("find成功");
	}
	
	@Test
	public void paging() {
		int start = 10;
		int end = 19;
		Jedis jedis = new Jedis("101.132.147.78",6379);
	    jedis.auth("admin");
	    //System.out.println(jedis.zrange("student", 0, -1));
	    //System.out.println(jedis.zcard("student"));
	    Set<String> set = jedis.zrevrange("avgscore",start, end);
	    List<Student> list  = new ArrayList<Student>();
	    Iterator<String> it1 = set.iterator();
        while(it1.hasNext()){
        	String key = it1.next();
            String id = jedis.hget(key, "id");
            String name = jedis.hget(key, "name");
            
            System.out.println(id);
            System.out.println(name);
            
            String birthday = jedis.hget(key, "birthday");
            //System.out.println(birthday);
            String description = jedis.hget(key, "description");
			String avgscore = jedis.hget(key, "avgscore");
			Student student = new Student();
			student.setId(id);
			student.setName(name);
			student.setBirthday(birthday);
			student.setDescription(description);
			student.setAvgscore(Integer.valueOf(avgscore));
			list.add(student);
        }
        long count = jedis.zcard("avgscore");
        System.out.println(count);
        System.out.println("find成功");
	}
	
	@Test
	public void del(){
		Jedis jedis = new Jedis("101.132.147.78",6379);
	    jedis.auth("admin");
	    jedis.zrem("avgscore", "person:35");
	    System.out.println("删除成功");
	    System.out.println(jedis.zscore("avgscore","person:35"));
	}
	
	@Test
	public void add() {
		Jedis jedis = new Jedis("101.132.147.78",6379);
	    jedis.auth("admin");
	    long count = jedis.zcard("avgscore");
	    Date date = new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    String str = format.format(date); 
	    map = new HashMap<String, String>();
		map.put("id", count+1+"");
		map.put("name", "李四"+new Random().nextInt(70) + "");
		map.put("birthday",str);
		map.put("description", "新增的"+new Random().nextInt(100) + "");
		int avgscore = new Random().nextInt(100);
		map.put("avgscore",99+ "");
		//jedis.rpush("personid", i+"");		// 保存用户id
		jedis.hmset("person:" + count+1, map);	// 保存用户信息
		jedis.zadd("avgscore",avgscore, "person:" + count+1+"");
		System.out.println("新增成功");
	}
	
	@Test
	public void update() {
		Jedis jedis = new Jedis("101.132.147.78",6379);
	    jedis.auth("admin");
	    long count = jedis.zcard("avgscore");
	    Date date = new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    String str = format.format(date); 
	    map = new HashMap<String, String>();
		map.put("id", count+1+"");
		map.put("name", "李四"+new Random().nextInt(70) + "");
		map.put("birthday",str);
		map.put("description", "新增的"+new Random().nextInt(100) + "");
		int avgscore = new Random().nextInt(100);
		map.put("avgscore",99+ "");
		//jedis.rpush("personid", i+"");		// 保存用户id
		jedis.hmset("person:" + count+1, map);	// 保存用户信息
		jedis.zadd("avgscore",avgscore, "person:" + count+1+"");
		System.out.println("新增成功");
	}
	
	
	@Test
	public void test(){
		Jedis jedis = new Jedis("101.132.147.78",6379);
	    jedis.auth("admin");
	    long c = jedis.zcard("avgscore");
	    String count = String.valueOf(c+1); 
	    System.out.println(count);
	}

}
