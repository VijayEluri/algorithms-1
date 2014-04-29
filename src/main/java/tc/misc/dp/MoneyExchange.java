package tc.misc.dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class MoneyExchange {
  Map<String, Double> cache = new HashMap<String, Double>();
  String[] rates;
  Set<String> visiting = new HashSet<String>();

  public double bestRate(String[] rates, String type1, String type2) {
    this.rates = rates;
    return solve(type1, type2);
  }

  private double solve(String type1, String type2) {
    if (type1.equals(type2)) {
      return 1;
    }

    String key = type1 + " " + type2;
    if (cache.containsKey(key)) {
      return cache.get(key);
    }

    double max = -1;
    visiting.add(type1);
    for (String rate : rates) {
      String[] components = rate.split(" +");
      if (components[0].equals(type1)) {
        double num1 = Double.valueOf(components[1]);
        double num2 = Double.valueOf(components[3]);
        if (visiting.contains(components[2])) //cycle present
        {
          continue;
        }

        double _max = solve(components[2], type2);
        if (_max == -1) {
          continue;
        } else {
          _max = num2 / num1 * _max;
        }
        if (_max > max) {
          max = _max;
        }
      }
    }

    visiting.remove(type1);
    cache.put(key, max);
    return max;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
