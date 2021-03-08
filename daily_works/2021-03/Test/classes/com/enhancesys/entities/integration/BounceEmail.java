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
@XmlType(name="bounceEmail")
@XmlRootElement(name="bounceEmail")
@Entity(name="com.enhancesys.entities.integration.BounceEmail")
@Table(name="ms_bounced_email")
@Access(AccessType.FIELD)
public class BounceEmail implements  java.io.Serializable
{
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
     if(this.bounceId == arg)
       return;
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
   
   public void setBounceType(Long arg)
   {
     if(this.bounceType == arg)
       return;
     this.bounceType = arg;
   }
      
   @XmlElement(name="bounceType", required=true)
   

   public Long getBounceType()
   {
     return this.bounceType;
   }
   
   public void setBounceSubType(Long arg)
   {
     if(this.bounceSubType == arg)
       return;
     this.bounceSubType = arg;
   }
      
   @XmlElement(name="bounceSubType", required=true)
   

   public Long getBounceSubType()
   {
     return this.bounceSubType;
   }
   
   public void setAction(Long arg)
   {
     if(this.action == arg)
       return;
     this.action = arg;
   }
      
   @XmlElement(name="action", required=true)
   

   public Long getAction()
   {
     return this.action;
   }
   
   public void setDiagnosticCode(String arg)
   {
     if(this.diagnosticCode == arg)
       return;
     this.diagnosticCode = arg;
   }
      
   @XmlElement(name="diagnosticCode", required=true)
   

   public String getDiagnosticCode()
   {
     return this.diagnosticCode;
   }
      
   @XmlTransient
   

   public Date getLastUpdatedTime()
   {
     return this.lastUpdatedTime;
   }




   @Audited
   @javax.validation.constraints.NotNull
   @Id
   @Column(name="bounce_id_n" , nullable=true)
   protected Long bounceId;

   @Audited
   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="bounce_mail_id_v" , nullable=true)
   protected String bounceEmail;

   @Audited
   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="bounce_type_v" , nullable=false)
   protected Long bounceType;

   @Audited
   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="bounce_sub_type_v" , nullable=false)
   protected Long bounceSubType;

   @Audited
   @Basic
   @Column(name="action_v" , nullable=true)
   protected Long action;

   @Audited
   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="diagnostic_code_v" , nullable=false)
   protected String diagnosticCode;

   @Audited
   @javax.validation.constraints.NotNull
   @Version
   @Column(name="last_updated_time_dt" , nullable=false)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date lastUpdatedTime;

}