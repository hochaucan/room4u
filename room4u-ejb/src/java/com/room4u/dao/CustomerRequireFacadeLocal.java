/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.dao;

import com.room4u.model.CustomerRequire;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Nick
 */
@Local
public interface CustomerRequireFacadeLocal {

    void create(CustomerRequire customerRequire);

    void edit(CustomerRequire customerRequire);

    void remove(CustomerRequire customerRequire);

    CustomerRequire find(Object id);

    List<CustomerRequire> findAll();

    List<CustomerRequire> findRange(int[] range);

    int count();
    
}
