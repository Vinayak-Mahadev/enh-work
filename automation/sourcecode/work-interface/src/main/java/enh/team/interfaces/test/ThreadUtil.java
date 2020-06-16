package enh.team.interfaces.test;

import java.util.ArrayList;
import java.util.List;

import enh.team.interfaces.soapservice.SndIntegrationSoupService;

public class ThreadUtil implements Runnable
{

	@Override
	public void run() 
	{
		try 
		{
			if(methodName.equals("processFile"))
				sndIntegrationSoupService.processFile(interfaceId);

			if(methodName.equals("processReceivedFiles"))
				sndIntegrationSoupService.processReceivedFiles(interfaceId);

			if(methodName.equals("prepareRejectionFile"))
				sndIntegrationSoupService.prepareRejectionFile(interfaceId);

			if(methodName.equals("pullDataToFile"))
				sndIntegrationSoupService.pullDataToFile(interfaceId);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void processFile(long interfaceId) 
	{
		ThreadUtil threadUtil = null;
		try 
		{
			threadUtil = new ThreadUtil();
			threadUtil.setMethodName(interfaceId);
			threadUtil.setMethodName("processFile");
			Thread thread = new Thread(threadUtil);
			thread.start();
			threads.add(thread);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}	
	}

	public static void processReceivedFiles(long interfaceId)
	{

		ThreadUtil threadUtil = null;
		try 
		{
			threadUtil = new ThreadUtil();
			threadUtil.setMethodName(interfaceId);
			threadUtil.setMethodName("processReceivedFiles");
			Thread thread = new Thread(threadUtil);
			thread.start();
			threads.add(thread);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}	

	}

	public static void prepareRejectionFile(long interfaceId)
	{

		ThreadUtil threadUtil = null;
		try 
		{
			threadUtil = new ThreadUtil();
			threadUtil.setMethodName(interfaceId);
			threadUtil.setMethodName("prepareRejectionFile");
			Thread thread = new Thread(threadUtil);
			thread.start();
			threads.add(thread);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}	

	}


	public static void pullDataToFile(long interfaceId)
	{

		ThreadUtil threadUtil = null;
		try 
		{
			threadUtil = new ThreadUtil();
			threadUtil.setMethodName(interfaceId);
			threadUtil.setMethodName("pullDataToFile");
			Thread thread = new Thread(threadUtil);
			thread.start();
			threads.add(thread);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}	

	}

	private void setMethodName(long interfaceId) 
	{
		this.interfaceId = interfaceId;
	}
	private void setMethodName(String methodName) 
	{
		this.methodName = methodName;
	}

	public static void threadsJoin() {
		try
		{
			for (Thread thread : threads) {
				thread.join();
			}
			threads.clear();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static void threadsKill() {
		try
		{
			for (Thread thread : threads) {
				thread.destroy();
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}


	private ThreadUtil() {
		super();
	}

	private final SndIntegrationSoupService sndIntegrationSoupService = new SndIntegrationSoupService();
	private long interfaceId = 0l;
	private String methodName;

	private final static List<Thread> threads = new ArrayList<Thread>();

}
