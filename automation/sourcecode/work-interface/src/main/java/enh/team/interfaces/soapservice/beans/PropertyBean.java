package enh.team.interfaces.soapservice.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="PropertyBean")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "property")
public class PropertyBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@XmlElement(name = "key", required = true)
	private String key;

	@XmlElement(name = "value")
	private String value;
	
	@XmlElement(name = "objValue")
	private Object objValue;


	public PropertyBean() {
		super();
	}

	public PropertyBean(String key, Object value) {
		super();
		this.key = key;
		this.objValue = value;
		this.value = value.toString();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public String getValueAsString() 
	{
		if(value != null && value instanceof String)
			return new String(value.toString());
		if(value != null)
			return value.toString();
		return null;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Object getObjValue() {
		return objValue;
	}

	public void setObjValue(Object objValue) {
		this.objValue = objValue;
	}

	
}
