/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.room4u.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author Nick
 */
@Entity
@Table(catalog = "room4u", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transactionprocess.findAll", query = "SELECT t FROM Transactionprocess t"),
    @NamedQuery(name = "Transactionprocess.findByCustID", query = "SELECT t FROM Transactionprocess t WHERE t.transactionprocessPK.custID = :custID"),
    @NamedQuery(name = "Transactionprocess.findByAccomID", query = "SELECT t FROM Transactionprocess t WHERE t.transactionprocessPK.accomID = :accomID"),
    @NamedQuery(name = "Transactionprocess.findByBookedFromDate", query = "SELECT t FROM Transactionprocess t WHERE t.bookedFromDate = :bookedFromDate"),
    @NamedQuery(name = "Transactionprocess.findByBookedToDate", query = "SELECT t FROM Transactionprocess t WHERE t.bookedToDate = :bookedToDate"),
    @NamedQuery(name = "Transactionprocess.findByTrxDate", query = "SELECT t FROM Transactionprocess t WHERE t.trxDate = :trxDate"),
    @NamedQuery(name = "Transactionprocess.findByQuantity", query = "SELECT t FROM Transactionprocess t WHERE t.quantity = :quantity")})
public class Transactionprocess implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TransactionprocessPK transactionprocessPK;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookedFromDate;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookedToDate;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date trxDate;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int quantity;
    @JoinColumn(name = "AccomID", referencedColumnName = "AccomId", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Accommodation accommodation;
    @JoinColumn(name = "CustID", referencedColumnName = "CustId", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Customer customer;

    public Transactionprocess() {
    }

    public Transactionprocess(TransactionprocessPK transactionprocessPK) {
        this.transactionprocessPK = transactionprocessPK;
    }

    public Transactionprocess(TransactionprocessPK transactionprocessPK, Date bookedFromDate, Date bookedToDate, Date trxDate, int quantity) {
        this.transactionprocessPK = transactionprocessPK;
        this.bookedFromDate = bookedFromDate;
        this.bookedToDate = bookedToDate;
        this.trxDate = trxDate;
        this.quantity = quantity;
    }

    public Transactionprocess(String custID, String accomID) {
        this.transactionprocessPK = new TransactionprocessPK(custID, accomID);
    }

    public TransactionprocessPK getTransactionprocessPK() {
        return transactionprocessPK;
    }

    public void setTransactionprocessPK(TransactionprocessPK transactionprocessPK) {
        this.transactionprocessPK = transactionprocessPK;
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

    public Date getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(Date trxDate) {
        this.trxDate = trxDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionprocessPK != null ? transactionprocessPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transactionprocess)) {
            return false;
        }
        Transactionprocess other = (Transactionprocess) object;
        if ((this.transactionprocessPK == null && other.transactionprocessPK != null) || (this.transactionprocessPK != null && !this.transactionprocessPK.equals(other.transactionprocessPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.room4u.model.Transactionprocess[ transactionprocessPK=" + transactionprocessPK + " ]";
    }
    
}
