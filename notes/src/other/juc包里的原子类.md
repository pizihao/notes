# JUC里的原子类

Atomic 是指一个操作是不可中断的。即使是在多个线程一起执行的时候，一个操作一旦开始，就不会被其他线程干扰。



java.util.concurrent.atomic包下存放着JUC里的原子类。

### 基本数据类型的原子类

#### AtomicBoolean  操作boolean类型

| 返回值类型 | 方法说明                                                     |
| ---------- | ------------------------------------------------------------ |
| `boolean`  | `compareAndSet(boolean expect,  boolean update)`  <br/>如果当前值为 `==`的预期值，则将该值原子设置为给定的更新值。 |
| `boolean`  | `get()`  <br/>返回当前值。                                   |
| `boolean`  | `getAndSet(boolean newValue)`  <br/>将原子设置为给定值并返回上一个值。 |
| `void`     | `set(boolean newValue)`  <br/>无条件地设置为给定的值。       |
| `String`   | `toString()`  <br/>返回当前值的String表示形式。              |
| `boolean`  | `weakCompareAndSet(boolean expect,  boolean update)`  <br/>如果当前值为 `==`为预期值，则将该值原子设置为给定的更新值。 |

- #### AtomicBoolean

  ```
  public AtomicBoolean(boolean initialValue)
  ```

  用给定的初始值创建一个新的 `AtomicBoolean` 。 

- #### AtomicBoolean

  ```
  public AtomicBoolean()
  ```

  创建一个新的 `AtomicBoolean` ，初始值为 `false` 。 

- #### get

  ```
  public final boolean get()
  ```

  返回当前值。 

- #### compareAndSet

  ```
  public final boolean compareAndSet(boolean expect,
                                     boolean update)
  ```

  如果当前值为 `==`的预期值，则将该值原子设置为给定的更新值。 

- #### weakCompareAndSet

  ```
  public boolean weakCompareAndSet(boolean expect,
                                   boolean update)
  ```

  如果当前值为`==`为预期值，则将该值原子设置为给定的更新值。

- #### set

  ```
  public final void set(boolean newValue)
  ```

  无条件地设置为给定的值。 

- #### getAndSet

  ```
  public final boolean getAndSet(boolean newValue)
  ```

  将原子设置为给定值并返回上一个值。 

- #### toString

  ```
  public String toString()
  ```

  返回当前值的String表示形式。 

#### AtomicInteger  操作int类型

| 返回值类型 | 方法说明                                                     |
| ---------- | ------------------------------------------------------------ |
| `int`      | `accumulateAndGet(int x,  IntBinaryOperator accumulatorFunction)`  <br/>使用将给定函数应用于当前值和给定值的结果原子更新当前值，返回更新后的值。 |
| `int`      | `addAndGet(int delta)`  <br/>将给定的值原子地添加到当前值。  |
| `boolean`  | `compareAndSet(int expect,  int update)`  <br/>如果当前值 `==`为预期值，则将该值原子设置为给定的更新值。 |
| `int`      | `decrementAndGet()`  原子减1当前值。                         |
| `int`      | `get()`  <br/>获取当前值。                                   |
| `int`      | `getAndAccumulate(int x,  IntBinaryOperator accumulatorFunction)` <br/> 使用给定函数应用给当前值和给定值的结果原子更新当前值，返回上一个值。 |
| `int`      | `getAndAdd(int delta)`  <br/>将给定的值原子地添加到当前值。  |
| `int`      | `getAndDecrement()`  <br/>原子减1当前值。                    |
| `int`      | `getAndIncrement()`  <br/>原子上增加一个当前值。             |
| `int`      | `getAndSet(int newValue)`  <br/>将原子设置为给定值并返回旧值。 |
| `int`      | `getAndUpdate(IntUnaryOperator updateFunction)`  <br/>用应用给定函数的结果原子更新当前值，返回上一个值。 |
| `int`      | `incrementAndGet()`  <br/>原子上增加一个当前值。             |
| `int`      | `intValue()`  <br/>将 `AtomicInteger`的值作为 `int` 。       |
| `void`     | `set(int newValue)`  <br/>设置为给定值。                     |

- #### AtomicInteger

  ```
  public AtomicInteger(int initialValue)
  ```

  用给定的初始值创建一个新的AtomicInteger。 

- #### AtomicInteger

  ```
  public AtomicInteger()
  ```

  创建一个新的AtomicInteger，初始值为 `0` 。 

- #### get

  ```
  public final int get()
  ```

  获取当前值。 

- #### set

  ```
  public final void set(int newValue)
  ```

  修改为给定值。 

- #### getAndSet

  ```
  public final int getAndSet(int newValue)
  ```

  将原子设置为给定值并返回旧值。 

- #### compareAndSet

  ```
  public final boolean compareAndSet(int expect, int update)
  ```

  如果当前值为 `==`为预期值，则将该值原子设置为给定的更新值。 

- #### weakCompareAndSet

  ```
  public final boolean weakCompareAndSet(int expect,
                                         int update)
  ```

  如果当前值为`==`的预期值，则将该值原子设置为给定的更新值。

- #### getAndIncrement

  ```
  public final int getAndIncrement()
  ```

  原子加一，先+1，再返回。 

- #### getAndDecrement

  ```
  public final int getAndDecrement()
  ```

  原子减一，先-1，再返回。 

- #### getAndAdd

  ```
  public final int getAndAdd(int delta)
  ```

  将给定的值原子地添加到当前值，先返回，再处理。 

- #### incrementAndGet

  ```
  public final int incrementAndGet()
  ```

  原子上增加一个当前值，先返回，再 +1。 

- #### decrementAndGet

  ```
  public final int decrementAndGet()
  ```

  原子减少一个当前值，先返回，再 -1。 

- #### addAndGet

  ```
  public final int addAndGet(int delta)
  ```

  将给定的值原子地添加到当前值，先处理，再返回。 

- #### getAndUpdate

  ```
  public final int getAndUpdate(IntUnaryOperator updateFunction)
  ```

  用应用给定函数的结果原子更新当前值，返回上一个值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。 

- #### updateAndGet

  ```
  public final int updateAndGet(IntUnaryOperator updateFunction)
  ```

  使用给定函数的结果原子更新当前值，返回更新的值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。 

- #### getAndAccumulate

  ```
  public final int getAndAccumulate(int x, IntBinaryOperator accumulatorFunction)
  ```

  使用给定函数应用给当前值和给定值的结果原子更新当前值，返回上一个值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。  该函数应用当前值作为其第一个参数，给定的更新作为第二个参数。 

- #### accumulateAndGet

  ```
  public final int accumulateAndGet(int x, IntBinaryOperator accumulatorFunction)
  ```

  使用将给定函数应用于当前值和给定值的结果原子更新当前值，返回更新后的值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。  该函数应用当前值作为其第一个参数，给定的更新作为第二个参数。 

