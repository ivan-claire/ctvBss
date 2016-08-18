package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	 
	    private static Connection conn;
	    private static String url = "jdbc:mysql://localhost:3306/ctvbss";
	    //jdbc:mysql://localhost:3307/dbname,"usrname","password"
	    private static String user = "root";
	    private static String pass = "";
	    
	    public static Connection connect() throws SQLException{
	        try{
	        	
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	            
	            System.out.println("connecting to the database...");
	            
	        }catch(ClassNotFoundException cnfe){
	            System.err.println("Error: "+cnfe.getMessage());
	        }catch(InstantiationException ie){
	            System.err.println("Error: "+ie.getMessage());
	        }catch(IllegalAccessException iae){
	            System.err.println("Error: "+iae.getMessage());
	        }
	        
	        conn = DriverManager.getConnection(url,user,pass);
	        System.out.print("Database is connected !");
	        return conn;
	    }
	    
	 

	    public static Connection getConnection() throws SQLException, ClassNotFoundException{
	        if(conn !=null && !conn.isClosed())
	            return conn;
	        connect();
	        return conn;
	    }
	}