package Classes;

import java.util.*;

public abstract class AbstractPractice {
    public static <T> void printArray(int[] arr) {
        printList(Arrays.asList(arr));
    }

    public static <T> void printList(List<T> arr) {
        for (var item : arr) {
            println(item);
        }
    }

    public static <T> void println(T val) {
        System.out.println(val);
    }
}
