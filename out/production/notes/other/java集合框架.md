# Java集合框架

简介：

~~~txt
集合是Java中保存大量对象的引用的一种方式，同样可以用来保存大量对象的引用的还有数组。
本身Java可以通过一个简单的特定类型的变量来存放一个特定类型实例的引用，但Java程序在运行时会创建很多新的对象，这些对象可能数量不定，也可能不清楚其类型，这个时候，就可以通过集合这种形式来持有这些新创建的对象的引用。
简而言之：集合能够持有对象。
~~~

划分：
Java中的集合框架的实现是Java容器类相关类库，容器类有两种划分：

## 1.Collection

一个包含独立元素的序列，序列的每一个位置都包含一个独立的元素，且各个元素之间是无序的，是可重复的，是可以为null的。

Collection接口：
Collection接口继承了Iterable接口，该接口主要有一个iterator方法返回一个Iterator迭代器对象，该迭代器只支持单向移动的访问数据

先来看看Collection，这里要注意还有一个类叫Collections

- Collection上边有解释，是一个集合接口，提供了对集合对象进行基本操作的通用接口方法
- Collections是集合类的一个工具类/帮助类，其中提供了一系列静态方法，用于对集合中元素进行排序、搜索以及线程安全等各种操作。

Collection的源码

~~~java
public interface Collection<E> extends Iterable<E> {
    int size();
    // 返回此集合中的元素数。
    boolean isEmpty();
    // 如果此集合不包含元素，则返回 true 
    boolean contains(Object o);
    //如果此集合包含指定的元素，则返回true 。
    Iterator<E> iterator();
    Object[] toArray();
    // 返回一个包含此集合中所有元素的数组。 如果此集合对其迭代器返回的元素的顺序做出任何保证，则此方法必须以相同的顺序返回元素。 
    <T> T[] toArray(T[] a);
    // 返回包含此集合中所有元素的数组; 返回的数组的运行时类型是指定数组的运行时类型。 如果集合适合指定的数组，则返回其中。 否则，将为指定数组的运行时类型和此集合的大小分配一个新数组。 
    boolean add(E e);
    // 确保此集合包含指定的元素（可选操作）。 如果此集合由于调用而更改，则返回true 。
    boolean remove(Object o);
    // 从该集合中删除指定元素的单个实例（如果存在）（可选操作）。 
    boolean containsAll(Collection<?> c);
    // 如果此集合包含指定 集合中的所有元素，则返回true。
    boolean addAll(Collection<? extends E> c);
    // 将指定集合中的所有元素添加到此集合（可选操作）。 
    boolean removeAll(Collection<?> c);
    // 删除指定集合中包含的所有此集合的元素（可选操作）。 
    default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<E> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }
    // 删除满足给定谓词的此集合的所有元素。
    boolean retainAll(Collection<?> c);
    // 仅保留此集合中包含在指定集合中的元素（可选操作）。 
    void clear();
    // 从此集合中删除所有元素（可选操作）。 
    boolean equals(Object o);
    // 将指定的对象与此集合进行比较以获得相等性。
    int hashCode();
    // 返回此集合的哈希码值。
    @Override
    default Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, 0);
    }
    // 可分割迭代器
    
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
    default Stream<E> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }
    // 流和并行流 
}
~~~

继承了Collection接口的接口有：List、Queue、Set

### (1),List

List是有序的Collection，会默认按元素的添加顺序给每个元素设置一个索引，增删改查均可基于索引操作。

继承list接口的有：AbstractList 及LinkedList，常用的ArrayList继承自AbstractList。

>AbstractList

ArrayList是基于数组实现的，其底层实现为一个长度动态增长的Object[]数组，因此其具有访问快，增删慢的特点.

以下是ArrayList的构造函数的源码

~~~java
public ArrayList(int initialCapacity) {
    if (initialCapacity > 0) {
        // 创建一个Object数组
        this.elementData = new Object[initialCapacity];
    } else if (initialCapacity == 0) {
        this.elementData = EMPTY_ELEMENTDATA;
    } else {
        throw new IllegalArgumentException("Illegal Capacity: "+
                                           initialCapacity);
    }
}

public ArrayList() {
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
}

public ArrayList(Collection<? extends E> c) {
    elementData = c.toArray();
    if ((size = elementData.length) != 0) {
        if (elementData.getClass() != Object[].class)
            elementData = Arrays.copyOf(elementData, size, Object[].class);
    } else {
            this.elementData = EMPTY_ELEMENTDATA;
    }
}