- #### toString

  ```
  public String toString()
  ```

  返回当前值的String表示形式。 

- #### intValue

  ```
  public int intValue()
  ```

  将 `AtomicInteger`的值作为 `int` 。 

#### AtomicLong  操作long类型

| 返回值类型 | 方法说明                                                     |
| ---------- | ------------------------------------------------------------ |
| `long`     | `accumulateAndGet(long x,  LongBinaryOperator accumulatorFunction)`  <br/>使用将给定函数应用于当前值和给定值的结果原子更新当前值，返回更新后的值。 |
| `long`     | `addAndGet(long delta)`  <br/>将给定的值原子地添加到当前值。 |
| `boolean`  | `compareAndSet(long expect,  long update)`  <br/>如果当前值为 `==` ，则将原值设置为给定的更新值。 |
| `long`     | `decrementAndGet()`  <br/>原子减1当前值。                    |
| `long`     | `get()`  <br/>获取当前值。                                   |
| `long`     | `getAndAccumulate(long x,  LongBinaryOperator accumulatorFunction)`  <br/>使用给定函数应用给当前值和给定值的结果原子更新当前值，返回上一个值。 |
| `long`     | `getAndAdd(long delta)`  <br/>将给定的值原子地添加到当前值。 |
| `long`     | `getAndDecrement()`  <br/>原子减1当前值。                    |
| `long`     | `getAndIncrement()`  <br/>原子上增加一个当前值。             |
| `long`     | `getAndSet(long newValue)`  <br/>将原子设置为给定值并返回旧值。 |
| `long`     | `getAndUpdate(LongUnaryOperator updateFunction)`  <br/>用应用给定函数的结果原子更新当前值，返回上一个值。 |
| `long`     | `incrementAndGet()`  <br/>原子上增加一个当前值。             |
| `void`     | `set(long newValue)`  <br/>设置为给定值。                    |
| `long`     | `updateAndGet(LongUnaryOperator updateFunction)`  <br/>使用给定函数的结果原子更新当前值，返回更新的值。 |
| `boolean`  | `weakCompareAndSet(long expect,  long update)`  <br/>如果当前值为 `==` ，则将原值设置为给定的更新值。 |

- #### AtomicLong

  ```
  public AtomicLong(long initialValue)
  ```

  用给定的初始值创建一个新的AtomicLong。 

- #### AtomicLong

  ```
  public AtomicLong()
  ```

  创建一个新的AtomicLong，初始值为 `0` 。 

- #### get

  ```
  public final long get()
  ```

  获取当前值。 

- #### set

  ```
  public final void set(long newValue)
  ```

  设置为给定值。 

- #### getAndSet

  ```
  public final long getAndSet(long newValue)
  ```

  将原子设置为给定值并返回旧值。 

- #### compareAndSet

  ```
  public final boolean compareAndSet(long expect,
                                     long update)
  ```

  如果当前值为 `==`为预期值，则将该值原子设置为给定的更新值。 

- #### weakCompareAndSet

  ```
  public final boolean weakCompareAndSet(long expect,
                                         long update)
  ```

  如果当前值为`==` ，则将原值设置为给定的更新值。

- #### getAndIncrement

  ```
  public final long getAndIncrement()
  ```

  原子上增加一个当前值。 

- #### getAndDecrement

  ```
  public final long getAndDecrement()
  ```

  原子减1当前值。 

- #### getAndAdd

  ```
  public final long getAndAdd(long delta)
  ```

  将给定的值原子地添加到当前值。 

- #### incrementAndGet

  ```
  public final long incrementAndGet()
  ```

  原子上增加一个当前值。 

- #### decrementAndGet

  ```
  public final long decrementAndGet()
  ```

  原子减1当前值。 

- #### addAndGet

  ```
  public final long addAndGet(long delta)
  ```

  将给定的值原子地添加到当前值。 

- #### getAndUpdate

  ```
  public final long getAndUpdate(LongUnaryOperator updateFunction)
  ```

  用应用给定函数的结果原子更新当前值，返回上一个值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。 

- #### updateAndGet

  ```
  public final long updateAndGet(LongUnaryOperator updateFunction)
  ```

  使用给定函数的结果原子更新当前值，返回更新的值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。 

- #### getAndAccumulate

  ```
  public final long getAndAccumulate(long x, LongBinaryOperator accumulatorFunction)
  ```

  使用给定函数应用给当前值和给定值的结果原子更新当前值，返回上一个值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。  该函数应用当前值作为其第一个参数，给定的更新作为第二个参数。 

- #### accumulateAndGet

  ```
  public final long accumulateAndGet(long x, LongBinaryOperator accumulatorFunction)
  ```

  使用将给定函数应用于当前值和给定值的结果原子更新当前值，返回更新后的值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。  该函数应用当前值作为其第一个参数，给定的更新作为第二个参数。 

### 数组类型的原子类

#### AtomicIntegerArray  整形数组原子类

| 返回值类型 | 方法说明                                                     |
| ---------- | ------------------------------------------------------------ |
| `int`      | `accumulateAndGet(int i,  int x, IntBinaryOperator accumulatorFunction)`  <br/>以索引 `i`原子更新元素，并将给定函数应用于当前值和给定值，返回更新后的值。 |
| `int`      | `addAndGet(int i,  int delta)`  <br/>原子地将索引 `i`的给定值添加到元素。 |
| `boolean`  | `compareAndSet(int i,  int expect, int update)`  <br/>如果当前值 `==`为预期值，则 `i`地将位置  `i`处的元素设置为给定的更新值。 |
| `int`      | `decrementAndGet(int i)` <br/> 索引 `i`的元素原子 `i` 。     |
| `int`      | `get(int i)`  <br/>获取位置 `i`的当前值。                    |
| `int`      | `getAndAccumulate(int i,  int x, IntBinaryOperator accumulatorFunction)`  <br/>以索引 `i`原子更新元素，并将给定函数应用于当前值和给定值，返回上一个值。 |
| `int`      | `getAndAdd(int i,  int delta)`  <br/>将索引 `i`的给定值原子地添加到元素。 |
| `int`      | `getAndDecrement(int i)`  <br/>索引 `i`的元素原子 `i` 。     |
| `int`      | `getAndIncrement(int i)`  <br/>在索引 `i`原子上增加一个元素。 |
| `int`      | `getAndSet(int i,  int newValue)`  <br/>将位置 `i`的元素原子设置为给定值并返回旧值。 |
| `int`      | `getAndUpdate(int i,  IntUnaryOperator updateFunction)`  <br/>使用应用给定函数的结果原子更新索引 `i`处的元素，返回上一个值。 |
| `int`      | `incrementAndGet(int i)`  <br/>在索引 `i`原子上增加一个元素。 |
| `int`      | `length()`  <br/>返回数组的长度。                            |
| `void`     | `set(int i,  int newValue)`  <br/>将位置 `i`处的元素设置为给定值。 |
| `int`      | `updateAndGet(int i,  IntUnaryOperator updateFunction)`  <br/>以索引 `i`原子更新应用给定函数的结果，返回更新的值。 |
| `boolean`  | `weakCompareAndSet(int i,  int expect, int update)`  <br/>如果当前值 `==`为预期值，则 `i`地将位置  `i`处的元素设置为给定的更新值。 |

