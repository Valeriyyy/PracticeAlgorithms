package src.Classes;

import java.util.*;
import src.DataStructures.*;

public class Leetcode extends AbstractPractice {

    public Leetcode() {
        super();
    }

    public int[] q2099(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k + 1);
        for (int n : nums) {
            pq.offer(n);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        println(pq.size());
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : pq) {
            freq.merge(n, 1, Integer::sum);
        }
        int[] seq = new int[k];
        int i = 0;
        for (int n : nums) {
            if (freq.merge(n, -1, Integer::sum) >= 0) {
                seq[i] = n;
                i++;
            }
        }
        return seq;
    }

    // https://leetcode.com/problems/find-target-indices-after-sorting-array/
    public List<Integer> targetIndices(int[] nums, int target) {
        int count = 0, lessthan = 0;
        for (int n : nums) {
            System.out.println("this is num " + n);
            if (n == target) {
                count++;
                System.out.println("count " + count);
            }
            if (n < target) {
                lessthan++;
                System.out.println("lessthan " + lessthan);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(lessthan++);
        }
        return result;
    }

    // https://leetcode.com/problems/distribute-candies/
    public int distributeCandies(int[] candyType) {
        var uniqueCandies = new HashSet<Integer>();
        for (int i = 0; i < candyType.length; i++) {
            uniqueCandies.add(candyType[i]);
        }

        return uniqueCandies.size() >= candyType.length / 2 ? candyType.length / 2 : uniqueCandies.size();
    }

    public ListNode reverseLinkedList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }

    // https://leetcode.com/problems/add-two-numbers/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        int sum = 0;
        while (l1 != null || l2 != null) {
            sum /= 10;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
        }

        if (sum / 10 == 1) {
            curr.next = new ListNode(1);
        }
        return dummyHead.next;
    }

    // https://leetcode.com/problems/flood-fill/
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor)
            return image;
        fill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    public void fill(int[][] image, int sr, int sc, int color, int newColor) {
        if (sr < 0 || sc < 0 || sr > image.length || sc > image[0].length || image[sr][sc] != color)
            return;
        image[sr][sc] = newColor;
        fill(image, sr + 1, sc, color, newColor);
        fill(image, sr - 1, sc, color, newColor);
        fill(image, sr, sc + 1, color, newColor);
        fill(image, sr, sc - 1, color, newColor);
    }

    // https://leetcode.com/problems/sum-of-all-odd-length-subarrays/
    // https://www.youtube.com/watch?v=J5IIH35EBVE&t=644s
    public int sumOddLengthSubarrays(int[] arr) {
        int res = 0, n = arr.length;
        for (int i = 0; i < n; ++i) {
            println("(" + (i + 1) + " * " + (n - i) + " + 1 ) / 2 * " + arr[i] + " = "
                    + ((i + 1) * (n - i) + 1) / 2 * arr[i]);
            res += ((i + 1) * (n - i) + 1) / 2 * arr[i];
        }
        return res;
    }

    // https://leetcode.com/problems/coin-change/
    public int coinChangeIterative(int[] coins, int amount) {
        if (amount < 1)
            return 0;
        int[] dp = new int[amount + 1];
        int sum = 0;

        while (++sum <= amount) {
            int min = -1;
            for (int coin : coins) {
                if (sum >= coin && dp[sum - coin] != -1) {
                    int temp = dp[sum - coin] + 1;
                    min = min < 0 ? temp : (temp < min ? temp : min);
                }
            }
            dp[sum] = min;
        }
        return dp[amount];
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isBalancedHelper(root) != -1;
    }

    private int isBalancedHelper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = isBalancedHelper(root.left);
        int right = isBalancedHelper(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }

        return Math.max(left, right) + 1;
    }

    public long fib(int n) {
        if (n < 2)
            return n;
        long a = 0, b = 1;
        while (n-- > 0) {
            long sum = a + b;
            a = b;
            b = sum;
        }

        return a;
    }

    List<List<Integer>> combinationSumList;

    public List<List<Integer>> combinationSum(int target, int[] nums) {
        combinationSumList = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return combinationSumList;
        }
        dfs(nums, target, 0, 0, new ArrayList<>());
        return combinationSumList;
    }

    private void dfs(int[] candidates, int target, int sum, int idx, List<Integer> l) {
        if (target == sum) {
            combinationSumList.add(l);
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            if (sum + candidates[i] <= target) {
                List<Integer> newList = new ArrayList<>(l);
                newList.add(candidates[i]);
                dfs(candidates, target, sum + candidates[i], i, newList);
            }
        }
    }

    // https://leetcode.com/problems/valid-parentheses/
    public boolean isValid(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false;
        }

        var stack = new Stack<Character>();
        for (var c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }

        return stack.isEmpty();
    }

    // https:// leetcode.com/problems/uncommon-words-from-two-sentences/
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> m = new HashMap<>();
        for (String s : (s1 + " " + s2).split("\s")) {
            m.put(s, m.getOrDefault(s, 0) + 1);
        }
        List<String> res = new ArrayList<>();
        for (String s : m.keySet()) {
            if (m.get(s) <= 1) {
                res.add(s);
            }
        }

        return res.toArray(new String[0]);
    }

    // https://leetcode.com/problems/merge-two-sorted-lists/submissions/
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode newNode = new ListNode(0);
        ListNode curr = newNode;
        while (list1 != null && list2 != null) {
            int l1 = list1.val;
            int l2 = list2.val;
            if (l1 <= l2) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;

        }
        curr.next = list1 == null ? list2 : list1;
        return newNode.next;
    }

    // https://leetcode.com/problems/merge-sorted-array/
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = nums1.length - 1;
        while (i >= 0 && j >= 0) {
            int num1 = nums1[i];
            int num2 = nums2[j];
            if (num1 < num2) {
                nums1[k--] = nums2[j--];
            } else {
                nums1[k--] = nums1[i--];
            }
        }

        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    public int arraySign(int[] nums) {
        int sign = 1;
        for (int n : nums) {
            if (n == 0) {
                return 0;
            }
            if (n < 0) {
                sign = -sign;
            }
        }
        return sign;
    }

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        return null;
    }

    // https://leetcode.com/problems/convert-1d-array-into-2d-array/
    public int[][] construct2DArray(int[] original, int m, int n) {
        if (original.length != m * n)
            return new int[0][0];
        int[][] res = new int[m][n];
        for (int i = 0; i < original.length; i++) {
            println(i / n + " " + i % 2);
            res[i / n][i % n] = original[i];
        }
        return res;
    }
}
