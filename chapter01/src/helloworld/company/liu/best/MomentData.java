package helloworld.company.liu.best;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: JVMDome
 * @description: 列车时刻表
 * @author: liuwenhao
 * @create: 2021-01-09 16:28
 **/
public class MomentData {

    /**
     * 列车时刻表
     */
    private List<Train> trainList = new ArrayList<>();

    public List<Train> getTrainList() {
        return trainList;
    }

    public void setTrainList(List<Train> trainList) {
        this.trainList = trainList;
    }
}