- #### AtomicIntegerArray

  ```
  public AtomicIntegerArray(int length)
  ```

  创建给定长度的新AtomicIntegerArray，所有元素最初为零。 

- #### AtomicIntegerArray

  ```
  public AtomicIntegerArray(int[] array)
  ```

  创建一个新的AtomicIntegerArray，其长度与从给定数组复制的所有元素相同。 

- #### length

  ```
  public final int length()
  ```

  返回数组的长度。 

- #### get

  ```
  public final int get(int i)
  ```

  获取位置 `i`的当前值。 

- #### set

  ```
  public final void set(int i,int newValue)
  ```

  将位置 `i`的元素设置为给定值。 

- #### getAndSet

  ```
  public final int getAndSet(int i, int newValue)
  ```

  将位置 `i`的元素原子设置为给定值并返回旧值。 

- #### compareAndSet

  ```
  public final boolean compareAndSet(int i,int expect,int update)
  ```

  如果当前值 `==`为预期值，则 `i`地将位置  `i`处的元素设置为给定的更新值。 

- #### weakCompareAndSet

  ```
  public final boolean weakCompareAndSet(int i,int expect,int update)
  ```

  如果当前值`==`为预期值，则`i`地将位置`i`处的元素设置为给定的更新值。

- #### getAndIncrement

  ```
  public final int getAndIncrement(int i)
  ```

  在索引 `i`原子方式递增一个元素。 

- #### getAndDecrement

  ```
  public final int getAndDecrement(int i)
  ```

  索引 `i`的元素原子 `i` 。 

- #### getAndAdd

  ```
  public final int getAndAdd(int i, int delta)
  ```

  将索引 `i`的给定值原子地添加到元素。 

- #### incrementAndGet

  ```
  public final int incrementAndGet(int i)
  ```

  索引 `i`处的元素按 `i` 。 

- #### decrementAndGet

  ```
  public final int decrementAndGet(int i)
  ```

  索引 `i`的元素原子 `i` 。 

- #### addAndGet

  ```
  public final int addAndGet(int i, int delta)
  ```

  将索引 `i`的给定值原子地添加到元素。 

- #### getAndUpdate

  ```
  public final int getAndUpdate(int i, IntUnaryOperator updateFunction)
  ```

  以索引`i`原子更新元素，并使用给定函数的结果返回上一个值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。 

- #### updateAndGet

  ```
  public final int updateAndGet(int i, IntUnaryOperator updateFunction)
  ```

  用索引`i`原子更新应用给定函数的结果，返回更新后的值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。 

- #### getAndAccumulate

  ```
  public final int getAndAccumulate(int i,int x,IntBinaryOperator accumulatorFunction)
  ```

  以索引`i`原子更新元素，并将给定函数应用于当前值和给定值，返回上一个值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。  该函数以索引`i`作为其第一个参数的当前值应用，给定的更新作为第二个参数。 

- #### accumulateAndGet

  ```
  public final int accumulateAndGet(int i,int x,IntBinaryOperator accumulatorFunction)
  ```

  以索引`i`原子更新元素，并将给定函数应用于当前值和给定值，返回更新后的值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。  该函数应用索引`i`作为其第一个参数的当前值，给定的更新作为第二个参数。 

#### AtomicReferenceArray  引用类型数组原子类

| 返回值类型 | 方法说明                                                     |
| ---------- | ------------------------------------------------------------ |
| `E`        | `accumulateAndGet(int i,  E x,  BinaryOperator accumulatorFunction)`  以索引 `i`原子更新元素，并将给定函数应用于当前值和给定值，返回更新后的值。 |
| `boolean`  | `compareAndSet(int i,  E expect,  E update)`  如果当前值 `==`为预期值，则 `i`地将位置  `i`处的元素设置为给定的更新值。 |
| `E`        | `get(int i)`  获取位置 `i`的当前值。                         |
| `E`        | `getAndAccumulate(int i,  E x,  BinaryOperator accumulatorFunction)`  以索引 `i`原子更新元素，并将给定的函数应用于当前值和给定值，返回上一个值。 |
| `E`        | `getAndSet(int i,  E newValue)`  将位置 `i`的元素原子设置为给定值并返回旧值。 |
| `E`        | `getAndUpdate(int i,  UnaryOperator updateFunction)`  用索引 `i`原子更新元素，并使用给定函数的结果返回上一个值。 |
| `void`     | `lazySet(int i,  E newValue)`  最终将位置 `i`的元素设置为给定值。 |
| `int`      | `length()`  返回数组的长度。                                 |
| `void`     | `set(int i,  E newValue)`  将位置 `i`的元素设置为给定值。    |
| `String`   | `toString()`  返回数组的当前值的String表示形式。             |
| `E`        | `updateAndGet(int i,  UnaryOperator updateFunction)`  用索引 `i`原子更新应用给定函数的结果，返回更新后的值。 |
| `boolean`  | `weakCompareAndSet(int i,  E expect,  E update)`  如果当前值 `==`为预期值，则 `i`地将位置  `i`处的元素设置为给定的更新值。 |

- #### AtomicReferenceArray

  ```
  public AtomicReferenceArray(int length)
  ```

  创建给定长度的新AtomicReferenceArray，所有元素最初为null。 

- #### AtomicReferenceArray

  ```
  public AtomicReferenceArray(E[] array)
  ```

  创建一个与原始数组相同的长度和所有元素的AtomicReferenceArray。 

- #### length

  ```
  public final int length()
  ```

  返回数组的长度。 

- #### get

  ```
  public final E get(int i)
  ```

  获取位置 `i`的当前值。 

- #### set

  ```
  public final void set(int i,
                        E newValue)
  ```

  将位置 `i`的元素设置为给定值。 

- #### getAndSet

  ```
  public final E getAndSet(int i,
                           E newValue)
  ```

  将位置 `i`处的元素原子设置为给定值并返回旧值。 

- #### compareAndSet

  ```
  public final boolean compareAndSet(int i,
                                     E expect,
                                     E update)
  ```

  如果当前值 `==`为期望值，则 `i`地将位置  `i`处的元素设置为给定的更新值。 

- #### weakCompareAndSet

  ```
  public final boolean weakCompareAndSet(int i,
                                         E expect,
                                         E update)
  ```

  如果当前值`==`为预期值，则`i`地将位置`i`处的元素设置为给定的更新值。

