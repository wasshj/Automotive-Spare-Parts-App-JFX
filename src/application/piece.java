package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class piece {

	private SimpleStringProperty nom;
	private SimpleStringProperty ref;
	private SimpleStringProperty vehicule;
	private SimpleIntegerProperty qqte;
	private SimpleStringProperty frns;
	private SimpleFloatProperty prix;
	
	public String getNom() {
		return nom.get();
	}

	public void setNom(String nom)
	{
		this.nom.set(nom);
	}

	public String getRef() {
		return ref.get();
	}
	
	public void setRef(String ref)
	{
		this.ref.set(ref);
	}


	public String getVehicule() {
		return vehicule.get();
	}

	public void setVehicule(String vehicule)
	{
		this.vehicule.set(vehicule);
	}

	public Integer getQqte() {
		return qqte.get();
	}

	public void setQqte(int qqte)
	{
		this.qqte.set(qqte);
	}


	public String getFrns() {
		return frns.get();
	}

	public void setFrns(String frns)
	{
		this.frns.set(frns);
	}

	public float getPrix() {
		return prix.get();
	}
	
	public void setPrix(Float prix)
	{
		this.prix.set(prix);
	}
	
	public StringProperty nameProperty() {
        return nom;
    }
	

	
	
	public piece(String nom, String ref, String vehicule, Integer qqte, String frns, float prix) {
		super();
		this.nom = new SimpleStringProperty(nom);
		this.ref = new SimpleStringProperty(ref);
		this.vehicule =new SimpleStringProperty(vehicule);
		this.qqte = new SimpleIntegerProperty(qqte);
		this.frns = new SimpleStringProperty(frns);
		this.prix =new SimpleFloatProperty(prix);
	}

	
	public String toString()
	{
		return nom.get()+" "+ref.get()+" "+" "+vehicule.get()+" "+qqte.get()+" "+frns.get()+" "+prix.get() ;
		
	}


	
}
