package tc.misc.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Car implements Comparable<Car> {
  int cost;
  int features;
  int fixTimes;

  public Car(int _cost, int _features, int _fixTimes) {
    cost     = _cost;
    features = _features;
    fixTimes = _fixTimes;
  }

  @Override
  public int compareTo(Car o) {
    if (cost > o.cost)
      return 1;
    if (cost == o.cost)
      return 0;
    return -1;
  }
}

public class AutoMarket {
  public int maxSet(int[] cost, int[] features, int[] fixTimes) {
    List<Car> cars = new ArrayList<Car>(cost.length);
    for (int i = 0; i < cost.length; i++)
      cars.add(new Car(cost[i], features[i], fixTimes[i]));
    Collections.sort(cars);

    Car[] carsA = cars.toArray(new Car[cost.length]);
    int[] s = new int[cost.length];
    s[0] = 1;
    for (int i = 1; i < cost.length; i++) {
      s[i] = 1;
      for (int j = i - 1; j >= 0; j--)
        if (satisfies(carsA[i], carsA[j]) &&
            s[j] + 1 > s[i])
          s[i] = s[j] + 1;
    }
    
    int max = -1;
    for (int i = 0; i < cost.length; i++)
      if (s[i] > max)
        max = s[i];
    return max;
  }

  private boolean satisfies(Car i, Car j) {
    if (i.cost > j.cost && i.features < j.features && i.fixTimes > j.fixTimes)
      return true;
    return false;
  }

  public static void main(String[] _) {
    System.out.println(new AutoMarket().maxSet(
        new int[]{10000, 14000, 8000, 12000},
        new int[]{1, 2, 4, 3},
        new int[]{17, 15, 8, 11}));

      System.out.println(new AutoMarket().maxSet(
          new int[]{1,2,3,4,5},
          new int[]{1,2,3,4,5},
          new int[]{1,2,3,4,5}));

      System.out.println(new AutoMarket().maxSet(
          new int[]{9000, 6000, 5000, 5000, 7000},
          new int[]{1, 3, 4, 5, 2},
          new int[]{10, 6, 6, 5, 9}));

      System.out.println(new AutoMarket().maxSet(
          new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20},
          new int[]{20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1},
          new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}));

      System.out.println(new AutoMarket().maxSet(
          new int[]{1000, 1000, 1000, 1000, 2000},
          new int[]{3,3,4,3,3},
          new int[]{3,3,3,4,3}));
  }
}
