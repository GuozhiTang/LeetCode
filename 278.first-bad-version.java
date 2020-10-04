/*
 * @lc app=leetcode id=278 lang=java
 *
 * [278] First Bad Version
 *
 * https://leetcode.com/problems/first-bad-version/description/
 *
 * algorithms
 * Easy (32.86%)
 * Likes:    969
 * Dislikes: 552
 * Total Accepted:    297.8K
 * Total Submissions: 902.8K
 * Testcase Example:  '5\n4'
 *
 * You are a product manager and currently leading a team to develop a new
 * product. Unfortunately, the latest version of your product fails the quality
 * check. Since each version is developed based on the previous version, all
 * the versions after a bad version are also bad.
 * 
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the
 * first bad one, which causes all the following ones to be bad.
 * 
 * You are given an API bool isBadVersion(version) which will return whether
 * version is bad. Implement a function to find the first bad version. You
 * should minimize the number of calls to the API.
 * 
 * Example:
 * 
 * 
 * Given n = 5, and version = 4 is the first bad version.
 * 
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * 
 * Then 4 is the first bad version. 
 * 
 * 
 */

// @lc code=start
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

// Time: O(logn)
// Space: O(1)
// Binary Search
class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int begin = 1, end = n;
        while (begin + 1 < end) {
            int mid = begin + (end - begin) / 2;
            if (isBadVersion(mid)) end = mid;
            else begin = mid;
        }

        if (isBadVersion(begin)) return begin;
        else return end;
    }
}
// @lc code=end

