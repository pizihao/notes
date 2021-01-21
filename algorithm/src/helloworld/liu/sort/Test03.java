package helloworld.liu.sort;

/**
 * @program: JVMDome
 * @description: 插入排序
 * @author: liuwenhao
 * @create: 2021-01-21 12:47
 **/
public class Test03 {
    public int[] sort(int[] arr){
        int len = arr.length;
        int preIndex, current;
        for(int i = 1; i < len; i++) {
            preIndex = i - 1;
            current = arr[i];
            while(preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
        return arr;
    }
}