package helloworld.company.liu.best;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @program: JVMDome
 * @description: 列车班次
 * @author: liuwenhao
 * @create: 2021-01-09 16:48
 **/
public class Train {

    /**
     * 出发点
     */
    private City head;

    /**
     * 终点
     */
    private City tail;

    /**
     * 途径站和时间
     */
    private Map<City,LocalDateTime> cityLocalDateTimeHashMap = new HashMap<>();

    /**
     * 软铺数
     */
    private int softSpread;

    /**
     * 硬铺数
     */
    private int hardShop;

    /**
     * 硬座数
     */
    private int hardSeat;


    /**
     * @description: 两个数组不能有重复
     * @param city 城市数组
     * @param dateTimes 时间数组
     * @param softSpread 软铺数
     * @param hardShop 硬铺数
     * @param hardSeat 硬座数
     * @author liuwenaho
     * @date 2021/1/9 17:25
     */
    public Train(City[] city, LocalDateTime[] dateTimes, int softSpread,int hardShop, int hardSeat){
        this.softSpread = softSpread;
        this.hardSeat = hardSeat;
        this.hardShop = hardShop;
        setHead(city[0]);
        setTail(city[city.length-1]);
        for (int i = 0; i < city.length; i++) {
            cityLocalDateTimeHashMap.put(city[i],dateTimes[i]);
        }
    }

    public void setHead(City head) {
        this.head = head;
    }

    public void setTail(City tail) {
        this.tail = tail;
    }

    public City getHead() {
        return head;
    }

    public City getTail() {
        return tail;
    }

    public void setCityLocalDateTimeHashMap(Map<City, LocalDateTime> cityLocalDateTimeHashMap) {
        this.cityLocalDateTimeHashMap = cityLocalDateTimeHashMap;
    }

    public Map<City, LocalDateTime> getCityLocalDateTimeHashMap() {
        return cityLocalDateTimeHashMap;
    }

    public int getSoftSpread() {
        return softSpread;
    }

    public void setSoftSpread(int softSpread) {
        this.softSpread = softSpread;
    }

    public int getHardShop() {
        return hardShop;
    }

    public void setHardShop(int hardShop) {
        this.hardShop = hardShop;
    }

    public int getHardSeat() {
        return hardSeat;
    }

    public void setHardSeat(int hardSeat) {
        this.hardSeat = hardSeat;
    }
}