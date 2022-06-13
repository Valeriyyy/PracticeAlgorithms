
import Classes.Leetcode;
import Classes.Memoization;

import java.util.*;

public class Program {
    public static void main(String[] args) {
        System.out.println("Hello World");
        var leet = new Leetcode();
        var memo = new Memoization();
    }

    public static <T> void printArray(int[] arr) {
        for (var item : arr) {
            System.out.println(item);
        }
    }

    public static <T> void printList(List<T> arr) {
        for (var item : arr) {
            System.out.println(item);
        }
    }

    public static <T> void println(T val) {
        System.out.println(val);
    }
}