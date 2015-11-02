/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.dao;

import com.room4u.model.Customer;
import com.room4u.model.OrderRoom;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author NickHo
 */
@Local
public interface OrderRoomFacadeLocal {

    void create(OrderRoom order1);

    void edit(OrderRoom order1);

    void remove(OrderRoom order1);

    OrderRoom find(Object id);

    List<OrderRoom> findAll();

    List<OrderRoom> findOrderRoomByUser(int custId);

    List<OrderRoom> findRange(int[] range);

    int count();

    

}
