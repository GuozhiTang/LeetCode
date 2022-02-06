import java.util.List;
import java.util.Map;

import javax.sound.sampled.SourceDataLine;

import java.util.HashMap;
import java.util.ArrayList;

/*
 * @lc app=leetcode id=981 lang=java
 *
 * [981] Time Based Key-Value Store
 *
 * https://leetcode.com/problems/time-based-key-value-store/description/
 *
 * algorithms
 * Medium (53.17%)
 * Likes:    873
 * Dislikes: 116
 * Total Accepted:    76.2K
 * Total Submissions: 143.2K
 * Testcase Example:  '["TimeMap","set","get","get","set","get","get"]\n[[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]'
 *
 * Create a timebased key-value store class TimeMap, that supports two
 * operations.
 * 
 * 1. set(string key, string value, int timestamp)
 * 
 * 
 * Stores the key and value, along with the given timestamp.
 * 
 * 
 * 2. get(string key, int timestamp)
 * 
 * 
 * Returns a value such that set(key, value, timestamp_prev) was called
 * previously, with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the one with the largest
 * timestamp_prev.
 * If there are no values, it returns the empty string ("").
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs =
 * [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
 * Output: [null,null,"bar","bar",null,"bar2","bar2"]
 * Explanation:   
 * TimeMap kv;   
 * kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with
 * timestamp = 1   
 * kv.get("foo", 1);  // output "bar"   
 * kv.get("foo", 3); // output "bar" since there is no value corresponding to
 * foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie
 * "bar"   
 * kv.set("foo", "bar2", 4);   
 * kv.get("foo", 4); // output "bar2"   
 * kv.get("foo", 5); //output "bar2"   
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: inputs = ["TimeMap","set","set","get","get","get","get","get"],
 * inputs =
 * [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
 * Output: [null,null,null,"","high","high","low","low"]
 * 
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * All key/value strings are lowercase.
 * All key/value strings have length in the range [1, 100]
 * The timestamps for all TimeMap.set operations are strictly increasing.
 * 1 <= timestamp <= 10^7
 * TimeMap.set and TimeMap.get functions will be called a total of 120000 times
 * (combined) per test case.
 * 
 * 
 */

// @lc code=start
class TimeMap {

    // Definition of Class Data
    class Data {
        String value;
        int timestamp;
        public Data(String value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    /** Initialize your data structure here. */
    // Use HashMap to get the key -> List(value, timestamp)
    Map<String, List<Data>> map;
    // Constructor
    public TimeMap() {
        map = new HashMap<String, List<Data>>();
    }
    
    public void set(String key, String value, int timestamp) {
        // Check whether key already exists
        if (!map.containsKey(key)) {
            List<Data> newDataSet = new ArrayList<>();
            newDataSet.add(new Data(value, timestamp));
            map.put(key, newDataSet);
            // map.put(key, new ArrayList<Data>());
        } else {
            // if key already exists
            map.get(key).add(new Data(value, timestamp));
        }
    }
    
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        } else {
            // return binarySearch(key, timestamp);
            return binarySearch(map.get(key), timestamp);
        }
    }

    // Space: O(logn)
    // Space: O(n)
    // Binary Search
    private String binarySearch(List<Data> pairList, int timestamp) {
        // pairLits<Data>: value <-> timestamp (ascending)
        // Corner Cases
        if (pairList == null || pairList.size() == 0) {
            return "";
        }

        int start = 0, end = pairList.size() - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (pairList.get(mid).timestamp == timestamp) {
                return pairList.get(mid).value;
            } else if (pairList.get(mid).timestamp < timestamp) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        // Judge the final "start" and "end" value.
        if (pairList.get(start).timestamp <= timestamp && timestamp < pairList.get(end).timestamp) {
            return pairList.get(start).value;
        } else if (pairList.get(end).timestamp <= timestamp) {
            return pairList.get(end).value;
        } else {
            return "";
        }
    }

    // // Time: O(n)
    // // Space: O(n)
    // private String binarySearch(String key, int timestamp) {
    //     List<Data> dataList = map.get(key);
    //     // Corner Cases
    //     if (dataList == null || dataList.size() == 0) {
    //         return "";
    //     }

    //     int[] timeStampArray = new int[dataList.size()];
    //     Map<Integer, String> pair = new HashMap<>();
    //     // O(n)
    //     for (int index = 0; index < dataList.size(); index++) {
    //         timeStampArray[index] = dataList.get(index).timestamp;
    //         System.out.println("timestamp added: Index: " + index + ", timestamp value: " + dataList.get(index).timestamp);
    //         pair.put(dataList.get(index).timestamp, dataList.get(index).value);
    //     }

    //     int start = 0, end = timeStampArray.length - 1;
    //     while (start + 1 < end) {
    //         int mid = start + (end - start) / 2;
    //         if (timeStampArray[mid] == timestamp) {
    //             System.out.println("Mid matched! minIndex: " + mid + ", timestamp: " + timeStampArray[mid] + ", value: " + pair.get(timeStampArray[mid]));
    //             return pair.get(timeStampArray[mid]);
    //         } else if (timeStampArray[mid] < timestamp) {
    //             start = mid;
    //         } else {
    //             end = mid;
    //         }
    //     }

    //     if (timeStampArray[start] <= timestamp && timestamp < timeStampArray[end]) {
    //         System.out.println("Start matched! minIndex: " + start + ", timestamp: " + timeStampArray[start] + ", value: " + pair.get(timeStampArray[start]));
    //         return pair.get(timeStampArray[start]);
    //     } else if (timeStampArray[end] <= timestamp) {
    //         System.out.println("Mid matched! minIndex: " + end + ", timestamp: " + timeStampArray[end] + ", value: " + pair.get(timeStampArray[end]));
    //         return pair.get(timeStampArray[end]);
    //     } else {
    //         return "";
    //     }
    // }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
// @lc code=end

