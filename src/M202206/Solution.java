package M202206;

import java.util.Arrays;

public class Solution {

    //06-19
    // 396. Rotate Function
//    public int maxRotateFunction(int[] nums) {
//        int[] dp = new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            dp[0] += i * nums[i];
//        }
//        for (int i = 1; i < nums.length; i++) {
//            for (int j = 0; j < nums.length; j++) {
//
//            }
//        }
//    }
//    public int solution(int[] A) {
//        // write your code in Java SE 8
//        Arrays.sort(A);
//        int res = 0;
//        for (int i = 1; i < A.length; i++) {
//            if(A[i] <= 0) continue;
//            if (A[i] - A[i - 1] > 1) {
//                if (A[i] * A[i-1] >= 0) return A[i-1] + 1;
//                else {
//                    if (A[i] == 1) continue;
//                    else return 1;
//                }
//            }
//        }
//        return A[A.length-1] <= 0 ? 1 : A[A.length-1] + 1;
//    }

//    public static int solution(int[] A) {
//        // write your code in Java SE 8
//        int n = A.length;
//        int l = 0, res = -1;
//        while (l < n){
//            if (A[l] < 0) l++;
//            else {
//                int r = l, sum = 0;
//                while(r < n && A[r] >= 0){
//                    sum += A[r];
//                    r++;
//                }
//                res = Math.max(res, sum);
//                l = r + 1;
//            }
//        }
//        return res;
//    }
//    static int res = 0;
//    public static int solution(int[] A) {
//        // write your code in Java SE 8
//        boolean[] r = new boolean[A.length];
//        for (int i = 0; i < A.length; i++) {
//            dfs(i, A, 0, r);
//        }
//        return res;
//    }
//
//    static void dfs(int i, int[] A, int l, boolean[] r){
//        if (r[i]) {
//            res = Math.max(res, l);
//            return;
//        }
//        else{
//            r[i] = true;
//            l++;
//            dfs(A[i], A, l, r);
//        }
//    }

    public int solution(String S) {
        // write your code in Java SE 8
        int start = 0;
        while(start < S.length()){
            if (S.charAt(start) == '1') break;
            start++;
        }
        int odd = 0, even = 0;
        for (int i = start; i < S.length(); i++){
            if (S.charAt(i) == '1') odd++;
            if (S.charAt(i) == '0') even++;
        }
        return odd * 2 + even - 1;
    }

    public static void main(String[] args) {
        int[] test = {5, 4, 0, 3, 1, 6, 2};
    }
}
