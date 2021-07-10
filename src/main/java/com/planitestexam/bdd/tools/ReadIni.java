package tools;

import java.util.Properties;
import java.io.FileInputStream;

public class ReadIni {
    private String DBPassword;
    private String DBUsername;
   	
        String log4jLocation;
        String sql;
        String dataBase;
        String ftp_Ip,ftp_User,ftp_Pass,ftp_Port;
        String excel_Dir;
        String query_Res;
        String txt_Dir;
        String NE;
        
        
	public void doIt_TatReport() throws Exception 
        {
            Properties p = new Properties();
    
            p.load(new FileInputStream("src/config/tatreport.config"));
            log4jLocation   =   p.getProperty("Log4JLocation");
            sql        =   p.getProperty("SQL");
            dataBase        =   p.getProperty("Database");
            ftp_Ip          =   p.getProperty("FTP_IP");
            ftp_User        =   p.getProperty("FTP_USER");
            ftp_Pass        =   p.getProperty("FTP_PASS");
            ftp_Port        =   p.getProperty("FTP_PORT");
            excel_Dir       =   p.getProperty("EXCEL_DIR");
            query_Res       =   p.getProperty("QUERY_RES_DIR");
            txt_Dir         =   p.getProperty("TXT_DIR");
            NE              =   p.getProperty("NE_LIST");
            DBUsername      =   p.getProperty("DBUsername");
            DBPassword      =   p.getProperty("DBPassword");
	}
        
        public String getLog4jLocation()
        {
            return log4jLocation;
        }
        
        public String getSql()
        {
            return sql;
        }
        
        public String getDatabase()
        {
            return dataBase;
        }
        
        public String getFtp_Ip()
        {
            return ftp_Ip;
        }
        
          public String getFtp_User()
        {
            return ftp_User;
        }
        
        public String getFtp_Pass()
        {
            return ftp_Pass;
        }
        
        public String getFtp_Port()
        {
            return ftp_Port;
        }
         
        public String getExcel_Dir()
        {
            return excel_Dir;
        }
        
        public String getQuery_Res()
        {
            return query_Res;
        }
        
         public String getTxt_Dir()
        {
            return txt_Dir;
        }
         
         public String getNEList()
         {
             return NE;
         }
         
         public String getDBUsername()
         {
             return DBUsername;
         }
         
         public String getDBPassword()
         {
             return DBPassword;
         }
}