package webserver;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.*;
import java.io.*;


public class Logger {
    File file;
    String message = "";

    public Logger(String fileName){
        file = new File(fileName);
    }

    public void write(Request request, Response response) throws IOException{
        InetAddress IP = InetAddress.getLocalHost();
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = " [" + dateFormat.format(date) + "] \"";
        message =   IP.getHostAddress()
                    + " - "
                    + IP.getHostName()
                    + strDate
                    + request.getVerb()
                    + " "
                    + request.getUri()
                    + " "
                    + request.getHttpVersion()
                    + "\" "
                    + response.code + " \n";
        System.out.print(message);

        appendMessage();
    }

    private void appendMessage() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
