package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject sessionFactory
	@Autowired
	public SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

		// get the current session
		Session session = sessionFactory.getCurrentSession();

		// create the query..order by last name
		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);

		// execute query and get the list of customers
		List<Customer> customers = query.getResultList();

		// return
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		// get the current session
		Session session = sessionFactory.getCurrentSession();

		// save the customer
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int theId) {
		// get the current session
		Session session = sessionFactory.getCurrentSession();

		// get the customer
		Customer customer = session.get(Customer.class, theId);

		return customer;
	}

	@Override
	public void delete(int theId) {
		// get the current session
		Session session = sessionFactory.getCurrentSession();

//		// get the customer
//		Customer customer = session.get(Customer.class, theId);
//
//		// delete the customer
//		session.delete(customer);
		
		//
		Query theQuery = session.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
		

	}

}
