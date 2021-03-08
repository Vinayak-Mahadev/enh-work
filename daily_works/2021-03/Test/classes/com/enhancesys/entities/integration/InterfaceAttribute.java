package com.enhancesys.entities.integration;


import javax.persistence.*;
import java.util.Date;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import org.hibernate.envers.*;
import org.hibernate.validator.constraints.*;
import org.hibernate.search.annotations.*;



@SuppressWarnings("unused")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="interfaceAttribute")
@XmlRootElement(name="interfaceAttribute")
@Entity(name="com.enhancesys.entities.integration.InterfaceAttribute")
@Table(name="ms_interface_attr")
@Access(AccessType.FIELD)
@EntityListeners(value={com.enhancesys.entities.integration.Interfaces.InterfacesListener.class})
public class InterfaceAttribute implements  java.io.Serializable
{
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
     if(this.attributeId == arg)
       return;
     this.attributeId = arg;
   }
      
   @XmlElement(name="attributeId", required = true)
   

   public Long getAttributeId()
   {
     return this.attributeId;
   }
   
   public void setInterfaces(com.enhancesys.entities.integration.Interfaces arg)
   {
     if(this.interfaces == arg)
       return;
     this.interfaces = arg;
   }
      
   @XmlJavaTypeAdapter(value=Interfaces.IDAdapter.class)
   
   @XmlElement(name="interfaceId", required=true)
   

   public com.enhancesys.entities.integration.Interfaces getInterfaces()
   {
     return this.interfaces;
   }
   
   public void setName(String arg)
   {
     if(this.name == arg)
       return;
     this.name = arg;
   }
      
   @XmlElement(name="name", required=true)
   

   public String getName()
   {
     return this.name;
   }
   
   public void setValue(String arg)
   {
     if(this.value == arg)
       return;
     this.value = arg;
   }
      
   @XmlElement(name="value", required=true)
   

   public String getValue()
   {
     return this.value;
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
   protected com.enhancesys.entities.integration.Interfaces interfaces;

   @Audited
   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="name_v" , nullable=false)
   protected String name;

   @Audited
   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="value_v" , nullable=false)
   protected String value;

   @Audited
   @javax.validation.constraints.NotNull
   @Version
   @Column(name="last_updated_time_dt" , nullable=false)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date lastUpdatedTime;

}