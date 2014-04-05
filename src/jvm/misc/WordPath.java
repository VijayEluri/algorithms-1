package misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordPath {
    Queue<String> q;
    Set<String> cache;
    Map<String, String> path;
    Set<String> dict;

    String start, end;

    public WordPath() throws IOException {
        dict = new HashSet<String>();
        final BufferedReader br = new BufferedReader(new FileReader("/usr/share/dict/words"));
        try {
            String w = null;
            while ((w = br.readLine()) != null) {
                if (w.length() == 3) {
                    dict.add(w.toLowerCase());
                }
            }
        } finally {
            br.close();
        }
    }

    Collection<String> path(String start, String end) {
        this.start = start;
        this.end = end;
        
        q = new LinkedList<String>();
        cache = new HashSet<String>();
        path = new HashMap<String, String>();

        q.add(start);
        cache.add(start);
        path.put(start, "");

        while (!q.isEmpty()) {
            final String w = q.poll();
            if (w.equals(end)) {
                // return path;
                return generatePath();
            }
            for (final String nghbr : neighbors(w)) {
                if (cache.add(nghbr)) {
                    path.put(nghbr, w);
                    q.offer(nghbr);
                }
            }
        }

        return Collections.emptyList(); // no path
    }

    private Collection<String> generatePath() {
        final Collection<String> s = new LinkedList<String>();
        String w = end;
        while (!w.equals("")) {
            s.add(w);
            w = path.get(w);
        }
        return s;
    }

    private Collection<String> neighbors(String w) {
        final Collection<String> r = new LinkedList<String>();

        for (int i = 0; i < 3; i++) {
            final char[] wc = w.toCharArray();
            for (char j = 'a'; j <= 'z'; j++) {
                if (w.charAt(i) != j) {
                    wc[i] = j;
                    final String wcS = new String(wc);
                    if (dict.contains(wcS)) {
                        r.add(wcS);
                    }
                }
            }
        }
        return r;
    }

    public static void main(String[] args) throws IOException {
        final WordPath wp = new WordPath();
        System.out.println(wp.path("god", "dog"));
    }
}
