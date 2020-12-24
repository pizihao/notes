package com.liu.effective.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @program: JVMDome
 * @description: StampedLock
 * @author: liuwenhao
 * @create: 2020-12-23 17:39
 **/
public class TestLock09 {
    public static void main(String[] args) {
        StampedLock stampedLock = new StampedLock();
        long readStamp = stampedLock.tryReadLock();
        try{
            TimeUnit.SECONDS.sleep(3);
            System.out.println("盖章读锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            stampedLock.unlockRead(readStamp);
        }

    }
}