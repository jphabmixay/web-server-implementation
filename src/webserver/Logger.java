package webserver;

import java.net.InetAddress;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Logger {
    File file;
    String message = "";

    public Logger(String fileName){
    }

    public void write(Request request, Response response) throws IOException{
        InetAddress IP = InetAddress.getLocalHost();
        Date date = new Date();
        message =   IP.getHostAddress()
                    + " - "
                    + IP.getHostName()
                    + " [" + date + "] \""
                    + request.getVerb()
                    + " "
                    + request.getUri()
                    + " "
                    + request.getHttpVersion()
                    + "\" "
                    + response.code + " ";
        System.out.println(message);
    }
}
