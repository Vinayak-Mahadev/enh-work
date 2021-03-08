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
@XmlType(name="interfaces")
@XmlRootElement(name="interfaces")
@Entity(name="com.enhancesys.entities.integration.Interfaces")
@Table(name="MS_INTERFACE")
@Access(AccessType.FIELD)
@EntityListeners(value={com.enhancesys.entities.integration.Module.ModuleListener.class})
public class Interfaces implements  java.io.Serializable
{
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
   public static class InterfacesListener
   {
     @PrePersist
     public void onPersist(com.enhancesys.entities.integration.InterfaceAttribute arg)
     {
       Interfaces obj = arg.getInterfaces();
       obj._addAttributes(arg);
     }
     
     @PreRemove
     public void onRemove(com.enhancesys.entities.integration.InterfaceAttribute arg)
     {
       Interfaces obj = arg.getInterfaces();
       obj._removeAttributes(arg);
     }
   
   }
   
   void _addAttributes(com.enhancesys.entities.integration.InterfaceAttribute arg)
   {
     this.attributes.add(arg);
   }
   
   void _removeAttributes(com.enhancesys.entities.integration.InterfaceAttribute arg)
   {
     this.attributes.remove(arg);
   }

   
   public void setInterfaceId(Long arg)
   {
     if(this.interfaceId == arg)
       return;
     this.interfaceId = arg;
   }
      
   @XmlElement(name="interfaceId", required = true)
   

   public Long getInterfaceId()
   {
     return this.interfaceId;
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
   
   public void setModule(com.enhancesys.entities.integration.Module arg)
   {
     if(this.module == arg)
       return;
     this.module = arg;
   }
      
   @XmlJavaTypeAdapter(value=Module.IDAdapter.class)
   
   @XmlElement(name="moduleId", required=true)
   

   public com.enhancesys.entities.integration.Module getModule()
   {
     return this.module;
   }
   
   public void setInterfaceType(Long arg)
   {
     if(this.interfaceType == arg)
       return;
     this.interfaceType = arg;
   }
      
   @XmlElement(name="interfaceType", required=true)
   

   public Long getInterfaceType()
   {
     return this.interfaceType;
   }
   
   public void setTransactionType(Long arg)
   {
     if(this.transactionType == arg)
       return;
     this.transactionType = arg;
   }
      
   @XmlElement(name="transactionType", required=true)
   

   public Long getTransactionType()
   {
     return this.transactionType;
   }
   
   public void setSequence(Long arg)
   {
     if(this.sequence == arg)
       return;
     this.sequence = arg;
   }
      
   @XmlElement(name="sequence", required=false)
   

   public Long getSequence()
   {
     return this.sequence;
   }
   
   public void setConverter(String arg)
   {
     if(this.converter == arg)
       return;
     this.converter = arg;
   }
      
   @XmlElement(name="converter", required=true)
   

   public String getConverter()
   {
     return this.converter;
   }
   
   public void setPublisher(String arg)
   {
     if(this.publisher == arg)
       return;
     this.publisher = arg;
   }
      
   @XmlElement(name="publisher", required=true)
   

   public String getPublisher()
   {
     return this.publisher;
   }
   
   public void setResponseProcessor(String arg)
   {
     if(this.responseProcessor == arg)
       return;
     this.responseProcessor = arg;
   }
      
   @XmlElement(name="responseProcessor", required=false)
   

   public String getResponseProcessor()
   {
     return this.responseProcessor;
   }

      
   @XmlTransient
   

   public Date getLastUpdatedTime()
   {
     return this.lastUpdatedTime;
   }
      
   public java.util.Set<com.enhancesys.entities.integration.InterfaceAttribute> getAttributes()
   {
     this.attributes.size();
     return java.util.Collections.unmodifiableSet(this.attributes);
   }


   public Boolean hasAttribute(com.enhancesys.entities.integration.InterfaceAttribute arg)
   {
     return this.attributes.contains(arg);
   }


   public void addAttribute(com.enhancesys.entities.integration.InterfaceAttribute arg)
   {
     if(arg == null || this.attributes.contains(arg))
       return;
     this.attributes.add(arg);
     arg.setInterfaces(this);
   }


   public void removeAttribute(com.enhancesys.entities.integration.InterfaceAttribute arg)
   {
     if(arg == null || !this.attributes.contains(arg))
       return;
     this.attributes.remove(arg);
     arg.setInterfaces(this);
   }

   @Audited
   @javax.validation.constraints.NotNull
   @Id
   @Column(name="interface_id_n")
   protected Long interfaceId;

   @Audited
   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="name_v" , nullable=false)
   protected String name;

   @javax.validation.constraints.NotNull
   @Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
   @ManyToOne(fetch=FetchType.EAGER)
   @JoinColumn(name="module_id_n", nullable=false)
   protected com.enhancesys.entities.integration.Module module;

   @Audited
   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="interface_type_n" , nullable=false)
   protected Long interfaceType;

   @Audited
   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="trans_type_n" , nullable=false)
   protected Long transactionType;

   @Audited
   @Basic
   @Column(name="seq_n" , nullable=true)
   protected Long sequence;

   @Audited
   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="converter_v" , nullable=false)
   protected String converter;

   @Audited
   @javax.validation.constraints.NotNull
   @Basic
   @Column(name="publisher_v" , nullable=false)
   protected String publisher;

   @Audited
   @Basic
   @Column(name="response_processor_v" , nullable=true)
   protected String responseProcessor;

   @Audited
   @javax.validation.constraints.NotNull
   @Version
   @Column(name="last_updated_time_dt" , nullable=false)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date lastUpdatedTime;

   @AuditMappedBy(mappedBy="interfaces")
   @OneToMany(fetch=FetchType.LAZY, mappedBy="interfaces")
   protected java.util.Set<com.enhancesys.entities.integration.InterfaceAttribute> attributes = new java.util.HashSet<com.enhancesys.entities.integration.InterfaceAttribute>();

}