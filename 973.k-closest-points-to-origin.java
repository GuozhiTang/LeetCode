// import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=973 lang=java
 *
 * [973] K Closest Points to Origin
 *
 * https://leetcode.com/problems/k-closest-points-to-origin/description/
 *
 * algorithms
 * Medium (61.66%)
 * Likes:    1196
 * Dislikes: 99
 * Total Accepted:    196.6K
 * Total Submissions: 317.9K
 * Testcase Example:  '[[1,3],[-2,2]]\n1'
 *
 * We have a list of points on the plane.  Find the K closest points to the
 * origin (0, 0).
 * 
 * (Here, the distance between two points on a plane is the Euclidean
 * distance.)
 * 
 * You may return the answer in any order.  The answer is guaranteed to be
 * unique (except for the order that it is in.)
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation: 
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just
 * [[-2,2]].
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 * 
 * 
 * 
 */

// @lc code=start
class Solution {

    public int[][] kClosest(int[][] points, int K) {
        // return bruteForce(points, K);
        return maxHeap(points, K);
    }

    // Time: O(nlogK) - store K in the heap while iterate n
    // Space: O(n)
    // HashMap + PriorityQueue (maxHeap)
    // if using minHeap - store n in the heap and iterate to get K
    private int[][] maxHeap(int[][] points, int K) {
        // Corner Cases
        if (points == null || points.length == 0 || points[0].length == 0) {
            return new int[][] {};
        }

        // Use HashMap to store: (position -> distance)
        HashMap<int[], Integer> map = new HashMap<>();
        // O(n)
        for (int[] point : points) {
            int distance = point[0] * point[0] + point[1] * point[1];
            map.put(point, distance);
        }

        // Use PriorityQueue with maxHeap
        PriorityQueue<Map.Entry<int[], Integer>> pqueue = new PriorityQueue<>(new Comparator<Map.Entry<int[], Integer>>() {
            @Override
            public int compare(Map.Entry<int[], Integer> o1, Map.Entry<int[], Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        // store K in the pqueue and get the k closest/smallest
        for (Map.Entry<int[], Integer> entry : map.entrySet()) {
            if (pqueue.size() < K) {
                pqueue.offer(entry);
            } else {
                if (entry.getValue() < pqueue.peek().getValue()) {
                    pqueue.poll();
                    pqueue.offer(entry);
                }
            }
        }

        // Get the result array
        int[][] result = new int[K][];
        for (int index = 0; index < K; index++) {
            result[index] = pqueue.poll().getKey();
        }

        return result;
    }

    /*****/

    // // Time: O(nlogn)
    // // Space: O(n)
    // // Comparator
    // private int[][] bruteForce(int[][] points, int K) {
    //     // Corner Cases
    //     if (points == null || points.length == 0 || points[0].length == 0) {
    //         return new int[][] {};
    //     }

    //     // O(nlogn)
    //     Arrays.sort(points, new Comparator<int[]>() {
    //         @Override
    //         public int compare(int[] a, int[] b) {
    //             int distanceA = a[0] * a[0] + a[1] * a[1];
    //             int distanceB = b[0] * b[0] + b[1] * b[1];

    //             return distanceA - distanceB;
    //         }
    //     });

    //     return Arrays.copyOfRange(points, 0, K);
    // }

    /***********************************************************/

    // public int[][] kClosest(int[][] points, int K) {
    //     bruteForce(points, K);
    //     maxHeap(points, K);
    //     return quickSelect(points, K);
    // }

    // // Time: O(nlogn)
    // // Space: O(n)
    // // Comparator + Arrays
    // private int[][] bruteForce(int[][] points, int K) {
    //     // Corner Cases
    //     if (points.length == 0 || points == null) {
    //         return new int[][] {};
    //     }

    //     // sort the array based on the disatance from small -> large
    //     Arrays.sort(points, new Comparator<int[]>() {
    //         @Override
    //         public int compare(int[] o1, int[] o2) {
    //             int distance1 = o1[0] * o1[0] + o1[1] * o1[1];
    //             int distance2 = o2[0] * o2[0] + o2[1] * o2[1];
    //             return distance1 - distance2;
    //         }
    //     });

    //     // Arrays.copyOfRange
    //     // [From, To)
    //     return Arrays.copyOfRange(points, 0, K);
    // }

    // // Time: O(nlogK)
    // // Space: O(n)
    // // HashMap + PriorityQueue (MaxHeap)
    // // (if use MinHeap, then store n in MinHeap and get out K => Klogn)
    // private int[][] maxHeap(int[][] points, int K) {
    //     // Corner Cases
    //     if (points.length == 0 || points == null || points[0].length == 0) {
    //         return new int[][] {};
    //     }

    //     // O(n)
    //     // use map to store the position => distance
    //     Map<int[], Integer> map = new HashMap<>();
    //     for (int[] point : points) {
    //         map.put(point, point[0] * point[0] + point[1] * point[1]);
    //     }

    //     // O(logn)
    //     // use PriorityQueue to store the map based on the distance of each position - MapHeap
    //     // use Comparator to sort it so that poll() will get the largest distance
    //     PriorityQueue<Map.Entry<int[], Integer>> pqueue = new PriorityQueue<>(new Comparator<Map.Entry<int[], Integer>>() {
    //         @Override
    //         public int compare(Map.Entry<int[], Integer> o1, Map.Entry<int[], Integer> o2) {
    //             return o2.getValue() - o1.getValue();
    //         }
    //     });

    //     // O(n*logk)
    //     // filter and get the K cloest points
    //     for (Map.Entry<int[], Integer> entry : map.entrySet()) {
    //         if (pqueue.size() < K) {
    //             pqueue.offer(entry);
    //         } else {
    //             if (entry.getValue() < pqueue.peek().getValue()) {
    //                 pqueue.poll();
    //                 pqueue.offer(entry);
    //             }
    //         }
    //     }

    //     int[][] result = new int[K][];
    //     // O(K)
    //     for (int index = 0; index < K; index++) {
    //         result[index] = pqueue.poll().getKey();
    //     }

    //     return result;
    // }

    // // Time: O(n)
    // // Space: O(1)
    // // Quick Select
    // private int[][] quickSelect(int[][] points, int K) {
    //     // Corner Cases
    //     if (points.length == 0 || points == null) {
    //         return new int[][] {};
    //     }

    //     int begin = 0, end = points.length - 1;
    //     while (begin <= end) {
    //         int mid = qs(points, begin, end);
    //         if (mid == K) {
    //             break;
    //         } else if (mid < K) {
    //             begin = mid + 1;
    //         } else {
    //             end = mid -1;
    //         }
    //     }
    //     return Arrays.copyOfRange(points, 0, K);
    // }

    // private int qs(int[][] points, int low, int high) {
    //     int left = low, right = high;
    //     int[] pivot = points[left];

    //     while (left < right) {
    //         // right
    //         while (left < right && distanceDiff(points[right], pivot) >= 0) {
    //             right--;
    //         }
    //         points[left] = points[right];
    //         // left
    //         while (left < right && distanceDiff(points[left], pivot) <= 0) {
    //             left++;
    //         }
    //         points[right] = points[left];
    //     }
    //     points[left] = pivot;
    //     return left;
    // }

    // private int distanceDiff(int[] arr1, int[] arr2) {
    //     int distance1 = arr1[0] * arr1[0] + arr1[1] * arr1[1];
    //     int distance2 = arr2[0] * arr2[0] + arr2[1] * arr2[1];
    //     return distance1 - distance2;
    // }

}
// @lc code=end

