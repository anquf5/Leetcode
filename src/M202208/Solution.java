package M202208;
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

    //256. Paint House
    public static int minCost(int[][] costs) {
        int m = costs.length;
        for (int i = 1; i < m; i++) {
            costs[i][0] += Math.min(costs[i-1][1], costs[i-1][2]);
            costs[i][1] += Math.min(costs[i-1][0], costs[i-1][2]);
            costs[i][2] += Math.min(costs[i-1][0], costs[i-1][1]);
        }
        return Math.min(Math.min(costs[m-1][0], costs[m-1][1]),costs[m-1][2]);
    }

    //276. Paint Fence
    public static int numWays(int n, int k) {
        int[] dp = new int[n + 1];
        if (n == 1) return k;
        if (n == 2) return k * k;
        dp[0] = 0;
        dp[1] = k;
        dp[2] = k * k;
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i-1] * (k - 1) + dp[i-2] * (k - 1);
        }
        return dp[n];
    }

    //1. Two Sum
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        return null;
    }

    //26. Remove Duplicates from Sorted Array
//    public int removeDuplicates(int[] nums) {
//        int low = 0, fast = 1, ans = 1;
//        while(fast < nums.length){
//            if (nums[fast] != nums[low]){
//                ans++;
//                low++;
//                nums[low] = nums[fast];
//                fast++;
//            }
//            else{
//                fast++;
//            }
//        }
//        return ans;
//    }

    //27. Remove Element
    public int removeElement(int[] nums, int val) {
        int low = 0, fast = 0, ans = 0;
        while(fast < nums.length){
            if (nums[fast] != val){
                nums[low] = nums[fast];
                low++;
                fast++;
                ans++;
            }
            else{
                fast++;
            }
        }
        return ans;
    }

    //66. Plus One
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n-1; i >= 0 ; i--) {
            if (digits[i] != 9){
                digits[i]++;
                return digits;
            }else{
                digits[i] = 0;
            }
        }
        digits = new int[n+1];
        digits[0] = 1;
        return digits;
    }

    //88. Merge Sorted Array
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Set p1 and p2 to point to the end of their respective arrays.
        int p1 = m - 1;
        int p2 = n - 1;

        // And move p backwards through the array, each time writing
        // the smallest value pointed at by p1 or p2.
        for (int p = m + n - 1; p >= 0; p--) {
            if (p2 < 0) {
                break;
            }
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1--];
            } else {
                nums1[p] = nums2[p2--];
            }
        }
    }

    //28. Implement strStr()
    public static int strStr(String haystack, String needle) {
        int hi = 0, ni = 0;
        while(hi <= haystack.length() - needle.length()){
            if (haystack.charAt(hi) == needle.charAt(ni)){
                if (haystack.substring(hi, hi + needle.length()).equals(needle)){
                    return hi;
                }
            }
            hi++;
        }
        return -1;
    }

    //80. Remove Duplicates from Sorted Array II
    public static int removeDuplicates(int[] nums) {
        int low = 0, fast = 0, num = 0;
        while(fast < nums.length){
            if (num < 2 || nums[low] != nums[fast]){
                nums[low] = nums[fast];
                low++;
                fast++;
            }else{
                fast++;
            }
        }
        return low;
    }

