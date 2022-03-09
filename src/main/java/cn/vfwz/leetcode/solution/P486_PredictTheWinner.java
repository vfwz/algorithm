package cn.vfwz.leetcode.solution;

import org.junit.Test;

/**
 * <a href="https://leetcode-cn.com/problems/predict-the-winner/">
 * 题目链接
 * </a>
 */
public class P486_PredictTheWinner {


    @Test
    public void test() {
        int[] nums = {1, 1};
//        int[] nums = {1,5,2};
//        int[] nums = {1, 5, 233, 7};
//        int[] nums = {9337301, 0, 2, 2245036, 4, 1997658, 5, 2192224, 960000, 1261120, 8824737, 1, 1161367, 9479977, 7, 2356738, 5, 4, 9};
        System.out.println(PredictTheWinner(nums));
    }

    public boolean PredictTheWinner(int[] nums) {
        int[][][] dp = new int[nums.length][nums.length][2];
        int[] bc = bestChoice(dp, nums, 0, nums.length - 1);
        int[] bcOp = nextBestChoice(dp, nums, 0, nums.length - 1);
        return bc[1] >= bcOp[1];
    }

    public int[] bestChoice(int[][][] dp, int[] nums, int start, int end) {
        // 使用DP数组优化
        if (dp[start][end][0] != 0) {
            return dp[start][end];
        }

        // bc[0] = -1, 选左边, bc[0] = 1, 选右边
        // bc[1] 最优得分
        int[] bc = new int[2];

        // 选左边能得到的最大得分
        int leftMax = nums[start] + nextBestChoice(dp, nums, start + 1, end)[1];
        // 选右边能得到的最大得分
        int rightMax = nums[end] + nextBestChoice(dp, nums, start, end - 1)[1];
        if (leftMax >= rightMax) {
            bc[0] = -1;
            bc[1] = leftMax;
        } else {
            bc[0] = 1;
            bc[1] = rightMax;
        }

        System.out.printf("start:%d, end:%d, nums[start]:%d, nums[end]:%d, bc[0]:%d, bc[1]:%d \n",
                start, end, nums[start], nums[end], bc[0], bc[1]);
        dp[start][end] = bc;
        return bc;
    }

    // 返回对手先手后，当前选手最优选择
    private int[] nextBestChoice(int[][][] dp, int[] nums, int start, int end) {
        // 没有数了，或者只有一个数，只能得0分
        if (start >= end) {
            return new int[]{0, 0};
        }
        // 对手的最优选择
        int[] nextOp = bestChoice(dp, nums, start, end);
        // 对手选左边
        if (nextOp[0] == -1) {
            start = start + 1;
        }
        // 对手选右边
        else if (nextOp[0] == 1) {
            end = end - 1;
        }
        return bestChoice(dp, nums, start, end);
    }

}
