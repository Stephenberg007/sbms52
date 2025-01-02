package com.jtc.Entity;

import jakarta.persistence.Column; 
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Student_Dtls")
public class Student {
	@Id
	@Column(name="Student_id")
	private Integer id;
	
	@Column(name="Student_name")
	private String name;
	
	@Column(name="Student_Rank")
	private Long rank;
	
	@Column(name="Student_gender")
	private String gender;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", rank=" + rank + ", gender=" + gender + "]";
	}

	public Student(Integer id, String name, Long rank, String gender) {
		//System.out.println("Param cons executed");
		super();
		this.id = id;
		this.name = name;
		this.rank = rank;
		this.gender = gender;
		System.out.println("Param cons executed");
	}
	public Student() {
		System.out.println("Def cons executed");
	}

}
