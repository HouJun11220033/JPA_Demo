package com.jpa;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Table(name = "CUSTOMERS")
@Entity
public class Customer {
	private Integer id;
	private String lastName;
	private String email;
	private int age;
	private int testTransient;
	private Date testDate;
	private Date Birth;
	private Date CreateTime;
	private Set<Order> orders = new HashSet<>();

	// 注意: 若在 1 的一端的 @OneToMany 中使用 mappedBy 属性, 则 @OneToMany 端就不能再使用
	// @JoinColumn 属性了.
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE }, mappedBy = "customer")
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@Temporal(TemporalType.DATE)
	public Date getBirth() {
		return Birth;
	}

	public void setBirth(Date birth) {
		Birth = birth;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}

	@TableGenerator(name = "ID_GENERATOR", table = "ID_GENERATOR", allocationSize = 100, initialValue = 1, pkColumnName = "PK_NAME", pkColumnValue = "CUSTOMER_ID", valueColumnName = "PK_VALUE")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
	@Id
	@Column(name = "CUSTOMER_ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "LAST_NAME", length = 50, nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Customer() {
		super();
	}

	@Transient
	public int getTestTransient() {
		return testTransient;
	}

	public void setTestTransient(int testTransient) {
		this.testTransient = testTransient;
	}

	@Temporal(TemporalType.TIME)
	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	@Transient
	public String getInfo() {
		return "lastName: " + lastName + ", email: " + email;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", lastName=" + lastName + ", email="
				+ email + ", age=" + age + ", testTransient=" + testTransient
				+ ", testDate=" + testDate + ", Birth=" + Birth
				+ ", CreateTime=" + CreateTime + "]";
	}

}
