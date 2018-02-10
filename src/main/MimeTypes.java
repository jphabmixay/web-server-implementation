public class MimeTypes extends ConfigurationReader{

    private Map<String, String> types;

    public MimeTypes(String fileName)
    {
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