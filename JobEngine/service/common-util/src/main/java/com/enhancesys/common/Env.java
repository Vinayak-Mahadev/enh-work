package com.enhancesys.common;

interface Env {

	static final  String PROJECT_LOC = System.getenv("APPSERVER_CONF_PATH") + "/jobengineconf/";

}
