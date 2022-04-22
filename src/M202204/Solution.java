package M202204;

import groovyjarjarantlr4.runtime.tree.Tree;

import javax.naming.spi.StateFactory;
import java.util.*;

// Definition for singly-linked list.
//class ListNode {
//    int val;
//    ListNode next;
//    ListNode() {}
//    ListNode(int val) { this.val = val; }
//    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//}

//class Node {
//    public int val;
//    public Node left;
//    public Node right;
//    public Node next;
//
//    public Node() {}
//
//    public Node(int _val) {
//        val = _val;
//    }
//
//    public Node(int _val, Node _left, Node _right, Node _next) {
//        val = _val;
//        left = _left;
//        right = _right;
//        next = _next;
//    }
//}


// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


 //Definition for singly-linked list.
//class ListNode {
//    int val;
//    ListNode next;
//    ListNode() {}
//    ListNode(int val) { this.val = val; }
//    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//}

class Solution {

    //04-14
    // 986. Interval List Intersections
//    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
//        int p1 = 0, p2 = 0;
//        List<int[]> res = new ArrayList<>();
//        while(p1 < firstList.length && p2 < secondList.length){
//            int lo = Math.max(firstList[p1][0], secondList[p2][0]);
//            int hi = Math.min(firstList[p1][1], secondList[p2][1]);
//            if(lo <= hi) res.add(new int[]{lo,hi});
//            if(firstList[p1][1] < secondList[p2][1]) p1++;
//            else p2++;
//        }
//        return res.toArray(new int[res.size()][]);
//    }

    // 11. Container With Most Water
//    public int maxArea(int[] height) {
//        int n = height.length;
//        int area = Math.min(height[n-1], height[0]) * (n - 1);
//        int l = 0, r = n - 1;
//        while(l < r){
//            if(height[l] < height[r]) {
//                l++;
//                area = Math.max(area, Math.min(height[r], height[l]) * (r - l));
//            }else{
//                r--;
//                area = Math.max(area, Math.min(height[r], height[l]) * (r - l));
//            }
//        }
//        return area;
//    }

    //438. Find All Anagrams in a String
//    public static List<Integer> findAnagrams(String s, String p) {
//        Map<Character, Integer> key = new HashMap<>();
//        Map<Character, Integer> smap = new HashMap<>();
//        List<Integer> res = new LinkedList<>();
//        for(char ch : p.toCharArray()){
//            if(key.containsKey(ch)) key.put(ch, key.get(ch)+1);
//            else key.put(ch,1);
//        }
//        int startIndex = 0, endIndex = 0;
//        while(endIndex < s.length()){
//            if(smap.containsKey(s.charAt(endIndex))) smap.put(s.charAt(endIndex), smap.get(s.charAt(endIndex)) + 1);
//            else smap.put(s.charAt(endIndex), 1);
//
//            if(endIndex - startIndex + 1 == p.length()){
//                if(smap.equals(key)){
//                    res.add(startIndex);
//                }
//                if(smap.containsKey(s.charAt(startIndex))){
//                    if(smap.get(s.charAt(startIndex)) == 1) smap.remove(s.charAt(startIndex));
//                    else smap.put(s.charAt(startIndex), smap.get(s.charAt(startIndex)) - 1);
//                }
//                startIndex++;
//            }
//            endIndex++;
//        }
//        return res;
//    }

    //322. Coin Change
//    public int coinChange(int[] coins, int amount) {
//        if(amount == 0) return 0;
//        int[] dp = new int[amount + 1];
//        Arrays.fill(dp, amount+1);
//        for (int i = 1; i <= amount; i++) {
//            for(int j = coins.length - 1; j >= 0; j--){
//                if(i == coins[j]) dp[i] = 1;
//                else if(i < coins[j]) continue;
//                else{
//                    if(dp[i - coins[j]] != amount + 1) dp[i] = Math.min(dp[i-coins[j]] + 1, dp[i]);
//                }
//            }
//        }
//        if(dp[amount] != amount + 1) return dp[amount];
//        else return -1;
//    }

