package webserver;

import java.util.*;
import java.lang.*;
import java.io.*;

public class MimeTypes extends ConfigurationReader{

    private Map<String, String> types;

    public MimeTypes(String fileName)
    {
        super(fileName);
        types = new HashMap<>();
        load();
    }

    @Override
    public void load()
    {
        while(hasMoreLines()) {
            String line = getLine();
            String[] tokenizeLine = line.split("\\s+");

            if(tokenizeLine.length > 0 && !tokenizeLine[0].contains("#")) {
                for(int index = 1; index < tokenizeLine.length; index++) {
                    types.put(tokenizeLine[index], tokenizeLine[0]);
                }
            }
        }
    }

    public String lookup (String extension)
    {
        String mimeType = "text/plain";
        if(types.containsKey(extension)) {
            mimeType = types.get(extension);
        }
        return mimeType;
    }
}