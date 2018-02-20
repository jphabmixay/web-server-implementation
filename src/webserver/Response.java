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
    int code;
    ArrayList<String> headers;
    byte[] body;

    public Response(Request request, Resource resource) throws IOException{
        this.resource = resource;
        this.file = new File(resource.absolutePath());
        this.headers = new ArrayList<String>();
        if(file.isFile()) {
            doVerb(request);
        }
        else {
            code = 404;
            headers.add(responseCode(code));
        }
    }

    public void send(OutputStream out) throws IOException{
        DataOutputStream output = new DataOutputStream(out);
        for (String header : headers) {
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
                code = 200;
                addHeaders();
                break;
            case "GET":
                code = 200;
                //body = getBytes(file); //Not correctly displaying web page.
                addHeaders();
                break;
            case "PUT":
                code = 201;
                addHeaders();
                break;
            case "DELETE":
                code = 204;
                addHeaders();
                break;
            case "POST":
                code = 304;
                addHeaders();
                break;
            case "BAD REQUEST":
                code = 400;
                addHeaders();
            default:
                break;
        }
    }
    public void addHeaders() {
        headers.add(responseCode(code));
        headers.add("Date: " + new Date());
        headers.add("Server: web-server-team-c");
        headers.add("Last Modified: " + file.lastModified());
        headers.add("Content-length: " + file.length());
        headers.add(getContentType(resource.absolutePath()));

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

