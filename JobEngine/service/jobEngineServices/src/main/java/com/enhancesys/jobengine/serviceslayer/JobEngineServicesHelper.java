package com.enhancesys.jobengine.serviceslayer;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.enhancesys.jobengine.beans.Module;
import com.enhancesys.jobengine.job.services.JobServcies;
import com.enhancesys.jobengine.repo.JobEngineJDBCRepository;
import com.enhancesys.jobengine.repo.JobEngineMongoRepository;
import com.enhancesys.jobengine.repo.JobEngineServiceRepository;

/**
 * <b>Purpose:</b><br>
 * 		JobEngine invoker implementation helper class<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2020<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        10-05-2010		   Vinayak Mahadev (vinay.nagaraj@enhancesys.com)
 * 	-- Base Release 
 * </pre>
 * 
 * <br>
 */

public class JobEngineServicesHelper 
{
	private static Logger log = Logger.getLogger(JobEngineMongoRepository.class);

	private final JobEngineServiceRepository repository = new JobEngineJDBCRepository();

	public JSONObject processModule(long moduleId) 
	{
		Module module = null;
		JSONObject jobParameter = null;
		try 
		{
			module = repository.getModule(moduleId);
			jobParameter = repository.getJobParameter(moduleId);

			log.info("Module :: " + module);
			log.info("JobParameter :: " + jobParameter);
			
			JobServcies.proceeRequest(jobParameter);

		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
		}
		return null;
	}
}
