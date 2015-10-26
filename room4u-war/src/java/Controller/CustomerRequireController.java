 package Controller;
 
 import ViewModel.RequirePosition;
import ViewModel.RoomImage;
 import com.google.gson.Gson;
 import com.room4u.dao.CustomerFacade;
 import com.room4u.dao.CustomerRequireFacadeLocal;
 import com.room4u.model.Customer;
 import com.room4u.model.CustomerRequire;
 import static com.sun.corba.se.impl.util.Utility.printStackTrace;
 import java.text.DateFormat;
 import java.text.SimpleDateFormat;
import java.util.ArrayList;
 import java.util.Date;
import java.util.List;
 import javax.ejb.EJB;
 import javax.faces.bean.ManagedBean;
 import javax.faces.bean.ManagedProperty;
 import javax.faces.bean.SessionScoped;
 
 /**
  *
  * @author JixthSune
  */
 @ManagedBean(name = "custReq")
 @SessionScoped
 public class CustomerRequireController {
     @EJB
     private CustomerRequireFacadeLocal customerRequireFacade;
     
     @ManagedProperty(value = "#{customerBean}")
     private CustomerController customerBean;
 
     CustomerRequire curCustReq;
     
     private String requireResult;
     private String latt;
     private String longt;
     private String rad;
    private String address;
    private String CusreqDel;
    
    private String getlatt;
    private String getlongt;
    private String getrad;
    private String getaddress;

    public String getGetaddress() {
        return getaddress;
    }

    public void setGetaddress(String getaddress) {
        this.getaddress = getaddress;
    }
     /**
      * Creates a new instance of CustomerRequireController
      */
     public CustomerRequireController() {
     }
 
     /**
      * @param customerBean the customerBean to set
      */
     public void setCustomerBean(CustomerController customerBean) {
         this.customerBean = customerBean;
     }
     
     public void createCustReq()
     {
         if (customerBean.getCurCust() == null) {
 //            Customer tmp = customerBean.getCurCust();
             requireResult = "requiredlogin";
             return;
         }
         
         try {
             curCustReq = new CustomerRequire();
             curCustReq.setCustReqId(1);
             curCustReq.setCustID(customerBean.getCurCust());
             RequirePosition reqPos = new RequirePosition();
             reqPos.setLattitude(latt);
             reqPos.setLongtitude(longt);
             reqPos.setRadius(rad);
            reqPos.setAddress(address);
             
             Gson gson = new Gson();
             String jsonPos = gson.toJson(reqPos);
             curCustReq.setRequirement(jsonPos);
             
             Date date = new Date();
             DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
             curCustReq.setCreatedDate(date);
             
             customerRequireFacade.create(curCustReq);
             
             requireResult="success";
             
         } catch (Exception ex) {
             printStackTrace();
             requireResult = "false";
         }
         
     }
    
    public List<CustomerRequire> getRequires()
    {
        return customerRequireFacade.findAll();
    }
    
    public List<CustomerRequire> getRequiresByCusID()
    { 
        List<CustomerRequire> listAll = customerRequireFacade.findAll();
        List<CustomerRequire> listUser = new ArrayList<>();
        String id = customerBean.getCurCust().toString();
        for(int i =0;i< listAll.size();i++)
        {
            String idAll = listAll.get(i).getCustID().toString();
            if(id.equals(idAll))
            {
                listUser.add(listAll.get(i));
            }
        }
        return listUser;
    }
 
    public void getAddress(String req)
    {
        Gson gson = new Gson();
        RequirePosition reqPos = gson.fromJson(req, RequirePosition.class);
        getlongt = reqPos.getLongtitude();
        getlatt = reqPos.getLattitude();
        getrad = reqPos.getRadius();
        getaddress = reqPos.getAddress();
    }
    
    public void deleteReq() {
        try{
            CustomerRequire cusreq = customerRequireFacade.find(Integer.parseInt(CusreqDel));
            customerRequireFacade.remove(cusreq);
        }catch(Exception ex)
        {
            printStackTrace();
        }
    }
     /**
      * @return the latt
      */
     public String getLatt() {
         return latt;
     }
 
     /**
      * @param latt the latt to set
      */
     public void setLatt(String latt) {
         this.latt = latt;
     }
 
     /**
      * @return the longt
      */
     public String getLongt() {
         return longt;
     }
 
     /**
      * @param longt the longt to set
      */
     public void setLongt(String longt) {
         this.longt = longt;
     }
 
     /**
      * @return the rad
      */
     public String getRad() {
         return rad;
     }
 
     /**
      * @param rad the rad to set
      */
     public void setRad(String rad) {
         this.rad = rad;
     }
 
     /**
      * @return the requireResult
      */
     public String getRequireResult() {
         return requireResult;
     }
 
     /**
      * @param requireResult the requireResult to set
      */
     public void setRequireResult(String requireResult) {
         this.requireResult = requireResult;
     }

    /**
     * @return the getlatt
     */
    public String getGetlatt() {
        return getlatt;
    }

    /**
     * @param getlatt the getlatt to set
     */
    public void setGetlatt(String getlatt) {
        this.getlatt = getlatt;
    }

    /**
     * @return the getlongt
     */
    public String getGetlongt() {
        return getlongt;
    }

    /**
     * @param getlongt the getlongt to set
     */
    public void setGetlongt(String getlongt) {
        this.getlongt = getlongt;
    }

    /**
     * @return the getrad
     */
    public String getGetrad() {
        return getrad;
    }

    /**
     * @param getrad the getrad to set
     */
    public void setGetrad(String getrad) {
        this.getrad = getrad;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the CusreqDel
     */
    public String getCusreqDel() {
        return CusreqDel;
    }

    /**
     * @param CusreqDel the CusreqDel to set
     */
    public void setCusreqDel(String CusreqDel) {
        this.CusreqDel = CusreqDel;
    }
     
 }