    //04-15
    //91. Decode Ways
    // Approach 1 - low efficient
//    public static int numDecodings(String s) {
//        Set<String> key = new HashSet<>();
//        for(int i = 1; i < 27; i++) key.add(""+ i);
//        int n = s.length();
//        if(n == 0) return 0;
//        if(s.charAt(0) == '0') return 0;
//        int[][] dp = new int[n][2];
//        dp[0][0] = 0;
//        dp[0][1] = 1;
//        for(int i = 1; i < n; i++){
//            String com = s.charAt(i-1) + "" + s.charAt(i);
//            if(key.contains(com)){
//                dp[i][0] = dp[i-1][1];
//                if(s.charAt(i) != '0') dp[i][1] = dp[i-1][0] + dp[i-1][1];
//            }
//            else{
//                if(s.charAt(i) == '0') continue;
//                dp[i][1] = dp[i-1][0] + dp[i-1][1];
//            }
//        }
//        return dp[n-1][0] + dp[n-1][1];
//    }
    // Approach 2 - Accept
//    public static int numDecodings(String s) {
//        int n = s.length();
//        if(n == 0 || s.charAt(0) == '0') return 0;
//        if(n <= 2 && Integer.parseInt(s) <= 26) return n;
//        int[] dp = new int[n + 1];
//        dp[0] = 1;
//        dp[1] = 1;
//        for (int i = 2; i < n + 1; i++) {
//            if(s.charAt(i) != '0') dp[i] = dp[i-1];
//            int twoDigits = Integer.parseInt(s.substring(i-2, i));
//            if(twoDigits >= 10 && twoDigits <= 26) dp[i] += dp[i-2];
//        }
//        return dp[n];
//    }

