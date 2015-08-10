/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.room4u.dao.CustomerFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Nick
 */
@ManagedBean
@SessionScoped
public class CustomerController {
    @EJB
    private CustomerFacade customerFacade;

    /**
     * Creates a new instance of CustomerController
     */
    public CustomerController() {
        
    }
    
}
