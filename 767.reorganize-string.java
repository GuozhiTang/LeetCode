import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

/*
 * @lc app=leetcode id=767 lang=java
 *
 * [767] Reorganize String
 *
 * https://leetcode.com/problems/reorganize-string/description/
 *
 * algorithms
 * Medium (46.21%)
 * Likes:    1106
 * Dislikes: 57
 * Total Accepted:    59.9K
 * Total Submissions: 127.7K
 * Testcase Example:  '"aab"'
 *
 * Given a string S, check if the letters can be rearranged so that two
 * characters that are adjacent to each other are not the same.
 * 
 * If possible, output any possible result.  If not possible, return the empty
 * string.
 * 
 * Example 1:
 * 
 * 
 * Input: S = "aab"
 * Output: "aba"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: S = "aaab"
 * Output: ""
 * 
 * 
 * Note:
 * 
 * 
 * S will consist of lowercase letters and have length in range [1, 500].
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(n)
    // Space: O(n)
    // Array + Char
    // https://leetcode.com/problems/reorganize-string/discuss/232469/Java-No-Sort-O(N)-0ms-beat-100
    public String reorganizeString(String S) {
        // Corner Cases
        if (S == null || S.length() == 0) {
            return "";
        }
        
        // calculate the occrance of each character
        int[] letterOccurance = new int[26];
        for (char letter : S.toCharArray()) {
            letterOccurance[letter - 'a']++;
        }

        int maxOccurance = 0, maxLetterIndex = 0;
        char maxLetter = ' ';
        // get the max occurance and index
        for (int index = 0; index < letterOccurance.length; index++) {
            System.out.println("Index " + index + " - letter " + String.valueOf((char)(index + 'a')) + " occurred " + letterOccurance[index] + " times.");
            if (letterOccurance[index] > maxOccurance) {
                maxOccurance = letterOccurance[index];
                maxLetter = (char) (index + 'a');
                maxLetterIndex = index;
            }
        }

        System.out.println("maxOccurance: " + maxOccurance);
        System.out.println("maxLetter: " + maxLetter);

        // check if the max occurance times is over (length+1)/2
        if (maxOccurance > (S.length() + 1) / 2) {
            return "";
        }

        // put the max occurred letter in even index
        char[] result = new char[S.length()];
        int inputIndex = 0;
        for (int index = 0; index < maxOccurance; index++) {
            result[inputIndex] = maxLetter;
            inputIndex += 2;
            letterOccurance[maxLetterIndex]--;
        }

        // put the rest of letters into the result array following the same logic
        // No need to check by occurance times here, so just to traverse the occurance array
        for (int index = 0; index < letterOccurance.length; index++) {
            for (int num = 0; num < letterOccurance[index]; num++) {
                if (inputIndex >= S.length()) {
                    inputIndex = 1;
                }
                result[inputIndex] = (char) (index + 'a');
                inputIndex += 2;
            }
        }

        return String.valueOf(result);
    }

    // =================================================================

    // // Time: O(nlogn)
    // // Space: O(n)
    // // Greedy + PriorityQueue
    // public String reorganizeString(String S) {
    //     // Corner Cases
    //     if (S.length() == 0 || S == null) {
    //         return "";
    //     }

    //     // use HashMap to store the char => count
    //     Map<Character, Integer> map = new HashMap<>();
    //     for (char ch : S.toCharArray()) {
    //         int count = map.getOrDefault(ch, 0) + 1;
    //         // Corner Case
    //         if (count > (S.length() + 1) / 2) {
    //             return "";
    //         }
    //         map.put(ch, count);
    //     }

    //     // use PriorityQueue
    //     PriorityQueue<Map.Entry<Character, Integer>> pqueue = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
    //         @Override
    //         public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
    //             return o2.getValue() - o1.getValue();
    //         }
    //     });

    //     for (Map.Entry<Character, Integer> entry : map.entrySet()) {
    //         pqueue.offer(entry);
    //     }

    //     StringBuilder sb = new StringBuilder();

    //     // Greedy
    //     while (!pqueue.isEmpty()) {
    //         // store character with highest frequency in cache
    //         Map.Entry<Character, Integer> entry1 = pqueue.poll();

    //         // if character in cache is different with tail character in current string
    //         if (sb.length() == 0 || entry1.getKey() != sb.charAt(sb.length() - 1)) {
    //             sb.append(entry1.getKey());
    //             entry1.setValue(entry1.getValue() - 1);
    //             if (entry1.getValue() != 0) {
    //                 pqueue.offer(entry1);
    //             }
    //         } else {
    //             // if character in cache is same as tail character in current string
    //             // we need to try the character with second highest frequency
    //             Map.Entry<Character, Integer> entry2 = pqueue.poll();
    //             // corner case: if no more elements in queue, the input string should be invalid
    //             // because we do not have any other characters that different with current string tail
    //             if (entry2 == null) {
    //                 return "";
    //             }
    //             sb.append(entry2.getKey());
    //             entry2.setValue(entry2.getValue() - 1);
    //             if (entry2.getValue() != 0) {
    //                 pqueue.offer(entry2);
    //             }

    //             pqueue.offer(entry1);
    //         }
    //     }

    //     return sb.toString();
    // }
}
// @lc code=end

