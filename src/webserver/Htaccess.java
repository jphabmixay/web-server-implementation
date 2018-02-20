package webserver;

import java.io.IOException;

public class Htaccess extends ConfigurationReader {
    private Htpassword userFile;
    private String authType;
    private String authName;
    private String require;

    public Htaccess(String fileName) {
        super(fileName);
    }

    @Override
    public void load() {
        while(hasMoreLines()) {
            String line = getLine();
            String[] tokenizeLine = line.split("\\s+");

            if(tokenizeLine.length > 0 && !tokenizeLine[0].contains("#")) {
                for(int index = 1; index < tokenizeLine.length; index++) {
                    switch (tokenizeLine[0]) {
                        case "AuthUserFile":    try { userFile = new Htpassword(tokenizeLine[1]); }
                                                catch (IOException e) { e.printStackTrace(); }
                                                break;
                        case "AuthType":        authType = tokenizeLine[1];
                                                break;
                        case "AuthName" :       authName = tokenizeLine[1];
                                                break;
                        case "Require" :        require = tokenizeLine[1];
                                                break;
                        default: break;
                    }
                }
            }
        }
    }

    public String getAuthType() { return authType; }

    public String getAuthName() { return authName; }

    public String getRequire() { return require; }

    // TODO: implement this, maybe similar to isAuthorized() inside Htpassword.java
    public boolean isAuthorized(String username, String password) {
        return false;
    }
}
