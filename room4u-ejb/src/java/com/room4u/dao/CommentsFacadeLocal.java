/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.dao;

import com.room4u.model.Comments;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author NickHo
 */
@Local
public interface CommentsFacadeLocal {

    void create(Comments comments);

    void edit(Comments comments);

    void remove(Comments comments);

    Comments find(Object id);

    List<Comments> findAll();

    List<Comments> findRange(int[] range);

    List<Comments> findCommentsByAccomId(int AccomId);

    int count();

}
