package enh.team.interfaces.soapservice.beans;

import java.io.Serializable;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="attachmentBean")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "attachment")
public class AttachmentBean implements Serializable
{

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "name", required = true)
	private String name;

	@XmlElement(name = "type")
	private String type;

	@XmlElement(name = "value")
	private DataHandler value;

	public AttachmentBean()
	{

	}
	public AttachmentBean(String name) {
		super();
		this.name = name;
	}

	public AttachmentBean(String name, DataHandler value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getNameWithExtention() {
		if(name != null && type != null)
			return name + "." + type;
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DataHandler getValue() {
		return value;
	}

	public void setValue(DataHandler value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
