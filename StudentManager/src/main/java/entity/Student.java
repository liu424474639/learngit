package entity;

import java.io.Serializable;
public class Student implements Serializable {

	/**
	 * Student bean
	 */
	private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String birthday;
    private String description;
    private Integer avgscore;
    
    
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getAvgscore() {
		return avgscore;
	}


	public void setAvgscore(Integer avgscore) {
		this.avgscore = avgscore;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Student() {
		
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", birthday=" + birthday + ", description=" + description
				+ ", avgscore=" + avgscore + "]";
	}


	public Student(String id, String name, String birthday, String description, Integer avgscore) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.description = description;
		this.avgscore = avgscore;
	}
	
	

}
