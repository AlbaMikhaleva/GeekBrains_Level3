package lesson1;

import java.util.ArrayList;
import java.util.Arrays;


public class СonvertToArrayList {

    public static void main(String[] args) {
        String [] arr = {"a", "b", "c", "d", "e"};
        arrayToArrayList(arr);
    }

    public static <Item> void arrayToArrayList(Item[] arr) {
        ArrayList<Item> arrayList = new ArrayList<Item>(Arrays.asList(arr));
        System.out.println("Результат преобразования: " + arrayList);
    }
}
