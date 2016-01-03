package infrastructure;

import java.util.Random;

/**
 * La position d'un {@link Calculateur} dans le {@link Reseau}.
 *
 * @author Jean-Michel Busca
 *
 */
public final class Position {

  //
  // CONSTANTES
  //
  public static final Position PXp1Y0Z0 = new Position(0.0f, 0.0f);
  public static final Position PXm1Y0Z0 = new Position((float) Math.PI, 0.0f);

  //
  // ATTRIBUTS DE CLASSE
  //
  private static final Random random = new Random(System.currentTimeMillis());

  //
  // ATTRIBUTS D'OBJET
  //
  private final float theta;
  private final float phi;

  //
  // CONSTRUCTEURS ET ACCESSEURS
  //
  /**
   * Crée une position aléatoire.
   */
  public Position() {
    this.theta = (float) Math.asin(2.0 * random.nextFloat() - 1.0);
    this.phi = (float) (2.0 * Math.PI * random.nextFloat());
  }

  /**
   * Crée une position ayant les coordonnées spécifiés.
   *
   * @param t
   *          coordonnée theta
   * @param p
   *          coordonnée phi
   */
  public Position(float t, float p) {
    this.theta = t;
    this.phi = p;
  }

  public float getTheta() {
    return theta;
  }

  public float getPhi() {
    return phi;
  }

  @Override
  public String toString() {
    return "Position [theta=" + theta + ", phi=" + phi + "]";
  }

  //
  // DISTANCE
  //
  public final float distance(Position p) {
    double d = Math.acos(Math.cos(phi - p.phi) * Math.cos(theta)
            * Math.cos(p.theta) + Math.sin(theta) * Math.sin(p.theta));
    return (float) d;
  }

  //
  // TEST
  //
  public static void main(String[] args) throws InterruptedException {

    Position p1 = new Position(0.0f, 0.0f);
    System.out.println("p1 = " + p1);
    Position p2 = new Position((float) Math.PI, 0.0f);
    System.out.println("p2 = " + p2);
    Position p3 = new Position(0.0f, (float) Math.PI);
    System.out.println("p3 = " + p3);

    System.out.println("d(p1, p2) = " + p1.distance(p2));
    System.out.println("d(p1, p3) = " + p1.distance(p3));
    System.out.println("d(p2, p3) = " + p2.distance(p3));
  }

}
