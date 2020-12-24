# LockSupport

## park()

```java
public static void park(Object blocker) {
    Thread t = Thread.currentThread();
    setBlocker(t, blocker);
    UNSAFE.park(false, 0L);
    setBlocker(t, null);
}
```

```java
private static void setBlocker(Thread t, Object arg) {
    // Even though volatile, hotspot doesn't need a write barrier here.
    UNSAFE.putObject(t, parkBlockerOffset, arg);
}
```

```java
public native void putObject(Object var1, long var2, Object var4);
```

putObject方法中存入的三个参数：

1.  t 线程实例 Thread.currentThread();

2. parkBlockerOffset 线程实例的parkBlocker字段相对Thread实例对象的起始地址的偏移量，就是这个属性在内存中的位置

   ```java
   Class<?> tk = Thread.class;
   parkBlockerOffset = UNSAFE.objectFieldOffset
       (tk.getDeclaredField("parkBlocker"));
   ```

3. blocker 一般指要上锁的Thread对象

## unpark()

```java
public static void unpark(Thread thread) {
    if (thread != null)
        UNSAFE.unpark(thread);
}
```

通过在park的时候设置的blocker 可以找到参数Thread实例阻塞的位置，之后唤醒，唤醒后park方法要通过    setBlocker(t, null);设置之前的屏障为null，表示我已经不阻塞了。

LockSupport还有对阻塞时间的控制

```java
//阻塞线程nanos纳秒
public static void parkNanos(Object blocker, long nanos) {
    if (nanos > 0) {
        Thread t = Thread.currentThread();
        setBlocker(t, blocker);
        UNSAFE.park(false, nanos);
        setBlocker(t, null);
    }
}
```

```java
//阻塞线程直到deadline
public static void parkUntil(Object blocker, long deadline) {
    Thread t = Thread.currentThread();
    setBlocker(t, blocker);
    UNSAFE.park(true, deadline);
    setBlocker(t, null);
}
```



还有不带Object参数的park()默认阻塞