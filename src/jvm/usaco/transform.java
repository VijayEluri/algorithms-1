package usaco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/*
  ID: rdsr.me1
  PROG: transform
  LANG: JAVA
*/

class Pattern {
    char[][] m;
    int N;

    public Pattern(int _N) {
        N = _N;
        m = new char[N][N];
    }

    Pattern rotate90() {
        Pattern p = new Pattern(N);

        for (int i=N-1; i >=0; i--)
            for (int j=0; j < N; j++)
                p.m[j][N - i -1] = m[i][j];

        return p;
    }

    Pattern rotate180() {
        return rotate90().rotate90();
    }

    Pattern rotate270() {
        return rotate180().rotate90();
    }

    Pattern mirror() {
        Pattern p = new Pattern(N);

        for (int j=N-1; j >=0; j--)
            for (int i=0; i < N; i++)
                p.m[i][N-1 - j] = m[i][j];

        return p;
    }

    @Override
        public boolean equals(Object o) {
        if (!(o instanceof Pattern))
            return false;
       
        Pattern q = (Pattern) o;
        if (N != q.N)
            return false;

        for (int i=0; i < N; i++)
            for (int j=0; j < N; j++)
                if (m[i][j] != q.m[i][j])
                    return false;
        return true;
    }
}

public class transform {
    public static void main(String[] _) throws Exception {
        BufferedReader br = new BufferedReader(
                                               new FileReader("src/main/resources/transform.in"));
        int N = Integer.valueOf(br.readLine());

        Pattern p = new Pattern(N);
        for (int i=0; i < N; i++)
            p.m[i] = br.readLine().toCharArray();

        Pattern q = new Pattern(N);
        for (int i=0; i < N; i++)
            q.m[i] = br.readLine().toCharArray();

        br.close();
        int change = 0;

        if (p.rotate90().equals(q)) change = 1;
        else if (p.rotate180().equals(q)) change = 2;
        else if (p.rotate270().equals(q)) change = 3;
        else if (p.mirror().equals(q)) change = 4;
        else if (p.mirror().rotate90().equals(q)
                 ||  p.mirror().rotate180().equals(q)
                 ||  p.mirror().rotate270().equals(q))
            change = 5;
        else if (p.equals(q)) change = 6;
        else change = 7;

        PrintWriter pw = new PrintWriter("src/main/resources/transform.out");
        pw.write(change);
        pw.close();
    }
}
