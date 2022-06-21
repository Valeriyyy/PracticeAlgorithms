package src.Classes;

import java.util.*;
import src.DataStructures.*;

public class Leetcode extends AbstractPractice {

    public Leetcode() {
        super();
    }

    // https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum/
    public int[] maxSubsequence(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k + 1);
        for (int n : nums) {
            pq.offer(n);
            if (pq.size() > k) {
                println(pq.poll());
            }
        }
        println("pq size " + pq.size());
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : pq) {
            int f = freq.merge(n, 1, Integer::sum);
            println("f " + n + " " + f);
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
        // count hold how many times the target was encounterd
        // lessThan holds how many numbers are going to be before
        // the target in a sorted list and will be the starting index
        // of the target(s)
        int count = 0, lessthan = 0;
        // loop through numbers and keep track of how many numbers are
        // less than the target number so we can count the index
        // of the target if the list was sorted.
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
        println("after loop less than " + lessthan);
        List<Integer> result = new ArrayList<>();
        // loop up to how many times the target number was encountered in the loop
        // then increment lessThan to equal the would be indexes
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

        return uniqueCandies.size() >= candyType.length / 2
                ? candyType.length / 2
                : uniqueCandies.size();
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

    public boolean ransomNote(String ransomNote, String magazine) {
        int[] arr = new int[128];
        for (char c : magazine.toCharArray()) {
            arr[c]++;
        }

        for (char c : ransomNote.toCharArray()) {
            if (--arr[c] < 0) {
                return false;
            }
        }

        return true;
    }

    // https://leetcode.com/problems/top-k-frequent-elements/
    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 1)
            return nums;
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (m.containsKey(num)) {
                int val = m.get(num);
                val++;
                m.put(num, val);
            } else {
                m.put(num, 0);
            }
        }
        int length = nums.length + 1;
        ArrayList<Integer>[] bucket = new ArrayList[length];
        for (Integer key : m.keySet()) {
            int freq = m.get(key);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<Integer>();
            }
            bucket[freq].add(key);
        }
        List<Integer> res = new ArrayList<>();
        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }

        return res.stream()
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    // https:// leetcode.com/problems/reverse-linked-list/
    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }

        return newHead;
    }

    // https://leetcode.com/problems/find-smallest-letter-greater-than-target/
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;

        int lo = 0, hi = n;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (letters[mid] > target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return letters[lo % n];
    }

    // https://leetcode.com/problems/time-needed-to-buy-tickets/
    public int timeRequiredToBuy(int[] tickets, int k) {
        int timeTaken = 0;

        int index = 0;
        while (tickets[k] != 0) {
            println(index);
            if (k == index && tickets[k] == 0) {
                return timeTaken;
            }
            if (tickets[index] == 0) {
                if (index + 1 == tickets.length) {
                    index = 0;
                } else {
                    index++;
                }
                continue;
            } else {
                tickets[index] -= 1;
                timeTaken++;
            }
            if (index + 1 == tickets.length) {
                index = 0;
            } else {
                index++;
            }
        }

        return timeTaken;
    }

    // https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2))
            return true;
        if (s1.length() != s2.length())
            return false;
        List<Integer> unequalCharacters = new ArrayList<>();
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i))
                unequalCharacters.add(i);
            if (unequalCharacters.size() > 2)
                return false; // added this line to short circuit the loop
        }
        return unequalCharacters.size() == 0 || (unequalCharacters.size() == 2
                && s1.charAt(unequalCharacters.get(0)) == s2.charAt(unequalCharacters.get(1))
                && s1.charAt(unequalCharacters.get(1)) == s2.charAt(unequalCharacters.get(0)));
    }

    // https://leetcode.com/problems/buddy-strings/
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length())
            return false;
        if (s.equals(goal)) {
            // if the strings are equal, create a set
            // to hold the unique characters of s
            Set<Character> chars = new HashSet<Character>();
            for (char c : s.toCharArray())
                chars.add(c);
            // if there are less unique characters than
            // there is the length of s, then it can be
            // swapped
            return chars.size() < s.length();
        }
        List<Integer> dif = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                dif.add(i);
            }
        }
        // return true if the difference between the strings is just two characters
        // and if the two strings are mirros of each other i.e s = ab and goal = ba
        return dif.size() == 2 && s.charAt(dif.get(0)) == goal.charAt(dif.get(1))
                && s.charAt(dif.get(1)) == goal.charAt(dif.get(0));
    }

    // https://leetcode.com/problems/lexicographical-numbers/
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            println("og " + i);
            lexicalOrderDfs(i, n, res);
        }
        return res;
    }

    private void lexicalOrderDfs(int cur, int n, List<Integer> res) {
        if (cur > n) {
            return;
        } else {
            res.add(cur);
            print(cur + " ");
            for (int i = 0; i < 10; i++) {
                if (10 * cur + i > n) {
                    print("returning\n");
                    return;
                }
                println("failed check");
                lexicalOrderDfs(10 * cur + i, n, res);
            }
        }
    }

    // https://leetcode.com/problems/climbing-stairs/
    public int climbStairs(int n) {
        if (n <= 2)
            return n;
        int a = 0, b = 1;
        while (n-- > 0) {
            int sum = a + b;
            a = b;
            b = sum;
            println(b);
        }

        // in this problem we are returning b in comparison to a in
        // the regular fibonacci sequence because we pretty much just need 
        // the sum of the previous and the second to previous number
        // of steps required to get to the step.
        return b;
    }
}
