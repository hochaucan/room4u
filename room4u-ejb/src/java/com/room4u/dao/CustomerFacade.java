/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.dao;

import com.room4u.model.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author NickHo
 */
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> implements CustomerFacadeLocal {
    @PersistenceContext(unitName = "room4u-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }
    
    @Override
    public boolean validateRegusterAccount(String accCust, String email){
        Query q = em.createQuery("SELECT c FROM Customer c WHERE c.accountCustomer= :loginName OR c.email :mail");
        q.setParameter("loginName", accCust);
        q.setParameter("mail", email);
        return q.getResultList().size() > 0;
    }
    
    @Override
    public boolean checkLogin(String u, String p){
        Query q = em.createQuery("SELECT c FROM Customer c WHERE c.accountCustomer= :loginName OR c.password :password");
        q.setParameter("loginName", u);
        q.setParameter("password", p);
        return q.getResultList().size() > 0;
    }
    
    
}
