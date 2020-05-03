# hashCode方法

**hashcode()**:返回对象的hash码值。从object中继承过来，默认使用对象的地址计算散列码及hash地址。

java中当重写了equals方法时，就必须重新hashCode()方法，如果没有重写，比如说是基于对象的内容实现的，而保留hashCode的实现不变，那么很可能某两个对象明明是“相等”，而hashCode却不一样。这是很危险的。

对于hashcode和equals一个是逻辑地址，一个是实际地址。equlas相等必须是对象值相同，且地址也是相同的。如果两个对象equals,java环境会认为他们的hashcode一定相等，所以如果重写equals方法那么一定要重写hashcode方法。但是两个对象不equals，但是hashcode有可能相等。hashcode是否相等，跟equals是否相等无关。

在Object方法中的hashCode方法是这样的：

~~~java
public native int hashCode();
~~~

用native修饰，说明调用的是底层的C/C++

像Collection,Map中的hashCode都带有这样的注解：

~~~java
@see Object#hashCode()
~~~

说明这些都是调用的Object中的hashCode()方法，所以这些都是调用的底层的C/C++

再来看看String的hashCode方法

~~~java
public int hashCode() {
    int h = hash;
    if (h == 0 && value.length > 0) {
        char val[] = value;

        for (int i = 0; i < value.length; i++) {
            h = 31 * h + val[i];
        }
        hash = h;
    }
    return h;
}
~~~

Integer：

~~~java
@Override
public int hashCode() {
    return Integer.hashCode(value);
}

public static int hashCode(int value) {
    return value;
}
~~~

Double：

~~~java
@Override
public int hashCode() {
    return Double.hashCode(value);
}

public static int hashCode(double value) {
    long bits = doubleToLongBits(value);
    return (int)(bits ^ (bits >>> 32));
}
~~~

Long：

~~~java
@Override
public int hashCode() {
    return Long.hashCode(value);
}

public static int hashCode(long value) {
    return (int)(value ^ (value >>> 32));
}
~~~

Float：

~~~java
@Override
public int hashCode() {
    return Float.hashCode(value);
}

public static int hashCode(float value) {
    return floatToIntBits(value);
}
~~~

Short：

~~~java
@Override
public int hashCode() {
    return Short.hashCode(value);
}

public static int hashCode(short value) {
    return (int)value;
}
~~~

Boolean：

~~~java
@Override
public int hashCode() {
    return Boolean.hashCode(value);
}

public static int hashCode(boolean value) {
    return value ? 1231 : 1237;
}

~~~

Byte：

~~~java
@Override
public int hashCode() {
    return Byte.hashCode(value);
}

public static int hashCode(byte value) {
    return (int)value;
}
~~~

Integer,Short,Byte都是把value转化为int类型，再return

true 的hash码是1231
false 的hash码是1237