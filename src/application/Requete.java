package application;

import java.io.Serializable;

public class Requete implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private boolean acq;
	private int horloge;
	private String emeteur;
	//id du processus
	private int id;
	
	
	@Override
	public String toString() {
		return "[Requete [acq=" + acq + ", horloge=" + horloge + ", emeteur=" + emeteur + ", id=" + id + "]]";
	}


	public Requete(boolean acq, int horloge) {
		super();
		this.acq = acq;
		this.horloge = horloge;
	}


	public Requete(boolean acq, int horloge, String emeteur) {
		super();
		this.acq = acq;
		this.horloge = horloge;
		this.emeteur = emeteur;
	}


	public Requete(boolean acq, int horloge, String emeteur, int id) {
		super();
		this.acq = acq;
		this.horloge = horloge;
		this.emeteur = emeteur;
		this.id = id;
	}


	public boolean isAcq() {
		return acq;
	}


	public void setAcq(boolean acq) {
		this.acq = acq;
	}


	public int getHorloge() {
		return horloge;
	}


	public void setHorloge(int horloge) {
		this.horloge = horloge;
	}


	public String getEmeteur() {
		return emeteur;
	}


	public void setEmeteur(String emeteur) {
		this.emeteur = emeteur;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
}