- #### getAndUpdate

  ```
  public final E getAndUpdate(int i,
                              UnaryOperator<E> updateFunction)
  ```

  用索引`i`的元素原子更新应用给定函数的结果，返回上一个值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。 

- #### updateAndGet

  ```
  public final E updateAndGet(int i,
                              UnaryOperator<E> updateFunction)
  ```

  使用应用给定函数的结果，原子更新索引`i`处的元素，返回更新的值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。 

- #### getAndAccumulate

  ```
  public final E getAndAccumulate(int i,
                                  E x,
                                  BinaryOperator<E> accumulatorFunction)
  ```

  以索引`i`原子更新元素，并将给定的函数应用于当前值和给定值，返回上一个值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。  该函数以索引`i`作为其第一个参数的当前值应用，给定的更新作为第二个参数。 

- #### accumulateAndGet

  ```
  public final E accumulateAndGet(int i,
                                  E x,
                                  BinaryOperator<E> accumulatorFunction)
  ```

  以索引`i`原子更新元素，并将给定函数应用于当前值和给定值，返回更新后的值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。  该函数应用索引`i`作为其第一个参数的当前值，给定的更新作为第二个参数。 

#### AtomicLongArray  长整形数组原子类

| 返回值类型 | 方法说明                                                     |
| ---------- | ------------------------------------------------------------ |
| `long`     | `accumulateAndGet(int i,  long x, LongBinaryOperator accumulatorFunction)`  以索引 `i`原子更新元素，并将给定函数应用于当前值和给定值，返回更新后的值。 |
| `long`     | `addAndGet(int i,  long delta)`  原子地将索引 `i`的给定值添加到元素。 |
| `boolean`  | `compareAndSet(int i,  long expect, long update)`  如果当前值 `==`为预期值，则 `i`地将位置  `i`处的元素设置为给定的更新值。 |
| `long`     | `decrementAndGet(int i)`  索引 `i`的元素原子 `i` 。          |
| `long`     | `get(int i)`  获取位置 `i`的当前值。                         |
| `long`     | `getAndAccumulate(int i,  long x, LongBinaryOperator accumulatorFunction)`  以索引 `i`原子更新元素，并将给定函数应用于当前值和给定值，返回上一个值。 |
| `long`     | `getAndAdd(int i,  long delta)`  原子地将给定的值添加到索引 `i`中的元素。 |
| `long`     | `getAndDecrement(int i)`  索引 `i`的元素原子 `i` 。          |
| `long`     | `getAndIncrement(int i)`  在索引 `i`原子上增加一个元素。     |
| `long`     | `getAndSet(int i,  long newValue)`  将位置 `i`的元素原子设置为给定值并返回旧值。 |
| `long`     | `getAndUpdate(int i,  LongUnaryOperator updateFunction)`  以索引 `i`原子更新应用给定函数的结果，返回上一个值。 |
| `long`     | `incrementAndGet(int i)`  索引号为 `i`的元素原子地增加一个。 |
| `int`      | `length()`  返回数组的长度。                                 |
| `void`     | `set(int i,  long newValue)`  将位置 `i`处的元素设置为给定值。 |
| `long`     | `updateAndGet(int i,  LongUnaryOperator updateFunction)`  用索引 `i`的元素原子更新应用给定函数的结果，返回更新后的值。 |
| `boolean`  | `weakCompareAndSet(int i,  long expect, long update)`  如果当前值 `==`为预期值，则 `i`地将位置  `i`处的元素设置为给定的更新值。 |

- #### AtomicLongArray

  ```
  public AtomicLongArray(int length)
  ```

  创建给定长度的新AtomicLongArray，所有元素最初为零。 

- #### AtomicLongArray

  ```
  public AtomicLongArray(long[] array)
  ```

  创建一个与原始数组相同长度的新的AtomicLongArray，并复制所有元素。 

- #### length

  ```
  public final int length()
  ```

  返回数组的长度。 

- #### get

  ```
  public final long get(int i)
  ```

  获取位置 `i`的当前值。 

- #### set

  ```
  public final void set(int i,
                        long newValue)
  ```

  将位置 `i`处的元素设置为给定值。

- #### getAndSet

  ```
  public final long getAndSet(int i,
                              long newValue)
  ```

  将位置 `i`处的元素原子设置为给定值并返回旧值。 

- #### compareAndSet

  ```
  public final boolean compareAndSet(int i,
                                     long expect,
                                     long update)
  ```

  如果当前值 `==`为预期值，则 `i`地将位置  `i`处的元素设置为给定的更新值。 

- #### weakCompareAndSet

  ```
  public final boolean weakCompareAndSet(int i,
                                         long expect,
                                         long update)
  ```

  如果当前值`==`为预期值，则`i`地将位置`i`处的元素设置为给定的更新值。

- #### getAndIncrement

  ```
  public final long getAndIncrement(int i)
  ```

  索引号为i的原子按一个元素 `i` 。 

- #### getAndDecrement

  ```
  public final long getAndDecrement(int i)
  ```

  索引 `i`的元素原子减少一个。 

- #### getAndAdd

  ```
  public final long getAndAdd(int i,
                              long delta)
  ```

  原子地将索引 `i`的给定值添加到元素。 

- #### incrementAndGet

  ```
  public final long incrementAndGet(int i)
  ```

  按索引 `i`原子地增加一个元素。 

- #### decrementAndGet

  ```
  public final long decrementAndGet(int i)
  ```

  索引 `i`的元素原子 `i` 。 

- #### addAndGet

  ```
  public long addAndGet(int i,
                        long delta)
  ```

  在索引原子将给定值的元素 `i` 。 

- #### getAndUpdate

  ```
  public final long getAndUpdate(int i,
                                 LongUnaryOperator updateFunction)
  ```

  用索引`i`原子更新应用给定函数的结果，返回上一个值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。 

- #### updateAndGet

  ```
  public final long updateAndGet(int i,
                                 LongUnaryOperator updateFunction)
  ```

  用索引`i`的元素进行原子更新，并应用给定的函数，返回更新的值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。 

- #### getAndAccumulate

  ```
  public final long getAndAccumulate(int i,
                                     long x,
                                     LongBinaryOperator accumulatorFunction)
  ```

  以索引`i`原子更新元素，并将给定函数应用于当前值和给定值，返回上一个值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。  该函数应用索引`i`作为其第一个参数的当前值，给定更新作为第二个参数。 

- #### accumulateAndGet

  ```
  public final long accumulateAndGet(int i,
                                     long x,
                                     LongBinaryOperator accumulatorFunction)
  ```

  以索引`i`原子更新元素，并将给定函数应用于当前值和给定值，返回更新后的值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。  该函数应用索引`i`作为其第一个参数的当前值，给定更新作为第二个参数。 

