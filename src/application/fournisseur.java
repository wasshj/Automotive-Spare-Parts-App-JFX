package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class fournisseur {

	private SimpleIntegerProperty idfrns;
	private SimpleStringProperty nom;
	private SimpleStringProperty spec;
	private SimpleStringProperty adr;
	private SimpleStringProperty resp;
	private SimpleStringProperty tel;
	private SimpleStringProperty mail;
	
	public fournisseur(int idfrns, String nom, String spec, String adr, String resp, String tel, String mail) {
		super();
		this.idfrns=new SimpleIntegerProperty(idfrns);
		this.nom=new SimpleStringProperty(nom);
		this.spec=new SimpleStringProperty(spec);
		this.adr=new SimpleStringProperty(adr);
		this.resp=new SimpleStringProperty(resp);
		this.tel=new SimpleStringProperty(tel);
		this.mail=new SimpleStringProperty(mail);
	}

	public int getIdfrns() {
		return idfrns.get();
	}

	public String getNom() {
		return nom.get();
	}

	public String getSpec() {
		return spec.get();
	}

	public String getAdr() {
		return adr.get();
	}

	public String getResp() {
		return resp.get();
	}

	public String getTel() {
		return tel.get();
	}

	public String getMail() {
		return mail.get();
	}

	public void setIdfrns(int idfrns) {
		this.idfrns.set(idfrns);
	}

	public void setNom(String nom) {
		this.nom.set(nom);
	}

	public void setSpec(String spec) {
		this.spec.set(spec);
	}

	public void setAdr(String adr) {
		this.adr.set(adr);
	}

	public void setResp(String resp) {
		this.resp.set(resp);
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
	
	
}
