package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ViewController implements Initializable {
	
	@FXML private Label regNum;    
	@FXML private Label cusName;   
	@FXML private Label area;
	@FXML private Label  stbC;
	@FXML private Label stbT;
	@FXML private Label sNum;
	@FXML private Label mac;
	@FXML private Label iDate;
	@FXML private Label aDate;
	@FXML private Label emp;
	@FXML private Label phoneNum;
	@FXML private Label mail;
	@FXML private Button okBtn;
	@FXML private Button editBtn;
	
	public static String regNumbers;
    public static String customerNames;
    public static String areaNames;
    public static String phoneNumbers;
    public static String emails;
    public static String stbCodes;
    public static String stbTypes;
    public static String serialNumbers;
    public static String macAddresss;
    public static String installDates;
    public static String activDates;
    public static String employees;
    public static String allThree;
    public static String id;
    public static String idb;
    public static LocalDate insDate;
    public static LocalDate acDate;
    String gettingId;
    
    void initData(String regNumber,String customerName,String areaName, String phoneNumber, String email,
    		String stbCode,String stbType,String serialNumber,String macAddress,LocalDate installDate,
    		LocalDate activDate,String employee){
    	
    	System.out.println("\nSUCCESFULLLY PARSEDDD--Registration Number"+regNumber);
 
    	regNumbers = regNumber;
 		customerNames = customerName;
 		areaNames = areaName;
     	phoneNumbers = phoneNumber;
     	emails = email;
     	stbCodes = stbCode;
     	stbTypes = stbType;
     	serialNumbers = serialNumber;
     	macAddresss = macAddress;
     	installDates = installDate.toString();
     	activDates = activDate.toString();
     	insDate = installDate;
     	acDate = activDate;
     	employees = employee;
     	
     	regNum.setText(regNumbers);
	    cusName.setText(customerNames);
	    area.setText(areaNames);
	    stbC.setText(stbCodes);
	    stbT.setText(stbTypes);
	    sNum.setText(serialNumbers);
	    mac.setText(macAddresss);
	    iDate.setText(installDates);
	    aDate.setText(activDates);
	    ////GET EMPLOYEE NAME FROM ID
	    emp.setText("Employee");
	    phoneNum.setText(phoneNumbers);
	    mail.setText(emails);
     	
    }
    void initData(String regNumber,String customerName,String serialNumber,String macAddress,String Number, LocalDate installDate,
    		LocalDate activDate,String serial,String employee){
    	
    	System.out.println("\nSUCCESFULLLY PARSEDDD--Registration Number"+regNumber);
 
    	regNumbers = regNumber;
 		customerNames = customerName;
     	serialNumbers = serialNumber;
     	macAddresss = macAddress;
     	installDates = installDate.toString();
     	activDates = activDate.toString();
     	insDate = installDate;
     	acDate = activDate;
     	employees = employee;
     	
     	regNum.setText(regNumbers);
	    cusName.setText(customerNames);
	    area.setText(areaNames);
	    stbC.setText(stbCodes);
	    stbT.setText(stbTypes);
	    sNum.setText(serialNumbers);
	    mac.setText(macAddresss);
	    iDate.setText(installDates);
	    aDate.setText(activDates);
	    ////GET EMPLOYEE NAME FROM ID
	    emp.setText("Employee");
	    phoneNum.setText(phoneNumbers);
	    mail.setText(emails);
     	
    }



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	
	
	okBtn.setOnAction(new EventHandler<ActionEvent>() {
		
	    @Override public void handle(ActionEvent e) {
	    	try{
	    		Main main = new Main();
	    		Stage mainStage = main.viewWindow;
	    		mainStage.close();
	    		
	    	}catch(Exception ee){
	    		
	    		ee.printStackTrace();
	    	}
	    	
	    }
	  		});
}

}
