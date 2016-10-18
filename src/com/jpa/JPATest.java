package com.jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JPATest {
	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;
	EntityTransaction transaction;

	@Before
	public void init() {
		entityManagerFactory = Persistence
				.createEntityManagerFactory("HelloJPA");
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		transaction.begin();

	}

	@After
	public void destory() {

		transaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void test1() {

		String a = entityManagerFactory.toString();
		String b = entityManager.toString();
		String c = transaction.toString();
		System.out.println(a + "\n" + b + "\n" + c + "\n");
		Customer customer = new Customer();
		customer.setAge(20);
		customer.setEmail("1144724015@qq.com");
		customer.setLastName("JDroid");
		entityManager.persist(customer);

	}

	@Test
	public void test2() {
		Department dept = new Department();
		dept.setDeptName("AA");
		Manager manager = new Manager();

		manager.setMgrName("JDroid");
		manager.setDept(dept);
		entityManager.persist(manager);

	}

	@Test
	public void testMerge4() {
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreateTime(new Date());
		customer.setEmail("dd@163.com");
		customer.setLastName("FUCK!!!!");
		customer.setId(500);
		Customer customer2 = entityManager.find(Customer.class, 500);
		entityManager.merge(customer);
		System.out.println(customer == customer2);

	}
 
	@Test
	public void testMerge3() {
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreateTime(new Date());
		customer.setEmail("ee@163.com");
		customer.setLastName("!!!!");
		customer.setId(800);

		// 如果没有指定ID就会一直往下面merge
		// 如果制定了ID如果数据库里面没有对应的记录，就直接往下insert
		// 如果有对应的记录则会在指定的记录上面一直merge

		Customer customer2 = entityManager.merge(customer);
		System.out.println(customer == customer2); // false
	}

	@Test
	public void testMerge2() {
		Customer customer = new Customer();
		customer.setAge(99);
		customer.setBirth(new Date());
		customer.setCreateTime(new Date());
		customer.setEmail("dd@163.com");
		customer.setLastName("DD");
		customer.setId(400);
		Customer customer2 = entityManager.merge(customer);

		System.out.println("customer#id:" + customer.getId());
		System.out.println("customer2#id:" + customer2.getId());
	}

	@Test
	public void testMerge1() {
		Customer customer = new Customer();
		customer.setAge(65);
		customer.setBirth(new Date());
		customer.setCreateTime(new Date());
		customer.setEmail("cc@163.com");
		customer.setLastName("CC");
		Customer customer2 = entityManager.merge(customer);
		System.out.println("customer#id:" + customer.getId());
		System.out.println("customer2#id:" + customer2.getId());

	}

	// 但注意: 该方法只能移除 持久化 对象. 而 hibernate 的 delete 方法实际上还可以移除 游离对象.
	@Test
	public void testRemove() {
		// Customer customer = new Customer
		// entityManager.remove(customer);
		Customer customer = entityManager.find(Customer.class, 100);
		entityManager.remove(customer);
	}

	@Test
	public void testPersistence() {
		Customer customer = new Customer();
		customer.setAge(15);
		customer.setBirth(new Date());
		customer.setCreateTime(new Date());
		customer.setEmail("bb@163.com");
		customer.setLastName("BB");
		customer.setId(400);
		entityManager.persist(customer);
		System.out.println(customer.getId());

	}

	@Test
	public void testGetReference() {
		Customer customer = entityManager.getReference(Customer.class, 1);
		// 这个类是一个代理类
		System.out.println(customer.getClass().getName());
		System.out.println(customer);

	}

	public static void main(String[] args) {

		// 1. 创建 EntitymanagerFactory

		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("HelloJPA");
		// Persistence.createEntityManagerFactory(persistenceUnitName);

		// 2. 创建 EntityManager. 类似于 Hibernate 的 SessionFactory
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		// 3. 开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// 4. 进行持久化操作
		Customer customer = new Customer();
		customer.setAge(12);
		customer.setEmail("tom@atguigu.com");
		customer.setLastName("Tom");

		entityManager.persist(customer);

		// 5. 提交事务
		transaction.commit();

		// 6. 关闭 EntityManager
		entityManager.close();

		// 7. 关闭 EntityManagerFactory
		entityManagerFactory.close();
	}

}
