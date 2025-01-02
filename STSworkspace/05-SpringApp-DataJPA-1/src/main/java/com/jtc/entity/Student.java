package com.jtc.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

	@CreationTimestamp
	@Column(name="Created_Date",updatable=false)
	private LocalDateTime createDate;
	
	@UpdateTimestamp
	@Column(name="Updated_Date", insertable=false)
	private LocalDateTime updateDate;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	public Student(Integer id, String name, Long rank, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.rank = rank;
		this.gender = gender;
	}

	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", rank=" + rank + ", gender=" + gender + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "]";
	}
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

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
