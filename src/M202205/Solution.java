package M202205;

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
class Solution {
    //05-01
    //376. Wiggle Subsequence
//    public static int wiggleMaxLength(int[] nums) {
//        int n = nums.length;
//        int[][] dp = new int[n][2];
//        int res = 1;
//        // 0 - the last difference is negative
//        // 1 - the last difference is positive
//        dp[0][0] = 1;
//        dp[0][1] = 1;
//        for (int i = 1; i < n; i++) {
//            for (int j = 0; j < i; j++) {
//                if (nums[i] > nums[j]){
//                    dp[i][1] = Math.max(dp[j][0] + 1, dp[i][1]);
//                }
//                else if(nums[i] < nums[j]){
//                    dp[i][0] = Math.max(dp[j][1] + 1, dp[i][0]);
//                }
//                else{
//                    continue;
//                }
//            }
//            res = Math.max(res, Math.max(dp[i][0], dp[i][1]));
//        }
//        return res;
//    }

    // optimized approach
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int down = 1, up = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1])
                up = down + 1;
            else if (nums[i] < nums[i - 1])
                down = up + 1;
        }
        return Math.max(down, up);
    }

    //38. Count and Say
    // wrong
    public static String countAndSay(int n) {
        String[] dp = new String[n + 1];
        dp[1] = "1";
        for (int i = 2; i <= n; i++) {
            dp[i] = count(dp[i-1]);
        }
        return dp[n];
    }

    static String count(String s){
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (char c : s.toCharArray()){
            int num = Integer.parseInt(c + "");
            max = Math.max(num, max);
            if(map.containsKey(num)) map.put(num, map.get(num) + 1);
            else map.put(num, 1);
        }
        String res = "";
        for (int i = max; i >= 0; i--) {
            if (map.containsKey(i)) res += map.get(i) + "" + i + "";
            else continue;
        }
        return res;
    }

    //978. Longest Turbulent Subarray
    public static int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int lo = 0, hi = 1;
        int max = 1;
        while(hi < n){
            int diff = Integer.compare(arr[hi - 1], arr[hi]);
            if (diff == 0){
                lo = hi;
            }
            else if(hi == n - 1 || diff * Integer.compare(arr[hi], arr[hi + 1]) != -1){
                max = Math.max(hi - lo + 1, max);
                lo = hi;
            }
            hi++;
        }
        return max;
    }

    //05-03
    //219. Contains Duplicate II
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int l = 0, r = 1;
        set.add(nums[l]);
        while(r - l <= k && r < nums.length){
            if (set.contains(nums[r])) return true;
            else set.add(nums[r]);
            if (r - l == k){
                set.remove(nums[l]);
                l++;
            }
            r++;
        }
        return false;
    }

    //643. Maximum Average Subarray I
    public static double findMaxAverage(int[] nums, int k) {
        int start = 0, end = k;
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        double max = sum;
        while(end < nums.length){
            sum = sum - nums[start++] + nums[end++];
            max = Math.max(max, sum);
        }
        return max / (double) k;
    }

    //3. Longest Substring Without Repeating Characters
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))){
                max = Math.max(map.size(), max);
                int end = map.get(s.charAt(i));
                for (int j = start; j <= end; j++) {
                    map.remove(s.charAt(j));
                }
                start = end + 1;
            }
            map.put(s.charAt(i), i);
        }
        return Math.max(max, map.size());
    }

    //187. Repeated DNA Sequences
    public static List<String> findRepeatedDnaSequences(String s) {
        Set<String> set = new HashSet<>();
        Set<String> res = new HashSet<>();
        int start = 0;
        while(start <= s.length() - 10){
            String subString = s.substring(start, start + 10);
            if (set.contains(subString)){
                res.add(subString);
            }
            set.add(subString);
            start++;
        }
        return new ArrayList<>(res);
    }

    //05-04
    //16. 3Sum Closest
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int cur_diff = Math.abs(nums[0] + nums[1] + nums[n-1] - target);
        int cur_sum = nums[0] + nums[1] + nums[n-1];
        for (int i = 0; i < n - 2; i++) {
            int lo = i + 1, hi = n - 1;
            int min_diff = Math.abs(nums[i] + nums[lo] + nums[hi] - target);
            while(lo < hi){
                int diff = nums[i] + nums[lo] + nums[hi] - target;
                if (diff == 0) return target;
                else if (diff > 0){
                    hi--;
                }
                else lo++;
                if(Math.abs(diff) < min_diff) {
                    min_diff = Math.abs(diff);
                }
                if (min_diff < cur_diff){
                    cur_diff = min_diff;
                    cur_sum = diff + target;
                }
            }
        }
        return cur_sum;
    }

    //31. Next Permutation
