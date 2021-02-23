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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
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
@XmlType(name="interfaceFileSummary")
@XmlRootElement(name="interfaceFileSummary")
@Entity(name="com.enhancesys.entities.integration.InterfaceFileSummary")
@Table(name="tr_interface_file_summary", schema = "interface")
@Access(AccessType.FIELD)
public class InterfaceFileSummary implements java.io.Serializable 
{
    /**
	 * 
	 */
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
    public static class FilesummarydetailsListener
    {
        @PrePersist
        public void onPersist(com.enhancesys.entities.integration.InterfaceFileSummaryDetails arg)
        {
            InterfaceFileSummary obj = arg.getInterfaceFileSummary();
            obj._addFilesummarydetails(arg);
        }
        
        @PreRemove
        public void onRemove(com.enhancesys.entities.integration.InterfaceFileSummaryDetails arg)
        {
            InterfaceFileSummary obj = arg.getInterfaceFileSummary();
            obj._removeFilesummarydetails(arg);
        }
        
    }
    
    void _addFilesummarydetails(com.enhancesys.entities.integration.InterfaceFileSummaryDetails arg)
    {
        this.filesummarydetails.add(arg);
    }

    void _removeFilesummarydetails(com.enhancesys.entities.integration.InterfaceFileSummaryDetails arg)
    {
        this.filesummarydetails.remove(arg);
    }
    
    public void setFileId(Long arg)
    {
        this.fileId = arg;
    }
    
    @XmlElement(name="fileId", required=true)
    public Long getFileId()
    {
        return this.fileId;
    }
    
    @XmlElement(name="fileName", required=true)
    public String getFileName()
    {
        return this.fileName;
    }
    
    public void setFileName(String arg)
    {
        this.fileName = arg;
    }
    
    @XmlJavaTypeAdapter(value=Interfaces.IDAdapter.class)
    @XmlElement(name="interfaces", required=true)
    public com.enhancesys.entities.integration.Interfaces getInterfaces()
    {
        return this.interfaces;
    }
    
    public void setInterfaces(com.enhancesys.entities.integration.Interfaces arg)
    {
        if(arg == null || this.interfaces == arg)
            return;

        this.interfaces = arg;
    }
    
    @XmlElement(name="receivedServerDetails", required=false)
    public String getReceivedServerDetails()
    {
        return this.receivedServerDetails;
    }
    
    public void setReceivedServerDetails(String arg)
    {
        this.receivedServerDetails = arg;
    }
    
    @XmlElement(name="sendServerDetails", required=false)
    public String getSendServerDetails()
    {
        return this.sendServerDetails;
    }
    
    public void setSendServerDetails(String arg)
    {
        this.sendServerDetails = arg;
    }
    
    @XmlElement(name="localServerDetails", required=false)
    public String getLocalServerDetails()
    {
        return this.localServerDetails;
    }
    
    public void setLocalServerDetails(String arg)
    {
        this.localServerDetails = arg;
    }
    
    @XmlElement(name="totalCount", required=true)
    public Long getTotalCount()
    {
        return this.totalCount;
    }
    
    public void setTotalCount(Long arg)
    {
        this.totalCount = arg;
    }
    
    @XmlElement(name="successCount", required=false)
    public Long getSuccessCount()
    {
        return this.successCount;
    }
    
    public void setSuccessCount(Long arg)
    {
        this.successCount = arg;
    }
    
    @XmlElement(name="errorCount", required=false)
    public Long getErrorCount()
    {
        return this.errorCount;
    }
    
    public void setErrorCount(Long arg)
    {
        this.errorCount = arg;
    }
    
    @XmlElement(name="filterCount", required=false)
    public Long getFilterCount()
    {
        return this.filterCount;
    }
    
    public void setFilterCount(Long arg)
    {
        this.filterCount = arg;
    }
    
    @XmlElement(name="uploadedBy", required=false)
    public Long getUploadedBy()
    {
        return this.uploadedBy;
    }
    
    public void setUploadedBy(Long arg)
    {
        this.uploadedBy = arg;
    }
    
    @XmlJavaTypeAdapter(value=com.enhancesys.entities.integration.Status.IDAdapter.class)
    @XmlElement(name="statusId", required=true)
    public com.enhancesys.entities.integration.Status getStatus()
    {
        return this.status;
    }
    
