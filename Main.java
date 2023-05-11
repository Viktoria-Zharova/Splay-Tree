import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[10000];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10000);
        }
        SplayTree tree = new SplayTree();
        double sumForTime = 0;
        double sumForCounter = 0;
        for (int x: arr) {
            long startTime = System.nanoTime();
            tree.insert(x);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            sumForTime += duration;
            sumForCounter += tree.counter;
        }
        System.out.println("Среднее время операции ДОБАВЛЕНИЕ " + (sumForTime / arr.length));
        System.out.println("Среднее количество итераций на ДОБАВЛЕНИЕ " + (sumForCounter / arr.length));
        sumForTime = 0;
        sumForCounter = 0;
        for (int x: arr) {
            long startTime = System.nanoTime();
            tree.search(x);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            sumForTime += duration;
            sumForCounter += tree.counter;
        }
        System.out.println("Среднее время операции ПОИСКА " + (sumForTime / arr.length));
        System.out.println("Среднее количество итераций на ПОИСКА " + (sumForCounter / arr.length));
        sumForTime = 0;
        sumForCounter = 0;
        for (int x: arr) {
            long startTime = System.nanoTime();
            tree.remove(x);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            sumForTime += duration;
            sumForCounter += tree.counter;
        }
        System.out.println("Среднее время операции УДАЛЕНИЕ " + (sumForTime / arr.length));
        System.out.println("Среднее количество итераций на УДАЛЕНИЕ " + (sumForCounter / arr.length));
//        System.out.println("Содержит 3? " + tree.contains(3)); // true
//        System.out.println("Содержит 9? " + tree.contains(9)); // false
//        tree.remove(7);
//        System.out.println("Содержит 7? " + tree.contains(7)); // false
    }
}
//в дереве есть узел с ключом 3, но нет узла с ключом 9 или 7 (который был удален ранее).