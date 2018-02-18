package webserver;

import javax.xml.crypto.Data;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Response {

    public Resource resource;
    public File file;
    ArrayList<String> headers;
    byte[] body;

    public Response(Request request, Resource resource) throws IOException{
        this.resource = resource;
        this.file = new File(resource.absolutePath());
        this.headers = new ArrayList<String>();
        headers.add("Date: " + new Date());
        headers.add("Server: web-server-team-c");

        if(file.isFile()) {
            doVerb(request);
        }
        else {
            headers.add(responseCode(404));
        }
    }

    public void send(OutputStream out) throws IOException{
        System.out.println("Sending...");
        DataOutputStream output = new DataOutputStream(out);
        for (String header : headers) {
            System.out.println(header);
            output.writeBytes(header + "\r\n");
        }
        if (body != null) {
            InputStream fileReader = new FileInputStream(file);
            while(fileReader.available() > 0){
                output.write(body, 0, fileReader.read(body));
            }
        }
        output.writeBytes("\r\n");
        output.flush();
    }

    public void doVerb(Request request) throws IOException{
        switch(request.getVerb()) {
            case "HEAD":
                headers.add(responseCode(200));
                break;
            case "GET":
                headers.add(getContentType(resource.absolutePath()));
                //body = getBytes(file); //Only returns raw value
                headers.add(responseCode(200));
                break;
            case "PUT":
                headers.add(responseCode(201));
                break;
            case "DELETE":
                headers.add(responseCode(204));
                break;
            case "POST":
                headers.add(responseCode(304));
                break;
            case "BAD REQUEST":
                headers.add(responseCode(400));
            default:
                break;
        }
    }
    public byte[] getBytes(File file) throws IOException{
        Path path = Paths.get(resource.absolutePath());
        byte[] bytes = Files.readAllBytes(path);
        return bytes;
    }

    public String getContentType(String path) {
        String content = "Content Type: ";
        if (path.endsWith(".html")) {
            return content + "text/html";
        } else {
            return content + "text/plain";
        }
    }

    public String responseCode(int code){
        switch(code){
            case 200: return "200 OK";
            case 201: return "201 Not Created";
            case 204: return "204 No Content";
            case 304: return "304 Not Modified";
            case 400: return "400 Bad Request";
            case 401: return "401 Unauthorized";
            case 403: return "403 Forbidden";
            case 404: return "404 Not Found";
            case 500: return "500 Internal Server Error";
            default: return null;
        }
    }
}

