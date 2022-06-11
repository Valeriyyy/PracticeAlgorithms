
// import Classes.Leetcode;
import Classes.Memoization;

import java.util.*;

public class Program {
    public static void main(String[] args) {
        System.out.println("Hello World");
        // var leet = new Leetcode();
        var memo = new Memoization();
        var numbers = new int[] { 1, 3, 4, 5 };
        var res = memo.coinChange(numbers, 17);

        println(res);
    }

    public static <T> void printArray(List<T> arr) {
        for (var item : arr) {
            System.out.println(item);
        }
    }

    public static <T> void println(T val) {
        System.out.println(val);
    }
}