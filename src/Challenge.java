import java.util.*;

public class Challenge {
    public static int solution(int[] A, String[] D) {
        // write your code in Java SE 8
        int num = A.length;
        int[][] account = new int[12][2];
        // 0 - the payment number of this month
        // 1 - the total payment of this month
        Set<Integer> deducMon = new HashSet<>();
        for (int i = 0; i < num; i++) {
            int m = Integer.valueOf(D[i].split("-")[1]) - 1;
            if(A[i] < 0) {
                account[m][0]++;
                account[m][1] += A[i];
            }
            if(account[m][0] >= 3 && account[m][1] <= -100) {
                deducMon.add(m);
            }
        }
        System.out.println(deducMon.size());
        return Arrays.stream(A).sum() - (5 * (12 - deducMon.size()));
    }
    public static int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        List<Integer> list = new ArrayList<>();
        findPeak(nums,l,r,list);
        return (int)list.toArray()[0];
    }

    static void findPeak(int nums[], int li, int ri, List<Integer> list){
        if(list.size()>0) return;
        while(li < ri){
            int mi = (li + ri) / 2;
            if(mi == 0 || mi == nums.length-1) return;
            if(nums[mi] > nums[mi-1] && nums[mi] > nums[mi+1]){
                list.add(mi);
                return;
            }else{
                findPeak(nums, li, mi, list);
                findPeak(nums, mi, ri, list);
            }
        }
        return;
    }

    public static int minReorder(int n, int[][] connections) {
        HashSet<Integer>[] start = new HashSet[n];
        HashSet<Integer>[] end = new HashSet[n];
        for (int i = 0; i < n; i++) {
            start[i] = new HashSet<>();
            end[i] = new HashSet<>();
        }

        for (int i = 0; i < connections.length; i++) {
            int st = connections[i][0];
            int en = connections[i][1];
            start[st].add(en);
            end[en].add(st);
        }
        List<Integer> num = new ArrayList<>(); // The times of change
        boolean[] route = new boolean[n]; // The visited vertex
        findRoad(0, num, route, start, end);
        return num.size();
    }

    static void findRoad(int index
            , List<Integer> num
            , boolean[] route
            , HashSet<Integer>[] start
            , HashSet<Integer>[] end){
        if(route[index]) return;
        route[index] = true;
        for(Integer in : end[index]){
            start[in].remove(index);
            findRoad(in, num, route, start, end);
        }
        for(Integer in : start[index]){
            end[in].remove(index);
            num.add(index);
            findRoad(in, num, route, start, end);
        }
    }

    public static int groupDivision(List<Integer> levels, int maxSpread) {
        // Write your code here
        int n = levels.size();
        int[] student = new int[n];
        for (int i = 0; i < n; i++) {
            student[i] = levels.get(i);
        }
        Arrays.sort(student);
        int lo = 0, hi = 1;
        int res = 0;
        while(hi < n){
            if(student[hi] - student[lo] == maxSpread){
                res++;
                lo = hi + 1;
                hi++;
            }
            if(student[hi] - student[lo] > maxSpread){
                res++;
                lo = hi;
                hi++;
            }
            while(student[hi] - student[lo] < maxSpread){
                hi++;
            }
        }
        return res;
    }
    public static int numPaths(List<List<Integer>> warehouse) {
        // Write your code here
        int m = warehouse.size();
        int n = warehouse.get(0).size();
        int[][] dp = new int[m][n];
        if(warehouse.get(0).get(0) == 0){
            dp[0][0] = 0;
        }
        else dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            if(warehouse.get(i).get(0) == 1){
                dp[i][0] = dp[i-1][0];
            }
        }
        for (int i = 1; i < n; i++) {
            if(warehouse.get(0).get(i) == 1){
                dp[0][i] = dp[0][i-1];
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(warehouse.get(i).get(j) == 0) dp[i][j] = 0;
                else dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    public static int ArrayChallenge(int[] arr) {
        // code goes here
        int N = arr[0];
        int len = arr.length - 1;
        int[] median = new int[len];
        int[] realArr = Arrays.copyOfRange(arr,1,len + 1);
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        median[0] = realArr[0];
        list.add(realArr[0]);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < len; i++) {
            list.add(realArr[i]);
            if(list.size() > 3) list.remove(0);
            median[i] = getMedian(new ArrayList<>(queue));
        }
        for (int i = 0; i < len; i++) {
            System.out.println(median[i]);
        }
        return 0;
    }
    static int getMedian(List<Integer> subArr){
        Collections.sort(subArr);
        int subLen = subArr.size();
        if(subLen % 2 == 0){
            return (subArr.get(subLen / 2) + subArr.get(subLen / 2 - 1)) / 2;
        }else{
            return subArr.get(subLen / 2);
        }
    }


    public static void main(String[] args) {
        int[] test = {3, 1, 3, 5, 10, 6, 4, 3, 1};
        ArrayChallenge(test);

    }
}
