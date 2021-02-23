package com.enhancesys.integration.snoc.entities;

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


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="module")
@XmlRootElement(name="module")
@Entity(name="com.enhancesys.integration.snoc.entities.Module")
@Table(name="MS_MODULE", schema = "interface")
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
    public static class InterfacesListener
    {
        @PrePersist
        public void onPersist(com.enhancesys.integration.snoc.entities.Interfaces arg)
        {
            Module obj = arg.getModule();
            obj._addInterfaces(arg);
        }
        
        @PreRemove
        public void onRemove(com.enhancesys.integration.snoc.entities.Interfaces arg)
        {
            Module obj = arg.getModule();
            obj._removeInterfaces(arg);
        }
        
    }
    
    void _addInterfaces(com.enhancesys.integration.snoc.entities.Interfaces arg)
    {
        this.interfaces.add(arg);
    }

    void _removeInterfaces(com.enhancesys.integration.snoc.entities.Interfaces arg)
    {
        this.interfaces.remove(arg);
    }
    
    public void setModuleId(Long arg)
    {
        this.moduleId = arg;
    }
    
    @XmlElement(name="moduleId", required=true)
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
    
    @XmlElement(name="callBack")
    public String getCallBack()
    {
        return this.callBack;
    }
    
    public void setCallBack(String arg)
    {
        this.callBack = arg;
    }
    
    @XmlElement(name="priority")
    public Long getPriority()
    {
        return this.priority;
    }
    
    public void setPriority(Long arg)
    {
        this.priority = arg;
    }
    
    @XmlTransient
    public Date getLastUpdatedTime()
    {
        return this.lastUpdatedTime;
    }
    
    public java.util.Set<com.enhancesys.integration.snoc.entities.Interfaces> getInterfaces()
    {
        this.interfaces.size();
        return java.util.Collections.unmodifiableSet(this.interfaces);
    }
    
    public Boolean hasInterface(com.enhancesys.integration.snoc.entities.Interfaces arg)
    {
        return this.interfaces.contains(arg);
    }
    
    public void addInterface(com.enhancesys.integration.snoc.entities.Interfaces arg)
    {
        if(arg == null || this.interfaces.contains(arg))
            return;
        this.interfaces.add(arg);
        arg.setModule(this);        
    }
    
    public void removeInterface(com.enhancesys.integration.snoc.entities.Interfaces arg)
    {
        if(arg == null || !this.interfaces.contains(arg))
            return;
        this.interfaces.remove(arg);
        arg.setModule(this);        
    }
    
    @javax.validation.constraints.NotNull
    
    @Id
    @Column(name="module_id_n")
    protected Long moduleId; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="name_v", nullable=false)
    protected String name; 
    
    
    @Basic
    @Column(name="call_back_v", nullable=true)
    protected String callBack; 
    
    
    @Basic
    @Column(name="priority_n", nullable=true)
    protected Long priority; 
    
    @javax.validation.constraints.NotNull
    
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="module")
    protected java.util.Set<com.enhancesys.integration.snoc.entities.Interfaces> interfaces = new java.util.HashSet<com.enhancesys.integration.snoc.entities.Interfaces>(); 
    
}     
