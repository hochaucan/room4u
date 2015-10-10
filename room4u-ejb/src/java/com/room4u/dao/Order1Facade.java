/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.dao;

import com.room4u.model.Order1;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author NickHo
 */
@Stateless
public class Order1Facade extends AbstractFacade<Order1> implements Order1FacadeLocal {
    @PersistenceContext(unitName = "room4u-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Order1Facade() {
        super(Order1.class);
    }
    
}
