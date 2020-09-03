package com.finevm.devtool.common;

interface DevToolEnvironment {

	public final static	String _userHome     = System.getProperty("user.home");
	public final static String _userName     = System.getProperty("user.name");


	public final static String _PROPS_DATA     = "\r\n" + 
			"# Vinayak Mahadev  <vinay.nagaraj@enhancesys.com>\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"# SERVER CONNECTION\r\n" + 
			"SERVER_LIST         = {\"_143_appuser\":{\"id\":\"143\",\"username\":\"appuser\",\"password\":\"app@123\",\"hostName\":\"192.168.2.143\",\"portNum\":\"22\",\"timeOut\":10000,\"passPath\":\"\",\"tomee\":{\"ear_loc\":\"/home/appuser/snoc/apps/apps/\",\"war_loc\":\"/home/appuser/snoc/apps/webapps/\",\"jar_loc\":\"\"}},\"_120_appuser\":{\"id\":\"120\",\"username\":\"appuser\",\"password\":\"app@123\",\"hostName\":\"192.168.2.120\",\"portNum\":\"22\",\"passPath\":\"\",\"timeOut\":10000},\"_144_appuser\":{\"id\":\"144\",\"username\":\"appuser\",\"password\":\"was@321\",\"hostName\":\"192.168.2.144\",\"portNum\":\"22\",\"passPath\":\"\",\"timeOut\":10000},\"_76_appuser\":{\"id\":\"76\",\"username\":\"appuser\",\"password\":\"app@123\",\"hostName\":\"192.168.2.76\",\"portNum\":\"22\",\"passPath\":\"\",\"timeOut\":10000},\"_251_appuser\":{\"id\":\"251\",\"username\":\"appuser\",\"password\":\"app@123\",\"hostName\":\"192.168.2.251\",\"portNum\":\"22\",\"passPath\":\"\",\"timeOut\":10000,\"tomee\":{\"ear_loc\":\"/home/appuser/snoc/apps/apps/\",\"war_loc\":\"/home/appuser/snoc/apps/webapps/\",\"jar_loc\":\"/home/appuser/vinay/test/\"}},\"_251_auth\":{\"id\":\"251\",\"username\":\"auth\",\"password\":\"app@123\",\"hostName\":\"192.168.2.251\",\"portNum\":\"22\",\"timeOut\":10000,\"passPath\":\"C:/Users/vinayak/FineVM/251.ppk\",\"tomee\":{\"ear_loc\":\"/home/appuser/snoc/apps/apps/\",\"war_loc\":\"/home/appuser/snoc/apps/webapps/\",\"jar_loc\":\"/home/appuser/vinay/test/\"}},\"LOC-UI-D1\":{\"id\":\"UI-D1\",\"hostName\":\"localhost\",\"tomee\":{\"loc\":\"E:/interface/frontend-UI/UI-SERVER/D1/tomee-home/apache-tomee-plus-1.7.1/apps/\",\"ear_loc\":\"E:/interface/frontend-UI/UI-SERVER/D1/tomee-home/apache-tomee-plus-1.7.1/apps/\",\"war_loc\":\"E:/interface/frontend-UI/UI-SERVER/D1/tomee-home/apache-tomee-plus-1.7.1/webapps/\",\"jar_loc\":\"E:/interface/frontend-UI/UI-SERVER/D1/tomee-home/apache-tomee-plus-1.7.1/lib/\"}},\"LOC-UI-D2\":{\"id\":\"UI-D2\",\"hostName\":\"localhost\",\"tomee\":{\"loc\":\"E:/interface/frontend-UI/UI-SERVER/D2/tomee-home/apache-tomee-plus-1.7.1/apps/\",\"ear_loc\":\"E:/interface/frontend-UI/UI-SERVER/D2/tomee-home/apache-tomee-plus-1.7.1/apps/\",\"war_loc\":\"E:/interface/frontend-UI/UI-SERVER/D2/tomee-home/apache-tomee-plus-1.7.1/webapps/\",\"jar_loc\":\"E:/interface/frontend-UI/UI-SERVER/D2/tomee-home/apache-tomee-plus-1.7.1/lib/\"}}}\r\n" + 
			"\r\n" + 
			"# AUTO_MAVEN CONFIG\r\n" + 
			"#AUTO_MAVEN_CONF     = { 	\"snd-INDO-D-sourcecode\": { 		\"mvnId\": \"snd-INDO-D-sourcecode\", 		\"mvnLoc\": \"E:/interface/backend/indosat/d-indo/snd-integration/sourcecode/\", 		\"mvnpackage\": \"pom\", 		\"git\": { 			\"username\": \"vinay.nagaraj@enhancesys.com\", 			\"password\": \"treet\", 			\"url\": \"appuser@192.168.2.227:/home/appuser/snoc/snd-integration\", 			\"localDir\": \"E:/interface/backend/indosat/d-indo/snd-integration/\" 		} 	}, 	\"snd-INDO-D-buildear\": { 		\"mvnId\": \"snd-INDO-D-buildear\", 		\"mvnLoc\": \"E:/interface/backend/indosat/d-indo/snd-integration/buildear/\", 		\"mvnpackage\": \"pom\", 		\"git\": { 			\"username\": \"vinay.nagaraj@enhancesys.com\", 			\"password\": \"treet\", 			\"url\": \"appuser@192.168.2.227:/home/appuser/snoc/snd-integration\", 			\"localDir\": \"E:/interface/backend/indosat/d-indo/snd-integration/\" 		} 	}, 	\"snd-INDO-d4s3-sourcecode\": { 		\"mvnId\": \"snd-INDO-d4s3-sourcecode\", 		\"mvnLoc\": \"E:/interface/backend/indosat/d4s3-indo/snd-integration/sourcecode/\", 		\"mvnpackage\": \"pom\", 		\"git\": { 			\"username\": \"vinay.nagaraj@enhancesys.com\", 			\"password\": \"treet\", 			\"url\": \"appuser@192.168.2.227:/home/appuser/snoc/snd-integration\", 			\"localDir\": \"E:/interface/backend/indosat/d4s3-indo/snd-integration/\" 		} 	}, 	\"snd-INDO-d4s3-buildear\": { 		\"mvnId\": \"snd-INDO-d4s3-buildear\", 		\"mvnLoc\": \"E:/interface/backend/indosat/d4s3-indo/snd-integration/buildear/\", 		\"mvnpackage\": \"ear\", 		\"git\": { 			\"username\": \"vinay.nagaraj@enhancesys.com\", 			\"password\": \"treet\", 			\"url\": \"appuser@192.168.2.227:/home/appuser/snoc/snd-integration\", 			\"localDir\": \"E:/interface/backend/indosat/d4s3-indo/snd-integration/\" 		} 	}, 	\"snd-KS-D-sourcecode\": { 		\"mvnId\": \"snd-KS-D-sourcecode\", 		\"mvnLoc\": \"E:/interface/backend/kyivstar/d-ks/snd-integration/sourcecode/\", 		\"mvnpackage\": \"pom\", 		\"git\": { 			\"username\": \"vinay.nagaraj@enhancesys.com\", 			\"password\": \"treet\", 			\"url\": \"appuser@192.168.2.227:/home/appuser/snoc/snd-integration\", 			\"localDir\": \"E:/interface/backend/kyivstar/d-ks/snd-integration/\" 		} 	}, 	\"snd-KS-D-buildear\": { 		\"mvnId\": \"snd-KS-D-buildear\", 		\"mvnLoc\": \"E:/interface/backend/kyivstar/d-ks/snd-integration/buildear/\", 		\"mvnpackage\": \"ear\", 		\"git\": { 			\"username\": \"vinay.nagaraj@enhancesys.com\", 			\"password\": \"treet\", 			\"url\": \"appuser@192.168.2.227:/home/appuser/snoc/snd-integration\", 			\"localDir\": \"E:/interface/backend/kyivstar/d-ks/snd-integration/\" 		} 	}, 	\"snd-KS-C-sourcecode\": { 		\"mvnId\": \"snd-KS-C-sourcecode\", 		\"mvnLoc\": \"E:/interface/backend/kyivstar/c-ks/snd-integration/sourcecode/\", 		\"mvnpackage\": \"pom\", 		\"git\": { 			\"username\": \"vinay.nagaraj@enhancesys.com\", 			\"password\": \"treet\", 			\"url\": \"appuser@192.168.2.227:/home/appuser/snoc/snd-integration\", 			\"localDir\": \"E:/interface/backend/kyivstar/c-ks/snd-integration/\" 		} 	}, 	\"snd-KS-C-buildear\": { 		\"mvnId\": \"snd-KS-C-buildear\", 		\"mvnLoc\": \"E:/interface/backend/kyivstar/c-ks/snd-integration/buildear/\", 		\"mvnpackage\": \"ear\", 		\"git\": { 			\"username\": \"vinay.nagaraj@enhancesys.com\", 			\"password\": \"treet\", 			\"url\": \"appuser@192.168.2.227:/home/appuser/snoc/snd-integration\", 			\"localDir\": \"E:/interface/backend/kyivstar/c-ks/snd-integration/\" 		} 	}, 	\"KS-ui-commons\": { 		\"mvnId\": \"KS-ui-commons\", 		\"mvnLoc\": \"E:/interface/frontend-UI/Development/kyivstar/commons/\", 		\"mvnpackage\": \"pom\" 	}, 	\"KS-ui-uiSnoc\": { 		\"mvnId\": \"KS-ui-uiSnoc\", 		\"mvnLoc\": \"E:/interface/frontend-UI/Development/kyivstar/uiSnoc/\", 		\"mvnpackage\": \"pom\" 	}, 	\"KS-ui-srSnoc\": { 		\"mvnId\": \"KS-ui-srSnoc\", 		\"mvnLoc\": \"E:/interface/frontend-UI/Development/kyivstar/srSnoc/\", 		\"mvnpackage\": \"pom\" 	} }\r\n" + 
			"#AUTO_MAVEN_CONF     = { 	\"snd-INDO-D-sourcecode\": { 		\"mvnId\": \"snd-INDO-D-sourcecode\", 		\"mvnLoc\": \"E:/interface/backend/indosat/d-indo/snd-integration/sourcecode/\", 		\"mvnpackage\": \"pom\", 		\"git\": { 			\"username\": \"vinay.nagaraj@enhancesys.com\", 			\"password\": \"treet\", 			\"url\": \"appuser@192.168.2.227:/home/appuser/snoc/snd-integration\", 			\"localDir\": \"E:/interface/backend/indosat/d-indo/snd-integration/\" 		} 	}, 	\"snd-INDO-D-buildear\": { 		\"mvnId\": \"snd-INDO-D-buildear\", 		\"mvnLoc\": \"E:/interface/backend/indosat/d-indo/snd-integration/buildear/\", 		\"mvnpackage\": \"pom\", 		\"git\": { 			\"username\": \"vinay.nagaraj@enhancesys.com\", 			\"password\": \"treet\", 			\"url\": \"appuser@192.168.2.227:/home/appuser/snoc/snd-integration\", 			\"localDir\": \"E:/interface/backend/indosat/d-indo/snd-integration/\" 		} 	}, 	\"snd-INDO-d4s3-sourcecode\": { 		\"mvnId\": \"snd-INDO-d4s3-sourcecode\", 		\"mvnLoc\": \"E:/interface/backend/indosat/d4s3-indo/snd-integration/sourcecode/\", 		\"mvnpackage\": \"pom\", 		\"git\": { 			\"username\": \"vinay.nagaraj@enhancesys.com\", 			\"password\": \"treet\", 			\"url\": \"appuser@192.168.2.227:/home/appuser/snoc/snd-integration\", 			\"localDir\": \"E:/interface/backend/indosat/d4s3-indo/snd-integration/\" 		} 	}, 	\"snd-INDO-d4s3-buildear\": { 		\"mvnId\": \"snd-INDO-d4s3-buildear\", 		\"mvnLoc\": \"E:/interface/backend/indosat/d4s3-indo/snd-integration/buildear/\", 		\"mvnpackage\": \"ear\", 		\"git\": { 			\"username\": \"vinay.nagaraj@enhancesys.com\", 			\"password\": \"treet\", 			\"url\": \"appuser@192.168.2.227:/home/appuser/snoc/snd-integration\", 			\"localDir\": \"E:/interface/backend/indosat/d4s3-indo/snd-integration/\" 		} 	} }\r\n" + 
			"AUTO_MAVEN_CONF      = {\"snd-INDO-D-sourcecode\":{\"mvnId\":\"snd-INDO-D-sourcecode\",\"mvnLoc\":\"E:/interface/backend/indosat/d-indo/snd-integration/sourcecode/\",\"mvnpackage\":\"pom\",\"JAVA_HOME\":\"C:/Program Files/Java/jdk1.7.0_80\",\"git\":{\"username\":\"vinay.nagaraj@enhancesys.com\",\"password\":\"treet\",\"url\":\"appuser@192.168.2.227:/home/appuser/snoc/snd-integration\",\"localDir\":\"E:/interface/backend/indosat/d-indo/snd-integration/\"}},\"snd-INDO-D-buildear\":{\"mvnId\":\"snd-INDO-D-buildear\",\"mvnLoc\":\"E:/interface/backend/indosat/d-indo/snd-integration/buildear/\",\"mvnpackage\":\"pom\",\"JAVA_HOME\":\"C:/Program Files/Java/jdk1.7.0_80\",\"git\":{\"username\":\"vinay.nagaraj@enhancesys.com\",\"password\":\"treet\",\"url\":\"appuser@192.168.2.227:/home/appuser/snoc/snd-integration\",\"localDir\":\"E:/interface/backend/indosat/d-indo/snd-integration/\"}},\"snd-INDO-d4s3-sourcecode\":{\"mvnId\":\"snd-INDO-d4s3-sourcecode\",\"mvnLoc\":\"E:/interface/backend/indosat/d4s3-indo/snd-integration/sourcecode/\",\"mvnpackage\":\"pom\",\"JAVA_HOME\":\"C:/Program Files/Java/jdk1.7.0_80\",\"git\":{\"username\":\"vinay.nagaraj@enhancesys.com\",\"password\":\"treet\",\"url\":\"appuser@192.168.2.227:/home/appuser/snoc/snd-integration\",\"localDir\":\"E:/interface/backend/indosat/d4s3-indo/snd-integration/\"}},\"snd-INDO-d4s3-buildear\":{\"mvnId\":\"snd-INDO-d4s3-buildear\",\"mvnLoc\":\"E:/interface/backend/indosat/d4s3-indo/snd-integration/buildear/\",\"mvnpackage\":\"ear\",\"JAVA_HOME\":\"C:/Program Files/Java/jdk1.7.0_80\",\"git\":{\"username\":\"vinay.nagaraj@enhancesys.com\",\"password\":\"treet\",\"url\":\"appuser@192.168.2.227:/home/appuser/snoc/snd-integration\",\"localDir\":\"E:/interface/backend/indosat/d4s3-indo/snd-integration/\"}},\"work-sample\":{\"mvnId\":\"work-sample\",\"mvnLoc\":\"E:/interface/work/enh-work/automation/sourcecode/\",\"mvnpackage\":\"pom\",\"JAVA_HOME\":\"C:/Program Files/Java/jdk1.7.0_80\"}}\r\n" + 
			"\r\n" + 
			"#AUTO_MAVEN_CMDS     = {\"1\":\"mvn clean\",\"2\":\"mvn clean install\",\"3\":\"mvn clean package\"}\r\n" + 
			"AUTO_MAVEN_CMDS     = {\"1\":\"mvn clean\",\"2\":\"mvn clean install\",\"3\":\"mvn clean package\",\"4\":\"mvn clean install -U\"}\r\n" + 
			"\r\n" + 
			"# Swing button names\r\n" + 
			"#BUTTON_NAMES_SWING  = {\"BUTTON_NAMES\":{\"launchPutty\":\"PUTTY\",\"launchssh\":\"UPLOAD\",\"launchGit\":\"GIT\",\"nextColor\":\"THEME\",\"launchAutoMvnconf\":\"MVN\",\"launchWinscp\":\"WINSCP\", \"serverProcess\":\"PROCESS KILL\"}}\r\n" + 
			"BUTTON_NAMES_SWING  = {\"BUTTON_NAMES\":{\"launchPutty\":\"PUTTY\",\"launchssh\":\"UPLOAD\",\"launchGit\":\"GIT\",\"nextColor\":\"THEME\",\"launchAutoMvnconf\":\"MVN\",\"launchWinscp\":\"WINSCP\", \"serverProcess\":\"PROCESS\"}}\r\n" + 
			"\r\n" + 
			"#PUTTY PATH IN LOCAL\r\n" + 
			"PUTTY_PATH          = C:/Program Files/PuTTY/putty.exe\r\n" + 
			"PLINK_PATH          = C:/Program Files/PuTTY/plink.exe\r\n" + 
			"\r\n" + 
			"#WINSCP PATH & Connection type (sftp/scp) default=> sftp\r\n" + 
			"WINSCP_PATH         = C:/Program Files (x86)/WinSCP/WinSCP.exe\r\n" + 
			"#WINSCP_CON_TYPE     = sftp\r\n" + 
			"#WINSCP_CON_TYPE     = scp\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"#TITLE\r\n" + 
			"TITLE               = Enhancesys Innovation (DeveloperEasy Tool)\r\n" + 
			"\r\n" + 
			"# For more details check README.txt file";