### 对象的属性修改类型的原子类

#### AtomicIntegerFieldUpdater   原子更新整形字段的更新器

| 返回值类型                          | 方法说明                                                     |
| ----------------------------------- | ------------------------------------------------------------ |
| `int`                               | `accumulateAndGet(T obj,  int x, IntBinaryOperator accumulatorFunction)`  原子更新由此更新程序管理的给定对象的字段，并将给定函数应用于当前值和给定值，返回更新后的值。 |
| `int`                               | `addAndGet(T obj,  int delta)`  将给定值原子地添加到由此更新程序管理的给定对象的字段的当前值。 |
| `abstract boolean`                  | `compareAndSet(T obj,  int expect, int update)`  如果当前值 `==`为预期值，则将由此更新程序管理的给定对象的字段原子设置为给定的更新值。 |
| `int`                               | `decrementAndGet(T obj)`  由此更新程序管理的给定对象的字段的当前值原子减1。 |
| `abstract int`                      | `get(T obj)`  获取由此更新程序管理的给定对象的字段中保留的当前值。 |
| `int`                               | `getAndAccumulate(T obj,  int x, IntBinaryOperator accumulatorFunction)`  原子更新由此更新程序管理的给定对象的字段，并将给定函数应用于当前值和给定值，返回上一个值。 |
| `int`                               | `getAndAdd(T obj,  int delta)`  将给定值原子地添加到由此更新程序管理的给定对象的字段的当前值。 |
| `int`                               | `getAndDecrement(T obj)`  由此更新程序管理的给定对象的字段的当前值原子减1。 |
| `int`                               | `getAndIncrement(T obj)`  由此更新程序管理的给定对象的字段的当前值以原子方式递增1。 |
| `int`                               | `getAndSet(T obj,  int newValue)`  将由此更新程序管理的给定对象的字段原子设置为给定值，并返回旧值。 |
| `int`                               | `getAndUpdate(T obj,  IntUnaryOperator updateFunction)`  使用应用给定函数的结果原子更新由此更新程序管理的给定对象的字段，返回上一个值。 |
| `int`                               | `incrementAndGet(T obj)`  由此更新程序管理的给定对象的字段的当前值以原子方式递增1。 |
| `static  AtomicIntegerFieldUpdater` | `newUpdater(类 tclass, String fieldName)`  创建并返回具有给定字段的对象的更新程序。 |
| `abstract void`                     | `set(T obj,  int newValue)`  将由此更新程序管理的给定对象的字段设置为给定的更新值。 |
| `int`                               | `updateAndGet(T obj,  IntUnaryOperator updateFunction)`  原子更新由此更新程序管理的给定对象的字段与应用给定函数的结果，返回更新的值。 |
| `abstract boolean`                  | `weakCompareAndSet(T obj,  int expect, int update)`  如果当前值 `==`为预期值，则将由此更新程序管理的给定对象的字段原子设置为给定的更新值。 |

- #### newUpdater

  ```
  public static <U> AtomicIntegerFieldUpdater<U> newUpdater(类<U> tclass,
                                                            String fieldName)
  ```

  创建并返回具有给定字段的对象的更新程序。  需要Class参数来检查反射类型和泛型类型是否匹配。 

- #### compareAndSet

  ```
  public abstract boolean compareAndSet(T obj,
                                        int expect,
                                        int update)
  ```

  如果当前值为`==`由此更新程序管理的给定对象的字段设置为给定的更新值。  相对于其他对`compareAndSet`和`set`调用，此方法保证是原子的，但不一定与本领域的其他更改相关。

- #### weakCompareAndSet

  ```
  public abstract boolean weakCompareAndSet(T obj,
                                            int expect,
                                            int update)
  ```

  如果当前值为`==`由此更新程序管理的给定对象的字段原子设置为给定的更新值。相对于其他对`compareAndSet`和`set`调用，此方法保证是原子的，但不一定与本领域的其他更改相关。

- #### set

  ```
  public abstract void set(T obj,
                           int newValue)
  ```

  将由此更新程序管理的给定对象的字段设置为给定的更新值。  该操作被确保作为一个易失性存储，以便随后调用`compareAndSet` 。 

- #### get

  ```
  public abstract int get(T obj)
  ```

  获取由此更新程序管理的给定对象的字段中保留的当前值。

- #### getAndSet

  ```
  public int getAndSet(T obj,
                       int newValue)
  ```

  将由此更新程序管理的给定对象的字段原子设置为给定值，并返回旧值。 

- #### getAndIncrement

  ```
  public int getAndIncrement(T obj)
  ```

  由此更新程序管理的给定对象的字段的当前值以原子方式递增1。 

- #### getAndDecrement

  ```
  public int getAndDecrement(T obj)
  ```

  由此更新程序管理的给定对象的字段的当前值原子减1。

- #### getAndAdd

  ```
  public int getAndAdd(T obj,
                       int delta)
  ```

  将给定值原子地添加到由此更新程序管理的给定对象的字段的当前值。 

- #### incrementAndGet

  ```
  public int incrementAndGet(T obj)
  ```

  由此更新程序管理的给定对象的字段的当前值以原子方式递增1。 

- #### decrementAndGet

  ```
  public int decrementAndGet(T obj)
  ```

  由此更新程序管理的给定对象的字段的当前值原子减1。 

- #### addAndGet

  ```
  public int addAndGet(T obj,int delta)
  ```

  将给定值原子地添加到由此更新程序管理的给定对象的字段的当前值。 

- #### getAndUpdate

  ```
  public final int getAndUpdate(T obj,IntUnaryOperator updateFunction)
  ```

  使用应用给定函数的结果原子更新由此更新程序管理的给定对象的字段，返回上一个值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。 

- #### updateAndGet

  ```
  public final int updateAndGet(T obj,IntUnaryOperator updateFunction)
  ```

  原子更新由此更新程序管理的给定对象的字段与应用给定函数的结果，返回更新的值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。 

- #### getAndAccumulate

  ```
  public final int getAndAccumulate(T obj,int x,IntBinaryOperator accumulatorFunction)
  ```

  原子更新由此更新程序管理的给定对象的字段，并将给定函数应用于当前值和给定值，返回上一个值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。  该函数应用当前值作为其第一个参数，给定的更新作为第二个参数。 

- #### accumulateAndGet

  ```
  public final int accumulateAndGet(T obj,
                                    int x,
                                    IntBinaryOperator accumulatorFunction)
  ```

  原子更新由此更新程序管理的给定对象的字段，并将给定函数应用于当前值和给定值，返回更新后的值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。  该函数应用当前值作为其第一个参数，给定的更新作为第二个参数。 

#### AtomicLongFieldUpdater  原子更新长整形字段的更新器

