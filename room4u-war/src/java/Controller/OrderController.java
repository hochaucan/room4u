/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.room4u.dao.OrderRoomFacadeLocal;
import com.room4u.dao.OrderDetailFacadeLocal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author NickHo
 */
@ManagedBean(name = "order")
@SessionScoped
public class OrderController {

    @EJB
    private OrderDetailFacadeLocal orderDetailFacade;
    @EJB
    private OrderRoomFacadeLocal order1Facade;

    /**
     * Creates a new instance of OrderController
     */
    public OrderController() {
    }

    public boolean bookRoom() {

        
        return true;
    }
    
    
}
