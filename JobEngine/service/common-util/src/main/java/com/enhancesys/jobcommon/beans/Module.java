package com.enhancesys.jobcommon.beans;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
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

import org.hibernate.envers.AuditMappedBy;
import org.hibernate.envers.Audited;

import com.enhancesys.jobcommon.Constants;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="module")
@XmlRootElement(name="module")
@Entity(name="com.enhancesys.jobcommon.beans.Module")
@Table(name="ms_module", schema = Constants._jobengineSchema)
@Access(AccessType.FIELD)
public class Module implements java.io.Serializable 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,Module>
    {
        @Override
        public Long marshal(Module module) throws Exception 
        {
            return module.getModuleId();
        }

        @Override
        public Module unmarshal(Long arg) throws Exception 
        {
            Module module = new Module();
            module.setModuleId(arg);
            return module;
        }
    
    }

    @XmlTransient
    public static class AttributesListener
    {
        @PrePersist
        public void onPersist(com.enhancesys.jobcommon.beans.ModuleAttribute arg)
        {
            Module obj = arg.getModule();
            obj._addAttributes(arg);
        }
        
        @PreRemove
        public void onRemove(com.enhancesys.jobcommon.beans.ModuleAttribute arg)
        {
            Module obj = arg.getModule();
            obj._removeAttributes(arg);
        }
        
    }
    
    void _addAttributes(com.enhancesys.jobcommon.beans.ModuleAttribute arg)
    {
        this.attributes.add(arg);
    }

    void _removeAttributes(com.enhancesys.jobcommon.beans.ModuleAttribute arg)
    {
        this.attributes.remove(arg);
    }
    
    public void setModuleId(Long arg)
    {
        this.moduleId = arg;
    }
    
    @XmlElement(name="moduleId", required = true)
    public Long getModuleId()
    {
        return this.moduleId;
    }
    
    @XmlElement(name="name", required=true)
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String arg)
    {
        this.name = arg;
    }
    
    @XmlElement(name="moduleType", required=true)
    public Long getModuleType()
    {
        return this.moduleType;
    }
    
    public void setModuleType(Long arg)
    {
        this.moduleType = arg;
    }
    
    @XmlElement(name="transactionType", required=true)
    public Long getTransactionType()
    {
        return this.transactionType;
    }
    
    public void setTransactionType(Long arg)
    {
        this.transactionType = arg;
    }
    
    @XmlElement(name="sequence", required=false)
    public Long getSequence()
    {
        return this.sequence;
    }
    
    public void setSequence(Long arg)
    {
        this.sequence = arg;
    }
    
    @XmlElement(name="converter", required=true)
    public String getConverter()
    {
        return this.converter;
    }
    
    public void setConverter(String arg)
    {
        this.converter = arg;
    }
    
    @XmlElement(name="publisher", required=true)
    public String getPublisher()
    {
        return this.publisher;
    }
    
    public void setPublisher(String arg)
    {
        this.publisher = arg;
    }
    
    @XmlElement(name="responseProcessor", required=false)
    public String getResponseProcessor()
    {
        return this.responseProcessor;
    }
    
    public void setResponseProcessor(String arg)
    {
        this.responseProcessor = arg;
    }
    
    @XmlTransient
    public Date getLastUpdatedTime()
    {
        return this.lastUpdatedTime;
    }
    
    public java.util.Set<com.enhancesys.jobcommon.beans.ModuleAttribute> getAttributes()
    {
        this.attributes.size();
        return java.util.Collections.unmodifiableSet(this.attributes);
    }
    
    public Boolean hasAttribute(com.enhancesys.jobcommon.beans.ModuleAttribute arg)
    {
        return this.attributes.contains(arg);
    }
    
    public void addAttribute(com.enhancesys.jobcommon.beans.ModuleAttribute arg)
    {
        if(arg == null || this.attributes.contains(arg))
            return;
        this.attributes.add(arg);
        arg.setModule(this);        
    }
    
    public void removeAttribute(com.enhancesys.jobcommon.beans.ModuleAttribute arg)
    {
        if(arg == null || !this.attributes.contains(arg))
            return;
        this.attributes.remove(arg);
        arg.setModule(this);        
    }
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Id
    @Column(name="module_id_n")
    protected Long moduleId; 
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="name_v", nullable=false)
    protected String name; 
    
    @Audited
    @javax.validation.constraints.NotNull

    @Basic
    @Column(name="module_type_n", nullable=false)
    protected Long moduleType; 
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="trans_type_n", nullable=false)
    protected Long transactionType; 
    
    @Audited
    
    @Basic
    @Column(name="seq_n", nullable=true)
    protected Long sequence; 
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="converter_v", nullable=false)
    protected String converter; 
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="publisher_v", nullable=false)
    protected String publisher; 
    
    @Audited
    
    @Basic
    @Column(name="response_processor_v", nullable=true)
    protected String responseProcessor; 
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
    @AuditMappedBy(mappedBy="module")
    @OneToMany(fetch=FetchType.LAZY, mappedBy="module")
    protected java.util.Set<com.enhancesys.jobcommon.beans.ModuleAttribute> attributes = new java.util.HashSet<com.enhancesys.jobcommon.beans.ModuleAttribute>(); 
    
}     