    //200. Number of Islands
//    void dfs(char[][] grid, int r, int c){
//        int nr = grid.length;
//        int nc = grid[0].length;
//        if(r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') return;
//        grid[r][c] = '0';
//        dfs(grid,r-1, c);
//        dfs(grid, r, c-1);
//        dfs(grid, r+1, c);
//        dfs(grid, r, c+1);
//    }
//    public int numIslands(char[][] grid) {
//        int res = 0;
//        if(grid.length == 0 || grid == null) return 0;
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++){
//                if(grid[i][j] == '1'){
//                    res++;
//                    dfs(grid,i,j);
//                }
//            }
//        }
//        return res;
//    }

    //100. Same Tree
    // Definition for a binary tree node.
//    public class TreeNode {
//        int val;
//        TreeNode left;
//        TreeNode right;
//        TreeNode() {}
//        TreeNode(int val) { this.val = val; }
//        TreeNode(int val, TreeNode left, TreeNode right) {
//            this.val = val;
//            this.left = left;
//            this.right = right;
//        }
//        public boolean isSameTree(TreeNode p, TreeNode q) {
//            if(p == null && q == null) return true;
//            if(p == null || q == null) return false;
//            if(p.val != q.val) return false;
//            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
//        }
//     }

     //572. Subtree of Another Tree
//    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
//         if(isSame(root, subRoot)) return true;
//         if(root == null) return false;
//         return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
//    }
//
//    boolean isSame(TreeNode a, TreeNode b){
//        if(a == null && b == null) return true;
//        else if(a == null || b == null) return false;
//        else if(a.val != b.val) return false;
//        return isSame(a.left, b.left) && isSame(a.right, b.right);
//    }

    //130. Surrounded Regions
//    public static void solve(char[][] board) {
//        int m = board.length;
//        int n = board[0].length;
//        int[][] route = new int[m][n];
//        int[][] mark = new int[m][n];
//        for (int i = 0; i < m; i++) {
//            if(board[i][0] == 'O') {
//                dfs(board, i, 0, route, mark);
//            }
//            if(board[i][n-1] == 'O'){
//                dfs(board, i, n-1, route, mark);
//            }
//        }
//        for (int i = 0; i < n; i++) {
//            if(board[0][i] == 'O') {
//                dfs(board, 0, i, route, mark);
//            }
//            if (board[m-1][i] == 'O') {
//                dfs(board, m-1, i, route, mark);
//            }
//        }
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0]. length; j++) {
//                if(board[i][j] == 'X') continue;
//                else{
//                    if(mark[i][j] != 1) board[i][j] = 'X';
//                }
//            }
//        }
//    }
//    static void dfs(char[][] board, int a, int b, int[][] route, int[][] mark){
//        if(a < 0 || b < 0 || a >= board.length || b>= board[0].length) return;
//        if(route[a][b] == 1) return;
//        route[a][b] = 1;
//        if(board[a][b] == 'O') {
//            mark[a][b] = 1;
//            dfs(board, a-1, b, route, mark);
//            dfs(board, a, b-1, route, mark);
//            dfs(board, a+1, b, route, mark);
//            dfs(board, a, b+1, route, mark);
//        }
//        else return;
//    }

    // 713. Subarray Product Less Than K
    // Approach 1. DFS -- Time Limit Exceeded
//    public static int numSubarrayProductLessThanK(int[] nums, int k) {
//        if(k == 0) return 0;
//        int res = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if(nums[i] < k){
//                res++;
//                int prod = nums[i];
//                res = dfs(i + 1, res, prod, nums, k);
//            }
//        }
//        return res;
//    }
//
//    static int dfs(int cur, int res, int prod, int[] nums, int k){
//        if(cur >= nums.length) return res;
//        prod *= nums[cur];
//        if(prod >= k){
//            return res;
//        }
//        else{
//            res++;
//            cur++;
//            return dfs(cur, res, prod, nums, k);
//        }
//    }

    // Approach 2 Sliding Window
//    public int numSubarrayProductLessThanK(int[] nums, int k) {
//        if(k < 1) return 0;
//        int prod = 1, l = 0, res = 0;
//        for (int i = 0; i < nums.length; i++) {
//            prod *= nums[i];
//            while(prod >= k) {
//                prod /= nums[l++];
//            }
//            res += i - l + 1;
//        }
//        return res;
//    }

    // 209. Minimum Size Subarray Sum
//    public static int minSubArrayLen(int target, int[] nums) {
//        int res = nums.length + 1, l = 0, sum = 0;
//        for (int i = 0; i < nums.length; i++) {
//            sum += nums[i];
//            while(sum >= target){
//                res = Math.min(i-l+1, res);
//                sum -= nums[l++];
//            }
//        }
//        return res == nums.length + 1 ? 0 : res;
//    }

    // 547. Number of Provinces
//    public static int findCircleNum(int[][] isConnected) {
//        int m = isConnected.length;
//        int[] flag = new int[m];
//        int res = 0;
//        for (int i = 0; i < m; i++) {
//            if(flag[i] == 1) continue;
//            res++;
//            dfs(isConnected, i, flag, m);
//        }
//        return res;
//    }
//    static void dfs(int[][] isConnected, int a, int[] flag, int m){
//        if(flag[a] == 1) return;
//        for (int i = 0; i < m; i++) {
//            if(i == a) {
//                flag[i] = 1;
//                continue;
//            }
//            if(isConnected[a][i] == 1){
//                dfs(isConnected, i, flag, m);
//            }
//        }
//    }

    // 2022-04-17
    //300. Longest Increasing Subsequence
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int max = 1;
        Arrays.fill(dp,1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    max = Math.max(dp[i], max);
                }
            }
        }
        return max;
    }

    //139. Word Break
