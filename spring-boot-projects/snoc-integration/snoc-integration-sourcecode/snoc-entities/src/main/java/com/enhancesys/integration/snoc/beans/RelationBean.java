package com.enhancesys.integration.snoc.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Purpose:</b><br>
 * Declaration of the RelationBean service parameter properties..<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Enhancesys Innovations 2018<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1       03-08-2018		   Suresh Upparu
 *   -- Base Release
 */
public class RelationBean
{
	private String parent;
	private String parentColumn;
	private List<String> childs;
	
	public String getParent()
	{
		return parent;
	}
	
	public void setParent(String parent)
	{
		this.parent = parent;
	}
	
	public String getParentColumn()
	{
		return parentColumn;
	}

	public void setParentColumn(String parentColumn)
	{
		this.parentColumn = parentColumn;
	}
	
	public List<String> getChilds()
	{
		if(childs == null)
			childs = new ArrayList<String>();
		return childs;
	}
}
