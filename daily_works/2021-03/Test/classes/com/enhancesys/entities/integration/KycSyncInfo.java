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
@XmlType(name="kycSyncInfo")
@XmlRootElement(name="kycSyncInfo")
@Entity(name="com.enhancesys.entities.integration.KycSyncInfo")
@Table(name="MS_KYC_SYNC_INFO")
@Access(AccessType.FIELD)
public class KycSyncInfo implements  java.io.Serializable
{
   private static final long serialVersionUID = 1L;


   @XmlTransient
   public static class IDAdapter extends XmlAdapter<Long,KycSyncInfo>
   {
     @Override
     public Long marshal(KycSyncInfo kycSyncInfo) throws Exception 
     {
       return kycSyncInfo.getSyncId();
     }
     
     @Override
     public KycSyncInfo unmarshal(Long arg) throws Exception 
     {
       KycSyncInfo kycSyncInfo = new KycSyncInfo();
       kycSyncInfo.setSyncId(arg);
       return kycSyncInfo;
     }
   
   }



   
   public void setSyncId(Long arg)
   {
     if(this.syncId == arg)
       return;
     this.syncId = arg;
   }
      
   @XmlElement(name="syncId", required = true)
   

   public Long getSyncId()
   {
     return this.syncId;
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
   
   public void setKycField(String arg)
   {
     if(this.kycField == arg)
       return;
     this.kycField = arg;
   }
      
   @XmlElement(name="kycField", required=false)
   

   public String getKycField()
   {
     return this.kycField;
   }
   
   public void setKycFlag(Long arg)
   {
     if(this.kycFlag == arg)
       return;
     this.kycFlag = arg;
   }
      
   @XmlElement(name="kycFlag", required=false)
   

   public Long getKycFlag()
   {
     return this.kycFlag;
   }

      
   @XmlTransient
   

   public Date getLastUpdatedTime()
   {
     return this.lastUpdatedTime;
   }



   @Audited
   @javax.validation.constraints.NotNull
   @Id
   @Column(name="sync_id_n")
   protected Long syncId;

   @javax.validation.constraints.NotNull
   @ManyToOne(fetch=FetchType.EAGER, optional = false)
   @JoinColumn(name="interface_id_n", nullable=false)
   protected com.enhancesys.entities.integration.Interfaces interfaces;

   @Basic
   @Column(name="kyc_field_v" , nullable=false)
   protected String kycField;

   @Basic
   @Column(name="kyc_flag_n" , nullable=false)
   protected Long kycFlag;

   @javax.validation.constraints.NotNull
   @Version
   @Column(name="last_updated_time_dt" , nullable=false)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date lastUpdatedTime;

}