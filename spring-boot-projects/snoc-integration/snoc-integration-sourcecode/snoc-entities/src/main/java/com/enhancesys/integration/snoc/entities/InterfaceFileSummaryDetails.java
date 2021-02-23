package com.enhancesys.integration.snoc.entities;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@XmlType(name="interfaceFileSummaryDetails")
@XmlRootElement(name="interfaceFileSummaryDetails")
@Entity(name="com.enhancesys.integration.snoc.entities.InterfaceFileSummaryDetails")
@Table(name="tr_interface_file_summary_details", schema = "interface")
@Access(AccessType.FIELD)
@EntityListeners(value={com.enhancesys.integration.snoc.entities.InterfaceFileSummary.FilesummarydetailsListener.class})
public class InterfaceFileSummaryDetails implements java.io.Serializable 
{
    /**
	 * 
	 */
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
        this.fileDetailsId = arg;
    }
    
    @XmlElement(name="fileDetailsId", required=true)
    public Long getFileDetailsId()
    {
        return this.fileDetailsId;
    }
    
    @XmlJavaTypeAdapter(value=InterfaceFileSummary.IDAdapter.class)
    @XmlElement(name="interfaceFileSummary", required=true)
    public com.enhancesys.integration.snoc.entities.InterfaceFileSummary getInterfaceFileSummary()
    {
        return this.interfaceFileSummary;
    }
    
    public void setInterfaceFileSummary(com.enhancesys.integration.snoc.entities.InterfaceFileSummary arg)
    {
        if(this.interfaceFileSummary == arg)
            return;
            
        com.enhancesys.integration.snoc.entities.InterfaceFileSummary oldInterfaceFileSummary = this.interfaceFileSummary;
        this.interfaceFileSummary = null;
        if(oldInterfaceFileSummary != null)
            oldInterfaceFileSummary.removeFileSummaryDetail(this);
        
        this.interfaceFileSummary = arg;

        if(this.interfaceFileSummary != null)
            this.interfaceFileSummary.addFileSummaryDetail(this);
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
    
    @XmlElement(name="controlFileName", required=true)
    public String getControlFileName()
    {
        return this.controlFileName;
    }
    
    public void setControlFileName(String arg)
    {
        this.controlFileName = arg;
    }
    
    @XmlElement(name="fileType", required=true)
    public String getFileType()
    {
        return this.fileType;
    }
    
    public void setFileType(String arg)
    {
        this.fileType = arg;
    }
    
    @XmlElement(name="totalCount", required=false)
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
    protected com.enhancesys.integration.snoc.entities.InterfaceFileSummary interfaceFileSummary; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="file_name_v", nullable=false)
    protected String fileName; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="ctrl_file_name_v", nullable=false)
    protected String controlFileName; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="file_type_v", nullable=false)
    protected String fileType; 
    
    
    @Basic
    @Column(name="total_count_n", nullable=true)
    protected Long totalCount; 
    
    
    @Basic
    @Column(name="success_count_n", nullable=true)
    protected Long successCount; 
    
    
    @Basic
    @Column(name="error_count_n", nullable=true)
    protected Long errorCount; 
    
    @javax.validation.constraints.NotNull
    
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
}     
