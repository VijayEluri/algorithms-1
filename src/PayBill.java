// Single Round Match 219 Round 1 - Division II, Level Three

import java.util.Arrays;

public class PayBill {
  boolean[] s;

  public int[] whoPaid(int[] meals, int totalMoney) {
    return meals;

  }


  public static void main(String[] _) {
    System.out.println(Arrays.toString(
        new PayBill().whoPaid(new int[]{ 100, 200, 350 }, 300)));
    System.out.println(Arrays.toString(
        new PayBill().whoPaid(new int[]{ 1000, 1200, 1300 }, 2500)));
    System.out.println(Arrays.toString(
        new PayBill().whoPaid(new int[]{ 150, 200, 350, 400 }, 900)));
    System.out.println(Arrays.toString(
        new PayBill().whoPaid(
            new int[]{
                6584,6733,6018,5840,2723,4902,4260,
                5375,6745,1234,3000,8222,2472,
                4348,1716,9995,415,1234,2345,5679}, 70630)));
    System.out.println(Arrays.toString(
        new PayBill().whoPaid(
            new int[]{
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 50)));
    System.out.println(Arrays.toString(
        new PayBill().whoPaid(
            new int[]{
                1020, 6131, 7841, 5949, 8782, 8187, 8140, 8149, 5387, 1256,
                2785, 5165, 3992, 8989, 1442, 6122, 2841, 9777, 9566, 2775,
                2299, 5653, 1690, 9660, 4382, 3695, 7194, 6753, 4784, 9940, 
                8597, 4627, 7416, 5786, 2333, 2104, 1433, 6896, 2032, 6417,
                760, 4610, 4304, 5864, 6134, 9845, 7018, 4960}, 4494)));
  }
} 