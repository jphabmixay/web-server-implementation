package webserver;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Request{
    String verb;
    String uri;
    String httpVersion;
    //Dictionary headers. Hashmap? List?

    String line;

    public Request(String test){

    }

    public Request(InputStream client) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client));
        line = bufferedReader.readLine();
        parse();
    }

    public void parse(){

        /*Split the string into URI, verb, and httpVersion.

        General Form of HTTP webserver.Request : HTTP_METHOD  IDENTIFIER  HTTP_VERSION  HTTP_HEADERS
        HTTP_METHOD = verb, IDENTIFIER = URI... 

        Example:    GET /test.html HTTP/1.1*/

        String[] tokenizeLine = line.split("\\s+");
        verb        = tokenizeLine[0]; //To Do: 400 Error if fail.
        uri         = tokenizeLine[1];
        httpVersion = tokenizeLine[2];
    }
}