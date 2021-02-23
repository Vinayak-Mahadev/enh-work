package com.enhancesys.entities.integration;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="cleanUpSummary")
@XmlRootElement(name="cleanUpSummary")
@Entity(name="com.enhancesys.entities.integration.CleanUpSummary")
@Table(name="tr_clean_up_summary", schema = "interface")
@Access(AccessType.FIELD)
public class CleanUpSummary implements java.io.Serializable 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,CleanUpSummary>
    {
        @Override
        public Long marshal(CleanUpSummary cleanUpSummary) throws Exception 
        {
            return cleanUpSummary.getCleanupId();
        }

        @Override
        public CleanUpSummary unmarshal(Long arg) throws Exception 
        {
            CleanUpSummary cleanUpSummary = new CleanUpSummary();
            cleanUpSummary.setCleanupId(arg);
            return cleanUpSummary;
        }
    
    }

    public void setCleanupId(Long arg)
    {
        this.cleanupId = arg;
    }
    
    @XmlElement(name="cleanupId")
    public Long getCleanupId()
    {
        return this.cleanupId;
    }
    
    @XmlJavaTypeAdapter(value=Interfaces.IDAdapter.class)
    @XmlElement(name="interfaces", required=true)
    public com.enhancesys.entities.integration.Interfaces getInterfaces()
    {
        return this.interfaces;
    }
    
    public void setInterfaces(com.enhancesys.entities.integration.Interfaces arg)
    {
        if(arg == null || this.interfaces == arg)
            return;

        this.interfaces = arg;
    }
    
    @XmlElement(name="userId", required=true)
    public String getUserId()
    {
        return this.userId;
    }
    
    public void setUserId(String arg)
    {
        this.userId = arg;
    }
    
    @XmlElement(name="actvityName", required=true)
    public String getActvityName()
    {
        return this.actvityName;
    }
    
    public void setActvityName(String arg)
    {
        this.actvityName = arg;
    }
    
    @XmlElement(name="message", required=true)
    public String getMessage()
    {
        return this.message;
    }
    
    public void setMessage(String arg)
    {
        this.message = arg;
    }
    
    @XmlJavaTypeAdapter(value=com.enhancesys.entities.integration.Status.IDAdapter.class)
    @XmlElement(name="statusId", required=true)
    public com.enhancesys.entities.integration.Status getStatus()
    {
        return this.status;
    }
    
    public void setStatus(com.enhancesys.entities.integration.Status arg)
    {
        if(this.status == arg)
            return;
            
        
        this.status = arg;

    }
    
    @XmlElement(name="errorMessage", required=true)
    public String getErrorMessage()
    {
        return this.errorMessage;
    }
    
    public void setErrorMessage(String arg)
    {
        this.errorMessage = arg;
    }
    
    @XmlElement(name="initiatedTime", required=true)
    public Date getInitiatedTime()
    {
        return this.initiatedTime;
    }
    
    public void setInitiatedTime(Date arg)
    {
        this.initiatedTime = arg;
    }
    
    @XmlElement(name="completedTime", required=false)
    public Date getCompletedTime()
    {
        return this.completedTime;
    }
    
    public void setCompletedTime(Date arg)
    {
        this.completedTime = arg;
    }
    
    @XmlTransient
    public Date getLastUpdatedTime()
    {
        return this.lastUpdatedTime;
    }
    
    @javax.validation.constraints.NotNull
    
    @Id
    @Column(name="cleanup_id_n")
    @GeneratedValue(generator="tr_clean_up_summary_seq", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize=1, initialValue=1, name="tr_clean_up_summary_seq", sequenceName="tr_clean_up_summary_seq")
    protected Long cleanupId; 
    
    @javax.validation.constraints.NotNull
    @ManyToOne(fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="interface_id_n", nullable=false)
    protected com.enhancesys.entities.integration.Interfaces interfaces; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="user_id_v", nullable=false)
    protected String userId; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="actvity_name_v", nullable=false)
    protected String actvityName; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="message_v", nullable=true)
    protected String message; 
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="status_n", nullable=false)
    protected com.enhancesys.entities.integration.Status status; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="error_message_v", nullable=true)
    protected String errorMessage; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="initiated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date initiatedTime; 
    
    
    @Basic
    @Column(name="completed_time_dt", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date completedTime; 
    
    @javax.validation.constraints.NotNull
    
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
}     
