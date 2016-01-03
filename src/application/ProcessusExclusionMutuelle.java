package application;

import infrastructure.Calculateur;
import infrastructure.Message;

import java.util.Timer;
import java.util.TimerTask;

public class ProcessusExclusionMutuelle extends Calculateur {

  //
  // ATTRIBUTS DE CLASSE
  //
  private static final Timer ordonnanceur = new Timer(true);

  //
  // ATTRIBUTS D'OBJET
  //
  private TimerTask tache;

  //
  // CONSTRUCTEURS ET ACCESSEURS
  //
  /**
   * Construit un processus doté d'un service d'exclusion mutuelle répartie.
   * 
   * @param nom
   *          nom du processus
   * @param groupe
   *          nom du groupe auquel il appartient
   */
  public ProcessusExclusionMutuelle(String nom, String groupe) {
    super(nom, true);
  }

  //
  // METHODES PUBLIQUES
  //
  @Override
  public void programme() throws InterruptedException {

    afficher("demarrage");

    // mettre ici le code d'une application de test
    // sollicitant le service d'exclusion mutuelle

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
    // à développer
  }

  /**
   * Signale la sortie de la section critique.
   * <p>
   * ATTENTION, si cette méthode est redéfinie, la redéfinition doit être
   * <code>synchronized<code>.
   * 
   */
  protected synchronized void sortir() {
    // à développer
  }

  //
  // RECEPTION DE MESSAGES
  //
  @Override
  public void recevoir(Message m) {
    // à développer
  }

  //
  // METHODES UTILITAIRES
  //
  /**
   * Bloque le thread appelant, jusqu'à ce qu'un autre thread (typiquement un
   * thread délivrant un message) appelle {@link #debloquer()}.
   */
  protected final synchronized void bloquer() {
    try {
      wait();
    } catch (InterruptedException e) {
      afficher("interruption inattendue : " + e);
    }
  }

  /**
   * Débloque tous les threads ayant appelé {@link #bloquer()}.
   * 
   */
  protected final synchronized void debloquer() {
    notifyAll();
  }

  /**
   * Arme un minuteur avec le délai spécifié. A échéance, le message spécifié
   * est affiché à la console, à moins que le minuteur soit désarmé entre temps
   * en appelant {@link #desarmerMinuteur()}.
   * 
   * @param delai
   *          délai d'attente en ms
   * @param message
   *          message à afficher quand le délai est dépassé
   */
  protected final synchronized void armerMinuteur(int delai,
          final String message) {
    tache = new TimerTask() {
      @Override
      public void run() {
        afficher(message);
      }
    };
    ordonnanceur.schedule(tache, delai);
  }

  /**
   * Désarme le minuteur armé par {@link #armerMinuteur(int, String)}. Si le
   * minuteur, n'a pas été armé, l'appel est sans effet.
   */
  protected final synchronized void desarmerMinuteur() {
    if (tache != null) {
      tache.cancel();
    }
  }

}
