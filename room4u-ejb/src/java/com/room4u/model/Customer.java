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
    @UniqueConstraint(columnNames = {"AccountCustomer"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByCustId", query = "SELECT c FROM Customer c WHERE c.custId = :custId"),
    @NamedQuery(name = "Customer.findByCustName", query = "SELECT c FROM Customer c WHERE c.custName = :custName"),
    @NamedQuery(name = "Customer.findByAccountCustomer", query = "SELECT c FROM Customer c WHERE c.accountCustomer = :accountCustomer"),
    @NamedQuery(name = "Customer.findByPassword", query = "SELECT c FROM Customer c WHERE c.password = :password"),
    @NamedQuery(name = "Customer.findByPhone", query = "SELECT c FROM Customer c WHERE c.phone = :phone"),
    @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email"),
    @NamedQuery(name = "Customer.findByRegisterDate", query = "SELECT c FROM Customer c WHERE c.registerDate = :registerDate"),
    @NamedQuery(name = "Customer.findByNotified", query = "SELECT c FROM Customer c WHERE c.notified = :notified")})
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 10)
    private String custId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(nullable = false, length = 20)
    private String custName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(nullable = false, length = 20)
    private String accountCustomer;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String notified;
    @OneToMany(mappedBy = "custID")
    private List<FriendIntroduction> friendIntroductionList;
    @OneToMany(mappedBy = "custID")
    private List<FavoriteMark> favoriteMarkList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Transactionprocess> transactionprocessList;
    @OneToMany(mappedBy = "custID")
    private List<CustomerRequire> customerRequireList;

    public Customer() {
    }

    public Customer(String custId) {
        this.custId = custId;
    }

    public Customer(String custId, String custName, String accountCustomer, String password, String phone, String email, Date registerDate, String notified) {
        this.custId = custId;
        this.custName = custName;
        this.accountCustomer = accountCustomer;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.registerDate = registerDate;
        this.notified = notified;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getAccountCustomer() {
        return accountCustomer;
    }

    public void setAccountCustomer(String accountCustomer) {
        this.accountCustomer = accountCustomer;
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

    public String getNotified() {
        return notified;
    }

    public void setNotified(String notified) {
        this.notified = notified;
    }

    @XmlTransient
    public List<FriendIntroduction> getFriendIntroductionList() {
        return friendIntroductionList;
    }

    public void setFriendIntroductionList(List<FriendIntroduction> friendIntroductionList) {
        this.friendIntroductionList = friendIntroductionList;
    }

    @XmlTransient
    public List<FavoriteMark> getFavoriteMarkList() {
        return favoriteMarkList;
    }

    public void setFavoriteMarkList(List<FavoriteMark> favoriteMarkList) {
        this.favoriteMarkList = favoriteMarkList;
    }

    @XmlTransient
    public List<Transactionprocess> getTransactionprocessList() {
        return transactionprocessList;
    }

    public void setTransactionprocessList(List<Transactionprocess> transactionprocessList) {
        this.transactionprocessList = transactionprocessList;
    }

    @XmlTransient
    public List<CustomerRequire> getCustomerRequireList() {
        return customerRequireList;
    }

    public void setCustomerRequireList(List<CustomerRequire> customerRequireList) {
        this.customerRequireList = customerRequireList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custId != null ? custId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.custId == null && other.custId != null) || (this.custId != null && !this.custId.equals(other.custId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.room4u.model.Customer[ custId=" + custId + " ]";
    }
    
}