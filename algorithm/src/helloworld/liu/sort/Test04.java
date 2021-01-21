package helloworld.liu.sort;

import java.util.Arrays;

/**
 * @program: JVMDome
 * @description: 归并排序
 * @author: liuwenhao
 * @create: 2021-01-21 14:39
 **/
public class Test04 {
    public void mergeSort(int[] a, int[] tmp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            // 左排序
            mergeSort(a, tmp, left, mid);
            // 右排序
            mergeSort(a, tmp, mid + 1, right);
            // 左右合并
            merge(a, tmp, left, mid + 1, right);
        }
    }
    public void merge(int[] a, int[] tmp, int left, int rightPos, int right) {
        int leftEnd = rightPos - 1;
        int tmpPos = left;
        int num = right - left + 1;
        while (left <= leftEnd && rightPos <= right) {
            if (a[left] < a[rightPos]) {
                tmp[tmpPos++] = a[left++];
            } else {
                tmp[tmpPos++] = a[rightPos++];
            }
        }
        while (left <= leftEnd) {
            tmp[tmpPos++] = a[left++];
        }
        while (rightPos <= right) {
            tmp[tmpPos++] = a[rightPos++];
        }
        for (int i = 0; i < num; i++, right--) {
            a[right] = tmp[right];
        }
    }
}