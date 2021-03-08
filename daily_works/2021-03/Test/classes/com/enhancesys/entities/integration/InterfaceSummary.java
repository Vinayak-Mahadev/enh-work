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
@XmlType(name="interfaceSummary")
@XmlRootElement(name="interfaceSummary")
@Entity(name="com.enhancesys.entities.integration.InterfaceSummary")
@Table(name="tr_interface_summary")
@Access(AccessType.FIELD)
public class InterfaceSummary implements  java.io.Serializable
{
   private static final long serialVersionUID = 1L;


   @XmlTransient
   public static class IDAdapter extends XmlAdapter<Long,InterfaceSummary>
   {
     @Override
     public Long marshal(InterfaceSummary interfaceSummary) throws Exception 
     {
       return interfaceSummary.getTransactionId();
     }
     
     @Override
     public InterfaceSummary unmarshal(Long arg) throws Exception 
     {
       InterfaceSummary interfaceSummary = new InterfaceSummary();
       interfaceSummary.setTransactionId(arg);
       return interfaceSummary;
     }
   
   }



   
   public void setTransactionId(Long arg)
   {
     if(this.transactionId == arg)
       return;
     this.transactionId = arg;
   }
      
   @XmlElement(name="transactionId")
   

   public Long getTransactionId()
   {
     return this.transactionId;
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
   
   public void setOriginalRequestData(byte[] arg)
   {
     if(this.originalRequestData == arg)
       return;
     this.originalRequestData = arg;
   }
      
   @XmlElement(name="originalRequestData", required=true)
   

   public byte[] getOriginalRequestData()
   {
     return this.originalRequestData;
   }
   
   public void setRequestData(byte[] arg)
   {
     if(this.requestData == arg)
       return;
     this.requestData = arg;
   }
      
   @XmlElement(name="requestData", required=true)
   

   public byte[] getRequestData()
   {
     return this.requestData;
   }
   
   public void setRequestTime(Date arg)
   {
     if(this.requestTime == arg)
       return;
     this.requestTime = arg;
   }
      
   @XmlElement(name="requestTime", required=true)
   

   public Date getRequestTime()
   {
     return this.requestTime;
   }
   
   public void setDueTime(Date arg)
   {
     if(this.dueTime == arg)
       return;
     this.dueTime = arg;
   }
      
   @XmlElement(name="dueTime", required=false)
   

   public Date getDueTime()
   {
     return this.dueTime;
   }
   
   public void setAckData(byte[] arg)
   {
     if(this.ackData == arg)
       return;
     this.ackData = arg;
   }
      
   @XmlElement(name="ackData", required=false)
   

   public byte[] getAckData()
   {
     return this.ackData;
   }
   
   public void setAckTime(Date arg)
   {
     if(this.ackTime == arg)
       return;
     this.ackTime = arg;
   }
      
   @XmlElement(name="ackTime", required=false)
   

   public Date getAckTime()
   {
     return this.ackTime;
   }
   
   public void setOriginalResponseData(byte[] arg)
   {
     if(this.originalResponseData == arg)
       return;
     this.originalResponseData = arg;
   }
      
   @XmlElement(name="originalResponseData", required=false)
   

   public byte[] getOriginalResponseData()
   {
     return this.originalResponseData;
   }
   
   public void setResponseData(byte[] arg)
   {
     if(this.responseData == arg)
       return;
     this.responseData = arg;
   }
      
   @XmlElement(name="responseData", required=false)
   

   public byte[] getResponseData()
   {
     return this.responseData;
   }
   
   public void setResponseTime(Date arg)
   {
     if(this.responseTime == arg)
       return;
     this.responseTime = arg;
   }
      
   @XmlElement(name="responseTime", required=false)
   

   public Date getResponseTime()
   {
     return this.responseTime;
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
   
   public void setReferenceData1(String arg)
   {
     if(this.referenceData1 == arg)
       return;
     this.referenceData1 = arg;
   }
      
   @XmlElement(name="referenceData1", required=false)
   

   public String getReferenceData1()
   {
     return this.referenceData1;
   }
   
   public void setReferenceData2(Long arg)
   {
     if(this.referenceData2 == arg)
       return;
     this.referenceData2 = arg;
   }
      
   @XmlElement(name="referenceData2", required=false)
   

   public Long getReferenceData2()
   {
     return this.referenceData2;
   }
   
   public void setReferenceData3(Long arg)
   {
     if(this.referenceData3 == arg)
       return;
     this.referenceData3 = arg;
   }
      
   @XmlElement(name="referenceData3", required=false)
   

   public Long getReferenceData3()
   {
     return this.referenceData3;
   }
   
   public void setReferenceData4(Long arg)
   {
     if(this.referenceData4 == arg)
       return;
     this.referenceData4 = arg;
   }
      
   @XmlElement(name="referenceData4", required=false)
   

   public Long getReferenceData4()
   {
     return this.referenceData4;
   }
   
   public void setReferenceData5(String arg)
   {
     if(this.referenceData5 == arg)
       return;
     this.referenceData5 = arg;
   }
      
   @XmlElement(name="referenceData5", required=false)
   

   public String getReferenceData5()
   {
     return this.referenceData5;
   }
   
   public void setReferenceData6(String arg)
   {
     if(this.referenceData6 == arg)
       return;
     this.referenceData6 = arg;
   }
      
   @XmlElement(name="referenceData6", required=false)
   

   public String getReferenceData6()
   {
     return this.referenceData6;
   }
   
   public void setReferenceData7(String arg)
   {
     if(this.referenceData7 == arg)
       return;
     this.referenceData7 = arg;
   }
      
   @XmlElement(name="referenceData7", required=false)
   

   public String getReferenceData7()
   {
     return this.referenceData7;
   }
   
   public void setReferenceData8(String arg)
   {
     if(this.referenceData8 == arg)
       return;
     this.referenceData8 = arg;
   }
      
   @XmlElement(name="referenceData8", required=false)
   

   public String getReferenceData8()
   {
     return this.referenceData8;
   }

      
   @XmlTransient
   

   public Date getLastUpdatedTime()
   {
     return this.lastUpdatedTime;
   }



   @javax.validation.constraints.NotNull
   @Id
   @Column(name="trans_id_n")
   @GeneratedValue(generator="tr_interface_summary_seq", strategy=GenerationType.SEQUENCE)
   @SequenceGenerator(allocationSize=1, initialValue=1, name="tr_interface_summary_seq", sequenceName="tr_interface_summary_seq")
   protected Long transactionId;

   @javax.validation.constraints.NotNull
   @ManyToOne(fetch=FetchType.EAGER, optional = false)
   @JoinColumn(name="interface_id_n", nullable=false)
   protected com.enhancesys.entities.integration.Interfaces interfaces;

   @javax.validation.constraints.NotNull
   @Lob
   @Basic
   @Column(name="orgnl_request_data_b" , nullable=false)
   protected byte[] originalRequestData;

   @javax.validation.constraints.NotNull
   @Lob
   @Basic
   @Column(name="request_data_b" , nullable=false)
   protected byte[] requestData;

   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="request_time_dt" , nullable=false)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date requestTime;

   @Basic
   @Column(name="due_time_dt" , nullable=true)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date dueTime;

   @Lob
   @Basic
   @Column(name="ack_data_b" , nullable=true)
   protected byte[] ackData;

   @Basic
   @Column(name="ack_time_dt" , nullable=true)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date ackTime;

   @Lob
   @Basic
   @Column(name="orgnl_response_data_b" , nullable=true)
   protected byte[] originalResponseData;

   @Lob
   @Basic
   @Column(name="response_data_b" , nullable=true)
   protected byte[] responseData;

   @Basic
   @Column(name="response_time_dt" , nullable=true)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date responseTime;

   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="status_n", nullable=false)
   protected net.treetechnologies.entities.masters.Status status;

   @Basic
   @Column(name="retry_count_n" , nullable=true)
   protected Long retryCount;

   @Basic
   @Column(name="ref_data1_v" , nullable=true)
   protected String referenceData1;

   @Basic
   @Column(name="ref_data2_n" , nullable=true)
   protected Long referenceData2;

   @Basic
   @Column(name="ref_data3_n" , nullable=true)
   protected Long referenceData3;

   @Basic
   @Column(name="ref_data4_n" , nullable=true)
   protected Long referenceData4;

   @Basic
   @Column(name="ref_data5_v" , nullable=true)
   protected String referenceData5;

   @Basic
   @Column(name="ref_data6_v" , nullable=true)
   protected String referenceData6;

   @Basic
   @Column(name="ref_data7_v" , nullable=true)
   protected String referenceData7;

   @Basic
   @Column(name="ref_data8_v" , nullable=true)
   protected String referenceData8;

   @javax.validation.constraints.NotNull
   @Version
   @Column(name="last_updated_time_dt" , nullable=false)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date lastUpdatedTime;

}