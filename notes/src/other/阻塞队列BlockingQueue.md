# 阻塞队列Blocking Queue

阻塞队列是一个支持两个附加操作的队列，这

两个附加的操作支持阻塞的插入和移除方法。

1. 支持阻塞的插入方法：意思是当队列满时，队列会阻塞插入元素的线程，直到队列不满。
2. 支持阻塞的移除方法：意思是在队列为空时，获取元素的线程会等待队列变为非空

四组API

| 方法\处理方式 | 抛出异常  | 返回特殊值 | 一直阻塞 | 超时退出           |
| ------------- | --------- | ---------- | -------- | ------------------ |
| 插入方法      | add(e)    | offer(e)   | put(e)   | offer(e,time,unit) |
| 移除方法      | remove()  | poll()     | take()   | poll(time,unit)    |
| 检查方法      | element() | peek()     | -        | -                  |

- 抛出异常：是指当阻塞队列满时候，再往队列里插入元素，会抛出 IllegalStateException("Queue full") 异常。当队列为空时，从队列里获取元素时会抛出 NoSuchElementException 异常 。
- 返回特殊值：插入方法会返回是否成功，成功则返回 true。移除方法，则是从队列里拿出一个元素，如果没有则返回 null
- 一直阻塞：当阻塞队列满时，如果生产者线程往队列里 put 元素，队列会一直阻塞生产者线程，直到拿到数据，或者响应中断退出。当队列空时，消费者线程试图从队列里 take 元素，队列也会阻塞消费者线程，直到队列可用。
- 超时退出：当阻塞队列满时，队列会阻塞生产者线程一段时间，如果超过一定的时间，生产者线程就会退出。
- 如果是无界阻塞队列，队列不可能会出现满的情况，所以使用put或offer方法永远不会被阻塞，而且使用offer方法时，该方法永远返回true

### 七个阻塞队列