    public void setStatus(com.enhancesys.entities.integration.Status arg)
    {
        if(this.status == arg)
            return;
            
        
        this.status = arg;

    }
    
    @XmlElement(name="message", required=false)
    public String getMessage()
    {
        return this.message;
    }
    
    public void setMessage(String arg)
    {
        this.message = arg;
    }
    
    @XmlElement(name="errorMessage", required=false)
    public String getErrorMessage()
    {
        return this.errorMessage;
    }
    
    public void setErrorMessage(String arg)
    {
        this.errorMessage = arg;
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
    
    @XmlElement(name="validatedOn", required=false)
    public Date getValidatedOn()
    {
        return this.validatedOn;
    }
    
    public void setValidatedOn(Date arg)
    {
        this.validatedOn = arg;
    }
    
    @XmlElement(name="processedOn", required=false)
    public Date getProcessedOn()
    {
        return this.processedOn;
    }
    
    public void setProcessedOn(Date arg)
    {
        this.processedOn = arg;
    }
    
    @XmlElement(name="uploadedOn", required=false)
    public Date getUploadedOn()
    {
        return this.uploadedOn;
    }
    
    public void setUploadedOn(Date arg)
    {
        this.uploadedOn = arg;
    }
    
    @XmlTransient
    public Date getLastUpdatedTime()
    {
        return this.lastUpdatedTime;
    }
    
    public java.util.Set<com.enhancesys.entities.integration.InterfaceFileSummaryDetails> getFileSummaryDetails()
    {
        this.filesummarydetails.size();
        return java.util.Collections.unmodifiableSet(this.filesummarydetails);
    }
    
    public Boolean hasFileSummaryDetail(com.enhancesys.entities.integration.InterfaceFileSummaryDetails arg)
    {
        return this.filesummarydetails.contains(arg);
    }
    
    public void addFileSummaryDetail(com.enhancesys.entities.integration.InterfaceFileSummaryDetails arg)
    {
        if(arg == null || this.filesummarydetails.contains(arg))
            return;
        this.filesummarydetails.add(arg);
        arg.setInterfaceFileSummary(this);        
    }
    
    public void removeFileSummaryDetail(com.enhancesys.entities.integration.InterfaceFileSummaryDetails arg)
    {
        if(arg == null || !this.filesummarydetails.contains(arg))
            return;
        this.filesummarydetails.remove(arg);
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
    @Column(name="file_name_v", nullable=false)
    protected String fileName; 
    
    @javax.validation.constraints.NotNull
    @ManyToOne(fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="interface_id_n", nullable=false)
    protected com.enhancesys.entities.integration.Interfaces interfaces; 
    
    
    @Basic
    @Column(name="received_server_v", nullable=true)
    protected String receivedServerDetails; 
    
    
    @Basic
    @Column(name="send_server_v", nullable=true)
    protected String sendServerDetails; 
    
    
    @Basic
    @Column(name="local_server_v", nullable=true)
    protected String localServerDetails; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="total_count_n", nullable=false)
    protected Long totalCount; 
    
    
    @Basic
    @Column(name="success_count_n", nullable=true)
    protected Long successCount; 
    
    
    @Basic
    @Column(name="error_count_n", nullable=true)
    protected Long errorCount; 
    
    
    @Basic
    @Column(name="filter_count_n", nullable=true)
    protected Long filterCount; 
    
    
    @Basic
    @Column(name="uploaded_by_n", nullable=true)
    protected Long uploadedBy; 
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="status_n", nullable=false)
    protected com.enhancesys.entities.integration.Status status; 
    
    
    @Basic
    @Column(name="message_v", nullable=true)
    protected String message; 
    
    
    @Basic
    @Column(name="error_message_v", nullable=true)
    protected String errorMessage; 
    
    
    @Basic
    @Column(name="retry_count_n", nullable=true)
    protected Long retryCount; 
    
    
    @Basic
    @Column(name="validated_on_dt", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date validatedOn; 
    
    
    @Basic
    @Column(name="processed_on_dt", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date processedOn; 
    
    
    @Basic
    @Column(name="uploaded_on_dt", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date uploadedOn; 
    
    @javax.validation.constraints.NotNull
    
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="interfaceFileSummary")
    protected java.util.Set<com.enhancesys.entities.integration.InterfaceFileSummaryDetails> filesummarydetails = new java.util.HashSet<com.enhancesys.entities.integration.InterfaceFileSummaryDetails>(); 
    
}     