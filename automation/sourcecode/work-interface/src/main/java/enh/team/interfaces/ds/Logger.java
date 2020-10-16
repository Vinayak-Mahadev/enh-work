package enh.team.interfaces.ds;

public class Logger {

	private static Logger logger;

	public static Logger getLogger(Class<?> cls) {
		if(logger == null)
			logger =  new Logger();
		return logger;
	}

	public void info(Object obj) {
		System.out.println(obj);
	}

	public void debug(Object obj) {
		System.out.println(obj);
	}

	public void error(Object obj) {
		System.out.println(obj);
	}
	
	public void error(Object obj, Throwable throwable) {
		System.err.println( obj+", " + throwable);
	}
}
