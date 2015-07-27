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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nick
 */
@Entity
@Table(catalog = "room4u", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"AccountLandLord"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LandLord.findAll", query = "SELECT l FROM LandLord l"),
    @NamedQuery(name = "LandLord.findByLandlordId", query = "SELECT l FROM LandLord l WHERE l.landlordId = :landlordId"),
    @NamedQuery(name = "LandLord.findByLandLordName", query = "SELECT l FROM LandLord l WHERE l.landLordName = :landLordName"),
    @NamedQuery(name = "LandLord.findByAccountLandLord", query = "SELECT l FROM LandLord l WHERE l.accountLandLord = :accountLandLord"),
    @NamedQuery(name = "LandLord.findByPassword", query = "SELECT l FROM LandLord l WHERE l.password = :password"),
    @NamedQuery(name = "LandLord.findByPhone", query = "SELECT l FROM LandLord l WHERE l.phone = :phone"),
    @NamedQuery(name = "LandLord.findByEmail", query = "SELECT l FROM LandLord l WHERE l.email = :email"),
    @NamedQuery(name = "LandLord.findByRegisterDate", query = "SELECT l FROM LandLord l WHERE l.registerDate = :registerDate")})
public class LandLord implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 10)
    private String landlordId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(nullable = false, length = 30)
    private String landLordName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(nullable = false, length = 20)
    private String accountLandLord;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(nullable = false, length = 20)
    private String password;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(nullable = false, length = 20)
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registerDate;
    @OneToMany(mappedBy = "landlordId")
    private List<Accommodation> accommodationList;

    public LandLord() {
    }

    public LandLord(String landlordId) {
        this.landlordId = landlordId;
    }

    public LandLord(String landlordId, String landLordName, String accountLandLord, String password, String phone, String email, Date registerDate) {
        this.landlordId = landlordId;
        this.landLordName = landLordName;
        this.accountLandLord = accountLandLord;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.registerDate = registerDate;
    }

    public String getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(String landlordId) {
        this.landlordId = landlordId;
    }

    public String getLandLordName() {
        return landLordName;
    }

    public void setLandLordName(String landLordName) {
        this.landLordName = landLordName;
    }

    public String getAccountLandLord() {
        return accountLandLord;
    }

    public void setAccountLandLord(String accountLandLord) {
        this.accountLandLord = accountLandLord;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @XmlTransient
    public List<Accommodation> getAccommodationList() {
        return accommodationList;
    }

    public void setAccommodationList(List<Accommodation> accommodationList) {
        this.accommodationList = accommodationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (landlordId != null ? landlordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LandLord)) {
            return false;
        }
        LandLord other = (LandLord) object;
        if ((this.landlordId == null && other.landlordId != null) || (this.landlordId != null && !this.landlordId.equals(other.landlordId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.room4u.model.LandLord[ landlordId=" + landlordId + " ]";
    }
    
}