其中
private static final Object[] EMPTY_ELEMENTDATA = {};

private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
// 其实这两个常量是一样的
~~~

ArrayList的底层是一个数组

>LinkedList

LinkedList除了List接口外还实现了Deque接口。

部分源码

~~~java
public LinkedList() {
}

public LinkedList(Collection<? extends E> c) {
    this();
    addAll(c);
}

public boolean addAll(Collection<? extends E> c) {
    return addAll(size, c);
}

public boolean addAll(int index, Collection<? extends E> c) {
    checkPositionIndex(index);
    Object[] a = c.toArray();
    int numNew = a.length;
    // 如果转换的数组中没有任何的元素，return false
    if (numNew == 0)
        return false;

    Node<E> pred, succ;
    //index 是从集合中插入第一个元素的索引
    if (index == size) {
        //index==size，说明是在当前集合的末尾插入新数据，因此没有后节点，succ=null，前节点为当前集合的最后一个节点pred=last
        succ = null;
        pred = last;
        // last 是指向最后一个元素的指针
    } else {
        succ = node(index);
        //找到当前下标指代的节点，要在该节点前插入数据，因此令succ等于该节点。
        pred = succ.prev;
        //pred则等于succ前面一位的节点。需要注意当index=0时，该点可以为null。
    }

    for (Object o : a) {
        @SuppressWarnings("unchecked") E e = (E) o;
        Node<E> newNode = new Node<>(pred, e, null);
        //创建一个新的节点，节点元素为e，前节点为pred，后节点为null。
        if (pred == null)
            first = newNode;
        //说明index=0，因此新插入的集合的第一个元素，作为first
        else
            pred.next = newNode;
        //说明index<>0，因此新的节点，作为前节点的后节点(pred.next)
        pred = newNode;
        // 下一个
    }

    if (succ == null) {
        last = pred;
        //说明是从当前集合的末尾开始插入数据，因此数据的最后一个元素，作为当前集合的last
    } else {
        pred.next = succ;
        succ.prev = pred;
        //pred为新插入的最后一个数据，令其的后节点等于之前拆开位置的后节点，succ为之前拆开位置的前节点，令其前节点prev等于新插入的元素的最后一个数据。
    }

    size += numNew; 
    //新插入numNew条数据，size增加相应的大小。
    modCount++;
    // 这里这个modCount是AbstractList里面的 代表此列表被结构修改的次数，。。。
    return true;
}
~~~

 LinkedList 底层是基于双向链表实现的

List接口还提供了特殊的迭代器ListIterator，ListIterator支持双向移动访问元素，支持插入和替换元素，还能够从指定位置开始获取ListIterator。

ArrayList和LinkedList的插入，取出时间复杂度：

| 操作                      | ArrayList                                                    | LinkedList                    |
| ------------------------- | ------------------------------------------------------------ | ----------------------------- |
| 读取get()                 | 根据下标直接查询，复杂度O(1)                                 | 遍历获取，复杂度O(n)          |
| 添加add(E)                | 直接尾部添加，复杂度O(1)中                                   | 直接尾部添加，复杂度O(1)      |
| 指定位置添加add (index.E) | 查询后添加，抽入位置后面的元素要向后移动-一个单位，复杂度O(n) | 询后，指针指向操作,复杂度O(n) |
| 删除remove (E)            | 删除指定元素，后面的元素需要逐个移动，复杂度O(n)             | 直接指针操作，复杂度O(1)      |

### (2),Queue 

Queue 不允许随机访问其中间的元素，只能从队首访问的Collection，且一般来说队列都应该是FIFO的。

Deque接口继承了Queue,AbstractQueue实现了Queue接口，且常用的PriorityQueue继承自AbstractQueue。

其中Deque接口代表一个"双端队列"，双端队列可以同时从两端来添加、删除元素，因此Deque的实现类既可以当成队列使用、也可以当成栈使用.

 PriorityQueue不是一个比较标准的队列实现，PriorityQueue保存队列元素的顺序并不是按照加入队列的顺序，而是按照队列元素的某种功能权重进行重新排序

除此之外还有BlockingDeque等接口

> Deque

双端队列，BlockingDeque接口继承了Deque接口

BlockingDeque：阻塞双端队列

