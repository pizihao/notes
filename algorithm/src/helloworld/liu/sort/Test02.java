package helloworld.liu.sort;

/**
 * @program: JVMDome
 * @description: 选择排序
 * @author: liuwenhao
 * @create: 2021-01-21 12:20
 **/
public class Test02 {
    public int[] sort(int[] arr){
        int len = arr.length;
        int minIndex, temp;
        for(int i = 0; i < len - 1; i++) {
            minIndex = i;
            for(int j = i + 1; j < len; j++) {
                // 寻找最小的数
                if(arr[j] < arr[minIndex]) {
                    // 将最小数的索引保存
                    minIndex = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        return arr;
    }
}