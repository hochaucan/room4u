/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.dao;

import com.room4u.model.Accommodation;
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
public class AccommodationFacade extends AbstractFacade<Accommodation> implements AccommodationFacadeLocal {

    @PersistenceContext(unitName = "room4u-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccommodationFacade() {
        super(Accommodation.class);
    }

    @Override
    public List<Accommodation> findAccomByUser(int userId) {
        Query q = em.createQuery("SELECT b FROM Accommodation b WHERE b.custId.custId = :_userId ORDER BY b.accomId DESC ");
        q.setParameter("_userId", userId);
        return q.getResultList();
    }

    @Override
    public List<Accommodation> findAllWithSortDesc() {
        Query q = em.createQuery("SELECT b FROM Accommodation b  ORDER BY b.accomId DESC ");
        return q.getResultList();
    }

}
