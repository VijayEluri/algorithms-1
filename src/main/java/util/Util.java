package util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Util {
  public static Scanner scanner(String filename) {
    try {
      return new Scanner(new BufferedInputStream(new FileInputStream(filename)));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public static PrintWriter writer(String fileName) {
    try {
      return new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
