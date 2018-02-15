package webserver;

import java.lang.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer
{
        private static final int DEFAULT_PORT = 8080;
        private HttpdConf configuration = new HttpdConf("src/conf/httpd.conf");
        private MimeTypes mimeTypes = new MimeTypes("src/conf/mime.types");
        private ServerSocket socket;
        //private Dictionary accessFiles
        
        public void start() throws IOException
        {
            //mimeTypes.load(); // Debug statement
            //configuration.load(); //Debug statement

            socket = new ServerSocket(DEFAULT_PORT);
            System.out.println("Java WebServer, Starting on port: " + DEFAULT_PORT);
            while (true){
                Socket client = socket.accept(); //accept() receives a connection and returns a Socket object
                System.out.println("Request Received!");
                Request request = new Request(client.getInputStream()); //To do: pass as an actual http request to Request() constructor
            }
        }

        public static void main(String args[]) throws IOException
        {
            try {
                WebServer server = new WebServer();
                server.start();
            } catch (Exception e) {
                //error log message
            }
        }
}