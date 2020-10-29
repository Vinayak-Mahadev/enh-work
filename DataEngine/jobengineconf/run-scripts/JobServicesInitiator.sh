#!/bin/sh
X=`ps -ef|grep JobEngine | grep -v grep |wc -l`
Y=0
if [ "$X" == "$Y" ]; then
	echo 'Starting JobEngine Job and log will print on $CATALINA_HOME/logs/job_services.log'
	export JOB_INFO_PATH=/home/appuser/snoc/lib/snoc
	java -Xms512M -Xmx1024M -XX:+PrintGCDetails -XX:+PrintGCDateStamps -D -cp $JOB_INFO_PATH/JobEngine.jar:$CATALINA_BASE/lib/*:$CATALINA_HOME/lib/*:$JOB_INFO_PATH/JobEngine.jar com.enhancesys.job.services.JobServicesInitiator $1
	echo "Started in background at: $(date)"
else
	echo "JobEngine : Already running"
fi
