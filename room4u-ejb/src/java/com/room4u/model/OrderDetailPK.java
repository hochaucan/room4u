/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author NickHo
 */
@Embeddable
public class OrderDetailPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "OrderId")
    private int orderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AccomId")
    private int accomId;

    public OrderDetailPK() {
    }

    public OrderDetailPK(int orderId, int accomId) {
        this.orderId = orderId;
        this.accomId = accomId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getAccomId() {
        return accomId;
    }

    public void setAccomId(int accomId) {
        this.accomId = accomId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) orderId;
        hash += (int) accomId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDetailPK)) {
            return false;
        }
        OrderDetailPK other = (OrderDetailPK) object;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.accomId != other.accomId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.room4u.model.OrderDetailPK[ orderId=" + orderId + ", accomId=" + accomId + " ]";
    }
    
}
