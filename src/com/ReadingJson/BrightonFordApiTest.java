package com.ReadingJson;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.gson.JsonArray;

public class BrightonFordApiTest
{
	public static WebDriver driver;
	public static String urlToFetch; 
	public static String vinToFetch;
	public static void main(String[] args) throws JSONException, Throwable
	{
		String url = "http://agiledealerwebapi.azurewebsites.net/api/AgileDealer/GetVin";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in .readLine()) != null) 
        {
        	response.append(inputLine);
        }
        in .close();
            
        System.out.println("****************************");
        System.out.println("Json response we got is: "+response.toString());
        System.out.println("****************************");
            
            
        JSONObject obj_JSONObject = new JSONObject(response.toString());
        
        //result after Reading JSON Response
            
        System.out.println("WebsiteUrl-- " + obj_JSONObject.getString("WebsiteUrl"));
        String urlToFetch = obj_JSONObject.getString("WebsiteUrl");
        System.out.println("After converting in to string URL: "+urlToFetch);
     
        JSONArray obj_JSONArray = obj_JSONObject.getJSONArray("VINS");
        String vinsNo = "obj_JSONArray";
        //int count = obj_JSONArray.length();
        System.out.println("Vin-- "+obj_JSONArray);
        
        
        ArrayList<String> list = new ArrayList<String>();
        System.out.println("Now running for loop");
        for(int i=0; i<obj_JSONArray.length(); i++)
        {
        	//Fetching all VIN one by one
        	vinToFetch = obj_JSONArray.getString(i);
        	System.out.println(vinToFetch);
        
        
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            
         
            
            
           
		System.setProperty("webdriver.chrome.driver", "E:\\Workspace\\GatorFord\\Driver\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		// Getting Dynamic url
		driver.navigate().to(urlToFetch+"inventory?keywords="+vinToFetch);
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		String b_Title = driver.getTitle();
		System.out.println("Title is: "+b_Title);
		Thread.sleep(3000);
		System.out.println("Navigated to Page");
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='btnInfo']")).click();
		System.out.println("clicked on button");
		Thread.sleep(3000);
		driver.findElement(By.id("btnSticker")).click();
		System.out.println("clicked on btnSticker");
		Thread.sleep(9000);
		
		String a = driver.getTitle();
		System.out.println("Current Tab title is: "+a);
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    //Thread.sleep(9000);
	    String b = driver.getTitle();
	   // Thread.sleep(9000);
		System.out.println("New Tab title is: "+b);
		
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		
        robot.keyPress(KeyEvent.VK_ADD);
        Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_ADD);
       Thread.sleep(1000);
       
        robot.keyPress(KeyEvent.VK_ADD);
        Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_ADD);
       Thread.sleep(1000);
      
        robot.keyPress(KeyEvent.VK_ADD);
       Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_ADD);
       Thread.sleep(1000);
        
        robot.keyPress(KeyEvent.VK_ADD);
       Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_ADD);
       Thread.sleep(3000);
        
        robot.keyPress(KeyEvent.VK_ADD);
        Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_ADD);
        Thread.sleep(1000);
        
        robot.keyPress(KeyEvent.VK_ADD);
        Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_ADD);
        Thread.sleep(1000);
               
        robot.keyRelease(KeyEvent.VK_CONTROL);
        String c = driver.getTitle();
        Thread.sleep(1000);
        System.out.println("pdf file title is: "+c);
        Thread.sleep(3000);
        //Scrolling down
        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        Thread.sleep(1000);
        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
        
        System.out.println("Scrolled down the screen");

        Thread.sleep(9000);
        System.out.println("It starts zooming in");
        Thread.sleep(9000);
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //Screenshot name will be as per VIN no.
        FileUtils.copyFile(screenshot, new File("E:\\Workspace\\GatorFord\\ScreenShot\\"+vinToFetch+".png"));
        Thread.sleep(3000);
        System.out.println("taking screenshot the url");
        driver.quit();  
      }
	}
}
