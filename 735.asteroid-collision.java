import java.util.LinkedList;

/*
 * @lc app=leetcode id=735 lang=java
 *
 * [735] Asteroid Collision
 *
 * https://leetcode.com/problems/asteroid-collision/description/
 *
 * algorithms
 * Medium (44.31%)
 * Likes:    3364
 * Dislikes: 264
 * Total Accepted:    190.3K
 * Total Submissions: 428.7K
 * Testcase Example:  '[5,10,-5]'
 *
 * We are given an array asteroids of integers representing asteroids in a
 * row.
 * 
 * For each asteroid, the absolute value represents its size, and the sign
 * represents its direction (positive meaning right, negative meaning left).
 * Each asteroid moves at the same speed.
 * 
 * Find out the state of the asteroids after all collisions. If two asteroids
 * meet, the smaller one will explode. If both are the same size, both will
 * explode. Two asteroids moving in the same direction will never meet.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: asteroids = [5,10,-5]
 * Output: [5,10]
 * Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never
 * collide.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: asteroids = [8,-8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: asteroids = [10,2,-5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide
 * resulting in 10.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= asteroids.length <= 10^4
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(n^2)
    // Space: O(n)
    // LinkedList
    // https://leetcode.com/problems/asteroid-collision/discuss/109694/JavaC%2B%2B-Clean-Code
    // Question: so initially, all negative ones are on the right and all positive ones are on the left?
    public int[] asteroidCollision(int[] asteroids) {
        // Corner Cases
        if (asteroids == null || asteroids.length == 0) {
            return new int[] {};
        }

        LinkedList<Integer> list = new LinkedList<>();

        for (int asteroid : asteroids) {
            // if asteroid is positive, add it to the list waiting to be compared
            if (asteroid > 0) {
                list.add(asteroid);
            } else {
                // if asteriod is negative
                // Continously compare it with very last positive asteriod
                // if the postive one is smaller, then remove it
                while (!list.isEmpty() && list.getLast() > 0 && list.getLast() < -asteroid) {
                    list.pollLast();
                }
                // edge case: if the positive and negative ones have same size
                if (!list.isEmpty() && list.getLast() == -asteroid) {
                    list.pollLast();
                }
                // if not, add it to the list which has only negative ones left or it is empty
                else if (list.isEmpty() || list.getLast() < 0) {
                    list.add(asteroid);
                }
            }
        }

        return list.stream().mapToInt(i -> i).toArray();
    }
}
// @lc code=end

