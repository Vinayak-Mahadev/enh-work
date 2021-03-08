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
@XmlType(name="interfaceFileSummary")
@XmlRootElement(name="interfaceFileSummary")
@Entity(name="com.enhancesys.entities.integration.InterfaceFileSummary")
@Table(name="tr_interface_file_summary")
@Access(AccessType.FIELD)
public class InterfaceFileSummary implements  java.io.Serializable
{
   private static final long serialVersionUID = 1L;


   @XmlTransient
   public static class IDAdapter extends XmlAdapter<Long,InterfaceFileSummary>
   {
     @Override
     public Long marshal(InterfaceFileSummary interfaceFileSummary) throws Exception 
     {
       return interfaceFileSummary.getFileId();
     }
     
     @Override
     public InterfaceFileSummary unmarshal(Long arg) throws Exception 
     {
       InterfaceFileSummary interfaceFileSummary = new InterfaceFileSummary();
       interfaceFileSummary.setFileId(arg);
       return interfaceFileSummary;
     }
   
   }



   @XmlTransient
   public static class InterfaceFileSummaryListener
   {
     @PrePersist
     public void onPersist(com.enhancesys.entities.integration.InterfaceFileSummaryDetails arg)
     {
       InterfaceFileSummary obj = arg.getInterfaceFileSummary();
       obj._addFileSummaryDetails(arg);
     }
     
     @PreRemove
     public void onRemove(com.enhancesys.entities.integration.InterfaceFileSummaryDetails arg)
     {
       InterfaceFileSummary obj = arg.getInterfaceFileSummary();
       obj._removeFileSummaryDetails(arg);
     }
   
   }
   
   void _addFileSummaryDetails(com.enhancesys.entities.integration.InterfaceFileSummaryDetails arg)
   {
     this.fileSummaryDetails.add(arg);
   }
   
   void _removeFileSummaryDetails(com.enhancesys.entities.integration.InterfaceFileSummaryDetails arg)
   {
     this.fileSummaryDetails.remove(arg);
   }

   
   public void setFileId(Long arg)
   {
     if(this.fileId == arg)
       return;
     this.fileId = arg;
   }
      
   @XmlElement(name="fileId", required=true)
   

