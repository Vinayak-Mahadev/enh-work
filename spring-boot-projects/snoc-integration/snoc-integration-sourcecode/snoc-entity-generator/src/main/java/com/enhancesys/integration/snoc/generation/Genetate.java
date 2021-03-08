package com.enhancesys.integration.snoc.generation;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.enhancesys.integration.snoc.generation.conf.EntityGeneration;

@Mojo(name="Genetate", defaultPhase = LifecyclePhase.COMPILE)
public class Genetate extends AbstractMojo 
{
	@Parameter(required = true)
	private String ormFilePath;

	@Parameter(required = true)
	private String outputPath;

	@Parameter(required = true)
	private String goal;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException 
	{
		try 
		{
			EntityGeneration.generateEntity(ormFilePath, outputPath, goal);
		} 
		catch (Exception e) 
		{
			throw new MojoExecutionException("Failed at generating entities");
		}

	}
}
