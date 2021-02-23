package com.enhancesys.entities.integration;

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


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="interfaceFailure")
@XmlRootElement(name="interfaceFailure")
@Entity(name="com.enhancesys.entities.integration.InterfaceFailure")
@Table(name="tr_interface_failure", schema = "interface")
@Access(AccessType.FIELD)
public class InterfaceFailure implements java.io.Serializable 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,InterfaceFailure>
    {
        @Override
        public Long marshal(InterfaceFailure interfaceFailure) throws Exception 
        {
            return interfaceFailure.getTransactionFailureId();
        }

        @Override
        public InterfaceFailure unmarshal(Long arg) throws Exception 
        {
            InterfaceFailure interfaceFailure = new InterfaceFailure();
            interfaceFailure.setTransactionFailureId(arg);
            return interfaceFailure;
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
    
    @XmlJavaTypeAdapter(value=InterfaceSummary.IDAdapter.class)
    @XmlElement(name="transactionId", required=true)
    public com.enhancesys.entities.integration.InterfaceSummary getInterfaceSummary()
    {
        return this.interfaceSummary;
    }
    
    public void setInterfaceSummary(com.enhancesys.entities.integration.InterfaceSummary arg)
    {
        if(this.interfaceSummary == arg)
            return;
            
        
        this.interfaceSummary = arg;

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
    @GeneratedValue(generator="tr_interface_failure_seq", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize=1, initialValue=1, name="tr_interface_failure_seq", sequenceName="tr_interface_failure_seq")
    protected Long transactionFailureId; 
    
    @javax.validation.constraints.NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="trans_id_n", nullable=false)
    protected com.enhancesys.entities.integration.InterfaceSummary interfaceSummary; 
    
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
