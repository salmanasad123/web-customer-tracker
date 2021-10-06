package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// always apply to DAO implementations so spring will register DAO implementation
@Repository
public class CustomerDAOImpl implements CustomerDAO {

    // need to inject session factory because our CustomerDAOImpl needs hibernate session factory
    // to interact with db
    @Autowired
    private SessionFactory sessionFactory;

    // @Transactional so spring will manage the transactions, we dont have to start and stop transactions
    @Override
    public List<Customer> getCustomers() {

        // create hibernate session
        Session session = sessionFactory.getCurrentSession();

        // create query (HQL)
        Query<Customer> theQuery = session.createQuery("from Customer", Customer.class);

        // execute query and get the result
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;
    }
}
