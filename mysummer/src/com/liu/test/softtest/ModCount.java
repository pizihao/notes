package com.liu.test.softtest;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: JVMDome
 * @description: 中位数
 * @author: liuwenhao
 * @create: 2021-02-28 19:08
 **/
public class ModCount {

    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int arrNums = scan.nextInt();
//        int[] arr = new int[arrNums];
//        System.out.println("arrNums:" + arrNums);
//        int i = 0;
//        while (i < arrNums) {
//            arr[i++] = scan.nextInt();
//        }
//        System.out.println(Arrays.toString(arr));
//
//        System.out.println(median(arr));
//        System.out.println(search(new int[]{1, 2, 3, 4, 5, 7, 8}, 10));
    }

    public static int median(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        int index = partition(nums, start, end);
        if (nums.length % 2 == 0) {
            while (index != nums.length / 2 - 1) {
                if (index > nums.length / 2 - 1) {
                    index = partition(nums, start, index - 1);
                } else {
                    index = partition(nums, index + 1, end);
                }
            }
        } else {
            while (index != nums.length / 2) {
                if (index > nums.length / 2) {
                    index = partition(nums, start, index - 1);
                } else {
                    index = partition(nums, index + 1, end);
                }
            }
        }
        return nums[index];
    }

    private static int partition(int[] nums, int start, int end) {
        int left = start;
        int right = end;
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            if (left < right) {
                nums[left] = nums[right];
                left++;
            }
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            if (left < right) {
                nums[right] = nums[left];
                right--;
            }
        }
        nums[left] = pivot;
        return left;
    }

    static int search(int[] nums, int target) {
        int index = -1;
        int begin = 0;
        int end = nums.length - 1;
        while (index == -1) {
            int mid = (begin + end) / 2;
            if (nums[mid] == target) {
                index = mid;
            } else if (nums[mid] < target) {
                if (mid == nums.length - 1 || nums[mid + 1] > target)
                {
                    index = mid + 1;
                } else {
                    begin = mid + 1;
                }
            } else if (nums[mid] > target) {
                if (mid == 0 || target > nums[mid - 1])
                {
                    index = mid;
                } else {
                    end = mid - 1;
                }
            }
        }
        return index;
    }

}