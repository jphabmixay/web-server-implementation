package webserver;

import java.util.*;
import java.lang.*;

public class HttpdConf extends ConfigurationReader{

    //Data fields to be loaded from configuration files
    private String serverRoot;
    private String documentRoot;
    private int listen;
    private String logFile;
    private Map<String, String> aliases;
    private Map<String, String> scriptAliases;

    public HttpdConf(String fileName){
        super(fileName);
        aliases = new HashMap<String, String>();
        scriptAliases = new HashMap<String, String>();
    }

    @Override
    public void load()
    {
    	while (hasMoreLines()){
            
        }
    }
}   