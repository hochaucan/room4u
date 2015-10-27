/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.dao;

import com.room4u.model.Customer;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author NickHo
 */
@Local
public interface CustomerFacadeLocal {

    void create(Customer customer);

    void edit(Customer customer);

    void remove(Customer customer);

    Customer find(Object id);

    List<Customer> findAll();

    List<Customer> findRange(int[] range);

    public List<Customer> checkLogin(String u, String p);

    int count();

    public int countAdminRole(int id);

}
