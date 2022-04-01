package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * author: vfwz
 * date: 2022-04-01 14:30:07
 * title: [413]等差数列划分
 */
@Slf4j
public class LC413_ArithmeticSlices {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方法：动态规划，优化dp数组为变量，优化去除一个变量
     * dpTable定义
     * dp[i][0] 不以nums[i]结尾的等差数列数量
     * dp[i][1] 以nums[i]结尾的等差数列数量
     * 状态转移方程：
     * dp[i][0] = dp[i-1][0] + dp[i-1][1]
     * dp[i][1] = 0; // 后三个元素不是等差数列
     * dp[i][1] = dp[i-1][1] + 1; // 后三个元素是等差数列
     */
    class Solution {
        public int numberOfArithmeticSlices(int[] nums) {
            int q = 0, r = 0;
            for (int i = 2; i < nums.length; i++) {
                q = nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2] ? q + 1 : 0;
                r = r + q;
            }
            return r;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    /**
     * 方法：动态规划
     * <p>
     * dpTable定义
     * dp[i][0] 不以nums[i]结尾的等差数列数量
     * dp[i][1] 以nums[i]结尾的等差数列数量
     * <p>
     * 状态转移方程：
     * dp[i][0] = dp[i-1][0] + dp[i-1][1]
     * dp[i][1] = 0; // 后三个元素不是等差数列
     * dp[i][1] = dp[i-1][1] + 1; // 后三个元素是等差数列
     */
    class Solution1 {
        public int numberOfArithmeticSlices(int[] nums) {
            int n = nums.length;
            int[][] dp = new int[n][2];
            if (n < 3) return 0;
            for (int i = 2; i < n; i++) {
                dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
                if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                    dp[i][1] = dp[i - 1][1] + 1;
                }
            }
            return dp[n - 1][0] + dp[n - 1][1];
        }
    }


}
    