//    public static void nextPermutation(int[] nums) {
//        int n = nums.length;
//        int i = n - 1;
//        while(i >= 0){
//            if (i == 0){
//                int l = 0, r = n - 1;
//                while(l < r){
//                    exchange(nums, l , r);
//                    l++;
//                    r--;
//                }
//                return;
//            }
//            else{
//                int j = i - 1;
//                while(j >= 0){
//                    if(nums[i] > nums[j]){
//                        exchange(nums, i, j);
//                        break;
//                    }
//                    j--;
//                }
//            }
//            i--;
//        }
//    }

    //39. Combination Sum
//    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
//        List<List<Integer>> res = new ArrayList<>();
//        LinkedList<Integer> group = new LinkedList<>();
//        for (int i = 0; i < candidates.length; i++) {
//            backTrack(res, group, candidates, i, target, 0);
//        }
//        return new LinkedList<>(res);
//    }
//
//    static void backTrack(List<List<Integer>> res,
//                   LinkedList<Integer> group,
//                   int[] candidates,
//                   int index,
//                   int target,
//                   int sum){
//        if (sum == target) {
//            if (!res.contains(group)){
//                res.add(new LinkedList<>(group));
//            }
//        }
//        else if (sum > target){
//            return;
//        }
//        else{
//            for (int i = index; i < candidates.length; i++) {
//                sum += candidates[index];
//                group.addLast(candidates[index]);
//                backTrack(res, group, candidates, i, target, sum);
//                sum -= candidates[index];
//                group.removeLast();
//            }
//        }
//    }

    //05-05
    //131. Palindrome Partitioning
//    public static List<List<String>> partition(String s) {
//        List<List<String>> res = new ArrayList<>();
//        LinkedList<String> group = new LinkedList<>();
//        backTracker(res, group, s, 0);
//        return res;
//    }
//
//    static void backTracker(List<List<String>> res,
//                     LinkedList<String> group,
//                     String s,
//                     int index){
//        if (index >= s.length()) {
//            res.add(new ArrayList<String>(group));
//        }
//        for (int i = index; i < s.length(); i++) {
//            if (isPalindrome(s, index, i)){
//                group.add(s.substring(index, i + 1));
//                backTracker(res, group, s, i + 1);
//                group.removeLast();
//            }
//        }
//    }

    static boolean isPalindrome(String s, int lo, int hi){
        while(lo < hi){
            if (s.charAt(lo++) != s.charAt(hi--)){
                return false;
            }
        }
        return true;
    }

    //134. Gas Station
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int total = 0, cur = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            total += gas[i] - cost[i];
            cur += gas[i] - cost[i];
            if (cur < 0) {
                start = i + 1;
                cur = 0;
            }
        }
        return total >= 0 ? start : -1;
    }

    //40. Combination Sum II
