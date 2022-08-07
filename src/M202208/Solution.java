package M202208;
import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    public static String reverseWords(String s) {
        s = s.trim();
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    // 189. Rotate Array - Approach 1
//    public static void rotate(int[] nums, int k) {
//        int m = k % nums.length;
//        Queue<Integer> queue = new LinkedList<>();
//        for (int i = nums.length - m; i < nums.length; i++) {
//            queue.add(nums[i]);
//        }
//        for (int i = nums.length - m - 1; i >= 0 ; i--) {
//            nums[i + m] = nums[i];
//        }
//        int i = 0;
//        while(!queue.isEmpty()) {
//            nums[i] = queue.remove();
//            i++;
//        }
//    }

    // 189. Rotate Array - Approach 2
    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }

    // 259. 3Sum Smaller
    public static int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int lo = i + 1, hi = nums.length - 1;
            while(lo < hi){
                int sum = nums[i] + nums[lo] + nums[hi];
                if(sum >= target){
                    hi--;
                }
                else if(sum < target){
                    ans += hi - lo;
                    lo++;
                }
            }
        }
        return ans;
    }

    //18. 4Sum - Time Limit Exceeded Approach
//    static List<List<Integer>> res = new ArrayList<>();
//    static LinkedList<Integer> quadruplet = new LinkedList<>();
//    public static List<List<Integer>> fourSum(int[] nums, int target) {
//        Arrays.sort(nums);
//        for (int i = 0; i < nums.length; i++) {
//            drawback(nums, i, target);
//        }
//        return res;
//    }
//
//    static void drawback(int[] nums, int i, int remain){
//        if (quadruplet.size() == 4) {
//            if (remain == 0 && !res.contains(quadruplet)){
//                res.add(new ArrayList<>(quadruplet));
//            }else return;
//        }
//        for (int j = i; j < nums.length; j++) {
//            quadruplet.add(nums[j]);
//            drawback(nums, j + 1, remain - nums[j]);
//            quadruplet.removeLast();
//        }
//    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    public List<List<Integer>> kSum(int[] nums, long target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();

        // If we have run out of numbers to add, return res.
        if (start == nums.length) {
            return res;
        }

        // There are k remaining values to add to the sum. The
        // average of these values is at least target / k.
        long average_value = target / k;

        // We cannot obtain a sum of target if the smallest value
        // in nums is greater than target / k or if the largest
        // value in nums is smaller than target / k.
        if  (nums[start] > average_value || average_value > nums[nums.length - 1]) {
            return res;
        }

        if (k == 2) {
            return twoSum(nums, target, start);
        }

        for (int i = start; i < nums.length; ++i) {
            if (i == start || nums[i - 1] != nums[i]) {
                for (List<Integer> subset : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size() - 1).addAll(subset);
                }
            }
        }

        return res;
    }

    public List<List<Integer>> twoSum(int[] nums, long target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        int lo = start, hi = nums.length - 1;

        while (lo < hi) {
            int currSum = nums[lo] + nums[hi];
            if (currSum < target || (lo > start && nums[lo] == nums[lo - 1])) {
                ++lo;
            } else if (currSum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1])) {
                --hi;
            } else {
                res.add(Arrays.asList(nums[lo++], nums[hi--]));
            }
        }

        return res;
    }

    // 72. Edit Distance
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        if(word1.length() * word2.length() == 0) return word1.length()+word2.length();
        for (int i = 0; i < word1.length() + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < word2.length() + 1; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1] - 1));
                }else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    //152. Maximum Product Subarray
    public static int maxProduct(int[] nums) {
//        int n = nums.length;
//        int[][] dp = new int[n][2];
//        // 0 - min 1 - max
//        dp[0][0] = nums[0];
//        dp[0][1] = nums[0];
//        int ans = dp[0][1];
//        for (int i = 1; i < n; i++) {
//            dp[i][0] = Math.min(Math.min(dp[i-1][0] * nums[i], dp[i-1][1]*nums[i]), nums[i]);
//            dp[i][1] = Math.max(Math.max(dp[i-1][0] * nums[i], dp[i-1][1]*nums[i]), nums[i]);
//            if(dp[i][1] > ans) ans = dp[i][1];
//        }
//        return ans;
        if (nums.length == 0) return 0;

        int n = nums.length;
        int min = nums[0];
        int max = nums[0];
        int ans = max;
        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            int temp = Math.max(Math.max(max * cur, min * cur), cur);
            min = Math.min(Math.min(max * cur, min * cur), cur);
            max = temp;
            ans = Math.max(max, ans);
        }
        return ans;
    }

    //53. Maximum Subarray
    public int maxSubArray(int[] nums) {
        if(nums.length == 1) return nums[0];
        int curr_sum = nums[0];
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            curr_sum = Math.max(curr, curr_sum + curr);
            ans = Math.max(curr_sum, ans);
        }
        return ans;
    }

    //97. Interleaving String
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        if (s3.length() != m + n) return false;
        boolean[][] dp = new boolean[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if(i == 0 && j == 0) dp[i][j] = true;
                else if(i == 0) dp[i][j] = s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j-1];
                else if(j == 0) dp[i][j] = s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i-1][j];
                else dp[i][j] = (s1.charAt(i-1) == s3.charAt(i + j - 1) && dp[i-1][j]) ||
                            (s2.charAt(j-1) == s3.charAt(i + j - 1) && dp[i][j-1]);
            }
        }
        return dp[m][n];
    }

    //198. House Robber