//    // 77. Combinations
//    static List<List<Integer>> ans = new LinkedList<>();
//    public static List<List<Integer>> combine(int n, int k) {
//        LinkedList<Integer> temp = new LinkedList<>();
//        drawback(ans, temp, 1, k, n);
//        return ans;
//    }
//     static void drawback(List<List<Integer>> ans, LinkedList<Integer> temp, int cur, int k, int n){
//        if (temp.size() == k){
//            ans.add(new LinkedList<>(temp));
//            return;
//        }
//         for (int i = cur; i <= n; i++) {
//             temp.add(i);
//             drawback(ans, temp, i + 1, k, n);
//             temp.removeLast();
//         }
//     }
//
//     //71. Simplify Path
//     public static String simplifyPath(String path) {
//        Stack<Character> res = new Stack<>();
//        res.push('/');
//         for (int i = 0; i < path.length(); i++) {
//             if (path.charAt(i) == '/' && (path.charAt(i) == res.peek() || i == path.length()-1)){
//                 continue;
//             }
//             else if (path.charAt(i) == '.') {
//                 while (res.peek() != '/') {
//                     res.pop();
//                 }
//                 continue;
//             }
//             res.push(path.charAt(i));
//         }
//         List<Character> temp = new LinkedList<>(res);
//         String s = "";
//         for (Character c: temp) {
//             s += c;
//         }
//         return s;
//     }
//
//     // 31. Next Permutation
//     public void nextPermutation(int[] nums) {
//        int n = nums.length, i = n-2;
//        while(i >= 0 && nums[i] >= nums[i+1]){
//            i--;
//        }
//        if (i >= 0){
//            int j = n - 1;
//            while(nums[i] >= nums[j]){
//                j--;
//            }
//            swap(i, j, nums);
//        }
//        reverse(i + 1, nums);
//     }
//
//     void swap(int i, int j, int[] nums){
//        int temp = nums[i];
//        nums[i] = nums[j];
//        nums[j] = temp;
//     }
//
//     void reverse(int start, int[] nums){
//        int i = start, j = nums.length - 1;
//        while(i < j){
//            swap(i, j, nums);
//            i++;
//            j--;
//        }
//     }
//
//     //33. Search in Rotated Sorted Array
//     public static int search(int[] nums, int target) {
//        int n = nums.length;
//        int l = 0, r = n - 1;
//        if (n == 1) return nums[0] == target ? 0: -1;
//        if (nums[l] < nums[r]) return binarySearch(nums, l, r, target);
//        else{
//            int max_index = binarySearchMax(nums);
//            if (nums[l] < target){
//                return binarySearch(nums, l, max_index, target);
//            }
//            else{
//                return binarySearch(nums, max_index+1, r, target);
//            }
//        }
//     }
//
//     static int binarySearchMax(int[] nums){
//        int l = 0, r = nums.length - 1;
//        while(l < r){
//            int m = (l + r) / 2;
//            if (nums[m] > nums[l] && nums[m] > nums[r]){
//                l = m;
//            }
//            else if (nums[m] < nums[l] && nums[m] < nums[r]){
//                r = m;
//            }
//            else{
//                r--;
//            }
//        }
//        return l;
//     }
//
//     //34. Find First and Last Position of Element in Sorted Array
//     public static int[] searchRange(int[] nums, int target) {
//         int n = nums.length;
//         if (n == 0) return new int[]{-1,-1};
//         if (n == 1) {
//             int result = nums[0] == target? 0: -1;
//             return new int[]{result, result};
//         }
//         if (nums[0] == target && nums[n-1] == target) return new int[]{0, n-1};
//        int[] result = {0,0};
//        int mid = binarySearch(nums, 0, n - 1, target);
//        if (mid == -1) return new int[]{-1, -1};
//        int start = mid;
//        while(nums[start] == target){
//            if (start == 0 || nums[start] != nums[start-1]) break;
//            start = binarySearch(nums, 0, start - 1, target);
//        }
//        result[0] = start;
//
//        int end = mid;
//        while(nums[end] == target){
//            if (end == n - 1 || nums[end] != nums[end+1]) break;
//            end = binarySearch(nums, end + 1, n-1, target);
//        }
//        result[1] = end;
//        return result;
//     }
//
//    static int binarySearch(int[] nums, int l, int r, int target){
//        while(l <= r){
//            int m = (l + r) / 2;
//            if (nums[m] > target) r = m - 1;
//            else if (nums[m] < target) l = m + 1;
//            else return m;
//        }
//        return -1;
//    }
//
//    //239. Sliding Window Maximum
//    public static int[] maxSlidingWindow(int[] nums, int k) {
//        List<Integer> list = new LinkedList<>();
//        if (nums.length <= k){
//            int i = 0;
//            int maxValue = 0;
//            while(i < nums.length){
//                maxValue = Math.max(nums[i], maxValue);
//                i++;
//            }
//            list.add(maxValue);
//            return list.stream().mapToInt((Integer::intValue)).toArray();
//        }
//        int l = 0, r = 0;
//        int maxIndex = l;
//        while(r < nums.length){
//            if (r - l + 1 < k){
//                if (nums[r] > nums[maxIndex]) {
//                    maxIndex = r;
//                }
//                r++;
//            }
//            else if(r - l + 1 == k){
//                if (l <= maxIndex){
//                    if (nums[r] >= nums[maxIndex]){
//                        maxIndex = r;
//                    }
//                }
//                else{
//                    maxIndex = l;
//                    int templeRight = maxIndex + 1;
//                    while(templeRight <= r){
//                        if (nums[templeRight] > nums[maxIndex]){
//                            maxIndex = templeRight;
//                        }
//                        templeRight++;
//                    }
//                }
//                l++;
//                r++;
//                list.add(nums[maxIndex]);
//            }
//        }
//        return list.stream().mapToInt((Integer::intValue)).toArray();
//    }
//
//    //338. Counting Bits
//    public int[] countBits(int n) {
//        int[] ans = new int[n + 1];
//        int x = 0;
//        int b = 1;
//
//        // [0, b) is calculated
//        while (b <= n) {
//            // generate [b, 2b) or [b, n) from [0, b)
//            while (x < b && x + b <= n) {
//                ans[x + b] = ans[x] + 1;
//                ++x;
//            }
//            x = 0; // reset x
//            b <<= 1; // b = 2b
//        }
//
//        return ans;
//    }

    //17. Letter Combinations of a Phone Number
