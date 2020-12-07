import java.util.Arrays;
import java.util.HashSet;

/*
 * @lc app=leetcode id=349 lang=java
 *
 * [349] Intersection of Two Arrays
 *
 * https://leetcode.com/problems/intersection-of-two-arrays/description/
 *
 * algorithms
 * Easy (63.27%)
 * Likes:    1108
 * Dislikes: 1379
 * Total Accepted:    421.3K
 * Total Submissions: 658.6K
 * Testcase Example:  '[1,2,2,1]\n[2,2]'
 *
 * Given two arrays, write a function to compute their intersection.
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 * 
 * 
 * Note:
 * 
 * 
 * Each element in the result must be unique.
 * The result can be in any order.
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(nlogn+n*logn+n) = O(n(2logn+1)) = O(nlogn)
    // Space: O(n)
    // Binary Search + HashSet
    public int[] intersection(int[] nums1, int[] nums2) {
       // Corner Cases
       if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
           return new int[] {};
       }

       // specify a HashSet to store and filter the duplicated results
       // Space: O(n)
       HashSet<Integer> set = new HashSet<>();

       int len1 = nums1.length;
       // Time: O(nlogn)
       // Space: O(n)
       Arrays.sort(nums2);
       // Time: O(n)
       for (int index1 = 0; index1 < len1; index1++) {
           // Space: O(logn)
           // Space: O(1)
           if (binarySearch(nums2, nums1[index1])) {
               set.add(nums1[index1]);
           }
       }

       // transfer the Hashset to array
       int[] result = new int[set.size()];

       int index = 0;
       // Time: O(n)
       for (int res : set) {
           result[index++] = res;
       }

       return result;
    }

    public boolean binarySearch(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (nums[start] == target || nums[end] == target) {
            return true;
        } else {
            return false;
        }
    }
}
// @lc code=end

