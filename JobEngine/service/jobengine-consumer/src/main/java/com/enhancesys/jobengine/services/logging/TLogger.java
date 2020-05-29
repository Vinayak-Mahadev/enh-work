package com.enhancesys.jobengine.services.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.enhancesys.jobengine.services.util.GenericProcessorException;

/**
 * <b>Purpose:</b><br>
 * 		Implementation Data log for Data Services..<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2018<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        15-10-2018		   Suresh Upparu
 * 	-- Base Release
 * 
  * </pre>
 * 
 * <br>
 */
public class TLogger
{
	private static Logger _log = Logger.getLogger(TLogger.class.getName());
	private static boolean isEnable = false;
	private static String loggerName = "com.enhancesys.jobEngine.logger";

	private static TLogger getInstance()
	{
		return LoggerHolder.logger;
	}

	private static String formatMessage(String message)
	{
		String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
		int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();
		if (lineNumber == -1)
		{
			lineNumber = Thread.currentThread().getStackTrace()[0].getLineNumber();
		}
		String printmessage = "[Thread]: " + Thread.currentThread().getId() + " [ClassName]: " + fullClassName + " [LineNumber]:  " + lineNumber + "  " + message;
		return printmessage;
	}

	private static String convertToString(Throwable t)
	{
		String logMessage = null;
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		logMessage = sw.toString();
		return logMessage;
	}

	public static void debug(String message)
	{
		getInstance();
		if (_log != null)
		{
			_log.fine(formatMessage(message));
		}
	}

	public static void warn(String message)
	{
		getInstance();
		if (_log != null)
		{
			_log.warning(formatMessage(message));
		}
	}

	public static void info(String message)
	{
		getInstance();
		if (_log != null)
		{
			_log.info(formatMessage(message));
		}
	}

	public static void error(String message)
	{
		getInstance();
		if (_log != null)
		{
			_log.severe(formatMessage(message));
		}
	}

	public static void error(String message, Throwable t)
	{
		getInstance();
		if (_log != null)
		{
			_log.severe(message + " : " + convertToString(t));
		}
	}

	public static void error(String message, GenericProcessorException t)
	{
		getInstance();
		if (_log != null)
		{
			_log.severe(message + " : " + convertToString(t));
		}
	}

	public static void error(String message, Exception t)
	{
		getInstance();
		if (_log != null)
		{
			_log.severe(message + " : " + convertToString(t));
		}
	}

	public static void setLogLevel(String level)
	{
		if ("debug".equalsIgnoreCase(level))
		{
			_log.setLevel(Level.FINE);
		}
		else if ("info".equalsIgnoreCase(level))
		{
			_log.setLevel(Level.INFO);
		}
		else if ("error".equalsIgnoreCase(level))
		{
			_log.setLevel(Level.SEVERE);
		}
		else if ("fatal".equalsIgnoreCase(level))
		{
			_log.setLevel(Level.SEVERE);
		}
		else if ("warn".equalsIgnoreCase(level))
		{
			_log.setLevel(Level.WARNING);
		}
	}

	private TLogger(String loggerName)
	{
		try
		{
			_log = Logger.getLogger(loggerName);
			_log.setLevel(Level.FINEST);

			isEnable = _log.isLoggable(Level.FINE);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void setLoggerName(String logName)
	{
		if (logName == null)
		{
			loggerName = "com.enhancesys.jobEngine.logger";
			getInstance();
		}
		else
		{
			loggerName = logName;
			getInstance();
		}
	}

	public static String getLoggerName()
	{
		return loggerName;
	}

	public static boolean debugIsEnabled()
	{
		getInstance();
		return isEnable;
	}
	
	public static class LoggerHolder
	{
		public static TLogger logger = new TLogger(TLogger.loggerName);
	}
}