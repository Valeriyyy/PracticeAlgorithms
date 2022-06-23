package src;

import src.Classes.Cracking;
import src.Classes.Leetcode;
import src.Classes.DynamicProgramming;

import java.util.*;

public class Program {
    public static void main(String[] args) {
        System.out.println("Hello World");
        var leet = new Leetcode();
        var dp = new DynamicProgramming();
        var cracking = new Cracking();
        printArray(dp.bestSumTabulation(122, new int[] { 6, 3, 5, 25 }));

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