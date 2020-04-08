# java锁

## 一，公平锁/非公平锁

### 1，公平锁

- 指多个线程按照申请锁的顺序来获取锁，线程直接进入队列中排队，队列中的第一个线程才能获得锁。
- 优点是所有的线程都能拿到锁，等待锁的线程不会饿死
- 缺点是整体的效率相对非公平锁比较低
- 等待队列中除第一个线程以外的所有线程都会阻塞，CPU唤醒阻塞线程的开销比非公平锁大。

### 2，非公平锁

- 是多个线程加锁时直接尝试获取锁，获取不到才会到等待队列的队尾等待。但如果此时锁刚好可用，那么这个线程可以无需阻塞直接获取到锁，所以非公平锁有可能出现后申请锁的线程先获取锁的场景。
- 优点是可以减少唤起线程的开销，整体的效率高，因为线程有几率不阻塞直接获得锁，CPU不必唤醒所有线程
- 缺点是处于等待队列中的线程有可能会饿死，或者需要等待很久

## 二，乐观锁/悲观锁

### 1，乐观锁

- 乐观锁认为自己在使用数据时不会有别的线程修改数据，所以不会添加锁，只是在更新数据的时候去判断之前有没有别的线程更新了这个数据
- 如果这个数据没有被更新，当前线程将自己修改的数据成功写入。
- 如果数据已经被其他线程更新，则根据不同的实现方式执行不同的操作（例如报错或者自动重试）。
- CAS算法（比较再交换）
- 乐观锁适合读操作多的场景，不加锁的特点能够使其读操作的性能大幅提升。

### 2，悲观锁

- 悲观锁认为自己在使用数据的时候一定有别的线程来修改数据，因此在获取数据的时候会先加锁，确保数据不会被别的线程修改。
- synchronized关键字和Lock的实现类都是悲观锁。
- 悲观锁适合写操作多的场景，先加锁可以保证写操作时数据正确。

## 三，可重入锁（递归锁）

### 1，可重入锁

- 可重入锁又名递归锁，是指在同一个线程在外层方法获取锁的时候，再进入该线程的内层方法会自动获取锁，不会因为之前已经获取过还没释放而阻塞。
- Java中ReentrantLock和synchronized都是可重入锁
- 优点是可一定程度避免死锁

## 四，自旋锁

### 1，自旋锁

- Java线程阻塞或唤醒一个线程需要作系统切换CPU状态来完成，这种状态转换需要耗费处理器时间。有可能得不偿失
- 如果物理机器有多个处理器，能够让两个或以上的线程同时并行执行，我们就可以让后面那个请求锁的线程不放弃CPU的执行时间，看看持有锁的线程是否很快就会释放锁。
- 而为了让当前线程“稍等一下”，我们需让当前线程进行自旋，如果在自旋完成后前面锁定同步资源的线程已经释放了锁，那么当前线程就可以不必阻塞而是直接获取同步资源，从而避免切换线程的开销。这就是自旋锁。
- 缺点是不能代替阻塞。自旋等待虽然避免了线程切换的开销，但它要占用处理器时间。如果锁被占用的时间很短，自旋等待的效果就会非常好。反之，如果锁被占用的时间很长，那么自旋的线程只会白浪费处理器资源。

~~~java
class AtomicReferenceDemo {
    private  AtomicReference<Thread> integerAtomicReference = new AtomicReference<>();

    public void mylocks() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "我是一个线程");

        while (!integerAtomicReference.compareAndSet(null, thread)) {
            System.out.println(thread.getName()+"------>自旋中");
        }
    }

    public void myUNLocks() {
        Thread thread = Thread.currentThread();
        integerAtomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "--->myUNLocks");
    }
}

//手写一个自旋锁
public class SpinLockDemo {

    public static void main(String[] args) {
        AtomicReferenceDemo spinLockDemo = new AtomicReferenceDemo();
        new Thread(() -> {
            spinLockDemo.mylocks();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUNLocks();
        }, "a").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLockDemo.mylocks();

            spinLockDemo.my=UNLocks();
        }, "b").start();
    }
}
~~~

## 五，读写锁

### 1，读写锁

- 特殊的自旋锁

- 把对共享资源对访问者划分成了读者和写者，读者只对共享资源进行访问，写者则是对共享资源进行写操作。

- 一个读写锁同时只能存在一个写锁但是可以存在多个读锁，但不能同时存在写锁和读锁。

- ```java
  //读写锁
  private ReadWriteLock lock = new ReentrantReadWriteLock();
  //上读锁
  lock.readLock().lock();
  //解锁
  lock.readLock().unlock();
  //上写锁
  lock.writeLock().lock();
  //解锁
  lock.writeLock().unlock();
  ```

## 六，无锁/偏向锁/轻量锁/重量锁

### 1，无锁

- 无锁没有对资源进行锁定，所有的线程都能访问并修改同一个资源，但同时只有一个线程能修改成功。
- 无锁的特点就是修改操作在循环内进行，线程会不断的尝试修改共享资源。
- 如果没有冲突就修改成功并退出，否则就会继续循环尝试。
- 如果有多个线程修改同一个值，必定会有一个线程能修改成功，而其他修改失败的线程会不断重试直到修改成功。

### 2，偏向锁

- 偏向锁是指一段同步代码一直被一个线程所访问，那么该线程会自动获取锁，降低获取锁的代价。
- 在大多数情况下，锁总是由同一线程多次获得，不存在多线程竞争，所以出现了偏向锁。其目标就是在只有一个线程执行同步代码块时能够提高性能。

### 3，轻量级锁

- 是指当锁是偏向锁的时候，被另外的线程所访问，偏向锁就会升级为轻量级锁，其他线程会通过自旋的形式尝试获取锁，不会阻塞，从而提高性能。

### 4，重量级锁

- 若当前只有一个等待线程，则该线程通过自旋进行等待。但是当自旋超过一定的次数，或者一个线程在持有锁，一个在自旋，又有第三个来访时，轻量级锁升级为重量级锁。

### 5，图示

| 锁状态   | 存储内容                                              | 标志位 |
| -------- | ----------------------------------------------------- | ------ |
| 无锁     | 对象的hashCode、对象分代年龄、是否是偏向锁(0)         | 01     |
| 偏向锁   | 偏向线程ID、偏向时间戳、对象分代年龄、是否是偏向锁(1) | 01     |
| 轻量级锁 | 指向栈中锁记录的指针                                  | 00     |
| 重量级锁 | 指向互斥量（重量级锁）的指针                          | 10     |

## 七，独占锁/共享锁

### 1，独占锁

- 指该锁一次只能被一个线程所持有
- 对ReentrantLock和Synchronized而言都是独占锁

### 2，共享锁

- 指该锁可被多个线程所持有。
- 对ReentrantReadWriteLock其读锁是共享锁，其写锁是独占锁。
- 读锁的共享锁可保证并发读是非常高效的，读写，写读，写写的过程是互斥的。



