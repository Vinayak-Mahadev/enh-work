package com.enhancesys.integration.snoc.generation;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.enhancesys.integration.snoc.generation.conf.EntityGeneration;

/**
 * <b>Purpose:</b><br>
 * 		Class to implement the generic process entity generation..<br>
 * <br>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2021<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 *  Sl No   Modified Date        Author</b>
 *  ==============================================
 *  1        10-01-2021          Vinayak Mahadev
 *    	-- Base Release
 * </pre>
 * 
 * <br>
 */
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
