package misc;

public class FindOne {
  int find(int[] a) {
    if (a[0] == '1')
      return 0;
    
    int i = 1;
    int j = -1;
    while(j == -1) {
      if (a[i] == '0')
        i *= 2;
      if (a[i] == '1')
        j = i;
    }
    
    while (i < j) {
      int m = (i + j)/2;
      if (a[m] == '0')
        i = m + 1;
      else if (a[m] == '1')
        j = m - 1;
    }
    return a[i];
  }
}
