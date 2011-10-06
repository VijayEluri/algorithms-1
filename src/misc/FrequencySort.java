package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FrequencySort {
  static final class Element implements Comparable<Element> {
    final int index;
    final int value;
    final int frequency;

    public Element(
        int _index, int _value, int _frequency) {
      index = _index;
      value = _value;
      frequency = _frequency;
    }   

    public String toString() {
      return "("+ index + " " + value + " " + frequency +")";
    }

    @Override public int compareTo(Element o) {
      if (frequency < o.frequency)
        return -1;
      else if (frequency > o.frequency)
        return 1;
      else {
        if (index < o.index)
          return -1;
        if (index > o.index)  
          return 1;
        else 
          throw new IllegalStateException(
              this + " and " + o + "shouldn't have the same index");
      }
    }
  }

  public int[] sort(int[] input) {
    Map<Integer, Element> freqTable = new HashMap<Integer, Element>();
    for (int i = 0; i < input.length; i++)
      if (freqTable.containsKey(input[i])) {
        Element e = freqTable.get(input[i]);
        freqTable.put(input[i], 
            new Element(e.index, e.value, e.frequency + 1));
      } else
        freqTable.put(input[i],
            new Element(i, input[i], 1));

    List<Element> elements = new ArrayList<Element>(freqTable.values());
    Collections.sort(elements);

    int [] result = new int[elements.size()];
    for (int i = 0; i < elements.size(); i++)
      result[i] = elements.get(i).value;

    return result;
  }

  public static void main(String[] _) {
    System.out.println(Arrays.toString(new FrequencySort().sort(new int[]{2,2,3,2,1,4,4,3,2,6,7,7})));
  }
}


