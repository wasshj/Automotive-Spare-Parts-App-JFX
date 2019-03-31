package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import connect.cnctclass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ajoutController implements Initializable{
	String choixSelected="";
	@FXML
	private TextField nametext;
	@FXML
	private TextField reftext;
	@FXML
	private TextField vehtext;
	@FXML
	private TextField qqtetext;
	@FXML
	private TextField frnstext;
	@FXML
	private TextField prixtext;
	
	@FXML
	private Button cancelAdding;
	@FXML
	private Button doneAdding;
	
	private mymainController ctrl1;
	@FXML
	private ComboBox<String> choixfrns=new ComboBox<String>();
	public ObservableList<String> frnslist1 = FXCollections.observableArrayList(
			
			
			);
	
	public void setController(mymainController ctrl1)
	{
		this.ctrl1=ctrl1;
	}
	
	
	public void onCancelButton(ActionEvent e_cancel)
	{
	    Stage stage = (Stage) cancelAdding.getScene().getWindow();
	    stage.close();
	}
	
	public void onOkButton(ActionEvent e)
	{
		
		 /*String nametext1=nametext.getText();
		 String reftext1=reftext.getText();
		 String vehtext1=vehtext.getText();
		 String qqtetext1=qqtetext.getText();
		 String frnstext1=frnstext.getText();
		 String prixtext1=prixtext.getText();*/
		

		
        cnctclass Cnctclass=new cnctclass();
        Connection connection=Cnctclass.getConnection();
        //String sql ="INSERT INTO piece (nom, ref, veh,qqte,frns,prix)" 
        //+"VALUES ('"+nametext.getText()+"','"+reftext.getText()+"','"+vehtext.getText()+"','"+qqtetext.getText()+"','"+frnstext.getText()+"','"+prixtext.getText()+"')"; 
      
      String sql ="INSERT INTO piece (nom, ref, veh,qqte,frns,prix)" 
        +"VALUES ('"+nametext.getText()+"','"+reftext.getText()+"','"+vehtext.getText()+"','"+qqtetext.getText()+"','"+choixSelected+"','"+prixtext.getText()+"')"; 
        
       try {
    	   if(connection!=null) {
    	   Statement statement=connection.createStatement();
    	   statement.executeUpdate(sql);
    	   Alert alert = new Alert(AlertType.INFORMATION);
    	   alert.setTitle("Ajout");
    	   alert.setHeaderText("Pièce enregistrée !");
    	  alert.setContentText("");
    	  alert.showAndWait();
    	  
    	  if(ctrl1!=null)
    	  {
    		  ctrl1.update();
    	  }

   	    Stage stage = (Stage) doneAdding.getScene().getWindow();
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
		updatemycombo();
		
	}

	public void updatemycombo()
	{

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

    		   frnslist1.add(index, f.getNom());
    	   }
    	   }
    	   else
    		   System.out.println("frns not connected !");
       } catch (SQLException e) {

		// TODO Auto-generated catch block
		e.printStackTrace();
       }
       
       choixfrns.setItems(frnslist1);
       //choixfrns.valueProperty().addListener((obs, oldVal, newVal) -> newVal);
       /*choixfrns.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
    	   System.out.println(choixfrns.getValue());
	    });*/
       choixfrns.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
    	   choixSelected=choixfrns.getValue();
	    });
       /*
       Alert alert = new Alert(AlertType.WARNING);
       alert.setTitle("Erreur");
       alert.setHeaderText(choixfrns.getValue();
       alert.setContentText("");
       alert.showAndWait();*/
       
	}
	
}
