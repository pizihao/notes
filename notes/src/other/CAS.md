# CAS

## 主要内容

本文从 CAS实例 --> 什么是UnSafe类 --> CAS底层 -->AtomicInteger.getAndIncrement() 进行分析 -->CAS缺点 --> 什么是ABA问题 -->原子引用 -->如何解决ABA问题 -->时间戳原子引用

## CAS原理

通过申明一个volatile （内存锁定，同一时刻只有一个线程可以修改内存值）类型的变量，再加上unsafe.compareAndSwapInt的方法，来保证实现线程同步的。

## CAS实例

CAS：比较并交换

~~~java
import java.util.concurrent.atomic.AtomicInteger;
public class Main {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(5);
        // true 100
        System.out.println(integer.compareAndSet(5, 100) +" "+ integer.get());
        integer.compareAndSet(5,30);
        //false 100
        System.out.println(integer.compareAndSet(5, 30) +" "+ integer.get());
    }
}
~~~



## 什么是UnSafe类

UnSafe类是CAS的核心类，由于Java无法直接访问底层系统，所以要通过本地的native方法进行访问，UnSafe类就相当于一个后门，基于该类可以直接操作特定内存中的数据，其内部就像C的指针一样操作内存。

观察UnSafe类的源码，可以看到UnSafe类都是native方法，也就是说Unsafe类都是直接调用操作系统底层资源执行任务。

## CAS底层

**java.util.concurrent **完全建立在CAS之上，CAS有三个操作数，内存值V、旧的预期值A、要修改的值B，如果 V == A, 那么 V =B，返回true；否则什么都不做返回false。

- CAS 的全称 Compare-And-Swap，它是一条 CPU 并发
- CAS 说白了就是使用真实值和期望值进行比较，如果相等的话，进行修改成功，否则修改失败。
- 在Java中 CAS 底层使用的就是自旋锁 + UnSafe类。
- CAS并发原语体现在Java语言就是UnSafe类中的各个方法，调用UnSafe类的CAS方法，JVM会帮我们实现出CAS的汇编指令，这是一种完全依赖于硬件的功能，通过它体现了原子性操作。
- CAS是系统原语，属于操作系统指令范畴，若干条指令组成，执行必须是连续的，并且执行过程中不会被中断，也就是说CAS是一种CPU的原子指令，不会造成所谓的数据不一致情况。

~~~java
// AtomicInteger.getAndIncrement() 进行分析
public final int getAndIncrement() {
	return unsafe.getAndAddInt(this, valueOffset, 1);
}
// this是 AtomicInteger实例对象；
// valueOffset是基于该实例对象的偏移量；
// 1是需要加的值
~~~



然后调用的是 UnSafe类 的 getAndAddInt方法。

UnSafe 的 getAndAddInt

```java
public final int getAndAddInt(Object var1, long var2, int var4) {
    int var5;
    do {
        var5 = this.getIntVolatile(var1, var2);
    } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
    return var5;
}
// var1 AtomicInteger (原子类型) 对象本身
// var2 内存地址偏移量
// var4 要进行加多少
// var5 在通过var1 var2 找出了主物理内存上面真实的值 用当前该对象的值比较var5 如果相同更新var5 + var4 并返回true
// 如果不同，继续取值然后再比较，直至更新完成
```
## CAS缺点？

- 循环时间长开销大，如果线程数比较多的话，CAS请求失败会一直循环下去，这样的话CPU带来的开销就比较大。
- 只能保证一个共享变量的原子操作，对于多个共享变量操作时，循环 CAS 就无法保证操作的原子性。
- 会出现ABA问题。

### 什么是ABA问题

举例说明：有两个人A、B，桌子上有一个耳机，然后A去拿耳机用，桌子上换成饼干，耳机用完，桌子上又换回了耳机。对于桌子而言状态变化：耳机 — 饼干 — 耳机。然后B同学去桌子拿耳机用，在B看来桌子上的耳机没有变化，但是过程中耳机已经被使用过了，这就是ABA问题。换句话说就是： 开头和结尾是一样的，中间的过程会发生变化。

### 原子引用

AtomicReference 一般的都是保证基本类型的原子性，对于一个类而言可以使用原子引用进行封装。

~~~java
AtomicReference<User> are = new AtomicReference<>();
~~~

以上就完成了保证User类原子性。

### 如何解决ABA问题

使用CAS+版本号进行解决，对一个数据如果修改了的话，那么版本号就进行+1，然后再循环比较的时候，不仅仅比较值再根据版本号就可以解决ABA问题。

### 时间戳原子引用

其中原有的原子性上面加入了版本号的概念：
使用案例：线程B修改值的时候，发现虽然内存中的值和预期的值一样，但是由于版本号已经发生了改变，所以修改失败。

~~~java
public class StampedReferenceDemo {
    public static void main(String[] args) {
        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100,1);
        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " 的版本号为：" + stamp);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stampedReference.compareAndSet(100, 101, stampedReference.getStamp(), stampedReference.getStamp() + 1 );
            stampedReference.compareAndSet(101, 100, stampedReference.getStamp(), stampedReference.getStamp() + 1 );
        },"A").start();

        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " 的版本号为：" + stamp);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = stampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
            System.out.println(b); // false
            System.out.println(stampedReference.getReference()); // 100
            System.out.println(stampedReference.getStamp()); // 3

        },"B").start();

    }

}
~~~

