package src.Classes;

import java.util.*;

/**
 * Learning about dynamic programming and memoization from this video
 * https://www.youtube.com/watch?v=oBt53YbR9Kk
 */
public class DynamicProgramming extends AbstractPractice {
    private HashMap<Integer, Integer> fibMemo = new HashMap<Integer, Integer>();
    private HashMap<String, Long> gridTravelerMemo = new HashMap<String, Long>();
    private HashMap<Integer, Boolean> canSumMemo = new HashMap<Integer, Boolean>();
    private HashMap<Integer, List<Integer>> howSumMemo = new HashMap<Integer, List<Integer>>();
    private HashMap<Integer, List<Integer>> bestSumMemo = new HashMap<Integer, List<Integer>>();
    private HashMap<String, Integer> countConstruct = new HashMap<>();
    private HashMap<String, List<List<String>>> allConstructMemo = new HashMap<>();

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
        if (countConstruct.containsKey(target))
            return countConstruct.get(target);
        if (target.equals(""))
            return 1;

        int totalCount = 0;
        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                String prefix = word;
                String suffix = target.substring(prefix.length());
                totalCount += countConstruct(suffix, wordBank);
            }
        }
        countConstruct.put(target, totalCount);
        return totalCount;
    }

    public List<List<String>> allConstruct(String target, String[] wordBank) {
        if (allConstructMemo.containsKey(target))
            return allConstructMemo.get(target);
        if (target.length() == 0)
            return new ArrayList<>();
        List<List<String>> result = new ArrayList<>();
        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                String suffix = target.substring(word.length());
                List<List<String>> suffixWays = allConstruct(suffix, wordBank);
                List<List<String>> targetWays = new ArrayList<>();

                for (int i = 0; i < suffixWays.size(); i++) {
                    List<String> tmp = new ArrayList<>(suffixWays.get(i));
                    tmp.add(0, word);
                }

                for (int i = 0; i < targetWays.size(); i++) {
                    result.add(new ArrayList<>(targetWays.get(i)));
                }
            }
        }

        allConstructMemo.put(target, result);
        return result;
    }

    public int fibTabulation(int n) {
        int[] table = new int[n + 3];
        table[1] = 1;
        for (int i = 0; i <= n; i++) {
            int current = table[i];
            if (i + 1 <= table.length)
                table[i + 1] += current;
            if (i + 2 < table.length)
                table[i + 2] += current;
        }

        return table[n];
    }

    public int gridTravelTabulation(int x, int y) {
        int[][] grid = new int[x + 1][y + 1];
        grid[1][1] = 1;
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                int current = grid[i][j];
                if (j + 1 <= y)
                    grid[i][j + 1] += current;
                if (i + 1 <= x)
                    grid[i + 1][j] += current;
            }
        }

        return grid[x][y];
    }

    public boolean canSumTabulation(int targetSum, int[] numbers) {
        if (targetSum == 0)
            return true;
        boolean[] arr = new boolean[targetSum + 1];
        arr[0] = true;

        for (int i = 0; i <= targetSum; i++) {
            if (arr[i] == true) {
                for (int num : numbers) {
                    if (i + num <= targetSum) {
                        arr[i + num] = true;
                    }
                }
            }
        }
        return arr[targetSum];
    }

    public int[] howSumTabulation(int targetSum, int[] numbers) {
        if (targetSum == 0)
            return null;
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] table = new ArrayList[targetSum + 1];

        table[0] = new ArrayList<Integer>();

        for (int i = 0; i < numbers.length; i++) {
            ArrayList<Integer> al = new ArrayList<Integer>();
            int nums = numbers[i];
            al.add(nums);
            table[nums] = al;
        }

        for (int i = 1; i <= targetSum; i++) {
            if (table[i] == null)
                continue;

            for (int j = 0; j < numbers.length; j++) {
                int num = numbers[j];
                int ndx = i + num;
                if (ndx > targetSum)
                    continue;
                if (table[ndx] == null)
                    table[ndx] = new ArrayList<Integer>();

                ArrayList<Integer> src = table[i];
                ArrayList<Integer> dst = table[ndx];
                dst.clear();
                dst.addAll(src);

                dst.add(num);

                if (ndx == targetSum) {
                    int[] arr = dst.stream().mapToInt(x -> x).toArray();
                    return arr;
                }
            }
        }
        if (table[targetSum] == null)
            return null;
        ArrayList<Integer> lst = table[targetSum];
        int[] arr = lst.stream().mapToInt(x -> x).toArray();
        return arr;
    }

    public int[] bestSumTabulation(int targetSum, int[] numbers) {
        if (targetSum == 0) return null;
    
        // **** initialization ****
        List<List<Integer>> table = new ArrayList<List<Integer>>();
        List<Integer> lst = new ArrayList<>();
    
        table.add(lst);
        for (int i = 1; i <= targetSum; i++)
            table.add(null);

        for (int i = 0; i <= targetSum; i++) {
            if (table.get(i) == null)
                continue;

            for (int j = 0; j < numbers.length; j++) {
                int num = numbers[j];
                int ndx = i + num;

                if (ndx > targetSum)
                    continue;

                lst = table.get(ndx);
                if (lst == null)
                    lst = new ArrayList<>();

                List<Integer> src = table.get(i);
                List<Integer> dst = table.get(ndx);

                dst = new ArrayList<Integer>();
                dst.addAll(src);
                dst.add(num);

                if (table.get(ndx) == null || dst.size() < table.get(ndx).size()) {
                    table.remove(ndx);
                    table.add(ndx, dst);
                }
            }
        }

        if (table.get(targetSum) == null)
            return null;
        else
            return table.get(targetSum).stream().mapToInt(x -> x).toArray();
    }
}