//    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
//        List<List<Integer>> res = new ArrayList<>();
//        LinkedList<Integer> group = new LinkedList<>();
//        Arrays.sort(candidates);
//        backTracker(candidates, target, 0, res, group, 0);
//        return res;
//    }
//
//    static void backTracker(int[] candidates,
//                     int target,
//                     int i,
//                     List<List<Integer>> res,
//                     LinkedList<Integer> group,
//                     int sum){
//        if (sum == target){
//            res.add(new ArrayList<>(group));
//            return;
//        }
//        for (int j = i; j < candidates.length; j++) {
//            if (j > i && candidates[j] == candidates[j - 1]) continue;
//            if (sum + candidates[j] > target){
//                break;
//            }
//            group.add(candidates[j]);
//            sum += candidates[j];
//            backTracker(candidates, target, j + 1, res, group, sum);
//            sum -= candidates[j];
//            group.removeLast();
//        }
//    }

    //133. Clone Graph
    Map<Node, Node> map = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null) return node;
        if (map.containsKey(node)) return map.get(node);
        Node copy = new Node(node.val);
        map.put(node, copy);
        for (Node n : node.neighbors){
            copy.neighbors.add(cloneGraph(n));
        }
        return copy;
    }

    //165. Compare Version Numbers
    public static int compareVersion(String version1, String version2) {
        String[] ver_1 = version1.split("\\.");
        String[] ver_2 = version2.split("\\.");
        int n1 = ver_1.length;
        int n2 = ver_2.length;
        int[] ver_1_int = new int[n1];
        int[] ver_2_int = new int[n2];
        for (int i = 0; i < n1; i++) {
            ver_1_int[i] = Integer.parseInt(ver_1[i]);
        }
        for (int i = 0; i < n2; i++) {
            ver_2_int[i] = Integer.parseInt(ver_2[i]);
        }
        int l1 = 0, l2 = 0;
        int min_len = Math.min(n1, n2);
        int max_len = Math.max(n1, n2);
        while(l1 == l2 && l1 < min_len){
            if (ver_1_int[l1] > ver_2_int[l2]) return 1;
            else if (ver_1_int[l1] < ver_2_int[l2]) return -1;
            l1++;
            l2++;
        }
        if (n1 == max_len){
            for (int i = min_len; i < max_len; i++) {
                if (ver_1_int[i] != 0) return 1;
            }
        }
        else if (n2 == max_len){
            for (int i = min_len; i < max_len; i++) {
                if (ver_2_int[i] != 0) return -1;
            }
        }
        return 0;
    }

    //121. Best Time to Buy and Sell Stock
//    public static int maxProfit(int[] prices) {
//        int min = Integer.MAX_VALUE;
//        int max = 0;
//        for (int i = 0; i < prices.length; i++) {
//            if (prices[i] < min) min = prices[i];
//            max = Math.max(prices[i] - min, max);
//        }
//        return max;
//    }

    //122. Best Time to Buy and Sell Stock II
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]){
                max += prices[i] - prices[i-1];
            }
        }
        return max;
    }

    //132. Palindrome Partitioning II
    //Approach 1: Dynamic Programming
    public static int minCut(String s) {
        if (isPartitioning(s, 0, s.length()-1)) return 0;
        int[] dp = new int[s.length()];
        Arrays.fill(dp, Integer.MAX_VALUE);
        Map<Character, List<Integer>> map = new HashMap<>();
        dp[0] = 1;
        List<Integer> list = new LinkedList<>();
        list.add(0);
        map.put(s.charAt(0), list);
        for (int i = 1; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))){
                List<Integer> temp_list = map.get(s.charAt(i));
                for (Integer index : temp_list){
                    if (isPartitioning(s, index, i)){
                        if (index == 0) dp[i] = dp[i] = Math.min(dp[index], dp[i]);
                        else dp[i] = Math.min(dp[index - 1] + 1, dp[i]);
                    }
                    else dp[i] = Math.min(dp[i-1] + 1, dp[i]);
                }
                temp_list.add(i);
                map.put(s.charAt(i), temp_list);
            }
            else{
                List<Integer> temp_list = new LinkedList<>();
                temp_list.add(i);
                map.put(s.charAt(i), temp_list);
                dp[i] = dp[i-1] + 1;
            }
        }
        return dp[s.length()-1] - 1;
    }

    static boolean isPartitioning(String s, int l, int r){
        while(l < r){
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }




    public static void main(String[] args) {
        String test1 = "aaabaa";
        String test2 = "a";
        String test3 = "ab";
        String test4 = "abbab";
        System.out.println(minCut(test4));

    }
}
