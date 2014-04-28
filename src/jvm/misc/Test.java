package misc;

import java.util.Arrays;

public class Test {
    static int[] arrange(int[] keys) {
        int i = 0;
        int j = keys.length - 1;

        while (i < j) {
            while (keys[i] < 0) {
                i++;
            }
            while (keys[j] >= 0) {
                j--;
            }

            if (i >= j) {
                break;
            }

            swap(keys, i, j);
        }

        return keys;
    }

    private static void swap(int[] keys, int i, int j) {
        final int t = keys[i];
        keys[i] = keys[j];
        keys[j] = t;
    }

    //
    static int findMissing(int[] a) {
        int i = 0;
        int j = a.length - 1;

        int m = -1;
        while (i < j - 1) {
            m = (i + j) / 2;
            final int nIntegers = m - i;
            if (a[i] + nIntegers < a[m]) {
                j = m;
            } else if (a[i] + nIntegers == a[m]) {
                i = m;
            } else {
                throw new IllegalStateException(i + " " + m + " " + j);
            }
        }
        
        return a[i] + 1;
    }


    public static void main(String[] args) {
        //System.aout.println(Arrays.toString(Test.arrange(new int[] {0, 3, 3, -1, 4, -5, 4, -9, 3})));
        System.out.println(Test.findMissing(new int[] {-1, 1, 2, 10, 11, 14, 20}));
    }
}
