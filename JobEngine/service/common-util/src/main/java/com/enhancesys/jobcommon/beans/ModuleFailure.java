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
@XmlType(name="moduleFailure")
@XmlRootElement(name="moduleFailure")
@Entity(name="com.enhancesys.jobcommon.beans.ModuleFailure")
@Table(name="tr_module_failure", schema = Constants._jobengineSchema)
@Access(AccessType.FIELD)
public class ModuleFailure implements java.io.Serializable 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,ModuleFailure>
    {
        @Override
        public Long marshal(ModuleFailure moduleFailure) throws Exception 
        {
            return moduleFailure.getTransactionFailureId();
        }

        @Override
        public ModuleFailure unmarshal(Long arg) throws Exception 
        {
            ModuleFailure moduleFailure = new ModuleFailure();
            moduleFailure.setTransactionFailureId(arg);
            return moduleFailure;
        }
    
    }

    public void setTransactionFailureId(Long arg)
    {
        this.transactionFailureId = arg;
    }
    
    @XmlElement(name="transactionFailureId", required = true)
    public Long getTransactionFailureId()
    {
        return this.transactionFailureId;
    }
    
    @XmlJavaTypeAdapter(value=ModuleSummary.IDAdapter.class)
    @XmlElement(name="transactionId", required=true)
    public com.enhancesys.jobcommon.beans.ModuleSummary getModuleSummary()
    {
        return this.moduleSummary;
    }
    
    public void setModuleSummary(com.enhancesys.jobcommon.beans.ModuleSummary arg)
    {
        if(this.moduleSummary == arg)
            return;
            
        
        this.moduleSummary = arg;

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
    
    @XmlElement(name="fileId", required=false)
    public Long getFileId()
    {
        return this.fileId;
    }
    
    public void setFileId(Long arg)
    {
        this.fileId = arg;
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
    
    @XmlElement(name="errorCode", required=true)
    public Long getErrorCode()
    {
        return this.errorCode;
    }
    
    public void setErrorCode(Long arg)
    {
        this.errorCode = arg;
    }
    
    @XmlElement(name="errorMessage", required=true)
    public String getErrorMessage()
    {
        return this.errorMessage;
    }
    
    public void setErrorMessage(String arg)
    {
        this.errorMessage = arg;
    }
    
    @XmlTransient
    public Date getLastUpdatedTime()
    {
        return this.lastUpdatedTime;
    }
    
    @javax.validation.constraints.NotNull
    
    @Id
    @Column(name="trans_failure_id_n")
    @GeneratedValue(generator="tr_module_failure_seq", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize=1, initialValue=1, name="tr_module_failure_seq", sequenceName="tr_module_failure_seq")
    protected Long transactionFailureId; 
    
    @javax.validation.constraints.NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="trans_id_n", nullable=false)
    protected com.enhancesys.jobcommon.beans.ModuleSummary moduleSummary; 
    
    @Lob
    
    @Basic
    @Column(name="ack_data_b", nullable=true)
    protected byte[] ackData; 
    
    
    @Basic
    @Column(name="ack_time_dt", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date ackTime; 
    
    
    @Basic
    @Column(name="file_id_n", nullable=true)
    protected Long fileId; 
    
    @Lob
    
    @Basic
    @Column(name="response_data_b", nullable=true)
    protected byte[] responseData; 
    
    
    @Basic
    @Column(name="response_time_dt", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date responseTime; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="error_code_n", nullable=false)
    protected Long errorCode; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="error_message_v", nullable=false)
    protected String errorMessage; 
    
    @javax.validation.constraints.NotNull
    
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
}     
