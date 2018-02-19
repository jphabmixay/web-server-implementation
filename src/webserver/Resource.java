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
            modifiedUri = config.getDocumentRoot().concat(config.getDirectoryIndex());
        }

        absolutePath = modifiedUri;
        System.out.println("absolutePath = " + absolutePath);


        //Does htaccess exists?
        String pathToHtaccess = config.getDocumentRoot().concat(config.getAccessFileName());
        System.out.println("pathToHtaccess: " + pathToHtaccess);
        File htaccessFile = new File(pathToHtaccess);
        //Check if .htaccess exist
        if(htaccessFile.exists()) {
            //yes, file exists so lets create instance of Htaccess.java?
            System.out.println(".htaccess file exists, create instance of Htaccess.java");
            Htaccess htaccessObject = new Htaccess(pathToHtaccess);

            //check if Authentication header exists?

        } else {
            //htaccess file doesnt exists so, its not protected?
            System.out.println(".htaccess file doesn't exist, so request is not protected?");
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