| 返回值类型                       | 方法说明                                                     |
| -------------------------------- | ------------------------------------------------------------ |
| `long`                           | `accumulateAndGet(T obj,  long x, LongBinaryOperator accumulatorFunction)`  原子更新由此更新程序管理的给定对象的字段，并将给定函数应用于当前值和给定值，返回更新后的值。 |
| `long`                           | `addAndGet(T obj,  long delta)`  将给定值原子地添加到由此更新程序管理的给定对象的字段的当前值。 |
| `abstract boolean`               | `compareAndSet(T obj,  long expect, long update)`  如果当前值为 `==`的预期值，则将由此更新程序管理的给定对象的字段原子设置为给定的更新值。 |
| `long`                           | `decrementAndGet(T obj)`  由此更新程序管理的给定对象的字段的当前值原子减1。 |
| `abstract long`                  | `get(T obj)`  获取由此更新程序管理的给定对象的字段中保留的当前值。 |
| `long`                           | `getAndAccumulate(T obj,  long x, LongBinaryOperator accumulatorFunction)`  原子更新由此更新程序管理的给定对象的字段，并将给定函数应用于当前值和给定值，返回上一个值。 |
| `long`                           | `getAndAdd(T obj,  long delta)`  将给定值原子地添加到由此更新程序管理的给定对象的字段的当前值。 |
| `long`                           | `getAndDecrement(T obj)`  由此更新程序管理的给定对象的字段的当前值原子减1。 |
| `long`                           | `getAndIncrement(T obj)`  由此更新程序管理的给定对象的字段的当前值以原子方式递增1。 |
| `long`                           | `getAndSet(T obj,  long newValue)`  将由此更新程序管理的给定对象的字段原子设置为给定值，并返回旧值。 |
| `long`                           | `getAndUpdate(T obj,  LongUnaryOperator updateFunction)`  使用应用给定函数的结果原子更新由此更新程序管理的给定对象的字段，返回上一个值。 |
| `long`                           | `incrementAndGet(T obj)`  由此更新程序管理的给定对象的字段的当前值以原子方式递增1。 |
| `static  AtomicLongFieldUpdater` | `newUpdater(class tclass, String fieldName)`  创建并返回具有给定字段的对象的更新程序。 |
| `abstract void`                  | `set(T obj,  long newValue)`  将由此更新程序管理的给定对象的字段设置为给定的更新值。 |
| `long`                           | `updateAndGet(T obj,  LongUnaryOperator updateFunction)`  原子更新由此更新程序管理的给定对象的字段与应用给定函数的结果，返回更新的值。 |
| `abstract boolean`               | `weakCompareAndSet(T obj,  long expect, long update)`  如果当前值 `==`为预期值，则将由此更新程序管理的给定对象的字段原子设置为给定的更新值。 |

- #### newUpdater

  ```
  public static <U> AtomicLongFieldUpdater<U> newUpdater(class<U> tclass,String fieldName)
  ```

  创建并返回具有给定字段的对象的更新程序。  需要Class参数来检查反射类型和泛型类型是否匹配。 

- #### compareAndSet

  ```
  public abstract boolean compareAndSet(T obj,long expect,long update)
  ```

  如果当前值为`==`的预期值，则将由此更新程序管理的给定对象的字段原子设置为给定的更新值。  相对于其他对`compareAndSet`和`set`调用，此方法保证是原子的，但不一定与本领域的其他更改相关。 

- #### weakCompareAndSet

  ```
  public abstract boolean weakCompareAndSet(T obj,long expect,long update)
  ```

  如果当前值为`==`由此更新程序管理的给定对象的字段设置为给定的更新值。相对于其他对`compareAndSet`和`set`调用，该方法保证是原子的，但不一定与本领域的其他更改相关。

- #### set

  ```
  public abstract void set(T obj,long newValue)
  ```

  将由此更新程序管理的给定对象的字段设置为给定的更新值。  该操作被确保作为一个易失性存储，以便随后调用`compareAndSet` 。 

- #### get

  ```
  public abstract long get(T obj)
  ```

  获取由此更新程序管理的给定对象的字段中保留的当前值。 

- #### getAndSet

  ```
  public long getAndSet(T obj,long newValue)
  ```

  将由此更新程序管理的给定对象的字段原子设置为给定值，并返回旧值。 

- #### getAndIncrement

  ```
  public long getAndIncrement(T obj)
  ```

  由此更新程序管理的给定对象的字段的当前值以原子方式递增1。 

- #### getAndDecrement

  ```
  public long getAndDecrement(T obj)
  ```

  由此更新程序管理的给定对象的字段的当前值原子减1。 

- #### getAndAdd

  ```
  public long getAndAdd(T obj,long delta)
  ```

  将给定值原子地添加到由此更新程序管理的给定对象的字段的当前值。 

- #### incrementAndGet

  ```
  public long incrementAndGet(T obj)
  ```

  由此更新程序管理的给定对象的字段的当前值以原子方式递增1。 

- #### decrementAndGet

  ```
  public long decrementAndGet(T obj)
  ```

  由此更新程序管理的给定对象的字段的当前值原子减1。 

- #### addAndGet

  ```
  public long addAndGet(T obj,long delta)
  ```

  将给定值原子地添加到由此更新程序管理的给定对象的字段的当前值。 

- #### getAndUpdate

  ```
  public final long getAndUpdate(T obj,LongUnaryOperator updateFunction)
  ```

  使用应用给定函数的结果原子更新由此更新程序管理的给定对象的字段，返回上一个值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。

- #### updateAndGet

  ```
  public final long updateAndGet(T obj, LongUnaryOperator updateFunction)
  ```

  原子更新由此更新程序管理的给定对象的字段与应用给定函数的结果，返回更新的值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。 

- #### getAndAccumulate

  ```
  public final long getAndAccumulate(T obj,long x,LongBinaryOperator accumulatorFunction)
  ```

  原子更新由此更新程序管理的给定对象的字段，并将给定函数应用于当前值和给定值，返回上一个值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。  该函数应用当前值作为其第一个参数，给定的更新作为第二个参数。 

- #### accumulateAndGet

  ```
  public final long accumulateAndGet(T obj,long x,LongBinaryOperator accumulatorFunction)
  ```

  原子更新由此更新程序管理的给定对象的字段，并将给定函数应用于当前值和给定值，返回更新后的值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。  该函数应用当前值作为其第一个参数，给定的更新作为第二个参数。 

#### AtomicReferenceFieldUpdater  原子更新带有版本号的引用类型

