import java.util.*;
import java.lang.*;
import java.io.*;

public class WebServer 
{
        private static final int port = 8080; //default port
        HttpdConf configuration = new HttpdConf("httpd.conf");
        //MimeTypes mimeTypes;
        //ServerSocket socket;
        //Dictionary accessFiles
        
        public void start() 
        {
            //
        }

        public static void main(String args[]) throws IOException
        {
            while(true) { //listens for requests in a while loop
                try
                {
                    //
                } catch (Exception e) {
                    //error log message
                }
            }
        }
}