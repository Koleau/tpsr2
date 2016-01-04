package test;

import infrastructure.Groupe;
import application.ProcessusExclusionMutuelle;
import application.ProcessusExclusionMutuelleRA81;

/**
 * Programme de test.
 * 
 * @author Jean-Michel Busca
 * 
 */
public class Test {

  public static void main(String[] args) throws InterruptedException {
    // créer le groupe des processus devant s'exclure mutuellement
    Groupe g = new Groupe("Groupe");
    g.ajouter("P1");
    g.ajouter("P2");
    g.ajouter("P3");

    // créer et lancer le/les processus du système réparti
    ProcessusExclusionMutuelleRA81 p1 = new ProcessusExclusionMutuelleRA81("P1",
            "Groupe");
    ProcessusExclusionMutuelleRA81 p2 = new ProcessusExclusionMutuelleRA81("P2",
            "Groupe");
    ProcessusExclusionMutuelleRA81 p3 = new ProcessusExclusionMutuelleRA81("P3",
            "Groupe");

    // ATTENTION : ajuster la valeur ci-dessous pour couvrir la durée du test
    Thread.sleep(30000);
    System.out.println("Fin !");
  }

}

