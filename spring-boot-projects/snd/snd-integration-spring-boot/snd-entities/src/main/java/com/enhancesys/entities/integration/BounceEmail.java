package com.enhancesys.entities.integration;

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


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="bounceEmail")
@XmlRootElement(name="bounceEmail")
@Entity(name="com.enhancesys.entities.integration.BounceEmail")
@Table(name="ms_bounced_email", schema = "interface")
@Access(AccessType.FIELD)
public class BounceEmail implements java.io.Serializable 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,BounceEmail>
    {
        @Override
        public Long marshal(BounceEmail bounceEmail) throws Exception 
        {
            return bounceEmail.getBounceId();
        }

        @Override
        public BounceEmail unmarshal(Long arg) throws Exception 
        {
            BounceEmail bounceEmail = new BounceEmail();
            bounceEmail.setBounceId(arg);
            return bounceEmail;
        }
    
    }

    public void setBounceId(Long arg)
    {
        this.bounceId = arg;
    }
    
    @XmlElement(name="bounceId", required = false)
    public Long getBounceId()
    {
        return this.bounceId;
    }
    
    @XmlElement(name="bounceEmail", required=false)
    public String getBounceEmail()
    {
        return this.bounceEmail;
    }
    
    public void setBounceEmail(String arg)
    {
        this.bounceEmail = arg;
    }
    
    @XmlElement(name="bounceType", required=true)
    public Long getBounceType()
    {
        return this.bounceType;
    }
    
    public void setBounceType(Long arg)
    {
        this.bounceType = arg;
    }
    
    @XmlElement(name="bounceSubType", required=true)
    public Long getBounceSubType()
    {
        return this.bounceSubType;
    }
    
    public void setBounceSubType(Long arg)
    {
        this.bounceSubType = arg;
    }
    
    @XmlElement(name="action", required=true)
    public Long getAction()
    {
        return this.action;
    }
    
    public void setAction(Long arg)
    {
        this.action = arg;
    }
    
    @XmlElement(name="diagnosticCode", required=true)
    public String getDiagnosticCode()
    {
        return this.diagnosticCode;
    }
    
    public void setDiagnosticCode(String arg)
    {
        this.diagnosticCode = arg;
    }
    
    @XmlTransient
    public Date getLastUpdatedTime()
    {
        return this.lastUpdatedTime;
    }
    
    @javax.validation.constraints.NotNull
    
    @Id
    @Column(name="bounce_id_n", nullable=true)
    protected Long bounceId; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="bounce_mail_id_v", nullable=true)
    protected String bounceEmail; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="bounce_type_v", nullable=false)
    protected Long bounceType; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="bounce_sub_type_v", nullable=false)
    protected Long bounceSubType; 
    
    
    @Basic
    @Column(name="action_v", nullable=true)
    protected Long action; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="diagnostic_code_v", nullable=false)
    protected String diagnosticCode; 
    
    @javax.validation.constraints.NotNull
    
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
}     
