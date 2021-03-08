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
@XmlType(name="dailyDumpSummary")
@XmlRootElement(name="dailyDumpSummary")
@Entity(name="com.enhancesys.entities.integration.DailyDumpSummary")
@Table(name="tr_daily_dump_summary")
@Access(AccessType.FIELD)
public class DailyDumpSummary implements  java.io.Serializable
{
   private static final long serialVersionUID = 1L;


   @XmlTransient
   public static class IDAdapter extends XmlAdapter<Long,DailyDumpSummary>
   {
     @Override
     public Long marshal(DailyDumpSummary dailyDumpSummary) throws Exception 
     {
       return dailyDumpSummary.getDumpId();
     }
     
     @Override
     public DailyDumpSummary unmarshal(Long arg) throws Exception 
     {
       DailyDumpSummary dailyDumpSummary = new DailyDumpSummary();
       dailyDumpSummary.setDumpId(arg);
       return dailyDumpSummary;
     }
   
   }



   
   public void setDumpId(Long arg)
   {
     if(this.dumpId == arg)
       return;
     this.dumpId = arg;
   }
      
   @XmlElement(name="dumpId")
   

   public Long getDumpId()
   {
     return this.dumpId;
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
   
   public void setCreatedDate(Date arg)
   {
     if(this.createdDate == arg)
       return;
     this.createdDate = arg;
   }
      
   @XmlElement(name="createdDate", required=true)
   

   public Date getCreatedDate()
   {
     return this.createdDate;
   }
   
   public void setStatus(Long arg)
   {
     if(this.status == arg)
       return;
     this.status = arg;
   }
      
   @XmlElement(name="status", required=false)
   

   public Long getStatus()
   {
     return this.status;
   }

      
   @XmlTransient
   

   public Date getLastUpdatedTime()
   {
     return this.lastUpdatedTime;
   }



   @javax.validation.constraints.NotNull
   @Id
   @Column(name="dump_id_n")
   @GeneratedValue(generator="tr_daily_dump_summary_seq", strategy=GenerationType.SEQUENCE)
   @SequenceGenerator(allocationSize=1, initialValue=1, name="tr_daily_dump_summary_seq", sequenceName="tr_daily_dump_summary_seq")
   protected Long dumpId;

   @javax.validation.constraints.NotNull
   @ManyToOne(fetch=FetchType.EAGER, optional = false)
   @JoinColumn(name="interface_id_n", nullable=false)
   protected com.enhancesys.entities.integration.Interfaces interfaces;

   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="created_date_dt" , nullable=false)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date createdDate;

   @Basic
   @Column(name="status_n" , nullable=false)
   protected Long status;

   @javax.validation.constraints.NotNull
   @Version
   @Column(name="last_updated_time_dt" , nullable=false)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date lastUpdatedTime;

}