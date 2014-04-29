package tc.misc;

public class Archimedes {
  public double approximatePi(int numSides) {
    return numSides * Math.cos(angle(numSides) / 2);
  }

  private double angle(int numSides) {
    return Math.toRadians(180 - 360.0 / numSides);
  }

  public static void main(String[] args) {
    System.out.println(new Archimedes().approximatePi(17280));
  }
}
