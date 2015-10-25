/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.dao;

import com.room4u.model.Comments;
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

    @Override
    public List<Comments> findCommentsByAccomId(int AccomId) {
        Query q = em.createQuery("SELECT b FROM Comments b WHERE b.accomId.accomId = :_accomId ");
        q.setParameter("_accomId", AccomId);
        return q.getResultList();
    }

    @Override
    public List<Comments> findCommentsByUser(int userId) {
        Query q = em.createQuery("SELECT b FROM Comments b WHERE b.custId.custId = :_userId ");
        q.setParameter("_userId", userId);
        return q.getResultList();
    }
}
