import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/*
 * @lc app=leetcode id=1268 lang=java
 *
 * [1268] Search Suggestions System
 *
 * https://leetcode.com/problems/search-suggestions-system/description/
 *
 * algorithms
 * Medium (58.84%)
 * Likes:    236
 * Dislikes: 67
 * Total Accepted:    20.4K
 * Total Submissions: 33.8K
 * Testcase Example:  '["mobile","mouse","moneypot","monitor","mousepad"]\r\n"mouse"\r'
 *
 * Given an array of strings products and a string searchWord. We want to
 * design a system that suggests at most three product names from products
 * after each character of searchWord is typed. Suggested products should have
 * common prefix with the searchWord. If there are more than three products
 * with a common prefix return the three lexicographically minimums products.
 * 
 * Return list of lists of the suggested products after each character of
 * searchWord is typed. 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: products = ["mobile","mouse","moneypot","monitor","mousepad"],
 * searchWord = "mouse"
 * Output: [
 * ["mobile","moneypot","monitor"],
 * ["mobile","moneypot","monitor"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"],
 * ["mouse","mousepad"]
 * ]
 * Explanation: products sorted lexicographically =
 * ["mobile","moneypot","monitor","mouse","mousepad"]
 * After typing m and mo all products match and we show user
 * ["mobile","moneypot","monitor"]
 * After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: products = ["havana"], searchWord = "havana"
 * Output:
 * [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: products = ["bags","baggage","banner","box","cloths"], searchWord =
 * "bags"
 * Output:
 * [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: products = ["havana"], searchWord = "tatiana"
 * Output: [[],[],[],[],[],[],[]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= products.length <= 1000
 * There are no repeated elements in products.
 * 1 <= Σ products[i].length <= 2 * 10^4
 * All characters of products[i] are lower-case English letters.
 * 1 <= searchWord.length <= 1000
 * All characters of searchWord are lower-case English letters.
 * 
 * 
 */

// @lc code=start
class Solution {

    // Time: O(n);
    // Space: O(n);
    // public List<List<String>> suggestedProducts(String[] products, String searchWord) {
    //     List<List<String>> res = new ArrayList<>();
    //     TreeMap<String, Integer> map = new TreeMap<>();
    //     Arrays.sort(products);
    //     List<String> productsList = Arrays.asList(products);

    //     for (int i = 0; i < products.length; i++) {
    //         map.put(products[i], i);
    //     }

    //     String key = "";
    //     for (char character : searchWord.toCharArray()) {
    //         key += character;
    //         String ceiling = map.ceilingKey(key);
    //         String floor = map.floorKey(key + "~");
    //         if (ceiling == null || floor == null) {
    //             break;
    //         }
    //         res.add(productsList.subList(map.get(ceiling), Math.min(map.get(ceiling) + 3, map.get(floor) + 1)));
    //     }

    //     while (res.size() < searchWord.length()) {
    //         res.add(new ArrayList<>());
    //     }
    //     return res;
    // }

    // Trie Node
    class Node {
        Node[] children = new Node[26]; // the character after this node
        String fullString; // if this node is the end, then store this string
    }

    // Time: O()
    // Space: O()
    // Trie
    public List<List<String>> suggestedProducts (String[] products, String searchWord) {
        Node root = new Node();

        // traverse each word to create the trie
        for (String word : products) {
            Node curRoot = root;
            // traverse each character of the word to store into the trie
            for (int letter = 0; letter < word.length(); letter++) {
                // index of current character
                int index = word.charAt(letter) - 'a';
                // if current character is not in current node's subnode
                if (curRoot.children[index] == null) {
                    // add current character to the subnode
                    curRoot.children[index] = new Node();
                }
                // update the current root node
                curRoot = curRoot.children[index];
            }
            // after inserting all the characters, set current node as the word's ending
            curRoot.fullString = word;
        }

        List<List<String>> res = new ArrayList<>();

        // traverse each character of the searchWord
        for (int letter = 0; letter < searchWord.length(); letter++) {
            // if root is empty, then there is no such word begining with the character
            if (root == null) {
                res.add(new ArrayList<>());
                continue;
            }
            // update the root according to searchWord
            root = root.children[searchWord.charAt(letter) - 'a'];
            // get all the words of this root
            res.add(getList(root, new ArrayList<>()));
        }
        return res;
    }

    public static List<String> getList(Node root, List<String> list) {
        // if root is empty or have found 3 products, return
        if (root == null || list.size() == 3) {
            return list;
        }
        // if current node is the ending
        if (root.fullString != null) {
            list.add(root.fullString);
        }
        
        for (int index = 0; index < 26; index++) {
            // if there exists characters, recursively get the whole word
            if (root.children[index] != null) {
                getList(root.children[index], list);
            }
        }
        return list;
    }
}
// @lc code=end

