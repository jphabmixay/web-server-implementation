import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class WebServer {
		static final int port = 8080; //default port
		
		public void start(int port) {
    	try {
	        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
	        server.setExecutor(null); //default executer
	        server.start();
    	} catch (IOException e) {
    		
    	}
    }
}