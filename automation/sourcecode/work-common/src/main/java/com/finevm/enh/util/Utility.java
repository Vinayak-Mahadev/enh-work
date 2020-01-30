package com.finevm.enh.util;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

public class Utility {

	/**
	 * <pre>
	 * <b>Algorithm</b><br>
	 * 	Formating xml as per industry standards...
	 * </pre>
	 * @author Vinayak Mahadev
	 * @param xml
	 * @return
	 */
	public static String formatXml(String xml) 
	{
		String responce = null;
		try 
		{
			final InputSource src = new InputSource(new StringReader(xml));
			final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src).getDocumentElement();
			final Boolean keepDeclaration = Boolean.valueOf(xml.startsWith("<?xml"));
			
			//May need this: System.setProperty(DOMImplementationRegistry.PROPERTY,"com.sun.org.apache.xerces.internal.dom.DOMImplementationSourceImpl");
			final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
			final DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
			final LSSerializer writer = impl.createLSSerializer();
			
			writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE); // Set this to true if the output needs to be beautified.
			writer.getDomConfig().setParameter("xml-declaration", keepDeclaration); // Set this to true if the declaration is needed to be outputted.
			
			responce = writer.writeToString(document);
		} 
		catch (Exception e) 
		{
			responce = null;
			throw new RuntimeException(e);
		}
		return responce;
	}
	
	
	public static String getTagValue(String xml, String tagName){
		String responce = null;	
		responce =  xml.split("<"+tagName+">")[1].split("</"+tagName+">")[0];
		return responce;
	}
	
	
}
