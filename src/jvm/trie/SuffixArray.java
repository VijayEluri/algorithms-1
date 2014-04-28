package trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import util.Pair;


class SuffixRank {
    int suffix;
    Pair rank;

    public SuffixRank(int s, Pair r) {
        suffix = s;
        rank = r;
    }
}


public class SuffixArray {
    static String[] create(String s) {
        final int N = s.length();

        final int[] suffices = new int[N];
        for (int i = 0; i < N; i++) {
            suffices[i] = i;
        }

        final SuffixRank[] srs = new SuffixRank[N];
        Map<Integer, Pair> suffixToRankMap = new HashMap<Integer, Pair>();
        for (int i = 0; i < N; i++) {
            Pair rank = new Pair('a' - s.charAt(suffices[i]), -1); 
            srs[i] = new SuffixRank(suffices[i], rank);
            suffixToRankMap.put(suffices[i], rank);
        }

        for (int i = 1; i <= N; i *= 2) {
            for (int j = 0; j < N; j++) {
                // for all suffices
                int r1 = -1;
                if (j == 0) {
                    r1 = 0;
                } else {
                    r1 = 
                }
                int suffix = suffix(srs[j], i); // suffix after i chars
                    
                    }
                            
                }
            }
            s.charAt(srs[i].suffix + i) 
        }
        return suffices;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(SuffixArray.create("palindrome")));
    }
}
