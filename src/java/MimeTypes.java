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

    }

    public String lookup (String extension)
    {

    }
}