//    public static boolean wordBreak(String s, List<String> wordDict) {
//        Set<String> key = new HashSet<>(wordDict);
//        boolean[] dp = new boolean[s.length() + 1];
//        dp[0] = true;
//        for (int i = 1; i < dp.length; i++) {
//            for(int j = i - 1; j >= 0; j--){
//                if(dp[j] && key.contains(s.substring(j,i))){
//                    dp[i] = true;
//                    break;
//                }
//            }
//        }
//        return dp[s.length()];
//    }

    //673. Number of Longest Increasing Subsequence
    // Haven't solved
//    public static int findNumberOfLIS(int[] nums) {
//        int[][] dp = new int[nums.length][2];
//        // dp[][0] - max dp[][1] count
//        dp[0][0] = 1;
//        dp[0][1] = 1;
//        int maxLen = 1;
//        List<Integer> maxLenIndex = new ArrayList<>();
//        for (int i = 1; i < nums.length; i++) {
//            for (int j = 0; j < i; j++) {
//                if(nums[i] > nums[j]){
//                    dp[i][0] = dp[j][0]+1;
//                    if(dp[j][0] == maxLen){
//                        if( dp[i][0] == maxLen + 1){
//                            dp[i][]
//                        }
//                        dp[i][1] = dp[j][1];
//                    }
//                }
//            }
//
//        }
//        return 0;
//    }

    //2022-04-18
    // 117. Populating Next Right Pointers in Each Node II
//    public Node connect(Node root) {
//        if(root == null) return root;
//        Queue<Node> queue = new LinkedList<>();
//        queue.add(root);
//        while(queue.size() > 0){
//            int size = queue.size();
//            for (int i = 0; i < size; i++){
//                Node node = queue.poll();
//                if(i < size - 1) node.next = queue.peek();
//                if(node.left != null) queue.add(node.left);
//                if(node.right != null) queue.add(node.right);
//            }
//        }
//        return root;
//    }

    // Recursive approach
//    public Node connect(Node root) {
//        if(root == null) return root;
//        if(root.left != null && root.right != null){
//            root.left.next = root.right;
//        }
//        if(root.left != null && root.right == null){
//            root.left.next = getConnection(root.next);
//        }
//        if(root.right != null){
//            root.right.next = getConnection(root.next);
//        }
//        connect(root.right);
//        connect(root.left);
//        return root;
//    }
//    Node getConnection(Node root){
//        if(root == null) return null;
//        if(root.left != null) return root.left;
//        if(root.right != null) return root.right;
//        if(root.next != null) return getConnection(root.next);
//        return null;
//    }

    //04-19
    //74. Search a 2D Matrix
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 1 && matrix[0].length == 1) return matrix[0][0] == target;
        int len = matrix.length - 1, wid = matrix[0].length - 1;
        int l = 0, r = len;
        int x = 0;
        while(l <= r){
            int m = (l + r) / 2;
            if(matrix[m][0] == target
                    || matrix[l][0] == target
                    || matrix[r][0] == target
                    || matrix[l][wid] == target
                    || matrix[r][wid] == target) return true;
            if(matrix[m][0] > target){
                if(l == m){
                    x = l;
                    break;
                }
                if(matrix[m - 1][0] > target) r = m - 1;
                else{
                    x = m - 1;
                    break;
                }
            }
            else{
                if(r == m) {
                    x = r;
                    break;
                }
                if(matrix[m][wid] < target) l = m + 1;
                else {
                    x = m;
                    break;
                }
            }
        }
        l = 0; r = wid;
        while(l <= r){
            int m = (l + r) / 2;
            if(matrix[x][m] == target) return true;
            if(matrix[x][m] > target) r = m - 1;
            else l = m + 1;
        }
        return false;
    }

    //494. Target Sum
    // Approach 1: Time Limit Exceeded
