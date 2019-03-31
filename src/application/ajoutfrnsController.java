package application;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connect.cnctclass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ajoutfrnsController {
	
	@FXML TextField frnsnom;
	@FXML TextField frnsspec;
	@FXML TextField frnsadr;
	@FXML TextField frnsresp;
	@FXML TextField frnstel;
	@FXML TextField frnsmail;
	
	@FXML Button cancelFrnsAdding;
	@FXML Button doneFrnsAdding;
	
	private mymainController ctrl1;
	
	public void setController(mymainController ctrl1)
	{
		this.ctrl1=ctrl1;
	}
	
	public int lastIndexFrns()
	{
		int count=0;
		cnctclass CnctclassForFrns=new cnctclass();
        Connection connectionForFrns=CnctclassForFrns.getConnection();
       String sqlCountFrns ="SELECT count(*) FROM fournisseur";
       
       try {
    	   
    	   if(connectionForFrns!=null) {
    	   Statement statement=connectionForFrns.createStatement();
    	   //statement.executeUpdate(sql);
    	   ResultSet rscount =statement.executeQuery(sqlCountFrns);
    	   
    	   
    	 // System.out.println("count initial ="+count);
    	   
    	   while(rscount.next())
    	   {
    		   count=rscount.getInt(1);
    		   //System.out.println("size bd is "+count);
    	   }
    	   
    	   
    	   }
    	   else
    		   System.out.println("client not connected !");
       } catch (SQLException e) {

		// TODO Auto-generated catch block
		e.printStackTrace();
       }
       
       return count+1;
	}
	
	public void onCancelAjoutFrnsButton(ActionEvent e_cancel)
	{
	    Stage stage = (Stage) cancelFrnsAdding.getScene().getWindow();
	    stage.close();
	}
	
	
	
	public void onOkAjoutFrnsButton(ActionEvent eajoutfrns)
	{
	
		 cnctclass Cnctclass=new cnctclass();
	        Connection connection=Cnctclass.getConnection();
	        String sql ="INSERT INTO fournisseur (idfrns, nom, spec,adr,resp,tel,mail)" 
	        +"VALUES ('"+lastIndexFrns()+"','"+frnsnom.getText()+"','"+frnsspec.getText()+"','"+frnsadr.getText()+"','"+frnsresp.getText()+"','"+frnstel.getText()+"','"+frnsmail.getText()+"')"; 
	      
	       try {
	    	   if(connection!=null) {
	    	   Statement statement=connection.createStatement();
	    	   statement.executeUpdate(sql);
	    	   Alert alert = new Alert(AlertType.INFORMATION);
	    	   alert.setTitle("Ajout");
	    	   alert.setHeaderText("Fourniseeur enregistré!");
	    	  alert.setContentText("");
	    	  alert.showAndWait();
	    	  
	    	  if(ctrl1!=null)
	    	  {
	    		  ctrl1.updatefrns();
	    	  }

	   	    Stage stage = (Stage) doneFrnsAdding.getScene().getWindow();
	   	    stage.close();

	    	   }
	    	   else
	    		   System.out.println("not connected !");
	       } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
	       }
		}
		
	

}
