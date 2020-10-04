/*
 * @lc app=leetcode id=866 lang=java
 *
 * [866] Prime Palindrome
 *
 * https://leetcode.com/problems/prime-palindrome/description/
 *
 * algorithms
 * Medium (23.69%)
 * Likes:    164
 * Dislikes: 465
 * Total Accepted:    16.3K
 * Total Submissions: 67.3K
 * Testcase Example:  '6'
 *
 * Find the smallest prime palindrome greater than or equal to N.
 * 
 * Recall that a number is prime if it's only divisors are 1 and itself, and it
 * is greater than 1. 
 * 
 * For example, 2,3,5,7,11 and 13 are primes.
 * 
 * Recall that a number is a palindrome if it reads the same from left to right
 * as it does from right to left. 
 * 
 * For example, 12321 is a palindrome.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: 6
 * Output: 7
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 8
 * Output: 11
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: 13
 * Output: 101
 * 
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= N <= 10^8
 * The answer is guaranteed to exist and be less than 2 * 10^8.
 * 
 * 
 */

// @lc code=start
class Solution {
    
    // Time: O()
    // Space: O(1)
    // Palindrone + Prime
    public int primePalindrome(int N) {
        // Corner Cases
        // In the following part, we ignore the even-digit number, but 11 is special
        if (N >= 8 && N <= 11) {
            return 11;
        }

        // O(100000)
        // All the even digits number can not be prime palindrome, all can be divided by 11
        // Maximum N = 10 ^ 8, it is 9 digits, so the maximum results could be in 11 digits
        // Try to create a palindrome here
        for (int left_int = 1; left_int < 100000; left_int++) {
            String left_string = Integer.toString(left_int);
            String right_string = new StringBuilder(left_string).reverse().toString();
            int number = Integer.parseInt("" + left_string + right_string.substring(1));

            if (number >= N && isPrime(number)) {
                return number;
            }
        }
        return -1;
    }

    // Time: O(sqrt(number)) = O(sqrt(N))
    private boolean isPrime(int number) {
        // Corner Cases
        if (number <= 2 || number % 2 == 0) {
            return number == 2;
        }

        // if number is not a prime, then there must exist a number 'num' (num * num <= number) as the factor of number
        // num += 2 is to keep odd numbers
        for (int num = 3; num * num <= number; num += 2) {
            if (number % num == 0) {
                return false;
            }
        }
        return true;
    }
}
// @lc code=end

