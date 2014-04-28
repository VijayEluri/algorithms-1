package misc;

public class AddStrings {
    static String add(String a, String b) {
        final int la = a.length();
        final int lb = b.length();

        final int lr = Math.max(la, lb);
        final char[] r = new char[lr];
        int carry = 0;

        int k = lr - 1;
        for (int i = la - 1, j = lb - 1; i >= 0 || j >= 0; i--, j--, k--) {
            final int ai = get(a, i);
            final int bj = get(b, j);

            int rk = ai + bj + carry;
            if (rk > 9) {
                carry = rk / 10;
                rk = rk % 10;
            } else {
                carry = 0;
            }

            r[k] = (char) (rk + '0');
        }

        if (carry == 0) {
            return new String(r);
        } else {
            return carry + new String(r);
        }
    }

    private static int get(String s, int i) {
        if (i >= 0) {
            return s.charAt(i) - '0';
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(AddStrings.add("91", "19"));
    }
}
