package com.finevm.devtool.common;

import java.util.Properties;

import org.json.JSONObject;


public final class DevToolConstants {
	public static Properties load(){
		_PROPERTIES               = DevToolPropertyLoader.getProperties();
		return _PROPERTIES;
	}
	public static  Properties  _PROPERTIES;//               = PropertyLoader.getProperties();
	public static final JSONObject  _serverList               = DevToolPropertyLoader.getProperty("SERVER_LIST") !=null ? new JSONObject(DevToolPropertyLoader.getProperty("SERVER_LIST")) :  new JSONObject();
	public static final JSONObject  _mvnConf                  = DevToolPropertyLoader.getProperty("AUTO_MAVEN_CONF") !=null ? new JSONObject(DevToolPropertyLoader.getProperty("AUTO_MAVEN_CONF")) :  new JSONObject();
	public static final JSONObject  _mvnCmdsConf              = DevToolPropertyLoader.getProperty("AUTO_MAVEN_CMDS") !=null ? new JSONObject(DevToolPropertyLoader.getProperty("AUTO_MAVEN_CMDS")) :  new JSONObject("{\"1\":\"mvn clean\",\"2\":\"mvn clean install\",\"3\":\"mvn clean package\"}");
	public static final JSONObject  _buttonNames              = DevToolPropertyLoader.getProperty("BUTTON_NAMES_SWING") !=null ? (new JSONObject(DevToolPropertyLoader.getProperty("BUTTON_NAMES_SWING")).getJSONObject("BUTTON_NAMES")) : new JSONObject("{\"BUTTON_NAMES\":{\"launchPutty\":\"PUTTY\",\"launchssh\":\"UPLOAD\",\"launchGit\":\"GIT\",\"nextColor\":\"THEME\",\"launchAutoMvnconf\":\"MVN\",\"launchWinscp\":\"WINSCP\"}}");
	public static final String      _autoMvnBatFileOutputLoc  = DevToolPropertyLoader.getProperty("AUTO_MAVEN_BAT_LOC");
	public static final String      _autoMvnShFileOutputLoc   = DevToolPropertyLoader.getProperty("AUTO_MAVEN_SH_LOC");
	public static final String      _swingTitle               = DevToolPropertyLoader.getProperty("TITLE")!=null ? DevToolPropertyLoader.getProperty("TITLE") :"FineVM";
	public static final String      _puttyPath                = DevToolPropertyLoader.getProperty("PUTTY_PATH").trim();
	public static final String      _plinkPath                = DevToolPropertyLoader.getProperty("PLINK_PATH").trim();
	public static final String      _winscpPath               = DevToolPropertyLoader.getProperty("WINSCP_PATH");
	public static final String      _winscpConType            = DevToolPropertyLoader.getProperty("WINSCP_CON_TYPE") != null ? DevToolPropertyLoader.getProperty("WINSCP_CON_TYPE").trim():"sftp";
	public static final String      _fineVmDir                = DevToolPropertyLoader.getFineVmDir();

	public static String _batFileautoMavenDir = DevToolPropertyLoader.getBatFileautoMavenDir();
	public static String _shFileautoMavenDir  = DevToolPropertyLoader.getShFileautoMavenDir();

	public static final String _uploadFileDirLoc = "uploadFileDirLoc";
	public static final String _java_home = "JAVA_HOME";
	public static final String _tomee = "tomee";
	public static final String _fileName = "file-name";
	public static final String _fileLoc = "file-loc";
	public static final String _jarLoc = "jar_loc";
	public static final String _warLoc = "war_loc";
	public static final String _earLoc = "ear_loc";
	public static final String _id = "id";
	public static final String _module = "module";
	public static final String _mvnPackage = "mvnpackage";
	public static final String _mvnId = "mvnId";
	public static final String _mvnLoc = "mvnLoc";
	public static final String _pomXml = "pom.xml";
	public static final String _pom = "pom";
	public static final String _war = "war";
	public static final String _jar = "jar";
	public static final String _ear = "ear"; 
	public static final String _empty = "-"; 
	public static final String _projectLoc = "projectLoc"; 
	public static final String _properties = "properties"; 
	public static final String _projectDirWithMvn = "projectDir"; 
	public static final String _groupId = "groupId"; 
	public static final String _artifactId = "artifactId"; 
	public static final String _desc = "desc"; 
	public static final String _version = "version"; 
	public static final String _value = "value"; 
	public static final String _scope = "scope"; 
	public static final String _type = "type"; 
	public static final String _exclusions = "exclusions"; 
	public static final String _packaging = "packaging"; 
	public static final String _name = "name"; 
	public static final String _url = "url"; 
	public static final String _modelVersion = "model-version"; 
	public static final String _buildFinalName = "buildFinalName"; 
	public static final String _buildFinalNameFlag = "buildFinalName-flag"; 
	public static final String _moduleNames = "moduleNames"; 
	public static final String _moduleDetails = "moduleDetails"; 
	public static final String _dependencies = "dependencies"; 
	public static final String _projectAbsolutePath = "projectAbsolutePath"; 
	public static final String _projectTargetLoc = "buildLoc-target"; 
	public static final String _AbsPathBuildPackage = "absPathBuildPackage"; 
	public static final String _AbsPathBuildPackageFlag = "absPathBuildPackage-flag"; 
	public static final String _fileseparator = "/";
	public static final String _target = "target";
	public static final String _hostName = "hostName";
	public static final String _targetFiles = "targetFiles";
	public static final String _targetLoc = "targetLoc";
}
