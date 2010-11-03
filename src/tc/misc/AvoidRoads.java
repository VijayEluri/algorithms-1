package tc.misc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AvoidRoads {
	long[][] s;
	Map<String, String> badPaths = new HashMap<String, String>();
	
	public static void main(String[] _) {
		System.out.println(new AvoidRoads().numWays(1, 1, new String[]{}));
		System.out.println(new AvoidRoads().numWays(6, 6, new String[]{"0010", "6656"})); //wrong output :( :(
		System.out.println(new AvoidRoads().numWays(2, 2, new String[]{"1222", "1121"}));
		System.out.println(new AvoidRoads().numWays(9, 100, new String[]{"0203", "1213", "2223", "3233", "4243", "5253", "6263", "7273", "8283", "9293"})); 	
		System.out.println(new AvoidRoads().numWays(95, 16, new String[]{}));
	}

	public long numWays(int width, int height, String[] bad) {
		s = new long[height + 1][width + 1];
		s[0][0] = 0;
		
		for(int i = 0; i < height + 1; i++)
			Arrays.fill(s[i], -1);

		for(int i = 0; i < bad.length; i++) {
			badPaths.put(bad[i].substring(0, 2), bad[i].substring(2));
			badPaths.put(bad[i].substring(2), bad[i].substring(0, 2));
		}
		
		return solve(height, width, height + width);
	}
	
	private long solve(int i, int j, int blocks) {
		if (i < 0 || j < 0)
			return 0;
		
		if(i == 0 && j == 0)
			return 1;
		
		if (s[i][j] >= 0)
			return s[i][j];
		
		long left = 0;
		if (!blocked(i, j, i, j - 1))
			left  =  solve(i, j - 1, blocks - 1);
		long down = 0; 
		if (!blocked(i, j, i - 1, j))
			down = solve(i - 1, j, blocks - 1);
		
		return s[i][j] = left + down;
	}
	
	private boolean blocked(int i1, int j1, int i2, int j2) {
		String p = j1 + "" + i1;        // since m[i][j] == p(j, i); --> j = x-coord, i = y-coord
		String q = j2 + "" + i2;
		
		String u = badPaths.get(p);
		String v = badPaths.get(q);
		
		if (q.equals(u) || p.equals(v))
			return true;
		return false;
	}
}