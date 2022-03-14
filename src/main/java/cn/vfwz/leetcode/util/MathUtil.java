package cn.vfwz.leetcode.util;

public class MathUtil {

    public int max(int... ints) {
        int max = Integer.MIN_VALUE;
        for (int anInt : ints) {
            max = Math.max(anInt, max);
        }
        return max;
    }

}
