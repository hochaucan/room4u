/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ViewModel.BookRoom;
import ViewModel.RoomAddress;
import ViewModel.RoomImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.room4u.dao.AccommodationFacadeLocal;
import com.room4u.dao.CommentsFacadeLocal;
import com.room4u.dao.CustomerFacadeLocal;
import com.room4u.dao.OrderRoomFacadeLocal;
import com.room4u.dao.OrderDetailFacadeLocal;
import com.room4u.dao.RatingFacadeLocal;
import com.room4u.model.Accommodation;
import com.room4u.model.Comments;
import com.room4u.model.Customer;
import com.room4u.model.OrderDetail;
import com.room4u.model.OrderRoom;
import com.room4u.model.Rating;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DecimalDV;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessBuilder.Redirect.Type;
import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.primefaces.context.RequestContext;

/**
 *
 * @author NickHo
 */
@ManagedBean(name = "landlord")
@SessionScoped
public class LandlordController {

    @EJB
    private RatingFacadeLocal ratingFacade1;
    @EJB
    private RatingFacadeLocal ratingFacade;
    @EJB
    private OrderDetailFacadeLocal orderDetailFacade;
    @EJB
    private OrderRoomFacadeLocal order1Facade;
    @EJB
    private CommentsFacadeLocal commentsFacade;
    @EJB
    private CustomerFacadeLocal customerFacade;
    @EJB
    private AccommodationFacadeLocal accommodationFacade;

    //@Inject
    // Logger log;
    @ManagedProperty(value = "#{customerBean}")
    private CustomerController customerBean;

    public void setCustomerBean(CustomerController customerBean) {
        this.customerBean = customerBean;
    }

    private Accommodation room = new Accommodation();
    private String houseNumber;
    private String street;
    private String ward;
    private String district;
    private String city;
    private String country;
    private Part thumbnail, file1, file2, file3;
    private List<String> roomImageFileNames;
    private Accommodation curAccom, curAccomUpdate = null;
    private String UpdateRoomResult;
    private String bookRoomDataJson;
    private String deletedRoomResult;
    private String deletedRoomOrderResult;
    private String deletedCustomerOrderRoomResult;
    private String roomFullAddress;
    private String roomPlaceId;
    private String allReceiptId;
    private String deleteAllReceiptResult;
    private String roomOrderId;
    private String getRoomOrderIdResult;

    public String getGetRoomOrderIdResult() {
        return getRoomOrderIdResult;
    }

    public void setGetRoomOrderIdResult(String getRoomOrderIdResult) {
        this.getRoomOrderIdResult = getRoomOrderIdResult;
    }

    public String getRoomOrderId() {
        return roomOrderId;
    }

    public void setRoomOrderId(String roomOrderId) {
        this.roomOrderId = roomOrderId;
    }

    public String getDeleteAllReceiptResult() {
        return deleteAllReceiptResult;
    }

    public void setDeleteAllReceiptResult(String deleteAllReceiptResult) {
        this.deleteAllReceiptResult = deleteAllReceiptResult;
    }

    public String getAllReceiptId() {
        return allReceiptId;
    }

    public void setAllReceiptId(String allReceiptId) {
        this.allReceiptId = allReceiptId;
    }

    public String getRoomPlaceId() {
        return roomPlaceId;
    }

    public void setRoomPlaceId(String roomPlaceId) {
        this.roomPlaceId = roomPlaceId;
    }

    public String getRoomFullAddress() {
        return roomFullAddress;
    }

    public void setRoomFullAddress(String roomFullAddress) {
        this.roomFullAddress = roomFullAddress;
    }

    public String getDeletedCustomerOrderRoomResult() {
        return deletedCustomerOrderRoomResult;
    }

    public void setDeletedCustomerOrderRoomResult(String deletedCustomerOrderRoomResult) {
        this.deletedCustomerOrderRoomResult = deletedCustomerOrderRoomResult;
    }

    public String getDeletedRoomOrderResult() {
        return deletedRoomOrderResult;
    }

    public void setDeletedRoomOrderResult(String deletedRoomOrderResult) {
        this.deletedRoomOrderResult = deletedRoomOrderResult;
    }
    private String deletedComResult;
    private String deletedAccomId;
    private String deletedComId;
    private String deletedRoomOrderId;
    private String deletedCustomerOrderRoomOrderId;

