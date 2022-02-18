import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

// https://leetcode.com/problems/analyze-user-website-visit-pattern/
class Pair {
  int time;
  String web;
  public Pair(int time, String web) {
      this.time = time;
      this.web = web;
  }
}

class Solution {
  
  // Time: O(n^3)
  // Space: O(n)
  // Brute Force according to the steps in explanation
  // https://leetcode.com/problems/analyze-user-website-visit-pattern/discuss/355606/Java-Very-Easy-Understand-With-Explanation
  public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
      // Corner Cases
      if (username == null || username.length == 0) {
          return new ArrayList<String>();
      }
      
      // username -> (time, web)
      Map<String, List<Pair>> map = new HashMap<>();
      int length = username.length;
      
      // collect the website info for every user
      for (int index = 0; index < length; index++) {
          map.putIfAbsent(username[index], new ArrayList<Pair>());
          map.get(username[index]).add(new Pair(timestamp[index], website[index]));
      }
      
      // Count map to record every 3 combination occuring time for different user
      // username -> Count for the pattern
      Map<String, Integer> count = new HashMap<>();
      String result = "";
      
      for (String key : map.keySet()) { // key is the username
          // this set is to avoid visiting the same 3-seq in one user
          Set<String> set = new HashSet<>();
          
          List<Pair> list = map.get(key);
          // Sort by time
          Collections.sort(list, (a, b) -> (a.time - b.time));
          
          // Brute Force solution
          for (int i = 0; i < list.size(); i++) {
              for (int j = i + 1; j < list.size(); j++) {
                  for (int k = j + 1; k < list.size(); k++) {
                      String webSeq = list.get(i).web + " " + list.get(j).web + " " + list.get(k).web;
                      
                      if (!set.contains(webSeq)) {
                          count.put(webSeq, count.getOrDefault(webSeq, 0) + 1);
                          set.add(webSeq);
                      }
                      
                      if (result.equals("") || count.get(result) < count.get(webSeq) || (count.get(result) == count.get(webSeq) && result.compareTo(webSeq) > 0)) {
                          // make sure the right lexi order
                          result = webSeq;
                      }
                  }
              }
          }
      }
      // grab the right answer
      String[] resArray = result.split(" ");
      List<String> finalResult = new ArrayList<>();
      for (String res : resArray) {
          finalResult.add(res);
      }
      return finalResult;
  }
}