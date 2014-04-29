package util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class FastScanner implements Closeable {
  private final BufferedReader reader;
  private StringTokenizer tokenizer;

  public FastScanner(InputStream in) {
    reader = new BufferedReader(new InputStreamReader(in));
    tokenizer = new StringTokenizer("");
  }

  public FastScanner(String filename)
      throws IOException {
    reader = new BufferedReader(new FileReader(filename));
    tokenizer = new StringTokenizer("");
  }

  public String next()
      throws IOException {
    while (!tokenizer.hasMoreTokens()) {
      tokenizer = new StringTokenizer(reader.readLine());
    }
    return tokenizer.nextToken();
  }

  public int nextInt()
      throws IOException {
    return Integer.parseInt(next());
  }

  public double nextDouble()
      throws IOException {
    return Double.parseDouble(next());
  }

  @Override
  public void close()
      throws IOException {
    reader.close();
  }
}
