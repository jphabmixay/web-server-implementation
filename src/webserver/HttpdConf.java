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
        scriptAliases = new HashMap<String, String>();
        aliases = new HashMap<String, String>();
    }

    @Override
    public void load()
    {
    	while (hasMoreLines()){
            String line = getLine();
            String[] tokenizeLine = line.split("\\s+");

            if (tokenizeLine.length > 0 && !tokenizeLine[0].contains("#")) { //ignores empty space and comments
                for (int index = 1; index < tokenizeLine.length; index ++) {

                    switch (tokenizeLine[0]) {
                        case "ServerRoot":      serverRoot = tokenizeLine[1];
                                                break;
                        case "DocumentRoot":    documentRoot = tokenizeLine[1];
                                                break;
                        case "Listen" :         listen = Integer.parseInt(tokenizeLine[1]);
                                                break;
                        case "LogFile" :        logFile = tokenizeLine[1];
                                                break;
                        case "ScriptAlias":     scriptAliases.put(tokenizeLine[1], tokenizeLine[2]);
                                                break;
                        case "Alias":           aliases.put(tokenizeLine[1], tokenizeLine[2]);
                                                break;
                        default: break;
                    }
                }
            }
        }
    }
}   