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

        //Does htaccess exists?
        String pathToHtaccess = config.getDocumentRoot().concat(config.getAccessFileName());
        File htaccessFile = new File(pathToHtaccess);
        //Check if .htaccess exist
        if(htaccessFile.exists()) {
            //yes, file exists so lets create instance of Htaccess.java?
            Htaccess htaccessObject = new Htaccess(pathToHtaccess);

            //check if Authentication header exists?

        } else {
            //htaccess file doesnt exists so, its not protected?
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