//    public static int rob(int[] nums) {
//        if (nums.length < 2) return nums.length == 0 ? 0 : nums[0];
//        int[] dp = new int[nums.length];
//        dp[0] = nums[0];
//        dp[1] = nums[1];
//        int ans = Math.max(dp[0], dp[1]);
//        for (int i = 2; i < nums.length; i++) {
//            for (int j = 0; j <= i - 2; j++) {
//                dp[i] = Math.max(dp[j] + nums[i], dp[i]);
//            }
//            ans = Math.max(dp[i], ans);
//        }
//        return ans;
//    }

//    public int rob(int[] nums) {
//        int n = nums.length;
//        if (n < 2) return n == 0 ? 0 : nums[0];
////        int dp[] = new int[n+1];
////        dp[n] = 0;
////        dp[n-1] = nums[n-1];
////        for (int i = n-2; i >= 0; i--) {
////            dp[i] = Math.max(dp[i + 1], dp[i + 2] + nums[i]);
////        }
////        return dp[0];
//        int prev = 0;
//        int cur = nums[n - 1];
//        int ans = 0;
//        for (int i = n-2; i >= 0; i--) {
//            ans = Math.max(prev + nums[i], cur);
//            prev = cur;
//            cur = ans;
//        }
//        return ans;
//    }

    //213. House Robber II
    public static int rob(int[] nums) {
        if (nums.length < 2) return nums.length == 0 ? 0 : nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int t1 = rob_helper(nums, 0, nums.length-2);
        int t2 = rob_helper(nums, 1, nums.length - 1);
        return Math.max(t1,t2);
    }

    static int rob_helper(int[] nums, int start, int end){
        int prev = 0;
        int cur = nums[end];
        int ans = 0;
        for (int i = end - 1; i >= start; i--) {
            ans = Math.max(cur, prev + nums[i]);
            prev = cur;
            cur = ans;
        }
        return ans;
    }

    //221. Maximal Square
    public static int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) dp[i][j] = matrix[i][j] - 48;
                else if(matrix[i][j] == '0') dp[i][j] = 0;
                else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i-1][j-1]) + 1;
                }
                max = Math.max(dp[i][j], max);
            }
        }
        return max * max;
    }

    //241. Different Ways to Add Parentheses


    public static void main(String[] args) {
//        int[] nums = {1,2};
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        char[][] matrix2 = {{'0','1'},{'1','0'}};
        System.out.println(maximalSquare(matrix2));
    }
}
