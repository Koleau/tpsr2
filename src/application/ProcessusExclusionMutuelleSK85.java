package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import infrastructure.Message;

public class ProcessusExclusionMutuelleSK85 extends ProcessusExclusionMutuelle {

	private static final int NBDEMANDE = 3;
	
	private final int nbProcessus = 3;
	private final int id;
	
	private int dem;
	private boolean enSC;
	private boolean aJeton;
	
	private JetonSK85 jeton;
	
	private List<Integer> req;
	
	
	
	public ProcessusExclusionMutuelleSK85(String nom, String groupe, int id) {
		super(nom, groupe);
		this.id = id;
		this.dem = 0;
		this.enSC = false;
		if (id == 1) {
			this.aJeton = true;
			this.jeton = new JetonSK85();
		} else {
			this.aJeton = false;
		}
		req = new ArrayList<Integer>();
		for (int i = 0; i < nbProcessus; i++) {
			req.add(0);
		}
	}
	
	
	  //
	  // METHODES PUBLIQUES
	  //
	  @Override
	  public void programme() throws InterruptedException {

	    afficher("demarrage");

	    afficher("demarrage "+this.getNom());

	    for (int i = 0; i < NBDEMANDE; i++) {
	    	afficher(getNom()+" demande de SC n: "+i);
			randomSleep();
			entrer();
		}

	    afficher("terminaison "+this.getNom());

	    afficher("terminaison");

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
	    this.dem ++;
	    if (!this.aJeton) {
	    	diffuser("Groupe", new Message(this.dem));
	    	bloquer();
	    }
	  }

	  /**
	   * Signale la sortie de la section critique.
	   * <p>
	   * ATTENTION, si cette méthode est redéfinie, la redéfinition doit être
	   * <code>synchronized<code>.
	   * 
	   */
	  protected synchronized void sortir() {
	    this.enSC = false;
	    ArrayList<Integer> tab = (ArrayList<Integer>)this.jeton.getTab();
	    tab.set(this.id-1, this.dem);
	    this.jeton.setTab(tab);
	    for (int k = 0; k < this.nbProcessus; k++) {
			if (this.req.get(k) > this.jeton.getTab().get(k)) {
				this.jeton.addToFa(k+1);
			}
		}
	    if (!this.jeton.getFa().isEmpty()) {
	    	this.aJeton = false;
	    	int suiv = this.jeton.getFirstFaAndRemove();
	    	//TODO: Changer le jeton pour avoir des strings pour envoyer le message
	    	envoyer(n, m);
	    }
	  }

	  //
	  // RECEPTION DE MESSAGES
	  //
	  @Override
	  public void recevoir(Message m) {
		RequeteSK85 r = (RequeteSK85)m.getContenu();
	    if (!r.isJeton()) {
	    	int reqVal = this.req.get(r.getIdEmetteur()-1);
	    	this.req.add(r.getIdEmetteur()-1, Math.max(reqVal, r.getDem()));
	    	if (this.aJeton && !this.enSC && this.req.get(r.getIdEmetteur()-1) == r.getDem()) {
	    		this.aJeton = false;
	    		RequeteSK85 rep = new RequeteSK85(this.id, this.jeton);
	    		envoyer(m.getEmetteur(), new Message(rep));
	    	}
	    } else {
	    	this.jeton = r.getJeton();
	    	this.aJeton = true;
	    	this.enSC = true;
	    	randomSleep();
	    	sortir();
	    }
	  }
	  
	  private void randomSleep() {
		Random rand = new Random();
		try {
			Thread.sleep(rand.nextInt(3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	  }
	  

}
