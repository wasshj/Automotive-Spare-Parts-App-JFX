package application;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;

import connect.cnctclass;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class showachatsController implements Initializable{
	
@FXML private TableView<achat> mesachatstab=new TableView<achat>();
	
	@FXML private TableColumn<achat,String> nomp=new TableColumn<achat,String>();;
	@FXML private TableColumn<achat,String> refp=new TableColumn<achat,String>();
	@FXML private TableColumn<achat,Integer> idclt=new TableColumn<achat,Integer>();;
	@FXML private TableColumn<achat,Integer> qqte=new TableColumn<achat,Integer>();;
	@FXML private TableColumn<achat,Float> prix=new TableColumn<achat,Float>();
	private Scanner x;
	
	public ObservableList<achat> mesachatlist = FXCollections.observableArrayList(
			//new piece("nom1","ref1","vehicule1",111,"frns1",(float) 1.1),
			//new piece("nom2","ref2","vehicule2",22,"frns2",(float) 2.2)
			
			);
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		
		
		//**********************GET DATA FROM DATABASE 
		/*mesachatlist.clear();
		  cnctclass CnctclassForClient=new cnctclass();
        Connection connectionForClient=CnctclassForClient.getConnection();
       String sqlForClient ="SELECT * FROM achats"; 
      // String sqlCountClient ="SELECT count(*) FROM client";
       
       try {
    	   
    	   if(connectionForClient!=null) {
    	   Statement statement=connectionForClient.createStatement();
    	   //statement.executeUpdate(sql);
    	   ResultSet rsclient =statement.executeQuery(sqlForClient);
    	  // ResultSet rscount =statement.executeQuery(sqlCountClient);
    	   //int count=0;
    	   int index=-1;
    	   
    	   
    	   while(rsclient.next())
    	   {
    		   //count++;
    		   achat a =new achat(0,"","",0,(float)0.0,0,(float)0.0);
    		   //String dbname=rs.getString("nom");
    		   //System.out.println("nom est "+dbname);
    		   a.setId(rsclient.getInt("id"));
    		   a.setRefp(rsclient.getString("refp"));
    		   a.setNomp(rsclient.getString("nomp"));
    		   a.setIdclt(rsclient.getInt("idclt"));
    		   a.setPrixunit(rsclient.getFloat("prixunit"));
    		   a.setQqtetotal(rsclient.getInt("qqtetotal"));
    		   a.setPrixtotal(rsclient.getFloat("prixtotal"));
    		   index++;
    		   //System.out.println(c);

    		   mesachatlist.add(index, a);
    	   }
    	   }
    	   else
    		   System.out.println("client not connected !");
       } catch (SQLException e) {

		// TODO Auto-generated catch block
		e.printStackTrace();
       }
       
       
       
       nomp.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
       refp.setCellValueFactory(new PropertyValueFactory<achat,String>("refp"));
		idclt.setCellValueFactory(new PropertyValueFactory<achat,Integer>("idclt"));
		qqte.setCellValueFactory(new PropertyValueFactory<achat,Integer>("qqtetotal"));
		prix.setCellValueFactory(new PropertyValueFactory<achat,Float>("prixtotal"));
		
		mesachatstab.setItems(mesachatlist);
		
		
		// END OF DATABASE WORK
		  */
		
		
		// ********************GET DATA FROM FILE
		mesachatlist.clear();
		
		// Open file 
		try {
			x=new Scanner(new File("achats.txt"));
		}
		catch(Exception e)
		{
			System.out.println("could not find file");
		}
		int index=-1;
		while(x.hasNext()) {
			achat a =new achat(0,"","",0,(float)0.0,0,(float)0.0);
			Integer id=Integer.parseInt(x.next());
			String refp=x.next();
			String nomp=x.next().replace("*"," ");
			Integer idclt=Integer.parseInt(x.next());
			Float prixunit=Float.parseFloat(x.next());//
			Integer qqtetotal=Integer.parseInt(x.next());
			Float prixtotal=Float.parseFloat(x.next());//
			a.setId(id);
			a.setRefp(refp);
			a.setNomp(nomp);
			a.setIdclt(idclt);
			a.setPrixunit(prixunit);
			a.setQqtetotal(qqtetotal);
			a.setPrixtotal(prixtotal);
			index++;
			mesachatlist.add(index, a);
		}
		
			nomp.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
	        refp.setCellValueFactory(new PropertyValueFactory<achat,String>("refp"));
			idclt.setCellValueFactory(new PropertyValueFactory<achat,Integer>("idclt"));
			qqte.setCellValueFactory(new PropertyValueFactory<achat,Integer>("qqtetotal"));
			prix.setCellValueFactory(new PropertyValueFactory<achat,Float>("prixtotal"));
			
			mesachatstab.setItems(mesachatlist);
		
	};

}