//    private List<String> res = new LinkedList<>();
//    private Map<Character, char[]> map = Map.of(
//    '2', new char[]{'a','b','c'},
//    '3', new char[]{'d','e','f'},
//    '4', new char[]{'g','h','i'},
//    '5', new char[]{'j','k','l'},
//    '6', new char[]{'m','n','o'},
//    '7', new char[]{'p','q','r','s'},
//    '8', new char[]{'t','u','v'},
//    '9', new char[]{'w','x','y','z'}
//    );
//    public List<String> letterCombinations(String digits) {
//        if (digits.length() == 0) return res;
//        backtrack(0, new StringBuilder(), digits);
//        return res;
//    }
//
//    void backtrack(int index, StringBuilder temp, String digits){
//        if (temp.length() == digits.length()){
//            res.add(temp.toString());
//            return;
//        }
//
//        char[] letters = map.get(digits.charAt(index));
//        for (char letter: letters){
//            temp.append(letter);
//            backtrack(index+1, temp, digits);
//            temp.deleteCharAt(temp.length()-1);
//        }
//    }

    //20. Valid Parentheses
//    public boolean isValid(String s) {
//        Stack<Character> stack = new Stack<>();
//        Map<Character, Character> map = new HashMap<>();
//        map.put('(',')');
//        map.put('{','}');
//        map.put('[',']');
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{'){
//                stack.push(s.charAt(i));
//            }
//            else {
//                if (stack.size() == 0 || s.charAt(i) != map.get(stack.peek())){
//                    return false;
//                }
//                else{
//                    stack.pop();
//                }
//            }
//        }
//        return stack.size() == 0 ? true : false;
//    }

    // 12. Integer to Roman
//    public String intToRoman(int num) {
//        Map<Integer, String> dict = Map.of(
//                1, "I",
//                4,"IV",
//                5, "V",
//                9, "IX",
//                10, "X",
//                40, "XL",
//                50, "L",
//                100, "C",
//                500, "D",
//                1000, "M"
//        );
//        dict.put(90, "XC");
//        dict.put(400, "CD");
//        dict.put(900, "CM");
//        String s = num + "";
//        for (int i = s.length(); i >= 0; i++) {
//
//        }
//    }

    //191. Number of 1 Bits
//    public static int hammingWeight(int n) {
//        int sum = 0;
//        while (n != 0) {
//            sum++;
//            n &= (n - 1);
//        }
//        return sum;
//    }

    //136. Single Number
//    public int singleNumber(int[] nums) {
//        int a = 0;
//        for (int i : nums) {
//            a ^= i;
//        }
//        return a;
//    }

    //90. Subsets II
//    public List<List<Integer>> subsetsWithDup(int[] nums) {
//        Arrays.sort(nums);
//        List<List<Integer>> set = new ArrayList<>();
//        List<Integer> list = new ArrayList<>();
//        backtrack(set, list, nums, 0);
//        return set;
//    }
//
//    void backtrack(List<List<Integer>> set, List<Integer> list, int[] nums, int index){
//        set.add(new ArrayList<>(list));
//        for (int i = index; i < nums.length; i++) {
//            if (i != index && nums[i] == nums[i - 1]) {
//                continue;
//            }
//            list.add(nums[i]);
//            backtrack(set, list, nums, i + 1);
//            list.remove(list.size() - 1);
//        }
//    }
}
