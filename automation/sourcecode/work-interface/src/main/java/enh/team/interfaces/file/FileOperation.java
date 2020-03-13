package enh.team.interfaces.file;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

import com.finevm.enh.util.IWorkConstants;
/**
 * <pre>Base Release</pre>
 * @date 05-SEP-2019
 * @author VINAYAK MAHADEV
 *
 */
public class FileOperation 
{


	/**
	 * <pre>
	 *  This Method check the data in csv file if present it write file with given path..<br> 
	 *  Example :
	 *   
	 *  String header           = "SCHEDULEDATETIME|VIsitDatetime|Channel|Region|Area|Sales Area|Cluster|Micro Cluster|Additional Territory|TerritoryID|VISIT_TYPE|BeatPlanID|BeatPlanName|VisitorOrgType|VisitorORGID|VisitorSaldoMOBO Org ID|VisitorOrgName|VisitedOrgType|VisitedORGID|VisitedSaldoMOBO Org ID|VisitedOrgName|VisitedOrg Address|OperatorType|OperatorId|UserName|Operator Full Name|StartDateTime|EndDatetime|Time On Beat|Comments|Planned Distance(in KM)|Activity Name|Activity Status|PRODUCT_CATEGORY|PRODUCT_BRAND|PRODUCT_CODE|PRODUCT_NAME|Total Sales Qty|Total Sales Value|Org Location|Activity Location|Deviation Distance(in KM)|Reason"; <br>
	 *  String columnName       = "Reason"; <br>
	 *  String csvFileAbPath    =  "C:\\Users\\vinayak\\Downloads\\Visit_Organization_Detail_20200127.csv"; <br>
	 *  String outputFileAbPath = "E:\\vm\\testprojects\\Visit_Organization_Detail.log"; <br>
	 *  String csvDelimiter     = "\\|"; <br>
	 * 
	 * Call like : fileOperation.checkCsvColumnDataIfValueNotEmptyWriteInFileWithLineNumberOfFile(header, columnName, csvFileAbPath, outputFileAbPath, csvDelimiter);
	 * </pre>
	 * @param csvFileAbPath
	 * @param outputFileAbPath
	 * @param csvDelimiter
	 * @param header
	 * @param columnName
	 * @author Vinayak Mahadev
	 * @throws Exception check the exception
	 * @since  Jan-28-2020
	 */
	public void checkCsvColumnDataIfValueNotEmptyWriteInFileWithLineNumberOfFile( String header, String columnName, String csvFileAbPath, String outputFileAbPath, String csvDelimiter) throws Exception {

		BufferedReader fileReader = null;
		FileWriter fileWriter = null;
		File file = null;
		String line = null;
		String[] lineDataArray = null;
		int lineDataPlace = 0;
		int lineNumber = 0;
		try
		{
			lineDataArray = header.split(csvDelimiter,-1);
			for (String data : lineDataArray) {
				if(data.trim().equals(columnName.trim()))
					break;
				lineDataPlace++;
			}
			file = new File(csvFileAbPath);
			fileReader = new BufferedReader(new FileReader(file));
			fileWriter = new FileWriter(new File(outputFileAbPath),true);
			fileWriter.write("\n\n\nFileName :: "+file.getName()+"\n\n");

			while ((line = fileReader.readLine()) != null) {
				lineNumber++;
				lineDataArray = line.split(csvDelimiter,-1);
				if(lineDataArray.length > lineDataPlace && lineDataArray[lineDataPlace]!=null && !lineDataArray[lineDataPlace].isEmpty())
				{
					fileWriter.write("LineNumber :: "+lineNumber+"    Data :  "+lineDataArray[lineDataPlace]+"\n");
				}
			}
			fileWriter.write("\n\n");
			fileReader.close();
			fileWriter.close();
		} 
		finally {

			if(fileReader!=null)
				fileReader.close();
			if(fileWriter!=null)
				fileWriter.close();

			fileReader = null;
			fileWriter = null;
			line = null;
			file = null;
			lineDataArray = null;

		}

	}

	/**
	 * <pre>
	 * This method finds the list of keys in fileDir. It accepts one jsonObj in that obj ...<br/>
	 * <br/>String -  keyLocation    - is used for loading list of keys from file.  
	 * <br/>String -  searchDir      - is used for which directory find the keys.  
	 * <br/>String -  outputFileName - is used for while finding keys if not found that key written in this given file location  
	 * <br/>Integer-  skiptimes      - is used for how many times we need to skip if we found key  
	 * <br/>JSONArray [String only]- skipFiles   - is used for finding keys in files if is there skip that file..
	 * <br/>JSONArray [String only]- extenInFiles- is used for loading list of keys from file.  
	 * <br/>
	 * </pre>
	 * <br/>
	 * 	<pre>Log is printing while calling this method </pre>
	 * @param jsonObject
	 * @throws Exception
	 * @author VINAYAK MAHADEV
	 * @Email vinay.nagaraj@enhancesys.com
	 */

