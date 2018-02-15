package webserver;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Resource {
    public Resource(String uri, HttpdConf config) {

    }

    public String absolutePath() {
        return null;
    }

    public boolean isScript() {
        return false;
    }

    public boolean isProtected() {
        return false;
    }
}
