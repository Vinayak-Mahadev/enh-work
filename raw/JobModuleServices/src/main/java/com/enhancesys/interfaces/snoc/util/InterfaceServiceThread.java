package com.enhancesys.interfaces.snoc.util;

import org.apache.log4j.Logger;

import com.enhancesys.interfaces.snoc.services.InterfaceServicesDeligator;
import com.enhancesys.interfaces.snoc.services.InterfaceServicesDownloadProcessor;
import com.mongodb.BasicDBObject;

public class InterfaceServiceThread implements Runnable{

	private static Logger LOGGER = Logger.getLogger(InterfaceServiceThread.class);
	
	private InterfaceServicesDeligator interfaceServicesDeligator;
	private BasicDBObject requestObject;
	
	public InterfaceServiceThread(BasicDBObject requestObject,InterfaceServicesDeligator interfaceServicesDeligator){
		this.requestObject = requestObject;
		this.interfaceServicesDeligator = interfaceServicesDeligator;
	}
	
	@Override
	public void run() {
		try{
			if(requestObject.containsField("method_call") && !requestObject.getString("method_call").equalsIgnoreCase("")){
				
				LOGGER.info("[InterfaceServiceThread - run ] invoking to "+requestObject.getString("method_call")+" method...");
				
				InterfaceServicesDownloadProcessor.updateNotification(null, requestObject, 351L,interfaceServicesDeligator.interfaceServicesDAO);
				InterfaceServicesDeligator.class.getDeclaredMethod(requestObject.getString("method_call"), BasicDBObject.class).invoke(interfaceServicesDeligator, requestObject);
				
			}else{
				InterfaceServicesDownloadProcessor.updateNotification(null, requestObject, 771L,interfaceServicesDeligator.interfaceServicesDAO);
			}
					
		}catch (Exception e) {
			LOGGER.error("[InterfaceServiceThread - run - Exception] : "+e.getMessage());
			try{
				InterfaceServicesDownloadProcessor.updateNotification(null, requestObject, 771L,interfaceServicesDeligator.interfaceServicesDAO);
				
			}catch (Exception exception) {
				LOGGER.error("[InterfaceServiceThread - run - Exception] : "+exception.getMessage());
			}
		}
	}
}