    public String getDeletedCustomerOrderRoomOrderId() {
        return deletedCustomerOrderRoomOrderId;
    }

    public void setDeletedCustomerOrderRoomOrderId(String deletedCustomerOrderRoomOrderId) {
        this.deletedCustomerOrderRoomOrderId = deletedCustomerOrderRoomOrderId;
    }

    public String getDeletedRoomOrderId() {
        return deletedRoomOrderId;
    }

    public void setDeletedRoomOrderId(String deletedRoomOrderId) {
        this.deletedRoomOrderId = deletedRoomOrderId;
    }
    private String slider1, slider2, slider3;
    private int commentsCount;
    private String bookRoomResult;
    private String roomRatingSelected;
    private int displayRate;
    private String commentMessage;

    public String getBookRoomDataJson() {
        return bookRoomDataJson;
    }

    public void setBookRoomDataJson(String bookRoomDataJson) {
        this.bookRoomDataJson = bookRoomDataJson;
    }

    public String getUpdateRoomResult() {
        return UpdateRoomResult;
    }

    public void setUpdateRoomResult(String UpdateRoomResult) {
        this.UpdateRoomResult = UpdateRoomResult;
    }

    public String getDeletedComId() {
        return deletedComId;
    }

    public void setDeletedComId(String deletedComId) {
        this.deletedComId = deletedComId;
    }

    public String getDeletedComResult() {
        return deletedComResult;
    }

    public void setDeletedComResult(String deletedComResult) {
        this.deletedComResult = deletedComResult;
    }

    public String getDeletedRoomResult() {
        return deletedRoomResult;
    }

    public void setDeletedRoomResult(String deletedRoomResult) {
        this.deletedRoomResult = deletedRoomResult;
    }

    public String getDeletedAccomId() {
        return deletedAccomId;
    }

    public void setDeletedAccomId(String deletedAccomId) {
        this.deletedAccomId = deletedAccomId;
    }

    public Accommodation getCurAccomUpdate() {
        return curAccomUpdate;
    }

    public void setCurAccomUpdate(Accommodation curAccomUpdate) {
        this.curAccomUpdate = curAccomUpdate;
    }

