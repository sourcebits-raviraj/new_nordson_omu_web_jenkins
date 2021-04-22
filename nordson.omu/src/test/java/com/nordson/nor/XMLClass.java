package com.nordson.nor;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLClass {

	public String XMLParser(String XMltagValtobefetched) throws SAXException, IOException
	{
		 String key="";
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		try{
		       DocumentBuilder buildr=factory.newDocumentBuilder();
		       Document doc=buildr.parse(System.getProperty("user.dir") + "\\src\\test\\java\\com\\nordson\\BDGTest\\Settings\\RecipeCurrent.xml");
		       XPathFactory xPathfactory = XPathFactory.newInstance();
		       XPath xpath = xPathfactory.newXPath();
		       XPathExpression expr = xpath.compile("//RecipeData/"+XMltagValtobefetched+"[@Value]");
		       NodeList nl = (NodeList)
		    expr.evaluate(doc, XPathConstants.NODESET);

		       for (int i = 0; i < nl.getLength(); i++)
		       {
		           Node currentItem = nl.item(i);
		            key = currentItem.getAttributes().getNamedItem("Value").getNodeValue();
		          		       }
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}     
		
		 return key;

  }

}