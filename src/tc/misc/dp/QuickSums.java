package tc.misc.dp;

// Single Round Match 197 Round 1 - Division II, Level Three
// buggy code breaks for some test cases

public class QuickSums {
    int ma[][][];
    int n;
    int[] numbers;

    public static void main(String[] _) {
        System.out.println(new QuickSums().minSums("99999", 45));
        System.out.println(new QuickSums().minSums("1110", 3));
        System.out.println(new QuickSums().minSums("0123456789", 45));
        System.out.println(new QuickSums().minSums("99999", 100));
        System.out.println(new QuickSums().minSums("382834", 100));
        System.out.println(new QuickSums().minSums("9230560001", 71));  //crap, this should give 4
        System.out.println(new QuickSums().minSums("0001", 1));

    }

    public int minSums(String ns, int sum) {
        n = ns.length();
        ma = new int[n][n][sum + 1];
        numbers = parse(ns);

        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++)
                for(int k = 0; k < sum + 1; k++)
                    ma[i][j][k] = Integer.MIN_VALUE;
        return solve(0, n - 1, sum);
    }

    private int solve(int i, int j, int sum) {
        if (i > j)
            return -1;

        if (ma[i][j][sum] > Integer.MIN_VALUE)
            return ma[i][j][sum];

        if (j - i == 2) {
            if (numbers[i] == 1 && numbers[i+1] == 0 && numbers[i+2] == 0 && sum == 100)
                return ma[i][j][sum] = 0;
        }

        if (j - i == 1) {
            if (10 * numbers[i] + numbers[j] == sum)
                return  ma[i][j][sum] = 0;
        }

        if (i == j) {
            if(numbers[i] == sum)
                return ma[i][i][sum] = 0;
        }

        if (equivToNumber(i, j)) {
            if (numbers[j] == sum)
                return ma[i][j][sum] = 0;
        }

        for (int k = i; k < j ; k++) {
            for (int s = 0; s < sum + 1; s++ ) {
                int l = solve(i, k, sum - s);
                int r = solve(k + 1, j, s);

                if (l != -1 && r != -1) {
                    if (ma[i][j][sum] == Integer.MIN_VALUE || l + r + 1 < ma[i][j][sum])
                        ma[i][j][sum] = l + r + 1;
                }
            }
        }
        if (ma[i][j][sum] == Integer.MIN_VALUE)
            ma[i][j][sum] = -1;
        return ma[i][j][sum];
    }

    private boolean equivToNumber(int i, int j) {
        boolean e = true;

        for (; i < j; i++) {
            if (numbers[i] != 0) {
                e = false;
                break;
            }
        }
        return e;
    }

    private int[] parse(String ns) {
        int [] numbers = new int[ns.length()];
        for (int i = 0; i < ns.length(); i++)
            numbers[i] = Character.digit(ns.charAt(i), 10);
        return numbers;
    }
}
