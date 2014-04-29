package misc;

import java.util.LinkedHashMap;
import java.util.Map;


public class LRUCache<K, V> {
  private final Map<K, V> map;
  private final int cacheSize;

  public LRUCache(int _cacheSize) {
    cacheSize = _cacheSize;
    map = new LinkedHashMap<K, V>() {
      @Override
      protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return false;
      }
    };
  }
}
