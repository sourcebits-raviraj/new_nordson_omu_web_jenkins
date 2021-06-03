package com.nordson.utilities;

import java.io.FileInputStream;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import com.nordson.testCases.BaseClass;

public class LocaleMSgReader extends BaseClass {
	
	private static ResourceBundle rsc=null;
	private static Locale locale=null;
	 static Language lngu=new Language();
	 static ReadConfig rcf=new ReadConfig();
	 
	
	public static String getString(String code) throws Exception
	{
		//get locale object
		locale=new Locale(rcf.getLocaleLanguage());
		
		//get resource Bundle
		
		  FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+
		  "/src/test/resources/ApplicationResources_"+rcf.getLocaleLanguage()+
		  ".properties"); 
		  rsc = new PropertyResourceBundle(fis);
		 
		// rsc = PropertyResourceBundle.getBundle("ApplicationResources",locale);
		
		//get the message
		String message = rsc.getString(code);
		return message;
				
	}
	

}
