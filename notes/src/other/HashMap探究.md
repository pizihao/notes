# 探究HashMap的初始化过程

有关HashMap基础：

https://blog.kuangstudy.com/index.php/archives/379/

在jdk1.8中hash表=数组+链表+红黑树，使用链地址法解决Hash冲突。

链表查询的时间复杂度是O(n)，查询效率很低，所以在jdk1.8中又引入的红黑树，其查询时间复杂度为O(logn)，比链表的查询效率高。

所以 **当链表长度>=8时链表转为红黑树**

~~~java
/** Because TreeNodes are about twice the size of regular nodes, we
* use them only when bins contain enough nodes to warrant use
* (see TREEIFY_THRESHOLD). And when they become too small (due to
* removal or resizing) they are converted back to plain bins.  In
* usages with well-distributed user hashCodes, tree bins are
* rarely used.  Ideally, under random hashCodes, the frequency of
* nodes in bins follows a Poisson distribution
* (http://en.wikipedia.org/wiki/Poisson_distribution) with a
* parameter of about 0.5 on average for the default resizing
* threshold of 0.75, although with a large variance because of
* resizing granularity. Ignoring variance, the expected
* occurrences of list size k are (exp(-0.5) * pow(0.5, k) /
* factorial(k)). The first values are:
*
* 0:    0.60653066
* 1:    0.30326533
* 2:    0.07581633
* 3:    0.01263606
* 4:    0.00157952
* 5:    0.00015795
* 6:    0.00001316
* 7:    0.00000094
* 8:    0.00000006
* more: less than 1 in ten million
**/
/** 下边是翻译:
因为树节点的大小大约是普通节点的两倍，所以我们只在箱子包含足够的节点时才使用树节点。当它们变得太小(由于删除或调整大小)时，就会被转换回普通的链表。在使用分布良好的用户散列码时，很少使用树箱。理想情况下，在随机哈希码下，箱子中节点的频率遵循泊松分布，默认调整阈值为0.75，平均参数约为0.5，尽管由于调整粒度而存在较大的差异。忽略方差，列表大小k的预期出现次数是(exp(-0.5) * pow(0.5, k) /factorial(k))。
**/
~~~

上面是官方的解释,大致的意思是当链表中的元素达到一定值(8)的时候就会把它转换为红黑树，如果红黑树中的元素小于某一个值(6)时就会再次回到链表，详细参照

https://blog.kuangstudy.com/index.php/archives/417/



当我们使用HashMap的时候一般这样声明：

~~~java
Map<Object, Object> map =  new HashMap<>();
~~~

现在探究一下HashMap的初始化过程：

~~~java
public HashMap() {
    this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
}
~~~

只有这些，也就是说当我们创建了一个HashMap对象的时候，jdk只是把DEFAULT_LOAD_FACTOR赋值给了

loadFactor，其中：

~~~java
/**
* The load factor used when none specified in constructor.
*/
static final float DEFAULT_LOAD_FACTOR = 0.75f;
~~~

DEFAULT_LOAD_FACTOR（负载因子）是一个常量。

再看看HashMap的其他构造方法：

~~~java
public HashMap(int initialCapacity, float loadFactor) {
    if (initialCapacity < 0){
        throw new IllegalArgumentException("Illegal initial capacity: " +
                                           initialCapacity);
    }
    if (initialCapacity > MAXIMUM_CAPACITY){
        initialCapacity = MAXIMUM_CAPACITY;
    }
    if (loadFactor <= 0 || Float.isNaN(loadFactor)){
        throw new IllegalArgumentException("Illegal load factor: " +
                                           loadFactor);
    }
    this.loadFactor = loadFactor;
    this.threshold = tableSizeFor(initialCapacity);
}
~~~

这里的代码是我添加了一些花括号后的样子，其中最后一行代码中有一个threshold，

参照：https://www.cnblogs.com/feng9exe/p/9273802.html 这篇博文给出的解释：

**threshold表示当HashMap的size大于threshold时会执行resize(扩容)操作。threshold=capacity*loadFactor**

capacity：

