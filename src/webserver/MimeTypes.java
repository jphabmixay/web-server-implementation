import java.util.*;
import java.lang.*;
import java.io.*;

public class MimeTypes extends ConfigurationReader{

    private Map<String, String> types;

    public MimeTypes(String fileName)
    {
        super(fileName);
        types = new HashMap<String, String>();
    }

    @Override
    public void load()
    {
        while(hasMoreLines()) {
            String line = getLine();

            String[] tokenizedLine = line.split("\\s");

            System.out.println("First token of each line: " + tokenizedLine[0]);

            if(tokenizedLine.length <= 0 || tokenizedLine[0] == "#") {
                ; // Ignores empty lines and lines that start with #
            }
        }
    }

    public String lookup (String extension)
    {
        return null;
    }
}