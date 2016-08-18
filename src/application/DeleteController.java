package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class DeleteController {
	    Connection conn;
	    PreparedStatement pstmt=null;
	    //ResultSet rs=null;
	    
	public void deleteRecord(String id){
		
		try{
   	   		conn = DBConnect.connect();
          //SQL FOR SELECTING ALL OF CUSTOMER
          String SQL ="DELETE FROM subscriber_info WHERE id = ?";
          //ResultSet
          ResultSet rs = null; //conn.createStatement().executeQuery(SQL);
          int rowsModified;
          pstmt=conn.prepareStatement(SQL);
          
         	// System.out.println("CONTENT:\n"+installDate.getValue().toString()+"\n"+activDate.getValue().toString()+"\n");
         	 System.out.println("ALL TEXT HAVE=========== BEEN ENTERED");
         	 pstmt.setInt(1,Integer.parseInt(id));		                	 
             
              rowsModified = pstmt.executeUpdate();
              System.out.println("Record is updated to Subcriber table!");
              
      }
      catch(Exception ee){
     	 ee.printStackTrace();
     	 System.out.println("EXCEPTION HERE"+ee);
      }
		
	}
	

}
