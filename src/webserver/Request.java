package webserver;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Request{
    private String verb;
    private String uri;
    private String httpVersion;
    private Map<String, String> headers;

    String line;
    FileReader fileReader;
    BufferedReader bufferedReader;

    public Request(String test){

    }

    public Request(InputStream client) throws IOException{
        headers = new HashMap<String, String>();
        bufferedReader = new BufferedReader(new InputStreamReader(client));
        line = bufferedReader.readLine();
        parse();

        if (!checkVerb() || !checkVersion()){
            this.verb = "BAD REQUEST";
        }
    }

    public void parse() throws IOException{
        String[] tokenizeLine = line.split("\\s+");

        verb        = tokenizeLine[0];
        uri         = tokenizeLine[1];
        httpVersion = tokenizeLine[2];

        //TO DO : PARSE OPTIONAL HEADERS   (Example Syntax -->  Accept: image/gif, image/jpeg)
        /*line = bufferedReader.readLine();
        while(line != null){
            tokenizeLine = line.split(": ");
            headers.put(tokenizeLine[0], tokenizeLine[1]);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();*/
    }

    public String getVerb() { return verb; }

    public String getUri() { return uri; }

    public String getHttpVersion() { return httpVersion; }

    public String getHeaders(String key) { return headers.get(key); }

    public boolean checkVerb(){
        switch (verb) {
            case "GET"  :
            case "HEAD" :
            case "POST" :
            case "PUT"  :
            case "DELETE" :
                return true;
            default :
                return false;
        }
    }

    public boolean checkVersion(){
        if (httpVersion.equals("HTTP/1.0") || httpVersion.equals("HTTP/1.1")){ return true; }
        return false;
    }
}