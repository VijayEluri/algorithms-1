package misc;

import java.util.HashMap;
import java.util.Map;

public class PostageStamps2 {
    Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
    int[] stamps;

    // much efficient algorithm
    int smallestPostage(int m, int[] denominations) {
        for (int d : denominations)
            cache.put(d, 1);
        stamps = denominations;

        for (int postage = 1; true; postage++) //hopefully the algo, may not run forever
            if (minStamps(postage) > m)
                return postage;
    }

    private int minStamps(int postage) {
        if (postage == 0)
            return 0;

        if (cache.containsKey(postage))
            return cache.get(postage);

        int min = Integer.MAX_VALUE;
        for (int stamp : stamps)
            if (postage > stamp) {
                int t = minStamps(postage - stamp) + 1;
                if (t < min)
                    min = t;
            }

        cache.put(postage, min);
        return min;
    }

    public static void main(String[] _) {
        System.out.println(new PostageStamps2().smallestPostage(10000,  new int[]{1, 2, 5, 7, 11, 13}));
        System.out.println(new PostageStamps2().smallestPostage(2,  new int[]{1, 1}));
        System.out.println(new PostageStamps2().smallestPostage(3,  new int[]{3}));
    }
}
