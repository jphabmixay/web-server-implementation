package webserver;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Resource {
    private String absolutePath;
    private boolean isScript = false;
    private boolean isProtected;

    public Resource(String uri, HttpdConf config) {
        String modifiedUri = uri;

        //Check if the uri is aliased or script-aliased.
        if(config.containsAliasKey(uri)) {
            modifiedUri = config.getAliasValue(uri);
        }
        else if(config.containsScriptAliasKey(uri)) {
                modifiedUri = config.getScriptAliasValue(uri);
                isScript = true;
        }
        else {
            modifiedUri = config.getDocumentRoot().concat(uri);
        }

        //Check if the path/uri is a file, else append index
        File file = new File(modifiedUri);
        if(!file.isFile()) {
            modifiedUri = config.getDocumentRoot().concat(config.getDirectoryIndex());
        }

        absolutePath = modifiedUri;

        String pathToHtaccess = config.getDocumentRoot().concat(config.getAccessFileName());
        File htaccessFile = new File(pathToHtaccess);
        if(htaccessFile.exists()) {
            Htaccess htaccessObject = new Htaccess(pathToHtaccess);

        } else {
            isProtected = false;
        }
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
