package helloworld.liu.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shidacaizi
 * @date 2020/3/18 15:33
 */
public class HashMapDemo01 {
    public static void main(String[] args) {
        Object a = "1500";
        System.out.println(hash(a));
        System.out.println(a.hashCode());

        HashMap<Object, Object> hashMap =  new HashMap<>();
        hashMap.put("hash","map");

    }
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}