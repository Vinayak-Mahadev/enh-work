package com.enhancesys.integration.snoc.generation.conf;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EnhEntitiy 
{


	public boolean startWritingJavaFile() throws Exception 
	{
		FileWriter writer = null;
		try 
		{
			load();
			writer = new FileWriter(getJavaAbFilepath());
			writer.write("package " + getEntityPackageName()+";" + "\n\n\n");
			writer.write(EntityGeneration.DEFAULT_IMPORTS + "\n\n");

			for (String string : getEntityAnnotations())
				writer.write(string + "\n");

			if(!EntityGeneration.ENABLE_LISTENERS.isEmpty() && EntityGeneration.ENABLE_LISTENERS.containsKey(entityName))
				//				System.out.println(entityName + " : " + EntityGeneration.ENABLE_LISTENERS.get(entityName));
				writer.write(EntityGeneration.ENABLE_LISTENERS.get(entityName) + "\n");
			writer.write("public class");

			if(defaultsImplements != null )
				writer.write(" " + getEntityName() + " implements " + defaultsImplements + "\n{\n   " + defaultSerialId + "\n\n");
			else
				writer.write("{");


			for (String string : idAdapters) 
				writer.write(string + "\n");	

			for(String str : listenerPros)
				writer.write(str + "\n");

			if(setters.size() == getters.size())
			{
				List<String> setList = new ArrayList<String>(setters);
				List<String> getList = new ArrayList<String>(getters);
				for (int i = 0; i < setList.size(); i++) 
				{
					writer.write(setList.get(i) + "\n");
					writer.write(getList.get(i) + "\n");
				}
			}
			else
			{
//				System.out.println(entityName + " :: Not equal.. setter&getter");
				
				List<String> setList = new ArrayList<String>(setters);
				List<String> getList = new ArrayList<String>(getters);
				
				int i = 0;
				int j = 0;
				
				while (i <= setList.size() || j <= getList.size()) 
				{
					if(i < setList.size())
						writer.write(setList.get(i) + "\n");
					if(j < getList.size())
						writer.write(getList.get(j) + "\n");
					i ++;
					j ++;
				}
				writer.write("\n");
			}
			
			for (String string : enquirers) 
				writer.write(string + "\n");

			for (String string : adders) 
				writer.write(string + "\n");
			for (String string : removers) 
				writer.write(string + "\n");
			
			for (String string : entityFields) 
				writer.write(string);

			writer.write("}");
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if(writer != null)
				writer.close();
		}
		return false;
	}

	private void load() throws Exception
	{
		for (String key : enhEntitiyAttrMap.keySet()) 
		{
			EnhEntitiyAttr attr =   enhEntitiyAttrMap.get(key).startWritingJavaFile();

			idAdapters.add(attr.getIdAdapter());
			setters.add(attr.getSetter());
			getters.add(attr.getGetter());
			getters.add(attr.getGetter());
			enquirers.add(attr.getEnquirer());
			adders.add(attr.getAdder());
			removers.add(attr.getRemover());
			entityFields.add(attr.getentityFields());
			listenerPros.add(attr.getListenerProps());
		}
		
		
	}

	public String startKeyWithUpperCase(String key)
	{
		if(key != null && !key.trim().isEmpty())
			return (key.charAt(0) + "").toUpperCase() + key.substring(1, key.length());
		return key;
	}
	public void prepareEntityAnnotationsAndLoadEntityAttr() throws Exception
	{
		Element element = null;
		NodeList javaAnnotations = null;
		String tableAnnotation = null;
		Node childNode = null;
		NodeList childNodes = null;
		NodeList attributesNodes = null;
		try 
		{
			element = (Element) entityNode;
			childNodes = element.getChildNodes();
			entityAnnotations.add("@SuppressWarnings(\"unused\")");
			for (int root = 0 ; root < childNodes.getLength(); root++) 
			{
				childNode = childNodes.item(root);

				if("java-annotations".equals(childNode.getNodeName()))
					javaAnnotations = childNode.getChildNodes();
				else if("table".equals(childNode.getNodeName()))
				{
					NamedNodeMap map = childNode.getAttributes();
					String name = null;
					String schema = null;
					if(map.getNamedItem("schema") != null && !map.getNamedItem("schema").getNodeValue().isEmpty())
						schema = map.getNamedItem("schema").getNodeValue().trim();
					if(map.getNamedItem("name") != null && !map.getNamedItem("name").getNodeValue().isEmpty())
						name = map.getNamedItem("name").getNodeValue().trim();

					if(schema != null && name != null)
						tableAnnotation = "@Table(name=\""+ name + "\"" + " , schema=\"" + schema + "\""+ ")";
					else if (name != null)
						tableAnnotation = "@Table(name=\""+ name +"\")";
				}
				else if ("attributes".equals(childNode.getNodeName()))
					attributesNodes = childNode.getChildNodes();	
			}

			if(javaAnnotations != null)
				for (int ann = 0 ; ann < javaAnnotations.getLength(); ann++) 
					if(javaAnnotations.item(ann).getNodeType()  != Node.COMMENT_NODE && !javaAnnotations.item(ann).getTextContent().trim().isEmpty())
						entityAnnotations.add(javaAnnotations.item(ann).getTextContent().trim());

			entityAnnotations.add("@Entity(name=\""+EntityGeneration.getPACKAGE_VALUE()+"."+entityName+"\")");

			if(tableAnnotation != null)
				entityAnnotations.add(tableAnnotation);

			for (int attr = 0 ; attr < attributesNodes.getLength(); attr++) 
				if(!attributesNodes.item(attr).getNodeName().trim().equals("#text")) 
					enhEntitiyAttrMap.put(attributesNodes.item(attr).getAttributes().getNamedItem("name").getNodeValue().trim(), new EnhEntitiyAttr(entityName, attributesNodes.item(attr)));

			entityAnnotations.add("@Access(AccessType.FIELD)");
		} 
		finally 
		{

		}

	}

	public EnhEntitiy(Node entityNode, String outputPath) throws Exception 
	{
		this.entityNode = entityNode;

		NamedNodeMap namedNodeMap = entityNode.getAttributes();
		namedNodeMap.getNamedItem("class");
		Node node = namedNodeMap.getNamedItem("class");

		setEntityName(node.getNodeValue().trim());
		setEntityPackageName(EntityGeneration.getPACKAGE_VALUE());
		setJavaFileName(node.getNodeValue().trim() + EntityGeneration.FILE_EXTENTION);
		setEntityImports(EntityGeneration.DEFAULT_IMPORTS);
		setJavaAbFilepath(outputPath + "/" + getJavaFileName());
		prepareEntityAnnotationsAndLoadEntityAttr();

	}


	public String getJavaAbFilepath() {
		return javaAbFilepath;
	}

	public void setJavaAbFilepath(String javaAbFilepath) {
		this.javaAbFilepath = javaAbFilepath;
	}
	public String getJavaFileName() {
		return javaFileName;
	}

	public void setJavaFileName(String javaFileName) {
		this.javaFileName = javaFileName;
	}

	public String getEntityPackageName() {
		return entityPackageName;
	}

	public void setEntityPackageName(String entityPackageName) {
		this.entityPackageName = entityPackageName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityImports() {
		return entityImports;
	}

	public void setEntityImports(String entityImports) {
		this.entityImports = entityImports;
	}

	public void setEntityAnnotations(Set<String> entityAnnotations) {
		this.entityAnnotations = entityAnnotations;
	}

	public Set<String> getEntityAnnotations() {
		return entityAnnotations;
	}

	protected final Node entityNode;

	protected String javaFileName;

	protected String javaAbFilepath;

	protected String entityPackageName;

	protected String entityName;

	protected String entityImports;

	protected Set<String> entityAnnotations = new LinkedHashSet<String>();

	protected Set<String> entityFields = new LinkedHashSet<String>();

	protected Set<String> idAdapters = new LinkedHashSet<String>();
	protected Set<String> setters = new LinkedHashSet<String>();
	protected Set<String> getters = new LinkedHashSet<String>();
	protected Set<String> enquirers = new LinkedHashSet<String>();
	protected Set<String> adders = new LinkedHashSet<String>();
	protected Set<String> removers = new LinkedHashSet<String>();
	protected Set<String> listenerPros = new LinkedHashSet<String>();


	protected String defaultSerialId = "private static final long serialVersionUID = 1L;\n";

	protected String defaultsImplements = " java.io.Serializable";

	protected Map<String, EnhEntitiyAttr> enhEntitiyAttrMap = new LinkedHashMap<String, EnhEntitiyAttr>();

}