	public final static String _README = "FineVM README                                                                           \r\n" + 
			"=============                                                                           \r\n" + 
			"																						 \r\n" + 
			"																						 \r\n" + 
			"DeveloperEasy Tool                                                                      \r\n" + 
			"========================================================================================\r\n" + 
			"This is the README file for the FineVM's DeveloperEasy tool                             \r\n" + 
			"you're reading this, you've probably just run our exe file and                          \r\n" + 
			"no need to install also on your system.                                                 \r\n" + 
			"========================================================================================\r\n" + 
			"How should I use this Tool ?                                                            \r\n" + 
			"----------------------------                                                            \r\n" + 
			"																						 \r\n" + 
			"=> Two type of server configuration : 1.localhost 2.remote                              \r\n" + 
			"																						 \r\n" + 
			"=> If you want to use PuTTY or Winscp to connect to remote server, just one click       \r\n" + 
			"tool helps to open putty or Winscp. For Putty local server it'll open cmd for that path.\r\n" + 
			"																						 \r\n" + 
			"=> When you are working in maven project , you are building ear, war and jar            \r\n" + 
			"for according your cmd the tool will helps you.                                         \r\n" + 
			"																						 \r\n" + 
			"=> Then after maybe you will replacing old ear, war or jar with new one                 \r\n" + 
			"from your local to server. This thing the tool helps you.                               \r\n" + 
			"																						 \r\n" + 
			"=> Git pull can done with this tool and recent logs you can see this from this tool     \r\n" + 
			"Git commit and push option is disabled because of security reasons                      \r\n" + 
			"																						 \r\n" + 
			"																						 \r\n" + 
			"What should I do next?                                                                  \r\n" + 
			"----------------------                                                                  \r\n" + 
			"Nothing just configure in common.properties. Enjoy with this tool. ;-)                  \r\n" + 
			"																						 \r\n" + 
			"Thanks & Regards                                                                        \r\n" + 
			"================                                                                        \r\n" + 
			"Enhancesys Innovation Pvt Ltd.                                                          \r\n" + 
			"																						 \r\n" + 
			"Suresh Upparu    <suresh.upparu@enhancesys.com>                                         \r\n" + 
			"Gopinath         <gopinath.angalakurthi@enhancesys.com>                                 \r\n" + 
			"Vinayak Mahadev  <vinay.nagaraj@enhancesys.com>                                         \r\n" + 
			"																						 \r\n" + 
			"																						 \r\n" + 
			"========================================================================================\r\n" + 
			"Last updated on 20 August 2019\r\n" + 
			"Note :                                                                                  \r\n" + 
			"Here you will find our list of known bugs and pending feature                           \r\n" + 
			"requests. If your problem is not listed in there, or in the FAQ, or                     \r\n" + 
			"in the manuals, send the Feedback  to vinayakmahadev.nm@gmail.com.                      \r\n" + 
			"________________________________________________________________________________________";
}
