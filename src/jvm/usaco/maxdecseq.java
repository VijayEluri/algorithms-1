package usaco;

import java.io.IOException;

import util.FastScanner;

public class maxdecseq {
    public static void main(String[] args) throws IOException {
        final FastScanner s = new FastScanner("resources/maxdecseq.in");
        final int n = s.nextInt();
        final int[] seq = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = s.nextInt();
        }
        s.close();

        final int[] bestRun = new int[n];
        bestRun[0] = seq[n - 1];
        int highestRun = 1;

        for (int i = n - 2; i >= 0; i--) {
            if (seq[i] < bestRun[0]) {
                bestRun[0] = seq[i];
                continue;
            }
            for (int j = highestRun - 1; j >= 0; j--) {
                if (seq[i] > bestRun[j]) {
                    if (j == highestRun - 1 || seq[i] < bestRun[j + 1]) {
                        bestRun[j + 1] = seq[i];
                        if (j + 1 == highestRun) {
                            highestRun += 1;
                        }
                    }
                }
            }
        }
        System.out.println(highestRun);
    }
}
