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
@XmlType(name="interfaceAttribute")
@XmlRootElement(name="interfaceAttribute")
@Entity(name="com.enhancesys.integration.snoc.entities.InterfaceAttribute")
@Table(name="ms_interface_attr", schema = "interface")
@Access(AccessType.FIELD)
@EntityListeners(value={com.enhancesys.integration.snoc.entities.Interfaces.AttributesListener.class})
public class InterfaceAttribute implements java.io.Serializable 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlTransient
    public static class IDAdapter extends XmlAdapter<Long,InterfaceAttribute>
    {
        @Override
        public Long marshal(InterfaceAttribute interfaceAttribute) throws Exception 
        {
            return interfaceAttribute.getAttributeId();
        }

        @Override
        public InterfaceAttribute unmarshal(Long arg) throws Exception 
        {
            InterfaceAttribute interfaceAttribute = new InterfaceAttribute();
            interfaceAttribute.setAttributeId(arg);
            return interfaceAttribute;
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
    
    @XmlJavaTypeAdapter(value=Interfaces.IDAdapter.class)
    @XmlElement(name="interfaceId", required=true)
    public com.enhancesys.integration.snoc.entities.Interfaces getInterfaces()
    {
        return this.interfaces;
    }
    
    public void setInterfaces(com.enhancesys.integration.snoc.entities.Interfaces arg)
    {
        if(this.interfaces == arg)
            return;
            
        com.enhancesys.integration.snoc.entities.Interfaces oldInterfaces = this.interfaces;
        this.interfaces = null;
        if(oldInterfaces != null)
            oldInterfaces.removeAttribute(this);
        
        this.interfaces = arg;

        if(this.interfaces != null)
            this.interfaces.addAttribute(this);
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
    
    @javax.validation.constraints.NotNull
    
    @Id
    @Column(name="attribute_id_n")
    protected Long attributeId; 
    
    @javax.validation.constraints.NotNull
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="interface_id_n", nullable=false)
    protected com.enhancesys.integration.snoc.entities.Interfaces interfaces; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="name_v", nullable=false)
    protected String name; 
    
    @javax.validation.constraints.NotNull
    
    @Basic
    @Column(name="value_v", nullable=false)
    protected String value; 
    
    @javax.validation.constraints.NotNull
    
    @Version
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime; 
    
}     
