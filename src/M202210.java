import java.util.*;

public class M202210 {
    //49. Group Anagrams
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] c = strs[i].toCharArray();
            Arrays.sort(c);
            String s = String.valueOf(c);
            if (map.containsKey(s)){
                int index = map.get(s);
                res.get(index).add(strs[i]);
            }
            else{
                res.add(new LinkedList<>());
                int index = res.size() - 1;
                res.get(index).add(strs[i]);
                map.put(s, index);
            }
        }
        return res;
    }

    //28. Find the Index of the First Occurrence in a String
    public static int strStr(String haystack, String needle) {
        int s1 = 0;
        while(s1 <= haystack.length() - needle.length()){
            if (haystack.charAt(s1) == needle.charAt(0)){
                if (haystack.substring(s1, s1+ needle.length()).equals(needle)){
                    return s1;
                }
            }
            s1++;
        }
        return -1;
    }

    //721. Accounts Merge
    Set<String> visited = new HashSet<>();
    Map<String, List<String>> graph = new HashMap<>();
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        for (List<String> account: accounts) {
            int size = account.size();
            String firstMail = account.get(1);
            if (!graph.containsKey(firstMail)) {
                graph.put(firstMail, new ArrayList<>());
            }
            for (int i = 2; i < size; i++) {
                String currentMail = account.get(i);
                graph.get(firstMail).add(currentMail);
                if (!graph.containsKey(currentMail)){
                    graph.put(currentMail, new ArrayList<>());
                }
                graph.get(currentMail).add(firstMail);
            }
        }

        List<List<String>> mergedAccounts = new ArrayList<>();
        for (List<String> account: accounts){
            String name = account.get(0);
            String accountFirstEmail = account.get(1);

            if (!visited.contains(accountFirstEmail)){
                List<String> mergedAccount = new ArrayList<>();
                mergedAccount.add(name);
                dfs(mergedAccount, accountFirstEmail);
                Collections.sort(mergedAccount.subList(1, mergedAccount.size()));
                mergedAccounts.add(mergedAccount);
            }
        }
        return mergedAccounts;
    }

    void dfs(List<String> mergedAccount, String email){
        visited.add(email);
        mergedAccount.add(email);
        if (!graph.containsKey(email)){
            return;
        }

        for (String neighbor: graph.get(email)){
            if (!visited.contains(neighbor)){
                dfs(mergedAccount, neighbor);
            }
        }
    }

    public int solution(String S) {
        // write your code in Java SE 8
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            int num = map.getOrDefault(S.charAt(i), 0);
            map.put(S.charAt(i), num+1);
        }
        int max = Math.max(map.get('^'), map.get('v'));
        max = Math.max(max, map.get('<'));
        max = Math.max(max, map.get('>'));
        return S.length()-max;

    }

    public static int solution(int[] A) {
        // write your code in Java SE 8
        if (A.length < 3) return 0;
        int l = 0, r = 1;
        int res = 0;
        int gap = A[r] - A[l];
        while (r < A.length){
            if (A[r] - A[r - 1] == gap){
                r++;
            }
            else{
                if (r - l > 2){
                    res += (r - l - 1) * (r - l - 2) / 2;
                }
                l = r - 1;
                gap = A[r] - A[l];
            }
        }
        if (r == A.length && r - l > 2) res += (r - l - 1) * (r - l - 2) / 2;
        return res;
    }

    //1584. Min Cost to Connect All Points
    public static void main(String args[]) {
        int[] test = {-1, 1, 3, 3, 3, 2, 3, 2, 1, 0};
        int[] test1 = {2,2,2,2,2,2};
        System.out.println(solution(test));
    }


}
