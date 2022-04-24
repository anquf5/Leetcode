import java.util.*;

public class Challenge {

//    public static int minReorder(int n, int[][] connections) {
//        HashSet<Integer>[] start = new HashSet[n];
//        HashSet<Integer>[] end = new HashSet[n];
//        for (int i = 0; i < n; i++) {
//            start[i] = new HashSet<>();
//            end[i] = new HashSet<>();
//        }
//
//        for (int i = 0; i < connections.length; i++) {
//            int st = connections[i][0];
//            int en = connections[i][1];
//            start[st].add(en);
//            end[en].add(st);
//        }
//        List<Integer> num = new ArrayList<>(); // The times of change
//        boolean[] route = new boolean[n]; // The visited vertex
//        findRoad(0, num, route, start, end);
//        return num.size();
//    }
//
//    static void findRoad(int index
//            , List<Integer> num
//            , boolean[] route
//            , HashSet<Integer>[] start
//            , HashSet<Integer>[] end){
//        if(route[index]) return;
//        route[index] = true;
//        for(Integer in : end[index]){
//            start[in].remove(index);
//            findRoad(in, num, route, start, end);
//        }
//        for(Integer in : start[index]){
//            end[in].remove(index);
//            num.add(index);
//            findRoad(in, num, route, start, end);
//        }
//    }
//
//    public boolean findTarget(TreeNode root, int k) {
//        Set<Integer> set = new HashSet<>();
//        Set<Integer> key = new HashSet<>();
//        search(root, k, set, key);
//        return key.size() > 0;
//    }
//
//    void search(TreeNode root, int k, Set<Integer> set, Set<Integer> key){
//        if(root != null){
//            if(set.contains(root.val)) {
//                key.add(root.val);
//                return;
//            }
//            set.add(k-root.val);
//            if(root.left != null) search(root.left, k, set, key);
//            if(root.right != null)search(root.right, k, set, key);
//        }
//        return;
//    }

    public static String gcdOfStrings(String str1, String str2) {
        String gcd = "";
        int n = str1.length() < str2.length() ? str1.length() : str2.length();
        if(str1.substring(0,n).equals(str2.substring(0,n))) gcd = str1.substring(0,n);
        else return "";
        while(n > 0){
            if(str1.length() % n == 0 && str2.length() % n == 0){
                if(compare(str1, gcd.substring(0,n)) && compare(str2, gcd.substring(0,n))) return gcd.substring(0,n);
                else n--;
            }
            else n--;
        }
        return "";
    }

    static boolean compare(String str, String gcd){
        String com = "";
        int n = str.length() / gcd.length();
        for (int i = 0; i < n; i++) {
            com += gcd + "";
        }
        return com.equals(str);
    }

    public static int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String email : emails) {
            String local = email.split("@")[0].split("[+]")[0].replace(".","");
            String domain = email.split("@")[1];
            set.add(""+local+"@"+domain);
        }
        return set.size();
    }

    public static int totalFruit(int[] fruits) {
        int n = fruits.length;
        if(n < 3) return n;
        Set<Integer> set = new HashSet<>();
        int i = 0;
        int max = 0;
        int second = 0;
        while(i < n){
            if(!set.contains(fruits[i])){
                set.add(fruits[i]);
            }
            int j = i + 1;
            while(j < n){
                if(set.contains(fruits[j])) {
                    j++;
                    if(j == n) return Math.max(j-i,max);
                }
                else{
                    set.add(fruits[j]);
                    if(set.size() == 3){
                        set.remove(fruits[i]);
                        max = Math.max(j - i, max);
                        i = second;
                        set.remove(fruits[j]);
                        break;
                    }
                    second = j;
                    j++;
                }
            }
        }
        return max;
    }
    
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
        }
        return dp[n];
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root.left == null && root.right == null) return 0;
        Set<Integer> leftLeaf = new HashSet<>();
        findLeftLeaf(root, leftLeaf);
        while(root.right != null){
            if(root.left == null && root.right == null) leftLeaf.remove(root.val);
            else root = root.right;
        }
        return leftLeaf.stream().mapToInt(Integer::intValue).sum();

    }

    void findLeftLeaf(TreeNode root, Set<Integer> list){
        if(root == null) return;
        if(root.left != null) findLeftLeaf(root.left, list);
        if(root.right != null && root.right.left != null) findLeftLeaf(root.right.left, list);
        if(root.right != null && root.right.left == null && root.right.right == null) return;
        if(root.left == null && root.right == null) list.add(root.val);
    }

    public static String removeOuterParentheses(String s) {
        Queue<Character> queue = new LinkedList<>();
        int open = 0;
        int close = 0;
        String res = "";
        for (char c : s.toCharArray()) {
            if(c == '(') {
                open++;
                queue.add(c);
            }
            if(c == ')') {
                close++;
                queue.add(c);
            }
            if(open == close){
                String temp = "";
                while(queue.size() > 0){
                    temp += queue.remove();
                }
                res += temp.substring(1,temp.length()-1);
            }
        }
        return res;
    }

    public static boolean divisorGame(int n) {
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        dp[1] = false;
        for (int i = 2; i <= n; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j < i; j++) {
                if(i % j == 0) list.add(j);
            }
            int num = 0;
            for (Integer j : list) {
                if(dp[i-j]) num++;
            }
            if (num == list.size()) dp[i] = false;
            else dp[i] = true;
        }
        return dp[n];
    }


    public static void main(String[] args) {
        System.out.println(divisorGame(2));

    }
}

class TreeNode {
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
}
