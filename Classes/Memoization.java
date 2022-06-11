package Classes;

import java.util.*;

/**
 * Learning about dynamic programming and memoization from this video
 * https://www.youtube.com/watch?v=oBt53YbR9Kk
 */
public class Memoization extends AbstractPractice {
    private HashMap<Integer, Integer> fibMemo = new HashMap<Integer, Integer>();
    private HashMap<String, Long> gridTravelerMemo = new HashMap<String, Long>();
    private HashMap<Integer, Boolean> canSumMemo = new HashMap<Integer, Boolean>();

    public int fibMemoization(int n) {
        if (n == 0 || n == 1)
            return n;
        if (fibMemo.containsKey(n)) {
            return fibMemo.get(n);
        }

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
        long result = gridTravelerMemoization(m - 1, n) + gridTravelerMemoization(m, n - 1);
        gridTravelerMemo.put(key, result);

        return gridTravelerMemo.get(key);
    }

    public boolean canSum(int target, int[] nums) {
        if (canSumMemo.containsKey(target)) {
            return canSumMemo.get(target);
        }
        if (target == 0)
            return true;
        if (target < 0)
            return false;
        for (Integer num : nums) {
            int remainder = target - num;
            println(remainder);
            if (canSum(remainder, nums)) {
                canSumMemo.put(target, true);
                return true;
            }
        }
        canSumMemo.put(target, false);
        return false;
    }

    // https://www.youtube.com/watch?v=H9bfqozjoqs
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] memo = new int[max];
        Arrays.fill(memo, max);
        memo[0] = 0;
        for (int i = 0; i < max; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    memo[i] = Math.min(memo[i], memo[i - coin] + 1);
                }
            }
        }
        printArray(memo);
        return memo[amount] != max ? memo[amount] : memo[amount] - 1;
    }
}
