package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

import cn.vfwz.leetcode.util.*;

/**
 * author: vfwz
 * date: 2022-03-25 11:14:31
 * title: [172]阶乘后的零
 */
@Slf4j
public class LC172_FactorialTrailingZeroes {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方法： 数学
     * 分解因子，找出5和2的个数，然后每一对5和2就是一个尾随0
     * 优化：5的数量必定小于2的数量，只要求因子5的数量即可
     * 再优化：n/5 = m, b表示 m*5 = n - x (x>=0)
     * 因为 (n-x) < n 必定是阶乘中的一个数，且包含m个因子5
     * 同理继续拆解m
     */
    class Solution {
        public int trailingZeroes(int n) {
            int fiveCnt = 0;
            while(n > 0) {
                n = n/5;
                fiveCnt += n;
            }
            return fiveCnt;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
    
