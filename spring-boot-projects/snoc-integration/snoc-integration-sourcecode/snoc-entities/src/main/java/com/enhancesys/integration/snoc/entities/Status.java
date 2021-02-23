package com.enhancesys.integration.snoc.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="status")
@XmlRootElement(name="status")
@Entity(name="com.enhancesys.integration.snoc.entities.Status")
@Table(name="SD_STATUS", schema = "interface")
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
    
    @XmlElement(name="statusId", required=true)
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
    
	
    @Id
    @Column(name="status_n")
    protected Long statusId; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="name_v", nullable=false)
    protected String name; 

}
