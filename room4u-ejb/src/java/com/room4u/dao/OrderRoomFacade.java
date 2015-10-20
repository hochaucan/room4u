/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.dao;


import com.room4u.model.OrderRoom;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author NickHo
 */
@Stateless
public class OrderRoomFacade extends AbstractFacade<OrderRoom> implements OrderRoomFacadeLocal {
    @PersistenceContext(unitName = "room4u-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderRoomFacade() {
        super(OrderRoom.class);
    }
    
}
