/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.dao;

import com.room4u.model.Customer;
import java.util.List;
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
    public List<Customer> checkLogin(String u, String p) {
        Query q = em.createQuery("SELECT a FROM Customer a WHERE a.accountCustomer = :u AND a.password = :p");
        q.setParameter("u", u);
        q.setParameter("p", p);
        return q.getResultList();
    }

    public int countAdminRole(int id) {
        Query q = em.createNamedQuery("SELECT count (a.roleId) FROM Customer a WHERE a.roleId = :id");
        int count = (int) q.getResultList().size();
        return count;
    }

}