> PriorityQueue

优先队列

构造函数

~~~java
private static final int DEFAULT_INITIAL_CAPACITY = 11;
// 构造一个具有默认容量的空队列
public PriorityQueue() {
    this(DEFAULT_INITIAL_CAPACITY, null);
}
// 构造一个具有指定容量的空队列
public PriorityQueue(int initialCapacity) {
    this(initialCapacity, null);
}
// 创建具有默认初始容量的 PriorityQueue ，并根据指定的比较器对其元素进行排序。 
public PriorityQueue(Comparator<? super E> comparator) {
    this(DEFAULT_INITIAL_CAPACITY, comparator);
}
// 创建具有 PriorityQueue初始容量的PriorityQueue，根据指定的比较器对其元素进行排序。
public PriorityQueue(int initialCapacity,
                     Comparator<? super E> comparator) {
    if (initialCapacity < 1)
        throw new IllegalArgumentException();
    // 判断容量是否合法
    this.queue = new Object[initialCapacity];
    // 创建数组
    this.comparator = comparator;
    // 比较器
}
// 创建一个 PriorityQueue集合中的元素的PriorityQueue。 
@SuppressWarnings("unchecked")
public PriorityQueue(Collection<? extends E> c) {
    if (c instanceof SortedSet<?>) {
        SortedSet<? extends E> ss = (SortedSet<? extends E>) c;
        this.comparator = (Comparator<? super E>) ss.comparator();
        initElementsFromCollection(ss);
    }
    else if (c instanceof PriorityQueue<?>) {
        PriorityQueue<? extends E> pq = (PriorityQueue<? extends E>) c;
        this.comparator = (Comparator<? super E>) pq.comparator();
        initFromPriorityQueue(pq);
    }
    else {
        this.comparator = null;
        initFromCollection(c);
    }
}
// 创建包含 PriorityQueue优先级队列中的元素的PriorityQueue。 
@SuppressWarnings("unchecked")
public PriorityQueue(PriorityQueue<? extends E> c) {
    this.comparator = (Comparator<? super E>) c.comparator();
    initFromPriorityQueue(c);
}
// 创建一个 PriorityQueue指定排序集中的元素的PriorityQueue。 
@SuppressWarnings("unchecked")
public PriorityQueue(SortedSet<? extends E> c) {
    this.comparator = (Comparator<? super E>) c.comparator();
    initElementsFromCollection(c);
}
~~~

### (3),set

Set是元素不重复的Collection。

实现了Set接口的有HashSet LinkedHashSet，SortedSet接口继承了Set接口，NavigableSet接口继承了SortedSet接口，常用的TreeSet实现了NavigableSet接口。

> HashSet 

~~~java
public HashSet() {
    map = new HashMap<>();
}

public HashSet(Collection<? extends E> c) {
    map = new HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
    addAll(c);
}

public HashSet(int initialCapacity, float loadFactor) {
    map = new HashMap<>(initialCapacity, loadFactor);
}

public HashSet(int initialCapacity) {
    map = new HashMap<>(initialCapacity);
}
// HashSet底层是HashMap，HashMap的key是HashSet存储的值，value为loadFactor
~~~

> LinkedHashSet

```java
public LinkedHashSet(int initialCapacity, float loadFactor) {
    super(initialCapacity, loadFactor, true);
}

public LinkedHashSet(int initialCapacity) {
    super(initialCapacity, .75f, true);
}

public LinkedHashSet() {
    super(16, .75f, true);
}

public LinkedHashSet(Collection<? extends E> c) {
    super(Math.max(2*c.size(), 11), .75f, true);
    addAll(c);
}

HashSet(int initialCapacity, float loadFactor, boolean dummy) {
    map = new LinkedHashMap<>(initialCapacity, loadFactor);
}
// 底层是LinkedHashMap
```

## 2.Map

一个每一组数据都是键值对的容器，并能够通过其键来查找其对应值；因为允许通过键查找，所以键应该具有唯一性，若重复则查找结果就不确定了。
Map接口：

间接基于Map接口实现的类有很多,常用的有：TreeMap，HashMap，LinkedHashMap

### (1),TreeMap

TreeMap存储key-value对(节点)时，需要根据key对节点进行排序，可以保证所有的key-value对处于有序状态，能便捷的实现对其内部元素的各种排序，但其一般性能比前两种map差

