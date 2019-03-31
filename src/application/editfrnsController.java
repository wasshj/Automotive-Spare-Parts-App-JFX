package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import connect.cnctclass;
import javafx.event.ActionEvent;

public class editfrnsController implements Initializable{
	@FXML
	private TextField frnsnomtext;
	@FXML
	private TextField frnsspectext;
	@FXML
	private TextField frnsadrtext;
	@FXML
	private TextField frnsresptext;
	@FXML
	private TextField frnsteltext;
	@FXML
	private TextField frnsteltemptext=new TextField();
	@FXML
	private TextField frnsmailtext;
	@FXML
	private Button doneFrnsModifying;
	@FXML
	private Button cancelFrnsModifying;
	
	private mymainController ctrl;
	
	
	public void setController(mymainController ctrl)
	{
		this.ctrl=ctrl;
	}
	
	public void setteltemp(String frnsteltemptext)
	{
		this.frnsteltemptext.setText(frnsteltemptext);
	}

	// Event Listener on Button[#doneFrnsModifying].onAction
	
	
	public void setText(String frnsnomtext,String frnsspectext,String frnsadrtext,String frnsresptext,String frnsteltext,String frnsmailtext)
	{
		this.frnsnomtext.setText(frnsnomtext);
		this.frnsspectext.setText(frnsspectext);
		this.frnsadrtext.setText(frnsadrtext);
		this.frnsresptext.setText(frnsteltext);
		this.frnsteltext.setText(frnsteltext);
		this.frnsmailtext.setText(frnsmailtext);
		
	}
	
	@FXML
	public void onCancelFrnsCltClicked(ActionEvent ecanceleditfrns) {
		

   	    Stage stage = (Stage) cancelFrnsModifying.getScene().getWindow();
   	    stage.close();
		
	}
	
	@FXML
	public void onOkEditFrnsClicked(ActionEvent eeditfrns) 
	{
		cnctclass Cnctclass=new cnctclass();
        Connection connection=Cnctclass.getConnection();
        String sql ="UPDATE fournisseur SET nom='"+frnsnomtext.getText()+"',spec='"+frnsspectext.getText()+"',adr='"+frnsadrtext.getText()+"',resp='"+frnsresptext.getText()+"',tel='"+frnsteltext.getText()+"',mail='"+frnsmailtext.getText()+"' WHERE tel='"+frnsteltemptext.getText()+"'" ;
        //+"VALUES ('"+nametext.getText()+"','"+reftext.getText()+"','"+vehtext.getText()+"','"+qqtetext.getText()+"','"+frnstext.getText()+"','"+prixtext.getText()+"')"; 
      
       try {
    	   if(connection!=null) {
    	   Statement statement=connection.createStatement();
    	   statement.executeUpdate(sql);
    	   
    	   Alert alert = new Alert(AlertType.INFORMATION);
    	   alert.setTitle("Edit");
    	   alert.setHeaderText("Informations du fournisseurs ont été mis a jour !");
    	  alert.setContentText("");
    	  alert.showAndWait();
    	  
    	 if(ctrl!=null)
    	  {
    		  ctrl.updatefrns();
    	  }

   	    Stage stage = (Stage) doneFrnsModifying.getScene().getWindow();
   	    stage.close();

    	   }
    	   else
    		   System.out.println("not connected !");
       } catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
       }

		
	}
	// Event Listener on Button[#cancelFrnsModifying].onAction

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{

		
	}



}
