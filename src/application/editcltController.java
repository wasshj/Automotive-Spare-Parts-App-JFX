package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import connect.cnctclass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class editcltController implements Initializable {
	
	@FXML
	private TextField cltnomtext=new TextField();
	@FXML
	private TextField cltpnomtext=new TextField();
	@FXML
	private TextField clttypetext=new TextField();
	@FXML
	private TextField cltadrtext=new TextField();
	@FXML
	private TextField cltteltext=new TextField();
	@FXML
	private TextField cltteltemptext=new TextField();
	@FXML
	private TextField cltmailtext=new TextField();
	@FXML
	private Button doneCltModifying;
	@FXML
	private Button cancelCltModifying;
	
	private mymainController ctrl;
	
	public void setController(mymainController ctrl)
	{
		this.ctrl=ctrl;
	}
	
	public void setteltemp(String cltteltemptext)
	{
		this.cltteltemptext.setText(cltteltemptext);
	}
	
	public void setText(String cltnomtext,String cltpnomtext,String clttypetext,String cltadrtext,String cltteltext,String cltmailtext)
	{
		this.cltnomtext.setText(cltnomtext);
		this.cltpnomtext.setText(cltpnomtext);
		this.clttypetext.setText(clttypetext);
		this.cltadrtext.setText(cltadrtext);
		this.cltteltext.setText(cltteltext);
		this.cltmailtext.setText(cltmailtext);
		
	}
	
	public void onCancelEditCltClicked()
	{
   	    Stage stage = (Stage) cancelCltModifying.getScene().getWindow();
   	    stage.close();
	}
	
	
	
	public void onOkEditCltClicked()
	{
		cnctclass Cnctclass=new cnctclass();
        Connection connection=Cnctclass.getConnection();
        String sql ="UPDATE client SET nom='"+cltnomtext.getText()+"',pnom='"+cltpnomtext.getText()+"',type='"+clttypetext.getText()+"',adr='"+cltadrtext.getText()+"',tel='"+cltteltext.getText()+"',mail='"+cltmailtext.getText()+"' WHERE tel='"+cltteltemptext.getText()+"'" ;
        //+"VALUES ('"+nametext.getText()+"','"+reftext.getText()+"','"+vehtext.getText()+"','"+qqtetext.getText()+"','"+frnstext.getText()+"','"+prixtext.getText()+"')"; 
      
       try {
    	   if(connection!=null) {
    	   Statement statement=connection.createStatement();
    	   statement.executeUpdate(sql);
    	   
    	   Alert alert = new Alert(AlertType.INFORMATION);
    	   alert.setTitle("Edit");
    	   alert.setHeaderText("Informations du client ont été mis a jour !");
    	  alert.setContentText("");
    	  alert.showAndWait();
    	  
    	  if(ctrl!=null)
    	  {
    		  ctrl.updateclt();
    	  }

   	    Stage stage = (Stage) doneCltModifying.getScene().getWindow();
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
		
	}

}
