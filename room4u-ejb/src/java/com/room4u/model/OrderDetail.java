/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NickHo
 */
@Entity
@Table(name = "OrderDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderDetail.findAll", query = "SELECT o FROM OrderDetail o"),
    @NamedQuery(name = "OrderDetail.findByOrderDetailId", query = "SELECT o FROM OrderDetail o WHERE o.orderDetailId = :orderDetailId"),
    @NamedQuery(name = "OrderDetail.findByPrice", query = "SELECT o FROM OrderDetail o WHERE o.price = :price"),
    @NamedQuery(name = "OrderDetail.findByBookedFromDate", query = "SELECT o FROM OrderDetail o WHERE o.bookedFromDate = :bookedFromDate"),
    @NamedQuery(name = "OrderDetail.findByBookedToDate", query = "SELECT o FROM OrderDetail o WHERE o.bookedToDate = :bookedToDate")})
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "OrderDetailId")
    private Integer orderDetailId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price")
    private BigDecimal price;
    @Column(name = "BookedFromDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookedFromDate;
    @Column(name = "BookedToDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookedToDate;
    @JoinColumn(name = "AccomId", referencedColumnName = "AccomId")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Accommodation accomId;
    @JoinColumn(name = "OrderId", referencedColumnName = "OrderId")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private OrderRoom orderId;

    public OrderDetail() {
    }

    public OrderDetail(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getBookedFromDate() {
        return bookedFromDate;
    }

    public void setBookedFromDate(Date bookedFromDate) {
        this.bookedFromDate = bookedFromDate;
    }

    public Date getBookedToDate() {
        return bookedToDate;
    }

    public void setBookedToDate(Date bookedToDate) {
        this.bookedToDate = bookedToDate;
    }

    public Accommodation getAccomId() {
        return accomId;
    }

    public void setAccomId(Accommodation accomId) {
        this.accomId = accomId;
    }

    public OrderRoom getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderRoom orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderDetailId != null ? orderDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDetail)) {
            return false;
        }
        OrderDetail other = (OrderDetail) object;
        if ((this.orderDetailId == null && other.orderDetailId != null) || (this.orderDetailId != null && !this.orderDetailId.equals(other.orderDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.room4u.model.OrderDetail[ orderDetailId=" + orderDetailId + " ]";
    }
    
}
