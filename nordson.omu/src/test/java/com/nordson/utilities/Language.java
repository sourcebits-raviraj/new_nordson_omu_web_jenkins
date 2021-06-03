package com.nordson.utilities;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nordson.pageObjects.LandingPage;
import com.nordson.testCases.BaseClass;

public class Language {
	
	ActionMethods Am=new ActionMethods();
	public static String langague ="";
	LandingPage lp=null;
	
	@Parameters("language")
	@Test
	public static void setLanguage(String lng)
	{
		Language.langague=lng;
	}
	
	public String getLanguage() {
		
		String Locallngpath="./LanguageResources/LocaleTest_"+langague+".properties";
		return Locallngpath;
	}
	
	public void selectLanguage(WebDriver driver,String langague) throws Exception
	{
		lp=new LandingPage(driver);
		 switch (langague) {

	        case "English":
	            Am.sleepTime(1000);
	            lp.clickLanguage();
	            Am.sleepTime(1000);
	            lp.LanaguageDropdowns("0");
	            System.out.println("English language selected");
	            break;

	        case "German":
	            Am.sleepTime(1000);
	            lp.clickLanguage();
	            Am.sleepTime(1000);
	           lp.LanaguageDropdowns("1");
	            System.out.println("Deutsch language selected");
	            break;

	        case "Spanish":
	            Am.sleepTime(1000);
	            lp.clickLanguage();
	            Am.sleepTime(1000);
	            lp.LanaguageDropdowns("2");
	            System.out.println("Espanol language selected");
	            break;

	        case "France":
	            Am.sleepTime(1000);
	            lp.clickLanguage();
	            Am.sleepTime(1000);
	            lp.LanaguageDropdowns("3");
	            System.out.println("Francias language selected");
	            break;

	        case "Italian":
	            Am.sleepTime(1000);
	            lp.clickLanguage();
	            Am.sleepTime(1000);
	            lp.LanaguageDropdowns("4");
	            System.out.println("Italiano language selected");
	            break;

	        case "Mandarin":
	            Am.sleepTime(1000);
	            lp.clickLanguage();
	            Am.sleepTime(1000);
	            lp.LanaguageDropdowns("5");
	            System.out.println("Chinese language selected");
	            break;

	        case "Japanese":
	            Am.sleepTime(1000);
	            lp.clickLanguage();
	            Am.sleepTime(1000);
	            lp.LanaguageDropdowns("6");
	            System.out.println("Japanse language selected");
	            break;

	        case "Dutch":
	            Am.sleepTime(1000);
	            lp.clickLanguage();
	            Am.sleepTime(1000);
	            lp.LanaguageDropdowns("7");
	            System.out.println("Dutch language selected");
	            break;

	        case "Portuguese":
	            Am.sleepTime(1000);
	            lp.clickLanguage();
	            Am.sleepTime(1000);
	            lp.LanaguageDropdowns("8");
	            System.out.println("Portuguse language selected");
	            break;

	 

	        }
	}

}
