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
    }

    @Override
    public void load()
    {
        //int extensionCounter = 0; // Debug statement

        while(hasMoreLines()) {
            String line = getLine();

            String[] tokenizeLine = line.split("\\s+");

            if(tokenizeLine.length <= 0) {
                // Ignores empty lines
            } else if(tokenizeLine[0].contains("#")) {
                // Ignores lines that start with #
            } else {
                for(int index = 1; index < tokenizeLine.length; index++) {
                    types.put(tokenizeLine[index], tokenizeLine[0]);

                    //extensionCounter++; // Debug statement
                }
            }
        }

        /* Debug statement
        System.out.println("Number of file extensions: " + extensionCounter);
        System.out.println("Number of key/value pairs: " + types.size() );
        System.out.println("Number of unique values: " + types.values().stream().distinct().count());
        //*/
    }

    public String lookup (String extension)
    {
        String mimeType = "text/plain"; // default mime type?
        if(types.containsKey(extension)) {
            mimeType = types.get(extension);
        }
        return mimeType;
    }
}