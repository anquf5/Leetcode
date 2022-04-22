import java.util.*;

public class Challenge {

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


    public static void main(String[] args) {

    }
}
