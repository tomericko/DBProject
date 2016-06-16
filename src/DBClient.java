


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

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

		/* set result String */

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

    /**************execution example**************************************************/
    public static void main(String[] args) throws Exception {

        DBClient dbClient = new DBClient();

        // send DDL query to update server's DB
        dbClient.sendDDLQuery(
                "INSERT INTO students (name, phone, id, age) VALUES ('ben', 0586182323, 203617884, 18)");

        String output = null;

        try {
            // send DML query to DB server and get result
            output = dbClient.sendDMLQuery("SELECT * from students");
        } catch (Exception e) {
            output = "#" + e.toString();
        }

        System.out.println(output);

        try {
            // send DML query to DB server and get result
            output = dbClient.sendDMLQuery("SELECT name, age from students");
        } catch (Exception e) {
            output = "#" + e.toString();
        }

        System.out.println(output);
    }

}
