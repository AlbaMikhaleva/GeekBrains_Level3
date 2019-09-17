package lesson1.taskAboutFruit;

import lesson1.taskAboutFruit.fruits.Fruit;
import java.util.ArrayList;


public class Box<T extends Fruit> {

    ArrayList<T> box = new ArrayList<>();

    public void addFruitToBox(T fruit, int count) {
        for (int i = 0; i < count; i++) {
            box.add(fruit);
        }
    }

    public float getWeight() {
        float weight = 0.0f;
        for (T o : box) {
            weight += o.getWeight();
        }
        return weight;
    }

    public boolean compareBox(Box otherBox) {
        if (getWeight() == otherBox.getWeight()) {
            return true;
        } else {
            return false;
        }
    }

    public void shiftFruit(Box <T> otherBox) {
        otherBox.box.addAll(box);
        box.clear();
        }
    }

