package webserver;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Request{
    String verb;
    String uri;
    String httpVersion;
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
    }

    public void parse() throws IOException{
        String[] tokenizeLine = line.split("\\s+");

        verb        = tokenizeLine[0];
        uri         = tokenizeLine[1];
        httpVersion = tokenizeLine[2];

        //Parse Optional Headers - (Example Syntax -->  Accept: image/gif, image/jpeg)
        while((line = bufferedReader.readLine()) != null){
            tokenizeLine = line.split(": ");
            headers.put(tokenizeLine[0], tokenizeLine[1]);
        }

        //debug statements:
        if (checkVerb() && checkVersion()){
            //testing
            System.out.print("Syntax Correct, displaying variables: ");
            System.out.print("Verb : " + verb);
            System.out.print("URI : " + uri);
            System.out.print("httpVersion : " + httpVersion);
            //Syntax is correct. Pass to Resource
        }
        else {
            //Response Code 400
            System.out.print("Syntax incorrect.");
        }
    }

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
        if (httpVersion.equals("HTTP/1.0") || httpVersion.equals("HTTP/1.1")){
            return true;
        }
        return false;
    }
}