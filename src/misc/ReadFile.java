package misc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadFile {
  byte[] toByteArray(File f) throws IOException {
    final byte[] bytes = new byte[(int) f.length()];
    InputStream in = null;
    try {
      //in = ...
      new BufferedInputStream(new FileInputStream(f));
      int bytesRead = -1;
      int offset = 0;
      long bytesToRead = f.length();
      while ((bytesRead = in.read(bytes, offset, bytesRead - offset)) != -1) {
        offset += bytesRead;
      }
      return bytes;
    } finally {
      in.close();
    }
  }
}