- **ArrayBlockingQueue** ：一个由数组结构组成的有界阻塞队列。

  - ArrayBlockingQueue是一个用数组实现的有界阻塞队列。此队列按照先进先出（FIFO）的原则对元素进行排序

  - ~~~java
    public class ArrayBlockingQueueDemo {
        public static void main(String[] args) {
            BlockingQueue queue = new ArrayBlockingQueue(3);
    
            System.out.println(queue.add("a"));
            System.out.println(queue.add("b"));
            System.out.println(queue.add("b"));
    
            System.out.println(queue.add("d"));
        }
    }
    /**
    结果会报错
    true
    true
    true
    Exception in thread "main" java.lang.IllegalStateException: Queue full
    	at java.util.AbstractQueue.add(AbstractQueue.java:98)
    	at java.util.concurrent.ArrayBlockingQueue.add(ArrayBlockingQueue.java:312)
    	at com.company.BlockingQueueDemo.ArrayBlockingQueueDemo.main(ArrayBlockingQueueDemo.java:18)
    前三个add正常，第四个会报Queue full队列已满的异常
    **/
    ~~~

  - ~~~java
    public class ArrayBlockingQueueDemo {
        public static void main(String[] args) {
            BlockingQueue queue = new ArrayBlockingQueue(3);
    
            queue.add("a");
            queue.add("b");
            queue.add("b");
    
            System.out.println(queue.remove());
            System.out.println(queue.remove());
            System.out.println(queue.remove());
    
            System.out.println(queue.remove());
        }
    }
    /**
    结果会报错
    a
    b
    b
    Exception in thread "main" java.util.NoSuchElementException
    	at java.util.AbstractQueue.remove(AbstractQueue.java:117)
    	at com.company.BlockingQueueDemo.ArrayBlockingQueueDemo.main(ArrayBlockingQueueDemo.java:22)
    没有找到元素异常
    **/
    ~~~

  - ~~~java
    public class ArrayBlockingQueueDemo {
        public static void main(String[] args) {
            BlockingQueue queue = new ArrayBlockingQueue(3);
    
            queue.add("a");
            queue.add("b");
            queue.add("c");
    
            System.out.println(queue.element());
        }
    }
    /**
    queue.element(),取队首元素
    a
    **/
    ~~~

  - ~~~java
    public class ArrayBlockingQueueDemo {
        public static void main(String[] args) {
            BlockingQueue queue = new ArrayBlockingQueue(3);
    
            System.out.println(queue.offer("a"));
            System.out.println(queue.offer("b"));
            System.out.println(queue.offer("c"));
            
            System.out.println(queue.offer("d"));
        }
    }
    /**
    结果
    true
    true
    true
    false
    队列满了以后再插入就会返回false
    **/
    ~~~

  - ~~~
    public class ArrayBlockingQueueDemo {
        public static void main(String[] args) {
            BlockingQueue queue = new ArrayBlockingQueue(3);
    
            queue.offer("a");
            queue.offer("b");
            queue.offer("c");
            
            System.out.println(queue.poll());
            System.out.println(queue.poll());
            System.out.println(queue.poll());
            System.out.println(queue.poll());
        }
    }
    /**
    结果
    a
    b
    c
    null
    无元素可取时返回为null
    **/
    ~~~

  - ~~~java
    public class ArrayBlockingQueueDemo {
        public static void main(String[] args) {
            BlockingQueue queue = new ArrayBlockingQueue(3);
    
            queue.offer("a");
            queue.offer("b");
            queue.offer("c");
            
            System.out.println(queue.peek());
        }
    }
    
    /**
    结果
    a
    返回队首元素
    **/
    ~~~

  - ~~~java
    public class ArrayBlockingQueueDemo {
        public static void main(String[] args) throws InterruptedException {
            BlockingQueue<String> queue = new ArrayBlockingQueue(3);
    
            queue.put("a");
            queue.put("b");
            queue.put("c");
            queue.put("d");
        }
    }
    /**
    无返回结果
    程序会一直运行
    put添加时如果队列满了，就会阻塞，一直等待，直到队列不满为止
    **/
    ~~~

  - ~~~java
    public class ArrayBlockingQueueDemo {
        public static void main(String[] args) throws InterruptedException {
            BlockingQueue<String> queue = new ArrayBlockingQueue(3)
                
            queue.put("a");
            queue.put("b");
            queue.put("c");
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        }
    }
    /**
    结果 
    a
    b
    c
    程序不终止
    take取值时如果队列为空，就会阻塞，一直等待，直到队列不为空为止
    **/
    ~~~

  - ~~~java
    public class ArrayBlockingQueueDemo {
        public static void main(String[] args) throws InterruptedException {
            BlockingQueue<String> queue = new ArrayBlockingQueue(3);
    
            System.out.println(queue.offer("a", 2, TimeUnit.SECONDS));
            System.out.println(queue.offer("b", 2, TimeUnit.SECONDS));
            System.out.println(queue.offer("c", 2, TimeUnit.SECONDS));
            System.out.println(queue.offer("d", 2, TimeUnit.SECONDS));
        }
    }
    /**
    结果
    true
    true
    true
    false
    不过会阻塞两秒钟
    **/
    ~~~

  - ~~~java
    public class ArrayBlockingQueueDemo {
        public static void main(String[] args) throws InterruptedException {
            BlockingQueue<String> queue = new ArrayBlockingQueue(3);
    
            queue.offer("a", 2, TimeUnit.SECONDS);
            queue.offer("b", 2, TimeUnit.SECONDS);
            queue.offer("c", 2, TimeUnit.SECONDS);
            queue.offer("d", 2, TimeUnit.SECONDS);
    
            System.out.println(queue.poll(2, TimeUnit.SECONDS));
            System.out.println(queue.poll(2, TimeUnit.SECONDS));
            System.out.println(queue.poll(2, TimeUnit.SECONDS));
            System.out.println(queue.poll(2, TimeUnit.SECONDS));
        }
    }
    /**
    结果
    a
    b
    c
    null
    阻塞两秒钟
    **/
    ~~~

  - 

- **LinkedBlockingQueue** ：一个由链表结构组成的有界阻塞队列。

- **PriorityBlockingQueue** ：一个支持优先级排序的无界阻塞队列。

- **DelayQueue**：一个使用优先级队列实现的无界阻塞队列。

- **SynchronousQueue**：一个不存储元素的阻塞队列，也即单个元素的队列。

  - ~~~java
    public class SynchronousQueueDemo {
        public static void main(String[] args) {
            BlockingQueue<String> synchronous = new SynchronousQueue<>();
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName()+"\t put 1");
                    synchronous.put("1");
                    System.out.println(Thread.currentThread().getName()+"\t put 2");
                    synchronous.put("2");
                    System.out.println(Thread.currentThread().getName()+"\t put 3");
                    synchronous.put("3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"AA").start();
    
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(synchronous.take());
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(synchronous.take());
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(synchronous.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"BB").start();
        }
    }
    /**
    结果
    AA	 put 1
    1
    AA	 put 2
    2
    AA	 put 3
    3
    
    **/
    ~~~

  - 

  

- **LinkedTransferQueue**：一个由链表结构组成的无界阻塞队列。
- **LinkedBlockingDeque**：一个由链表结构组成的双向阻塞队列。

