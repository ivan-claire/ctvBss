package application;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.Connection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddAllocatedPackages implements Initializable {
	
	@FXML private TextField regNumber;    
    @FXML private TextField customerName;   
    @FXML private ComboBox selectPackage ;
    @FXML private TextField period;
    @FXML private TextField amountPaid;
    @FXML private TextField status;
    @FXML private DatePicker reqDate;
    @FXML private DatePicker endDate;
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
		AllocatedPackages alloPackage = new AllocatedPackages();
		int lastID = alloPackage.lastID;
		int nextID = lastID+1;
		System.out.println("MAXIMUM ID::::::"+lastID);
		
		try{
			
			List<List<String>> outer = new ArrayList<>();
			 conn = DBConnect.connect();
	         //SQL FOR SELECTING ALL OF CUSTOMER
	         String query = "SELECT packageName FROM package_setup";
	         //ResultSet
	         ResultSet rs = null; //conn.createStatement().executeQuery(SQL);
	         int rowsModified;
	         pstmt=conn.prepareStatement(query);
			 rs = pstmt.executeQuery(query);
			 ResultSetMetaData rsmd = rs.getMetaData();
			 int columnsNumber = rsmd.getColumnCount();
			 while (rs.next()) {
			     List<String> inner = new ArrayList<>(); 
			     for(int i = 1; i <= columnsNumber; i++) {
			        inner.add(rs.getString(i));
			     }    
			     outer.add(inner);     
			     
			 }
			 
			ObservableList<List<String>> packageList = FXCollections.observableArrayList(outer);
			
			for(List<String> Package:packageList){
				
				ObservableList<String> eachPackage = FXCollections.observableArrayList(Package);
				System.out.println("\nArrayList Content\n"+eachPackage);
				selectPackage.setItems(eachPackage);
			}
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		//regNumber.setText("RN"+nextID);
		editBtn.setVisible(false);
		// TODO Auto-generated method stub
		saveBtn.setOnAction(new EventHandler<ActionEvent>() {
			
		    @Override public void handle(ActionEvent e) {
		    	
		    	// SQLException, ClassNotFoundException{      
		    		    
		             try{
		          	   conn = DBConnect.connect();
		                 //SQL FOR SELECTING ALL OF CUSTOMER
		                 String SQL ="INSERT INTO allocated_packages(id,regNumber,reqPackage,period,amountPaid,dateRequested,"
		                 		+ "		endingDate,Status,EmployeeId)VALUES(?,?,?,?,?,?,?,?,?)";
		                 //ResultSet
		                 ResultSet rs = null; //conn.createStatement().executeQuery(SQL);
		                 int rowsModified;
		                 pstmt=conn.prepareStatement(SQL);
		                 
		                 if(regNumber.getText().isEmpty()|| selectPackage.getPromptText().isEmpty() 
		                		 || period.getText().isEmpty() || amountPaid.getText().isEmpty() || status.getText().isEmpty() 
		                		 || reqDate.getValue().toString()==null || endDate.getValue().toString()==null) {
		                	 
		                	 System.out.println("CONTENT:\n"+regNumber.getText()+"\n"+customerName.getText()+"\n"
		                			 +selectPackage.getValue()+"\n"+amountPaid.getText()+"\n");
		                	 System.out.println("ALL TEXT HAVE NOOOOOT BEEN ENTERED");
		                	 errorMessage.setVisible(true);
		                	 errorMessage.setText("ALL Information must be Entered");
				            
		                 }else{
		                	 
		                	// System.out.println("CONTENT:\n"+installDate.getValue().toString()+"\n"+activDate.getValue().toString()+"\n");
		                	 System.out.println("ALL TEXT HAVE=========== BEEN ENTERED");
		                	 pstmt.setInt(1,lastID+1);		                	 
			                 pstmt.setString(2,regNumber.getText()+"#");
			                 pstmt.setString(3,selectPackage.getValue()+"#");
			                 pstmt.setString (4,period.getText()+"#");
			                 pstmt.setString (5,amountPaid.getText()+"#");
			                 pstmt.setString (6,status.getText()+"#");
			                 pstmt.setString(7,reqDate.getValue().toString());
			                 pstmt.setString(8,endDate.getValue().toString());
			                 pstmt.setInt(9, 2);
			                 
			                 rowsModified = pstmt.executeUpdate();
			                 System.out.println("ROWS MODIFIED"+rowsModified);
			                 
			                 //back to original page
			                Parent root = FXMLLoader.load(getClass().getResource("view/allocatePackages.fxml"));
			                Stage mainStage = alloPackage.parentWindow;
		                	alloPackage.subsInfoPage(mainStage); 
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
