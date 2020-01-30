package enh.team.interfaces.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;

import com.finevm.enh.util.IWorkConstants;


public class FileSearch {

	private JSONArray findInExtentionsInFile;
	private JSONArray skipExtentionsInFile;
	private FileOutputStream oStream;
	private boolean logStatus;
	private  Integer count = 0;
	private String wordSearch;
	private String fileLoc = IWorkConstants.FILE_OPERATION_LOC+"FileSearchLogs-" ;

	public FileSearch(){
		
	}


	public FileSearch startLog() throws Exception
	{
		SimpleDateFormat date= null; 

		if(logStatus)
		{
			date     = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String d = (date.format(new Date())).replaceAll(" ", "-").replaceAll(":", "");
			fileLoc  = fileLoc + d+ ".log";
			oStream  = (new FileOutputStream(new File(fileLoc),true));
		}

		return this;
	}

	public  void start(String directory, JSONArray findInExtention, JSONArray skipExtentions) {

		this.findInExtentionsInFile = findInExtention;
		this.skipExtentionsInFile   = skipExtentions;

		File file = new File(directory);

		if (file.isDirectory()) {
			search(file);
		} else {
			System.out.println(file.getAbsoluteFile() + " is not a directory!");
		}
	}


	public FileSearch setLogStatus(boolean logStatus) {
		this.logStatus = logStatus;
		return this;
	}

	public void close() throws Exception 
	{
		if(oStream!=null)
			oStream.close();
	}


	private void search(File file){

		try {

			if (file.isDirectory()) {

				if (file.canRead()) {
					for (File temp : file.listFiles()) {
						if (temp.isDirectory()) 
						{
							search(temp);
						} 
						else 
						{
							String filename          = temp.getAbsoluteFile().toString();
							boolean findIn           = false;

							if(findInExtentionsInFile != null)
							{
								for(Object endExten : findInExtentionsInFile )
								{
									if(filename.endsWith(endExten.toString())){
										findIn = true;
									}
								} 
							}
							if(skipExtentionsInFile != null)
							{
								for(Object endExten : skipExtentionsInFile )
								{
									if(filename.endsWith(endExten.toString())){
										findIn = false;
									}
								} 
							}
							if(skipExtentionsInFile==null || findInExtentionsInFile == null){
								findIn = true;
							}
							if(findIn)
							{
								BufferedReader b = new BufferedReader(new FileReader(new File(filename)));
								String readLine  = "";
								int lineNum      = 0;

								while ((readLine = b.readLine()) != null) {
									if(readLine.contains(wordSearch))
									{
										count++;
										String data = (wordSearch+" ::   "+filename + "  ::   Line :: "+(++lineNum)+"   ::    "+readLine+"\n");
										//System.out.println(data);
										if(oStream != null)
											oStream.write(data.getBytes());
									}
								}
								b.close();
							}
						}
					}
				} 
				else 
					System.out.println(file.getAbsoluteFile() + "Permission Denied");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}


	public  String getWordSearch() {
		return wordSearch;
	}
	public  void setWordSearch(String wordSearch) {
		this.wordSearch = wordSearch;
	}

	public  Integer getCount() {
		return count;
	}
	public  void setCount(Integer count) {
		this.count = count;
	}


}