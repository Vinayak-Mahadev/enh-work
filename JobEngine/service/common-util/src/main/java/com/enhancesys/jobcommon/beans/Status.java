package com.enhancesys.jobcommon.beans;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.hibernate.envers.Audited;

import com.enhancesys.jobcommon.Constants;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="status")
@XmlRootElement(name="status")
@Entity(name="com.enhancesys.jobcommon.beans.Status")
@Table(name="sd_status", schema = Constants._jobengineSchema)
@Access(AccessType.FIELD)
public class Status 
{

    @XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,Status>
    {
        @Override
        public Long marshal(Status status) throws Exception 
        {
            return status.getStatusId();
        }

        @Override
        public Status unmarshal(Long arg) throws Exception 
        {
            Status status = new Status();
            status.setStatusId(arg);
            return status;
        }
    
    }
    
    public void setStatusId(Long arg)
    {
        this.statusId = arg;
    }
    
    @XmlElement(name="statusId", required = true)
    public Long getStatusId()
    {
        return this.statusId;
    }
    
    @XmlElement(name="name", required=true)
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String arg)
    {
        this.name = arg;
    }
    
    @XmlElement(name="startDate", required=false)
    public Date getStartDate()
    {
        return this.startDate;
    }
    
    public void setStartDate(Date arg)
    {
        this.startDate = arg;
    }
 
    @XmlElement(name="endDate", required=false)
    public Date getEndDate()
    {
        return this.endDate;
    }
    
    public void setEndDate(Date arg)
    {
        this.endDate = arg;
    }
 
    
    @XmlTransient
    public Date getLastUpdatedTime()
    {
        return this.lastUpdatedTime;
    }
    
    
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Id
    @Column(name="status_n")
    protected Long statusId; 
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="name_v", nullable=false)
    protected String name; 
    
    
    @Basic
    @Column(name="start_date_dt", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date startDate; 
    
    
    @Basic
    @Column(name="end_date_dt", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date endDate; 
    
    @javax.validation.constraints.NotNull
    
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 

	
}
