package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * author: vfwz
 * date: 2022-03-15 11:16:42
 * title: [455]分发饼干
 */
@Slf4j
public class LC455_AssignCookies {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        System.out.println(3|5);
        System.out.println(3|1|5);
        System.out.println(3|2|5);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路： 贪心算法，迭代给当前最小胃口的小孩分配最小合适尺寸的饼干
     */
    class Solution {
        public int findContentChildren(int[] g, int[] s) {
            Arrays.sort(g);
            Arrays.sort(s);
            int res = 0;
            for (int i = 0, j = 0; i < g.length && j < s.length; i++) {
                // 这里用s.length-1做为结束条件，循环结束后j不会越界
                while (j < s.length - 1 && g[i] > s[j]) {
                    j++;
                }
                if (g[i] <= s[j]) {
                    j++;
                    res++;
                }
            }
            return res;

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
    
