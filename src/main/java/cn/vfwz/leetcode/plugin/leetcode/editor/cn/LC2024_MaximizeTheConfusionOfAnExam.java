package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

import cn.vfwz.leetcode.util.*;

/**
 * author: vfwz
 * date: 2022-03-29 17:54:34
 * title: [2024]考试的最大困扰度
 */
@Slf4j
public class LC2024_MaximizeTheConfusionOfAnExam {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        System.out.println(solution.maxConsecutiveAnswers("TTFTTFTTF", 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方法一：滑动窗口
     * 思路：设置一个2k大小的窗口，在这个窗口里面必有T或者F的数量小于k
     * 然后移动窗口右边界扩充窗口，并记录窗口里T和F的计数，
     * 只要有两个计数均超过k，表示该窗口已经不符合条件了
     * 此时记录有效窗口的最大数目，然后右移窗口右侧重复以上过程
     */
    class Solution {
        public int maxConsecutiveAnswers(String answerKey, int k) {
            int res = 0;
            char[] chars = answerKey.toCharArray();
            int n = answerKey.length();

            // 记录窗口里 T 和 F 的数量
            int tCnt = 0, fCnt = 0;
            if (n <= 2 * k) return n;
            int left = 0, right = 0;

            while (right <= n) {
                if (tCnt > k && fCnt > k) {
                    res = Math.max(res, right - left - 1);
                    if ('T' == chars[left]) tCnt--;
                    if ('F' == chars[left]) fCnt--;
                    left++;
                    continue;
                }
                if (right == n) {
                    // 最后一个窗口的判断
                    if (tCnt <= k || fCnt <= k) res = Math.max(res, right - left);
                    break;
                }
                if ('T' == chars[right]) tCnt++;
                if ('F' == chars[right]) fCnt++;
                right++;
            }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
    
