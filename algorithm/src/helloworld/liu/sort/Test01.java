package helloworld.liu.sort;

/**
 * @program: JVMDome
 * @description: 冒泡排序
 * @author: liuwenhao
 * @create: 2021-01-21 11:25
 **/
public class Test01 {


    public int[] sort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                // 相邻元素两两对比
                if (arr[j] > arr[j + 1]) {
                    // 元素交换
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}