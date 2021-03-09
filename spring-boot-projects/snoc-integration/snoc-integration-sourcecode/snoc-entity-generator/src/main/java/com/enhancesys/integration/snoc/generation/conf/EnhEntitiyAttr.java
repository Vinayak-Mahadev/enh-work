package com.enhancesys.integration.snoc.generation.conf;

import java.util.LinkedHashSet;
import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EnhEntitiyAttr {

	protected final String name;

	protected String entityName;

	protected String type;

	protected String dataType;

	protected String dataTypescope;

	protected String fieldFullName;

	protected Set<String> entityAttrAnnotations = new LinkedHashSet<String>();

	protected String columnName;

	protected String mapedBy;

	protected String fetchType;

	protected String optionalType;

	protected String setterAndGetter;

	protected boolean isNullable;

	protected Node entityAttrNode;

	protected NodeList javaAttributes;

	protected String typeId;

	protected boolean isOneTomany;

	protected boolean isManyToOne;

	protected String listenerProps = "";

	private String setter = "";
	private String getter = "";
	private String enquirer = "";
	private String adder = "";
	private String remover = "";
	private String idAdapter = "";

	public EnhEntitiyAttr(String entityName, Node entityAttrNode) throws Exception {
		this.entityName = entityName;
		this.entityAttrNode = entityAttrNode;
		type = entityAttrNode.getNodeName().trim();
		name = entityAttrNode.getAttributes().getNamedItem("name").getNodeValue().trim();

		if (entityAttrNode.getAttributes().getNamedItem("mapped-by") != null
				&& entityAttrNode.getAttributes().getNamedItem("mapped-by").getNodeValue() != null)
			mapedBy = entityAttrNode.getAttributes().getNamedItem("mapped-by").getNodeValue().trim();

		if (entityAttrNode.getAttributes().getNamedItem("fetch") != null
				&& entityAttrNode.getAttributes().getNamedItem("fetch").getNodeValue() != null)
			fetchType = entityAttrNode.getAttributes().getNamedItem("fetch").getNodeValue().trim();
		if (entityAttrNode.getAttributes().getNamedItem("optional") != null
				&& entityAttrNode.getAttributes().getNamedItem("optional").getNodeValue() != null)
			optionalType = entityAttrNode.getAttributes().getNamedItem("optional").getNodeValue().trim();
		init();
	}

	private void init() throws Exception {
		Element element = null;
		NodeList javaAnnotations = null;
		Node childNode = null;
		NodeList childNodes = null;
		String temporal = null;
		String columnAnnotation = null;
		String joinColumn = null;
		String generatedValue = null;
		String sequenceGenerator = null;
		try {
			element = (Element) entityAttrNode;
			childNodes = element.getChildNodes();

			for (int root = 0; root < childNodes.getLength(); root++) {
				childNode = childNodes.item(root);

				if ("java-annotations".equals(childNode.getNodeName()))
					javaAnnotations = childNode.getChildNodes();
				else if ("java-attributes".equals(childNode.getNodeName())) {
					javaAttributes = childNode.getChildNodes();

					NamedNodeMap map = childNode.getAttributes();
					if (map.getNamedItem("type") != null && !map.getNamedItem("type").getNodeValue().isEmpty())
						dataType = map.getNamedItem("type").getNodeValue().trim();
					if (map.getNamedItem("scope") != null && !map.getNamedItem("scope").getNodeValue().isEmpty())
						dataTypescope = map.getNamedItem("scope").getNodeValue().trim();
					else
						dataTypescope = "protected";

					if ("one-to-many".equals(type)) {
						fieldFullName = dataTypescope + " " + "java.util.Set<" + EntityGeneration.getPACKAGE_VALUE()
						+ "." + dataType + "> " + name + " = new java.util.HashSet<"
						+ EntityGeneration.getPACKAGE_VALUE() + "." + dataType + ">();";
						isOneTomany = true;
					} else if ("many-to-one".equals(type)) {
						isManyToOne = true;
						if (EntityGeneration.CUSTOM_DATA_TYPES.contains(dataType)) {

							// System.out.println(EntityGeneration.CUSTOM_DATA_TYPES.contains(dataType));
							fieldFullName = dataTypescope + " " + EntityGeneration.getPACKAGE_VALUE() + "." + dataType
									+ " " + name + ";";
						} else
							fieldFullName = dataTypescope + " " + dataType + " " + name + ";";
						// System.out.println(fieldFullName);
					} else
						fieldFullName = dataTypescope + " " + dataType + " " + name + ";";
				} else if ("column".equals(childNode.getNodeName())) {
					NamedNodeMap map = childNode.getAttributes();
					String name = null;
					String nullable = null;
					if (map.getNamedItem("nullable") != null && !map.getNamedItem("nullable").getNodeValue().isEmpty())
						nullable = map.getNamedItem("nullable").getNodeValue().trim();
					if (map.getNamedItem("name") != null && !map.getNamedItem("name").getNodeValue().isEmpty())
						name = map.getNamedItem("name").getNodeValue().trim();

					if (nullable != null && name != null)
						columnAnnotation = "@Column(name=\"" + name + "\"" + " , nullable=" + nullable + ")";
					else if (name != null)
						columnAnnotation = "@Column(name=\"" + name + "\")";
				} else if ("temporal".equals(childNode.getNodeName())) {
					temporal = "@Temporal(TemporalType.TIMESTAMP)";
				} else if ("generated-value".equals(childNode.getNodeName())) {
					NamedNodeMap map = childNode.getAttributes();

					generatedValue = "@GeneratedValue(generator=\"" + map.getNamedItem("generator").getNodeValue()
							+ "\", strategy=GenerationType." + map.getNamedItem("strategy").getNodeValue() + ")";
				} else if ("sequence-generator".equals(childNode.getNodeName())) {
					NamedNodeMap map = childNode.getAttributes();
					sequenceGenerator = "@SequenceGenerator(allocationSize="
							+ map.getNamedItem("allocation-size").getNodeValue() + ", initialValue="
							+ map.getNamedItem("initial-value").getNodeValue() + ", name=\""
							+ map.getNamedItem("name").getNodeValue() + "\", sequenceName=\""
							+ map.getNamedItem("sequence-name").getNodeValue() + "\")";
				} else if ("join-column".equals(childNode.getNodeName())) {
					NamedNodeMap map = childNode.getAttributes();
					String name = null;
					String nullable = null;
					if (map.getNamedItem("nullable") != null && !map.getNamedItem("nullable").getNodeValue().isEmpty())
						nullable = map.getNamedItem("nullable").getNodeValue().trim();
					if (map.getNamedItem("name") != null && !map.getNamedItem("name").getNodeValue().isEmpty())
						name = map.getNamedItem("name").getNodeValue().trim();

					if (nullable != null && name != null)
						joinColumn = ("@JoinColumn(name=\"" + name + "\", nullable=" + nullable + ")");
					else
						joinColumn = ("@JoinColumn(name=\"" + name + "\")");
				}
			}

			if (javaAnnotations != null)
				for (int ann = 0; ann < javaAnnotations.getLength(); ann++)
					if (javaAnnotations.item(ann).getNodeType() != Node.COMMENT_NODE
					&& !javaAnnotations.item(ann).getTextContent().trim().isEmpty())
						entityAttrAnnotations.add(
								javaAnnotations.item(ann).getTextContent().trim().replaceAll("<java-annotation>", "")
								.replaceAll("</java-annotation>", "").replaceAll("\n", ""));

			if (type.equals("id")) {
				typeId = name;
				entityAttrAnnotations.add("@Id");
			}
			if (type.equals("basic"))
				entityAttrAnnotations.add("@Basic");
			if (type.equals("version"))
				entityAttrAnnotations.add("@Version");
			if (type.equals("one-to-many"))
				entityAttrAnnotations
				.add("@OneToMany(fetch=FetchType." + fetchType + ", mappedBy=\"" + mapedBy + "\")");
			if (type.equals("many-to-one")) {
				if (optionalType != null)
					entityAttrAnnotations
					.add("@ManyToOne(fetch=FetchType." + fetchType + ", optional = " + optionalType + ")");
				else
					entityAttrAnnotations.add("@ManyToOne(fetch=FetchType." + fetchType + ")");

			}
			if (columnAnnotation != null)
				entityAttrAnnotations.add(columnAnnotation);
			if (temporal != null)
				entityAttrAnnotations.add(temporal);
			if (joinColumn != null)
				entityAttrAnnotations.add(joinColumn);
			if (generatedValue != null)
				entityAttrAnnotations.add(generatedValue);
			if (sequenceGenerator != null)
				entityAttrAnnotations.add(sequenceGenerator);
			prepareMethods("");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	private String prepareMethods(String choice) {

		String type = dataType;

		if (EntityGeneration.CUSTOM_DATA_TYPES.contains(dataType))
			type = EntityGeneration.getPACKAGE_VALUE() + "." + dataType;

		for (int attr = 0; attr < javaAttributes.getLength(); attr++) {
			Node node = javaAttributes.item(attr);
			if (node.getNodeName().equals("getter")) {
				getter = "";
				String getterAnnotaions = "";
				if (node.getChildNodes() != null) {
					for (int x = 0; x < node.getChildNodes().getLength(); x++) {
						Node nodeX = node.getChildNodes().item(x);
						if (nodeX != null && nodeX.getNodeType() != Node.COMMENT_NODE
								&& nodeX.getNodeName().equals("java-annotations")) {
							NodeList annoList = nodeX.getChildNodes();
							if (annoList != null)
								for (int anno = 0; anno < annoList.getLength(); anno++)
									if (annoList.item(anno) != null
									&& (annoList.item(anno).getNodeType() != Node.COMMENT_NODE)
									&& annoList.item(anno).getTextContent() != null
									&& !annoList.item(anno).getTextContent().trim().isEmpty())
										setGetter(getGetter() + "   "
												+ annoList.item(anno).getTextContent().trim().replaceAll("\t", "")
												+ "\n");
						}
					}
				}

				if (!EntityGeneration.CUSTOM_DATA_TYPES.contains(dataType) || isManyToOne)

					setGetter(getGetter() + "\n" + "   public " + type + " get" + startKeyWithUpperCase(name) + "()\n"
							+ "   {\n" + "     return this." + name + ";\n" + "   }");
				else
					setGetter(getGetter() + "   \n" + "   public java.util.Set<" + type + "> get"
							+ startKeyWithUpperCase(name) + "()\n" + "   {\n" + "     this." + name + ".size();\n"
							+ "     return java.util.Collections.unmodifiableSet(this." + name + ");\n" + "   }");

				setGetter("   " + getterAnnotaions + getGetter());
			}

			if (node.getNodeName().equals("setter")) {
				// System.out.println(entityName);
				// if(!EntityGeneration.ENABLE_LISTENERS.containsKey(dataType))
				if (EntityGeneration.ENABLE_LISTENERS.get(dataType) == null
						&& EntityGeneration.CUSTOM_DATA_TYPES.contains(entityName)
						&& entityName.toLowerCase().contains(name.toLowerCase())) {
					String temp = EntityGeneration.ENABLE_LISTENERS_PARENT_PROPS.get(entityName);
					// System.out.println(temp);
					if (temp != null)
						setSetter("\n" + "   public void set" + startKeyWithUpperCase(name) + "(" + type + " arg)\n"
								+ "   {\n" + "       if(this." + name + " == arg)\n" + "         return;\n"
								+ "       \n" + "       " + type + " old" + dataType + " = this." + name + ";\n"
								+ "       this." + name + " = null;\n" + "       if(old" + dataType + " != null)\n"
								+ "         old" + dataType + ".remove" + temp.substring(0, temp.length() - 1)
								+ "(this);\n" + "       \n" + "       this." + name + " = arg;\n" + "       \n"
								+ "       if(this." + name + " != null)\n" + "         this." + name + ".add"
								+ temp.substring(0, temp.length() - 1) + "(this);\n" + "   }\n" + "");
				} else
					setSetter("   \n" + "   public void set" + startKeyWithUpperCase(name) + "(" + type + " arg)\n"
							+ "   {\n" + "     if(this." + name + " == arg)\n" + "       return;\n" + "     this."
							+ name + " = arg;\n" + "   }");
			}
			if (node.getNodeName().equals("enquirer"))
				setEnquirer("   public Boolean has"
						+ startKeyWithUpperCase(name).substring(0, startKeyWithUpperCase(name).length() - 1) + "("
						+ type + " arg)\n" + "   {\n" + "     return this." + name + ".contains(arg);\n" + "   }\n");

			if (node.getNodeName().equals("adder"))
				setAdder("   public void add"
						+ startKeyWithUpperCase(name).substring(0, startKeyWithUpperCase(name).length() - 1) + "("
						+ type + " arg)\n" + "   {\n" + "     if(arg == null || this." + name + ".contains(arg))\n"
						+ "       return;\n" + "     this." + name + ".add(arg);\n" + "     arg.set" + entityName
						+ "(this);\n" + "   }\n");

			if (node.getNodeName().equals("remover"))
				setRemover("   public void remove"
						+ startKeyWithUpperCase(name).substring(0, startKeyWithUpperCase(name).length() - 1) + "("
						+ type + " arg)\n" + "   {\n" + "     if(arg == null || !this." + name + ".contains(arg))\n"
						+ "       return;\n" + "     this." + name + ".remove(arg);\n" + "     arg.set" + entityName
						+ "(this);\n" + "   }\n");
		}
		if (typeId != null && !typeId.trim().isEmpty()) {
			setIdAdapter("   @XmlTransient\n" + "   @JsonIgnoreType\n"
					+ "   public static class IDAdapter extends XmlAdapter<Long," + entityName + ">\n" + "   {\n"
					+ "     @Override\n" + "     public Long marshal(" + entityName + " "
					+ startKeyWithLowerCase(entityName) + ") throws Exception \n" + "     {\n" + "       return "
					+ startKeyWithLowerCase(entityName) + ".get" + startKeyWithUpperCase(typeId) + "();\n" + "     }\n"
					+ "     \n" + "     @Override\n" + "     public " + entityName
					+ " unmarshal(Long arg) throws Exception \n" + "     {\n" + "       " + entityName + " "
					+ startKeyWithLowerCase(entityName) + " = new " + entityName + "();\n" + "       "
					+ startKeyWithLowerCase(entityName) + ".set" + startKeyWithUpperCase(typeId) + "(arg);\n"
					+ "       return " + startKeyWithLowerCase(entityName) + ";\n" + "     }\n" + "   \n" + "   }\n"
					+ "");

		}
		if (isOneTomany || isManyToOne) {
			String startFromFirstUpper = findOutNextUpperCase(entityName);
			String listener = startFromFirstUpper + "Listener";
			String nameWithUpper = startKeyWithUpperCase(name);

			// EntityGeneration.ENABLE_LISTENERS.put(EntityGeneration.getPACKAGE_VALUE()+"."+entityName+"."+startKeyWithUpperCase(name)+"Listener.class");
			if (isOneTomany)
				EntityGeneration.ENABLE_LISTENERS.put(dataType, "@EntityListeners(value={"
						+ EntityGeneration.getPACKAGE_VALUE() + "." + entityName + "." + listener + ".class" + "})");
			// if(isManyToOne)
			EntityGeneration.ENABLE_LISTENERS_PARENT_PROPS.put(dataType, nameWithUpper);

			String props = "   @XmlTransient\n" + "   @JsonIgnoreType\n" + "   public static class " + listener + "\n"
					+ "   {\n" + "     @PrePersist\n" + "     public void onPersist(" + type + " arg)\n" + "     {\n"
					+ "       " + entityName + " obj = arg.get" + entityName + "();\n" + "       obj._add"
					+ nameWithUpper + "(arg);\n" + "     }\n" + "     \n" + "     @PreRemove\n"
					+ "     public void onRemove(" + type + " arg)\n" + "     {\n" + "       " + entityName
					+ " obj = arg.get" + entityName + "();\n" + "       obj._remove" + nameWithUpper + "(arg);\n"
					+ "     }\n" + "   \n" + "   }\n" + "   \n" + "   void _add" + nameWithUpper + "(" + type
					+ " arg)\n" + "   {\n" + "     this." + name + ".add(arg);\n" + "   }\n" + "   \n"
					+ "   void _remove" + nameWithUpper + "(" + type + " arg)\n" + "   {\n" + "     this." + name
					+ ".remove(arg);\n" + "   }\n" + "";
			if (isOneTomany)
				setListenerProps(props);

		}

		return "";
	}

	public EnhEntitiyAttr startWritingJavaFile() throws Exception {
		init();
		return this;
	}

	public String getentityFieldsMethods(String choice) throws Exception {
		// init();
		// prepareMethods(choice);
		if (choice.equals("idAdapter"))
			return idAdapter;
		if (choice.equals("setter"))
			return setter;
		if (choice.equals("getter"))
			return getter;
		if (choice.equals("enquirer"))
			return enquirer;
		if (choice.equals("adder"))
			return adder;
		if (choice.equals("remover"))
			return remover;
		else
			return "";
		// return prepareMethods(choice);
	}

	public String getentityFields() {
		String temp = "";
		for (String string : entityAttrAnnotations)
			temp = temp + ("   " + string + "\n");
		return temp + ("   " + fieldFullName + "\n\n");
	}

	public String getSetter() {
		return setter;
	}

	public void setSetter(String setter) {
		this.setter = setter;
	}

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getEnquirer() {
		return enquirer;
	}

	public void setEnquirer(String enquirer) {
		this.enquirer = enquirer;
	}

	public String getAdder() {
		return adder;
	}

	public void setAdder(String adder) {
		this.adder = adder;
	}

	public String getRemover() {
		return remover;
	}

	public void setRemover(String remover) {
		this.remover = remover;
	}

	public String getIdAdapter() {
		return idAdapter;
	}

	public void setIdAdapter(String idAdapter) {
		this.idAdapter = idAdapter;
	}

	public String getName() {
		return name;
	}

	public String getEntityName() {
		return entityName;
	}

	public String getType() {
		return type;
	}

	public String getDataType() {
		return dataType;
	}

	public String getDataTypescope() {
		return dataTypescope;
	}

	public String getFieldFullName() {
		return fieldFullName;
	}

	public Set<String> getEntityAttrAnnotations() {
		return entityAttrAnnotations;
	}

	public String getColumnName() {
		return columnName;
	}

	public String getMapedBy() {
		return mapedBy;
	}

	public String getFetchType() {
		return fetchType;
	}

	public String getSetterAndGetter() {
		return setterAndGetter;
	}

	public boolean isNullable() {
		return isNullable;
	}

	public Node getEntityAttrNode() {
		return entityAttrNode;
	}

	public NodeList getJavaAttributes() {
		return javaAttributes;
	}

	public String getTypeId() {
		return typeId;
	}

	public String getListenerProps() {
		return listenerProps;
	}

	public void setListenerProps(String listenerProps) {
		this.listenerProps = listenerProps;
	}

	public String startKeyWithUpperCase(String key) {
		if (key != null && !key.trim().isEmpty())
			return (key.charAt(0) + "").toUpperCase() + key.substring(1, key.length());
		return key;
	}

	public String startKeyWithLowerCase(String key) {
		if (key != null && !key.trim().isEmpty())
			return (key.charAt(0) + "").toLowerCase() + key.substring(1, key.length());
		return key;
	}

	public String findOutNextUpperCase(final String key) {
		String temp = "";
		boolean status = false;
		if (key != null && !key.trim().isEmpty()) {
			for (int i = 0; i < key.length(); i++) {
				if (Character.isUpperCase(key.charAt(i)))
					status = true;
				if (status) {
					temp = temp + key.charAt(i);
					status = true;
				}
			}
			return temp;
		}
		return key;
	}
}
