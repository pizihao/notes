package helloworld.company.liu.best;

/**
 * @program: JVMDome
 * @description: 价格计算
 * @author: liuwenhao
 * @create: 2021-01-09 16:31
 **/
public class PriceUtil {
    /**
     * 价格
     */
    private Double price;

    public Double getPrice(TypeState state, Integer weight) {
        //自己实现价格计算，应该和路程和座位类型相关
        return weight + Math.pow(Double.parseDouble(state.toString()),2);
    }
}