    public String getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
    }

    public int getDisplayRate() {
        return displayRate;
    }

    public void setDisplayRate(int displayRate) {
        this.displayRate = displayRate;
    }

    public String getRoomRatingSelected() {
        return roomRatingSelected;
    }

    public void setRoomRatingSelected(String roomRatingSelected) {
        this.roomRatingSelected = roomRatingSelected;
    }

    public String getBookRoomResult() {
        return bookRoomResult;
    }

    public void setBookRoomResult(String bookRoomResult) {
        this.bookRoomResult = bookRoomResult;
    }

    public String getSlider1() {
        return slider1;
    }

    public void setSlider1(String slider1) {
        this.slider1 = slider1;
    }

    public String getSlider2() {
        return slider2;
    }

    public void setSlider2(String slider2) {
        this.slider2 = slider2;
    }

    public String getSlider3() {
        return slider3;
    }

    public void setSlider3(String slider3) {
        this.slider3 = slider3;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Accommodation getCurAccom() {
        return curAccom;
    }

    public void setCurAccom(Accommodation curAccom) {
        this.curAccom = curAccom;
    }

    public Part getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Part thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Part getFile2() {
        return file2;
    }

    public void setFile2(Part file2) {
        this.file2 = file2;
    }

    public Part getFile3() {
        return file3;
    }

    public void setFile3(Part file3) {
        this.file3 = file3;
    }

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Accommodation getRoom() {
        return room;
    }

    public void setRoom(Accommodation room) {
        this.room = room;
    }

    public LandlordController() {

    }

    public void updateComment(Comments com) {
        Comments comment = commentsFacade.find(com.getComId());
        if (comment != null) {
            comment.setAnswer(com.getAnswer());
            commentsFacade.edit(comment);
        }
    }

    public void createComment() {
        try {
            Comments com = new Comments();
            Date date = new Date();
            com.setComId(1);
            com.setAccomId(curAccom);
            com.setCustId(customerBean.getCurCust());
            com.setContent(commentMessage);
            com.setComDate(date);

            commentsFacade.create(com);
            commentMessage = "";
        } catch (Exception ex) {
            printStackTrace();
        }
    }

    public int getCommentsByAccomId(int accomId) {

        return commentsFacade.findCommentsByAccomId(accomId).size();
    }

    public List<Accommodation> displayRoom() {

        return accommodationFacade.findAll();
    }

    public List<Accommodation> displayRoomProfile() {
        return accommodationFacade.findAccomByUser(customerBean.getCurCust().getCustId());
    }

    public List<Comments> displayComments() {
        return commentsFacade.findCommentsByAccomId(curAccom.getAccomId());
    }

    public List<Comments> displayCommentsByUser() {
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        List<Comments> comResult = new ArrayList<Comments>();
        List<Accommodation> accoms = accommodationFacade.findAccomByUser(customerBean.getCurCust().getCustId());
        for (Accommodation accomItem : accoms) {
            List<Comments> coms = commentsFacade.findCommentsByAccomId(accomItem.getAccomId());
            for (Comments comItem : coms) {
                comResult.add(comItem);
            }
        }
        return comResult;
    }

    public String deleteAllReceipt() {
        if (allReceiptId.equals("")) {
            return deleteAllReceiptResult = "notcheckbox";
        } else {

            try {
                String[] ids = allReceiptId.split(",");
                for (String id : ids) {
                    OrderRoom od = order1Facade.find(Integer.parseInt(id));
                    if (od != null) {
                        order1Facade.remove(od);
                    }
                }
                deleteAllReceiptResult = "success";
            } catch (Exception ex) {
                deleteAllReceiptResult = "false";
                printStackTrace();
            }
        }
        return deleteAllReceiptResult;

    }

    public void editRoom() {
        try {
            Accommodation acc = accommodationFacade.find(curAccomUpdate.getAccomId());
            if (acc != null) {
                acc.setAccomName(curAccomUpdate.getAccomName());
                acc.setDescription(curAccomUpdate.getDescription());
                acc.setNoOfBed(curAccomUpdate.getNoOfBed());
                acc.setNoOfPersons(curAccomUpdate.getNoOfPersons());
                acc.setNoOfPersons(curAccomUpdate.getNoOfToilet());
                acc.setPrice(curAccomUpdate.getPrice());

                //Change room images
//                roomImageFileNames = new ArrayList<>();
                Map<String, String> fileNames = new HashMap<String, String>();
                Map<String, Part> files = new HashMap<String, Part>();
                if (thumbnail != null) {
                    files.put("thumbnail", thumbnail);
                }
                if (file1 != null) {
                    files.put("file1", file1);
                }
                if (file2 != null) {
                    files.put("file2", file2);
                }
                if (file3 != null) {
                    files.put("file3", file3);
                }

                // Uploading room image 
                for (String key : files.keySet()) {
                    Part itemFile = files.get(key);
//                    System.out.println("Key = " + key + ", Value = " + value);

//                for (Part itemFile : files) {
                    Date dateForFileName = new Date();
                    DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

                    InputStream inputStream = itemFile.getInputStream();
                    String fileName = dateFormat.format(dateForFileName) + getFilename(itemFile);
//                    roomImageFileNames.add(fileName);

                    if (key.equals("thumbnail")) {
                        fileNames.put(key, fileName);
                    }
                    if (key.equals("file1")) {
                        fileNames.put(key, fileName);
                    }
                    if (key.equals("file2")) {
                        fileNames.put(key, fileName);
                    }
                    if (key.equals("file3")) {
                        fileNames.put(key, fileName);
                    }

                    File file = new File("C:/room4u/images/" + fileName);
                    FileOutputStream outputStream = new FileOutputStream(file);

                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    byte[] buffer = new byte[1000000];
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
//                }
                }
                Gson gson = new Gson();
                RoomImage roomImage = new RoomImage();
                roomImage = gson.fromJson(acc.getImages(), RoomImage.class);

                if (fileNames.get("thumbnail") != null) {
                    roomImage.setThumbnail(fileNames.get("thumbnail"));
                }
                if (fileNames.get("file1") != null) {
                    roomImage.setSlider1(fileNames.get("file1"));
                }
                if (fileNames.get("file2") != null) {
                    roomImage.setSlider2(fileNames.get("file2"));
                }
                if (fileNames.get("file3") != null) {
                    roomImage.setSlider3(fileNames.get("file3"));
                }

                String jsonImage = gson.toJson(roomImage);
                acc.setImages(jsonImage);
                accommodationFacade.edit(acc);
                UpdateRoomResult = "success";
            }
        } catch (Exception ex) {
            UpdateRoomResult = "false";
            printStackTrace();
        }
    }

    public void displayAccomUpdate(Accommodation acc) {
        // curAccomUpdate = null;
        curAccomUpdate = new Accommodation();
        curAccomUpdate.setAccomId(acc.getAccomId());
        curAccomUpdate.setAccomName(acc.getAccomName());
        curAccomUpdate.setDescription(acc.getDescription());
        curAccomUpdate.setAddress(acc.getAddress());
        curAccomUpdate.setNoOfBed(acc.getNoOfBed());
        curAccomUpdate.setNoOfPersons(acc.getNoOfPersons());
        curAccomUpdate.setNoOfToilet(acc.getNoOfToilet());
        curAccomUpdate.setPrice(acc.getPrice());
        curAccomUpdate.setImages(acc.getImages());
        Gson gson = new Gson();
//        RoomImage ri = new RoomImage();
//        ri = gson.fromJson(acc.getImages(), RoomImage.class);
//        curThumbnail = ri.getThumbnail();
//        curImages1 = ri.getSlider1();
//        curImages2 = ri.getSlider2();
//        curImages3 = ri.getSlider3();

        String jsonRoom = gson.toJson(curAccomUpdate);
        UpdateRoomResult = jsonRoom;
    }

    public void deleteComment() {
        deletedComResult = "";
        Comments com = commentsFacade.find(Integer.parseInt(deletedComId));
        if (com != null) {
            commentsFacade.remove(com);
            deletedComResult = "success";
        } else {
            deletedComResult = "false";
        }
    }

    public void deleteRoom() {
        deletedRoomResult = "";
        Accommodation accom = accommodationFacade.find(Integer.parseInt(deletedAccomId));
        if (accom != null) {
            accommodationFacade.remove(accom);
            deletedRoomResult = "success";
        } else {
            deletedRoomResult = "false";
        }
    }

    public void deleteRoomOrder() {
        deletedRoomOrderResult = "";
        OrderRoom roomOrder = order1Facade.find(Integer.parseInt(deletedRoomOrderId));
        if (roomOrder != null) {
            roomOrder.setStatus("Đã xóa");
            order1Facade.edit(roomOrder);
//            order1Facade.remove(roomOrder);
            deletedRoomOrderResult = "success";
        } else {
            deletedRoomOrderResult = "false";
        }
    }

    public void deleteCustomerOrderRoom() {
        deletedCustomerOrderRoomResult = "";
        OrderRoom roomOrder = order1Facade.find(Integer.parseInt(deletedCustomerOrderRoomOrderId));
        if (roomOrder != null) {
            // roomOrder.setStatus("Đã xóa");
            order1Facade.remove(roomOrder);
//            order1Facade.remove(roomOrder);
            deletedCustomerOrderRoomResult = "success";
        } else {
            deletedCustomerOrderRoomResult = "false";
        }
    }

    public String displayRoomDetail(int id) {
        try {
            Accommodation accom = accommodationFacade.find(id);
            if (accom != null) {
                curAccom = new Accommodation();
                curAccom.setAccomName(accom.getAccomName());
                curAccom.setDescription(accom.getDescription());
                curAccom.setAddress(accom.getAddress());
                curAccom.setPrice(accom.getPrice());
                curAccom.setAccomId(id);
                curAccom.setNoOfBed(accom.getNoOfBed());
                curAccom.setNoOfPersons(accom.getNoOfPersons());
                curAccom.setNoOfToilet(accom.getNoOfToilet());
                curAccom.setCustId(customerFacade.find(1));

                List<Comments> commentsCount = commentsFacade.findCommentsByAccomId(id);// != null ? commentsFacade.findCommentsByAccomId(id).size() : 0;
                Gson gson = new Gson();
                RoomImage roomImage = gson.fromJson(accom.getImages(), RoomImage.class);
                // curAccom.setImages(accom.getImages());
                slider1 = roomImage.getSlider1();
                slider2 = roomImage.getSlider2();
                slider3 = roomImage.getSlider3();

                List<Rating> ratings = ratingFacade.findByAccRoomId(id);
                int sumRateScore = 0;
                for (Rating rate : ratings) {
                    sumRateScore += rate.getScore();
                }
                displayRate = ratings.size() > 0 ? sumRateScore / ratings.size() : 0;
            }
        } catch (Exception ex) {
            printStackTrace();
        }
        return "roomdetail";
    }

    public int displayRating(int accomId) {
        List<Rating> ratings = ratingFacade.findByAccRoomId(accomId);
        int sumRateScore = 0;
        for (Rating rate : ratings) {
            sumRateScore += rate.getScore();
        }

        return ratings.size() > 0 ? sumRateScore / ratings.size() : 0;
    }

    public List<OrderRoom> displayOrderRoomByUserByAccom() {
//        List<OrderRoom> ordersByUser = order1Facade.findOrderRoomByUser(customerBean.getCurCust().getCustId());
        List<OrderRoom> ordersByUser = order1Facade.findAll();
        List<OrderRoom> orderRoomResults = new ArrayList<OrderRoom>();
        for (OrderRoom od : ordersByUser) {
            if (od.getOrderDetailList().get(0).getAccomId().getCustId().getCustId().equals(customerBean.getCurCust().getCustId())) {
                orderRoomResults.add(od);
            }
        }

        return orderRoomResults;
    }

    public List<OrderRoom> displayOrderRoomByUser() {
        return order1Facade.findOrderRoomByUser(customerBean.getCurCust().getCustId());

    }

    public List<OrderDetail> orderdetailList;

    public List<OrderDetail> getOrderdetailList() {
        return orderdetailList;
    }

    public void setOrderdetailList(List<OrderDetail> orderdetailList) {
        this.orderdetailList = orderdetailList;
    }

    public String displayOrderDetailByOrderId() {

//        return orderDetailFacade.findOrderDetailByOrderId(roomOrderId != null ? Integer.parseInt(roomOrderId) : 0);
        String id = roomOrderId;
        if (id != null) {
            getRoomOrderIdResult = "success";
            orderdetailList = orderDetailFacade.findOrderDetailByOrderId(Integer.parseInt(id));
        } else {
            getRoomOrderIdResult = "false";
            orderdetailList = orderDetailFacade.findOrderDetailByOrderId(0);
        }
        return getRoomOrderIdResult;
    }

    public void bookRoom() {
        bookRoomResult = "";
        if (customerBean.getCurCust() == null) {
            bookRoomResult = "requiredlogin";
            return;
        }

        try {

            OrderRoom order = new OrderRoom();

            Gson gson = new Gson();
            BookRoom or = new BookRoom();
            java.lang.reflect.Type token = new TypeToken<List<BookRoom>>() {
            }.getType();

            List<BookRoom> bookRoomDetail = gson.fromJson(bookRoomDataJson, token);
            int TotalPrice = 0;
            for (BookRoom br : bookRoomDetail) {
                TotalPrice += br.getPrice();
            }

            order.setOrderId(1);
            order.setCustID(customerBean.getCurCust());
            order.setTotalPrice(new BigDecimal(TotalPrice, MathContext.DECIMAL64));
            order.setOrderDate(new Date());
            order.setStatus("Đã đặt phòng");
            order1Facade.create(order);
            int orderId = order.getOrderId();

            for (BookRoom br : bookRoomDetail) {
                OrderDetail orderDetail = new OrderDetail();
//                OrderDetailPK orderDetailPk = new OrderDetailPK();
//                orderDetailPk.setOrderId(orderId);
//                orderDetailPk.setAccomId(curAccom.getAccomId());
                //   OrderDe orderDetail = new OrderDetail();
//                orderDetail.setOrderDetailPK(orderDetailPk);
                orderDetail.setOrderDetailId(1);
                orderDetail.setOrderId(order);
                orderDetail.setAccomId(curAccom);
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date fromDate = formatter.parse(br.getFromDate());
                Date toDate = formatter.parse(br.getToDate());
                orderDetail.setBookedFromDate(fromDate);
                orderDetail.setBookedToDate(toDate);
                orderDetail.setPrice(new BigDecimal(br.getPrice(), MathContext.DECIMAL64));
                orderDetailFacade.create(orderDetail);
            }
            bookRoomResult = "success";

        } catch (Exception ex) {
            printStackTrace();
            bookRoomResult = "false";
        }
    }

    public String createRoom() {
        try {
//             log.info("call upload...");      
//        log.log(Level.INFO, "content-type:{0}", file.getContentType());
//        log.log(Level.INFO, "filename:{0}", file.getName());
//        log.log(Level.INFO, "submitted filename:{0}", file.getSubmittedFileName());

            Gson gson = new Gson();
            Date date = new Date();
            roomImageFileNames = new ArrayList<>();
            List<Part> files = new ArrayList<Part>();

            files.add(thumbnail);

            files.add(file1);

            files.add(file2);

            files.add(file3);
            // Uploading room image 
            for (Part itemFile : files) {

//                if (itemFile == null) {
//
//                    continue;
//                }
                Date dateForFileName = new Date();
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

                InputStream inputStream = itemFile.getInputStream();
                String fileName = dateFormat.format(dateForFileName) + getFilename(itemFile);
                roomImageFileNames.add(fileName);
                File file = new File("C:/room4u/images/" + fileName);
                FileOutputStream outputStream = new FileOutputStream(file);

                if (!file.exists()) {
                    file.createNewFile();
                }

                byte[] buffer = new byte[1000000];
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
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Room is created!"));

            room.setAccomId(1);
            Customer cust = customerBean.getCurCust();
            room.setCustId(cust);
//            room.setAddress(houseNumber
//                    + " " + street + "phường " + ward + "quận " + district + " " + city);

//             room.setAddress(houseNumber
//                    + "| " + street + "|phường " + ward + "|quận " + district + "| " + city);
            RoomAddress roomAddress = new RoomAddress();
            roomAddress.setPlaceId(roomPlaceId);
            roomAddress.setFullAddress(roomFullAddress);

            room.setAddress(gson.toJson(roomAddress));

            room.setCreatedDate(date);

            room.setCreatedBy(customerBean.getCurCust().getCustName());

            // Store Image File name as json string.
            RoomImage roomImage = new RoomImage();

            roomImage.setThumbnail(roomImageFileNames.get(0));
            roomImage.setSlider1(roomImageFileNames.get(1));
            roomImage.setSlider2(roomImageFileNames.get(2));
            roomImage.setSlider3(roomImageFileNames.get(3));

            String jsonImage = gson.toJson(roomImage);
            room.setImages(jsonImage);

            accommodationFacade.create(room);
            //RequestContext.getCurrentInstance().execute("alert('peek-a-boo');");

        } catch (Exception ex) {
            printStackTrace();
        }

        return "index";
    }

    public void roomRating() {
        //  FacesContext context = FacesContext.getCurrentInstance();
        //context.addMessage(null, new FacesMessage("Successful", "Your message: "));
//        context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
        try {
            Rating rate = new Rating();
            rate.setRateId(1);
            rate.setCustId(curAccom.getCustId());
            rate.setAccomId(curAccom);
            rate.setScore(Integer.parseInt(roomRatingSelected));
            ratingFacade.create(rate);
        } catch (Exception ex) {
            printStackTrace();
        }
    }

// Get File Name when upload file
    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.  
            }
        }
        return null;
    }

    public void displayRoomImages(String images) {
        Gson gson = new Gson();
        RoomImage roomImage = gson.fromJson(images, RoomImage.class);
        curThumbnail = roomImage.getThumbnail();
        curImages1 = roomImage.getSlider1();
        curImages2 = roomImage.getSlider2();
        curImages3 = roomImage.getSlider3();
    }

    public String getCurImages1() {
        return curImages1;
    }

    public void setCurImages1(String curImages1) {
        this.curImages1 = curImages1;
    }

    public String getCurImages2() {
        return curImages2;
    }

    public void setCurImages2(String curImages2) {
        this.curImages2 = curImages2;
    }

    public String getCurImages3() {
        return curImages3;
    }

    public void setCurImages3(String curImages3) {
        this.curImages3 = curImages3;
    }

    String curImages1, curImages2, curImages3, curThumbnail;

    public String getCurThumbnail() {
        return curThumbnail;
    }

    public void setCurThumbnail(String curThumbnail) {
        this.curThumbnail = curThumbnail;
    }

    public String curAcc, curCus;

    public void displayAccName(Accommodation acc) {
        curAcc = acc.getAccomName();
    }

    public void displayCusName(Customer cus) {
        curCus = cus.getCustName();
    }

    public String getCurAcc() {
        return curAcc;
    }

    public void setCurAcc(String curAcc) {
        this.curAcc = curAcc;
    }

    public String getCurCus() {
        return curCus;
    }

    public void setCurCus(String curCus) {
        this.curCus = curCus;
    }

    public List<Comments> getAllComments() {
        return commentsFacade.findAll();
    }
}
