package enh.team.interfaces.dumptest.util;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <b>Purpose:</b><br>
 * Declaration of the FileBean service parameter properties..<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Enhancesys Innovations 2015<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1       31-03-2015		   Suresh Upparu
 *   -- Base Release
 */
@XmlRootElement(name = "fileBean")
public class FileBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String fileName;
	private String fileHeaders;
	
	public String getFileName() 
	{
		return fileName;
	}
	
	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}
	
	public String getFileHeaders() 
	{
		return fileHeaders;
	}
	
	public void setFileHeaders(String fileHeaders) 
	{
		this.fileHeaders = fileHeaders;
	}
}