package test;

import infrastructure.Groupe;
import application.ProcessusExclusionMutuelle;

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
    ProcessusExclusionMutuelle p1 = new ProcessusExclusionMutuelle("P1",
            "Groupe");
    ProcessusExclusionMutuelle p2 = new ProcessusExclusionMutuelle("P2",
            "Groupe");
    ProcessusExclusionMutuelle p3 = new ProcessusExclusionMutuelle("P3",
            "Groupe");

    // ATTENTION : ajuster la valeur ci-dessous pour couvrir la durée du test
    Thread.sleep(10000);
  }

}

