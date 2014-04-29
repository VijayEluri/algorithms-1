package tc.misc.dp;

// SRM 233 Div 2 Level 3

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class Car implements Comparable<Car> {
  int cost;
  int features;
  int fixTimes;

  public Car(int _cost, int _features, int _fixTimes) {
    cost = _cost;
    features = _features;
    fixTimes = _fixTimes;
  }

  public int compareTo(Car o) {
    if (cost > o.cost) {
      return 1;
    }
    if (cost == o.cost) {
      return 0;
    }
    return -1;
  }
}

public class AutoMarket {
  public int maxSet(int[] cost, int[] features, int[] fixTimes) {
    List<Car> cars = new ArrayList<Car>(cost.length);
    for (int i = 0; i < cost.length; i++) {
      cars.add(new Car(cost[i], features[i], fixTimes[i]));
    }
    Collections.sort(cars);

    Car[] carsA = cars.toArray(new Car[cost.length]);
    int[] s = new int[cost.length];
    s[0] = 1;
    for (int i = 1; i < cost.length; i++) {
      s[i] = 1;
      for (int j = i - 1; j >= 0; j--) {
        if (satisfies(carsA[i], carsA[j]) && s[j] + 1 > s[i]) {
          s[i] = s[j] + 1;
        }
      }
    }

    int max = -1;
    for (int i = 0; i < cost.length; i++) {
      if (s[i] > max) {
        max = s[i];
      }
    }
    return max;
  }

  private boolean satisfies(Car i, Car j) {
    if (i.cost > j.cost && i.features < j.features && i.fixTimes > j.fixTimes) {
      return true;
    }
    return false;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
