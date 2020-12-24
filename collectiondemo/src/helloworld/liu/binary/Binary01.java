package helloworld.liu.binary;

/**
 * @program: JVMDome
 * @description: 二分查找01
 * @author: liuwenhao
 * @create: 2020-11-14 10:36
 *一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 **/
public class Binary01 {

    public static void main(String[] args) {
        System.out.println(missingNumber(new int[]{0,1,2,3,4,5,6,7,9}));
    }

    public static int missingNumber(int[] num){
        /*左边的位置*/
        int left = 0;
        /*右边的位置*/
        int right = num.length - 1;
        /*两个位置重叠后*/
        while (left <= right){
            /*判断 当前中间的值是否和中间下标对等*/
            /*如果对等说明 中间值之前没有缺少元素， 左边界移动*/
            /*如果不对等说明 中间值之前缺少元素，右边界移动*/
            /*中间值*/
            int middle = left + (right - left) / 2;
            /*有关这个区间的确定*/
            if (middle == num[middle]){
                left = middle + 1;
            }else {
                right = middle - 1;
            }
        }
        /*最后返回 左边界的值*/
        return left;
    }
}