package webserver;

import java.util.*;
import java.lang.*;

public class HttpdConf extends ConfigurationReader{

    //Data fields to be loaded from configuration files
    private String serverRoot;
    private String documentRoot;
    private int listen;
    private String logFile;
    private String accessFileName = ".htaccess";  // default value is .htaccess
    private String directoryIndex = "/Users/jrob/workspace/server/public_html/index.html"; // default value i think is index.html
    private Map<String, String> aliases;
    private Map<String, String> scriptAliases;

    public HttpdConf(String fileName){
        super(fileName);
        scriptAliases = new HashMap<String, String>();
        aliases = new HashMap<String, String>();
        load();
    }

    @Override
    public void load()
    {
    	while (hasMoreLines()){
            String line = getLine();
            String[] tokenizeLine = line.split("\\s+");

            if (tokenizeLine.length > 0 && !tokenizeLine[0].contains("#")) {
                tokenizeLine[1] = tokenizeLine[1].replace("\"", "");
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
                        case "AccessFileName":  accessFileName = tokenizeLine[1];
                                                break;
                        case "DirectoryIndex ": directoryIndex = tokenizeLine[1];
                        default: break;
                    }
                }
            }
        }
    }

    public boolean containsAliasKey(String key) {
        boolean flag = false;
        if(aliases.containsKey(key)) {
            flag = true;
        }
        return flag;
    }

    public String getAliasValue(String key) {
        String aliasValue = aliases.get(key);
        return aliasValue;
    }

    public boolean containsScriptAliasKey(String key) {
        boolean flag = false;
        if(scriptAliases.containsKey(key)) {
            flag = true;
        }
        return flag;
    }

    public String getScriptAliasValue(String key) {
        String scriptAliasValue = scriptAliases.get(key);
        return scriptAliasValue;
    }

    public String getServerRoot() { return serverRoot; }

    public String getDocumentRoot() { return documentRoot; }

    public String getLogFile() { return logFile; }

    public String getAccessFileName() { return accessFileName; }

    public String getDirectoryIndex() { return directoryIndex; }

    public int getListen() { return listen; }

}   