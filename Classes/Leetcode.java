package Classes;

import java.util.*;

import DataStructures.*;

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

    // public boolean isBalanced(TreeNode root) {

    // }

    // public int isBalancedHelper(TreeNode root) {

    // }

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

}
