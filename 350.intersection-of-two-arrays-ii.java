import java.util.Arrays;

/*
 * @lc app=leetcode id=350 lang=java
 *
 * [350] Intersection of Two Arrays II
 *
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/description/
 *
 * algorithms
 * Easy (51.64%)
 * Likes:    1794
 * Dislikes: 473
 * Total Accepted:    428.7K
 * Total Submissions: 827.5K
 * Testcase Example:  '[1,2,2,1]\n[2,2]'
 *
 * Given two arrays, write a function to compute their intersection.
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * 
 * 
 * Note:
 * 
 * 
 * Each element in the result should appear as many times as it shows in both
 * arrays.
 * The result can be in any order.
 * 
 * 
 * Follow up:
 * 
 * 
 * What if the given array is already sorted? How would you optimize your
 * algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is
 * better?
 * What if elements of nums2 are stored on disk, and the memory is limited such
 * that you cannot load all elements into the memory at once?
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(nlogn+nlogn+n+n) = O(nlogn)
    // Space: O(n)
    // Basic two arrays' traverse
    public int[] intersect(int[] nums1, int[] nums2) {
        // Corner Cases
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[] {};
        }

        // Time: O(nlogn)
        // Space: O(n)
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] res = new int[nums1.length];

        int indexForRes = 0;
        for (int i = 0, j = 0; i < nums1.length;) { // the ++ of both i & j should be handled inside the loop after each judgement
            if (nums1[i] < nums2[j]) { // left < right
                i++;
            } else if (nums1[i] == nums2[j]) { // left == right
                res[indexForRes] = nums1[i];
                indexForRes++;
                i++;
                if (j < nums2.length - 1) {
                    j++;
                } else break;
            } else { // left > right
                if (j < nums2.length - 1) {
                    j++;
                } else break;
            }
        }

        return Arrays.copyOfRange(res, 0, indexForRes);
    }
}
// @lc code=end

