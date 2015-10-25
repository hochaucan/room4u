/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.dao;

import com.room4u.model.Accommodation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author NickHo
 */
@Local
public interface AccommodationFacadeLocal {

    void create(Accommodation accommodation);

    void edit(Accommodation accommodation);

    void remove(Accommodation accommodation);

    Accommodation find(Object id);

    List<Accommodation> findAll();
    
    List<Accommodation> findAccomByUser(int userId);


    List<Accommodation> findRange(int[] range);

    int count();
    
}
