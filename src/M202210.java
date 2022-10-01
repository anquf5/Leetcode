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


    public static void main(String[] args) {
        int[][] test = {{0,1},{0,2},{1,3},{1,4},{3,4}};
    }
}
