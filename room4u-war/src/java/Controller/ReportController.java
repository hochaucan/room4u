/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.room4u.dao.AccommodationFacadeLocal;
import com.room4u.dao.CommentsFacadeLocal;
import com.room4u.dao.CustomerFacadeLocal;
import com.room4u.dao.CustomerRequireFacadeLocal;
import com.room4u.dao.OrderDetailFacadeLocal;
import com.room4u.dao.OrderRoomFacadeLocal;
import com.room4u.dao.RatingFacadeLocal;
import com.room4u.model.Accommodation;
import com.room4u.model.Comments;
import com.room4u.model.Customer;
import com.room4u.model.CustomerRequire;
import com.room4u.model.OrderRoom;
import com.room4u.model.Rating;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author NickHo
 */
@ManagedBean(name = "report")
@SessionScoped
public class ReportController {

    @EJB
    private CustomerRequireFacadeLocal customerRequireFacade;

    @EJB
    private RatingFacadeLocal ratingFacade;
    @EJB
    private OrderDetailFacadeLocal orderDetailFacade;
    @EJB
    private OrderRoomFacadeLocal orderRoomFacade;
    @EJB
    private CustomerFacadeLocal customerFacade;
    @EJB
    private CommentsFacadeLocal commentsFacade;
    @EJB
    private AccommodationFacadeLocal accommodationFacade;

    public ReportController() {
    }

    private List<OrderRoom> orders;
    private List<Accommodation> rooms;
    private List<Comments> comments;
    private List<Customer> customers;
    private List<CustomerRequire> registerCustomers;

    public List<CustomerRequire> getRegisterCustomers() {
        return registerCustomers;
    }

    public void setRegisterCustomers(List<CustomerRequire> registerCustomers) {
        this.registerCustomers = registerCustomers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public List<Accommodation> getRooms() {
        return rooms;
    }

    public void setRooms(List<Accommodation> rooms) {
        this.rooms = rooms;
    }
    private String reportDate;
    private String Status;
    private String reportPostDate;
    private String reportCommentDate;
    private String reportUserDate;
    private String registerRoomDate;

    public String getRegisterRoomDate() {
        return registerRoomDate;
    }

    public void setRegisterRoomDate(String registerRoomDate) {
        this.registerRoomDate = registerRoomDate;
    }

    public String getReportUserDate() {
        return reportUserDate;
    }

    public void setReportUserDate(String reportUserDate) {
        this.reportUserDate = reportUserDate;
    }

    public String getReportCommentDate() {
        return reportCommentDate;
    }

    public void setReportCommentDate(String reportCommentDate) {
        this.reportCommentDate = reportCommentDate;
    }

    public String getReportPostDate() {
        return reportPostDate;
    }

    public void setReportPostDate(String reportPostDate) {
        this.reportPostDate = reportPostDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public List<OrderRoom> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderRoom> orders) {
        this.orders = orders;
    }

    public String commentRoomByMonth() {
        rooms = new ArrayList<Accommodation>();
        int month = Integer.parseInt(reportCommentDate.split("/")[0]);
        int year = Integer.parseInt(reportCommentDate.split("/")[1]);

        for (Accommodation room : accommodationFacade.findAll()) {
            room.getCommentsList().size();
            room.getRatingList().size();

            int _month = room.getCreatedDate().getMonth() + 1;
            SimpleDateFormat formatNowYear = new SimpleDateFormat("yyyy");

            String currentYear = formatNowYear.format(room.getCreatedDate()); // = '2006'
            int _year = Integer.parseInt(currentYear);

            int sumRateScore = 0;
            List<Rating> ratings = room.getRatingList();
            for (Rating rate : ratings) {
                sumRateScore += rate.getScore();
            }
            int displayRate = ratings.size() > 0 ? sumRateScore / ratings.size() : 0;

            for (Rating rate : ratings) {
                rate.setScore(displayRate);
            }

            if (month == _month && year == _year) {
                rooms.add(room);
            }
        }

        return "report_huy";
    }

    public String registerRoomByMonth() {
        registerCustomers = new ArrayList<CustomerRequire>();
        int month = Integer.parseInt(registerRoomDate.split("/")[0]);
        int year = Integer.parseInt(registerRoomDate.split("/")[1]);

        for (CustomerRequire room : customerRequireFacade.findAll()) {
            int _month = room.getCreatedDate().getMonth() + 1;
            SimpleDateFormat formatNowYear = new SimpleDateFormat("yyyy");

            String currentYear = formatNowYear.format(room.getCreatedDate()); // = '2006'
            int _year = Integer.parseInt(currentYear);

            if (month == _month && year == _year) {
                registerCustomers.add(room);
            }
        }

        return "report_cuong";
    }

    public String postRoomByMonth() {
        rooms = new ArrayList<Accommodation>();
        int month = Integer.parseInt(reportPostDate.split("/")[0]);
        int year = Integer.parseInt(reportPostDate.split("/")[1]);

        for (Accommodation room : accommodationFacade.findAll()) {
            int _month = room.getCreatedDate().getMonth() + 1;
            SimpleDateFormat formatNowYear = new SimpleDateFormat("yyyy");

            String currentYear = formatNowYear.format(room.getCreatedDate()); // = '2006'
            int _year = Integer.parseInt(currentYear);

            if (month == _month && year == _year) {
                rooms.add(room);
            }
        }

        return "report_hung";
    }

    public String registerCustomerByMonth() {
        customers = new ArrayList<Customer>();
        int month = Integer.parseInt(reportUserDate.split("/")[0]);
        int year = Integer.parseInt(reportUserDate.split("/")[1]);

        for (Customer room : customerFacade.findAll()) {
            int _month = room.getRegisterDate().getMonth() + 1;
            SimpleDateFormat formatNowYear = new SimpleDateFormat("yyyy");

            String currentYear = formatNowYear.format(room.getRegisterDate()); // = '2006'
            int _year = Integer.parseInt(currentYear);

            if (month == _month && year == _year) {
                customers.add(room);
            }
        }

        return "report_khai";
    }

    public String bookRoomByMonth() {
//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
//        reportDate = formatter.format(date);
        orders = new ArrayList<OrderRoom>();
        int month = Integer.parseInt(reportDate.split("/")[0]);
        int year = Integer.parseInt(reportDate.split("/")[1]);

        for (OrderRoom room : orderRoomFacade.findAll()) {
            int _month = room.getOrderDate().getMonth() + 1;
            SimpleDateFormat formatNowYear = new SimpleDateFormat("yyyy");

            String currentYear = formatNowYear.format(room.getOrderDate()); // = '2006'
            int _year = Integer.parseInt(currentYear);

            if (room.getStatus().equals(Status) && month == _month && year == _year) {
                orders.add(room);
            } else if (Status.equals("Tất cả")) {
                orders = orderRoomFacade.findAll();
                break;
            }
        }

        return "report_can";
    }

}
