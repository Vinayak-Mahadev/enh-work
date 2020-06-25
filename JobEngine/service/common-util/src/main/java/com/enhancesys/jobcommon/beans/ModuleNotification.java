package com.enhancesys.jobcommon.beans;

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

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.enhancesys.jobcommon.Constants;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="moduleNotification")
@XmlRootElement(name="moduleNotification")
@Entity(name="com.enhancesys.jobcommon.beans.ModuleNotification")
@Table(name="ms_module_notification", schema = Constants._jobengineSchema)
@Access(AccessType.FIELD)
public class ModuleNotification implements java.io.Serializable 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,ModuleNotification>
    {
        @Override
        public Long marshal(ModuleNotification moduleNotification) throws Exception 
        {
            return moduleNotification.getModuleNotificationId();
        }

        @Override
        public ModuleNotification unmarshal(Long arg) throws Exception 
        {
            ModuleNotification moduleNotification = new ModuleNotification();
            moduleNotification.setModuleNotificationId(arg);
            return moduleNotification;
        }
    
    }

    public void setModuleNotificationId(Long arg)
    {
        this.moduleNotificationId = arg;
    }
    
    @XmlElement(name="moduleNotificationId", required=true)
    public Long getModuleNotificationId()
    {
        return this.moduleNotificationId;
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
    
    @XmlJavaTypeAdapter(value=com.enhancesys.jobcommon.beans.Status.IDAdapter.class)
    @XmlElement(name="statusId", required=true)
    public com.enhancesys.jobcommon.beans.Status getStatus()
    {
        return this.status;
    }
    
    public void setStatus(com.enhancesys.jobcommon.beans.Status arg)
    {
        if(this.status == arg)
            return;
            
        
        this.status = arg;

    }
    
    @XmlJavaTypeAdapter(value=Module.IDAdapter.class)
    @XmlElement(name="moduleId", required=true)
    public com.enhancesys.jobcommon.beans.Module getModule()
    {
        return this.modules;
    }
    
    public void setModule(com.enhancesys.jobcommon.beans.Module arg)
    {
        if(this.modules == arg)
            return;
            
        
        this.modules = arg;

    }
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Id
    @Column(name="module_notification_id_n")
    @GeneratedValue(generator="ms_module_notif_seq", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize=1, initialValue=1, name="ms_module_notif_seq", sequenceName="ms_module_notif_seq")
    protected Long moduleNotificationId; 
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="scenario_v", nullable=false)
    protected String scenario; 
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="subject_v", nullable=false)
    protected String subject; 
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="to_first_name_v", nullable=false)
    protected String toFirstName; 
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="to_address_v", nullable=false)
    protected String toAddress; 
    
    @Audited
    
    @Basic
    @Column(name="to_cc_v", nullable=true)
    protected String toCC; 
    
    @Audited
    
    @Basic
    @Column(name="to_bcc_v", nullable=true)
    protected String toBCC; 
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="media_v", nullable=false)
    protected String media; 
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="language_v", nullable=false)
    protected String language; 
    
    @Audited
    
    @Basic
    @Column(name="template_name_v", nullable=false)
    protected String templateName; 
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="status_n", nullable=false)
    protected com.enhancesys.jobcommon.beans.Status status; 
    
    @javax.validation.constraints.NotNull
    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="module_id_n", nullable=false)
    protected com.enhancesys.jobcommon.beans.Module modules; 
    
}     
