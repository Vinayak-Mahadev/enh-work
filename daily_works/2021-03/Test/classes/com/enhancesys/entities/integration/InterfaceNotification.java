package com.enhancesys.entities.integration;


import javax.persistence.*;
import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import org.hibernate.envers.*;
import org.hibernate.validator.constraints.*;
import org.hibernate.search.annotations.*;



@SuppressWarnings("unused")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="interfaceNotification")
@XmlRootElement(name="interfaceNotification")
@Entity(name="com.enhancesys.entities.integration.InterfaceNotification")
@Table(name="MS_INTERFACE_NOTIFICATION")
@Access(AccessType.FIELD)
public class InterfaceNotification implements  java.io.Serializable
{
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
     if(this.interfaceNotificationId == arg)
       return;
     this.interfaceNotificationId = arg;
   }
      
   @XmlElement(name="interfaceNotificationId", required=true)
   

   public Long getInterfaceNotificationId()
   {
     return this.interfaceNotificationId;
   }
   
   public void setScenario(String arg)
   {
     if(this.scenario == arg)
       return;
     this.scenario = arg;
   }
      
   @XmlElement(name="scenario", required=true)
   

   public String getScenario()
   {
     return this.scenario;
   }
   
   public void setSubject(String arg)
   {
     if(this.subject == arg)
       return;
     this.subject = arg;
   }
      
   @XmlElement(name="subject", required=true)
   

   public String getSubject()
   {
     return this.subject;
   }
   
   public void setToFirstName(String arg)
   {
     if(this.toFirstName == arg)
       return;
     this.toFirstName = arg;
   }
      
   @XmlElement(name="toFirstName", required=true)
   

   public String getToFirstName()
   {
     return this.toFirstName;
   }
   
   public void setToAddress(String arg)
   {
     if(this.toAddress == arg)
       return;
     this.toAddress = arg;
   }
      
   @XmlElement(name="toAddress", required=true)
   

   public String getToAddress()
   {
     return this.toAddress;
   }
   
   public void setToCC(String arg)
   {
     if(this.toCC == arg)
       return;
     this.toCC = arg;
   }
      
   @XmlElement(name="toCC", required=false)
   

   public String getToCC()
   {
     return this.toCC;
   }
   
   public void setToBCC(String arg)
   {
     if(this.toBCC == arg)
       return;
     this.toBCC = arg;
   }
      
   @XmlElement(name="toBCC", required=false)
   

   public String getToBCC()
   {
     return this.toBCC;
   }
   
   public void setMedia(String arg)
   {
     if(this.media == arg)
       return;
     this.media = arg;
   }
      
   @XmlElement(name="media", required=true)
   

   public String getMedia()
   {
     return this.media;
   }
   
   public void setLanguage(String arg)
   {
     if(this.language == arg)
       return;
     this.language = arg;
   }
      
   @XmlElement(name="language", required=true)
   

   public String getLanguage()
   {
     return this.language;
   }
   
   public void setTemplateName(String arg)
   {
     if(this.templateName == arg)
       return;
     this.templateName = arg;
   }
      
   @XmlElement(name="templateName", required=true)
   

   public String getTemplateName()
   {
     return this.templateName;
   }

      
   @XmlTransient
   

   public Date getLastUpdatedTime()
   {
     return this.lastUpdatedTime;
   }
   
   public void setStatus(net.treetechnologies.entities.masters.Status arg)
   {
     if(this.status == arg)
       return;
     this.status = arg;
   }
      
   @XmlJavaTypeAdapter(value=net.treetechnologies.entities.masters.Status.IDAdapter.class)
   
   @XmlElement(name="statusId", required=true)
   

   public net.treetechnologies.entities.masters.Status getStatus()
   {
     return this.status;
   }
   
   public void setInterfaces(com.enhancesys.entities.integration.Interfaces arg)
   {
     if(this.interfaces == arg)
       return;
     this.interfaces = arg;
   }
      
   @XmlJavaTypeAdapter(value=Interfaces.IDAdapter.class)
   
   @XmlElement(name="interfaceId", required=true)
   

   public com.enhancesys.entities.integration.Interfaces getInterfaces()
   {
     return this.interfaces;
   }



   @Audited
   @javax.validation.constraints.NotNull
   @Id
   @Column(name="interface_notification_id_n")
   @GeneratedValue(generator="ms_interfc_notif_seq", strategy=GenerationType.SEQUENCE)
   @SequenceGenerator(allocationSize=1, initialValue=1, name="ms_interfc_notif_seq", sequenceName="ms_interfc_notif_seq")
   protected Long interfaceNotificationId;

   @Audited
   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="scenario_v" , nullable=false)
   protected String scenario;

   @Audited
   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="subject_v" , nullable=false)
   protected String subject;

   @Audited
   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="to_first_name_v" , nullable=false)
   protected String toFirstName;

   @Audited
   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="to_address_v" , nullable=false)
   protected String toAddress;

   @Audited
   @Basic
   @Column(name="to_cc_v" , nullable=true)
   protected String toCC;

   @Audited
   @Basic
   @Column(name="to_bcc_v" , nullable=true)
   protected String toBCC;

   @Audited
   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="media_v" , nullable=false)
   protected String media;

   @Audited
   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="language_v" , nullable=false)
   protected String language;

   @Audited
   @Basic
   @Column(name="template_name_v" , nullable=false)
   protected String templateName;

   @Audited
   @javax.validation.constraints.NotNull
   @Version
   @Column(name="last_updated_time_dt" , nullable=false)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date lastUpdatedTime;

   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="status_n", nullable=false)
   protected net.treetechnologies.entities.masters.Status status;

   @javax.validation.constraints.NotNull
   @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="interface_id_n", nullable=false)
   protected com.enhancesys.entities.integration.Interfaces interfaces;

}