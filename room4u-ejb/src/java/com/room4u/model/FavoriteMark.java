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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nick
 */
@Entity
@Table(catalog = "room4u", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FavoriteMark.findAll", query = "SELECT f FROM FavoriteMark f"),
    @NamedQuery(name = "FavoriteMark.findByFavoMarkID", query = "SELECT f FROM FavoriteMark f WHERE f.favoMarkID = :favoMarkID"),
    @NamedQuery(name = "FavoriteMark.findByCreatedDate", query = "SELECT f FROM FavoriteMark f WHERE f.createdDate = :createdDate")})
public class FavoriteMark implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 10)
    private String favoMarkID;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @JoinColumn(name = "AccomID", referencedColumnName = "AccomId")
    @ManyToOne
    private Accommodation accomID;
    @JoinColumn(name = "CustID", referencedColumnName = "CustId")
    @ManyToOne
    private Customer custID;

    public FavoriteMark() {
    }

    public FavoriteMark(String favoMarkID) {
        this.favoMarkID = favoMarkID;
    }

    public FavoriteMark(String favoMarkID, Date createdDate) {
        this.favoMarkID = favoMarkID;
        this.createdDate = createdDate;
    }

    public String getFavoMarkID() {
        return favoMarkID;
    }

    public void setFavoMarkID(String favoMarkID) {
        this.favoMarkID = favoMarkID;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Accommodation getAccomID() {
        return accomID;
    }

    public void setAccomID(Accommodation accomID) {
        this.accomID = accomID;
    }

    public Customer getCustID() {
        return custID;
    }

    public void setCustID(Customer custID) {
        this.custID = custID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (favoMarkID != null ? favoMarkID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FavoriteMark)) {
            return false;
        }
        FavoriteMark other = (FavoriteMark) object;
        if ((this.favoMarkID == null && other.favoMarkID != null) || (this.favoMarkID != null && !this.favoMarkID.equals(other.favoMarkID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.room4u.model.FavoriteMark[ favoMarkID=" + favoMarkID + " ]";
    }
    
}