	public void startFileSearch(JSONObject jsonObject) throws Exception
	{
		String outputFileName      = jsonObject.getString("outputFileName");
		String keyLocation         = jsonObject.getString("keyLocation");
		String searchDir           = jsonObject.getString("searchDir");
		JSONArray extenInFiles     = jsonObject.getJSONArray("extenInFiles");
		JSONArray skipFiles        = jsonObject.getJSONArray("skipFiles");
		int skiptimes              = jsonObject.getInt("skiptimes");

		FileSearch fileSearch      = new FileSearch().setLogStatus(true).startLog();
		FileOutputStream osFail    = new FileOutputStream(new File(outputFileName),true);
		String data                = null;
		int i = 0;

		for( String key: loadKeys(keyLocation, false))
		{
			fileSearch.setWordSearch(key.toString());
			fileSearch.setCount(0);
			fileSearch.start(searchDir, extenInFiles,skipFiles);

			if((fileSearch.getCount().intValue() == skiptimes))
			{
				data = fileSearch.getWordSearch()+"\n";
				osFail.write(data.getBytes());
			}
			System.out.println(++i + "  "+ fileSearch.getWordSearch());
		}
		osFail.close();
		fileSearch.close();
	}

	/**
	 * <pre>
	 * 1.   This method takes file location path, read the filename if it's .properties file key only takes and add to list.
	 * if not a .properties file it takes as txt file entier line added to list.. </pre>
	 * <br/>
	 * <pre>
	 * 2.   If you want keys as a separate file pass ifNeedAsAFile is true
	 * </pre>
	 * @param keyLocation
	 * @return list of key names
	 * @throws Exception
	 * @author VINAYAK MAHADEV
	 * @Email vinay.nagaraj@enhancesys.com
	 */
	public List<String> loadKeys(String keyLocation, boolean ifNeedAsAFile) throws Exception
	{
		List<String> list = null;
		BufferedOutputStream bos = null;
		String keyFileLoc = null;
		try
		{

			list = 	new ArrayList<String>();
			if(ifNeedAsAFile)
			{
				keyFileLoc = IWorkConstants.FILE_OPERATION_LOADKEY_OUTPUT_FILE+"loadKeys.txt";
				bos = new BufferedOutputStream(new FileOutputStream(new File(keyFileLoc)));
			}

			if(keyLocation.endsWith(".properties"))
			{
				Properties properties   = new Properties();
				File file = new File(keyLocation);
				properties.load(new FileInputStream(file));

				for (Object key : properties.keySet()) 
					list.add(key.toString());

			}
			else
			{
				BufferedReader br = new BufferedReader(new FileReader(new File(keyLocation)));

				String readLine   = null;

				while ((readLine = br.readLine()) != null) 
					list.add(readLine);


				if(br!=null)
					br.close();
			}

			Collections.sort(list);

			if(ifNeedAsAFile)
			{
				for (String string : list) {
					bos.write((string+"\n").getBytes());
				}
			}

		} 
		finally 
		{
			if(bos!=null){
				bos.close();
				System.out.println("Key file created location  ::  " + keyFileLoc);
				keyFileLoc= null;
				bos = null;
			}
		}
		return list;


	}

	/**
	 * <pre>
	 * Give records in Input file and Deleted records in delete file. 
	 * This method read delete file line by line , if line is contains in input 
	 * file that line remove from that main record. Finally the filtered record
	 * present in output file
	 * </pre>
	 * @author VINAYAK MAHADEV
	 * @Email vinay.nagaraj@enhancesys.com
	 * @throws Exception
	 */
	public void deleteLineInFile(JSONObject jsonObject) throws Exception{

		String inputFile  = jsonObject.getString("inputFile");
		String deleteFile = jsonObject.getString("deleteFile");
		String outputFile = jsonObject.getString("outputFile");



		// PrintWriter object for output.txt 
		PrintWriter pw = new PrintWriter(outputFile); 

		// BufferedReader object for input.txt 
		BufferedReader br1 = new BufferedReader(new FileReader(inputFile)); 
		BufferedReader br2 = null;
		String line1 = br1.readLine(); 

		// loop for each line of input.txt 
		while(line1 != null) 
		{ 
			boolean flag = false; 

			// BufferedReader object for delete.txt 
			br2 = new BufferedReader(new FileReader(deleteFile)); 

			String line2 = br2.readLine(); 

			// loop for each line of delete.txt 
			while(line2 != null) 
			{ 
				if(line1.contains(line2)) 
				{ 
					flag = true; 
					break; 
				} 

				line2 = br2.readLine(); 
			} 

			// if flag = false 
			// write line of input.txt to output.txt 
			if(!flag) 
				pw.println(line1); 

			line1 = br1.readLine(); 

		} 

		pw.flush(); 

		// closing resources 
		if(br1!=null)
			br1.close(); 
		if(br2!=null)
			br2.close();
		if(pw!=null)
			pw.close(); 

		System.out.println("File operation performed successfully");
	}



	public void readFile(String fileAbPath) {
		BufferedReader reader = null;
		String line;
		try 
		{
			reader = new BufferedReader(new FileReader(new File(fileAbPath)));
			while ((line = reader.readLine()) != null) 
				System.out.println(line);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		finally {
			if(reader!=null)
				try {
					reader.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			reader = null;
		}
	} 

	public String readFirstLineInFile(String fileAbPath) {
		BufferedReader reader = null;
		String line = null;
		try 
		{
			reader = new BufferedReader(new FileReader(new File(fileAbPath)));
			if ((line = reader.readLine()) != null) 
				return line;
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(reader!=null)
				try {
					reader.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			reader = null;
		}
		return line;
	}


	public static String  readFileWithoutNewLine(File file) {
		String line=null;
		String responce = "";
		BufferedReader reader = null;
		try {
			reader =new BufferedReader( new FileReader(file));
			while ((line = reader.readLine()) != null) 
				responce=responce+line;
		} catch (IOException e) {
			responce = null;
			e.printStackTrace();
		} 
		finally {
			if(reader!=null)
				try {
					reader.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			reader = null;
			line=null;
		}
		return responce;
	} 

	
}
