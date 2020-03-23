package com.finevm.enh.interfaces.entities;

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


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="interfaceAttribute")
@XmlRootElement(name="interfaceAttribute")
@Entity(name="com.finevm.enh.interfaces.entities.InterfaceAttribute")
@Table(name="ms_interface_attr", schema = "INTERFACE")
@Access(AccessType.FIELD)
@EntityListeners(value={com.finevm.enh.interfaces.entities.Interfaces.AttributesListener.class})
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
    public com.finevm.enh.interfaces.entities.Interfaces getInterfaces()
    {
        return this.interfaces;
    }
    
    public void setInterfaces(com.finevm.enh.interfaces.entities.Interfaces arg)
    {
        if(this.interfaces == arg)
            return;
            
        com.finevm.enh.interfaces.entities.Interfaces oldInterfaces = this.interfaces;
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
    
    @Audited
    @javax.validation.constraints.NotNull
    
    @Id
    @Column(name="attribute_id_n")
    protected Long attributeId; 
    
    @javax.validation.constraints.NotNull
    @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="interface_id_n", nullable=false)
    protected com.finevm.enh.interfaces.entities.Interfaces interfaces; 
    
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
    
    
    @Column(name="last_updated_time_dt", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedTime;

	@Override
	public String toString() {
		return "InterfaceAttribute [attributeId=" + attributeId + ", interfacesId=" + interfaces.getInterfaceId() + ", name=" + name
				+ ", value=" + value + ", lastUpdatedTime=" + lastUpdatedTime + "]";
	} 
    
	

}     