**capacity译为容量。capacity就是指HashMap中桶的数量。默认值为16。一般第一次扩容时会扩容到64，之后好像是2倍。总之，容量都是2的幂。**

上述代码中threshold是tableSizeFor方法的返回值：

~~~java
/**
* Returns a power of two size for the given target capacity.
*/
static final int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
}
~~~

对于这个算法，参照：https://blog.csdn.net/fan2012huan/article/details/51097331

总之返回的是大于等于initialCapacity的最小的2的幂。

也就是当前HashMap的capacity，容量。

在这个地方，threshold接收了这个值。这样一来就和HashMap扩容的说法不一样了，HashMap扩容要求size > capacity*loadFactor,源码中是这样比较的：

~~~java
if (++size > threshold)
	resize();
~~~

很明显这里的threshold != capacity*loadFactor

之所以会这样，是因为table还没有初始化，HashMap依旧没有完成，

table是用于储存容器中所有的元素的变量。

而table的初始化，放在了HashMap第一次put的时候，

也就是说：

~~~java
map.put("hash","map");
~~~

的时候才会进行table的初始化：

~~~java
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    // 在这个地方，因为table还没有初始化，所以为null，会调用resize()，用来创建容器
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    else {
        Node<K,V> e; K k;
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else {
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    // 这里才是扩容
    if (++size > threshold)
        resize();
    afterNodeInsertion(evict);
    return null;
}
~~~

来看看  resize() 方法：

~~~java
// jdk1.8中因为添加了红黑树又进行了优化（避免的死环），所以比较复杂。
final Node<K,V>[] resize() {
    Node<K,V>[] oldTab = table;
    // 第一次执行时，table 为null，所以这里oldCap 为 0
    int oldCap = (oldTab == null) ? 0 : oldTab.length;
    int oldThr = threshold;
    int newCap, newThr = 0;
    if (oldCap > 0) {
        if (oldCap >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return oldTab;
        }
        else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                 oldCap >= DEFAULT_INITIAL_CAPACITY)
            newThr = oldThr << 1; // double threshold
    }
    // threshold 进行了初始化 oldThr > 0
    else if (oldThr > 0) // initial capacity was placed in threshold
        // 这里 newCap = threshold
        newCap = oldThr;
    else {               // zero initial threshold signifies using defaults
        newCap = DEFAULT_INITIAL_CAPACITY;
        newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    }
    // 执行
    if (newThr == 0) {
       // ft = threshold * loadFactor 这里的threshold 代表 HashMap的 容量
        float ft = (float)newCap * loadFactor;
        // 对比 newCap和ft与最大值的大小，当然不能大于最大值 将合适的值赋值给newThr
        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                  (int)ft : Integer.MAX_VALUE);
    }
    // 在这个地方threshold才可以成为扩容的标志
    threshold = newThr;
    @SuppressWarnings({"rawtypes","unchecked"})
    Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
    table = newTab;
    if (oldTab != null) {
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            if ((e = oldTab[j]) != null) {
                oldTab[j] = null;
                if (e.next == null)
                    newTab[e.hash & (newCap - 1)] = e;
                else if (e instanceof TreeNode)
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                else { // preserve order
                    Node<K,V> loHead = null, loTail = null;
                    Node<K,V> hiHead = null, hiTail = null;
                    Node<K,V> next;
                    do {
                        next = e.next;
                        if ((e.hash & oldCap) == 0) {
                            if (loTail == null)
                                loHead = e;
                            else
                                loTail.next = e;
                            loTail = e;
                        }
                        else {
                            if (hiTail == null)
                                hiHead = e;
                            else
                                hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);
                    if (loTail != null) {
                        loTail.next = null;
                        newTab[j] = loHead;
                    }
                    if (hiTail != null) {
                        hiTail.next = null;
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
    }
    return newTab;
}
~~~

多线程情况下 HashMap 是不安全的 所以建议使用ConcurrentHashMap

感谢几位大佬分享：

​	https://blog.kuangstudy.com/index.php/archives/379/

​	https://blog.kuangstudy.com/index.php/archives/417/

​	https://www.cnblogs.com/feng9exe/p/9273802.html

​	https://blog.csdn.net/fan2012huan/article/details/51097331





