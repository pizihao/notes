package com.liu.test.person;

/**
 * @author shidacaizi
 * @date 2020/5/14 15:32
 */
public class NewTest {
    public static void main(String[] args) {
        double[][] Q = new double[][]{
                {-1, 0, 0, -1},
                {-1, -1, -1, 0},
                {-1, -1, -1, 0},
                {-1, -1, -1, -1}
        };

        int[][] graph = new int[][]{
                {0, 3, 5, 0},
                {0, 0, 0, 1},
                {0, 0, 0, 2},
                {0, 0, 0, 0}
        };

        double epsilon = 0.8;
        double alpha = 0.2;
        double gamma = 0.8;
        int MAX_EPISODES = 100;
        for (int episode = 0; episode < MAX_EPISODES; ++episode) {
            int index = 0;
            while (index != 3) {
                int next;
                if (Math.random() < epsilon) next = max(Q[index]);
                else next = randomNext(Q[index]);
                int reward = 5 - graph[index][next];
                Q[index][next] = (1 - alpha) * Q[index][next] + alpha * (reward + gamma * maxNextQ(Q[next]));
                index = next;
            }
        }
        for (int i = 0; i < Q.length; i++) {
            System.out.print("[");
            for (int j = 0; j < Q.length; j++) {
                System.out.print(Q[i][j]+",");
            }
            System.out.print("]");
            System.out.println();
        }
    }

    private static int randomNext(double[] is) {
        int next = 0, n = 1;
        for (int i = 0; i < is.length; ++i) {
            if (is[i] >= 0 && Math.random() < 1.0 / n++) next = i;
        }
        return next;
    }

    private static int max(double[] is) {
        int max = 0;
        for (int i = 1; i < is.length; ++i) {
            if (is[i] > is[max]) max = i;
        }
        return max;
    }

    private static double maxNextQ(double[] is) {
        double max = is[0];
        for (int i = 1; i < is.length; ++i) {
            if (is[i] > max) max = is[i];
        }
        return max;
    }

}
