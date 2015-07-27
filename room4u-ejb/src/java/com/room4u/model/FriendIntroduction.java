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
    @NamedQuery(name = "FriendIntroduction.findAll", query = "SELECT f FROM FriendIntroduction f"),
    @NamedQuery(name = "FriendIntroduction.findByFriendIntroId", query = "SELECT f FROM FriendIntroduction f WHERE f.friendIntroId = :friendIntroId"),
    @NamedQuery(name = "FriendIntroduction.findByFriendName", query = "SELECT f FROM FriendIntroduction f WHERE f.friendName = :friendName"),
    @NamedQuery(name = "FriendIntroduction.findByFriendContact", query = "SELECT f FROM FriendIntroduction f WHERE f.friendContact = :friendContact"),
    @NamedQuery(name = "FriendIntroduction.findByCreatedDate", query = "SELECT f FROM FriendIntroduction f WHERE f.createdDate = :createdDate")})
public class FriendIntroduction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 10)
    private String friendIntroId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(nullable = false, length = 30)
    private String friendName;
    @Size(max = 50)
    @Column(length = 50)
    private String friendContact;
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

    public FriendIntroduction() {
    }

    public FriendIntroduction(String friendIntroId) {
        this.friendIntroId = friendIntroId;
    }

    public FriendIntroduction(String friendIntroId, String friendName, Date createdDate) {
        this.friendIntroId = friendIntroId;
        this.friendName = friendName;
        this.createdDate = createdDate;
    }

    public String getFriendIntroId() {
        return friendIntroId;
    }

    public void setFriendIntroId(String friendIntroId) {
        this.friendIntroId = friendIntroId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendContact() {
        return friendContact;
    }

    public void setFriendContact(String friendContact) {
        this.friendContact = friendContact;
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
        hash += (friendIntroId != null ? friendIntroId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FriendIntroduction)) {
            return false;
        }
        FriendIntroduction other = (FriendIntroduction) object;
        if ((this.friendIntroId == null && other.friendIntroId != null) || (this.friendIntroId != null && !this.friendIntroId.equals(other.friendIntroId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.room4u.model.FriendIntroduction[ friendIntroId=" + friendIntroId + " ]";
    }
    
}
