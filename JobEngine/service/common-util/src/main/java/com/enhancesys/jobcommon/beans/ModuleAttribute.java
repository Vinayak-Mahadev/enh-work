package com.enhancesys.jobcommon.beans;

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

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import com.enhancesys.jobcommon.Constants;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="moduleAttribute")
@XmlRootElement(name="moduleAttribute")
@Entity(name="com.enhancesys.jobcommon.beans.ModuleAttribute")
@Table(name="ms_module_attr", schema = Constants._jobengineSchema)
@Access(AccessType.FIELD)
@EntityListeners(value={com.enhancesys.jobcommon.beans.Module.AttributesListener.class})
public class ModuleAttribute implements java.io.Serializable 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,ModuleAttribute>
    {
        @Override
        public Long marshal(ModuleAttribute moduleAttribute) throws Exception 
        {
            return moduleAttribute.getAttributeId();
        }

        @Override
        public ModuleAttribute unmarshal(Long arg) throws Exception 
        {
            ModuleAttribute moduleAttribute = new ModuleAttribute();
            moduleAttribute.setAttributeId(arg);
            return moduleAttribute;
        }
    
    }

    public void setAttributeId(Long arg)
    {
        this.attributeId = arg;
    }
    
    @XmlElement(name="attributeId", required = true)
    public Long getAttributeId()
    {
        return this.attributeId;
    }
    
    @XmlJavaTypeAdapter(value=Module.IDAdapter.class)
    @XmlElement(name="moduleId", required=true)
    public com.enhancesys.jobcommon.beans.Module getModule()
    {
        return this.module;
    }
    
    public void setModule(com.enhancesys.jobcommon.beans.Module arg)
    {
        if(this.module == arg)
            return;
            
        com.enhancesys.jobcommon.beans.Module oldModule = this.module;
        this.module = null;
        if(oldModule != null)
            oldModule.removeAttribute(this);
        
        this.module = arg;

        if(this.module != null)
            this.module.addAttribute(this);
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
    
    @XmlElement(name="value", required=true)
    public String getValue()
    {
        return this.value;
    }
    
    public void setValue(String arg)
    {
        this.value = arg;
    }
    
    @XmlTransient
    public Date getLastUpdatedTime()
    {
        return this.lastUpdatedTime;
    }
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Id
    @Column(name="attribute_id_n")
    protected Long attributeId; 
    
    @javax.validation.constraints.NotNull
    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="module_id_n", nullable=false)
    protected com.enhancesys.jobcommon.beans.Module module; 
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="name_v", nullable=false)
    protected String name; 
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="value_v", nullable=false)
    protected String value; 
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
}     
