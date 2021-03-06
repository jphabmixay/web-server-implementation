package webserver;

import java.util.HashMap;
import java.util.Base64;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.io.IOException;
import webserver.ConfigurationReader;

public class Htpassword extends ConfigurationReader {
  private HashMap<String, String> passwords;

  public Htpassword( String filename ) throws IOException {
    super( filename );
    System.out.println( "Password file: " + filename );

    this.passwords = new HashMap<String, String>();
    this.load();
  }

  @Override
  public void load() {
    while(hasMoreLines()) {
      parseLine(getLine());
    }
  }

  protected void parseLine( String line ) {
    String[] tokens = line.split( ":" );

    if( tokens.length == 2 ) {
      passwords.put( tokens[ 0 ], tokens[ 1 ].replace( "{SHA}", "" ).trim() );
    }
  }

  public boolean isAuthorized( String authInfo ) {
    String credentials = new String(
      Base64.getDecoder().decode( authInfo ),
      Charset.forName( "UTF-8" )
    );

    String[] tokens = credentials.split( ":" );

    // TODO: implement this
    return verifyPassword(tokens[0], tokens[1]);
  }

  private boolean verifyPassword( String username, String password ) {
    // encrypt the password, and compare it to the password stored
    // in the password file (keyed by username)
    // TODO: implement this - note that the encryption step is provided as a
    // method, below

    boolean flag = false;
    String encryptedPassword = encryptClearPassword(password);
    if(passwords.get(username) == encryptedPassword) {
      flag = true;
    }
    return flag;
  }

  private String encryptClearPassword( String password ) {
    try {
      MessageDigest mDigest = MessageDigest.getInstance( "SHA-1" );
      byte[] result = mDigest.digest( password.getBytes() );

     return Base64.getEncoder().encodeToString( result );
    } catch( Exception e ) {
      return "";
    }
  }
}
