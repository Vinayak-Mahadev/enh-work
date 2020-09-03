package com.finevm.devtool.mvn;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.maven.model.Model;
import org.apache.maven.model.Profile;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.json.JSONArray;
import org.json.JSONObject;

import com.finevm.devtool.common.DevToolConstants;


class DevToolMavenConfig 
{
	private final static Logger logger = Logger.getLogger(DevToolMavenConfig.class);
	private static	DevToolMavenConfig autoConf = null;
	private  final List<JSONObject> totalModules= new ArrayList<JSONObject>();
	private  final JSONObject autoMVNConf = new JSONObject();
	private JSONObject json = null;


	DevToolMavenConfig(JSONObject mavenProjectList) throws Exception 
	{
		json = getConf(mavenProjectList);
		logger.info(this.getClass().getName()+" obj created :::   "+objInfo());
	}

	private String objInfo() 
	{
		return "DevToolMavenConfig [config json = " + json + "]";
	}

	public JSONObject getJson() 
	{
		return json;
	}

	public static  DevToolMavenConfig getMvnConf(JSONObject _mavenProjectList)
	{
		try
		{
			autoConf = new DevToolMavenConfig(_mavenProjectList);
		}
		catch (Exception e) 
		{
			logger.error(e.getMessage()+"   "+e);
			e.printStackTrace();
		}
		return autoConf;
	}

	public final  JSONObject getConf(JSONObject mavenProjectList) throws Exception
	{
		JSONObject mvn = null;
		try 
		{
			for (String object : mavenProjectList.keySet()) {
				mvn = mavenProjectList.getJSONObject(object); 
				getMVNProjectDetail(mvn);
				autoMVNConf.put(mvn.getString(DevToolConstants._mvnId), totalModules);
			}
		} 
		catch (Exception e)
		{
			logger.error(e.getMessage()+"   "+e);
			e.printStackTrace();
		}
		finally 
		{
			mvn = null;
		}
		return autoMVNConf;
	}

