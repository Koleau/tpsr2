package infrastructure;

import java.util.Random;

/**
 * Modélise le réseau de communication entre {@link Calculateur}s. Le réseau est
 * à l'échelle terrestre : les {@link Calculateur} sont placés sur une sphère
 * représentant la terre pour calculer les latences de communication.
 *
 * @author Jean-Michel Busca
 *
 */
final class Reseau {

  //
  // CONSTANTES
  //
  private static final float DEMI_CIRCONFERENCE = 1000.0f; // ms
  private static final float POURCENT_GIGUE = 0.5f; // 50%

  //
  // ATTRIBUTS DE CLASSE
  //
  private static final Random random = new Random(System.currentTimeMillis());

  //
  // CONSTRUCTEURS ET ACCESSEURS
  //
  private Reseau() {
  }

  //
  // METHODES PUBLIQUES
  //
  /**
   * Retourne une nouvelle position dans le réseau.
   *
   * @return une nouvelle position dans le réseau
   */
  public static Position newPosition() {
    return new Position();

  }

  /**
   * Calcule la latence de transmission d'un message entre deux positions. La
   * latence inclut une jigue aléatoire.
   *
   * @param p1
   *          première position dans le réseau
   * @param p2
   *          deuxième position dans le réseau
   * @return la latence entre les deux positions
   */
  public static final long latence(Position p1, Position p2) {
    float l = DEMI_CIRCONFERENCE * (p1.distance(p2) / (float) Math.PI);
    l *= (1 + ((random.nextFloat() - 0.5f) * 2.0f * POURCENT_GIGUE));
    return (long) l;
  }

  //
  // TEST
  //
  public static void main(String[] args) throws InterruptedException {

    final int MAX = 1000;

    Position p1 = Position.PXp1Y0Z0; // newPosition();
    System.out.println("p1 = " + p1);
    Position p2 = Position.PXm1Y0Z0; // newPosition();
    System.out.println("p2 = " + p2);

    long t = 0;
    long min = Long.MAX_VALUE;
    long max = Long.MIN_VALUE;

    for (int i = 0; i < MAX; i++) {

      long l = latence(p1, p2);
      // System.out.println("latence = " + l);

      if (l < min) {
        min = l;
      } else if (max < l) {
        max = l;
      }
      t += l;

    }

    System.out.print("min = " + min);
    System.out.print(", moy = " + ((float) t / MAX));
    System.out.println(", max = " + max);

  }

}
