import java.io*;
import java.lang.Object;

public class ConfigurationReader{

    String line;
    FileReader fileReader;
    BufferedReader bufferedReader;
    public ConfigurationReader(String fileName)
    {
        //Begin reading file
        try{
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
        }catch (Exception e){
            //error message
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
        line = bufferedReader.readLine();
    }

    public abstract void load();
}