| 返回值类型                            | 方法说明                                                     |
| ------------------------------------- | ------------------------------------------------------------ |
| `V`                                   | `accumulateAndGet(T obj,  V x,  BinaryOperator accumulatorFunction)`  原子更新由此更新程序管理的给定对象的字段，并将给定函数应用于当前值和给定值，返回更新后的值。 |
| `abstract boolean`                    | `compareAndSet(T obj,  V expect,  V update)`  如果当前值 `==`为预期值，则将由此更新程序管理的给定对象的字段原子设置为给定的更新值。 |
| `abstract V`                          | `get(T obj)`  获取由此更新程序管理的给定对象的字段中保留的当前值。 |
| `V`                                   | `getAndAccumulate(T obj,  V x,  BinaryOperator accumulatorFunction)`  原子更新由此更新程序管理的给定对象的字段，并将给定函数应用于当前值和给定值，返回上一个值。 |
| `V`                                   | `getAndSet(T obj,  V newValue)`  将由此更新程序管理的给定对象的字段原子设置为给定值，并返回旧值。 |
| `V`                                   | `getAndUpdate(T obj,  UnaryOperator updateFunction)`  使用应用给定函数的结果原子更新由此更新程序管理的给定对象的字段，返回上一个值。 |
| `static  AtomicReferenceFieldUpdater` | `newUpdater(类 tclass, 类 vclass, String fieldName)`  创建并返回具有给定字段的对象的更新程序。 |
| `abstract void`                       | `set(T obj,  V newValue)`  将由此更新程序管理的给定对象的字段设置为给定的更新值。 |
| `V`                                   | `updateAndGet(T obj,  UnaryOperator updateFunction)`  原子更新由此更新程序管理的给定对象的字段与应用给定函数的结果，返回更新的值。 |
| `abstract boolean`                    | `weakCompareAndSet(T obj,  V expect,  V update)`  如果当前值 `==`为预期值，则将由此更新程序管理的给定对象的字段原子设置为给定的更新值。 |



- #### newUpdater

  ```
  public static <U,W> AtomicReferenceFieldUpdater<U,W> newUpdater(类<U> tclass, 类<W> vclass,String fieldName)
  ```

  创建并返回具有给定字段的对象的更新程序。  需要Class参数来检查反射类型和通用类型是否匹配。 

- #### compareAndSet

  ```
  public abstract boolean compareAndSet(T obj, V expect, V update)
  ```

  如果当前值`==`为预期值，则将由此更新程序管理的给定对象的字段原子设置为给定的更新值。  相对于其他对`compareAndSet`和`set`调用，该方法保证是原子的，但不一定与本领域的其他更改相关。 

- #### weakCompareAndSet

  ```
  public abstract boolean weakCompareAndSet(T obj,V expect, V update)
  ```

  如果当前值为`==` ，则将此更新程序管理的给定对象的字段设置为给定的更新值。相对于其他对`compareAndSet`和`set`调用，此方法保证是原子的，但不一定与本领域的其他更改相关。

- #### set

  ```
  public abstract void set(T obj,V newValue)
  ```

  将由此更新程序管理的给定对象的字段设置为给定的更新值。  该操作确保充当相对于的后续调用易失性存储器`compareAndSet` 。

- #### get

  ```
  public abstract V get(T obj)
  ```

  获取由此更新程序管理的给定对象的字段中保留的当前值。 

- #### getAndSet

  ```
  public V getAndSet(T obj,V newValue)
  ```

  将由此更新程序管理的给定对象的字段原子设置为给定值，并返回旧值。 

- #### getAndUpdate

  ```
  public final V getAndUpdate(T obj,UnaryOperator<V> updateFunction)
  ```

  使用应用给定函数的结果原子更新由此更新程序管理的给定对象的字段，返回上一个值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。

- #### updateAndGet

  ```
  public final V updateAndGet(T obj,UnaryOperator<V> updateFunction)
  ```

  原子更新由此更新程序管理的给定对象的字段与应用给定函数的结果，返回更新的值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。 

- #### getAndAccumulate

  ```
  public final V getAndAccumulate(T obj, V x,BinaryOperator<V> accumulatorFunction)
  ```

  原子更新由此更新程序管理的给定对象的字段，并将给定函数应用于当前值和给定值，返回上一个值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。  该函数应用当前值作为其第一个参数，给定的更新作为第二个参数。

- #### accumulateAndGet

  ```
  public final V accumulateAndGet(T obj,V x, BinaryOperator<V> accumulatorFunction)
  ```

  原子更新由此更新程序管理的给定对象的字段，并将给定函数应用于当前值和给定值，返回更新后的值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。  该函数应用当前值作为其第一个参数，给定的更新作为第二个参数。 

### 引用类型的原子类

#### AtomicMarkableReference  原子更新带有标记位的引用类型

| 返回值类型 | 方法说明                                                     |
| ---------- | ------------------------------------------------------------ |
| `boolean`  | `attemptMark(V expectedReference,  boolean newMark)`  以原子方式设置标志给定的更新值的值，如果当前引用 `==`预期引用。 |
| `boolean`  | `compareAndSet(V expectedReference,  V newReference,  boolean expectedMark, boolean newMark)`  以原子方式设置该引用和标记给定的更新值的值，如果当前的参考是  `==`至预期的参考和当前标记等于预期标记。 |
| `V`        | `get(boolean[] markHolder)`  返回引用和标记的当前值。        |
| `V`        | `getReference()`  返回引用的当前值。                         |
| `boolean`  | `isMarked()`  返回标记的当前值。                             |
| `void`     | `set(V newReference,  boolean newMark)`  无条件地设置引用和标记的值。 |
| `boolean`  | `weakCompareAndSet(V expectedReference,  V newReference,  boolean expectedMark, boolean newMark)`  以原子方式设置该引用和标记给定的更新值的值，如果当前的参考是  `==`至预期的参考和当前标记等于预期标记。 |

- #### getReference

  ```
  public V getReference()
  ```

  返回引用的当前值。 

- #### isMarked

  ```
  public boolean isMarked()
  ```

  返回标记的当前值。 

- #### get

  ```
  public V get(boolean[] markHolder)
  ```

  返回引用和标记的当前值。 典型用法是`boolean[1] holder;  ref = v.get(holder);` 。

- #### weakCompareAndSet

  ```
  public boolean weakCompareAndSet(V expectedReference,V newReference,boolean expectedMark,boolean newMark)
  ```

  以原子方式设置该引用和标记给定的更新值的值，如果当前的参考是`==`至预期的参考和当前标记等于预期标记。

- #### compareAndSet

  ```
  public boolean compareAndSet(V expectedReference,V newReference, boolean expectedMark,boolean newMark)
  ```

  以原子方式设置该引用和标记给定的更新值的值，如果当前的参考是  `==`至预期的参考和当前标记等于预期标记。 

- #### set

  ```
  public void set(V newReference,boolean newMark)
  ```

  无条件地设置引用和标记的值。 

- #### attemptMark

  ```
  public boolean attemptMark(V expectedReference,boolean newMark)
  ```

  以原子方式设置标志给定的更新值的值，如果当前引用`==`预期引用。  对此操作的任何给定的调用可能会失败（返回`false`  ），但是当当前值保持预期值并且没有其他线程也尝试设置该值将最终成功时重复调用。 

