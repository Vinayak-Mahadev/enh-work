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
@XmlType(name="dailyDumpSummary")
@XmlRootElement(name="dailyDumpSummary")
@Entity(name="com.enhancesys.entities.integration.DailyDumpSummary")
@Table(name="tr_daily_dump_summary", schema = "interface")
@Access(AccessType.FIELD)
public class DailyDumpSummary implements java.io.Serializable 
{
    /**
	 * 
	 */
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
        this.dumpId = arg;
    }
    
    @XmlElement(name="dumpId")
    public Long getDumpId()
    {
        return this.dumpId;
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
    
    @XmlElement(name="createdDate", required=true)
    public Date getCreatedDate()
    {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date arg)
    {
        this.createdDate = arg;
    }
    
    @XmlElement(name="status", required=false)
    public Long getStatus()
    {
        return this.status;
    }
    
    public void setStatus(Long arg)
    {
        this.status = arg;
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
    @ManyToOne(fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="interface_id_n", nullable=false)
    protected com.enhancesys.entities.integration.Interfaces interfaces; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="created_date_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate; 
    
    
    @Basic
    @Column(name="status_n", nullable=false)
    protected Long status; 
    
    @javax.validation.constraints.NotNull
    
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
}     
