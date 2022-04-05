import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

class Solution {
    
  // Time: O(n)
  // Space: O(n)
  // HashMap + HashSet + Genera Logic
  // https://leetcode.com/problems/kill-process/discuss/103176/Java-Solution-HashMap
  public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
     // Corner Cases
      if (kill == 0) {
          return pid;
      }
      if (pid == null || pid.size() == 0 || ppid == null || ppid.size() == 0) {
          return new ArrayList<>();
      }
      
      // node - children
      Map<Integer, Set<Integer>> map = new HashMap<>();
      
      // initialize the map and add all nodes - regarding to pid
      for (int index = 0; index < pid.size(); index++) {
          map.put(pid.get(index), new HashSet<>());
      }
      
      // add the children to each node - regarding to ppid
      for (int index = 0; index < pid.size(); index++) {
          // if there is a parent node in original list, or if would be the root
          if (map.containsKey(ppid.get(index))) {
              Set<Integer> children = map.get(ppid.get(index));
              children.add(pid.get(index));
              map.put(ppid.get(index), children);
          }
      }
      
      List<Integer> results = new ArrayList<>();
      
      traverse(map, results, kill);
      
      return results;
  }
  
  private void traverse(Map<Integer, Set<Integer>> map, List<Integer> results, int kill) {
      
      results.add(kill);
      
      Set<Integer> children = map.get(kill);
      
      for (int child : children) {
          traverse(map, results, child);
      }
  }
}