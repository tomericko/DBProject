import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 16/06/2016.
 */
public class readScript {

    public List<String> readScriptFromFile(File file){
        String content = new String();
        StringBuffer sb = new StringBuffer();
        List<String> commands = new ArrayList<String>();
        try
        {
            FileReader fr = new FileReader(file);
            // be sure to not have line starting with "--" or "/*" or any other non aplhabetical character

            BufferedReader br = new BufferedReader(fr);

            while((content = br.readLine()) != null)
            {
                sb.append(content);
            }
            br.close();

            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] inst = sb.toString().split(";");

            for(int i = 0; i<inst.length; i++)
            {
                // we ensure that there is no spaces before or after the request string
                // in order to not execute empty statements
                if(!inst[i].trim().equals(""))
                {
                    commands.add(inst[i]);
                }
            }
        }
        catch(Exception e)
        {
        }
        return commands;
    }
    public static void main(String[] args) throws Exception {
        File test = new File("test.txt");
        readScript rs = new readScript();
        List<String> commands = rs.readScriptFromFile(test);
        int arrayListSize = commands.size();
        for(int i = 0; i < arrayListSize; i++)
        {
            System.out.println(commands.get(i));
        }
    }
}