	public final  JSONObject getMVNProjectDetail(JSONObject mvn) throws Exception 
	{
		JSONObject parentJson = null;
		JSONObject childJson1 = null;
		JSONObject childJson2 = null;
		JSONObject childJson3 = null;
		JSONArray parentJsonArray = null;
		JSONArray childJsonArray1 = null;
		JSONArray childJsonArray2 = null;
		String projectFileLoc = null;
		String packageType    = null;
		String javaHome       = null;

		try 
		{
			projectFileLoc = mvn.getString(DevToolConstants._mvnLoc);
			packageType    = mvn.getString(DevToolConstants._mvnPackage);

			if(mvn.has(DevToolConstants._java_home) && mvn.get(DevToolConstants._java_home) != null)
				javaHome       = mvn.getString(DevToolConstants._java_home);

			totalModules.clear();
			parentJson = (getModuleDetails(javaHome, projectFileLoc,packageType));
			parentJsonArray = (parentJson.get(DevToolConstants._moduleNames) != null) ? (parentJson.getJSONArray(DevToolConstants._moduleNames)) : (new JSONArray());

			for (Object childModule1 : parentJsonArray) 
			{
				childJson1 = (getModuleDetails(javaHome, parentJson.getString(DevToolConstants._projectLoc)+childModule1+DevToolConstants._fileseparator,null));
				childJsonArray1 = (childJson1.get(DevToolConstants._moduleNames) != null) ? (childJson1.getJSONArray(DevToolConstants._moduleNames)) : (new JSONArray());

				for (Object childModule2 : childJsonArray1) {
					childJson2 = (getModuleDetails(javaHome, childJson1.getString(DevToolConstants._projectLoc)+childModule2+DevToolConstants._fileseparator,null));
					childJsonArray2 = (childJson2.get(DevToolConstants._moduleNames) != null) ? (childJson2.getJSONArray(DevToolConstants._moduleNames)) : (new JSONArray());

					for (Object childModule3 : childJsonArray2) {
						childJson3 = (getModuleDetails(javaHome, childJson2.getString(DevToolConstants._projectLoc)+childModule3+DevToolConstants._fileseparator,null));
						childJson2.getJSONArray(DevToolConstants._moduleDetails).put(childJson3);
					}
					childJson1.getJSONArray(DevToolConstants._moduleDetails).put(childJson2);
				}
				parentJson.getJSONArray(DevToolConstants._moduleDetails).put(childJson1);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			logger.error(e.getMessage()+"   "+e);
		}
		finally 
		{
			childJson1 = null;
			childJson2 = null;
			childJson3 = null;
			parentJsonArray = null;
			childJsonArray1 = null;
			childJsonArray2 = null;

		}
		return parentJson;
	} 

	public final  JSONObject getModuleDetails(String javaHome, String mvnLoc,String mvnpackage) throws Exception {

		File file = null;
		MavenXpp3Reader reader = null;
		Model model = null;
		InputStream inputStream = null;
		List<Profile> profileList = null;
		String filePath = null;
		List<String> moduleNames = new ArrayList<String>();
		JSONObject jsonObj = new JSONObject();
		try 
		{
			filePath = mvnLoc+DevToolConstants._pomXml;
			reader = new MavenXpp3Reader();
			file = new File(filePath);
			inputStream = new FileInputStream(file);
			model = reader.read(inputStream);
			jsonObj.put(DevToolConstants._java_home, javaHome);
			jsonObj.put(DevToolConstants._projectAbsolutePath, filePath);
			jsonObj.put(DevToolConstants._artifactId, model.getArtifactId() != null ?model.getArtifactId():DevToolConstants._empty);
			jsonObj.put(DevToolConstants._groupId, model.getGroupId() != null ?model.getGroupId():DevToolConstants._empty);
			jsonObj.put(DevToolConstants._name, model.getName() != null ?model.getName():DevToolConstants._empty);

			jsonObj.put(DevToolConstants._packaging, model.getPackaging() != null ?model.getPackaging():DevToolConstants._empty);

			jsonObj.put(DevToolConstants._url, model.getUrl() != null ?model.getUrl():DevToolConstants._empty);
			jsonObj.put(DevToolConstants._modelVersion, model.getModelVersion() != null ?model.getModelVersion():DevToolConstants._empty);
			jsonObj.put(DevToolConstants._desc, model.getDescription() != null ?model.getDescription():DevToolConstants._empty);
			jsonObj.put(DevToolConstants._version, model.getVersion() != null ?model.getVersion():DevToolConstants._empty);

			if(jsonObj.getString(DevToolConstants._version).equalsIgnoreCase("${project.version}") || jsonObj.getString(DevToolConstants._version).equalsIgnoreCase(DevToolConstants._empty))
				if(model.getParent()!=null)
					jsonObj.put(DevToolConstants._version, model.getParent().getVersion());

			jsonObj.put(DevToolConstants._projectDirWithMvn, model.getProjectDirectory() != null ?model.getProjectDirectory().getAbsolutePath():DevToolConstants._empty);
			jsonObj.put(DevToolConstants._buildFinalName, 
					(model.getBuild() != null)
					? (model.getBuild().getFinalName()!=null ? model.getBuild().getFinalName() :DevToolConstants._empty)
							: DevToolConstants._empty);

			jsonObj.put(DevToolConstants._buildFinalNameFlag, 
					(model.getBuild() != null)
					? (model.getBuild().getFinalName()!=null ? model.getBuild().getFinalName() : (jsonObj.get(DevToolConstants._artifactId)+"-"+jsonObj.get(DevToolConstants._version)))
							:  (jsonObj.get(DevToolConstants._artifactId)+"-"+jsonObj.get(DevToolConstants._version)));


			jsonObj.put(DevToolConstants._moduleDetails, new JSONArray());
			jsonObj.put(DevToolConstants._projectLoc, mvnLoc);



			profileList = model.getProfiles() !=null ? model.getProfiles() : new ArrayList<Profile>();

			if(model.getModules() != null) {
				for (String modulename : model.getModules()) {
					if(!modulename.endsWith(".xml"))
						moduleNames.add(modulename);
				}
			}

			if(profileList != null)
				for (Profile profile : profileList) 
				{
					for (String modulename : profile.getModules()) {
						if(!modulename.endsWith(".xml"))
							moduleNames.add(modulename);
					}
				}

			moduleNames = new ArrayList<>( new HashSet<String>(moduleNames));
			Collections.sort(moduleNames);
			jsonObj.put(DevToolConstants._moduleNames, moduleNames);

			jsonObj.put(DevToolConstants._projectTargetLoc, jsonObj.get(DevToolConstants._projectLoc)+DevToolConstants._target+DevToolConstants._fileseparator);
			jsonObj.put(DevToolConstants._AbsPathBuildPackage, jsonObj.get(DevToolConstants._projectTargetLoc)+""+jsonObj.get(DevToolConstants._buildFinalName)+"."+jsonObj.get(DevToolConstants._packaging));
			jsonObj.put(DevToolConstants._AbsPathBuildPackageFlag,  jsonObj.get(DevToolConstants._projectTargetLoc)+""+jsonObj.get(DevToolConstants._buildFinalNameFlag)+"."+jsonObj.get(DevToolConstants._packaging));

			if(mvnpackage!=null) 
			{
				if (!mvnpackage.equalsIgnoreCase("pom")) {

					jsonObj.put(DevToolConstants._AbsPathBuildPackage, jsonObj.get(DevToolConstants._projectTargetLoc)+""+jsonObj.get(DevToolConstants._buildFinalName)+"."+mvnpackage);
					jsonObj.put(DevToolConstants._AbsPathBuildPackageFlag,  jsonObj.get(DevToolConstants._projectTargetLoc)+""+jsonObj.get(DevToolConstants._buildFinalNameFlag)+"."+mvnpackage);
				} 
				else 
				{
					jsonObj.put(DevToolConstants._AbsPathBuildPackage, jsonObj.get(DevToolConstants._projectTargetLoc)+"");
					jsonObj.put(DevToolConstants._AbsPathBuildPackageFlag,  jsonObj.get(DevToolConstants._projectTargetLoc)+"");
				}
			}

			totalModules.add(jsonObj);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			logger.error(e.getMessage()+"   "+e);
			//System.exit(0);
		}
		finally 
		{
			if(inputStream != null)
				inputStream.close();
			file       = null;
			model      = null;
			reader     = null;
			filePath   = null;
			inputStream= null;
			moduleNames= null;
			profileList= null;
			moduleNames= null;

		}
		return jsonObj;
	}

}
