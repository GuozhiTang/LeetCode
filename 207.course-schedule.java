import java.util.ArrayList;

/*
 * @lc app=leetcode id=207 lang=java
 *
 * [207] Course Schedule
 *
 * https://leetcode.com/problems/course-schedule/description/
 *
 * algorithms
 * Medium (44.84%)
 * Likes:    8542
 * Dislikes: 334
 * Total Accepted:    798.5K
 * Total Submissions: 1.8M
 * Testcase Example:  '2\n[[1,0]]'
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to
 * numCourses - 1. You are given an array prerequisites where prerequisites[i]
 * = [ai, bi] indicates that you must take course bi first if you want to take
 * course ai.
 * 
 * 
 * For example, the pair [0, 1], indicates that to take course 0 you have to
 * first take course 1.
 * 
 * 
 * Return true if you can finish all courses. Otherwise, return false.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take. 
 * To take course 1 you should have finished course 0. So it is possible.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take. 
 * To take course 1 you should have finished course 0, and to take course 0 you
 * should also have finished course 1. So it is impossible.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= numCourses <= 10^5
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(E+V)
    // Space: O(E+V)
    // Topological Sorting + Graph (DAG - Directed Acyclic Graph)
    // https://leetcode.com/problems/course-schedule/discuss/162743/JavaC%2B%2BPython-BFS-Topological-Sorting-O(N-%2B-E)
    // https://www.jianshu.com/p/b59db381561a
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Corner Cases
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {
            return true;
        }

        // Graph[x] means x is the prerequisites of the following list int[]
        ArrayList<Integer>[] Graph = new ArrayList[numCourses];
        // degree is to check whether y has prereq (whether there is '->' pointing to y)
        int[] degree = new int[numCourses];
        // bfs is to check the non in-degree node (no `->` pointing it)
        ArrayList<Integer> bfs = new ArrayList<>();

        // initilaize the list in the array
        for (int index = 0; index < numCourses; index++) {
            Graph[index] = new ArrayList<Integer>();
        }

        // if x -> y, x is the prereq/
        // then degree[y]++
        for (int[] reqPair : prerequisites) {
            Graph[reqPair[1]].add(reqPair[0]);
            degree[reqPair[0]]++;
        }

        // firstly find out all the non in-degree node
        for (int index = 0; index < numCourses; index++) {
            if (degree[index] == 0) {
                bfs.add(index);
            }
        }

        // Then traverse the bfs list
        for (int bfsIndex = 0; bfsIndex < bfs.size(); bfsIndex++) {
            // traverse the corresponding list of each non in-degree nodes
            for (int node : Graph[bfs.get(bfsIndex)]) {
                // break the vectors, then check if it is the new non in-degree node
                if (--degree[node] == 0) {
                    bfs.add(node);
                }
            }
        }

        // If it is a DAG, then the final list should contain all the values.
        return bfs.size() == numCourses;
    }
}
// @lc code=end

