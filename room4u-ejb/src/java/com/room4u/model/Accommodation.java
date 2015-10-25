/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author NickHo
 */
@Entity
@Table(name = "Accommodation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accommodation.findAll", query = "SELECT a FROM Accommodation a"),
    @NamedQuery(name = "Accommodation.findByAccomId", query = "SELECT a FROM Accommodation a WHERE a.accomId = :accomId"),
    @NamedQuery(name = "Accommodation.findByAccomName", query = "SELECT a FROM Accommodation a WHERE a.accomName = :accomName"),
    @NamedQuery(name = "Accommodation.findByImages", query = "SELECT a FROM Accommodation a WHERE a.images = :images"),
    @NamedQuery(name = "Accommodation.findByPrice", query = "SELECT a FROM Accommodation a WHERE a.price = :price"),
    @NamedQuery(name = "Accommodation.findByAddress", query = "SELECT a FROM Accommodation a WHERE a.address = :address"),
    @NamedQuery(name = "Accommodation.findByDescription", query = "SELECT a FROM Accommodation a WHERE a.description = :description"),
    @NamedQuery(name = "Accommodation.findByNoOfBed", query = "SELECT a FROM Accommodation a WHERE a.noOfBed = :noOfBed"),
    @NamedQuery(name = "Accommodation.findByNoOfPersons", query = "SELECT a FROM Accommodation a WHERE a.noOfPersons = :noOfPersons"),
    @NamedQuery(name = "Accommodation.findByNoOfToilet", query = "SELECT a FROM Accommodation a WHERE a.noOfToilet = :noOfToilet"),
    @NamedQuery(name = "Accommodation.findByCreatedDate", query = "SELECT a FROM Accommodation a WHERE a.createdDate = :createdDate"),
    @NamedQuery(name = "Accommodation.findByCreatedBy", query = "SELECT a FROM Accommodation a WHERE a.createdBy = :createdBy")})
public class Accommodation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "AccomId")
    private Integer accomId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "AccomName")
    private String accomName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Images")
    private String images;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private double price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "Address")
    private String address;
    @Size(max = 2147483647)
    @Column(name = "Description")
    private String description;
    @Column(name = "NoOfBed")
    private Integer noOfBed;
    @Column(name = "NoOfPersons")
    private Integer noOfPersons;
    @Column(name = "NoOfToilet")
    private Integer noOfToilet;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "CreatedBy")
    private String createdBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accommodation")
    private List<OrderDetail> orderDetailList;
    @OneToMany(mappedBy = "accomId")
    private List<Comments> commentsList;
    @OneToMany(mappedBy = "accomId")
    private List<Rating> ratingList;
    @JoinColumn(name = "CustId", referencedColumnName = "CustId")
    @ManyToOne
    private Customer custId;

    public Accommodation() {
    }

    public Accommodation(Integer accomId) {
        this.accomId = accomId;
    }

    public Accommodation(Integer accomId, String accomName, String images, double price, String address, Date createdDate, String createdBy) {
        this.accomId = accomId;
        this.accomName = accomName;
        this.images = images;
        this.price = price;
        this.address = address;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Integer getAccomId() {
        return accomId;
    }

    public void setAccomId(Integer accomId) {
        this.accomId = accomId;
    }

    public String getAccomName() {
        return accomName;
    }

    public void setAccomName(String accomName) {
        this.accomName = accomName;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNoOfBed() {
        return noOfBed;
    }

    public void setNoOfBed(Integer noOfBed) {
        this.noOfBed = noOfBed;
    }

    public Integer getNoOfPersons() {
        return noOfPersons;
    }

    public void setNoOfPersons(Integer noOfPersons) {
        this.noOfPersons = noOfPersons;
    }

    public Integer getNoOfToilet() {
        return noOfToilet;
    }

    public void setNoOfToilet(Integer noOfToilet) {
        this.noOfToilet = noOfToilet;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @XmlTransient
    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @XmlTransient
    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    @XmlTransient
    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public Customer getCustId() {
        return custId;
    }

    public void setCustId(Customer custId) {
        this.custId = custId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accomId != null ? accomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accommodation)) {
            return false;
        }
        Accommodation other = (Accommodation) object;
        if ((this.accomId == null && other.accomId != null) || (this.accomId != null && !this.accomId.equals(other.accomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.room4u.model.Accommodation[ accomId=" + accomId + " ]";
    }
    
}