#### AtomicReference  引用类型原子类

| 返回值类型 | 方法说明                                                     |
| ---------- | ------------------------------------------------------------ |
| `V`        | `accumulateAndGet(V x, BinaryOperator accumulatorFunction)`  使用将给定函数应用于当前值和给定值的结果原子更新当前值，返回更新后的值。 |
| `boolean`  | `compareAndSet(V expect,  V update)`  如果当前值 `==`为预期值，则将值设置为给定的更新值。 |
| `V`        | `get()`  获取当前值。                                        |
| `V`        | `getAndAccumulate(V x, BinaryOperator accumulatorFunction)`  使用给定函数应用给当前值和给定值的结果原子更新当前值，返回上一个值。 |
| `V`        | `getAndSet(V newValue)`  将原子设置为给定值并返回旧值。      |
| `V`        | `getAndUpdate(UnaryOperator updateFunction)`  用应用给定函数的结果原子更新当前值，返回上一个值。 |
| `void`     | `set(V newValue)`  设置为给定值。                            |
| `V`        | `updateAndGet(UnaryOperator updateFunction)`  使用给定函数的结果原子更新当前值，返回更新的值。 |
| `boolean`  | `weakCompareAndSet(V expect,  V update)`  如果当前值为 `==` ，则将原值设置为给定的更新值。 |

- #### AtomicReference

  ```
  public AtomicReference(V initialValue)
  ```

  用给定的初始值创建一个新的AtomicReference。 

- #### AtomicReference

  ```
  public AtomicReference()
  ```

  使用null初始值创建新的AtomicReference。 

- #### get

  ```
  public final V get()
  ```

  获取当前值。 

- #### set

  ```
  public final void set(V newValue)
  ```

  设置为给定值。 

- #### compareAndSet

  ```
  public final boolean compareAndSet(V expect, V update)
  ```

  如果当前值 `==`为预期值，则将值设置为给定的更新值。

- #### weakCompareAndSet

  ```
  public final boolean weakCompareAndSet(V expect,V update)
  ```

  如果当前值为`==`的预期值，则将该值原子设置为给定的更新值。

- #### getAndSet

  ```
  public final V getAndSet(V newValue)
  ```

  将原子设置为给定值并返回旧值。 

- #### getAndUpdate

  ```
  public final V getAndUpdate(UnaryOperator<V> updateFunction)
  ```

  用应用给定函数的结果原子更新当前值，返回上一个值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。 

- #### updateAndGet

  ```
  public final V updateAndGet(UnaryOperator<V> updateFunction)
  ```

  使用给定函数的结果原子更新当前值，返回更新的值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。 

- #### getAndAccumulate

  ```
  public final V getAndAccumulate(V x,BinaryOperator<V> accumulatorFunction)
  ```

  使用给定函数应用给当前值和给定值的结果原子更新当前值，返回上一个值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。  该函数应用当前值作为其第一个参数，给定的更新作为第二个参数。 

- #### accumulateAndGet

  ```
  public final V accumulateAndGet(V x,BinaryOperator<V> accumulatorFunction)
  ```

  使用将给定函数应用于当前值和给定值的结果原子更新当前值，返回更新后的值。  该功能应该是无副作用的，因为尝试的更新由于线程之间的争用而失败时可能会被重新应用。  该函数应用当前值作为其第一个参数，给定的更新作为第二个参数。 

#### AtomicStampedReference  原子更新引用类型里的字段原子类

| 返回值类型 | 方法说明                                                     |
| ---------- | ------------------------------------------------------------ |
| `boolean`  | `attemptStamp(V expectedReference,  int newStamp)`  如果当前参考是预期参考的，则 `==`地将该值的值设置为给定的更新值。 |
| `boolean`  | `compareAndSet(V expectedReference,  V newReference,  int expectedStamp, int newStamp)`  以原子方式设置该引用和邮票给定的更新值的值，如果当前的参考是  `==`至预期的参考，并且当前标志等于预期标志。 |
| `V`        | `get(int[] stampHolder)`  返回引用和戳记的当前值。           |
| `V`        | `getReference()`  返回引用的当前值。                         |
| `int`      | `getStamp()`  返回邮票的当前值。                             |
| `void`     | `set(V newReference,  int newStamp)`  无条件地设置引用和戳记的值。 |
| `boolean`  | `weakCompareAndSet(V expectedReference,  V newReference,  int expectedStamp, int newStamp)`  以原子方式设置该引用和邮票给定的更新值的值，如果当前的参考是  `==`至预期的参考，并且当前标志等于预期标志。 |

- #### AtomicStampedReference

  ```
  public AtomicStampedReference(V initialRef,int initialStamp)
  ```

  用给定的初始值创建一个新的 `AtomicStampedReference` 。 

- #### getReference

  ```
  public V getReference()
  ```

  返回引用的当前值。 

- #### getStamp

  ```
  public int getStamp()
  ```

  返回邮票的当前值。 

- #### get

  ```
  public V get(int[] stampHolder)
  ```

  返回引用和戳记的当前值。 典型用法是`int[1] holder; ref =  v.get(holder);` 。 

- #### weakCompareAndSet

  ```
  public boolean weakCompareAndSet(V expectedReference,V newReference,int expectedStamp,int newStamp)
  ```

  以原子方式设置该引用和邮票给定的更新值的值，如果当前的参考是`==`至预期的参考，并且当前标志等于预期标志。

- #### compareAndSet

  ```
  public boolean compareAndSet(V expectedReference,V newReference,int expectedStamp, int newStamp)
  ```

  以原子方式设置该引用和邮票给定的更新值的值，如果当前的参考是  `==`至预期的参考，并且当前标志等于预期标志。 

- #### set

  ```
  public void set(V newReference, int newStamp)
  ```

  无条件地设置引用和戳记的值。 

- #### attemptStamp

  ```
  public boolean attemptStamp(V expectedReference, int newStamp)
  ```

  如果当前引用为期望引用的`==` ，则将该戳的值原子设置为给定的更新值。  任何给定的调用此操作可能会失败（返回`false`  ），但是当当前值保留预期值并且没有其他线程也尝试设置该值将最终成功时重复调用。 

### 为什么使用CAS而不是synchronize

CAS原理：

​	通过申明一个volatile （内存锁定，同一时刻只有一个线程可以修改内存值）类型的变量，再加上unsafe.compareAndSwapInt的方法，来保证实现线程同步的。

​	CAS有3个操作数，内存值V，旧的预期值A，要修改的新值B。这是一种乐观锁的思路，它相信在它修改之前，没有其它线程去修改它；而Synchronized是一种悲观锁，它认为在它修改之前，一定会有其它线程去修改它，悲观锁效率很低。

[CAS链接](‪D:\桌面\现用\笔记\CAS.md)