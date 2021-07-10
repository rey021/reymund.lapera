package common;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.Exception;

public class AlarmDispatch
{
    public static boolean sendAlarm (String modID,String insID,String ip,String sev,String msg, String ts, String errID)
       throws Exception
    {
       try {
          String directoryName = "/D:/SPSWD2/PSWD2/src/ALARMS/Alarm_";
	  File directory = new File(directoryName);
		
    	
          SimpleDateFormat sdf= new SimpleDateFormat("ddMMMyyyy");
          java.util.Date now = new java.util.Date();
          SimpleDateFormat sdf2= new SimpleDateFormat("HH:mm:ss");
          String date  = sdf.format(now);
          String time  = sdf2.format(now);	
        
          String filename = directoryName + date + ".log";
          File theFile = new File(filename);
          try{
             FileWriter out = new FileWriter(theFile, true);
             out.write("32~" + modID +"~"+ insID +"~"+ ip +"~"+ sev +"~"+ msg +"~"+ ts +"~"+ errID +"\n");
             out.close();
          } catch (IOException e) {
             throw new Exception (e.toString());
          }
            
          return true;

       } catch (Exception exp) {
          try {
             writeToFile(exp);
          } catch (Exception mex) {
             throw new Exception (mex.toString());        	
          }
          throw new Exception (exp.toString());        
       }     	 
   }

   public static void writeToFile(Exception exception) throws Exception {
      String directoryName = "/D:/SPSWD2/PSWD2/src/ALARMS";
      File directory = new File(directoryName);

      try {
         long id = System.currentTimeMillis();
         String filename = directoryName + id;
         File theFile = new File(filename);
         FileWriter out = new FileWriter(theFile, true);
         out.write(id+","+ exception.toString() +"\n");
         out.close();
      } catch (IOException e) {
         throw new Exception (e.toString());
      }
   }
   
}
