package com.finevm.devtool.swingui.util;

import org.json.JSONObject;

import com.finevm.devtool.common.DevToolConstants;
import com.finevm.devtool.mvn.DevToolAutoMaven;

public abstract class DevToolUtils 
{
    public static final JSONObject  _serverList               = DevToolConstants._serverList !=null ? DevToolConstants._serverList : new JSONObject();
	public static final JSONObject  _mvnConf                  = DevToolConstants._mvnConf !=null ? DevToolConstants._mvnConf : new JSONObject();                
	public static final JSONObject  _mvnCmdsConf              = DevToolConstants._mvnCmdsConf !=null ? DevToolConstants._mvnCmdsConf : new JSONObject();
	public static final JSONObject  _buttonNames              = DevToolConstants._buttonNames !=null ? DevToolConstants._buttonNames : new JSONObject();            
	public static final String      _autoMvnBatFileOutputLoc  = DevToolConstants._autoMvnBatFileOutputLoc;
	public static final String      _autoMvnShFileOutputLoc   = DevToolConstants._autoMvnShFileOutputLoc; 
	public static final String      _swingTitle               = DevToolConstants._swingTitle;             
	public static final String      _puttyPath                = DevToolConstants._puttyPath;    
	public static final String      _plinkPath                = DevToolConstants._plinkPath;    
	public static final String      _winscpPath               = DevToolConstants._winscpPath;             
	public static final String      _winscpConType            = DevToolConstants._winscpConType;      
	

	public static final String      _winscpBtn                = _buttonNames.isNull("launchWinscp")      ? "WINSCP"   : _buttonNames.getString("launchWinscp")     ;
	public static final String      _serverProcessBtn         = _buttonNames.isNull("serverProcess")     ? "PROCESS"  : _buttonNames.getString("serverProcess")    ;
	public static final String      _puttyBtn                 = _buttonNames.isNull("launchPutty")       ? "PUTTY"    : _buttonNames.getString("launchPutty")      ;
	public static final String      _mvnBtn                   = _buttonNames.isNull("launchAutoMvnconf") ? "MVN"      : _buttonNames.getString("launchAutoMvnconf");
	public static final String      _uploadBtn                = _buttonNames.isNull("launchssh")         ? "UPLOAD"   : _buttonNames.getString("launchssh")        ;
	public static final String      _launchGit                = _buttonNames.isNull("launchGit")         ? "GIT"      : _buttonNames.getString("launchGit")        ;
	public static final String      _nextColor                = _buttonNames.isNull("nextColor")         ? "THEME"    : _buttonNames.getString("nextColor")        ;
	public static final DevToolAutoMaven      _AutoMaven      = new DevToolAutoMaven(_mvnConf);
	

}
