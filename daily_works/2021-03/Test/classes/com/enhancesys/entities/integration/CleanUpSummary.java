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
@XmlType(name="cleanUpSummary")
@XmlRootElement(name="cleanUpSummary")
@Entity(name="com.enhancesys.entities.integration.CleanUpSummary")
@Table(name="tr_clean_up_summary")
@Access(AccessType.FIELD)
public class CleanUpSummary implements  java.io.Serializable
{
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
     if(this.cleanupId == arg)
       return;
     this.cleanupId = arg;
   }
      
   @XmlElement(name="cleanupId")
   

   public Long getCleanupId()
   {
     return this.cleanupId;
   }
   
   public void setInterfaces(com.enhancesys.entities.integration.Interfaces arg)
   {
     if(this.interfaces == arg)
       return;
     this.interfaces = arg;
   }
      
   @XmlJavaTypeAdapter(value=Interfaces.IDAdapter.class)
   
   @XmlElement(name="interfaces", required=true)
   

   public com.enhancesys.entities.integration.Interfaces getInterfaces()
   {
     return this.interfaces;
   }
   
   public void setUserId(String arg)
   {
     if(this.userId == arg)
       return;
     this.userId = arg;
   }
      
   @XmlElement(name="userId", required=true)
   

   public String getUserId()
   {
     return this.userId;
   }
   
   public void setActvityName(String arg)
   {
     if(this.actvityName == arg)
       return;
     this.actvityName = arg;
   }
      
   @XmlElement(name="actvityName", required=true)
   

   public String getActvityName()
   {
     return this.actvityName;
   }
   
   public void setMessage(String arg)
   {
     if(this.message == arg)
       return;
     this.message = arg;
   }
      
   @XmlElement(name="message", required=true)
   

   public String getMessage()
   {
     return this.message;
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
   
   public void setErrorMessage(String arg)
   {
     if(this.errorMessage == arg)
       return;
     this.errorMessage = arg;
   }
      
   @XmlElement(name="errorMessage", required=true)
   

   public String getErrorMessage()
   {
     return this.errorMessage;
   }
   
   public void setInitiatedTime(Date arg)
   {
     if(this.initiatedTime == arg)
       return;
     this.initiatedTime = arg;
   }
      
   @XmlElement(name="initiatedTime", required=true)
   

   public Date getInitiatedTime()
   {
     return this.initiatedTime;
   }
   
   public void setCompletedTime(Date arg)
   {
     if(this.completedTime == arg)
       return;
     this.completedTime = arg;
   }
      
   @XmlElement(name="completedTime", required=false)
   

   public Date getCompletedTime()
   {
     return this.completedTime;
   }

      
   @XmlTransient
   

   public Date getLastUpdatedTime()
   {
     return this.lastUpdatedTime;
   }



   @Audited
   @javax.validation.constraints.NotNull
   @Id
   @Column(name="cleanup_id_n")
   @GeneratedValue(generator="tr_clean_up_summary_seq", strategy=GenerationType.SEQUENCE)
   @SequenceGenerator(allocationSize=1, initialValue=1, name="tr_clean_up_summary_seq", sequenceName="tr_clean_up_summary_seq")
   protected Long cleanupId;

   @javax.validation.constraints.NotNull
   @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
   @ManyToOne(fetch=FetchType.EAGER, optional = false)
   @JoinColumn(name="interface_id_n", nullable=false)
   protected com.enhancesys.entities.integration.Interfaces interfaces;

   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="user_id_v" , nullable=false)
   protected String userId;

   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="actvity_name_v" , nullable=false)
   protected String actvityName;

   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="message_v" , nullable=true)
   protected String message;

   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="status_n", nullable=false)
   protected net.treetechnologies.entities.masters.Status status;

   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="error_message_v" , nullable=true)
   protected String errorMessage;

   @Audited
   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="initiated_time_dt" , nullable=false)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date initiatedTime;

   @Basic
   @Column(name="completed_time_dt" , nullable=true)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date completedTime;

   @Audited
   @javax.validation.constraints.NotNull
   @Version
   @Column(name="last_updated_time_dt" , nullable=false)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date lastUpdatedTime;

}