/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ViewModel.CustImage;
import ViewModel.RepeatPaginator;
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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
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

    private String barLabel = "'JanuaryTest', 'February', 'March', 'April', 'May', 'June', 'July'";

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
    private int uid;
    private List<String> pageList;
    private RepeatPaginator paginator;
    private String customerDeleteId;
    private String recipientAddress;
    private String createUserResult;
    private String editUserResult;
    private String changePasswordResult;

    public String getChangePasswordResult() {
        return changePasswordResult;
    }

    public void setChangePasswordResult(String changePasswordResult) {
        this.changePasswordResult = changePasswordResult;
    }

    public String getEditUserResult() {
        return editUserResult;
    }

    public void setEditUserResult(String editUserResult) {
        this.editUserResult = editUserResult;
    }

    public String getCreateUserResult() {
        return createUserResult;
    }

    public void setCreateUserResult(String createUserResult) {
        this.createUserResult = createUserResult;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getCustomerDeleteId() {
        return customerDeleteId;
    }

    public void setCustomerDeleteId(String customerDeleteId) {
        this.customerDeleteId = customerDeleteId;
    }

    public RepeatPaginator getPaginator() {
        return paginator;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getBarLabel() {
        return barLabel;
    }

    public void setBarLabel(String barLabel) {
        this.barLabel = barLabel;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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

    public int getCurrentRoleID() {
        return curCust == null || curCust.getRoleId() == null ? 2 : curCust.getRoleId().getRoleId();
    }

    public void setCurrentRoleID(int rid) {
        UserRole ur = new UserRole();
        ur.setRoleId(rid);
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

    public void resetPassword(String email) {
        List<Customer> lc = customerFacade.findEmail(email);
        if (lc != null && lc.size() > 0) {
            Customer resetCust = lc.get(0);
            resetCust.setPassword("ROOM4U");
            customerFacade.updatePassword(resetCust.getCustId(), resetCust.getPassword());
            SendMailController smc = new SendMailController();
            smc.sendMailSupport(email, "Lấy lại mật khẩu", "Mật khẩu của bạn được cập nhật mới là 'ROOM4U'");
        }
    }

//    public void resetPassword(String email) {
//        SendMailController smc = new SendMailController();
//        smc.sendMailSupport(email, "Lấy lại mật khẩu", "Mật khẩu của bạn được cập nhật mới là 'ROOM4U'");
////        Customer resetCust = customerFacade.find(uid)
//    }
    public String add() {
        this.customerFacade.create(this.c);
        this.c = new Customer();
        return "index";
    }

    public String delete() {
        Customer cust = customerFacade.find(Integer.parseInt(customerDeleteId));
        if (cust.getCustId() == uid) {
            notifyMessage("Không thể xóa tài khoản đang login");
        } else if (cust.getCustId() == 1) {
            notifyMessage("Không cho phép xóa tài khoản admin");
        } else {
            this.customerFacade.remove(cust);
        }
        paginator = new RepeatPaginator(this.getCustList());
        return "customer";
    }

    private String existEmail;

    public String getExistEmail() {
        return existEmail;
    }

    public void setExistEmail(String existEmail) {
        this.existEmail = existEmail;
    }

    public String edit() {
        editUserResult = "";
        List<Customer> lc = customerFacade.findEmail(curCust.getEmail());

        if (lc != null && !curCust.getEmail().equals(existEmail) ) {
            return editUserResult = "existEmail";
        }

        if (image != null) {
            try {
                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                Part itemFile = image;
                InputStream inputStream = itemFile.getInputStream();
                String fileName = dateFormat.format(date) + getFilename(itemFile);
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

                curCust.setImages(fileName);

            } catch (Exception ex) {
                printStackTrace();
                editUserResult = "false";
            }
        }
        this.customerFacade.edit(this.curCust);
        existEmail = curCust.getEmail();
        editUserResult = "success";
        return editUserResult;
    }

//    public String edit() {
//        if (image != null) {
//            try {
//                Date date = new Date();
//                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//                Part itemFile = image;
//                InputStream inputStream = itemFile.getInputStream();
//                String fileName = dateFormat.format(date) + getFilename(itemFile);
//                File file = new File("C:/room4u/images/" + fileName);
//                FileOutputStream outputStream = new FileOutputStream(file);
//
//                if (!file.exists()) {
//                    file.createNewFile();
//                }
//
//                byte[] buffer = new byte[6096];
//                int bytesRead = 0;
//                while (true) {
//                    bytesRead = inputStream.read(buffer);
//                    if (bytesRead > 0) {
//                        outputStream.write(buffer, 0, bytesRead);
//                    } else {
//                        break;
//                    }
//                }
//                outputStream.close();
//                inputStream.close();
//
//                curCust.setImages(fileName);
//
//            } catch (Exception ex) {
//                printStackTrace();
//            }
//        }
//        this.customerFacade.edit(this.curCust);
//        return null;
//    }
    public String validateRegisterAccount() {
        //return this.customerFacade.validateRegusterAccount(accName, mail) ? "index" : "registerFail";
        return "index";
    }

    public boolean checkAdminRole() {
        if (customerFacade.countAdminRole(c.getCustId()) > 1) {
            return true;
        }
        return false;
    }

    public Customer checkLogin() {

        try {
            List<Customer> customers = customerFacade.checkLogin(accName, password);
            curCust = customers.size() > 0 ? customers.get(0) : null;

            if (curCust != null) {
                HttpSession sess = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                sess.setAttribute("username", accName);
                sess.setAttribute("pwd", password);
                sess.setAttribute("isauthenticated", isAuthenticated);
                roleId = curCust.getRoleId().getRoleId();
                uid = curCust.getCustId();
                existEmail = curCust.getEmail();
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
        createUserResult = "";
        List<Customer> checkMail = customerFacade.findEmail(c.getEmail());
        List<Customer> lc = customerFacade.findCustByUserName(c.getAccountCustomer());

        if (checkMail != null && checkMail.size() > 0) {
            return createUserResult = "existEmail";
        }

        if (lc != null && lc.size() > 0) {
            return createUserResult = "existUsername";
        }

        try {
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Part itemFile = image;
            InputStream inputStream = itemFile.getInputStream();
            String fileName = dateFormat.format(date) + getFilename(itemFile);
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

            c.setImages(fileName);

            c.setCustId(0);
            c.setRegisterDate(date);
            c.setNotified("1");
            c.setIsActived(true);
            UserRole ur = new UserRole(roleId);
            c.setRoleId(ur);
            customerFacade.create(c);

            accName = c.getAccountCustomer();
            password = c.getPassword();
            createUserResult = "success";
            checkLogin();
        } catch (Exception ex) {
            createUserResult = "false";
            printStackTrace();
        }
        return "index?faces-redirect=true";
    }

    public String changePassword() {

        changePasswordResult = "";
        try {
            if (!curCust.getPassword().equals(oldPassword)) {
                return changePasswordResult = "passnotcorrect";
            }
            curCust.setPassword(newPassword);
            customerFacade.edit(curCust);
            changePasswordResult = "success";
        } catch (Exception ex) {
            changePasswordResult = "false";
            printStackTrace();
        }

        return changePasswordResult;
    }

    @PostConstruct
    public void init() {
        paginator = new RepeatPaginator(this.getCustList());

    }

    public void notifyMessage(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(message));
    }

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }

    public static int getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (int) session.getAttribute("userid");
        } else {
            return 0;
        }
    }

}
