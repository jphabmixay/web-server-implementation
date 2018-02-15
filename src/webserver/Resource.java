package webserver;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Resource {
    private String absolutePath;
    private boolean isScript = false;
    private boolean isProtected;

    public Resource(String uri, HttpdConf config) {
        String modifiedUri = uri; // uri is unmodified if not aliased or script aliased

        //uri aliased?
        if(config.containsAliasKey(uri)) {
            // yes, uri is aliased, so modify the uri
            modifiedUri = config.getAliasValue(uri);
        } else {
            //uri is script aliased?
            if(config.containsScriptAliasKey(uri)) {
                //yes, uri is script aliased, so modify uri
                modifiedUri = config.getScriptAliasValue(uri);
                isScript = true;
            }
        }

        //check if uri is unmodified
        if(modifiedUri == uri) {
            //yes, uri is unmodified, so Resolve path (DOC_ROOT + URI)
            modifiedUri = config.getDocumentRoot().concat(uri);
        }

        //check if the path/uri is a file
        File file = new File(modifiedUri);
        if(!file.isFile()) {
            //no, path/uri is a directory, so append directory index
            modifiedUri.concat(config.getDirectoryIndex());
        }

        absolutePath = modifiedUri;

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
