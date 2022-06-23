package src.Classes;

import java.util.*;

public abstract class AbstractPractice {
    public static <T> void printArray(int[] arr) {
        for (var item : arr) {
            print(item + " ");
        }
    }

    public static <T> void printList(List<T> arr) {
        for (var item : arr) {
            print(item + " ");
        }
    }

    public static <T> void print(T val) {
        System.out.print(val);
    }

    public static <T> void println(T val) {
        System.out.println(val);
    }
}
