package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * author: vfwz
 * date: 2022-03-22 13:34:01
 * title: [2038]如果相邻两个颜色均相同则删除当前颜色
 */
@Slf4j
public class LC2038_RemoveColoredPiecesIfBothNeighborsAreTheSameColor {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        System.out.println(solution.winnerOfGame("AAAAABBBB"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 官方题解评论区的写法
     * 思路：使用数组+三元运算符减少代码
     */
    class Solution {
        public boolean winnerOfGame(String colors) {
            int[] freq = {0, 0};
            for (int i = 1; i < colors.length() - 1; i++) {
                // 连续三个相同的颜色
                if (colors.charAt(i - 1) == colors.charAt(i) && colors.charAt(i) == colors.charAt(i + 1)) {
                    freq[colors.charAt(i) == 'A' ? 0 : 1]++;
                }
            }
            return freq[0] > freq[1];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 思路：A和B的选择都不会影响到对方的操作
     * 遍历颜色，找到A能最多移除多少颜色和B能移除多少颜色
     * 由于A先手，需要A可移除的颜色更多才能获胜
     */
    class Solution1 {
        public boolean winnerOfGame(String colors) {
            char[] colorChars = colors.toCharArray();
            int removableA = 0, removableB = 0;

            for (int i = 1; i < colors.length() - 1; i++) {
                if ('A' == colorChars[i]
                        && 'A' == colorChars[i - 1]
                        && 'A' == colorChars[i + 1]) {
                    removableA++;
                } else if ('B' == colorChars[i]
                        && 'B' == colorChars[i - 1]
                        && 'B' == colorChars[i + 1]) {
                    removableB++;
                }
            }

            return removableA > removableB;
        }
    }

}
    
