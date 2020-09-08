package com.liu.test.softtest.stream;

/**
 * @author shidacaizi
 * @date 2020/8/15 9:25
 */
public class _01 {
    public static void main(String[] args) {
//        一
//        List<String> list = new ArrayList<>();
//        Stream<String> stream = list.stream(); //获取一个顺序流
//        Stream<String> parallelStream = list.parallelStream(); //获取一个并行流
//        Stream<Double> stream3 = Stream.generate(Math::random).limit(2);
//        stream3.forEach(System.out::println);
//        Stream<Integer> stream1 = Stream.of(6, 4, 6, 7, 3, 9, 8, 10, 12, 14, 14);
//        二
//        filter：过滤流中的某些元素
//        limit(n)：获取n个元素
//        skip(n)：跳过n元素，配合limit(n)可实现分页
//        distinct：通过流中元素的 hashCode() 和 equals() 去除重复元素
//        Stream<Integer> newStream = stream1.filter(s -> s > 5) //6 6 7 9 8 10 12 14 14
//                .distinct() //6 7 9 8 10 12 14
//                .skip(2) //9 8 10 12 14
//                .limit(2); //9 8
//        newStream.forEach(System.out::println);
//        三
//        map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
//        flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
////        List<String> list1 = Arrays.asList("a,b,c", "1,2,3");
////        //将每个元素转成一个新的且不带逗号的元素
////        Stream<String> s1 = list1.stream().map(s -> s.replaceAll(",", ""));
////        s1.forEach(System.out::println); // abc  123
////
////        Stream<String> s3 = list1.stream().flatMap(s -> {
////            //将每个元素转换成一个stream
////            String[] split = s.split(",");
//            return Arrays.stream(split);
//        });
//        s3.forEach(System.out::println); // a b c 1 2 3
//        四
//        sorted()：自然排序，流中元素需实现Comparable接口
//        sorted(Comparator com)：定制排序，自定义Comparator排序器
//        List<String> list = Arrays.asList("aa", "ff", "dd");
////        String 类自身已实现Compareable接口
//        list.stream().sorted().forEach(System.out::println);// aa dd ff
//        Student s1 = new Student("aa", 10);
//        Student s2 = new Student("bb", 20);
//        Student s3 = new Student("aa", 30);
//        Student s4 = new Student("dd", 40);
//        List<Student> studentList = Arrays.asList(s1, s2, s3, s4);
////        自定义排序：先按姓名升序，姓名相同则按年龄升序
//        studentList.stream().sorted(
//                (o1, o2) -> {
//                    if (o1.getName().equals(o2.getName())) {
//                        return o1.getAge() - o2.getAge();
//                    } else {
//                        return o1.getName().compareTo(o2.getName());
//                    }
//                }
//        ).forEach(System.out::println);
//        五
//        peek：如同于map，能得到流中的每一个元素。
//        但map接收的是一个Function表达式，有返回值；而peek接收的是Consumer表达式，没有返回值。
//        Student s1 = new Student("aa", 10);
//        Student s2 = new Student("bb", 20);
//        List<Student> studentList = Arrays.asList(s1, s2);
//        studentList.stream()
//                .peek(o -> o.setAge(100))
//                .forEach(System.out::println);
//          结果：
//        Student{name='aa', age=100}
//        Student{name='bb', age=100}
//        六
//        allMatch：接收一个 Predicate 函数，当流中每个元素都符合该断言时才返回true，否则返回false
//        noneMatch：接收一个 Predicate 函数，当流中每个元素都不符合该断言时才返回true，否则返回false
//        anyMatch：接收一个 Predicate 函数，只要流中有一个元素满足该断言则返回true，否则返回false
//        findFirst：返回流中第一个元素
//        findAny：返回流中的任意元素
//        count：返回流中元素的总个数
//        max：返回流中元素最大值
//        min：返回流中元素最小值
//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
//        boolean allMatch = list.stream().allMatch(e -> e > 10); //false
//        boolean noneMatch = list.stream().noneMatch(e -> e > 10); //true
//        boolean anyMatch = list.stream().anyMatch(e -> e > 4);  //true
//        Integer findFirst = list.stream().findFirst().get(); //1
//        Integer findAny = list.stream().findAny().get(); //1
//        long count = list.stream().count(); //5
//        Integer max = list.stream().max(Integer::compareTo).get(); //5
//        Integer min = list.stream().min(Integer::compareTo).get(); //1
    }
}
