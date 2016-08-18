package application;

import java.lang.reflect.Field;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.Connection;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class EditController extends Main implements Initializable {
	
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
    
    @FXML private Button dash;
    @FXML private Button subsInfo;
    @FXML private Button alloPackages;
    @FXML private Button packageSetup;
    
    //@FXML private TextField employee;
    
    Connection conn;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    String message = "";
    TableView tableview;
    Main main;
    int counter =0;
    
    public static String regNumberFin;
    public static String customerNameFin;
    public static String areaNameFin;
    public static String phoneNumberFin;
    public static String emailFin;
    public static String stbCodeFin;
    public static String stbTypeFin;
    public static String serialNumberFin;
    public static String macAddressFin;
    public static LocalDate installDateFin;
    public static LocalDate activDateFin;
    public static String employeeFin;
    public static String allThree;
    public static String id;
    public static String idb;
    
 void initData(Object data){
    	
    	//System.out.println("\nSUCCESFULLLY PARSEDDD"+data);
 
    	String rowValue = data.toString();
    	//List<String> tokens = getTokens("^[^[#]]+",rowValue);
    	//List<String> tokens = getTokens("[a-zA-Z0-9]+",rowValue);
    	String[] tokens = rowValue.split("#");
    	
    	for(int i=0; i<tokens.length; i++){
    		String values = tokens[i];
    		 id = tokens[0];
    		//regNumberFin = tokens[0];
    		customerNameFin = tokens[1].substring(1);
    		areaNameFin = tokens[2].substring(1);
        	phoneNumberFin = tokens[3].substring(1);
        	emailFin = tokens[4].substring(1);
        	stbCodeFin = tokens[5].substring(1);
        	stbTypeFin = tokens[6].substring(1);
        	serialNumberFin = tokens[7].substring(1);
        	macAddressFin = tokens[8].substring(1);
        	allThree = tokens[9].substring(1);
    	}
    	
    	String[] token = allThree.split(",");
		String[] ids = id.split(",");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" yyyy-MM-dd");
		//formatter = formatter.withLocale( Locale.US );  
		for(int i=0; i<token.length; i++){
    		String values = token[i];
    
    		installDateFin = LocalDate.parse(token[0],formatter);
    		activDateFin = LocalDate.parse(token[1],formatter);
    		employeeFin = token[2];
		}
		
		for(int i=0; i<ids.length; i++){
    		String values = ids[i];
    		 //idb = ids[0];
    		
    		regNumberFin = ids[1].substring(1);
    		gettingId = ids[1].substring(3);
		}
			//List<String> idbb = getTokens("[0-9]+",idb);
			//id=idbb.get(0);
			//System.out.println("ID NUMBER"+id);
		id = gettingId;
    }
 
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		errorMessage.setVisible(false);
		main = new Main();
	
		System.out.println("MAXIMUM ID:"+lastID);
		int lastID = main.lastID;
		int nextID = lastID+1;
		//String reg = main.result;
		saveBtn.setVisible(false);
		System.out.println("\nReg Number:"+stbTypeFin);
		
		regNumber.setText(regNumberFin);
		customerName.setText(customerNameFin);
		areaName.setText(areaNameFin);
		phoneNumber.setText(phoneNumberFin);
		email.setText(emailFin);
		stbCode.setText(stbCodeFin);
		stbType.setText(stbTypeFin);
		serialNumber.setText(serialNumberFin);
		macAddress.setText(macAddressFin);
		
		installDate.setValue(installDateFin);
		activDate.setValue(activDateFin);
		// TODO Auto-generated method stub
		editBtn.setOnAction(new EventHandler<ActionEvent>() {
			
		    @Override public void handle(ActionEvent e) {
		    	
		    	// SQLException, ClassNotFoundException{      
		    		    
		             try{
		          	   conn = DBConnect.connect();
		                 //SQL FOR SELECTING ALL OF CUSTOMER
		                 String SQL ="UPDATE subscriber_info SET regNumber=?,subsName=?,areaName=?,phoneNumber=?,email=?,stbCode=?,stbType=?,"
		                 		+ "serialNumber=?,macAddress=?,installDate=?,activDate=?,employeeId=? WHERE id = ?";
		                 //ResultSet
		                 ResultSet rs = null; //conn.createStatement().executeQuery(SQL);
		                 int rowsModified;
		                 pstmt=conn.prepareStatement(SQL);
		                 
		                 if(regNumber.getText().isEmpty()||customerName.getText().isEmpty() || areaName.getText().isEmpty() 
		                		 || phoneNumber.getText().isEmpty() || email.getText().isEmpty() || stbCode.getText().isEmpty() || stbType.getText().isEmpty() 
		                		 || serialNumber.getText().isEmpty() || macAddress.getText().isEmpty()
		                		 || installDate.getValue().toString()==null || activDate.getValue().toString()==null) {
		                	 
		                	 System.out.println("CONTENT:\n"+regNumber.getText()+"\n"+customerName.getText()+"\n"
		                			 +areaName.getText()+"\n"+phoneNumber.getText()+"\n"+email.getText()+"\n"+stbCode.getText()+"\n"
		                			 +stbType.getText()+"\n"+serialNumber.getText()+"\n"+macAddress.getText()+"\n");
		                	 System.out.println("ALL TEXT HAVE NOOOOOT BEEN ENTERED");
		                	 errorMessage.setVisible(true);
		                	 errorMessage.setText("ALL Information must be Entered");
				            
		                 }else{
		                	 
		                	// System.out.println("CONTENT:\n"+installDate.getValue().toString()+"\n"+activDate.getValue().toString()+"\n");
		                	 System.out.println("ALL TEXT HAVE=========== BEEN ENTERED");
		                	 pstmt.setInt(13,Integer.parseInt(id));		                	 
			                 pstmt.setString(1,regNumber.getText()+"#");
			                 pstmt.setString(2,customerName.getText()+"#");
			                 pstmt.setString(3,areaName.getText()+"#");
			                 pstmt.setString (4,phoneNumber.getText()+"#");
			                 pstmt.setString (5,email.getText()+"#");
			                 pstmt.setString (6,stbCode.getText()+"#");
			                 pstmt.setString(7,stbType.getText()+"#");
			                 pstmt.setString(8,serialNumber.getText()+"#");
			                 pstmt.setString(9,macAddress.getText()+"#");
			                 pstmt.setString(10,installDate.getValue().toString());
			                 pstmt.setString(11,activDate.getValue().toString());
			                 pstmt.setInt(12, 2);
			                
			                 rowsModified = pstmt.executeUpdate();
			                 System.out.println("Record is updated to Subcriber table!");
			                 
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
	
    protected List<String> getTokens(String pattern,String text)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
    

}
