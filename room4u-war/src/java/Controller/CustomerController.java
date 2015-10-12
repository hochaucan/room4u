/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.room4u.dao.CustomerFacade;
import com.room4u.model.Customer;
import java.util.List;
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
    private Customer c = new Customer();
    
    /**
     * Creates a new instance of CustomerController
     */
    public CustomerController() {
        
    }

    public Customer getC() {
        return c;
    }

    public void setC(Customer c) {
        this.c = c;
    }
    
    public List<Customer> getCustList(){
        return this.customerFacade.findAll();
    }
    
    public String add (){
        this.customerFacade.create(this.c);
        this.c = new Customer();
        return "index";
    }
    
    public void delete(Customer c){
        this.customerFacade.remove(c);
    }
    
    public String edit(Customer c){
        this.c = c;
        return "edit";
    }
    
    public String edit(){
        this.customerFacade.edit(this.c);
        this.c = new Customer();
        return "index";
    }
}