//    public static int findTargetSumWays(int[] nums, int target) {
//        if(nums.length == 1) return nums[0] == target || nums[0] == -target ? 1 : 0;
//        Map<Integer, List<Integer>> dp = new HashMap<>();
//        List<Integer> start = new LinkedList<>();
//        start.add(nums[0]);
//        start.add(-nums[0]);
//        dp.put(0, start);
//        int sum = 0;
//        for (int i = 1; i < nums.length; i++) {
//            int pos = nums[i];
//            int neg = -nums[i];
//            List<Integer> cur = new LinkedList<>();
//            for(Integer j : dp.get(i-1)){
//                if(i == nums.length - 1){
//                    if(j + pos == target) sum++;
//                    if(j + neg == target) sum++;
//                }else{
//                    cur.add(j + pos);
//                    cur.add(j + neg);
//                    dp.put(i, cur);
//                }
//            }
//        }
//        return sum;
//    }

    // Approach 2 Accepted
//    public static int findTargetSumWays(int[] nums, int target) {
//        if(nums.length == 1) return nums[0] == target || nums[0] == -target ? 1 : 0;
//        int total = Arrays.stream(nums).sum();
//        if(Math.abs(target) > total) return 0;
//        int[][] dp = new int[nums.length][2 * total + 1];
//        dp[0][nums[0] + total] = 1;
//        dp[0][-nums[0] + total]++;
//        for (int i = 1; i < nums.length; i++) {
//            for(int j = -total; j < total + 1; j++){
//                if(dp[i-1][j + total] != 0){
//                    dp[i][j + total + nums[i]] += dp[i-1][j + total];
//                    dp[i][j + total - nums[i]] += dp[i-1][j + total];
//                }
//            }
//        }
//        return dp[nums.length-1][target + total];
//    }

    //04-20
    //55. Jump Game
    public static boolean canJump(int[] nums) {
        int maxLen = 0;
        int i = 0;
        while(i < nums.length && i <= maxLen){
            maxLen = Math.max(nums[i] + i, maxLen);
            if(maxLen >= nums.length - 1) return true;
            else i++;
        }
        return false;
    }

    //5. Longest Palindromic Substring
    public static String longestPalindrome(String s) {
        if(s.length() < 2) return s;
        int sLen = s.length();
        boolean[][] dp = new boolean[sLen][sLen];
        dp[0][0] = true;
        int maxLen = 1;
        int maxStart = 0;
        for (int i = 1; i < sLen; i++) {
            dp[i][i] = true;
            if(s.charAt(i) == s.charAt(i-1)) {
                dp[i-1][i] = true;
                maxLen = 2;
                maxStart = i-1;
            }
        }
        for (int i = 1; i < sLen; i++) {
            for (int j = 0; j < i - 1; j++) {
                dp[j][i] = dp[j+1][i-1] && s.charAt(j) == s.charAt(i);
                if(dp[j][i] && i - j + 1 > maxLen){
                    maxLen = i - j + 1;
                    maxStart = j;
                }
            }
        }
        return s.substring(maxStart, maxStart + maxLen);
    }

    //  04-21
    // 264. Ugly Number II
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int n2 = 0, n3 = 0, n5 = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(dp[n2]*2,(Math.min(dp[n3]*3,dp[n5]*5)));
            if(dp[i] == dp[n2] * 2) n2++;
            if(dp[i] == dp[n3] * 3) n3++;
            if(dp[i] == dp[n5] * 5) n5++;
        }
        return dp[n-1];
    }

    //04-22
    // 22. Generate Parentheses
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    static void backtrack(List<String> res, StringBuilder string, int open, int close, int n){
        if(string.length() == 2 * n){
            res.add(string.toString());
            return;
        }
        if(open < n){
            string.append('(');
            backtrack(res, string, open+1, close, n);
            string.deleteCharAt(string.length()-1);
        }
        if(close < open){
            string.append(')');
            backtrack(res, string, open, close+1, n);
            string.deleteCharAt(string.length()-1);
        }
    }

    // 64. Minimum Path Sum
    public static int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        int[][] test1 = {{1,3,1},{1,5,1},{4,2,1}};
        int[][] test2 = {{1,2,3},{4,5,6}};
        System.out.println(minPathSum(test1));
    }
}
