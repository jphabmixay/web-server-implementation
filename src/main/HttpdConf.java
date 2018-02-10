public class HttpdConf extends ConfigurationReader{

    private String serverRoot;
    private String documentRoot;
    private int listen;
    private String logFile;
    private Map<String, String> aliases;
    private Map<String, String> scriptAliases;

    public HttpdConf(String fileName)
    {
        aliases = new HashMap<String, String>();
        scriptAliases = new HashMap<String, String>();
    }

    @Override
    public void load()
    {

    }
}   