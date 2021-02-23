package com.enhancesys.integration.snoc.entities;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="interfaces")
@XmlRootElement(name="interfaces")
@Entity(name="com.enhancesys.integration.snoc.entities.Interfaces")
@Table(name="MS_INTERFACE", schema = "interface")
@Access(AccessType.FIELD)
@EntityListeners(value={com.enhancesys.integration.snoc.entities.Module.InterfacesListener.class})
public class Interfaces implements java.io.Serializable 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,Interfaces>
    {
        @Override
        public Long marshal(Interfaces interfaces) throws Exception 
        {
            return interfaces.getInterfaceId();
        }

        @Override
        public Interfaces unmarshal(Long arg) throws Exception 
        {
            Interfaces interfaces = new Interfaces();
            interfaces.setInterfaceId(arg);
            return interfaces;
        }
    
    }

    @XmlTransient
    public static class AttributesListener
    {
        @PrePersist
        public void onPersist(com.enhancesys.integration.snoc.entities.InterfaceAttribute arg)
        {
            Interfaces obj = arg.getInterfaces();
            obj._addAttributes(arg);
        }
        
        @PreRemove
        public void onRemove(com.enhancesys.integration.snoc.entities.InterfaceAttribute arg)
        {
            Interfaces obj = arg.getInterfaces();
            obj._removeAttributes(arg);
        }
        
    }
    
    void _addAttributes(com.enhancesys.integration.snoc.entities.InterfaceAttribute arg)
    {
        this.attributes.add(arg);
    }

    void _removeAttributes(com.enhancesys.integration.snoc.entities.InterfaceAttribute arg)
    {
        this.attributes.remove(arg);
    }
    
    public void setInterfaceId(Long arg)
    {
        this.interfaceId = arg;
    }
    
    @XmlElement(name="interfaceId", required = true)
    public Long getInterfaceId()
    {
        return this.interfaceId;
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
    
    @XmlJavaTypeAdapter(value=Module.IDAdapter.class)
    @XmlElement(name="moduleId", required=true)
    public com.enhancesys.integration.snoc.entities.Module getModule()
    {
        return this.module;
    }
    
    public void setModule(com.enhancesys.integration.snoc.entities.Module arg)
    {
        if(this.module == arg)
            return;
            
        com.enhancesys.integration.snoc.entities.Module oldModule = this.module;
        this.module = null;
        if(oldModule != null)
            oldModule.removeInterface(this);
        
        this.module = arg;

        if(this.module != null)
            this.module.addInterface(this);
    }
    
    @XmlElement(name="interfaceType", required=true)
    public Long getInterfaceType()
    {
        return this.interfaceType;
    }
    
    public void setInterfaceType(Long arg)
    {
        this.interfaceType = arg;
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
    
    public java.util.Set<com.enhancesys.integration.snoc.entities.InterfaceAttribute> getAttributes()
    {
        this.attributes.size();
        return java.util.Collections.unmodifiableSet(this.attributes);
    }
    
    public Boolean hasAttribute(com.enhancesys.integration.snoc.entities.InterfaceAttribute arg)
    {
        return this.attributes.contains(arg);
    }
    
    public void addAttribute(com.enhancesys.integration.snoc.entities.InterfaceAttribute arg)
    {
        if(arg == null || this.attributes.contains(arg))
            return;
        this.attributes.add(arg);
        arg.setInterfaces(this);        
    }
    
    public void removeAttribute(com.enhancesys.integration.snoc.entities.InterfaceAttribute arg)
    {
        if(arg == null || !this.attributes.contains(arg))
            return;
        this.attributes.remove(arg);
        arg.setInterfaces(this);        
    }
    
    @javax.validation.constraints.NotNull
    
    @Id
    @Column(name="interface_id_n")
    protected Long interfaceId; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="name_v", nullable=false)
    protected String name; 
    
    @javax.validation.constraints.NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="module_id_n", nullable=false)
    protected com.enhancesys.integration.snoc.entities.Module module; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="interface_type_n", nullable=false)
    protected Long interfaceType; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="trans_type_n", nullable=false)
    protected Long transactionType; 
    
    
    @Basic
    @Column(name="seq_n", nullable=true)
    protected Long sequence; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="converter_v", nullable=false)
    protected String converter; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="publisher_v", nullable=false)
    protected String publisher; 
    
    
    @Basic
    @Column(name="response_processor_v", nullable=true)
    protected String responseProcessor; 
    
    @javax.validation.constraints.NotNull
    
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="interfaces")
    protected java.util.Set<com.enhancesys.integration.snoc.entities.InterfaceAttribute> attributes = new java.util.HashSet<com.enhancesys.integration.snoc.entities.InterfaceAttribute>(); 
    
}     
