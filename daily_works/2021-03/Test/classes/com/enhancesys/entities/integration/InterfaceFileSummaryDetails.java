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
@XmlType(name="interfaceFileSummaryDetails")
@XmlRootElement(name="interfaceFileSummaryDetails")
@Entity(name="com.enhancesys.entities.integration.InterfaceFileSummaryDetails")
@Table(name="tr_interface_file_summary_details")
@Access(AccessType.FIELD)
@EntityListeners(value={com.enhancesys.entities.integration.InterfaceFileSummary.InterfaceFileSummaryListener.class})
public class InterfaceFileSummaryDetails implements  java.io.Serializable
{
   private static final long serialVersionUID = 1L;


   @XmlTransient
   public static class IDAdapter extends XmlAdapter<Long,InterfaceFileSummaryDetails>
   {
     @Override
     public Long marshal(InterfaceFileSummaryDetails interfaceFileSummaryDetails) throws Exception 
     {
       return interfaceFileSummaryDetails.getFileDetailsId();
     }
     
     @Override
     public InterfaceFileSummaryDetails unmarshal(Long arg) throws Exception 
     {
       InterfaceFileSummaryDetails interfaceFileSummaryDetails = new InterfaceFileSummaryDetails();
       interfaceFileSummaryDetails.setFileDetailsId(arg);
       return interfaceFileSummaryDetails;
     }
   
   }



   
   public void setFileDetailsId(Long arg)
   {
     if(this.fileDetailsId == arg)
       return;
     this.fileDetailsId = arg;
   }
      
   @XmlElement(name="fileDetailsId", required=true)
   

   public Long getFileDetailsId()
   {
     return this.fileDetailsId;
   }

   public void setInterfaceFileSummary(com.enhancesys.entities.integration.InterfaceFileSummary arg)
   {
       if(this.interfaceFileSummary == arg)
         return;
       
       com.enhancesys.entities.integration.InterfaceFileSummary oldInterfaceFileSummary = this.interfaceFileSummary;
       this.interfaceFileSummary = null;
       if(oldInterfaceFileSummary != null)
         oldInterfaceFileSummary.removeFileSummaryDetail(this);
       
       this.interfaceFileSummary = arg;
       
       if(this.interfaceFileSummary != null)
         this.interfaceFileSummary.addFileSummaryDetail(this);
   }

      
   @XmlJavaTypeAdapter(value=InterfaceFileSummary.IDAdapter.class)
   
   @XmlElement(name="interfaceFileSummary", required=true)
   

   public com.enhancesys.entities.integration.InterfaceFileSummary getInterfaceFileSummary()
   {
     return this.interfaceFileSummary;
   }
   
   public void setFileName(String arg)
   {
     if(this.fileName == arg)
       return;
     this.fileName = arg;
   }
      
   @XmlElement(name="fileName", required=true)
   

   public String getFileName()
   {
     return this.fileName;
   }
   
   public void setControlFileName(String arg)
   {
     if(this.controlFileName == arg)
       return;
     this.controlFileName = arg;
   }
      
   @XmlElement(name="controlFileName", required=true)
   

   public String getControlFileName()
   {
     return this.controlFileName;
   }
   
   public void setFileType(String arg)
   {
     if(this.fileType == arg)
       return;
     this.fileType = arg;
   }
      
   @XmlElement(name="fileType", required=true)
   

   public String getFileType()
   {
     return this.fileType;
   }
   
   public void setTotalCount(Long arg)
   {
     if(this.totalCount == arg)
       return;
     this.totalCount = arg;
   }
      
   @XmlElement(name="totalCount", required=false)
   

   public Long getTotalCount()
   {
     return this.totalCount;
   }
   
   public void setSuccessCount(Long arg)
   {
     if(this.successCount == arg)
       return;
     this.successCount = arg;
   }
      
   @XmlElement(name="successCount", required=false)
   

   public Long getSuccessCount()
   {
     return this.successCount;
   }
   
   public void setErrorCount(Long arg)
   {
     if(this.errorCount == arg)
       return;
     this.errorCount = arg;
   }
      
   @XmlElement(name="errorCount", required=false)
   

   public Long getErrorCount()
   {
     return this.errorCount;
   }

      
   @XmlTransient
   

   public Date getLastUpdatedTime()
   {
     return this.lastUpdatedTime;
   }



   @javax.validation.constraints.NotNull
   @Id
   @Column(name="file_details_id_n")
   @GeneratedValue(generator="tr_interface_file_sum_det_seq", strategy=GenerationType.SEQUENCE)
   @SequenceGenerator(allocationSize=1, initialValue=1, name="tr_interface_file_sum_det_seq", sequenceName="tr_interface_file_sum_det_seq")
   protected Long fileDetailsId;

   @javax.validation.constraints.NotNull
   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="file_id_n", nullable=false)
   protected com.enhancesys.entities.integration.InterfaceFileSummary interfaceFileSummary;

   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="file_name_v" , nullable=false)
   protected String fileName;

   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="ctrl_file_name_v" , nullable=false)
   protected String controlFileName;

   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="file_type_v" , nullable=false)
   protected String fileType;

   @Basic
   @Column(name="total_count_n" , nullable=true)
   protected Long totalCount;

   @Basic
   @Column(name="success_count_n" , nullable=true)
   protected Long successCount;

   @Basic
   @Column(name="error_count_n" , nullable=true)
   protected Long errorCount;

   @javax.validation.constraints.NotNull
   @Version
   @Column(name="last_updated_time_dt" , nullable=false)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date lastUpdatedTime;

}