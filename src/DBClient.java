


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/* Present a DB client who connects to server (detailed by conf file),
 * sends queries to the server and replies the server result */
public class DBClient {

    private Connection connect = null;
    private Statement statement = null;

    public static final String dbClass = "com.mysql.jdbc.Driver";

    /* constructor
     * establish connection with server */
    public DBClient() throws Exception {

		/* extract configuration from conf.txt file located locally in
		 * current directory */
        File conf = new File("conf.txt");
        BufferedReader br = new BufferedReader(new FileReader(conf));
        String url = br.readLine();
        String userName = br.readLine();
        String password = br.readLine();

        // register JDBC driver
        Class.forName(dbClass);

        // Open connection
        connect = DriverManager.getConnection(url, userName, password);
        statement = connect.createStatement();
    }

    /* send DML query to server and return server's result, or
     * in case of error - throw the server's exception */
    public String sendDMLQuery(String query) throws Exception {

        ResultSet resultSet = statement.executeQuery(query);
        ResultSetMetaData rsmd = resultSet.getMetaData();

		/* set result Strin */

        String result = "";
        int columnsNumber = rsmd.getColumnCount();

        for (int i = 1; i <= columnsNumber; i++) {
            if (i > 1)
                result += ",  ";
            result += rsmd.getColumnName(i);
        }
        result += "\n";

        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1)
                    result += ",  ";
                String columnValue = resultSet.getString(i);
                result += columnValue;
            }
            result += "\n";
        }
        return result;
    }

    /* send DDL query to update server's DB,
     * in case of error - throw the server's exception */
    public void sendDDLQuery(String query) throws Exception {

        statement.executeUpdate(query);
    }



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

}
