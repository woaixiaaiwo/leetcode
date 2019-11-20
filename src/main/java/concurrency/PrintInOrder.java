package concurrency;

/**
 * 我们提供了一个类：
 *
 * public class Foo {
 *   public void one() { print("one"); }
 *   public void two() { print("two"); }
 *   public void three() { print("three"); }
 * }
 * 三个不同的线程将会共用一个 Foo 实例。
 *
 * 线程 A 将会调用 one() 方法
 * 线程 B 将会调用 two() 方法
 * 线程 C 将会调用 three() 方法
 * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 */
public class PrintInOrder {

    public PrintInOrder() {

    }

    private volatile Integer count = 0;

    public void one() {
        while(count != 0){}
        System.out.print("one");
        count++;
    }
    public void two() {
        while(count != 1){}
        System.out.print("two");
        count++;
    }
    public void three() {
        while(count != 2){}
        System.out.print("three");
        count++;
    }

    public static void main(String[] args) {
        PrintInOrder printInOrder = new PrintInOrder();
        Thread[] threads = new Thread[3];
        threads[0] = new Thread(new Runnable() {
            @Override
            public void run() {
                printInOrder.one();
            }
        });
        threads[1] = new Thread(new Runnable() {
            @Override
            public void run() {
                printInOrder.two();
            }
        });
        threads[2] = new Thread(new Runnable() {
            @Override
            public void run() {
                printInOrder.three();
            }
        });
        threads[0].start();
        threads[1].start();
        threads[2].start();
    }

}
