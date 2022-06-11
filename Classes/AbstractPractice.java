package Classes;

import java.util.*;

public abstract class AbstractPractice {
    public static <T> void printArray(List<T> arr) {
        for (var item : arr) {
            System.out.println(item);
        }
    }

    public static <T> void println(T val) {
        System.out.println(val);
    }
}
