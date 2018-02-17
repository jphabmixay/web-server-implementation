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
        String modifiedUri = uri; // uri is unmodified if not aliased or script aliased
        System.out.println("modifiedUri = " + modifiedUri);

        //uri aliased?
        if(config.containsAliasKey(uri)) {
            System.out.println("modifiedUri = " + modifiedUri);
            modifiedUri = config.getAliasValue(uri);
        }
        //uri script-aliased?
        else if(config.containsScriptAliasKey(uri)) {
                modifiedUri = config.getScriptAliasValue(uri);
                isScript = true;
        }
        //Neither alias nor script-aliased. Pass unmodified URI
        else {
            System.out.println("modifiedUri is not aliased or script aliased. Unmodified URI");
            modifiedUri = config.getDocumentRoot().concat(uri);
            System.out.println("modifiedUri = " + modifiedUri);
        }

        //check if the path/uri is a file
        File file = new File(modifiedUri);
        if(!file.isFile()) {
            System.out.println(modifiedUri + " does not exist. Appending index.");
            //no, path/uri is a directory, so append directory index
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
