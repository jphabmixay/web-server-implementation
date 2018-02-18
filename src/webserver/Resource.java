package webserver;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Resource {
    private String absolutePath;
    private boolean isScript = false;
    private boolean isProtected;

    public Resource(String uri, HttpdConf config) {
        System.out.println("Creating resource file with URI: " + uri);
        String modifiedUri = uri;

        //Check if the uri is aliased or script-aliased.
        if(config.containsAliasKey(uri)) {
            System.out.println("modifiedUri = " + modifiedUri);
            modifiedUri = config.getAliasValue(uri);
        }
        else if(config.containsScriptAliasKey(uri)) {
                modifiedUri = config.getScriptAliasValue(uri);
                isScript = true;
        }
        else {
            System.out.println("modifiedUri is not aliased or script aliased; unmodified URI");
            modifiedUri = config.getDocumentRoot().concat(uri);
            System.out.println("modifiedUri = " + modifiedUri);
        }

        //Check if the path/uri is a file, else append index
        File file = new File(modifiedUri);
        if(!file.isFile()) {
            System.out.println(modifiedUri + " does not exist. Appending index.");
            modifiedUri = config.getDirectoryIndex();
        }

        absolutePath = modifiedUri;
        System.out.println("absolutePath = " + absolutePath);
    }

    public String absolutePath() {
        return absolutePath;
    }

    public boolean isScript() {
        return isScript;
    }

    public boolean isProtected() {
        return isProtected;
    }
}
