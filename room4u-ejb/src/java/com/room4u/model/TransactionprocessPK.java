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
import javax.validation.constraints.Size;

/**
 *
 * @author Nick
 */
@Embeddable
public class TransactionprocessPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CustID")
    private String custID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "AccomID")
    private String accomID;

    public TransactionprocessPK() {
    }

    public TransactionprocessPK(String custID, String accomID) {
        this.custID = custID;
        this.accomID = accomID;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getAccomID() {
        return accomID;
    }

    public void setAccomID(String accomID) {
        this.accomID = accomID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custID != null ? custID.hashCode() : 0);
        hash += (accomID != null ? accomID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransactionprocessPK)) {
            return false;
        }
        TransactionprocessPK other = (TransactionprocessPK) object;
        if ((this.custID == null && other.custID != null) || (this.custID != null && !this.custID.equals(other.custID))) {
            return false;
        }
        if ((this.accomID == null && other.accomID != null) || (this.accomID != null && !this.accomID.equals(other.accomID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.room4u.model.TransactionprocessPK[ custID=" + custID + ", accomID=" + accomID + " ]";
    }
    
}
