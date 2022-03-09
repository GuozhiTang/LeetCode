import java.util.ArrayList;

/*
 * @lc app=leetcode id=210 lang=java
 *
 * [210] Course Schedule II
 *
 * https://leetcode.com/problems/course-schedule-ii/description/
 *
 * algorithms
 * Medium (45.79%)
 * Likes:    6250
 * Dislikes: 228
 * Total Accepted:    583.4K
 * Total Submissions: 1.3M
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
 * Return the ordering of courses you should take to finish all courses. If
 * there are many valid answers, return any of them. If it is impossible to
 * finish all courses, return an empty array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you
 * should have finished course 0. So the correct course order is [0,1].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you
 * should have finished both courses 1 and 2. Both courses 1 and 2 should be
 * taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is
 * [0,2,1,3].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(E+V)
    // Space: O(E+V)
    // Topological Sorting + Graph (DAG - Directed Acyclic Graph)
    // Based on 207. Course Schedule
    // https://leetcode.com/problems/course-schedule/discuss/162743/JavaC%2B%2BPython-BFS-Topological-Sorting-O(N-%2B-E)
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Corner Cases
        if (numCourses == 0) {
            return new int[] {};
        }
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {
            int[] noPreReq = new int[numCourses];
            int courseIndex = 0;
            for (int index = 0; index < numCourses; index++) {
                noPreReq[index] = courseIndex;
                courseIndex++;
            }
            return noPreReq;
        }

        // Graph[x] means x is the prerequisites of the following list int[]
        ArrayList<Integer>[] Graph = new ArrayList[numCourses];
        // degree is to check whether y has prereq (whether there is `->` pointing to y)
        int[] degree = new int[numCourses];
        // bfs is to check the non in-degree node (no `->` pointing it)
        ArrayList<Integer> bfs = new ArrayList<>();

        // initialize the list in the query
        for (int index = 0; index < numCourses; index++) {
            Graph[index] = new ArrayList<Integer>();
        }

        // if x -> y, then x is the prerequisites
        for (int[] reqPair : prerequisites) {
            Graph[reqPair[1]].add(reqPair[0]);
            degree[reqPair[0]]++;
        }

        // firstly find out all the non in-degree nodes
        for (int index = 0; index < numCourses; index++) {
            if (degree[index] == 0) {
                bfs.add(index);
            }
        }

        // Then traverse the bfs list
        for (int bfsIndex = 0; bfsIndex < bfs.size(); bfsIndex++) {
            // traverse the corresponding list of each non in-degree node
            for (int node : Graph[bfs.get(bfsIndex)]) {
                if (--degree[node] == 0) {
                    bfs.add(node);
                }
            }
        }

        // check whether all classes can be finished
        if (bfs.size() != numCourses) {
            return new int[] {};
        }

        // if yes, return that bfs in int[] type
        int[] result = new int[bfs.size()];
        int indexRes = 0;
        for (int course : bfs) {
            result[indexRes] = course;
            indexRes++;
        }

        return result;
    }
}
// @lc code=end

