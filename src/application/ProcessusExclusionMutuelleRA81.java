package application;

import java.util.ArrayList;
import java.util.List;

import infrastructure.Message;

public class ProcessusExclusionMutuelleRA81 extends ProcessusExclusionMutuelle {
	
	
	private static final int NBDEMANDE = 3;
	
	private int nAcq;
	private List<Requete> fa;
	private Requete reqLoc;
	private int horloge;
	private static int nbProcessus = 0;
	private int id;
	

	public ProcessusExclusionMutuelleRA81(String nom, String groupe) {
		super(nom, groupe);
		this.horloge = 0;
		this.fa = new ArrayList<Requete>();
		this.nAcq = 0;
		this.reqLoc = null;
		nbProcessus ++;
		this.id = nbProcessus;
	}
	
  //
  // METHODES PUBLIQUES
  //
  @Override
  public void programme() throws InterruptedException {

    afficher("demarrage "+this.getNom());

    for (int i = 0; i < NBDEMANDE; i++) {
    	
		randomSleep();
		armerMinuteur(20000, "ERREUR DE VIVACITE");
		afficher(getNom()+" demande de SC n: "+i);
		entrer();
		
	}

    afficher("terminaison "+this.getNom());

  }

//
  // SERVICE D'EXLCUSION MUTUELLE
  //
  /**
   * Demande l'entrée en section critique.
   * <p>
   * ATTENTION, si cette méthode est redéfinie, la redéfinition doit être
   * <code>synchronized<code>.
   * 
   */
  protected synchronized void entrer() {
	this.horloge ++;
    this.reqLoc = new Requete(false, this.horloge, this.getNom());
    diffuser("Groupe", new Message(this.reqLoc));
    this.nAcq = 1;
    bloquer();
  }

  /**
   * Signale la sortie de la section critique.
   * <p>
   * ATTENTION, si cette méthode est redéfinie, la redéfinition doit être
   * <code>synchronized<code>.
   * 
   */
  protected synchronized void sortir() {
	  desarmerMinuteur();
	  decrPEnSC();
	List<Requete> requetes = new ArrayList<Requete>(this.fa);
    for (Requete r : requetes) {
    	this.horloge ++;
    	Requete acq = new Requete(true, this.horloge, this.getNom());
    	envoyer(r.getEmeteur(), new Message(acq));
    }
    this.fa.removeAll(requetes);
    this.reqLoc = null;
    this.nAcq = 0;
    debloquer();
    afficher(getNom()+" sort en SC");
  }

  //
  // RECEPTION DE MESSAGES
  //
  @Override
  public void recevoir(Message m) {
    Requete r = (Requete)m.getContenu();
    this.horloge = Math.max(this.horloge, r.getHorloge()) + 1;
    if (r.isAcq()) {
    	this.nAcq ++;
    	if (this.nAcq == nbProcessus) {
    		addPEnSC();
    		afficher(getNom()+" entre en SC");
    		randomSleep();
    		sortir();
    	}
    } else if (this.reqLoc == null 
    		|| this.reqLoc.getHorloge() > r.getHorloge() 
    		|| (this.reqLoc.getHorloge() == r.getHorloge() && this.id > r.getId())) {
    	
    	this.horloge ++;
    	Requete acq = new Requete(true, this.horloge, this.getNom());
    	envoyer(r.getEmeteur(), new Message(acq));
    } else {
    	this.fa.add(r);
    }
  }
  
  private void randomSleep() {
	try {
		Thread.sleep(1500);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
  }
}
