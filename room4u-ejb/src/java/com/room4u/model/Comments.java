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
@Table(name = "Comments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comments.findAll", query = "SELECT c FROM Comments c"),
    @NamedQuery(name = "Comments.findByComId", query = "SELECT c FROM Comments c WHERE c.comId = :comId"),
    @NamedQuery(name = "Comments.findByComDate", query = "SELECT c FROM Comments c WHERE c.comDate = :comDate"),
    @NamedQuery(name = "Comments.findByContent", query = "SELECT c FROM Comments c WHERE c.content = :content"),
    @NamedQuery(name = "Comments.findByAnswer", query = "SELECT c FROM Comments c WHERE c.answer = :answer")})
public class Comments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ComId")
    private Integer comId;
    @Column(name = "ComDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date comDate;
    @Size(max = 2147483647)
    @Column(name = "Content")
    private String content;
    @Size(max = 2147483647)
    @Column(name = "Answer")
    private String answer;
    @JoinColumn(name = "AccomId", referencedColumnName = "AccomId")
    @ManyToOne
    private Accommodation accomId;
    @JoinColumn(name = "CustId", referencedColumnName = "CustId")
    @ManyToOne
    private Customer custId;

    public Comments() {
    }

    public Comments(Integer comId) {
        this.comId = comId;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public Date getComDate() {
        return comDate;
    }

    public void setComDate(Date comDate) {
        this.comDate = comDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Accommodation getAccomId() {
        return accomId;
    }

    public void setAccomId(Accommodation accomId) {
        this.accomId = accomId;
    }

    public Customer getCustId() {
        return custId;
    }

    public void setCustId(Customer custId) {
        this.custId = custId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comId != null ? comId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments other = (Comments) object;
        if ((this.comId == null && other.comId != null) || (this.comId != null && !this.comId.equals(other.comId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.room4u.model.Comments[ comId=" + comId + " ]";
    }
    
}
