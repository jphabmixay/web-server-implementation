import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.ServerSocket;

public class WebServer 
{
        private static final int DEFAULT_PORT = 8080;
        private HttpdConf configuration = new HttpdConf("../conf/httpd.conf");
        private MimeTypes mimeTypes = new MimeTypes("../conf/mime.types");
        //private ServerSocket socket;
        //private Dictionary accessFiles
        
        public void start() throws IOException
        {
            ServerSocket socket = new ServerSocket(DEFAULT_PORT);
            System.out.println("Java WebServer, Starting on port: " + DEFAULT_PORT);
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