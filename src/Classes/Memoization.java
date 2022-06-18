package src.Classes;

import java.util.*;

/**
 * Learning about dynamic programming and memoization from this video
 * https://www.youtube.com/watch?v=oBt53YbR9Kk
 */
public class Memoization extends AbstractPractice {
    private HashMap<Integer, Integer> fibMemo = new HashMap<Integer, Integer>();
    private HashMap<String, Long> gridTravelerMemo = new HashMap<String, Long>();
    private HashMap<Integer, Boolean> canSumMemo = new HashMap<Integer, Boolean>();
    private HashMap<Integer, List<Integer>> howSumMemo = new HashMap<Integer, List<Integer>>();
    private HashMap<Integer, List<Integer>> bestSumMemo = new HashMap<Integer, List<Integer>>();
    private HashMap<String, Integer> countConstruct = new HashMap<>();

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
            if (canSum(remainder, nums)) {
                canSumMemo.put(target, true);
                return true;
            }
        }
        canSumMemo.put(target, false);
        return false;
    }

    // https://www.johncanessa.com/2021/05/31/how-sum/
    public List<Integer> howSum(int target, int[] nums) {
        if (howSumMemo.containsKey(target))
            return howSumMemo.get(target);
        if (target == 0)
            return new ArrayList<Integer>();
        if (target < 0)
            return null;
        List<Integer> lst = null;
        for (int i = 0; i < nums.length && lst == null; i++) {
            int num = nums[i];
            int remainder = target - num;
            if (!howSumMemo.containsKey(remainder)) {
                howSumMemo.put(remainder, howSum(remainder, nums));
            }
            lst = howSumMemo.get(remainder);

            if (lst != null) {
                lst.add(num);
            }
        }

        return lst;
    }

    public List<Integer> bestSum(int target, int[] nums) {
        if (bestSumMemo.containsKey(target))
            return bestSumMemo.get(target);
        if (target == 0)
            return new ArrayList<>();
        if (target < 0)
            return null;
        List<Integer> shortestCombination = null;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int remainder = target - num;
            List<Integer> remainderCombination = bestSum(remainder, nums);
            if (remainderCombination != null) {
                List<Integer> combination = new ArrayList<>(remainderCombination);
                combination.add(num);
                if (shortestCombination == null || combination.size() < shortestCombination.size()) {
                    shortestCombination = combination;
                    println("found a shorter combo");
                    printList(shortestCombination);
                }
            }
            // println("");
        }
        bestSumMemo.put(target, shortestCombination);
        return shortestCombination;
    }

    public int coinChangeMemoization(int amount, int[] coins) {
        if (amount < 1)
            return 0;
        return helper(coins, amount, new int[amount]);
    }

    // rem: remaining coins after the last step; count[rem]:
    // minimum number of coins to sum up to rem
    private int helper(int[] coins, int rem, int[] count) {
        if (rem < 0)
            return -1; // not valid
        if (rem == 0)
            return 0; // completed
        if (count[rem - 1] != 0)
            return count[rem - 1]; // already computed, so reuse
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = helper(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    public int countConstruct(String target, String[] wordBank) {
        if(countConstruct.containsKey(target)) return countConstruct.get(target);
        if(target.equals("")) return 1;

        int totalCount = 0;
        for(String word : wordBank) {
            if(target.indexOf(word) == 0) {
                String prefix = word;
                String suffix = target.substring(prefix.length());
                totalCount += countConstruct(suffix, wordBank);
            }
        }
        countConstruct.put(target, totalCount);
        return totalCount;
    }
}
