//import javax.jms.*;
package common;

import java.lang.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import java.io.*;
//import common.model.*;

public  class NetInfo {
    //public static void main(String[] args) {
    //new NetInfo().getInfo();
    //}

    public static java.net.InetAddress getInfo()  {
        try {
            java.net.InetAddress i = java.net.InetAddress.getLocalHost();
            return(i);                  // name and IP address
        }catch(Exception e){
            return (null);

        }
    }

    public static String getIP()  {
        try {
            java.net.InetAddress i = java.net.InetAddress.getLocalHost();
            return(i.getHostAddress());    // IP address only
        }catch(Exception e){
            return (null);

        }
    }

     public static String getName() {
        try {
            java.net.InetAddress i = java.net.InetAddress.getLocalHost();
            return(i.getHostName());    // name only
        } catch(Exception e) {
            return (null);

        }
    }
}

