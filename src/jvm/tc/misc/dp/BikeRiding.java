package tc.misc.dp;

// SRM 345 Div 2 Level 3

public class BikeRiding {
  Integer cache[];
  boolean cycle[];
  boolean visiting[];

  int n;
  int endPoint;
  String[] paths;

  public int countRoutes(String[] paths, int[] startPoints, int endPoint, int n) {
    int cnt = 0;
    cache = new Integer[paths.length];
    cycle = new boolean[paths.length];
    visiting = new boolean[paths.length];

    this.n = n;
    this.paths = paths;
    this.endPoint = endPoint;

    for (int startPoint : startPoints) {
      int _cnt = countFromPoint(startPoint);
      if (_cnt == -1 || _cnt > n || _cnt + cnt > n)
        return -1;
      cnt += _cnt;
    }

    if (cache[endPoint] != null && cache[endPoint] > 0 && hasCycle(endPoint))
      return -1;

    for (int point = 0; point < paths.length; point++)
      if (cache[point] != null &&  // reachable from one of the startPoints
          (cycle[point] == true) && 
          hasRouteToEndPoint(point))
        return -1;

    return cnt;
  }


  boolean hasCycle(int startPoint) {
    visiting[startPoint] = true;

    for (int neighbor = 0; neighbor < paths.length; neighbor++)
      if (paths[startPoint].charAt(neighbor) == '1')
        if (visiting[neighbor]) {
          if (neighbor == endPoint)
            return true;
        } else {
          if (hasCycle(neighbor))
            return true;
        }

    return false;
  }


  boolean hasRouteToEndPoint(int point) {
    for (int neighbor = 0; neighbor < paths.length; neighbor++)
      if (paths[point].charAt(neighbor) == '1' && 
          cache[neighbor] != null && 
          cache[neighbor] > 0)
        return true;
    return false;
  }


  private int countFromPoint(int startPoint) {
    if (startPoint == endPoint)
      return cache[startPoint] = 1;

    if (cache[startPoint] != null)
      return cache[startPoint];

    int cnt = 0;
    visiting[startPoint] = true;
    for (int neighbor = 0; neighbor < paths.length; neighbor++) {
      if (paths[startPoint].charAt(neighbor) == '1') {
        if (visiting[neighbor])
          cycle[startPoint] = true;
        else {
          int _cnt = countFromPoint(neighbor);
          if (_cnt == -1 || _cnt + cnt > n)
            return -1;
          else
            cnt += _cnt;
        }
      }
    }
    visiting[startPoint] = false;
    return cache[startPoint] = cnt;
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
