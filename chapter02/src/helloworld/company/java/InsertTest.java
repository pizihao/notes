package helloworld.company.java;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: JVMDome
 * @description: 测试
 * @author: liuwenhao
 * @create: 2021-03-04 14:35
 **/
public class InsertTest {

    public static void main(String[] args) {
        List<List<String>> lists = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("John");
        list.add("john00@mail.com");
        list.add("john_newyork@mail.com");
        list.add("johnsmith@mail.com");
        lists.add(list);
        List<String> list1 = new ArrayList<>();
        list1.add("John");
        list1.add("johnnybravo@mail.com");
        lists.add(list1);
        List<String> list2 = new ArrayList<>();
        list2.add("Mary");
        list2.add("mary@mail.com");
        lists.add(list2);
        List<String> list3 = new ArrayList<>();
        list3.add("John");
        list3.add("johnsmith@mail.com");
        lists.add(list3);
        System.out.println(mergeAccount(lists));
    }

    public static List<List<String>> mergeAccount(List<List<String>> accounts) {
        // 在此处编写实现代码逻辑
        List<String> list = new ArrayList<>();
        Set<Map<String, String>> mapSet = new HashSet<>();
        List<Map<String, String>> collect = accounts.stream().map(strings -> {
            Map<String, String> maps = new HashMap<>(16);
            String str = strings.remove(0);
            strings.forEach(s -> {
                maps.put(s, str);
                list.add(s);
            });
            return maps;
        }).distinct().collect(Collectors.toList());
        list.forEach(s -> mapSet.addAll(merge(collect,s)));

        return mapSet.stream().map(setMap -> {
            List<String> list1 = new ArrayList<>();
            for (Map.Entry<String, String> entry : setMap.entrySet()) {
                if (list1.isEmpty()) {
                    list1.add(entry.getValue());
                }
                list1.add(entry.getKey());
            }
            return list1;
        }).collect(Collectors.toList());
    }

    public static List<Map<String, String>> merge(List<Map<String, String>> m1, String mergeKey) {
        Set<String> set = new HashSet<>();
        return m1.stream().filter(map -> map.get(mergeKey) != null)
                .collect(Collectors.groupingBy(o -> {
                    set.addAll(o.keySet());
                    return o.get(mergeKey);
                })).values().stream().map(maps -> maps.stream().flatMap(m -> m.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b)))
                .collect(Collectors.toList());
    }
}