package com.liu.test.leetcode._nowcoder._01;

import java.util.HashSet;

/**
 * @author shidacaizi
 * @date 2020/7/28 16:03
 */
public class _02 {
    /**
     * 判断给定的正整数最少能表示成多少个素数的和
     * @param N int整型 给定的正整数
     * @return int整型
     */
    HashSet<Integer> primes = new HashSet<>();
    HashSet<Integer> noPrimes = new HashSet<>();
    public int MinPrimeSum (int num) {
        // write code here
        int size=1;
        if(num<=1){
            return 0;
        }
        if(!isPrime(num)){
            if(num%2==0){
                size = 2;
            }else{
                //如果一个数-2是一个质数,而其自身不是质数,那么获取这个数就是一个质数+2
                if(isPrime(num-2)){
                    size=2;
                }else{
                    //如果一个数自身不是一个质数,-2也不是一个质数,那么他就可以根据歌德巴克猜想减去3,成为一个偶数,偶数可以由两个质数获得,再加上3这个质数就是三个
                    size=3;
                }
            }
        }
        return size;
    }
    public boolean isPrime(int num){
        if(primes.contains(num))return true;
        if(noPrimes.contains(num))return false;
        if(num%2==0){
            noPrimes.add(num);
            return false;
        }
        int p = (int)Math.sqrt(num);
        for(int i = 3;i <= p ;i++){
            if(num%i==0){
                noPrimes.add(num);
                return false;
            }
        }
        primes.add(num);
        return true;
    }
}
