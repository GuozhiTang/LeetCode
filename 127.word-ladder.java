import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * @lc app=leetcode id=127 lang=java
 *
 * [127] Word Ladder
 *
 * https://leetcode.com/problems/word-ladder/description/
 *
 * algorithms
 * Medium (27.43%)
 * Likes:    2551
 * Dislikes: 1026
 * Total Accepted:    373.3K
 * Total Submissions: 1.3M
 * Testcase Example:  '"hit"\n"cog"\n["hot","dot","dog","lot","log","cog"]'
 *
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * the length of shortest transformation sequence from beginWord to endWord,
 * such that:
 * 
 * 
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is
 * not a transformed word.
 * 
 * 
 * Note:
 * 
 * 
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * Output: 5
 * 
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" ->
 * "dog" -> "cog",
 * return its length 5.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 
 * Output: 0
 * 
 * Explanation: The endWord "cog" is not in wordList, therefore no possible
 * transformation.
 * 
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        ladderLength_Qeueue(beginWord, endWord, wordList);
        return ladderLength_Set(beginWord, endWord, wordList);
    }

    private int ladderLength_Set(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordListSet = new HashSet<>(wordList);
        // Corner Cases
        // O(1)
        if (wordListSet.size() == 0 || wordListSet == null || !wordListSet.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        // remove the beginWord
        wordListSet.remove(beginWord);
        int count = 1;

        // BFS starts from the beginWord
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            // traverse the each layer to find neighbors
            for (int index = 0; index < queueSize; index++) {
                String curWord = queue.poll();
                if (curWord.equals(endWord)) {
                    return count;
                }
                // find all the neighbors of curWord
                // O(mn)
                List<String> neighbors = findNeighbors_Set(curWord, wordListSet);
                // O(n)
                for (String neighbor : neighbors) {
                    queue.offer(neighbor);
                }
            }
            count++;
        }
        return 0;
    }

    // find all the neighbors of curWord
    private List<String> findNeighbors_Set(String curWord, Set<String> wordList) {
        List<String> neighbors = new ArrayList<>();
        // replace the character in the word with all the letter to judge whether it is in wordList
        for (int index = 0; index < curWord.length(); index++) {
            // each time we need to reset the char array to the curWord
            char[] curWord_array = curWord.toCharArray();
            for (char char_replace = 'a'; char_replace <= 'z'; char_replace++) {
                curWord_array[index] = char_replace;
                String newWord = new String(curWord_array);
                // judge whether the new word is in wordlist
                if (wordList.contains(newWord)) {
                    wordList.remove(newWord);
                    neighbors.add(newWord);
                }
            }
        }
        return neighbors;
    }

    // Time: O(m*n^2)
    // Space: O(m*n)
    // BFS + Queue
    private int ladderLength_Qeueue(String beginWord, String endWord, List<String> wordList) { 
        // Corner Cases
        // O(n)
        if (wordList.size() == 0 || wordList == null || !wordList.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        // remove the beginWord
        wordList.remove(beginWord);
        int count = 1;

        // BFS starts from the beginWord
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            // traverse the each layer to find neighbors
            for (int index = 0; index < queueSize; index++) {
                String curWord = queue.poll();
                if (curWord.equals(endWord)) {
                    return count;
                }
                // find all the neighbors of curWord
                // O(mn)
                List<String> neighbors = findNeighbors(curWord, wordList);
                // O(n)
                for (String neighbor : neighbors) {
                    queue.offer(neighbor);
                }
            }
            count++;
        }
        return 0;
    }

    // O(m*26*n) = O(m*n) m: wordLength, n: listSize
    // find all the neighbors of curWord
    private List<String> findNeighbors(String curWord, List<String> wordList) {
        List<String> neighbors = new ArrayList<>();
        // replace the character in the word with all the letter to judge whether it is in wordList
        for (int index = 0; index < curWord.length(); index++) {
            // each time we need to reset the char array to the curWord
            char[] curWord_array = curWord.toCharArray();
            for (char char_replace = 'a'; char_replace <= 'z'; char_replace++) {
                curWord_array[index] = char_replace;
                String newWord = new String(curWord_array);
                // judge whether the new word is in wordlist
                if (wordList.contains(newWord)) {
                    wordList.remove(newWord);
                    neighbors.add(newWord);
                }
            }
        }
        return neighbors;
    }

}
// @lc code=end

