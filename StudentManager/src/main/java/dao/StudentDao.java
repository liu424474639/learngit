package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import entity.Student;
import redis.clients.jedis.Jedis;


public class StudentDao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String, String> map = null;
	int pageSize = 10;
	
	public List<Student> find() {	
		Jedis jedis = new Jedis("101.132.147.78",6379);
	    jedis.auth("admin");
	    Set<String> set = jedis.zrevrange("avgscore",0, 9);
	    List<Student> list  = new ArrayList<Student>();
	    Iterator<String> it1 = set.iterator();
        while(it1.hasNext()){
        	String key = it1.next();
            String id = jedis.hget(key, "id");
            String name = jedis.hget(key, "name");
            //System.out.println(id);
            //System.out.println(name);
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
        return list;
	}
	
	public List<Student> paging(int page) {	
		int start = (page - 1)*pageSize;
		int end = page*pageSize - 1;
		Jedis jedis = new Jedis("101.132.147.78",6379);
	    jedis.auth("admin");
	    Set<String> set = jedis.zrevrange("avgscore",start, end);
	    List<Student> list  = new ArrayList<Student>();
	    Iterator<String> it1 = set.iterator();
        while(it1.hasNext()){
        	String key = it1.next();
            String id = jedis.hget(key, "id");
            String name = jedis.hget(key, "name");
            //System.out.println(id);
            //System.out.println(name);
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
        return list;
	}
	
	public void delete(String id){
		Jedis jedis = new Jedis("101.132.147.78",6379);
	    jedis.auth("admin");
	    jedis.zrem("avgscore", "person:"+id);
	    System.out.println("删除成功");
	    System.out.println(jedis.zscore("avgscore","person:35"));
	}
	
	public void add(String id,String name,String birthday,String description,int avgscore) {
		Jedis jedis = new Jedis("101.132.147.78",6379);
	    jedis.auth("admin");
	    long count = jedis.zcard("avgscore");
	    //Date date = new Date();
//	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
//	    String str = format.format(birthday); 
	    map = new HashMap<String, String>();
		map.put("id", count+1+"");
		map.put("name", name);
		map.put("birthday",birthday);
		map.put("description",description);
		//int avgscore = new Random().nextInt(100);
		map.put("avgscore",avgscore+"");
		//jedis.rpush("personid", i+"");		// 保存用户id
		jedis.hmset("person:" + count+1, map);	// 保存用户信息
		jedis.zadd("avgscore",avgscore, "person:" + id);
		System.out.println("新增成功");
	}
	
	public void update(String id,String name,String birthday,String description,int avgscore) {
		Jedis jedis = new Jedis("101.132.147.78",6379);
	    jedis.auth("admin");
	    //long count = jedis.zcard("avgscore");
	    //Date date = new Date();
//	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
//	    String str = format.format(birthday); 
	    map = new HashMap<String, String>();
		map.put("id", id);
		map.put("name", name);
		map.put("birthday",birthday);
		map.put("description",description);
		//int avgscore = new Random().nextInt(100);
		map.put("avgscore",avgscore+ "");
		//jedis.rpush("personid", i+"");		// 保存用户id
		jedis.hmset("person:" +id, map);	// 保存用户信息
		jedis.zadd("avgscore",avgscore, "person:" + id);
		System.out.println("新增成功");
	}
}
