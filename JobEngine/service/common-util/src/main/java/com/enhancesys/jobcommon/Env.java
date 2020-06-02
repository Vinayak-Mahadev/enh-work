package com.enhancesys.jobcommon;

interface Env {

	static final  String PROJECT_LOC = System.getenv("APPSERVER_CONF_PATH") + "/jobengineconf/";

}
