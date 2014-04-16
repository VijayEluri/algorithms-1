package misc;

public class MultiplyStrings {
    static String multiply(String s1, String s2) {
        long r = 0l;
        final int n1 = s1.length();
        for (int i = 0; i < s1.length(); i++) {
            r += (long) (Math.pow(10, i) * multiply(s1.charAt(n1 - i - 1), s2));
        }

        return Long.toString(r);
    }

    private static long multiply(char c, String s) {
        int n = c -'0';
        int carry = 0;
        int r = 0;
        for (int i = s.length() - 1, j = 0; i >= 0; i--, j++) {
            final int mi = s.charAt(i) - '0';
            int ri = (mi * n  + carry);
            if (ri > 9) {
                carry = ri / 10;
                ri = ri % 10;
            } else {
                carry = 0;
            }
            r += Math.pow(10, j) * ri;
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(MultiplyStrings.multiply("12", "2"));
    }
}
