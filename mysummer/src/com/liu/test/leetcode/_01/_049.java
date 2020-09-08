package com.liu.test.leetcode._01;

/**
 * @author shidacaizi
 * @date 2020/7/22 11:57
 */
//https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
public class _049 {
//    直接查找
//    选取第一个,枚举数组中的元素 如果出现了比第一个小的元素 那么返回该元素
//    如果没有出现，那么说明没有经过旋转 返回 该元素

//    使用二分查找
//    设定左边界 left 右边届 right
//    mid = left + (right - left)/2 这个是中间值
//    把numbers[mid]和numbers[right]作对比
//    如果numbers[mid]>numbers[right] 说明这个最小值应该在这个中间值的右侧 left = mid + 1
//    如果numbers[mid]<numbers[right] 说明这个最小值应该在这个中间值的左侧 right = mid
//    如果numbers[mid]=numbers[right] 说明中间值是一个重复值 而且和最右边元素相同，
//      所以抛弃一个重复值 right = right — 1
    public int minArray(int[] numbers) {
        if(numbers.length == 0){
            return 0;
        }

        int left = 0;
        int right = numbers.length -1;

        while (left < right){
            int mid = left + (right - left)/2;
            if (numbers[mid] > numbers[right]){
                left = mid + 1;
            }else if (numbers[mid]<numbers[right]){
                right = mid;
            }else {
                right = right - 1;
            }
        }
        return numbers[left];
    }
}
