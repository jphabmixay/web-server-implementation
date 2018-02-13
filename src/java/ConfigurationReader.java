import java.util.*;
import java.lang.*;
import java.io.*;

public abstract class ConfigurationReader{

    String line;
    FileReader fileReader;
    BufferedReader bufferedReader;
    public ConfigurationReader(String fileName)
    {
        //Begin reading file
        try{
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean hasMoreLines()
    {
        if (this.nextLine() != null){
            return true;
        }
        else {
            return false;
        }
    }

    public String nextLine()
    {
        try{
            line = bufferedReader.readLine();
            return line;
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public abstract void load();
}