/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.dao;

import com.room4u.model.Rating;
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
public class RatingFacade extends AbstractFacade<Rating> implements RatingFacadeLocal {

    @PersistenceContext(unitName = "room4u-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RatingFacade() {
        super(Rating.class);
    }

    @Override
    public List<Rating> findByAccRoomId(int id) {
        Query q = em.createQuery("SELECT a FROM Rating a WHERE a.accomId.accomId = :u");
        q.setParameter("u", id);
        return q.getResultList();
    }

}
