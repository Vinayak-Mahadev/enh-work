package com.enhancesys.integration.snoc.generation.conf;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EntityGeneration 
{

	public static void main(String[] args) {
		EntityGeneration.generateEntity(null, null, null);
	}
	public static void generateEntity(String ormFilePath, String outputPath, String goal) 
	{
//		ormFilePath = "E:\\interface\\work\\enh-work\\daily_works\\2021-03\\Test\\orm-integration.xml";
//		outputPath = "E:\\interface\\work\\enh-work\\daily_works\\2021-03\\Test\\classes";
		new EntityGeneration(ormFilePath, outputPath, "generate java files");
		//								new EntityGeneration(ormFilePath, outputPath, "clean java files");
System.out.println(ENABLE_LISTENERS_PARENT_PROPS);
//new EntityGeneration(ormFilePath, outputPath, "generate java files");
	}


	private EntityGeneration(String ormFilePath, String outputPath, String goal) 
	{
		boolean generateFile = false;
		try 
		{
			//set default data types into map
			for (String string : DEFAULT_DATA_TYPES) 
			{
				DATA_TYPES.put(string, "default");
			}

			init(ormFilePath, outputPath);

			generateFile = validatePath(outputPath, goal);

			if(generateFile)
				if(startJavaFileWriting())
					System.out.println("Successfully generated java files...");			
				else
					System.out.println("Failed at generating java files...");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private boolean startJavaFileWriting() throws Exception 
	{
		for (String entitiy : enhEntitiySet.keySet()) 
		{
			//			System.out.println(entitiy + " :: " + enhEntitiySet.get(entitiy).getEntityName());
			if(!enhEntitiySet.get(entitiy).startWritingJavaFile())
				return false;
		}
//		System.out.println(ENABLE_LISTENERS);
		return true;
	}
	private boolean validatePath(String outputPath, String goal) throws Exception
	{
		if(goal != null && (goal.equals("generate java files") || goal.equals("clean java files")))
		{
			File file = new File(outputPath);
			if(file.isDirectory())
			{
				file = new File(file.getAbsolutePath() + "/" + PACKAGE_VALUE.replaceAll("\\.", "/"));
				if(file.isDirectory()) 
				{
					String path = outputPath + "/";
					for (int i = 0; i < PACKAGE_VALUE.replaceAll("\\.", ";").split(";").length - 1 ; i ++) 
					{
						path = 	path + PACKAGE_VALUE.replaceAll("\\.", ";").split(";")[i] + "/";
					}
					file = new File(path);
					if(file.isDirectory())
						deleteDir(file);
					System.out.println("Deleted path :: " + file.getAbsolutePath());
				}
				if(goal.equals("generate java files"))
				{
					file = new File(outputPath + "/" + PACKAGE_VALUE.replaceAll("\\.", "/"));
					if(!file.mkdir())
						Files.createDirectories(Paths.get(file.getAbsolutePath()));
					System.out.println("Recreated path :: " + file.getAbsolutePath());
					return true;
				}
			}
			else
				throw new Exception("outputPath is not directory :: " + outputPath);

		}
		else
			throw new Exception("plz provide valid goal :: " + goal);
		return false;
	}

	private void init(String ormFilePath, String outputPath) throws Exception
	{
		File ormFile = null;
		DocumentBuilderFactory dbf = null;
		DocumentBuilder documentBuilder = null;
		Document ormDoc = null;
		try 
		{ 
			ormFile = new File(ormFilePath);  
			//an instance of factory that gives a document builder  
			dbf = DocumentBuilderFactory.newInstance();  
			//an instance of builder to parse the specified xml file  
			documentBuilder = dbf.newDocumentBuilder();  
			ormDoc = documentBuilder.parse(ormFile);
			ormDoc.getDocumentElement().normalize();
			//			System.out.println(ormFile + " : " +  ormDoc);
			//			System.out.println("Root element: " + ormDoc.getDocumentElement().getNodeName());

			NodeList packageList = ormDoc.getElementsByTagName("package");
			for (int i = 0 ; i < packageList.getLength(); i++) 
			{
				Node packageNode = packageList.item(i);
				setPACKAGE_VALUE(packageNode.getTextContent().trim().toLowerCase());
			}

			NodeList entitiyList = ormDoc.getElementsByTagName("entity");  

			for (int i = 0 ; i < entitiyList.getLength(); i++) 
			{
				Node entityNode = entitiyList.item(i);
				NamedNodeMap namedNodeMap = entityNode.getAttributes();
				Node node = namedNodeMap.getNamedItem("class");
				CUSTOM_DATA_TYPES.add(node.getNodeValue().trim());			//add custom data types into map
				DATA_TYPES.put(node.getNodeValue().trim(), "custom");				
				javaFileNames.add(node.getNodeValue().trim() + FILE_EXTENTION);
				enhEntitiySet.put(node.getNodeValue().trim(), new EnhEntitiy(entityNode, outputPath + "/" + PACKAGE_VALUE.replaceAll("\\.", "/")));
			}

		}
		finally 
		{

		}
	}



	static void setPACKAGE_VALUE(String pack)
	{
		PACKAGE_VALUE = pack;
	}
	public static String getPACKAGE_VALUE()
	{
		return PACKAGE_VALUE;
	}

	private static void deleteDir(File file) 
	{
		File[] contents = file.listFiles();
		if (contents != null) {
			for (File f : contents) 
			{
				System.out.println("File deleted :: " + f.getAbsolutePath());
				deleteDir(f);
			}
		}
		file.delete();
	}


	protected final static Set<String> DEFAULT_DATA_TYPES = new LinkedHashSet<String>(Arrays.asList("Long,String,Date,byte[]".split(",")));

	protected final static Set<String> CUSTOM_DATA_TYPES = new LinkedHashSet<String>();

	protected final List<String> javaFileNames = new LinkedList<String>();

	protected final Map<String,EnhEntitiy> enhEntitiySet = new LinkedHashMap<String, EnhEntitiy>();

	protected final static String FILE_EXTENTION = ".java";

	protected  static String PACKAGE_VALUE = null;

	public final static String DEFAULT_IMPORTS = "\n"
			+ "import java.util.Date;\n"
			+ "\n"
			+ "import javax.persistence.Access;\n"
			+ "import javax.persistence.AccessType;\n"
			+ "import javax.persistence.Basic;\n"
			+ "import javax.persistence.Column;\n"
			+ "import javax.persistence.Entity;\n"
			+ "import javax.persistence.EntityListeners;\n"
			+ "import javax.persistence.FetchType;\n"
			+ "import javax.persistence.GeneratedValue;\n"
			+ "import javax.persistence.GenerationType;\n"
			+ "import javax.persistence.Id;\n"
			+ "import javax.persistence.JoinColumn;\n"
			+ "import javax.persistence.Lob;\n"
			+ "import javax.persistence.ManyToOne;\n"
			+ "import javax.persistence.OneToMany;\n"
			+ "import javax.persistence.PrePersist;\n"
			+ "import javax.persistence.PreRemove;\n"
			+ "import javax.persistence.SequenceGenerator;\n"
			+ "import javax.persistence.Table;\n"
			+ "import javax.persistence.Temporal;\n"
			+ "import javax.persistence.TemporalType;\n"
			+ "import javax.persistence.Version;\n"
			+ "import javax.xml.bind.annotation.XmlAccessType;\n"
			+ "import javax.xml.bind.annotation.XmlAccessorType;\n"
			+ "import javax.xml.bind.annotation.XmlElement;\n"
			+ "import javax.xml.bind.annotation.XmlRootElement;\n"
			+ "import javax.xml.bind.annotation.XmlTransient;\n"
			+ "import javax.xml.bind.annotation.XmlType;\n"
			+ "import javax.xml.bind.annotation.adapters.XmlAdapter;\n"
			+ "import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;\n"
			+ "\n"
			+ "import org.hibernate.envers.AuditMappedBy;\n"
			+ "import org.hibernate.envers.Audited;\n"
			+ "import org.hibernate.envers.RelationTargetAuditMode;\n"
			+ "\n"
			+ "\n\n";
	//			+ "import org.hibernate.search.annotations.*;\n\n";

	protected final static Map<String, String> DATA_TYPES = new LinkedHashMap<String, String>();

	protected final static Map<String,String> ENABLE_LISTENERS = new LinkedHashMap<String, String>();
	protected final static Map<String,String> ENABLE_LISTENERS_PARENT_PROPS = new LinkedHashMap<String, String>();
}
