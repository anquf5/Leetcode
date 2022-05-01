package M202205;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    public static void main(String[] args) {
        System.out.println(countAndSay(4));
    }
}
