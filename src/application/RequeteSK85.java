package application;

import java.io.Serializable;

public class RequeteSK85 implements Serializable {

	private final int idEmetteur;
	private final boolean isJeton;
	private JetonSK85 jeton;
	private int dem;
	
	
	public RequeteSK85(int idEmetteur, int dem) {
		super();
		this.idEmetteur = idEmetteur;
		this.dem = dem;
		this.isJeton = false;
	}


	public RequeteSK85(int idEmetteur, JetonSK85 jeton) {
		super();
		this.idEmetteur = idEmetteur;
		this.isJeton = true;
		this.jeton = jeton;
	}


	public JetonSK85 getJeton() {
		return jeton;
	}


	public void setJeton(JetonSK85 jeton) {
		this.jeton = jeton;
	}


	public int getDem() {
		return dem;
	}


	public void setDem(int dem) {
		this.dem = dem;
	}


	public int getIdEmetteur() {
		return idEmetteur;
	}


	public boolean isJeton() {
		return isJeton;
	}
	
	

}
