package com.musicbox.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class FileUtil {
	
 private static Log logger = LogFactory.getLog(FileUtil.class);
  public static HashMap getFileData(String filePath){
	HashMap retHm = new HashMap();
	
	try { 
			
	      // String csvFile = "D:\\Laxmi\\eRoster\\SummaryFiles\\2012_08_Summary_Data_Test.csv";

	       //create BufferedReader to read csv file
	       BufferedReader br = new BufferedReader(new FileReader(filePath));
	       String line = "";
	       StringTokenizer st = null;

	       int lineNumber = 0; 
	       int tokenNumber = 0;
	       int summarytokenNumber = 0;	       
	      
	       HashMap headersHm = new HashMap();	      
	       
	       //read comma separated file line by line
	       while ((line = br.readLine()) != null) {
		         lineNumber++;
		         headersHm = new HashMap();
		         //use comma as token separator
		         st = new StringTokenizer(line, ",");
	
		         while (st.hasMoreTokens()){
		        	 tokenNumber++;	
		        	 String lineVal  = st.nextToken();		        	        		 
		        	 headersHm.put(""+tokenNumber , lineVal);
		        	 //System.out.print("  Header Val " + lineVal);
		         }
		         //reset token number
		         tokenNumber = 0;
		         retHm.put(""+lineNumber, headersHm);
	       	}
	       
	       //System.out.print("  Summary Val Hm" + retHm);
	     }catch (Exception e) {
	       System.err.println("CSV file cannot be read : " + e);
	       logger.info("CSV file cannot be read : " + e);
	     }
	     return retHm;
	}
	
  	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		/*String csvFile = "D:\\Laxmi\\eRoster\\SummaryFiles\\2012_08_Summary_Data_Test.csv";
		HashMap retHm = getFileData(csvFile);
		System.out.println();
		System.out.println("size" +retHm.size());
		
		for(int i=1;i<=retHm.size();i++){
			
			HashMap temphm = (HashMap)retHm.get(""+i);
			System.out.println(" temphm : " + temphm);
		}*/
	}
}
