package com.enhancesys.integration.services.dataengine.initiator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JobServiceTest {

	public static void main(String[] args) 
	{
		boolean isLocal = true;

		try 
		{
			if(isLocal) 
			{
				invoke("CourseLvlSummaryDtlsReport_csv.json");
				invoke("CourseLvlSummaryDtlsReport_pdf.json");
				invoke("CourseLvlSummaryDtlsReport_excel.json");
				invoke("CourseLvlSummaryReport_csv.json");
				invoke("CourseLvlSummaryReport_pdf.json");
				invoke("CourseLvlSummaryReport_excel.json");
				invoke("CourseRwrdSummaryReport_csv.json");
				invoke("CourseRwrdSummaryReport_pdf.json");
				invoke("CourseRwrdSummaryReport_excel.json");
				invoke("user_org_mapping_csv.json");
				invoke("user_org_mapping_pdf.json");
				invoke("user_org_mapping_excel.json");
				invoke("Inventory_ledger_dtls_csv.json");
				invoke("Inventory_ledger_dtls_xls.json");
				invoke("Inventory_ledger_dtls_pdf.json");
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void invoke(String fileName) throws Exception
	{
		Double startTime = Double.valueOf(System.currentTimeMillis());
		Double endTime = null;
		File cpFile = null;
		String path = "E:/interface/work/enh-work/DataEngine/jobengineconf/sample_data/" + fileName;
		String setLocalOuputPath = "E:/interface/work/enh-work/DataEngine/jobengineconf/outputfiles/";
		String[] args = new String[1];
		cpFile = setLocalConfig(path, setLocalOuputPath);
		args[0] = cpFile.getAbsolutePath();

		JobServicesInitiator.main(args);

		cpFile.delete();
		endTime = Double.valueOf(System.currentTimeMillis());
		System.out.println("Total Time required for JobServices :: " + Float.valueOf(( (endTime - startTime) / 60000)+"") + " mins  :: Filepath :: " + path + "\n\n");

	}

	@SuppressWarnings("unchecked")
	private static File setLocalConfig(String filepath, String setLocalOuputPath)
	{
		String jsonStr = null;
		String line = null;
		StringBuffer buffer = null;
		File file = null;
		FileInputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		JSONParser parser = null;
		JSONObject jobParameters = null;
		FileWriter fileWriter = null;
		try
		{
			if(filepath == null || (filepath != null && filepath.trim().isEmpty())) {
				System.out.println("File not found given input :: " + filepath);
				return null;
			}
			try 
			{
				file  = new File(filepath);
				if(!file.exists()) 
				{
					System.out.println("File not present given location :: " + filepath);
					return null;
				}

				inputStream = new FileInputStream(file);
				inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
				bufferedReader = new BufferedReader(inputStreamReader);

				buffer = new StringBuffer();
				line = null;
				while ((line= bufferedReader.readLine()) != null)
				{
					buffer.append(line);
				}

				inputStream.close();
				inputStreamReader.close();
				bufferedReader.close();
				jsonStr = buffer.toString();

				parser = new JSONParser();
				jobParameters = (JSONObject) parser.parse(jsonStr);
				JSONObject jobData = (JSONObject) jobParameters.get("job-data");

				jobData.put("output_file_path", setLocalOuputPath);
				jobData.put("output_file_date_patten", "yyyyMMdd");
				jobParameters.put("job-data", jobData);
				file = new File(filepath+".cp");
				fileWriter = new FileWriter(filepath+".cp");
				fileWriter.write(jobParameters+"");
				fileWriter.close();

				System.out.println("Copy file with local output path :: " + file.getAbsolutePath());
			} 
			catch (Exception e) 
			{
				System.out.println("Exeception while processing file :: " + filepath + e);
			}

		}
		catch(Exception exception)
		{
			System.out.println("Unhandled Exception : " + exception.getMessage() + exception);
		}
		finally
		{
			filepath = null;
			jsonStr = null;
			line = null;
			buffer = null;
			bufferedReader = null;
			inputStreamReader = null;
			inputStream = null;
			parser = null;
			jobParameters = null;
		}
		return file;
	}
}
