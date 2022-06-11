package Classes;

import java.util.*;

public class Memoization extends AbstractPractice {
    private HashMap<Integer, Integer> fibMemo = new HashMap<Integer, Integer>();
    private HashMap<String, Long> gridTravelerMemo = new HashMap<String, Long>();

    public int fibMemoization(int n) {
        if (n == 0 || n == 1)
            return n;
        if (fibMemo.containsKey(n)) {
            println("grabbing memo " + n);
            return fibMemo.get(n);
        }

        println("computing fib(" + n + ")");
        int result = fibMemoization(n - 1) + fibMemoization(n - 2);
        fibMemo.put(n, result);
        return fibMemo.get(n);
    }

    public long gridTravelerMemoization(long m, long n) {
        var key = n + "," + m;
        if (gridTravelerMemo.containsKey(key)) {
            return gridTravelerMemo.get(key);
        }

        if (m == 1 && n == 1)
            return 1;
        if (m == 0 || n == 0)
            return 0;
        println(m + " " + n);
        long result = gridTravelerMemoization(m - 1, n) + gridTravelerMemoization(m, n - 1);
        gridTravelerMemo.put(key, result);

        return gridTravelerMemo.get(key);
    }
}
