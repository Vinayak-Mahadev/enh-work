package com.finevm.enh.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestWriter {

	private final String fileLoc = IWorkConstants.FILE_OPERATION_LOC+"output/";
	private File file ;
	private final String abFile;
	private BufferedWriter bufferedWriter;
	private FileWriter fileWriter;
	public TestWriter(String fileName) {
		abFile = fileLoc+fileName;	
		try {

			file = new File(abFile);
			fileWriter = new FileWriter(file, false);
			bufferedWriter = new BufferedWriter(fileWriter);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static TestWriter getWriter(String fileName) {
		return new TestWriter(fileName);
	}

	public void write(Object line) {
		try {
			bufferedWriter.write(line.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeln(Object line){
		try {
			bufferedWriter.write(line+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
