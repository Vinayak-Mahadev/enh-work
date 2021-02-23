package com.enhancesys.integration.services.dataengine;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.enhancesys.entities.integration.Interfaces;
import com.enhancesys.entities.integration.Module;

@ComponentScan("com.enhancesys.integration.services.dataengine")
@Configuration
@EnableAutoConfiguration
@RestController
public class DataEngineBeans
{
	@Autowired
	public SessionFactory sessionFactory;

	@GetMapping("/")
	public String hello() 
	{
		return "Hello world";
	}


	@GetMapping("/modules")
	public List<Module> getModules() 
	{
		EntityManager entityManager = sessionFactory.createEntityManager();
		List<Module> modules =(List<Module>) entityManager.createQuery("select m from interface.ms_module m order by 1", Module.class).getResultList();
		if(modules != null)
			return modules;
		return null;
	}
	
	@GetMapping("/interfaces")
	public List<Interfaces> getInterfaces() 
	{
		EntityManager entityManager = sessionFactory.createEntityManager();
		List<Interfaces> modules =(List<Interfaces>) entityManager.createQuery("select inter from interface."+Interfaces.class.getCanonicalName()+" inter order by 1", Interfaces.class).getResultList();
		if(modules != null)
			return modules;
		return null;
	}
	
	@GetMapping("/module/{id}")
	public Module getModule(@PathVariable(value = "id") Long id) 
	{
		EntityManager entityManager = sessionFactory.createEntityManager();
		Module module = entityManager.find(Module.class, id);
		if(module != null)
			return module;
		return null;
	}
	@GetMapping("/interface/{id}")
	public Interfaces hello(@PathVariable(value = "id") Long id) 
	{
		EntityManager entityManager = sessionFactory.createEntityManager();
		Interfaces inter = entityManager.find(Interfaces.class, id);
		if(inter != null)
			return inter;
		return null;
	}

}
