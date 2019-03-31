package application;
import  connect.cnctclass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Formatter;
import java.util.ResourceBundle;
import java.util.logging.Logger;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class mymainController implements Initializable {
	@FXML 
	private GridPane topgridpane=new GridPane(); 
	
	float x=(float)0.0;
	boolean exist;
	
	// *************** Piece UI
	@FXML private TableView<piece> mytab=new TableView<piece>();
	
	@FXML private TableColumn<piece,String> name=new TableColumn<piece,String>();;
	@FXML private TableColumn<piece,String> ref=new TableColumn<piece,String>();
	@FXML private TableColumn<piece,String> veh=new TableColumn<piece,String>();;
	@FXML private TableColumn<piece,Integer> qqte=new TableColumn<piece,Integer>();;
	@FXML private TableColumn<piece,String> frns=new TableColumn<piece,String>();;
	@FXML private TableColumn<piece,Float> prix=new TableColumn<piece,Float>();;
	
	@FXML Label namelabel=new Label();
	@FXML Label reflabel=new Label();
	@FXML Label vehlabel=new Label();
	@FXML Label qqtelabel=new Label();
	@FXML Label frnslabel=new Label();
	@FXML Label prixlabel=new Label();
	
 
	
	
	/*@FXML TextField nametext=new TextField();
	@FXML TextField reftext=new TextField();;
	@FXML TextField vehtext=new TextField();;
	@FXML TextField qqtetext=new TextField();;
	@FXML TextField frnstext=new TextField();;
	@FXML TextField prixtext=new TextField();;*/
	
	public ObservableList<piece> list = FXCollections.observableArrayList(
			//new piece("nom1","ref1","vehicule1",111,"frns1",(float) 1.1),
			//new piece("nom2","ref2","vehicule2",22,"frns2",(float) 2.2)
			
			);
	
	public ObservableList<piece> list1 = FXCollections.observableArrayList(
			//new piece("nom1","ref1","vehicule1",111,"frns1",(float) 1.1),
			//new piece("nom2","ref2","vehicule2",22,"frns2",(float) 2.2)
			
			);
	String piecetempid;
	//************** FIN piece UI
	
	//****************** Client Ui
	
	@FXML private TableView<client> myclttab=new TableView<client>();
	
	@FXML private TableColumn<client,Integer> cltid=new TableColumn<client,Integer>();
	@FXML private TableColumn<client,String> cltnom=new TableColumn<client,String>();
	@FXML private TableColumn<client,String> cltpnom=new TableColumn<client,String>();
	@FXML private TableColumn<client,String> clttype=new TableColumn<client,String>();
	@FXML private TableColumn<client,String> cltadr=new TableColumn<client,String>();
	@FXML private TableColumn<client,String> clttel=new TableColumn<client,String>();
	@FXML private TableColumn<client,String> cltmail=new TableColumn<client,String>();
	
	@FXML Label cltnomlabel=new Label();
	@FXML Label cltpnomlabel=new Label();
	@FXML Label clttypelabel=new Label();
	@FXML Label cltadrlabel=new Label();
	@FXML Label clttellabel=new Label();
	@FXML Label cltmaillabel=new Label();


	public ObservableList<client> cltlist = FXCollections.observableArrayList(
			//new client(1,"nom1","pnom1","type1","adr1","tel1","mail1@s1.com"),
			//new client(2,"nom2","pnom2","type2","adr2","tel2","mail2@s2.com")
			
			);
	int clttempid;
	//****************** FIN clt ui
	
	
	//******************************* FRNS UI *************************************
	
	@FXML private TableView<fournisseur> myfrnstab=new TableView<fournisseur>();
	
	@FXML private TableColumn<fournisseur,Integer> frnsid=new TableColumn<fournisseur,Integer>();
	@FXML private TableColumn<fournisseur,String> frnsnom=new TableColumn<fournisseur,String>();
	@FXML private TableColumn<fournisseur,String> frnsspec=new TableColumn<fournisseur,String>();
	@FXML private TableColumn<fournisseur,String> frnsadr=new TableColumn<fournisseur,String>();
	@FXML private TableColumn<fournisseur,String> frnsresp=new TableColumn<fournisseur,String>();
	@FXML private TableColumn<fournisseur,String> frnstel=new TableColumn<fournisseur,String>();
	@FXML private TableColumn<fournisseur,String> frnsmail=new TableColumn<fournisseur,String>();
	
	@FXML Label frnsnomlabel=new Label();
	@FXML Label frnsspeclabel=new Label();
	@FXML Label frnsadrlabel=new Label();
	@FXML Label frnsresplabel=new Label();
	@FXML Label frnstellabel=new Label();
	@FXML Label frnsmaillabel=new Label();


	public ObservableList<fournisseur> frnslist = FXCollections.observableArrayList(
			//new fournisseur(1,"nom1","spec1","adr1","resp1","tel1","mail1@s1.com"),
			//new fournisseur(2,"nom2","spec2","adr2","resp2","tel2","mail2@s2.com")
			
			);
	
	
	
	// ************************** END FRNS UI ********************************************** 
	
	//*********************************** Facturation **************************************
	
	@FXML ComboBox<client> facCltcombo=new ComboBox<client>();
	@FXML ComboBox<piece> facPiececombo=new ComboBox<piece>();
	@FXML TextField quant=new TextField();
	@FXML TextField totaltext=new TextField();
	
	
	@FXML private TableView<achat> myachatstab=new TableView<achat>();
	
	@FXML private TableColumn<achat,Integer> achatid=new TableColumn<achat,Integer>();
	@FXML private TableColumn<achat,String> achatref=new TableColumn<achat,String>();
	@FXML private TableColumn<achat,String> achatnom=new TableColumn<achat,String>();
	@FXML private TableColumn<achat,Integer> achatidclt=new TableColumn<achat,Integer>();
	@FXML private TableColumn<achat,Float> achatprixunit=new TableColumn<achat,Float>();
	@FXML private TableColumn<achat,Integer> achatqqtetotal=new TableColumn<achat,Integer>();
	@FXML private TableColumn<achat,Float> achatprixtotal=new TableColumn<achat,Float>();
	
	public ObservableList<achat> achatslist = FXCollections.observableArrayList(
			//new achat(1,"ref1","nom1",11,(float)1.1,11,(float)1.1),
			//new achat(2,"ref2","nom2",22,(float)2.2,22,(float)2.2)
			
			);
	
	
	
	//********************************** END Facturation ***********************************
	
	public String myname()
	{
		return namelabel.getText();
	}
	
	public void showright(piece p)
	{
		if (p != null) {
		namelabel.setText(p.getNom());
		reflabel.setText(p.getRef());
		vehlabel.setText(p.getVehicule());
		qqtelabel.setText(p.getQqte().toString());
		frnslabel.setText(p.getFrns());
		prixlabel.setText(Float.toString(p.getPrix()));
		//remplirEditInterface();
		//nametext.setText(p.getNom());
		
		}
		else
		{
			namelabel.setText("");
			reflabel.setText("");
			vehlabel.setText("");
			qqtelabel.setText("");
			frnslabel.setText("");
			prixlabel.setText("");
		}
	}
	
	public void showrightClient(client c)
	{
		if (c != null) {
			
		cltnomlabel.setText(c.getNom());
		cltpnomlabel.setText(c.getPnom());
		clttypelabel.setText(c.getType());
		cltadrlabel.setText(c.getAdr());
		clttellabel.setText(c.getTel());
		cltmaillabel.setText(c.getMail());
		//remplirEditInterface();
		//nametext.setText(p.getNom());
		
		}
		else
		{
			cltnomlabel.setText("");
			cltpnomlabel.setText("");
			clttypelabel.setText("");
			cltadrlabel.setText("");
			clttellabel.setText("");
			cltmaillabel.setText("");
		}
	}
	
	public void showrightFrns(fournisseur f)
	{
		if (f != null) {
			
		frnsnomlabel.setText(f.getNom());
		frnsspeclabel.setText(f.getSpec());
		frnsadrlabel.setText(f.getAdr());
		frnsresplabel.setText(f.getResp());
		frnstellabel.setText(f.getTel());
		frnsmaillabel.setText(f.getMail());
		//remplirEditInterface();
		//nametext.setText(p.getNom());
		
		}
		else
		{
			frnsnomlabel.setText("");
			frnsspeclabel.setText("");
			frnsadrlabel.setText("");
			frnsresplabel.setText("");
			frnstellabel.setText("");
			frnsmaillabel.setText("");
		}
	}
	
	
	/*public void showOnEdit(piece p)
	{
		if (p != null) {
		nametext.setText(p.getNom());
		reftext.setText(p.getRef());
		vehtext.setText(p.getVehicule());
		qqtetext.setText(p.getQqte().toString());
		frnstext.setText(p.getFrns());
		prixtext.setText(Float.toString(p.getPrix()));
		//remplirEditInterface();
		//nametext.setText(p.getNom());
		
		}
		else
		{
			nametext.setText("");
			reftext.setText("");
			vehtext.setText("");
			qqtetext.setText("");
			frnstext.setText("");
			prixtext.setText("");
		}
	}*/
	
	@FXML
	private void handleDeletePiece() {
		
		int selectedIndex = mytab.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) {
	        

			cnctclass Cnctclass=new cnctclass();
	        Connection connection=Cnctclass.getConnection();
	        String sql ="DELETE FROM piece WHERE ref='"+reflabel.getText()+"'" ;
	        //+"VALUES ('"+nametext.getText()+"','"+reftext.getText()+"','"+vehtext.getText()+"','"+qqtetext.getText()+"','"+frnstext.getText()+"','"+prixtext.getText()+"')"; 
	      
	       try {
	    	   if(connection!=null) {
	    	   Statement statement=connection.createStatement();
	    	   statement.executeUpdate(sql);
	    	   
	    	   Alert alert = new Alert(AlertType.INFORMATION);
	    	   alert.setTitle("DELETE");
	    	   alert.setHeaderText("Pièce supprimée !");
	    	  alert.setContentText("");
	    	  alert.showAndWait();
	    	  mytab.getItems().remove(selectedIndex);
	   	    //Stage stage = (Stage) doneModifying.getScene().getWindow();
	   	    //stage.close();

	    	   }
	    	   else
	    		   System.out.println("not connected !");
	       } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
	       }
	        
	        
	    } else {
	    	
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Erreur");
	        alert.setHeaderText("Aucune pièce selectionnée!");
	        //alert.setContentText("Veuillez selectionner une.");
	        alert.showAndWait();
	    }
	}
	
	public void OnEditButton(ActionEvent event)
	{
		int selectedIndex = mytab.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) {
		
	    Stage primaryStage = new Stage();
	    
		try {
			//BorderPane root = new BorderPane();
			//remplirEditInterface();
			
			Parent root = FXMLLoader.load(getClass().getResource("/application/edit.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			
			primaryStage.show();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	    }
	    else
	    {
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Erreur");
	        alert.setHeaderText("Aucune pièce selectionnée");
	        alert.setContentText("Veuillez selectionner une.");
	        alert.showAndWait();
	    }
	}
	
	
	
	public void OnEditButton1(ActionEvent e)
	{
		String nameText=namelabel.getText();
		String refText=reflabel.getText();
		String vehText=vehlabel.getText();
		String qqteText=qqtelabel.getText();
		String frnsText=frnslabel.getText();
		String prixText=prixlabel.getText();
		
		int selectedIndex = mytab.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) 
	    {
		
	    	FXMLLoader Loader = new FXMLLoader();
	    	Loader.setLocation(getClass().getResource("edit.fxml"));
	    	try
	    	{
	    		Loader.load();
	    		
	    	}catch(IOException ex)
	    	{
	    		Logger.getLogger(mymainController.class.getName()).log(Level.SEVERE,null,ex);
	    	}
	    	editController display=new editController();
	    	display=Loader.getController();	    	
	    	display.setText(nameText, refText, vehText, qqteText, frnsText, prixText);
	    	
	    	//******
	    	display.setController(this);
	    	//*****
	    	
	    	Parent p=Loader.getRoot();
	    	Stage stage=new Stage();
	    	stage.setTitle("Modifier une pièce");
	    	stage.setScene(new Scene (p));
	    	stage.showAndWait();
	    }
	    else
	    {
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Erreur");
	        alert.setHeaderText("Aucune pièce selectionnée");
	        alert.setContentText("Veuillez selectionner une.");
	        alert.showAndWait();
	    }
	}
	
	/*public void remplirEditInterface()
	{
		nametext.setText(myname());
		//nametext.setText(namelabel.getText());
		reftext.setText(reflabel.getText());;
		vehtext.setText(vehlabel.getText());;
		qqtetext.setText(qqtelabel.getText());;
		frnstext.setText(frnslabel.getText());;
		prixtext.setText(prixlabel.getText());;
	}*/
	
	public void onAjoutButtonClicked(ActionEvent e)
	{
		
		FXMLLoader Loader1 = new FXMLLoader();
    	Loader1.setLocation(getClass().getResource("ajout.fxml"));
    	try
    	{
    		Loader1.load();
    		
    	}catch(IOException ex)
    	{
    		Logger.getLogger(mymainController.class.getName()).log(Level.SEVERE,null,ex);
    	}
    	
    	ajoutController display1=new ajoutController();
    	display1=Loader1.getController();	
    	display1.setController(this);
    	
    	
    	Parent p1=Loader1.getRoot();
    	Stage stage1=new Stage();
    	stage1.setTitle("Ajouter une pièce");
    	stage1.setScene(new Scene (p1));
    	stage1.showAndWait();
    	//stage1.show();
	}
	 
	
	//******************** Clt button functions **********************
	
	public void onAjoutClientButtonClicked(ActionEvent eClient)
	{
		
		FXMLLoader LoaderAjoutClt = new FXMLLoader();
		LoaderAjoutClt.setLocation(getClass().getResource("ajoutclt.fxml"));
    	try
    	{
    		LoaderAjoutClt.load();
    		
    	}catch(IOException ex)
    	{
    		Logger.getLogger(mymainController.class.getName()).log(Level.SEVERE,null,ex);
    	}
    	
    	ajoutcltController displayAjoutClt=new ajoutcltController();
    	displayAjoutClt=LoaderAjoutClt.getController();	
    	displayAjoutClt.setController(this);
    	
    	
    	Parent pClient=LoaderAjoutClt.getRoot();
    	Stage stageClient=new Stage();
    	stageClient.setTitle("Ajouter un client");
    	stageClient.setScene(new Scene (pClient));
    	stageClient.showAndWait();
	}
	
	
	
	
	
	public void OnEditCltButton(ActionEvent eedit)
	{
		String cltnomText=cltnomlabel.getText();
		String cltpnomText=cltpnomlabel.getText();
		String clttypeText=clttypelabel.getText();
		String cltadrText=cltadrlabel.getText();
		String clttelText=clttellabel.getText();
		String cltmailText=cltmaillabel.getText();
		
		int selectedIndex = myclttab.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) 
	    {
		
	    	FXMLLoader Loadereditclt = new FXMLLoader();
	    	Loadereditclt.setLocation(getClass().getResource("editclt.fxml"));
	    	try
	    	{
	    		Loadereditclt.load();
	    		
	    	}catch(IOException ex)
	    	{
	    		Logger.getLogger(mymainController.class.getName()).log(Level.SEVERE,null,ex);
	    	}
	    	editcltController displayEditClt=new editcltController();
	    	displayEditClt=Loadereditclt.getController();	    	
	    	displayEditClt.setText(cltnomText, cltpnomText, clttypeText, cltadrText, clttelText, cltmailText);
	    	displayEditClt.setteltemp(clttelText);
	    	
	    	//******
	    	displayEditClt.setController(this);
	    	//*****
	    	
	    	Parent p=Loadereditclt.getRoot();
	    	Stage stageEditClt=new Stage();
	    	stageEditClt.setTitle("Modifier un client");
	    	stageEditClt.setScene(new Scene (p));
	    	stageEditClt.showAndWait();
	    }
	    else
	    {
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Erreur");
	        alert.setHeaderText("Aucun client selectionné");
	        alert.setContentText("");
	        alert.showAndWait();
	    }
	}
	
	
		@FXML
		private void onDeleteClt(ActionEvent edeleteclt) {
		
		int selectedIndex = myclttab.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) {
	        

			cnctclass Cnctclass=new cnctclass();
	        Connection connection=Cnctclass.getConnection();
	        String sql ="DELETE FROM client WHERE tel='"+clttellabel.getText()+"'" ;
	        //+"VALUES ('"+nametext.getText()+"','"+reftext.getText()+"','"+vehtext.getText()+"','"+qqtetext.getText()+"','"+frnstext.getText()+"','"+prixtext.getText()+"')"; 
	      
	       try {
	    	   if(connection!=null) {
	    	   Statement statement=connection.createStatement();
	    	   statement.executeUpdate(sql);
	    	   
	    	   Alert alert = new Alert(AlertType.INFORMATION);
	    	   alert.setTitle("DELETE");
	    	   alert.setHeaderText("Client supprimé !");
	    	  alert.setContentText("");
	    	  alert.showAndWait();
	    	  myclttab.getItems().remove(selectedIndex);
	   	    //Stage stage = (Stage) doneModifying.getScene().getWindow();
	   	    //stage.close();

	    	   }
	    	   else
	    		   System.out.println("not connected !");
	       } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
	       }
	        
	        
	    } else {
	    	
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Erreur");
	        alert.setHeaderText("Aucun client selectionné!");
	        //alert.setContentText("Veuillez selectionner une.");
	        alert.showAndWait();
	    }
	}

	
	//********************* END clt button functions *********************
		
		
		// ************************** FRNS BUTTON FUNCTIONS
		
		public void onAjoutFrnsButton(ActionEvent efrnsajout)
		{
			
			FXMLLoader LoaderAjoutFrns = new FXMLLoader();
			LoaderAjoutFrns.setLocation(getClass().getResource("ajoutfrns.fxml"));
	    	try
	    	{
	    		LoaderAjoutFrns.load();
	    		
	    	}catch(IOException ex)
	    	{
	    		Logger.getLogger(mymainController.class.getName()).log(Level.SEVERE,null,ex);
	    	}
	    	
	    	ajoutfrnsController displayAjoutClt=new ajoutfrnsController();
	    	displayAjoutClt=LoaderAjoutFrns.getController();	
	    	displayAjoutClt.setController(this);
	    	
	    	
	    	Parent pClient=LoaderAjoutFrns.getRoot();
	    	Stage stageClient=new Stage();
	    	stageClient.setTitle("Ajouter un fournisseur");
	    	stageClient.setScene(new Scene (pClient));
	    	stageClient.showAndWait();
	    	

	    	
		}
		
		
		public void OnEditFrnsButton(ActionEvent eeditfrns)
		{
			String frnsnomText=frnsnomlabel.getText();
			String frnsspecText=frnsspeclabel.getText();
			String frnsadrText=frnsadrlabel.getText();
			String frnsrespText=frnsresplabel.getText();
			String frnstelText=frnstellabel.getText();
			String frnsmailText=frnsmaillabel.getText();
			
			int selectedIndex = myfrnstab.getSelectionModel().getSelectedIndex();
		    if (selectedIndex >= 0) 
		    {
			
		    	FXMLLoader Loadereditfrns = new FXMLLoader();
		    	Loadereditfrns.setLocation(getClass().getResource("editfrns.fxml"));
		    	try
		    	{
		    		Loadereditfrns.load();
		    		
		    	}catch(IOException ex)
		    	{
		    		Logger.getLogger(mymainController.class.getName()).log(Level.SEVERE,null,ex);
		    	}
		    	editfrnsController displayEditFrns=new editfrnsController();
		    	displayEditFrns=Loadereditfrns.getController();	    	
		    	displayEditFrns.setText(frnsnomText, frnsspecText, frnsadrText, frnsrespText, frnstelText, frnsmailText);
		    	displayEditFrns.setteltemp(frnstelText);
		    	
		    	//******
		    	displayEditFrns.setController(this);
		    	//*****
		    	
		    	Parent p=Loadereditfrns.getRoot();
		    	Stage stageEditClt=new Stage();
		    	stageEditClt.setTitle("Modifier un fournisseur");
		    	stageEditClt.setScene(new Scene (p));
		    	stageEditClt.showAndWait();
		    }
		    else
		    {
		        Alert alert = new Alert(AlertType.WARNING);
		        alert.setTitle("Erreur");
		        alert.setHeaderText("Aucun fournisseur selectionné");
		        alert.setContentText("");
		        alert.showAndWait();
		    }
		}
		
		
		
		@FXML
		private void onDeleteFrns(ActionEvent edeletefrns) {
		
		int selectedIndex = myfrnstab.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) {
	        

			cnctclass Cnctclass=new cnctclass();
	        Connection connection=Cnctclass.getConnection();
	        String sql ="DELETE FROM fournisseur WHERE tel='"+frnstellabel.getText()+"'" ;
	        //+"VALUES ('"+nametext.getText()+"','"+reftext.getText()+"','"+vehtext.getText()+"','"+qqtetext.getText()+"','"+frnstext.getText()+"','"+prixtext.getText()+"')"; 
	      
	       try {
	    	   if(connection!=null) {
	    	   Statement statement=connection.createStatement();
	    	   statement.executeUpdate(sql);
	    	   
	    	   Alert alert = new Alert(AlertType.INFORMATION);
	    	   alert.setTitle("DELETE");
	    	   alert.setHeaderText("Fournisseur supprimé !");
	    	  alert.setContentText("");
	    	  alert.showAndWait();
	    	  myfrnstab.getItems().remove(selectedIndex);
	   	    //Stage stage = (Stage) doneModifying.getScene().getWindow();
	   	    //stage.close();

	    	   }
	    	   else
	    		   System.out.println("not connected !");
	       } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
	       }
	        
	        
	    } else {
	    	
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Erreur");
	        alert.setHeaderText("Aucun fournisseur selectionné!");
	        //alert.setContentText("Veuillez selectionner une.");
	        alert.showAndWait();
	    }
	}
		
		//**************************** END FRNS BUTTON FUNCTIONS
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//img1 = new ImageView(new Image(getClass().getResourceAsStream("C:\\Users\\WassHj\\Desktop\\autoparts.png")));
		
		
		// TODO Auto-generated method stub
		//name.setCellValueFactory(new PropertyValueFactory<piece,String>("name"));
		//remplirEditInterface();
		

		// *************************** Iinit Piece Part **************************************
		//** DB
		
		
		cnctclass Cnctclass=new cnctclass();
        Connection connection=Cnctclass.getConnection();
       String sql ="SELECT * FROM piece"; 
       
       try {
    	   if(connection!=null) {
    	   Statement statement=connection.createStatement();
    	   //statement.executeUpdate(sql);
    	   ResultSet rs =statement.executeQuery(sql);
    	   int index=-1;
    	   while(rs.next())
    	   {
    		   piece pe=new piece("","","",0,"",(float)0.0);
    		   //String dbname=rs.getString("nom");
    		   //System.out.println("nom est "+dbname);
    		   pe.setNom(rs.getString("nom"));
    		   pe.setRef(rs.getString("ref"));
    		   pe.setVehicule(rs.getString("veh"));
    		   pe.setQqte(rs.getInt("qqte"));
    		   pe.setFrns(rs.getString("frns"));
    		   pe.setPrix(rs.getFloat("prix"));
    		   //System.out.println(pe);
    		   index++;
    		   list.add(index, pe);
    		   list1.add(index, pe);
    	   }
    	   }
    	   else
    		   System.out.println("not connected !");
       } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
       }
       
       
       
		name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		ref.setCellValueFactory(new PropertyValueFactory<piece,String>("ref"));
		veh.setCellValueFactory(new PropertyValueFactory<piece,String>("veh"));
		//qqte.setCellValueFactory(cellData -> cellData.getValue().qqteProperty());
		qqte.setCellValueFactory(new PropertyValueFactory<piece,Integer>("qqte"));
		frns.setCellValueFactory(new PropertyValueFactory<piece,String>("frns"));
		prix.setCellValueFactory(new PropertyValueFactory<piece,Float>("prix"));
		mytab.setItems(list);
		
		showright(null);

	    // Listen for selection changes and show the person details when changed.
	    mytab.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> showright(newValue));
	    
	    

	    //********************************** CONNECT DB ***************
       /* cnctclass Cnctclass=new cnctclass();
        Connection connection=Cnctclass.getConnection();
       String sql ="INSERT INTO user VALUES ('"+"yousef"+"')"; 
       
       try {
    	   if(connection!=null) {
    	   Statement statement=connection.createStatement();
    	   statement.executeUpdate(sql);
    	  //ResultSet rs=statement.executeQuery(sql);
    	   }
    	   else
    		   System.out.println("not connected !");
       } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
       }*/
       
       //************************ FIN CONNECT  BD
       
	   // remplirEditInterface();
	    
	    //******************* FIN piece part *******************************
	    
	    
	    
	    
	    //******************** Iinit Client part *******************************
	    
	    cltlist.clear();
		cnctclass CnctclassForClient=new cnctclass();
        Connection connectionForClient=CnctclassForClient.getConnection();
       String sqlForClient ="SELECT * FROM client"; 
      // String sqlCountClient ="SELECT count(*) FROM client";
       
       try {
    	   
    	   if(connectionForClient!=null) {
    	   Statement statement=connectionForClient.createStatement();
    	   //statement.executeUpdate(sql);
    	   ResultSet rsclient =statement.executeQuery(sqlForClient);
    	  // ResultSet rscount =statement.executeQuery(sqlCountClient);
    	   //int count=0;
    	   int index=-1;
    	   
    	 // System.out.println("count initial ="+count);
    	   
    	   /*while(rscount.next())
    	   {
    		   count=rscount.getInt(1);
    		   System.out.println("size bd is "+count);
    	   }*/
    	   
    	   while(rsclient.next())
    	   {
    		   //count++;
    		   client c=new client(0,"","","","","","");
    		   //String dbname=rs.getString("nom");
    		   //System.out.println("nom est "+dbname);
    		   c.setId(rsclient.getInt("id"));
    		   c.setNom(rsclient.getString("nom"));
    		   c.setPnom(rsclient.getString("pnom"));
    		   c.setType(rsclient.getString("type"));
    		   c.setAdr(rsclient.getString("adr"));
    		   c.setTel(rsclient.getString("tel"));
    		   c.setMail(rsclient.getString("mail"));
    		   index++;
    		   //System.out.println(c);

    		   cltlist.add(index, c);
    	   }
    	   }
    	   else
    		   System.out.println("client not connected !");
       } catch (SQLException e) {

		// TODO Auto-generated catch block
		e.printStackTrace();
       }
       
       
       cltid.setCellValueFactory(new PropertyValueFactory<client,Integer>("id"));
		cltnom.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		cltpnom.setCellValueFactory(new PropertyValueFactory<client,String>("pnom"));
		clttype.setCellValueFactory(new PropertyValueFactory<client,String>("type"));
		cltadr.setCellValueFactory(new PropertyValueFactory<client,String>("adr"));
		clttel.setCellValueFactory(new PropertyValueFactory<client,String>("tel"));
		cltmail.setCellValueFactory(new PropertyValueFactory<client,String>("mail"));
		
		myclttab.setItems(cltlist);
		
		showrightClient(null);

	    // Listen for selection changes and show the person details when changed.
	    myclttab.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> showrightClient(newValue));
	    
	    //********************** END CLT PART *****************************************
	    
	    
	    
	    
	    
	    
	    //********************* Iinit FRNS PART *****************************************
	    
	    cnctclass CnctclassForFrns=new cnctclass();
        Connection connectionForFrns=CnctclassForFrns.getConnection();
       String sqlForFrns ="SELECT * FROM fournisseur"; 
      // String sqlCountClient ="SELECT count(*) FROM client";
       
       try {
    	   
    	   if(connectionForFrns!=null) {
    	   Statement statement=connectionForFrns.createStatement();
    	   //statement.executeUpdate(sql);
    	   ResultSet rsfrns =statement.executeQuery(sqlForFrns);
    	  // ResultSet rscount =statement.executeQuery(sqlCountClient);
    	   //int count=0;
    	   int index=-1;
    	   
    	 // System.out.println("count initial ="+count);
    	   
    	   /*while(rscount.next())
    	   {
    		   count=rscount.getInt(1);
    		   System.out.println("size bd is "+count);
    	   }*/
    	   
    	   while(rsfrns.next())
    	   {
    		   //count++;
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
    		   index++;
    		  // System.out.println(f);

    		   frnslist.add(index, f);
    	   }
    	   }
    	   else
    		   System.out.println("client not connected !");
       } catch (SQLException e) {

		// TODO Auto-generated catch block
		e.printStackTrace();
       }
       
       
       frnsid.setCellValueFactory(new PropertyValueFactory<fournisseur,Integer>("idfrns"));
		frnsnom.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		frnsspec.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("spec"));
		frnsadr.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("adr"));
		frnsresp.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("resp"));
		frnstel.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("tel"));
		frnsmail.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("mail"));
		
		myfrnstab.setItems(frnslist);
		
		showrightClient(null);

	    // Listen for selection changes and show the person details when changed.
	    myfrnstab.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> showrightFrns(newValue));
	    
	    showrightFrns(null);
	    
	    // ********************* FRNS PART *****************************************
	    
	    //*************  Iinit Facturation part *******************************************
	    
	    totaltext.setText(Float.toString((float) 0.0));
	    
	    // comobox facCltcombo affichant les clients
	    facCltcombo.setItems(cltlist);
	    // cltlist est la liste des clients
	    facCltcombo.valueProperty().addListener((obs, oldVal, newVal) -> newVal.getNom());
	    // a chaque selection le nom afficher dedans 
	    
	    
	    facCltcombo.setConverter(new StringConverter<client>() {
	        @Override
	        public String toString(client object) {
	            return object.getNom();
	        }

	        @Override
	        public client fromString(String string) {
	            return null;
	        }
	    });
	    
	    facCltcombo.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
	           clttempid=newValue.getId();//System.out.println("clttempid="+clttempid);
	    }
	    ); 

	    
	    //**** combobox de piece 
	    facPiececombo.setItems(list);
	    facPiececombo.valueProperty().addListener((obs, oldVal, newVal) -> newVal.getNom());

	    facPiececombo.setConverter(new StringConverter<piece>() {
	        @Override
	        public String toString(piece object) {
	            return object.getNom();
	        }

	        @Override
	        public piece fromString(String string) {
	            return null;
	        }
	    });
	    
	    facPiececombo.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
	          piecetempid=newValue.getRef();//System.out.println("piecetempid="+piecetempid);
	    }
	    ); 
	   
	   
	    

	    
	    
	    // ************ END facturation  part *************************************
	    
	    
	    
	}

	
	public int lastIndexFac()
	{
		int count=0;
		cnctclass CnctclassForClient=new cnctclass();
        Connection connectionForClient=CnctclassForClient.getConnection();
       String sqlCountClient ="SELECT * FROM achats";
       
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
	
	public void onVentesButton(ActionEvent eventes)
	{
		FXMLLoader Loader1 = new FXMLLoader();
    	Loader1.setLocation(getClass().getResource("showachats.fxml"));
    	try
    	{
    		Loader1.load();
    		
    	}catch(IOException ex)
    	{
    		Logger.getLogger(mymainController.class.getName()).log(Level.SEVERE,null,ex);
    	}
    	
    	showachatsController display1=new showachatsController();
    	display1=Loader1.getController();	
    	
    	
    	
    	Parent p1=Loader1.getRoot();
    	Stage stage1=new Stage();
    	stage1.setTitle("Liste des ventes");
    	stage1.setScene(new Scene (p1));
    	stage1.showAndWait();
	}
	
    public void onAjoutAchatClicked(ActionEvent eajoutachat)
    {
    	int pos=0;
    	facCltcombo.setDisable(true);
    	//totaltext.setText(Float.toString((float) 0.0));
    	//System.out.println("temppiece is"+clttempid+"    tempcltis"+piecetempid);
    	achat a =new achat(0,"","",0,(float)0.0,0,(float)0.0);
    	a.setId(lastIndexFac()+achatslist.size());
    	a.setIdclt(clttempid);
    	a.setRefp(piecetempid);
    	a.setQqtetotal(Integer.parseInt(quant.getText()));
    	a.setPrixtotal((float)0.0);
    	// determiner prixunit et nompp 
    	cnctclass Cnctclass=new cnctclass();
        Connection connection=Cnctclass.getConnection();
       String sql ="SELECT * FROM piece"; 
       
       try {
    	   if(connection!=null) {
    	   Statement statement=connection.createStatement();
    	   //statement.executeUpdate(sql);
    	   ResultSet rs =statement.executeQuery(sql);
    	   int index=-1;
    	   while(rs.next())
    	   {

    		   //index++;
    		  // list.add(index, pe);
    		   
    		   if(rs.getString("ref").equals(piecetempid))
    		   {
    			   a.setNomp(rs.getString("nom"));
    			   a.setPrixunit(rs.getFloat("prix"));
    			   a.addToPrixTottal(rs.getFloat("prix"));
    			   a.calculPrixTotoal();
    			  //float f=Float.parseFloat(totaltext.getText())+a.getPrixtotal();
    			  //totaltext.setText(Float.toString(f));
    			   
    		   }
    	   }
    	   }
    	   else
    		   System.out.println("not connected !");
       } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
       }
       System.out.println(a);
       exist=false;
       /*myachatstab.getItems().stream().forEach((a1)-> {
    	   if(a1.getRefp()==piecetempid) 
    	   {
    		   a1.setQqtetotal(a1.getQqtetotal()+Integer.parseInt(quant.getText()));
    		   exist=true;
    		   achatslist.re
    	   }
       });*/
       /*achatslist.forEach((a2) -> { 
    	    if(a2.getRefp()==piecetempid)
    	    	
    	});*/
       //a.getRefp()
       for(int i=0;i<achatslist.size();i++)
    	   if(achatslist.get(i).getRefp().equals(a.getRefp()))
    	   {
    		    float x11= Float.valueOf(totaltext.getText())-achatslist.get(i).getPrixtotal();
   				totaltext.setText(Float.toString(x11));
    		   a.setQqtetotal(a.getQqtetotal()+achatslist.get(i).getQqtetotal());
    		   a.setPrixtotal((float)a.getQqtetotal()*a.getPrixunit());
    		   //achatslist.set(i, a);
    		  // achatslist.remove(i);
    		   myachatstab.getItems().remove(i);
    		  // achatslist.add(achatslist.size(), a);
    		   //myachatstab.setItems(achatslist);
    		   
    	   }
       
        achatslist.add(0,a);
		achatid.setCellValueFactory(new PropertyValueFactory<achat,Integer>("id"));
		achatref.setCellValueFactory(new PropertyValueFactory<achat,String>("refp"));
		achatnom.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		achatidclt.setCellValueFactory(new PropertyValueFactory<achat,Integer>("idclt"));
		achatprixunit.setCellValueFactory(new PropertyValueFactory<achat,Float>("prixunit"));
		achatqqtetotal.setCellValueFactory(new PropertyValueFactory<achat,Integer>("qqtetotal"));
		achatprixtotal.setCellValueFactory(new PropertyValueFactory<achat,Float>("prixtotal"));
		myachatstab.setItems(achatslist);
		//achatslist.clear();
		float x1= Float.valueOf(totaltext.getText())+a.getPrixtotal();
		totaltext.setText(Float.toString(x1));
		 //float x = 0;
		//myachatstab.getItems().stream().forEach((a1)-> addFloatValue(achatprixtotal.getCellData(a1)));
		//totaltext.setText(Float.toString(x));
    }
    
    
    Formatter y;
    public void onValiderButton(ActionEvent eajoutachats)
    {
    	
    	myachatstab.getItems().stream().forEach((a1)-> {
     	   
    		 cnctclass Cnctclass=new cnctclass();
    	        Connection connection=Cnctclass.getConnection();
    	        String sql ="INSERT INTO achats (id, refp, nomp,idclt,prixunit,qqtetotal,prixtotal)" 
    	        +"VALUES ('"+a1.getId()+"','"+a1.getRefp()+"','"+a1.getNomp()+"','"+a1.getIdclt()+"','"+a1.getPrixunit()+"','"+a1.getQqtetotal()+"','"+a1.getPrixtotal()+"')"; 
    	      
    	       try {
    	    	   if(connection!=null) {
    	    	   Statement statement=connection.createStatement();
    	    	   statement.executeUpdate(sql);
    	    	   
    	    	   // **********Adding to file 
    	    	   
    	    	   // Opening FILE 
    	    	   try {
    	   			FileWriter f = new FileWriter("achats.txt", true);
    	   			y = new Formatter(f);
    	   			//y=new Formatter(new File("wass.txt"));
    	   		}
    	   		catch(Exception e)
    	   		{
    	   			System.out.println("could not find file");
    	   		}
    	    	  
    	    	   // writing to FILE
    	    	   y.format("%s %s %s %s %s %s %s\r\n",a1.getId(),a1.getRefp(),a1.getNomp().replace(" ", "*"),a1.getIdclt(),a1.getPrixunit(),a1.getQqtetotal(),a1.getPrixtotal());
    	    	   
    	    	   y.close();
    	    	   

    	    	   }
    	    	   else
    	    		   System.out.println("not connected !");
    	       } catch (SQLException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    	       }
    		
        });
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Ajout");
 	   alert.setHeaderText("Achats enregistrés!");
 	  alert.setContentText("");
 	  alert.showAndWait();
 	 achatslist.clear();
 	 myachatstab.setItems(achatslist);
 	 quant.setText("");
 	 facCltcombo.setDisable(false);
 	 facCltcombo.setSelectionModel(null);
 	 facPiececombo.setSelectionModel(null);
 	totaltext.setText(Float.toString((float) 0.0));
    }
	
    public void onAnnuler(ActionEvent eannuler)
    {
    	achatslist.clear();
    	 myachatstab.setItems(achatslist);
    	 quant.setText("");
    	 facCltcombo.setDisable(false);
    	 facCltcombo.setSelectionModel(null);
    	 facPiececombo.setSelectionModel(null);
    	totaltext.setText(Float.toString((float) 0.0));

    	
    }
    public void addFloatValue(float x1)
    {
    	x+=x1;
    }
	public void update()
	{
		list.clear();
		 mytab.setItems(list);
		cnctclass Cnctclass=new cnctclass();
        Connection connection=Cnctclass.getConnection();
       String sql ="SELECT * FROM piece"; 
       
       try {
    	   if(connection!=null) {
    	   Statement statement=connection.createStatement();
    	   //statement.executeUpdate(sql);
    	   ResultSet rs =statement.executeQuery(sql);
    	   int index=-1;
    	   while(rs.next())
    	   {
    		   piece pe=new piece("","","",0,"",(float)0.0);
    		   //String dbname=rs.getString("nom");
    		   //System.out.println("nom est "+dbname);
    		   pe.setNom(rs.getString("nom"));
    		   pe.setRef(rs.getString("ref"));
    		   pe.setVehicule(rs.getString("veh"));
    		   pe.setQqte(rs.getInt("qqte"));
    		   pe.setFrns(rs.getString("frns"));
    		   pe.setPrix(rs.getFloat("prix"));
    		   //System.out.println(pe);
    		   index++;
    		   list.add(index, pe);
    	   }
    	   }
    	   else
    		   System.out.println("not connected !");
       } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
       }
       
       
       
		name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		ref.setCellValueFactory(new PropertyValueFactory<piece,String>("ref"));
		veh.setCellValueFactory(new PropertyValueFactory<piece,String>("veh"));
		qqte.setCellValueFactory(new PropertyValueFactory<piece,Integer>("qqte"));
		frns.setCellValueFactory(new PropertyValueFactory<piece,String>("frns"));
		prix.setCellValueFactory(new PropertyValueFactory<piece,Float>("prix"));
		mytab.setItems(list);
		
	}
	
	public void updateclt()
	{
		
		cltlist.clear();
		cnctclass CnctclassForClient=new cnctclass();
        Connection connectionForClient=CnctclassForClient.getConnection();
       String sqlForClient ="SELECT * FROM client"; 
      // String sqlCountClient ="SELECT count(*) FROM client";
       
       try {
    	   
    	   if(connectionForClient!=null) {
    	   Statement statement=connectionForClient.createStatement();
    	   //statement.executeUpdate(sql);
    	   ResultSet rsclient =statement.executeQuery(sqlForClient);
    	   //ResultSet rscount =statement.executeQuery(sqlCountClient);
    	   int index=-1;
    	   
    	  //System.out.println("count initial ="+count);
    	   
    	  /* while(rscount.next())
    	   {
    		   count=rscount.getInt(1);
    		   //System.out.println("size bd is "+count);
    	   }*/
    	   
    	   while(rsclient.next())
    	   {
    		   index++;
    		   client c=new client(index,"","","","","","");
    		   //String dbname=rs.getString("nom");
    		   //System.out.println("nom est "+dbname);
    		   c.setId(rsclient.getInt("id"));
    		   c.setNom(rsclient.getString("nom"));
    		   c.setPnom(rsclient.getString("pnom"));
    		   c.setType(rsclient.getString("type"));
    		   c.setAdr(rsclient.getString("adr"));
    		   c.setTel(rsclient.getString("tel"));
    		   c.setMail(rsclient.getString("mail"));
    		  // System.out.println(c);

    		   cltlist.add(index, c);
    	   }
    	   }
    	   else
    		   System.out.println("frns not connected !");
       } catch (SQLException e) {

		// TODO Auto-generated catch block
		e.printStackTrace();
       }
       
       
       cltid.setCellValueFactory(new PropertyValueFactory<client,Integer>("id"));
		cltnom.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		cltpnom.setCellValueFactory(new PropertyValueFactory<client,String>("pnom"));
		clttype.setCellValueFactory(new PropertyValueFactory<client,String>("type"));
		cltadr.setCellValueFactory(new PropertyValueFactory<client,String>("adr"));
		clttel.setCellValueFactory(new PropertyValueFactory<client,String>("tel"));
		cltmail.setCellValueFactory(new PropertyValueFactory<client,String>("mail"));
		
		myclttab.setItems(cltlist);
		
		showrightClient(null);

	    // Listen for selection changes and show the person details when changed.
	    myclttab.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> showrightClient(newValue));
	}
	
	
	
	public void updatefrns()
	{
		
		frnslist.clear();
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

    		   frnslist.add(index, f);
    	   }
    	   }
    	   else
    		   System.out.println("frns not connected !");
       } catch (SQLException e) {

		// TODO Auto-generated catch block
		e.printStackTrace();
       }
       
       
       frnsid.setCellValueFactory(new PropertyValueFactory<fournisseur,Integer>("idfrns"));
		frnsnom.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		frnsspec.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("spec"));
		frnsadr.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("adr"));
		frnsresp.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("resp"));
		frnstel.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("tel"));
		frnsmail.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("mail"));
		
		myfrnstab.setItems(frnslist);
		
		showrightFrns(null);

	    // Listen for selection changes and show the person details when changed.
	    myfrnstab.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> showrightFrns(newValue));
	}
	
}
