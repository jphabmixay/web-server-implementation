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
            String line = getLine();
            String[] tokenizeLine = line.split("\\s+");

            if (tokenizeLine.length > 0 && !tokenizeLine[0].contains("#")) { //ignores empty space and comments
                for (int index = 1; index < tokenizeLine.length; index ++) {

                    switch (tokenizeLine[0]) {
                        case "ServerRoot": serverRoot = tokenizeLine[1];
                                            break;
                        //off to class. will finish rest of cases after.
                        default: break;
                    }
                }
            }
        }

        //debug statement to test member variables:
        System.out.println("serverRoot :" + serverRoot);
    }
}   