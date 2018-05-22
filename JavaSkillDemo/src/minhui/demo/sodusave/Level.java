package minhui.demo.sodusave;

/**
 * @author minhui.zhu
 *         Created by minhui.zhu on 2018/4/20.
 *         Copyright © 2017年 Oceanwing. All rights reserved.
 */

public enum Level {
    EASY(32,50) , COMMON(26,32), HARD(22,26);
    int minSum;
    int maxSum;

    Level(int minSum, int maxSum) {
        this.minSum = minSum;
        this.maxSum = maxSum;
    }

    public int getMinSum() {
        return minSum;
    }

    public int getMaxSum() {
        return maxSum;
    }
}
