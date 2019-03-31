package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import connect.cnctclass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class editController implements Initializable {
	String choixSelected1="";
	@FXML
	private ComboBox<String> choixfrns1=new ComboBox<String>();
	public ObservableList<String> frnslist11 = FXCollections.observableArrayList(
			
			
			);
	@FXML
	private TextField nametext=new TextField();
	@FXML
	private TextField reftext=new TextField();;
	@FXML
	private TextField vehtext=new TextField();;
	@FXML
	private TextField qqtetext=new TextField();;
	@FXML
	private TextField frnstext=new TextField();;
	@FXML
	private TextField prixtext=new TextField();;
	@FXML
	private Button doneModifying;
	@FXML
	private Button cancelModifying;
	
	private mymainController ctrl;
	
	public void setController(mymainController ctrl)
	{
		this.ctrl=ctrl;
	}
	
	public void setText(String nametext,String reftext,String vehtext,String qqtetext,String frnstext,String prixtext)
	{
		this.nametext.setText(nametext);
		this.reftext.setText(reftext);
		this.vehtext.setText(vehtext);
		this.qqtetext.setText(qqtetext);
		this.frnstext.setText(frnstext);
		this.prixtext.setText(prixtext);
		
	}
	
	public void onCancelEditClicked()
	{
   	    Stage stage = (Stage) cancelModifying.getScene().getWindow();
   	    stage.close();
	}
	

	
	public void onOkEditClicked()
	{
		cnctclass Cnctclass=new cnctclass();
        Connection connection=Cnctclass.getConnection();
        String sql ="UPDATE piece SET nom='"+nametext.getText()+"',veh='"+vehtext.getText()+"',qqte='"+qqtetext.getText()+"',frns='"+choixSelected1+"',prix='"+prixtext.getText()+"' WHERE ref='"+reftext.getText()+"'" ;
        //+"VALUES ('"+nametext.getText()+"','"+reftext.getText()+"','"+vehtext.getText()+"','"+qqtetext.getText()+"','"+frnstext.getText()+"','"+prixtext.getText()+"')"; 
      
       try {
    	   if(connection!=null) {
    	   Statement statement=connection.createStatement();
    	   statement.executeUpdate(sql);
    	   
    	   Alert alert = new Alert(AlertType.INFORMATION);
    	   alert.setTitle("Edit");
    	   alert.setHeaderText("Pièce mise a jour !");
    	  alert.setContentText("");
    	  alert.showAndWait();
    	  
    	  if(ctrl!=null)
    	  {
    		  ctrl.update();
    	  }

   	    Stage stage = (Stage) doneModifying.getScene().getWindow();
   	    stage.close();

    	   }
    	   else
    		   System.out.println("not connected !");
       } catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
       }
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		/*
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("mymain.fxml"));
    	try
    	{
    		Loader.load();
    		
    	}catch(IOException ex)
    	{
    		System.out.println("error !!!!!!!!!!");
    	}
    	mymainController mc=Loader.getController();*/
 
		//nametext.setText(mc.myname());
		//nametext.setText("HELOOOOO");
		
		

		cnctclass CnctclassForFrns=new cnctclass();
        Connection connectionForFrns=CnctclassForFrns.getConnection();
       String sqlForFrns ="SELECT * FROM fournisseur"; 
      // String sqlCountClient ="SELECT count(*) FROM client";
       
       try {
    	   
    	   if(connectionForFrns!=null) {
    	   Statement statement=connectionForFrns.createStatement();
    	   //statement.executeUpdate(sql);
    	   ResultSet rsfrns =statement.executeQuery(sqlForFrns);
    	   //ResultSet rscount =statement.executeQuery(sqlCountClient);
    	   int index=-1;
    	   
    	  //System.out.println("count initial ="+count);
    	   
    	  /* while(rscount.next())
    	   {
    		   count=rscount.getInt(1);
    		   //System.out.println("size bd is "+count);
    	   }*/
    	   
    	   while(rsfrns.next())
    	   {
    		   index++;
    		   fournisseur f=new fournisseur(0,"","","","","","");
    		   //String dbname=rs.getString("nom");
    		   //System.out.println("nom est "+dbname);
    		   f.setIdfrns(rsfrns.getInt("idfrns"));
    		   f.setNom(rsfrns.getString("nom"));
    		   f.setSpec(rsfrns.getString("spec"));
    		   f.setAdr(rsfrns.getString("adr"));
    		   f.setResp(rsfrns.getString("resp"));
    		   f.setTel(rsfrns.getString("tel"));
    		   f.setMail(rsfrns.getString("mail"));
    		   //System.out.println(f);

    		   frnslist11.add(index, f.getNom());
    	   }
    	   }
    	   else
    		   System.out.println("frns not connected !");
       } catch (SQLException e) {

		// TODO Auto-generated catch block
		e.printStackTrace();
       }
       
       choixfrns1.setItems(frnslist11);
       //choixfrns.valueProperty().addListener((obs, oldVal, newVal) -> newVal);
       /*choixfrns.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
    	   System.out.println(choixfrns.getValue());
	    });*/
       choixfrns1.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
    	   choixSelected1=choixfrns1.getValue();
	    });
       /*
       Alert alert = new Alert(AlertType.WARNING);
       alert.setTitle("Erreur");
       alert.setHeaderText(choixfrns.getValue();
       alert.setContentText("");
       alert.showAndWait();*/
       
	}
		
		
		
	}


