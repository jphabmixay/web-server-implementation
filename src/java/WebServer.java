import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.ServerSocket;

public class WebServer 
{
        private static final int port = 8080; //default port
        HttpdConf configuration = new HttpdConf("../conf/httpd.conf");
        //MimeTypes mimeTypes;
        //ServerSocket socket;
        //Dictionary accessFiles
        
        public void start() throws IOException
        {
            ServerSocket socket = new ServerSocket(port);
            System.out.println("Java WebServer, Starting on port: " + port);
            while (true){
                //listen for requests
            }
        }

        public static void main(String args[]) throws IOException
        {
            try {
                new WebServer().start();
            } catch (Exception e) {
                //error log message
            }
        }
}