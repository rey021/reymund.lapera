package common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AlarmSender {
	
	private static String formatAlarmDate(){
		String theDate="";
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
			java.util.Date now = new java.util.Date();
			theDate=sdf.format(now);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return theDate;
	}
	
	public static void sendAlarm(String modID,String insID,String ip,String sev,String msg,String errID) {
		String theMessage = msg.replaceAll("\n"," - ");
		//theMessage = theMessage.replaceAll("\n(?d)","-");
		//theMessage = theMessage.replaceAll("\r(?d)","-");
		try {
			System.out.println("ALARM:" + modID + "," + insID + "," + ip + "," + sev + "," + theMessage +"," + formatAlarmDate() + "," + errID);
			AlarmDispatch.sendAlarm(modID,insID,NetInfo.getIP(),sev,theMessage,formatAlarmDate(),errID);
		} catch (Exception e) {
			System.out.println("Unable to send ALARM!! " + msg);
			e.printStackTrace();
		}
	}
	
}

