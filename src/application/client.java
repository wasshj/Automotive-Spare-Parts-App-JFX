package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class client {
	
	private SimpleIntegerProperty id;
	private SimpleStringProperty nom;
	private SimpleStringProperty pnom;
	private SimpleStringProperty type;
	private SimpleStringProperty adr;
	private SimpleStringProperty tel;
	private SimpleStringProperty mail;
	
	
	public Integer getId() {
		return id.get();
	}
	public String getNom() {
		return nom.get();
	}
	public String getPnom() {
		return pnom.get();
	}
	public String getType() {
		return type.get();
	}
	public String getAdr() {
		return adr.get();
	}
	public String getTel() {
		return tel.get();
	}
	public String getMail() {
		return mail.get();
	}
	
	
	
	public void setId(Integer id) {
		this.id.set(id);
	}
	public void setNom(String nom) {
		this.nom.set(nom);
	}
	public void setPnom(String pnom) {
		this.pnom.set(pnom);
	}
	public void setType(String type) {
		this.type.set(type);
	}
	public void setAdr(String adr) {
		this.adr.set(adr);
	}
	public void setTel(String tel) {
		this.tel.set(tel);
	}
	public void setMail(String mail) {
		this.mail.set(mail);
	}
	
	
	public StringProperty nameProperty() {
        return nom;
    }
	
	public String givename()
	{
		return nom.get();
	}
	
	
	public client(Integer id, String nom, String pnom,
			String type, String adr, String tel, String mail) {
		super();
		this.id=new SimpleIntegerProperty(id);
		this.nom= new SimpleStringProperty(nom);
		this.pnom= new SimpleStringProperty(pnom);
		this.type= new SimpleStringProperty(type);
		this.adr= new SimpleStringProperty(adr);
		this.tel= new SimpleStringProperty(tel);
		this.mail= new SimpleStringProperty(mail);
	}
	
	
	
	

}
