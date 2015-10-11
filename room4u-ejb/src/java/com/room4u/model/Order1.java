/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "Order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Order1.findAll", query = "SELECT o FROM Order1 o"),
    @NamedQuery(name = "Order1.findByOrderId", query = "SELECT o FROM Order1 o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "Order1.findByUnregistedCustomerName", query = "SELECT o FROM Order1 o WHERE o.unregistedCustomerName = :unregistedCustomerName"),
    @NamedQuery(name = "Order1.findByUnregistedCustomerPhone", query = "SELECT o FROM Order1 o WHERE o.unregistedCustomerPhone = :unregistedCustomerPhone"),
    @NamedQuery(name = "Order1.findByUnregistedCustomerEmail", query = "SELECT o FROM Order1 o WHERE o.unregistedCustomerEmail = :unregistedCustomerEmail"),
    @NamedQuery(name = "Order1.findByTotalPrice", query = "SELECT o FROM Order1 o WHERE o.totalPrice = :totalPrice"),
    @NamedQuery(name = "Order1.findByStatus", query = "SELECT o FROM Order1 o WHERE o.status = :status"),
    @NamedQuery(name = "Order1.findByOrderDate", query = "SELECT o FROM Order1 o WHERE o.orderDate = :orderDate")})
public class Order1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "OrderId")
    private Integer orderId;
    @Size(max = 500)
    @Column(name = "UnregistedCustomerName")
    private String unregistedCustomerName;
    @Size(max = 50)
    @Column(name = "UnregistedCustomerPhone")
    private String unregistedCustomerPhone;
    @Size(max = 200)
    @Column(name = "UnregistedCustomerEmail")
    private String unregistedCustomerEmail;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TotalPrice")
    private BigDecimal totalPrice;
    @Size(max = 100)
    @Column(name = "Status")
    private String status;
    @Column(name = "OrderDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @JoinColumn(name = "CustID", referencedColumnName = "CustId")
    @ManyToOne(optional = false)
    private Customer custID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order1")
    private List<OrderDetail> orderDetailList;

    public Order1() {
    }

    public Order1(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getUnregistedCustomerName() {
        return unregistedCustomerName;
    }

    public void setUnregistedCustomerName(String unregistedCustomerName) {
        this.unregistedCustomerName = unregistedCustomerName;
    }

    public String getUnregistedCustomerPhone() {
        return unregistedCustomerPhone;
    }

    public void setUnregistedCustomerPhone(String unregistedCustomerPhone) {
        this.unregistedCustomerPhone = unregistedCustomerPhone;
    }

    public String getUnregistedCustomerEmail() {
        return unregistedCustomerEmail;
    }

    public void setUnregistedCustomerEmail(String unregistedCustomerEmail) {
        this.unregistedCustomerEmail = unregistedCustomerEmail;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustID() {
        return custID;
    }

    public void setCustID(Customer custID) {
        this.custID = custID;
    }

    @XmlTransient
    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order1)) {
            return false;
        }
        Order1 other = (Order1) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.room4u.model.Order1[ orderId=" + orderId + " ]";
    }
    
}
