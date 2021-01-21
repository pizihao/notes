package helloworld.company.liu.best;

import java.util.Map;

/**
 * @program: JVMDome
 * @description: 城市
 * @author: liuwenhao
 * @create: 2021-01-09 16:16
 **/
public class City {
    /**
     * 城市名
     */
    private String name;

    /**
     * 包含与相邻城市的权重信息,包括到达的时间 按照1:1对应权重 (分钟)
     */
    private Map<City,Integer> cityMap;

    public Map<City, Integer> getWeight() {
        return cityMap;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setWeight(Map<City, Integer> cityMap) {
        this.cityMap = cityMap;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}