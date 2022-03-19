package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * author: vfwz
 * date: 2022-03-18 21:31:13
 * title: [69]x 的平方根
 */
@Slf4j
public class LC69_Sqrtx {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        int iv = Integer.MAX_VALUE;
        long lv = (1 << 31) - 1;
        log.warn("iv[{}], lv[{}], same: {}", iv, lv, iv == lv);
        for (int i = 1; i < 10000; i++) {
//            log.warn("isSame:[{}], mySqrt({})=[{}], Math.sqrt({})=[{}]",
//                    solution.mySqrt(i) == (int) Math.sqrt(i),
//                    i, solution.mySqrt(i), i, Math.sqrt(i));
        }
        int i = Integer.MAX_VALUE;
        log.warn("isSame:[{}], mySqrt({})=[{}], Math.sqrt({})=[{}]",
                solution.mySqrt(i) == (int) Math.sqrt(i),
                i, solution.mySqrt(i), i, Math.sqrt(i));

        i = 2147395599;
        log.warn("isSame:[{}], mySqrt({})=[{}], Math.sqrt({})=[{}]",
                solution.mySqrt(i) == (int) Math.sqrt(i),
                i, solution.mySqrt(i), i, Math.sqrt(i));

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 牛顿下山法
     * x = sqrt(c), 则 x^2 = c
     * 令 f(x) = x^2 - c
     * 由迭代公式： x(n+1) = xn - (xn^2 - c)/2xn = (xn + c/xn)/2
     */
    class Solution {
        public int mySqrt(int x) {
            double g0 = (double) x / 2;
            double g1 = (g0 + 2) / 2;
            // 迭代精度 0.00001
            while (Math.abs(g1 - g0) > 0.00001) {
                g0 = g1;
                g1 = (g0 + x / g0) / 2;
            }
            return (int)g1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    /**
     * 方法一：二分查找
     */
    class Solution1 {
        public int mySqrt(int x) {
            int l = 0, r = x;
            while (l < r) {
                int mid = l + (r - l) / 2;
                // mid*mid的结果可能int溢出，用long处理
                long multi = (long) mid * mid;
                if (multi == x) {
                    return mid;
                } else if (multi < x) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }

            return x < 2 ? l : l - 1;
        }
    }


}
    
