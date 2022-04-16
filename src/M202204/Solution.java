package M202204;

import groovyjarjarantlr4.runtime.tree.Tree;

import java.util.*;

// Definition for singly-linked list.
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
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int p1 = 0, p2 = 0;
        List<int[]> res = new ArrayList<>();
        while(p1 < firstList.length && p2 < secondList.length){
            int lo = Math.max(firstList[p1][0], secondList[p2][0]);
            int hi = Math.min(firstList[p1][1], secondList[p2][1]);
            if(lo <= hi) res.add(new int[]{lo,hi});
            if(firstList[p1][1] < secondList[p2][1]) p1++;
            else p2++;
        }
        return res.toArray(new int[res.size()][]);
    }

    // 11. Container With Most Water
    public int maxArea(int[] height) {
        int n = height.length;
        int area = Math.min(height[n-1], height[0]) * (n - 1);
        int l = 0, r = n - 1;
        while(l < r){
            if(height[l] < height[r]) {
                l++;
                area = Math.max(area, Math.min(height[r], height[l]) * (r - l));
            }else{
                r--;
                area = Math.max(area, Math.min(height[r], height[l]) * (r - l));
            }
        }
        return area;
    }

    //438. Find All Anagrams in a String
    // *** required to consider
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> key = new HashMap<>();
        int pLen = p.length();
        for(char c : p.toCharArray()) key.put(c,0);
        Queue<Integer> qs = new LinkedList<>();
        char[] char_s = s.toCharArray();
        for(int i = 0; i < s.length(); i++){
            if(key.containsKey(char_s[i])) {
                if(key.get(char_s[i]) == 0){
                    qs.add(i);
                    key.put(char_s[i], 1);
                }
                else{
                    qs.remove();
                    continue;
                }
                if(qs.size() == pLen){
                    key.put(char_s[qs.element()], 0);
                    res.add(qs.remove());
                }
            }else{
                qs.clear();
                continue;
            }
        }
        System.out.println(res.toString());
        return null;
    }

    //322. Coin Change
    public int coinChange(int[] coins, int amount) {
        if(amount == 0) return 0;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount+1);
        for (int i = 1; i <= amount; i++) {
            for(int j = coins.length - 1; j >= 0; j--){
                if(i == coins[j]) dp[i] = 1;
                else if(i < coins[j]) continue;
                else{
                    if(dp[i - coins[j]] != amount + 1) dp[i] = Math.min(dp[i-coins[j]] + 1, dp[i]);
                }
            }
        }
        if(dp[amount] != amount + 1) return dp[amount];
        else return -1;
    }

    //04-15
    //91. Decode Ways
    // First Solution - low efficient
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

    public static int numDecodings(String s) {
        int n = s.length();
        if(n == 0 || s.charAt(0) == '0') return 0;
        if(n <= 2 && Integer.parseInt(s) <= 26) return n;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            if(s.charAt(i) != '0') dp[i] = dp[i-1];
            int twoDigits = Integer.parseInt(s.substring(i-2, i));
            if(twoDigits >= 10 && twoDigits <= 26) dp[i] += dp[i-2];
        }
        return dp[n];
    }

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
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if(p == null && q == null) return true;
            if(p == null || q == null) return false;
            if(p.val != q.val) return false;
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
     }

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
    public static void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] route = new int[m][n];
        int[][] mark = new int[m][n];
        for (int i = 0; i < m; i++) {
            if(board[i][0] == 'O') {
                dfs(board, i, 0, route, mark);
            }
            if(board[i][n-1] == 'O'){
                dfs(board, i, n-1, route, mark);
            }
        }
        for (int i = 0; i < n; i++) {
            if(board[0][i] == 'O') {
                dfs(board, 0, i, route, mark);
            }
            if (board[m-1][i] == 'O') {
                dfs(board, m-1, i, route, mark);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0]. length; j++) {
                if(board[i][j] == 'X') continue;
                else{
                    if(mark[i][j] != 1) board[i][j] = 'X';
                }
            }
        }
    }
    static void dfs(char[][] board, int a, int b, int[][] route, int[][] mark){
        if(a < 0 || b < 0 || a >= board.length || b>= board[0].length) return;
        if(route[a][b] == 1) return;
        route[a][b] = 1;
        if(board[a][b] == 'O') {
            mark[a][b] = 1;
            dfs(board, a-1, b, route, mark);
            dfs(board, a, b-1, route, mark);
            dfs(board, a+1, b, route, mark);
            dfs(board, a, b+1, route, mark);
        }
        else return;
    }

    public static void main(String[] args) {
        char[][] test = new char[][]{{'X','O','X','O','X','O'},{'O','X','O','X','O','X'},{'X','O','X','O','X','O'},{'O','X','O','X','O','X'}};
        System.out.println("-------start--------");
        for (int i = 0; i < test.length; i++) {
            for (int j = 0; j < test[0].length; j++) {
                System.out.print(test[i][j] + ", ");
            }
            System.out.println();
        }
        solve(test);
        System.out.println("-------finish------");
        for (int i = 0; i < test.length; i++) {
            for (int j = 0; j < test[0].length; j++) {
                System.out.print(test[i][j] + ", ");
            }
            System.out.println();
        }
    }
}
