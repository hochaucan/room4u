/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.room4u.dao.AccommodationFacadeLocal;

import com.room4u.model.Accommodation;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

/**
 *
 * @author NickHo
 */
@ManagedBean(name = "landlord")
@SessionScoped
public class LandlordController {

    @EJB
    private AccommodationFacadeLocal accommodationFacade;

    private Accommodation room = new Accommodation();
    private String houseNumber;
    private String street;
    private String ward;
    private String district;
    private String city;
    private String country;
    private Part file1;

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

    public String createRoom() {
        try {
            //room = new Accommodation();
            Date date = new Date();
            //DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

//            Accommodation r = new Accommodation();
//            r.setAccomId(2);
//            r.setAccomName("can");
//            r.setAddress("klshdkfsd");
//            r.setImages("klshdf");
//            r.setDescription("ksdkfkh");
//            r.setNoOfBed(3);
//            r.setNoOfPersons(4);
//            r.setPrice(30);
//            r.setCreatedDate(date);
//            r.setCreatedBy("Oanh");
            room.setAccomId(1);
            room.setImages("test.jpg");
            room.setAddress(houseNumber + " " + street + " " + ward + " " + district + " " + city);

            room.setCreatedDate(date);
            room.setCreatedBy("Can");
            accommodationFacade.create(room);

        } catch (Exception ex) {
            printStackTrace();
        }

        return "index";
    }

    public String upload() throws IOException {
        InputStream inputStream = file1.getInputStream();
        FileOutputStream outputStream = new FileOutputStream("D:/Lock/" + getFilename(file1));

        byte[] buffer = new byte[4096];
        int bytesRead = 0;
        while (true) {
            bytesRead = inputStream.read(buffer);
            if (bytesRead > 0) {
                outputStream.write(buffer, 0, bytesRead);
            } else {
                break;
            }
        }
        // file1.write("D:\\Lock\\"+getFilename(file1));
        outputStream.close();
        inputStream.close();

        return "faq";
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
}
