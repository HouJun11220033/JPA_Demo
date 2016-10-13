package com.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

public class TestJPA {

	@Test
	public void helloJPA() {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("HelloJPA");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		String a = entityManagerFactory.toString();
		String b = entityManager.toString();
		String c = transaction.toString();
		System.out.println(a + b + c);
		Customer customer = new Customer();
		customer.setAge(20);
		customer.setEmail("1144724015@qq.com");
		customer.setLastName("JDroid");
		entityManager.persist(customer);
		transaction.commit();
		entityManager.close();
		entityManagerFactory.close();

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
