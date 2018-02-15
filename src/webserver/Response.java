package webserver;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Response {
    public int code;
    public String reasonPhrase;
    public Resource resource;

    public Response(Resource resource) {

    }

    public void send(OutputStream out) {

    }
}
