package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * author: vfwz
 * date: 2022-03-09 14:49:25
 * title: [7]整数反转
 */
@Slf4j
public class LC7_ReverseInteger {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        log.warn("reverse[123]:[{}]", solution.reverse(123));
        log.warn("reverse[-123]:[{}]", solution.reverse(-123));
        log.warn("reverse[120]:[{}]", solution.reverse(120));
        log.warn("reverse[0]:[{}]", solution.reverse(0));
        log.warn("reverse[{}}]:[{}]", Integer.MAX_VALUE, solution.reverse(Integer.MAX_VALUE));
        log.warn("reverse[{}}]:[{}]", Integer.MIN_VALUE, solution.reverse(Integer.MIN_VALUE));
        log.warn("reverse[{}}]:[{}]", Integer.MAX_VALUE>>2, solution.reverse(Integer.MAX_VALUE>>2));
        log.warn("reverse[{}}]:[{}]", Integer.MIN_VALUE>>2, solution.reverse(Integer.MIN_VALUE>>2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 解法思路：
     * 将原数通过对10的倍数取余得到每个位上的数字
     * 然后反向从高位乘以10的倍数得到翻转后的数
     * 注意事项：处理相加后的和的溢出，处理高位乘10的倍数后直接溢出
     */
    class Solution {
        public int reverse(int x) {
            //log.warn("------- method start -------");
            int[] nums = new int[10];
            int res = 0;
            int tmp = x;
            int i = 0;
            while (tmp != 0) {
                int e = tmp % 10;
                nums[i++] = e;
                tmp /= 10;
            }
            log.warn("nums[]:{}", Arrays.toString(nums));

            boolean start = false;
            int times = 1;
            for (int j = 9; j >= 0; j--) {
                if (!start) {
                    if(nums[j] != 0) {
                        start = true;
                    } else {
                        continue;
                    }
                }
                // 打个补丁，如果翻转后最高位超限了，直接返回0
                if(times == 1000000000 && (nums[j] > 2 || nums[j] < -2)) {
                    return 0;
                }
                int next = nums[j] * times;
                //log.warn("res:{}, next:{}", res, next);
                if(exceedLimit(res, next)) {
                    //log.warn("------- method exceed end -------");
                    return 0;
                }
                res += next;
                times *= 10;
            }
            //log.warn("------- method end -------");
            return res;
        }

        // 判断当前值加上下一位后，和是否会超限
        private boolean exceedLimit(int res, int next) {
            if(res > 0) {
                if(Integer.MAX_VALUE - res > next) {
                    return false;
                }
            } else if(res < 0) {
                if(Integer.MIN_VALUE - res <= next) {
                    return false;
                }
            } else {
                return false;
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
    
