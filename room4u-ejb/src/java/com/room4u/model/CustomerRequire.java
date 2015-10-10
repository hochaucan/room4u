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
 * @author NickHo
 */
@Entity
@Table(name = "CustomerRequire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerRequire.findAll", query = "SELECT c FROM CustomerRequire c"),
    @NamedQuery(name = "CustomerRequire.findByCustReqId", query = "SELECT c FROM CustomerRequire c WHERE c.custReqId = :custReqId"),
    @NamedQuery(name = "CustomerRequire.findByRequirement", query = "SELECT c FROM CustomerRequire c WHERE c.requirement = :requirement"),
    @NamedQuery(name = "CustomerRequire.findByCreatedDate", query = "SELECT c FROM CustomerRequire c WHERE c.createdDate = :createdDate")})
public class CustomerRequire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CustReqId")
    private Integer custReqId;
    @Size(max = 2147483647)
    @Column(name = "Requirement")
    private String requirement;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "CustID", referencedColumnName = "CustId")
    @ManyToOne
    private Customer custID;

    public CustomerRequire() {
    }

    public CustomerRequire(Integer custReqId) {
        this.custReqId = custReqId;
    }

    public CustomerRequire(Integer custReqId, Date createdDate) {
        this.custReqId = custReqId;
        this.createdDate = createdDate;
    }

    public Integer getCustReqId() {
        return custReqId;
    }

    public void setCustReqId(Integer custReqId) {
        this.custReqId = custReqId;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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
        hash += (custReqId != null ? custReqId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerRequire)) {
            return false;
        }
        CustomerRequire other = (CustomerRequire) object;
        if ((this.custReqId == null && other.custReqId != null) || (this.custReqId != null && !this.custReqId.equals(other.custReqId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.room4u.model.CustomerRequire[ custReqId=" + custReqId + " ]";
    }
    
}
