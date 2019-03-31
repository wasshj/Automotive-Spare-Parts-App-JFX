package application;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class achat {
	
	
	private SimpleIntegerProperty id;
	private SimpleStringProperty refp;
	private SimpleStringProperty nomp;//
	private SimpleIntegerProperty idclt;
	private SimpleFloatProperty prixunit;//
	private SimpleIntegerProperty qqtetotal;
	private SimpleFloatProperty prixtotal;//
	
	public String toString()
	{
		return id.get()+" "+refp.get()+" "+" "+nomp.get()+" "+idclt.get()+" "+prixunit.get()+" "+qqtetotal.get()+" "+prixtotal;
	}
	
	public achat(int id, String refp, String nomp, int idclt, float prixunit, int qqtetotal, float prixtotal) {
		super();
		this.id=new SimpleIntegerProperty(id);
		this.refp = new SimpleStringProperty(refp);
		this.nomp = new SimpleStringProperty(nomp);
		this.idclt = new SimpleIntegerProperty(idclt);
		this.prixunit = new SimpleFloatProperty(prixunit);
		this.qqtetotal = new SimpleIntegerProperty(qqtetotal);
		this.prixtotal = new SimpleFloatProperty(prixtotal);
	}
	
	public void calculPrixTotoal()
	{
		prixtotal.set(qqtetotal.get()*prixunit.get());
	}
	public StringProperty nameProperty() {
        return nomp;
    }
	public int getId() {
		return id.get();
	}
	public String getRefp() {
		return refp.get();
	}
	public String getNomp() {
		return nomp.get();
	}
	public int getIdclt() {
		return idclt.get();
	}
	public float getPrixunit() {
		return prixunit.get();
	}
	public int getQqtetotal() {
		return qqtetotal.get();
	}
	public float getPrixtotal() {
		return prixtotal.get();
	}
	public void setId(Integer id) {
		this.id.set(id);
	}
	public void setRefp(String refp) {
		this.refp.set(refp);
	}
	public void setNomp(String nomp) {
		this.nomp.set(nomp);
	}
	public void setIdclt(Integer idclt) {
		this.idclt.set(idclt);
	}
	public void setPrixunit(Float prixunit) {
		this.prixunit.set(prixunit);
	}
	public void setQqtetotal(Integer qqtetotal) {
		this.qqtetotal.set(qqtetotal);
	}
	public void setPrixtotal(Float prixtotal) {
		this.prixtotal.set(prixtotal);
	}
	
	public void addToPrixTottal(float x)
	{
		prixtotal.add(x);
	}
	
	

}