```java
public TreeMap() {
    comparator = null;
}

public TreeMap(Comparator<? super K> comparator) {
    this.comparator = comparator;
}

public TreeMap(Map<? extends K, ? extends V> m) {
    comparator = null;
    putAll(m);
}

public TreeMap(SortedMap<K, ? extends V> m) {
    comparator = m.comparator();
    try {
        buildFromSorted(m.size(), m.entrySet().iterator(), null, null);
    } catch (java.io.IOException cannotHappen) {
    } catch (ClassNotFoundException cannotHappen) {
    }
}

private void buildFromSorted(int size, Iterator<?> it,
                             java.io.ObjectInputStream str,
                             V defaultVal)
    throws  java.io.IOException, ClassNotFoundException {
    this.size = size;
    root = buildFromSorted(0, 0, size-1, computeRedLevel(size),
                           it, str, defaultVal);
}
```

### (2),HashMap和Hashtable

HashMap用于快速访问，实现快速存储和检索，但其缺点是其包含的元素是无序的，这导致它在存在大量迭代的情况下表现不佳。

而Hashtable，线程安全的，效率很低，既不支持Null key也不支持Null value

Hashtable的初始长度是11，之后每次扩充容量变为之前的2n+1（n为上一次的长度）

而HashMap的初始长度为16，之后每次扩充变为原来的两倍

Hashtable直接使用对象的hashCode。hashCode是JDK根据对象的地址或者字符串或者数字算出来的int类型的数值。然后再使用除留余数发来获得最终的位置。 然而除法运算是非常耗费时间的。效率很低

HashMap为了提高计算效率，将哈希表的大小固定为了2的幂，这样在取模预算时，不需要做除法，只需要做位运算。位运算比除法的效率要高很多。

如果考虑到线程安全，建议使用ConcurrentHashMap



##### HashMap = 数组+链表+红黑树，发生hash冲突后会把元素放在链表的头部

put的过程

~~~java
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}
// 会调用putVal方法

final V putVal (int hash, K key, V value,boolean onlyIfAbsent, boolean evict){
    Node<K, V>[] tab;
    Node<K, V> p;
    int n, i;
    //	tab：内部数组
    //	p：hash对应的索引位中的首节点
    //	n：内部数组的长度
    //	i：hash对应的索引位

    //	首次put数据时，内部数组为空，扩充数组。
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    //	计算数组索引，获取该索引位置的首节点，如果为null，添加一个新的节点
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    else {
        //	如果首节点的key和要存入的key相同，那么直接覆盖value的值。
        Node<K, V> e;
        K k;
        //	这个判断hash值是否一样，一样的话，就是同一个key，那么就直接覆盖
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        //	否则的话就是判断是否是红黑树节点。如果首节点是红黑树的，将键值对插添加到红黑树
        else if (p instanceof TreeNode）
            e = ((TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
            // 此时首节点为链表，如果链表中存在该键值对，直接覆盖value。
            // 如果不存在，则在末端插入键值对。然后判断链表是否大于等于7，尝试转换成红黑树。
            // 注意此处使用“尝试”，因为在treeifyBin方法中还会判断当前数组容量是否到达64，
            // 否则会放弃次此转换，优先扩充数组容量。
            // 走到这里，hash碰撞了。检查链表中是否包含key，或将键值对添加到链表末尾
        else{
            for (int binCount = 0; ; ++binCount) {
                // 如果p.next == null，则就是到达链表末尾，添加新节点，如果长度足够，转换成树结构
                //static final int TREEIFY_THRESHOLD = 8;
                //static final int UNTREEIFY_THRESHOLD = 6;
                //这两个参数很重要，可以判断是否需要转为树或者列表的区间值
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                // 检查链表中是否已经包含key
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
           }
       }
            // 覆盖value
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                //返回老的值，-------------------->case 4:put之后的返回值代表什么含义
                return oldValue;
            }
        }
    ++modCount;//容错机制
    //如果数组的长度大于阈值，扩充数组
    if (++size > threshold)
    	resize();
    afterNodeInsertion(evict);
	return null;
}
~~~

##### TreeMap=红黑树，也就是一个平衡二叉树

TreeMap可以实现存储元素的自动排序。在TreeMap中，键值对之间按键有序。默认按照键的自然顺序排序



### (3),LinkedHashMap

LinkedHashMap能够保持元素插入的顺序，也提供快速访问的能力

