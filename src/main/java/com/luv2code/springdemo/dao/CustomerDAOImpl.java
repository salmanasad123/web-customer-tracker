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

        // create query (HQL) .. sort by Last Name , use lastName because this is the actual property name on the object
        // don't use the actual column name here
        Query<Customer> theQuery = session.createQuery("from Customer order by lastName", Customer.class);

        // execute query and get the result
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {

        // creat hibernate session
        Session session = sessionFactory.getCurrentSession();

        // save the customer to the database using hibernate
        // we are using the same method saveCustomer() for saving and updating, so we will use a
        // method called saveOrUpdate() and by this hibernate will figure out if it is a new record
        // means the id is null it will perform the save operation and if id is not null it will perform
        // the update
        session.saveOrUpdate(theCustomer);
    }

    @Override
    public Customer getCustomer(int id) {

        // create hibernate session
        Session session = sessionFactory.getCurrentSession();

        // get the customer from database using the id and return it to CustomerServiceImpl
        return session.get(Customer.class, id);
    }

    @Override
    public void deleteCustomer(int id) {

        // create hibernate session
        Session session = sessionFactory.getCurrentSession();

        // delete the customer from database using the id and return it to CustomerServiceImpl
        Query<Customer> theQuery = session.createQuery("delete from Customer where id=:customerId");
        // setting the value for parameter :id
        theQuery.setParameter("customerId", id);

        // execute the query
        theQuery.executeUpdate();
    }
}
