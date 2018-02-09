import java.io.IOException;
import java.net.ServerSocket;
import java.net.InetSocketAddress;

public class WebServer 
{
        private static final int port = 8080; //default port
        
        public void start(int port) 
        {
            //
        }

        public static void main(String args[]) 
        {
            try 
            {

                new WebServer().start(port);

            } catch (Exception e) {

                //error message

            }
        }
}