package application;
	
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import javafx.application.Application;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class AllocatedPackages extends Application {
	
	 //TABLE VIEW AND DATA
    private ObservableList<ObservableList> data;
    public static TableView tableview;
    Button addSubsPackage;
    Button dash;
    Button subsInfo;
    Button alloPackages;
    Button packageSetup;
    TableColumn column;
    ImageView tableImage;
    ImageView boxImage;
    Text hash;
    Scene scene;
    Scene scene1;
    Text errorMessage;
   
    public static String regNumbers;    
    public String customerNames;   
    private String requestedPackages;
    //public static String contacts;
    public static String period;
    public static String amountPaid;
    public static LocalDate dateRequested;
    public static LocalDate endingDate;
    public static String employees;
    public static String status;
    public static Stage parentWindow;
    public static int lastID;
    public static String allThree;
    public ObservableList clickedRow;
    public static String id;
    String  gettingId;
    String tokenValues;
    int gettingLastId= 0;
    int maxId = 0;
    int counter = 0;
    int counts;
    int sum;
    public static Object value;
    public static Stage viewWindow;
    
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) {
		
		Parent root;
		URL path = getClass().getResource("view/logo.JPG");
		parentWindow = stage;
		try {
			root = FXMLLoader.load(getClass().getResource("view/allocatePackages.fxml"));
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//URL path = getClass().getResource("view/image.JPG");
			//TableView
	        //tableview = new TableView();
			tableview = (TableView) scene.lookup("#info");
			tableview.setEditable(true);
			
			addSubsPackage = (Button) scene.lookup("#addSubs");
			boxImage = (ImageView) scene.lookup("#boximage");
			Image image = new Image("file:images/logo.jpg");
            boxImage.setImage(image);
            hash = new Text();
            dash = (Button) scene.lookup("#dash");
            subsInfo = (Button) scene.lookup("#subsInfo");
            alloPackages = (Button) scene.lookup("#alloPackages");
            packageSetup = (Button) scene.lookup("#packageSetup");
            
            tableview.getSelectionModel().setCellSelectionEnabled(true);
            
            final ObservableList<TablePosition> selectedCells = tableview.getSelectionModel().getSelectedCells();
			selectedCells.addListener(new ListChangeListener<TablePosition>() {
			    @Override
			    public void onChanged(Change change) {
			        for (TablePosition pos : selectedCells) {
			            System.out.println("Cell selected in row "+pos.getRow()+" and column "+pos.getColumn());
			            clickedRow = tableview.getSelectionModel().getSelectedItems();
			            Object rows = tableview.getSelectionModel().getSelectedItem();
			            
			            EditController control = new EditController();
			            for (Field field : rows.getClass().getDeclaredFields()) {
			                field.setAccessible(true); // You might want to set modifier to public first.
			                
							try {
								value = field.get(rows);
								//String values= field.get(rows).toString();
								if (value != null) {
				                    System.out.println(field.getName() + "=" + value);
				                    //System.out.println("STRING FORM\n" + values);
				                    
				                    String rowValue = value.toString();
				                	String[] tokens = rowValue.split("#");
				                	
				                	for(int i=0; i<tokens.length; i++){
				                		String values = tokens[i];
				                		 id = tokens[0];
				                		//regNumberFin = tokens[0];
				                		customerNames = tokens[1].substring(1);
				                		requestedPackages = tokens[2].substring(1);
				                    	period = tokens[3].substring(1);
				                    	amountPaid = tokens[4].substring(1);
				                    	/*dateRequested = tokens[5].substring(1);
				                    	stbTypes = tokens[6].substring(1);
				                    	serialNumbers = tokens[7].substring(1);
				                    	macAddresss = tokens[8].substring(1);*/
				                    	allThree = tokens[9].substring(1);
				                	}
				                	
				                	String[] token = allThree.split(",");
				            		String[] ids = id.split(",");
				            		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" yyyy-MM-dd");
				            		//formatter = formatter.withLocale( Locale.US );  
				            		for(int i=0; i<token.length; i++){
				                		String values = token[i];
				                
				                		dateRequested = LocalDate.parse(token[0],formatter);
				                		endingDate = LocalDate.parse(token[1],formatter);
				                		status = token[2];
				                		employees = token[3];
				            		}
				                	
				                	
				                    //getting the id of clicked row
				                    for(int i=0; i<tokens.length; i++){
				                		String values = tokens[i];
				                		 tokenValues = tokens[0];
				                	}
				                	
				            		//String[] ids = tokenValues.split(",");
				            		for(int i=0; i<ids.length; i++){
				                		String values = ids[i];
				                		 //gettingId = ids[0];
				                		regNumbers = ids[1].substring(1);
				                		 gettingId = ids[1].substring(3);
				             
				            		}
				            			//List<String> idbb = control.getTokens("[0-9]+",gettingId);
				            			//id=idbb.get(0);
				            		    id = gettingId;
				            			System.out.println("ID NUMBER: "+id);
				                    
				                    FXMLLoader loader = new FXMLLoader(getClass().getResource("view/editsubscriber.fxml"));
									Parent root1 = (Parent) loader.load();
									EditController controller = loader.getController(); 
			                    	controller.initData(value);
				                }
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
			                
			            }
			        }
			    }
			});
          
			addSubsPackage.setOnAction(new EventHandler<ActionEvent>() {
				
			    @Override public void handle(ActionEvent e) {
			    	try {
			      
			    		
			            Parent root = FXMLLoader.load(getClass().getResource("view/addAllocatedPackages.fxml"));
			            Stage mainStage;
			            mainStage = parentWindow;
			            mainStage.getScene().setRoot(root); 
			            boxImage = (ImageView) mainStage.getScene().lookup("#boximage");
						Image image = new Image("file:images/logo.jpg");
			            boxImage.setImage(image);
			            
			            } catch(Exception ee) {
			               ee.printStackTrace();
			              }
			    }
			});
			
	        buildData();
	        //Main Scene
	        //Scene scene = new Scene(tableview); 
	        dash.setOnAction(new EventHandler<ActionEvent>() {
				
			    @Override public void handle(ActionEvent e) {
			    	try {
			    		
			            System.out.println("DASHBOARD CLICKED");
			            Parent root = FXMLLoader.load(getClass().getResource("view/subscriberInfo.fxml"));
		                Stage mainStage = parentWindow;		               
		                buildData();
		                
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
			            Stage mainStage = parentWindow;
		                
		                main.subsInfoPage(mainStage);
		                
			            } catch(Exception ee) {
			               ee.printStackTrace();
			              }
			    }
			});
			
	        alloPackages.setOnAction(new EventHandler<ActionEvent>() {
				
			    @Override public void handle(ActionEvent e) {
			    	try {
			    		
			            System.out.println("LOADING ALLOCATED PACKAGES");
			            Parent root = FXMLLoader.load(getClass().getResource("view/allocatePackages.fxml"));
		                Stage mainStage = parentWindow;		               
		                buildData();
		                
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
		                Stage mainStage = parentWindow;		               
		                buildData();
		                
			            } catch(Exception ee) {
			               ee.printStackTrace();
			              }
			    }
			});
			
		      stage.setScene(scene);
		      stage.setMaximized(true);
		      stage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	}
	
	//CONNECTION DATABASE
    @SuppressWarnings({ "unchecked", "rawtypes", "rawtypes", "rawtypes", "rawtypes" })
	public void buildData(){
          Connection c ;
          data = FXCollections.observableArrayList();
          
          try{
            c = DBConnect.connect();
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from allocated_packages";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);
           
            System.out.println("RESULT SET:/n"+rs);
            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<=rs.getMetaData().getColumnCount()+3; i++){
                //We are using non property style for making dynamic table
                final int j = i;  
               // TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                if (i==0){
                	
                TableColumn col = new TableColumn( "Id" );
                col.setCellFactory( new Callback<TableColumn, TableCell>()
                {
                    @Override
                    public TableCell call( TableColumn p )
                    {
                        return new TableCell()
                        {
                            @Override
                            public void updateItem( Object item, boolean empty )
                            {
                                super.updateItem( item, empty );
                                setGraphic( null );
                                setText( empty ? null : getIndex() + 1 + "" );
                            }
                        };
                    }
                });
                
                tableview.getColumns().addAll(col); 
                //System.out.println("Column ["+i+"] ");
                
                }else if(i==1){
                
                TableColumn col = new TableColumn("Registration Number");	
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){  
                	
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {      
                    	int size = param.getValue().get(j).toString().length();
                    	//System.out.println("STRING VLUE::"+param.getValue().get(j).toString().substring(2,size-1));
                    	gettingLastId = Integer.parseInt(param.getValue().get(j).toString().substring(2,size-1)); 
                    	
                    	if(gettingLastId > lastID){
                    		lastID = gettingLastId;
                    	}
                    	System.out.println("LAST ID:::"+lastID);
                        return new SimpleStringProperty(param.getValue().get(j).toString().substring(0,size-1));                        
                    }                    
                });
                
                TableColumn col1 = new TableColumn("Customer Name");
                
                tableview.getColumns().addAll(col,col1); 
                System.out.println("Column ["+i+"] ");
 
                /*col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){  
                	
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {      
                    	int size = param.getValue().get(j).toString().length();
                    	if(size>1){
                    		
                         return new SimpleStringProperty(param.getValue().get(j).toString().substring(0,size-1));
                        
                    	}else{
                    		
                    		return new SimpleStringProperty(param.getValue().get(j).toString());
                    	}
                    }                    
                });

                tableview.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");*/
                
                }else if(i==2){
                
                TableColumn col = new TableColumn("Requested Packages");	
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){  
                	
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {      
                    	int size = param.getValue().get(j).toString().length();
                    	if(size>1){
                    		
                            return new SimpleStringProperty(param.getValue().get(j).toString().substring(0,size-1));
                           
                       	}else{
                       		
                       		return new SimpleStringProperty(param.getValue().get(j).toString());
                       	}                    
                    }                    
                });

                tableview.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
                
                }else if (i==3){
                	
                TableColumn col = new TableColumn("Period");
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){  
                	
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {      
                    	int size = param.getValue().get(j).toString().length();
                    	
                    	if(size>1){
                    		
                            return new SimpleStringProperty(param.getValue().get(j).toString().substring(0,size-1));
                           
                       	}else{
                       		
                       		return new SimpleStringProperty(param.getValue().get(j).toString());
                       	}                    
                    }                    
                });

                tableview.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
                
                }else if (i==4){
                	
                TableColumn col = new TableColumn("Amount Paid");
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){  
                	
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {      
                    	int size = param.getValue().get(j).toString().length();
                    	
                    	if(size>1){
                    		
                            return new SimpleStringProperty(param.getValue().get(j).toString().substring(0,size-1));
                           
                       	}else{
                       		
                       		return new SimpleStringProperty(param.getValue().get(j).toString());
                       	}                    
                    }                    
                });

                tableview.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
                
                }else if(i==5){
                
                TableColumn col = new TableColumn("Date Requested");	
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){  
                	
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {      
                    	int size = param.getValue().get(j).toString().length();
                    	
                    	if(size>1){
                    		
                            return new SimpleStringProperty(param.getValue().get(j).toString().substring(0,size-1));
                           
                       	}else{
                       		
                       		return new SimpleStringProperty(param.getValue().get(j).toString());
                       	}                   
                    }                    
                });

                tableview.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
                
                }else if (i==6){
                	
                TableColumn col = new TableColumn("Ending Date");
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){  
                	
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {      
                    	int size = param.getValue().get(j).toString().length();
                    	
                    	if(size>1){
                    		
                            return new SimpleStringProperty(param.getValue().get(j).toString().substring(0,size-1));
                           
                       	}else{
                       		
                       		return new SimpleStringProperty(param.getValue().get(j).toString());
                       	}                   
                    }                    
                });

                tableview.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
                
                }else if (i==7){
                	
                TableColumn col = new TableColumn("Status");
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){  
                	
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {      
                    	int size = param.getValue().get(j).toString().length();
                    	
                    	if(size>1){
                    		
                            return new SimpleStringProperty(param.getValue().get(j).toString().substring(0,size-1));
                           
                       	}else{
                       		
                       		return new SimpleStringProperty(param.getValue().get(j).toString());
                       	}                     
                    }                    
                });

                tableview.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
                
                }else if(i==8){
                
                TableColumn col = new TableColumn("Employee");	
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){  
                	
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {      
                    	int size = param.getValue().get(j).toString().length();
                    	if(size>1){
                    		
                            return new SimpleStringProperty(param.getValue().get(j).toString().substring(0,size-1));
                           
                       	}else{
                       		
                       		return new SimpleStringProperty(param.getValue().get(j).toString());
                       	}
                    }                    
                });
                

                tableview.getColumns().addAll(col); 
                System.out.println("Column ["+i+"] ");
                
            }else if(i==9){
            	
            	/* this involves three classes: ImageModel,FavoriteImage, ImageClickCellFactory*/
            	
                TableColumn col = new TableColumn("View");
                col.setPrefWidth(90);
                col.setCellValueFactory(
                        new PropertyValueFactory<ViewImageModel, FavoriteImage>("favoriteImage"));

                ViewImageClickCellFactory cellFactory
                        = new ViewImageClickCellFactory(
                                new ViewClickMouseEventHandler());

                col.setCellFactory(cellFactory);
                          
            	tableview.getColumns().addAll(col); 
            	
                System.out.println("Column ["+i+"] ");
                

            }else if(i==10){
                
            	TableColumn col = new TableColumn("Edit");
                col.setPrefWidth(90);
                col.setCellValueFactory(
                        new PropertyValueFactory<ImageModel, FavoriteImage>("favoriteImage"));

                ImageClickCellFactory cellFactory
                        = new ImageClickCellFactory(
                                new EditClickMouseEventHandler());

                col.setCellFactory(cellFactory);
                
               // table.getColumns().addAll(nameCol, favoriteCol);

                 
            	tableview.getColumns().addAll(col); 
            	
                System.out.println("Column ["+i+"] ");
                
            }else if(i==11){
                
            	TableColumn col = new TableColumn("Delete");
                col.setPrefWidth(90);
                col.setCellValueFactory(
                        new PropertyValueFactory<DeleteImageModel, FavoriteImage>("favoriteImage"));

                DeleteImageClickCellFactory cellFactory
                        = new DeleteImageClickCellFactory(
                                new DeleteClickMouseEventHandler());

                col.setCellFactory(cellFactory);
               
            	tableview.getColumns().addAll(col); 
            	
                System.out.println("Column ["+i+"] ");
            }
                
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            int count=0;
            while(rs.next()){
                //Iterate Row
            	 
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                    
                }
                System.out.println("Row [1] added "+row );
                data.add(row);
                count++;
            }
            //FINALLY ADDED TO TableView
            tableview.setItems(data);
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
      }

    
	public void subsInfoPage(Stage mainStage){
		
		Parent root;
		//URL path = getClass().getResource("view/logo.JPG");
		parentWindow = mainStage;
		try {
			root = FXMLLoader.load(getClass().getResource("view/allocatePackages.fxml"));
			//scene = new Scene(root);
			mainStage.getScene().setRoot(root);
			tableview = (TableView) mainStage.getScene().lookup("#info");// .getScene().setRoot(root).lookup("");
	        buildData();
	        //tableview = (TableView) scene.lookup("#info");
	        //tableview = (TableView) mainStage.getScene().lookup("#info");
			tableview.setEditable(true);
			
			addSubsPackage = (Button) mainStage.getScene().lookup("#addSubs");
			boxImage = (ImageView) mainStage.getScene().lookup("#boximage");
			Image image = new Image("file:images/logo.jpg");
            boxImage.setImage(image);
            hash = new Text();
          
          
            tableview.getSelectionModel().setCellSelectionEnabled(true);
            
            final ObservableList<TablePosition> selectedCells = tableview.getSelectionModel().getSelectedCells();
			selectedCells.addListener(new ListChangeListener<TablePosition>() {
			    @Override
			    public void onChanged(Change change) {
			        for (TablePosition pos : selectedCells) {
			            System.out.println("Cell selected in row "+pos.getRow()+" and column "+pos.getColumn());
			            clickedRow = tableview.getSelectionModel().getSelectedItems();
			            Object rows = tableview.getSelectionModel().getSelectedItem();
			            Object value;
			            
			            for (Field field : rows.getClass().getDeclaredFields()) {
			                field.setAccessible(true); // You might want to set modifier to public first.
			                
							try {
								value = field.get(rows);
								//String values= field.get(rows).toString();
								if (value != null) {
				                    System.out.println(field.getName() + "=" + value);
				                    //System.out.println("STRING FORM\n" + values);
				                    FXMLLoader loader = new FXMLLoader(getClass().getResource("view/editsubscriber.fxml"));
									Parent root1 = (Parent) loader.load();
									EditController controller = loader.getController(); 
			                    	controller.initData(value);
				                }
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
			                
			            }
			        }
			    }
			});
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addSubsPackage.setOnAction(new EventHandler<ActionEvent>() {
			
		    @Override public void handle(ActionEvent e) {
		    	try {
		            
		            Parent root = FXMLLoader.load(getClass().getResource("view/addAllocatedPackages.fxml"));
		            Stage mainStage;
		            mainStage = parentWindow;
		            mainStage.getScene().setRoot(root); 
		            
		            
		            } catch(Exception ee) {
		               ee.printStackTrace();
		              }
		    }
		});
		
	}
	
	// This class implement the mouse event handler for editing
    private class ViewClickMouseEventHandler
            implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if (event.getClickCount() == 1) {
 	
                	Parent root;
					try {
						
						final Stage dialogStage = new Stage();
						viewWindow = dialogStage;
                        dialogStage.initModality(Modality.WINDOW_MODAL);
                        dialogStage.setTitle("Confirmation To Delete");
                        
						//root = FXMLLoader.load(getClass().getResource("view/viewInfo.fxml"));
						 FXMLLoader loader = new FXMLLoader(getClass().getResource("view/viewInfo.fxml"));
							Parent root1 = (Parent) loader.load();
							ViewController controller = loader.getController(); 
							System.out.println("REGISTRATION NUMBERS:"+regNumbers);
							
	                    	controller.initData(regNumbers,customerNames,requestedPackages,period,amountPaid
	                    			,dateRequested,endingDate,status,employees);
	                    	
						dialogStage.setResizable(true);
						Scene dialogScene = new Scene(root1,500,600);
						dialogScene.getStylesheets().add(getClass().getResource("dialog.css").toExternalForm());  
						dialogStage.setScene(dialogScene);
					    
						dialogStage.show();
			            //mainStage = parentWindow;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            
					
		           
                    
            }
        }
    }
    
 // This class implement the mouse event handler for editing
    private class EditClickMouseEventHandler
            implements EventHandler<MouseEvent> {
    	
    	//Object object =  tableview.getSelectionModel().selectedItemProperty().get();
    	//int index = tableview.getSelectionModel().selectedIndexProperty().get();
        @SuppressWarnings("unchecked")
		@Override
        public void handle(MouseEvent event) {
            if (event.getClickCount() == 1) {
                	Parent root;
					try {
						
						//System.out.println("Content of ROW/n"+object);
						//System.out.println("/nINDEX OF ROW/n"+index);
						root = FXMLLoader.load(getClass().getResource("view/editsubscriber.fxml"));
						Stage mainStage;
			            mainStage = parentWindow;
			            mainStage.getScene().setRoot(root);
			            boxImage = (ImageView) mainStage.getScene().lookup("#boximage");
						Image image = new Image("file:images/logo.jpg");
			            boxImage.setImage(image);
			            
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            
		           
                    
            }
        }
    }
    
    
 // This class implement the mouse event handler for editing
    private class DeleteClickMouseEventHandler
            implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if (event.getClickCount() == 1) {
            			lastID = 0;
            			final Stage dialogStage = new Stage();
                        dialogStage.initModality(Modality.WINDOW_MODAL);
                        dialogStage.setTitle("Confirmation To Delete");
                        
                        Label label = new Label("Confirm Delete");
                        label.setAlignment(Pos.TOP_LEFT);
                        label.setId("#reddish");
                        Label exitLabel = new Label("Are you sure you want to DELETE this subscriber from the system?");
                        exitLabel.setAlignment(Pos.CENTER);   
                        
                        Button yesBtn = new Button("Yes");
                        yesBtn.setId("#button");
                        yesBtn.setMinWidth(50.0);
                        yesBtn.applyCss();
                        yesBtn.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent arg0) {
                            	
                            	System.out.println("DELETE BUTTON CLICKED ID NUMBER"+id);
                            	DeleteController delControl = new DeleteController();
                            	delControl.deleteRecord(id);
                            	buildData();
                            	
                                dialogStage.close();

                            }
                        });
                        Button noBtn = new Button("Cancel");
                        noBtn.setId("#button");
                        noBtn.setMinWidth(50.0);
                        yesBtn.applyCss();
                        noBtn.setOnAction(new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent arg0) {
                                dialogStage.close();

                            }
                        });

                        HBox hBox = new HBox();
                        hBox.setAlignment(Pos.TOP_CENTER);
                        hBox.setSpacing(35.0);
                        hBox.getChildren().addAll(yesBtn, noBtn);

                        VBox vBox = new VBox();
                        vBox.setMinSize(600, 90);
                        vBox.setSpacing(40.0);
                        vBox.getChildren().addAll(label,exitLabel, hBox);
                        Scene delScene = new Scene(vBox);
                        dialogStage.setScene(delScene);
                        delScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            			
                        dialogStage.show();
                    } 
        }
    }
	
    
	public static void main
	(String[] args) {
		launch(args);
	}
}
