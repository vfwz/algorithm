package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * author: vfwz
 * date: 2022-04-01 14:12:04
 * title: [70]爬楼梯
 */
@Slf4j
public class LC70_ClimbingStairs {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方法：动态规划，相关状态较少，优化DP数组成两个变量
     * 状态转移方程：
     * dp[1] = 1, dp[2] = 2
     * dp[i] = dp[i-1] + dp[i-2]
     */
    class Solution {
        public int climbStairs(int n) {
            int p = 0, q = 0, r = 1;
            for (int i = 0; i < n; i++) {
                p = q;
                q = r;
                r = p + q;
            }
            return r;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 方法：动态规划
     * 状态转移方程：
     * dp[1] = 1, dp[2] = 2
     * dp[i] = dp[i-1] + dp[i-2]
     */
    class Solution1 {
        public int climbStairs(int n) {
            int[] dp = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                if (i == 1) {
                    dp[i] = 1;
                } else if (i == 2) {
                    dp[i] = 2;
                } else {
                    dp[i] = dp[i - 2] + dp[i - 1];
                }
            }
            return dp[n];
        }
    }

}
    
