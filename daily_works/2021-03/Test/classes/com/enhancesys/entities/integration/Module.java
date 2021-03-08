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
@XmlType(name="module")
@XmlRootElement(name="module")
@Entity(name="com.enhancesys.entities.integration.Module")
@Table(name="MS_MODULE" , schema="interface")
@Access(AccessType.FIELD)
public class Module implements  java.io.Serializable
{
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
   public static class ModuleListener
   {
     @PrePersist
     public void onPersist(com.enhancesys.entities.integration.Interfaces arg)
     {
       Module obj = arg.getModule();
       obj._addInterfaces(arg);
     }
     
     @PreRemove
     public void onRemove(com.enhancesys.entities.integration.Interfaces arg)
     {
       Module obj = arg.getModule();
       obj._removeInterfaces(arg);
     }
   
   }
   
   void _addInterfaces(com.enhancesys.entities.integration.Interfaces arg)
   {
     this.interfaces.add(arg);
   }
   
   void _removeInterfaces(com.enhancesys.entities.integration.Interfaces arg)
   {
     this.interfaces.remove(arg);
   }

   
   public void setModuleId(Long arg)
   {
     if(this.moduleId == arg)
       return;
     this.moduleId = arg;
   }
      
   @XmlElement(name="moduleId", required=true)
   

   public Long getModuleId()
   {
     return this.moduleId;
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
   
   public void setCallBack(String arg)
   {
     if(this.callBack == arg)
       return;
     this.callBack = arg;
   }
      
   @XmlElement(name="callBack")
   

   public String getCallBack()
   {
     return this.callBack;
   }
   
   public void setPriority(Long arg)
   {
     if(this.priority == arg)
       return;
     this.priority = arg;
   }
      
   @XmlElement(name="priority")
   

   public Long getPriority()
   {
     return this.priority;
   }

      
   @XmlTransient
   

   public Date getLastUpdatedTime()
   {
     return this.lastUpdatedTime;
   }
      
   public java.util.Set<com.enhancesys.entities.integration.Interfaces> getInterfaces()
   {
     this.interfaces.size();
     return java.util.Collections.unmodifiableSet(this.interfaces);
   }


   public Boolean hasInterface(com.enhancesys.entities.integration.Interfaces arg)
   {
     return this.interfaces.contains(arg);
   }


   public void addInterface(com.enhancesys.entities.integration.Interfaces arg)
   {
     if(arg == null || this.interfaces.contains(arg))
       return;
     this.interfaces.add(arg);
     arg.setModule(this);
   }


   public void removeInterface(com.enhancesys.entities.integration.Interfaces arg)
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
   @Column(name="name_v" , nullable=false)
   protected String name;

   @Audited
   @Basic
   @Column(name="call_back_v" , nullable=true)
   protected String callBack;

   @Audited
   @Basic
   @Column(name="priority_n" , nullable=true)
   protected Long priority;

   @Audited
   @javax.validation.constraints.NotNull
   @Version
   @Column(name="last_updated_time_dt" , nullable=false)
   @Temporal(TemporalType.TIMESTAMP)
   protected Date lastUpdatedTime;

   @AuditMappedBy(mappedBy="module")
   @OneToMany(fetch=FetchType.LAZY, mappedBy="module")
   protected java.util.Set<com.enhancesys.entities.integration.Interfaces> interfaces = new java.util.HashSet<com.enhancesys.entities.integration.Interfaces>();

}