/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.dao;

import com.room4u.model.OrderDetail;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author NickHo
 */
@Local
public interface OrderDetailFacadeLocal {

    void create(OrderDetail orderDetail);
    
    void edit(OrderDetail orderDetail);

    void remove(OrderDetail orderDetail);

    OrderDetail find(Object id);

    List<OrderDetail> findAll();
    List<OrderDetail> findOrderDetailByOrderId(int orderId);

    List<OrderDetail> findRange(int[] range);

    int count();

}
