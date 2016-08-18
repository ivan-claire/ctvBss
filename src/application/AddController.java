package application;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.sql.Connection;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddController implements Initializable {
	
	@FXML private TextField regNumber;    
    @FXML private TextField customerName;   
    @FXML private TextField areaName;
    @FXML private TextField phoneNumber;
    @FXML private TextField email;
    @FXML private TextField stbCode;
    @FXML private TextField stbType;
    @FXML private TextField serialNumber;
    @FXML private TextField macAddress;
    @FXML private DatePicker installDate;
    @FXML private DatePicker activDate;
    @FXML private Text errorMessage;
    @FXML private Button saveBtn;
    @FXML private Button editBtn;
    //@FXML private TextField employee;
    
    @FXML private Button dash;
    @FXML private Button subsInfo;
    @FXML private Button alloPackages;
    @FXML private Button packageSetup;
    
    Connection conn;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    String message = "";
    TableView tableview;
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		errorMessage.setVisible(false);
		Main main = new Main();
		int lastID = main.lastID;
		int nextID = lastID+1;
		System.out.println("MAXIMUM ID::::::"+lastID);
		/////////////
		regNumber.setText("RN"+nextID);
		editBtn.setVisible(false);
		// TODO Auto-generated method stub
		saveBtn.setOnAction(new EventHandler<ActionEvent>() {
			
		    @Override public void handle(ActionEvent e) {
		    	
		    	// SQLException, ClassNotFoundException{      
		    		    
		             try{
		          	   conn = DBConnect.connect();
		                 //SQL FOR SELECTING ALL OF CUSTOMER
		                 String SQL ="INSERT INTO subscriber_info(id,regNumber,subsName,areaName,phoneNumber,email,stbCode,stbType,"
		                 		+ "serialNumber,macAddress,installDate,activDate,employeeId)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		                 //ResultSet
		                 ResultSet rs = null; //conn.createStatement().executeQuery(SQL);
		                 int rowsModified;
		                 pstmt=conn.prepareStatement(SQL);
		                 
		                 if(regNumber.getText().isEmpty()||customerName.getText().isEmpty() || areaName.getText().isEmpty() 
		                		 || phoneNumber.getText().isEmpty() || email.getText().isEmpty() || stbCode.getText().isEmpty() || stbType.getText().isEmpty() 
		                		 || serialNumber.getText().isEmpty() || macAddress.getText().isEmpty()
		                		 || installDate.getValue().toString()==null || activDate.getValue().toString()==null) {
		                	 
		                	 System.out.println("CONTENT:\n"+regNumber.getText()+"\n"+customerName.getText()+"\n"
		                			 +areaName.getText()+"\n"+phoneNumber.getText()+"\n"+stbCode.getText()+"\n"
		                			 +stbType.getText()+"\n"+serialNumber.getText()+"\n"+macAddress.getText()+"\n");
		                	 System.out.println("ALL TEXT HAVE NOOOOOT BEEN ENTERED");
		                	 errorMessage.setVisible(true);
		                	 errorMessage.setText("ALL Information must be Entered");
				            
		                 }else{
		                	 
		                	// System.out.println("CONTENT:\n"+installDate.getValue().toString()+"\n"+activDate.getValue().toString()+"\n");
		                	 System.out.println("ALL TEXT HAVE=========== BEEN ENTERED");
		                	 pstmt.setInt(1,lastID+1);		                	 
			                 pstmt.setString(2,regNumber.getText()+"#");
			                 pstmt.setString(3,customerName.getText()+"#");
			                 pstmt.setString(4,areaName.getText()+"#");
			                 pstmt.setString (5,phoneNumber.getText()+"#");
			                 pstmt.setString (6,email.getText()+"#");
			                 pstmt.setString (7,stbCode.getText()+"#");
			                 pstmt.setString(8,stbType.getText()+"#");
			                 pstmt.setString(9,serialNumber.getText()+"#");
			                 pstmt.setString(10,macAddress.getText()+"#");
			                 pstmt.setString(11,installDate.getValue().toString());
			                 pstmt.setString(12,activDate.getValue().toString());
			                 pstmt.setInt(13, 2);
			                 
			                 rowsModified = pstmt.executeUpdate();
			                 System.out.println("ROWS MODIFIED"+rowsModified);
			                 
			                 //back to original page
			                Parent root = FXMLLoader.load(getClass().getResource("view/subscriberInfo.fxml"));
			                Stage mainStage = main.parentWindow;
			                
			                //main.start(mainStage);
		                	main.subsInfoPage(mainStage); 
		                 }
		                 
		             }
		             catch(Exception ee){
		            	 ee.printStackTrace();
		            	 System.out.println("EXCEPTION HERE"+ee);
		             }
		    }
		});
		
		 dash.setOnAction(new EventHandler<ActionEvent>() {
				
			 @Override public void handle(ActionEvent e) {
			    	try {
			    		
			    		System.out.println("DASHBOARD CLICKED");
			            Parent root = FXMLLoader.load(getClass().getResource("view/subscriberInfo.fxml"));
		                Stage mainStage = main.parentWindow;		               
		                main.subsInfoPage(mainStage);
		                
			            } catch(Exception ee) {
			               ee.printStackTrace();
			              }
			    }
			});
		
		
		 subsInfo.setOnAction(new EventHandler<ActionEvent>() {
				
			 @Override public void handle(ActionEvent e) {
			    	try {
			    		
			    		Main main = new Main();
			    		System.out.println("LOADING SUBSCRIBERS INFO");
			            Parent root = FXMLLoader.load(getClass().getResource("view/subscriberInfo.fxml"));
			            Stage mainStage = main.parentWindow;
		                
		                main.subsInfoPage(mainStage);
		                
			            } catch(Exception ee) {
			               ee.printStackTrace();
			              }
			    }
			});
		 
		 alloPackages.setOnAction(new EventHandler<ActionEvent>() {
				
			 @Override public void handle(ActionEvent e) {
			    	try {
			    		
			    		AllocatedPackages packagesAllocated = new AllocatedPackages();
			    		System.out.println("LOADING ALLOCATED PACKAGES");
			            Parent root = FXMLLoader.load(getClass().getResource("view/allocatePackages.fxml"));
			            Stage mainStage = packagesAllocated.parentWindow;
		                
		                packagesAllocated.subsInfoPage(mainStage);
		                
			            } catch(Exception ee) {
			               ee.printStackTrace();
			              }
			    }
			});
		 
			 
			 packageSetup.setOnAction(new EventHandler<ActionEvent>() {
					
				 @Override public void handle(ActionEvent e) {
				    	try {
				    		
				    		System.out.println("DASHBOARD CLICKED");
				            Parent root = FXMLLoader.load(getClass().getResource("view/subscriberInfo.fxml"));
			                Stage mainStage = main.parentWindow;		               
			                main.subsInfoPage(mainStage);
			                
				            } catch(Exception ee) {
				               ee.printStackTrace();
				              }
				    }
				});
			 
	}
	
	

}
