package com.finevm.enh.interfaces.entities;

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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.hibernate.envers.AuditMappedBy;
import org.hibernate.envers.Audited;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="module")
@XmlRootElement(name="module")
@Entity(name="com.finevm.enh.interfaces.entities.Module")
@Table(name="MS_MODULE", schema = "INTERFACE")
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
        public void onPersist(com.finevm.enh.interfaces.entities.Interfaces arg)
        {
            Module obj = arg.getModule();
            obj._addInterfaces(arg);
        }
        
        @PreRemove
        public void onRemove(com.finevm.enh.interfaces.entities.Interfaces arg)
        {
            Module obj = arg.getModule();
            obj._removeInterfaces(arg);
        }
        
    }
    
    void _addInterfaces(com.finevm.enh.interfaces.entities.Interfaces arg)
    {
        this.interfaces.add(arg);
    }

    void _removeInterfaces(com.finevm.enh.interfaces.entities.Interfaces arg)
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
    
    public java.util.Set<com.finevm.enh.interfaces.entities.Interfaces> getInterfaces()
    {
        this.interfaces.size();
        return java.util.Collections.unmodifiableSet(this.interfaces);
    }
    
    public Boolean hasInterface(com.finevm.enh.interfaces.entities.Interfaces arg)
    {
        return this.interfaces.contains(arg);
    }
    
    public void addInterface(com.finevm.enh.interfaces.entities.Interfaces arg)
    {
        if(arg == null || this.interfaces.contains(arg))
            return;
        this.interfaces.add(arg);
        arg.setModule(this);        
    }
    
    public void removeInterface(com.finevm.enh.interfaces.entities.Interfaces arg)
    {
        if(arg == null || !this.interfaces.contains(arg))
            return;
        this.interfaces.remove(arg);
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
    
    @Basic
    @Column(name="call_back_v", nullable=true)
    protected String callBack; 
    
    @Audited
    
    @Basic
    @Column(name="priority_n", nullable=true)
    protected Long priority; 
    
    @Audited
    @javax.validation.constraints.NotNull
    
    
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
    @AuditMappedBy(mappedBy="module")
    @OneToMany(fetch=FetchType.LAZY, mappedBy="module")
    protected java.util.Set<com.finevm.enh.interfaces.entities.Interfaces> interfaces = new java.util.HashSet<com.finevm.enh.interfaces.entities.Interfaces>();

	@Override
	public String toString() {
		return "Module [moduleId=" + moduleId + ", name=" + name + ", callBack=" + callBack + ", priority=" + priority
				+ ", lastUpdatedTime=" + lastUpdatedTime + "]";
	}

    

}     
