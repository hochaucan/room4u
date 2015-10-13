/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ViewModel.RoomImage;
import com.google.gson.Gson;
import com.room4u.dao.AccommodationFacadeLocal;
import com.room4u.dao.CustomerFacadeLocal;

import com.room4u.model.Accommodation;
import com.room4u.model.Customer;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.shape.Shape;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author NickHo
 */
@ManagedBean(name = "landlord")
@SessionScoped
public class LandlordController {

    @EJB
    private CustomerFacadeLocal customerFacade;

    @EJB
    private AccommodationFacadeLocal accommodationFacade;

    private Accommodation room = new Accommodation();
    private String houseNumber;
    private String street;
    private String ward;
    private String district;
    private String city;
    private String country;
    private Part thumbnail, file1, file2, file3;
    private List<String> roomImageFileNames;

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

    public List<Accommodation> displayRoom() {
        return accommodationFacade.findAll();
    }

    public String createRoom() {
        try {
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            roomImageFileNames = new ArrayList<>();
            List<Part> files = new ArrayList<Part>();

            files.add(thumbnail);

            files.add(file1);

            files.add(file2);

            files.add(file3);
            // Uploading room image 
            for (Part itemFile : files) {

                if (itemFile == null) {

                    continue;
                }

                InputStream inputStream = itemFile.getInputStream();
                String fileName = dateFormat.format(date) + getFilename(itemFile);
                roomImageFileNames.add(fileName);
                File file = new File("C:/room4u/images/" + fileName);
                FileOutputStream outputStream = new FileOutputStream(file);

                if (!file.exists()) {
                    file.createNewFile();
                }

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
                outputStream.close();
                inputStream.close();
            }

            room.setAccomId(1);
            Customer cust = customerFacade.find(1);
            room.setCustId(cust);
            room.setAddress(houseNumber
                    + " " + street + " " + ward + " " + district + " " + city);
            room.setCreatedDate(date);

            room.setCreatedBy("Can");

            // Store Image File name as json string.
            RoomImage roomImage = new RoomImage();

            roomImage.setThumbnail(roomImageFileNames.get(0));
            roomImage.setSlider1(roomImageFileNames.get(1));
            roomImage.setSlider2(roomImageFileNames.get(2));
            roomImage.setSlider3(roomImageFileNames.get(3));

            Gson gson = new Gson();
            String jsonImage = gson.toJson(roomImage);
            room.setImages(jsonImage);

            accommodationFacade.create(room);

        } catch (Exception ex) {
            printStackTrace();
        }

        return "index";
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
}
