package misc;

public class SumTo0 {
    static boolean sumTo(int[] nos, int i, int n) {
        if (i == 0) {
            if (nos[i] == n) {
                return true;
            } else {
                return false;
            }
        }

        final int m = nos[i];
        return sumTo(nos, i - 1, 0 - (m + n)) ||
                sumTo(nos, i - 1, 0 - (m - n));

    }

    public static void main(String[] args) {
        System.out.println(sumTo(new int[] {3, 6, 2}, 2, 0));
    }
}
