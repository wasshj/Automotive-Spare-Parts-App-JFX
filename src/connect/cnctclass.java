package connect;
import java.sql.Connection;
import java.sql.DriverManager;

public class cnctclass {

	public Connection connection;
	public  Connection getConnection(){


        String dbName="";
        String userName="root";
        String password="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/tutorial?useTimezone=true&serverTimezone=UTC"+dbName,userName,password);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return connection;
    }
		
		//*****************************************
		/*try
		{
			//Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/tutorial", "root", "");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/tutorial", "root", "");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return connection;*/
	}

