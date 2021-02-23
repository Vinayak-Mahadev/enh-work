package com.enhancesys.integration.snoc.entities;

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
@XmlType(name="interfaceNotification")
@XmlRootElement(name="interfaceNotification")
@Entity(name="com.enhancesys.integration.snoc.entities.InterfaceNotification")
@Table(name="MS_INTERFACE_NOTIFICATION", schema = "interface")
@Access(AccessType.FIELD)
public class InterfaceNotification implements java.io.Serializable 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,InterfaceNotification>
    {
        @Override
        public Long marshal(InterfaceNotification interfaceNotification) throws Exception 
        {
            return interfaceNotification.getInterfaceNotificationId();
        }

        @Override
        public InterfaceNotification unmarshal(Long arg) throws Exception 
        {
            InterfaceNotification interfaceNotification = new InterfaceNotification();
            interfaceNotification.setInterfaceNotificationId(arg);
            return interfaceNotification;
        }
    
    }

    public void setInterfaceNotificationId(Long arg)
    {
        this.interfaceNotificationId = arg;
    }
    
    @XmlElement(name="interfaceNotificationId", required=true)
    public Long getInterfaceNotificationId()
    {
        return this.interfaceNotificationId;
    }
    
    @XmlElement(name="scenario", required=true)
    public String getScenario()
    {
        return this.scenario;
    }
    
    public void setScenario(String arg)
    {
        this.scenario = arg;
    }
    
    @XmlElement(name="subject", required=true)
    public String getSubject()
    {
        return this.subject;
    }
    
    public void setSubject(String arg)
    {
        this.subject = arg;
    }
    
    @XmlElement(name="toFirstName", required=true)
    public String getToFirstName()
    {
        return this.toFirstName;
    }
    
    public void setToFirstName(String arg)
    {
        this.toFirstName = arg;
    }
    
    @XmlElement(name="toAddress", required=true)
    public String getToAddress()
    {
        return this.toAddress;
    }
    
    public void setToAddress(String arg)
    {
        this.toAddress = arg;
    }
    
    @XmlElement(name="toCC", required=false)
    public String getToCC()
    {
        return this.toCC;
    }
    
    public void setToCC(String arg)
    {
        this.toCC = arg;
    }
    
    @XmlElement(name="toBCC", required=false)
    public String getToBCC()
    {
        return this.toBCC;
    }
    
    public void setToBCC(String arg)
    {
        this.toBCC = arg;
    }
    
    @XmlElement(name="media", required=true)
    public String getMedia()
    {
        return this.media;
    }
    
    public void setMedia(String arg)
    {
        this.media = arg;
    }
    
    @XmlElement(name="language", required=true)
    public String getLanguage()
    {
        return this.language;
    }
    
    public void setLanguage(String arg)
    {
        this.language = arg;
    }
    
    @XmlElement(name="templateName", required=true)
    public String getTemplateName()
    {
        return this.templateName;
    }
    
    public void setTemplateName(String arg)
    {
        this.templateName = arg;
    }
    
    @XmlTransient
    public Date getLastUpdatedTime()
    {
        return this.lastUpdatedTime;
    }
    
    @XmlJavaTypeAdapter(value=com.enhancesys.integration.snoc.entities.Status.IDAdapter.class)
    @XmlElement(name="statusId", required=true)
    public com.enhancesys.integration.snoc.entities.Status getStatus()
    {
        return this.status;
    }
    
    public void setStatus(com.enhancesys.integration.snoc.entities.Status arg)
    {
        if(this.status == arg)
            return;
            
        
        this.status = arg;

    }
    
    @XmlJavaTypeAdapter(value=Interfaces.IDAdapter.class)
    @XmlElement(name="interfaceId", required=true)
    public com.enhancesys.integration.snoc.entities.Interfaces getInterfaces()
    {
        return this.interfaces;
    }
    
    public void setInterfaces(com.enhancesys.integration.snoc.entities.Interfaces arg)
    {
        if(this.interfaces == arg)
            return;
            
        
        this.interfaces = arg;

    }
    
    @javax.validation.constraints.NotNull
    
    @Id
    @Column(name="interface_notification_id_n")
    @GeneratedValue(generator="ms_interfc_notif_seq", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize=1, initialValue=1, name="ms_interfc_notif_seq", sequenceName="ms_interfc_notif_seq")
    protected Long interfaceNotificationId; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="scenario_v", nullable=false)
    protected String scenario; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="subject_v", nullable=false)
    protected String subject; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="to_first_name_v", nullable=false)
    protected String toFirstName; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="to_address_v", nullable=false)
    protected String toAddress; 
    
    
    @Basic
    @Column(name="to_cc_v", nullable=true)
    protected String toCC; 
    
    
    @Basic
    @Column(name="to_bcc_v", nullable=true)
    protected String toBCC; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="media_v", nullable=false)
    protected String media; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="language_v", nullable=false)
    protected String language; 
    
    
    @Basic
    @Column(name="template_name_v", nullable=false)
    protected String templateName; 
    
    @javax.validation.constraints.NotNull
    
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="status_n", nullable=false)
    protected com.enhancesys.integration.snoc.entities.Status status; 
    
    @javax.validation.constraints.NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="interface_id_n", nullable=false)
    protected com.enhancesys.integration.snoc.entities.Interfaces interfaces; 
    
}     