   public Long getFileId()
   {
     return this.fileId;
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
   
   public void setReceivedServerDetails(String arg)
   {
     if(this.receivedServerDetails == arg)
       return;
     this.receivedServerDetails = arg;
   }
      
   @XmlElement(name="receivedServerDetails", required=false)
   

   public String getReceivedServerDetails()
   {
     return this.receivedServerDetails;
   }
   
   public void setSendServerDetails(String arg)
   {
     if(this.sendServerDetails == arg)
       return;
     this.sendServerDetails = arg;
   }
      
   @XmlElement(name="sendServerDetails", required=false)
   

   public String getSendServerDetails()
   {
     return this.sendServerDetails;
   }
   
   public void setLocalServerDetails(String arg)
   {
     if(this.localServerDetails == arg)
       return;
     this.localServerDetails = arg;
   }
      
   @XmlElement(name="localServerDetails", required=false)
   

   public String getLocalServerDetails()
   {
     return this.localServerDetails;
   }
   
   public void setTotalCount(Long arg)
   {
     if(this.totalCount == arg)
       return;
     this.totalCount = arg;
   }
      
   @XmlElement(name="totalCount", required=true)
   

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
   
   public void setFilterCount(Long arg)
   {
     if(this.filterCount == arg)
       return;
     this.filterCount = arg;
   }
      
   @XmlElement(name="filterCount", required=false)
   

   public Long getFilterCount()
   {
     return this.filterCount;
   }
   
   public void setUploadedBy(Long arg)
   {
     if(this.uploadedBy == arg)
       return;
     this.uploadedBy = arg;
   }
      
   @XmlElement(name="uploadedBy", required=false)
   

   public Long getUploadedBy()
   {
     return this.uploadedBy;
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
   
   public void setMessage(String arg)
   {
     if(this.message == arg)
       return;
     this.message = arg;
   }
      
   @XmlElement(name="message", required=false)
   

   public String getMessage()
   {
     return this.message;
   }
   
   public void setErrorMessage(String arg)
   {
     if(this.errorMessage == arg)
       return;
     this.errorMessage = arg;
   }
      
   @XmlElement(name="errorMessage", required=false)
   

   public String getErrorMessage()
   {
     return this.errorMessage;
   }
   
   public void setRetryCount(Long arg)
   {
     if(this.retryCount == arg)
       return;
     this.retryCount = arg;
   }
      
   @XmlElement(name="retryCount", required=false)
   

   public Long getRetryCount()
   {
     return this.retryCount;
   }
   
   public void setValidatedOn(Date arg)
   {
     if(this.validatedOn == arg)
       return;
     this.validatedOn = arg;
   }
      
   @XmlElement(name="validatedOn", required=false)
   

   public Date getValidatedOn()
   {
     return this.validatedOn;
   }
   
   public void setProcessedOn(Date arg)
   {
     if(this.processedOn == arg)
       return;
     this.processedOn = arg;
   }
      
   @XmlElement(name="processedOn", required=false)
   

   public Date getProcessedOn()
   {
     return this.processedOn;
   }
   
   public void setUploadedOn(Date arg)
   {
     if(this.uploadedOn == arg)
       return;
     this.uploadedOn = arg;
   }
      
   @XmlElement(name="uploadedOn", required=false)
   

   public Date getUploadedOn()
   {
     return this.uploadedOn;
   }

      
   @XmlTransient
   

   public Date getLastUpdatedTime()
   {
     return this.lastUpdatedTime;
   }
      
   public java.util.Set<com.enhancesys.entities.integration.InterfaceFileSummaryDetails> getFileSummaryDetails()
   {
     this.fileSummaryDetails.size();
     return java.util.Collections.unmodifiableSet(this.fileSummaryDetails);
   }


   public Boolean hasFileSummaryDetail(com.enhancesys.entities.integration.InterfaceFileSummaryDetails arg)
   {
     return this.fileSummaryDetails.contains(arg);
   }


   public void addFileSummaryDetail(com.enhancesys.entities.integration.InterfaceFileSummaryDetails arg)
   {
     if(arg == null || this.fileSummaryDetails.contains(arg))
       return;
     this.fileSummaryDetails.add(arg);
     arg.setInterfaceFileSummary(this);
   }


   public void removeFileSummaryDetail(com.enhancesys.entities.integration.InterfaceFileSummaryDetails arg)
   {
     if(arg == null || !this.fileSummaryDetails.contains(arg))
       return;
     this.fileSummaryDetails.remove(arg);
     arg.setInterfaceFileSummary(this);
   }

   @javax.validation.constraints.NotNull
   @Id
   @Column(name="file_id_n")
   @GeneratedValue(generator="tr_interface_file_sum_seq", strategy=GenerationType.SEQUENCE)
   @SequenceGenerator(allocationSize=1, initialValue=1, name="tr_interface_file_sum_seq", sequenceName="tr_interface_file_sum_seq")
   protected Long fileId;

   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="file_name_v" , nullable=false)
   protected String fileName;

   @javax.validation.constraints.NotNull
   @ManyToOne(fetch=FetchType.EAGER, optional = false)
   @JoinColumn(name="interface_id_n", nullable=false)
   protected com.enhancesys.entities.integration.Interfaces interfaces;

   @Basic
   @Column(name="received_server_v" , nullable=true)
   protected String receivedServerDetails;

   @Basic
   @Column(name="send_server_v" , nullable=true)
   protected String sendServerDetails;

   @Basic
   @Column(name="local_server_v" , nullable=true)
   protected String localServerDetails;

   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="total_count_n" , nullable=false)
   protected Long totalCount;

   @Basic
   @Column(name="success_count_n" , nullable=true)
   protected Long successCount;

   @Basic
   @Column(name="error_count_n" , nullable=true)
   protected Long errorCount;

   @Basic
   @Column(name="filter_count_n" , nullable=true)
   protected Long filterCount;

   @Basic
   @Column(name="uploaded_by_n" , nullable=true)
   protected Long uploadedBy;

   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="status_n", nullable=false)
   protected net.treetechnologies.entities.masters.Status status;

   @Basic
   @Column(name="message_v" , nullable=true)
   protected String message;

   @Basic
   @Column(name="error_message_v" , nullable=true)
   protected String errorMessage;

   @Basic
   @Column(name="retry_count_n" , nullable=true)
   protected Long retryCount;

   @Basic
   @Column(name="validated_on_dt" , nullable=true)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date validatedOn;

   @Basic
   @Column(name="processed_on_dt" , nullable=true)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date processedOn;

   @Basic
   @Column(name="uploaded_on_dt" , nullable=true)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date uploadedOn;

   @javax.validation.constraints.NotNull
   @Version
   @Column(name="last_updated_time_dt" , nullable=false)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date lastUpdatedTime;

   @OneToMany(fetch=FetchType.LAZY, mappedBy="interfaceFileSummary")
   protected java.util.Set<com.enhancesys.entities.integration.InterfaceFileSummaryDetails> fileSummaryDetails = new java.util.HashSet<com.enhancesys.entities.integration.InterfaceFileSummaryDetails>();

}