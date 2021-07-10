package com.planitestexam.bdd.uitest;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import common.AlarmDispatch;
import common.AlarmSender;
import common.NetInfo;
import tools.FileHandler;

public class LogManager {
    Logger logger = Logger.getLogger(LogManager.class);
    AlarmDispatch alarm = new AlarmDispatch ();
    String ip = NetInfo.getIP ();
    FileHandler fileHandler = new FileHandler();
    String dir = null;   
      
	public void initialize(String logType, String dir) {
	  
	  try {
	
	    this.dir=dir + logType +".ini";
	    System.out.print(this.dir);
	    fileHandler.setFileName(this.dir);
	    System.out.println(fileHandler.isExist());
	    PropertyConfigurator.configure(this.dir);
      } catch (Exception e) {
	      logger.info(" Exception " + e);    
      }      	
	}  

	public synchronized void info(String infoString) {
	  PropertyConfigurator.configure(dir);	
	   logger.info(infoString);	
	}	
	public synchronized void warn(String warnString) {
	   PropertyConfigurator.configure(dir);	
	   logger.warn(warnString);	
	}	
	public synchronized void debug(String debugString) {
	   PropertyConfigurator.configure(dir);	
	   logger.debug(debugString);	
	}	
	public synchronized void error(String warnString) {
	   PropertyConfigurator.configure(dir);	
	   logger.error(warnString);	
	}	
	public synchronized void fatal(String debugString) {
	   PropertyConfigurator.configure(dir);
	   logger.fatal(debugString);	
	}		
	public synchronized void logAlarm(String moduleID, String instance,String message, String errorCode, String severity) {
		try {
			String newmessage = "";
			newmessage = newmessage+message.replaceAll("'", "`");
			newmessage = newmessage.replaceAll ("\"", " ");
			//**** remove new line or carriage return
			newmessage = newmessage.replaceAll ("(\r\n)|(\n)|(\r)|(\n\r)", " - ");
				
			Date dateNow = new Date();
			SimpleDateFormat dateFormat= new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
			String contentDate=dateFormat.format(dateNow);
			alarm.sendAlarm (moduleID,instance,ip,severity,newmessage,contentDate,errorCode);
		} catch (Exception e) {
			logger.error ("Unable to write to alarm file [" + moduleID + "] " + e);
		}
	}	
	
	
	public synchronized void logAlarm(String agentID, String moduleID, String instance,String message, String errorCode, String severity) {
		try {
			String newmessage = "";
			newmessage = newmessage+message.replaceAll("'", "`");
			newmessage = newmessage.replaceAll ("\"", " ");
			//**** remove new line or carriage return
			newmessage = newmessage.replaceAll ("(\r\n)|(\n)|(\r)|(\n\r)", " - ");
				
			Date dateNow = new Date();
			SimpleDateFormat dateFormat= new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
			String contentDate=dateFormat.format(dateNow);
			alarm.sendAlarm (moduleID,instance,ip,severity,newmessage,contentDate,errorCode);
		} catch (Exception e) {
			logger.error ("Unable to write to alarm file [" + moduleID + "] " + e);
		}
	}
	
	public synchronized void printErrorLog(Exception e)
	{
		StackTraceElement[] ste = e.getStackTrace();
	    	
		error (" Cannot write alarm Exception: "+e);
	    for(int i=0; i<ste.length; i++)
	    	error (" Cannot write alarm Exception: "+ste[i]);
	}
	
/*---------------------------------------------------------------------------------------------------------------------*/			
	public synchronized void printErrorLog(String moduleID, Exception e, String errorID, String severity)
	{
		StackTraceElement[] ste = e.getStackTrace();
	    	
	   	printErrorLog(moduleID, "Exception: "+e, errorID, severity);
	    for(int i=0; i<ste.length; i++)
	    	error("   Exception: "+ste[i]);
	}
	
/*---------------------------------------------------------------------------------------------------------------------*/				
	public synchronized void printErrorLog(String moduleID, String message, String errorID, String severity)
	{		
		error(message+"");	
		AlarmSender.sendAlarm(moduleID,"1",NetInfo.getIP(),severity,message,errorID);
	}	
	
/*---------------------------------------------------------------------------------------------------------------------*/			
	public synchronized void printErrorLog(String moduleID, Exception e, String message, String errorID, String severity)
	{
		StackTraceElement[] ste = e.getStackTrace();
	    	
	    printErrorLog(moduleID, "Exception: "+message+": "+e, errorID, severity);
	    for(int i=0; i<ste.length; i++)
	    	error("   Exception: "+message+": "+ste[i]);
	}	
}	