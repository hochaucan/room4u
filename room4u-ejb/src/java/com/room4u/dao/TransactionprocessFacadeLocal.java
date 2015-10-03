/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.dao;

import com.room4u.model.Transactionprocess;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Nick
 */
@Local
public interface TransactionprocessFacadeLocal {

    void create(Transactionprocess transactionprocess);

    void edit(Transactionprocess transactionprocess);

    void remove(Transactionprocess transactionprocess);

    Transactionprocess find(Object id);

    List<Transactionprocess> findAll();

    List<Transactionprocess> findRange(int[] range);

    int count();
    
}
