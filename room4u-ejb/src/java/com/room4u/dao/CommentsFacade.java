/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.dao;

import com.room4u.model.Comments;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author NickHo
 */
@Stateless
public class CommentsFacade extends AbstractFacade<Comments> implements CommentsFacadeLocal {
    @PersistenceContext(unitName = "room4u-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentsFacade() {
        super(Comments.class);
    }
 
    public boolean validateDuplicateAccount(String accCust, String email){
        Query q = em.createQuery("SELECT c FROM Customer c WHERE c.accountCustomer= :loginName OR c.email :mail");
        q.setParameter("loginName", accCust);
        q.setParameter("mail", email);
        return q.getResultList().size() > 0;
    }
}
