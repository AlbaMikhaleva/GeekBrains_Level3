package lesson1.taskAboutFruit;


import lesson1.taskAboutFruit.fruits.Apple;
import lesson1.taskAboutFruit.fruits.Orange;


public class MainClass {
    public static void main(String[] args) {

        Box<Apple> box1 = new Box<>();
        Box<Apple> box2 = new Box<>();
        Box<Orange> box3 = new Box<>();
        Box<Orange> box4 = new Box<>();
        Apple apple = new Apple();
        Orange orange = new Orange();


        box1.addFruitToBox(apple, 5);
        box2.addFruitToBox(apple, 5);
        box3.addFruitToBox(orange, 20);
        box4.addFruitToBox(orange, 5);


        System.out.println("Вес коробок:  box1 = " + box1.getWeight()+ "; box2 = " + box2.getWeight()+ "; box3 = " + box3.getWeight()+ "; box4 = " + box4.getWeight());
        System.out.println();
        System.out.println(box1.compareBox(box4));
        System.out.println(box1.compareBox(box2));
        System.out.println();

        box1.shiftFruit(box2);
        System.out.println("Вес коробок:  box1 = " + box1.getWeight()+ "; box2 = " + box2.getWeight());
    }

}
