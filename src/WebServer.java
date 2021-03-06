import webserver.HttpdConf;
import webserver.MimeTypes;
import webserver.Request;
import webserver.Resource;
import webserver.Response;
import webserver.Logger;

import java.lang.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.*;

public class WebServer
{
        private int port;
        private HttpdConf configuration = new HttpdConf("src/conf/httpd.conf");
        private MimeTypes mimeTypes = new MimeTypes("src/conf/mime.types");
        private Logger log = new Logger(configuration.getLogFile());
        private ServerSocket socket;
        //private Dictionary accessFiles
        
        public void start() throws IOException
        {
            port = configuration.getListen();
            socket = new ServerSocket(port);
            System.out.println("Java WebServer, Starting on port: " + port);
            while (true){
                Socket client = socket.accept();
                Request request     = new Request(client.getInputStream());
                Resource resource   = new Resource(request.getUri(), configuration);
                Response response   = new Response(request, resource);
                response.send(client.getOutputStream());
                log.write(request, response);
                client.close();
            }
        }

        public static void main(String args[]) throws IOException
        {
            try {
                WebServer server = new WebServer();
                server.start();
            } catch (Exception e) {
                //
            }
        }
}