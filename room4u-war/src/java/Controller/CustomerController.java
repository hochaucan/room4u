/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ViewModel.CustImage;
import com.google.gson.Gson;
import com.room4u.dao.CustomerFacade;
import com.room4u.dao.CustomerFacadeLocal;
import com.room4u.model.Customer;
import com.room4u.model.UserRole;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Nick
 */
@ManagedBean(name = "customerBean")
@SessionScoped
public class CustomerController {

    @EJB
    private CustomerFacadeLocal customerFacade;

    private String accName;
    private String password;
    private String mail;
    private boolean isAuthenticated = false;
//     @ManagedProperty(value="#{customerBean}")
    private Customer curCust = null;
    private int roleId;
    private Part image;
    private List<String> custImage;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }

    public List<String> getCustImage() {
        return custImage;
    }

    public void setCustImage(List<String> custImage) {
        this.custImage = custImage;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Customer getCurCust() {
        return curCust;
    }

    public void setCurCust(Customer curCust) {
        this.curCust = curCust;
    }

    public boolean isIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    private Customer c = new Customer();

    public Customer getC() {
        return c;
    }

    public void setC(Customer c) {
        this.c = c;
    }

    /**
     * Creates a new instance of CustomerController
     */
    public CustomerController() {

    }

    public String userAuthentication() {

        if (!isAuthenticated) {
            return "/index";
        }
        return "Admin/index";
    }

    public String logout() {
        isAuthenticated = false;
        // Clear session
//        HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//        sess.invalidate();
        curCust = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

//    public String testingAjax() {
//        return accName + " Nick HO " + password;
//    }
    public List<Customer> getCustList() {
        return this.customerFacade.findAll();
    }

    public String add() {
        this.customerFacade.create(this.c);
        this.c = new Customer();
        return "index";
    }

    public void delete(Customer c) {
        this.customerFacade.remove(c);
    }

    public String edit(Customer c) {
        this.c = c;
        return "edit";
    }

    public String edit() {
        this.customerFacade.edit(this.c);
        this.c = new Customer();
        return "index";
    }

    public String validateRegisterAccount() {
        //return this.customerFacade.validateRegusterAccount(accName, mail) ? "index" : "registerFail";
        return "index";
    }

    public Customer checkLogin() {

        try {
            List<Customer> customers = customerFacade.checkLogin(accName, password);
            curCust = customers.size() > 0 ? customers.get(0) : null;

            if (curCust != null) {
                HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                sess.setAttribute("username", accName);
                sess.setAttribute("rold", password);
                sess.setAttribute("isauthenticated", isAuthenticated);
                roleId = curCust.getRoleId().getRoleId();
                return curCust;
            }
        } catch (Exception ex) {
            printStackTrace();
        }
        return null;
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.  
            }
        }
        return null;
    }

    public String createUser() {
        try {
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            custImage = new ArrayList<>();
            List<Part> files = new ArrayList<Part>();

            files.add(image);

            // Uploading room image 
            for (Part itemFile : files) {
                if (itemFile == null) {
                    continue;
                }

                InputStream inputStream = itemFile.getInputStream();
                String fileName = dateFormat.format(date) + getFilename(itemFile);
                custImage.add(fileName);
                File file = new File("C:/room4u/images/" + fileName);
                FileOutputStream outputStream = new FileOutputStream(file);

                if (!file.exists()) {
                    file.createNewFile();
                }

                byte[] buffer = new byte[6096];
                int bytesRead = 0;
                while (true) {
                    bytesRead = inputStream.read(buffer);
                    if (bytesRead > 0) {
                        outputStream.write(buffer, 0, bytesRead);
                    } else {
                        break;
                    }
                }
                outputStream.close();
                inputStream.close();
            }

            // Store Image File name as json string.
            //CustImage ci = new CustImage();
            // ci.setImage(custImage.get(0));
           // Gson gson = new Gson();
            //  String jsonImage = gson.toJson(ci);
            c.setImages(custImage.get(0));

            c.setCustId(0);
            c.setRegisterDate(date);
            c.setNotified("1");
            c.setIsActived(true);
            UserRole ur = new UserRole(roleId);
            c.setRoleId(ur);
            customerFacade.create(c);

        } catch (Exception ex) {
            printStackTrace();
        }
        return "index";
    }

    public void changePassword() {
        if (curCust.getPassword().equals(oldPassword)) {
            if (newPassword == confirmPassword) {
                curCust.setPassword(newPassword);
                customerFacade.edit(curCust);
            } else {
                notifyMessage("Mật khẩu xác nhận không đúng");
            }
        } else {
            notifyMessage("Sai mật khẩu");
        }
    }

    public void notifyMessage(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(message));
    }

}
