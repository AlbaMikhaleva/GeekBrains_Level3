package lesson1;

import java.util.Arrays;

public class SwapElements {

    public static void main(String[] args) {

        String[] str = {"a", "b", "c", "d" };
        Integer[] integ = {1, 2, 3, 4, 5};
        swap(str, 2, 3);
        swap(integ,1, 4 );
    }


    public static void swap(Object[] array, int i, int j) {
        System.out.println("Начальный массив: " + Arrays.toString(array));
        Object n = array[i];
        array[i] = array[j];
        array[j] = n;
        System.out.println("Измененный массив: " + Arrays.toString(array));
    }

}
