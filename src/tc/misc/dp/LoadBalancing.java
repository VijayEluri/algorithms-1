package tc.misc.dp;

import java.util.HashMap;
import java.util.Map;

public class LoadBalancing {
  int [] chunkSizes;
  Map<String, Integer> cache = new HashMap<String, Integer>();

  int minTime(int[] chunkSizes) {
    this.chunkSizes = chunkSizes;
    return minTime(chunkSizes.length - 1, 0, 0);
  }

  int minTime(
      int i, int time_spent_on_cpu1, int time_spent_on_cpu2) {
    if (i == 0)
      return Math.max(
          Math.min(time_spent_on_cpu1, time_spent_on_cpu2) + chunkSizes[i], 
          Math.max(time_spent_on_cpu1, time_spent_on_cpu2));

    String key = i + " " + time_spent_on_cpu1 + " " + time_spent_on_cpu2;
    if (cache.containsKey(key))
      return cache.get(key);

    int minTime = 
      Math.min(
          minTime(i - 1,  time_spent_on_cpu1 + chunkSizes[i], time_spent_on_cpu2),
          minTime(i - 1,  time_spent_on_cpu1, time_spent_on_cpu2 + chunkSizes[i]));

    cache.put(key, minTime);
    return minTime;
  }

  public static void main(String[] _) {
    System.out.println(new LoadBalancing().minTime(new int[]{3072, 3072, 7168, 3072, 1024}));
    System.out.println(new LoadBalancing().minTime(new int[]{2048}));
    System.out.println(new LoadBalancing().minTime(new int[]{2048, 1024, 1024, 2048}));
    System.out.println(new LoadBalancing().minTime(
        new int[]{
            4194304, 4194304, 4194304, 4194304, 4194304,
            4194304, 4194304, 4194304, 4194304, 4194304,
            4194304, 4194304, 4194304, 4194304, 4194304,
            4194304, 4194304, 4194304, 4194304, 4194304,
            4194304, 4194304, 4194304, 4194304, 4194304,
            4194304, 4194304, 4194304, 4194304, 4194304,
            4194304, 4194304, 4194304, 4194304, 4194304,
            4194304, 4194304, 4194304, 4194304, 4194304,
            4194304, 4194304, 4194304, 4194304, 4194304,
            4194304, 4194304, 4194304, 4194304, 4194304}));
  }

}
