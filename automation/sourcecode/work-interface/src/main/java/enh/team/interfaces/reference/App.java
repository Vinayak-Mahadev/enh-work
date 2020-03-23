package enh.team.interfaces.reference;

public class App {

	public static void main(String[] args)
	{
		GenerateFile generateFile = null;
		try 
		{
			generateFile = new GenerateFile();
			generateFile.start(false);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			generateFile = null;	
		}

	}

}
