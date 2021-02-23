package com.enhancesys.integration.snoc.entities;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@XmlType(name="kycSyncInfo")
@XmlRootElement(name="kycSyncInfo")
@Entity(name="com.enhancesys.integration.snoc.entities.KycSyncInfo")
@Table(name="MS_KYC_SYNC_INFO", schema = "interface")
@Access(AccessType.FIELD)
public class KycSyncInfo implements java.io.Serializable 
{
    /**
	 * 
	 */
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
        this.syncId = arg;
    }
    
    @XmlElement(name="syncId", required = true)
    public Long getSyncId()
    {
        return this.syncId;
    }
    
    @XmlJavaTypeAdapter(value=Interfaces.IDAdapter.class)
    @XmlElement(name="interfaces", required=true)
    public com.enhancesys.integration.snoc.entities.Interfaces getInterfaces()
    {
        return this.interfaces;
    }
    
    public void setInterfaces(com.enhancesys.integration.snoc.entities.Interfaces arg)
    {
        if(arg == null || this.interfaces == arg)
            return;

        this.interfaces = arg;
    }
    
    @XmlElement(name="kycField", required=false)
    public String getKycField()
    {
        return this.kycField;
    }
    
    public void setKycField(String arg)
    {
        this.kycField = arg;
    }
    
    @XmlElement(name="kycFlag", required=false)
    public Long getKycFlag()
    {
        return this.kycFlag;
    }
    
    public void setKycFlag(Long arg)
    {
        this.kycFlag = arg;
    }
    
    @XmlTransient
    public Date getLastUpdatedTime()
    {
        return this.lastUpdatedTime;
    }
    
    @javax.validation.constraints.NotNull
    
    @Id
    @Column(name="sync_id_n")
    protected Long syncId; 
    
    @javax.validation.constraints.NotNull
    @ManyToOne(fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="interface_id_n", nullable=false)
    protected com.enhancesys.integration.snoc.entities.Interfaces interfaces; 
    
    
    @Basic
    @Column(name="kyc_field_v", nullable=false)
    protected String kycField; 
    
    
    @Basic
    @Column(name="kyc_flag_n", nullable=false)
    protected Long kycFlag; 
    
    @javax.validation.constraints.NotNull
    
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
}     
