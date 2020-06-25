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
import javax.persistence.Lob;
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

import com.enhancesys.jobcommon.Constants;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="moduleSummary")
@XmlRootElement(name="moduleSummary")
@Entity(name="com.enhancesys.jobcommon.beans.ModuleSummary")
@Table(name="tr_module_summary", schema = Constants._jobengineSchema)
@Access(AccessType.FIELD)
public class ModuleSummary implements java.io.Serializable 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,ModuleSummary>
    {
        @Override
        public Long marshal(ModuleSummary moduleSummary) throws Exception 
        {
            return moduleSummary.getTransactionId();
        }

        @Override
        public ModuleSummary unmarshal(Long arg) throws Exception 
        {
            ModuleSummary moduleSummary = new ModuleSummary();
            moduleSummary.setTransactionId(arg);
            return moduleSummary;
        }
    
    }

    public void setTransactionId(Long arg)
    {
        this.transactionId = arg;
    }
    
    @XmlElement(name="transactionId")
    public Long getTransactionId()
    {
        return this.transactionId;
    }
    
    @XmlJavaTypeAdapter(value=Module.IDAdapter.class)
    @XmlElement(name="module", required=true)
    public com.enhancesys.jobcommon.beans.Module getModule()
    {
        return this.module;
    }
    
    public void setModule(com.enhancesys.jobcommon.beans.Module arg)
    {
        if(arg == null || this.module == arg)
            return;

        this.module = arg;
    }
    
    @XmlElement(name="originalRequestData", required=true)
    public byte[] getOriginalRequestData()
    {
        return this.originalRequestData;
    }
    
    public void setOriginalRequestData(byte[] arg)
    {
        this.originalRequestData = arg;
    }
    
    @XmlElement(name="requestData", required=true)
    public byte[] getRequestData()
    {
        return this.requestData;
    }
    
    public void setRequestData(byte[] arg)
    {
        this.requestData = arg;
    }
    
    @XmlElement(name="requestTime", required=true)
    public Date getRequestTime()
    {
        return this.requestTime;
    }
    
    public void setRequestTime(Date arg)
    {
        this.requestTime = arg;
    }
    
    @XmlElement(name="dueTime", required=false)
    public Date getDueTime()
    {
        return this.dueTime;
    }
    
    public void setDueTime(Date arg)
    {
        this.dueTime = arg;
    }
    
    @XmlElement(name="ackData", required=false)
    public byte[] getAckData()
    {
        return this.ackData;
    }
    
    public void setAckData(byte[] arg)
    {
        this.ackData = arg;
    }
    
    @XmlElement(name="ackTime", required=false)
    public Date getAckTime()
    {
        return this.ackTime;
    }
    
    public void setAckTime(Date arg)
    {
        this.ackTime = arg;
    }
    
    @XmlElement(name="originalResponseData", required=false)
    public byte[] getOriginalResponseData()
    {
        return this.originalResponseData;
    }
    
    public void setOriginalResponseData(byte[] arg)
    {
        this.originalResponseData = arg;
    }
    
    @XmlElement(name="responseData", required=false)
    public byte[] getResponseData()
    {
        return this.responseData;
    }
    
    public void setResponseData(byte[] arg)
    {
        this.responseData = arg;
    }
    
    @XmlElement(name="responseTime", required=false)
    public Date getResponseTime()
    {
        return this.responseTime;
    }
    
    public void setResponseTime(Date arg)
    {
        this.responseTime = arg;
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
    
    @XmlElement(name="retryCount", required=false)
    public Long getRetryCount()
    {
        return this.retryCount;
    }
    
    public void setRetryCount(Long arg)
    {
        this.retryCount = arg;
    }
    
    @XmlElement(name="referenceData1", required=false)
    public String getReferenceData1()
    {
        return this.referenceData1;
    }
    
    public void setReferenceData1(String arg)
    {
        this.referenceData1 = arg;
    }
    
    @XmlElement(name="referenceData2", required=false)
    public Long getReferenceData2()
    {
        return this.referenceData2;
    }
    
    public void setReferenceData2(Long arg)
    {
        this.referenceData2 = arg;
    }
    
    @XmlElement(name="referenceData3", required=false)
    public Long getReferenceData3()
    {
        return this.referenceData3;
    }
    
    public void setReferenceData3(Long arg)
    {
        this.referenceData3 = arg;
    }
    
    @XmlElement(name="referenceData4", required=false)
    public Long getReferenceData4()
    {
        return this.referenceData4;
    }
    
    public void setReferenceData4(Long arg)
    {
        this.referenceData4 = arg;
    }
    
    @XmlElement(name="referenceData5", required=false)
    public String getReferenceData5()
    {
        return this.referenceData5;
    }
    
    public void setReferenceData5(String arg)
    {
        this.referenceData5 = arg;
    }
    
    @XmlTransient
    public Date getLastUpdatedTime()
    {
        return this.lastUpdatedTime;
    }
    
    @javax.validation.constraints.NotNull
    
    @Id
    @Column(name="trans_id_n")
    @GeneratedValue(generator="tr_module_summary_seq", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize=1, initialValue=1, name="tr_module_summary_seq", sequenceName="tr_module_summary_seq")
    protected Long transactionId; 
    
    @javax.validation.constraints.NotNull
    @ManyToOne(fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="module_id_n", nullable=false)
    protected com.enhancesys.jobcommon.beans.Module module; 
    
    @javax.validation.constraints.NotNull
    @Lob
    
    @Basic
    @Column(name="orgnl_request_data_b", nullable=false)
    protected byte[] originalRequestData; 
    
    @javax.validation.constraints.NotNull
    @Lob
    
    @Basic
    @Column(name="request_data_b", nullable=false)
    protected byte[] requestData; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="request_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date requestTime; 
    
    
    @Basic
    @Column(name="due_time_dt", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dueTime; 
    
    @Lob
    
    @Basic
    @Column(name="ack_data_b", nullable=true)
    protected byte[] ackData; 
    
    
    @Basic
    @Column(name="ack_time_dt", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date ackTime; 
    
    @Lob
    
    @Basic
    @Column(name="orgnl_response_data_b", nullable=true)
    protected byte[] originalResponseData; 
    
    @Lob
    
    @Basic
    @Column(name="response_data_b", nullable=true)
    protected byte[] responseData; 
    
    
    @Basic
    @Column(name="response_time_dt", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date responseTime; 
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="status_n", nullable=false)
    protected com.enhancesys.jobcommon.beans.Status status; 
    
    
    @Basic
    @Column(name="retry_count_n", nullable=true)
    protected Long retryCount; 
    
    
    @Basic
    @Column(name="ref_data1_v", nullable=true)
    protected String referenceData1; 
    
    
    @Basic
    @Column(name="ref_data2_n", nullable=true)
    protected Long referenceData2; 
    
    
    @Basic
    @Column(name="ref_data3_n", nullable=true)
    protected Long referenceData3; 
    
    
    @Basic
    @Column(name="ref_data4_n", nullable=true)
    protected Long referenceData4; 
    
    
    @Basic
    @Column(name="ref_data5_v", nullable=true)
    protected String referenceData5; 
    
    @javax.validation.constraints.NotNull
    
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
}     
