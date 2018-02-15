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
        int extensionCounter = 0; // Debug statement

        System.out.println("BEFORE THE KEYS AND VALUES ARE STORED IN HASHMAP.");

        while(hasMoreLines()) {
            String line = getLine();

            String[] tokenizeLine = line.split("\\s+");


            if(tokenizeLine.length > 0 && !tokenizeLine[0].contains("#")) { //Ignores empty lines & lines starting with #
                for(int index = 1; index < tokenizeLine.length; index++) {
                    types.put(tokenizeLine[index], tokenizeLine[0]);
                    extensionCounter++; // Debug statement
                    System.out.println("pair number: " + extensionCounter + ", key: " + tokenizeLine[index] + ", value: " + tokenizeLine[0]);
                    types.put(tokenizeLine[index], tokenizeLine[0]);
                }
            }
        }
         System.out.println("=====================================");

        ///* Debug statement
        System.out.println("Number of file extensions: " + extensionCounter);
        System.out.println("Number of key/value pairs: " + types.size() );
        System.out.println("Number of unique values: " + types.values().stream().distinct().count());
        //*/

        int counter = 0;

        System.out.println("=====================================");
        System.out.println("AFTER THE KEYS AND VALUES ARE STORED IN HASHMAP.");
        Iterator it = types.entrySet().iterator();
        while (it.hasNext()) {
            counter++;
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println("pair number: " + counter + ", key: " + pair.getKey() + ", value: " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }

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