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
   * Construit un processus dot� d'un service d'exclusion mutuelle r�partie.
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
   * Demande l'entr�e en section critique.
   * <p>
   * ATTENTION, si cette m�thode est red�finie, la red�finition doit �tre
   * <code>synchronized<code>.
   * 
   */
  protected synchronized void entrer() {
    // � d�velopper
  }

  /**
   * Signale la sortie de la section critique.
   * <p>
   * ATTENTION, si cette m�thode est red�finie, la red�finition doit �tre
   * <code>synchronized<code>.
   * 
   */
  protected synchronized void sortir() {
    // � d�velopper
  }

  //
  // RECEPTION DE MESSAGES
  //
  @Override
  public void recevoir(Message m) {
    // � d�velopper
  }

  //
  // METHODES UTILITAIRES
  //
  /**
   * Bloque le thread appelant, jusqu'� ce qu'un autre thread (typiquement un
   * thread d�livrant un message) appelle {@link #debloquer()}.
   */
  protected final synchronized void bloquer() {
    try {
      wait();
    } catch (InterruptedException e) {
      afficher("interruption inattendue : " + e);
    }
  }

  /**
   * D�bloque tous les threads ayant appel� {@link #bloquer()}.
   * 
   */
  protected final synchronized void debloquer() {
    notifyAll();
  }

  /**
   * Arme un minuteur avec le d�lai sp�cifi�. A �ch�ance, le message sp�cifi�
   * est affich� � la console, � moins que le minuteur soit d�sarm� entre temps
   * en appelant {@link #desarmerMinuteur()}.
   * 
   * @param delai
   *          d�lai d'attente en ms
   * @param message
   *          message � afficher quand le d�lai est d�pass�
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
   * D�sarme le minuteur arm� par {@link #armerMinuteur(int, String)}. Si le
   * minuteur, n'a pas �t� arm�, l'appel est sans effet.
   */
  protected final synchronized void desarmerMinuteur() {
    if (tache != null) {
      tache.cancel();
    }
  }

}
