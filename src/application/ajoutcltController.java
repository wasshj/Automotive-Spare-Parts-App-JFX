package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connect.cnctclass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ajoutcltController {
	
	@FXML TextField cltnom;
	@FXML TextField cltpnom;
	@FXML TextField clttype;
	@FXML TextField cltadr;
	@FXML TextField clttel;
	@FXML TextField cltmail;
	
	@FXML Button cancenClientAdding;
	@FXML Button doneClientAdding;
	
	private mymainController ctrl1;
	
	public ObservableList<fournisseur> frnslist = FXCollections.observableArrayList(
			//new fournisseur(1,"nom1","spec1","adr1","resp1","tel1","mail1@s1.com"),
			//new fournisseur(2,"nom2","spec2","adr2","resp2","tel2","mail2@s2.com")
			
			);
	
	public void setController(mymainController ctrl1)
	{
		this.ctrl1=ctrl1;
	}
	
	public int lastIndexClient()
	{
		int count=0;
		cnctclass CnctclassForClient=new cnctclass();
        Connection connectionForClient=CnctclassForClient.getConnection();
       String sqlCountClient ="SELECT * FROM client";
       
       try {
    	   
    	   if(connectionForClient!=null) {
    	   Statement statement=connectionForClient.createStatement();
    	   //statement.executeUpdate(sql);
    	   ResultSet rscount =statement.executeQuery(sqlCountClient);
    	   
    	   
    	 // System.out.println("count initial ="+count);
    	   
    	   while(rscount.next())
    	   {
    		   //count=rscount.getInt(1);
    		   if(rscount.getInt("id")>count)
    			   count=rscount.getInt("id");
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
	
	
	
	
	public void onCancelAjoutClientButton(ActionEvent e_cancel)
	{
	    Stage stage = (Stage) cancenClientAdding.getScene().getWindow();
	    stage.close();
	}
	
	
	public void onOkAjoutCltButton(ActionEvent e)
	{
		
		
		
        cnctclass Cnctclass=new cnctclass();
        Connection connection=Cnctclass.getConnection();
        String sql ="INSERT INTO client (id, nom, pnom,type,adr,tel,mail)" 
        +"VALUES ('"+lastIndexClient()+"','"+cltnom.getText()+"','"+cltpnom.getText()+"','"+clttype.getText()+"','"+cltadr.getText()+"','"+clttel.getText()+"','"+cltmail.getText()+"')"; 
      
       try {
    	   if(connection!=null) {
    	   Statement statement=connection.createStatement();
    	   statement.executeUpdate(sql);
    	   Alert alert = new Alert(AlertType.INFORMATION);
    	   alert.setTitle("Ajout");
    	   alert.setHeaderText("Client enregistré!");
    	  alert.setContentText("");
    	  alert.showAndWait();
    	  
    	  if(ctrl1!=null)
    	  {
    		  ctrl1.updateclt();
    	  }

   	    Stage stage = (Stage) doneClientAdding.getScene().getWindow();
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
