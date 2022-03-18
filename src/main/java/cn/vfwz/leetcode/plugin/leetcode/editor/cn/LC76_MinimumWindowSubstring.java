package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * author: vfwz
 * date: 2022-03-18 13:44:09
 * title: [76]最小覆盖子串
 */
@Slf4j
public class LC76_MinimumWindowSubstring {

    @Test
    public void testSolution() {
        Solution2 solution = new Solution2();
        System.out.println(solution.minWindow("ADOBECODEBANCefABCaabcfeaavea", "ABC"));
        System.out.println(solution.minWindow("aa", "a"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * 官方题解评论区 [sp1998] 的解法
     * 精妙处：
     * 常规思路是右指针一直右移，直到窗口中包含t，然后左指针一直右移，直到窗口中不包含t，
     * 此过程中要一直验证窗口中是否包含t，时间复杂度高
     * 思想：滑动窗口（优化版） 面对窗口中是否包含某一字符串这一问题，可以用数组统计每个字符出现的次数的方式。
     * 在该题中，右指针是一直右移直到窗口包含t，此时左指针不一定移动，
     * 只有当左指针指向的字符在窗口出现的次数太多时，即抛弃该字符窗口内仍包含t，此时才移动左指针。
     * <p>
     * hash数组保存当前窗口中字符的数量，目标串中字符需要的数量用负数表示
     * hash数组中隐含了字符是否可以被删掉（hash[ch] > 0)表示可缩减掉
     * （目标串中的字符，在迭代过程中，数量会递增至 >= 0，
     * 非目标串中的字符，数量在迭代过程中会递增，窗口缩减时，也不可能递减至0以下）
     * <p>
     * PS: 加一点点优化，如果 res.length == t.length 表示无冗余直接返回就好
     */
    class Solution {
        public String minWindow(String s, String t) {
            char[] chars = s.toCharArray(), chart = t.toCharArray();
            int n = chars.length, m = chart.length;

            // ASCII字符出现的次数，下标就是ASCII的索引
            int[] hash = new int[128];

            // 更新目标串中的字符在hash数组中的次数
            for (char ch : chart) hash[ch]--;

            // Result
            String res = "";

            // i: 窗口右侧, j: 窗口左侧, cnt: 目标串中的字符被满足的数量
            for (int i = 0, j = 0, cnt = 0; i < n; i++) {
                // 累计当前窗口中的字符次数
                hash[chars[i]]++;

                // 在这个位置，hash[ch] <= 0 必定是目标串中的字符
                if (hash[chars[i]] <= 0) cnt++;

                // cnt == m，表示目标串中的字符被全部满足，也就是hash中值全部 >= 0
                // 此时窗口符合答案条件，缩减左窗口其长度最小
                // hash[ch] > 0 表示这个字符剔除后当前窗口依然符合答案条件
                // 终止时， cnt == m && hash[ch] == 0, 也就是第一次遇到不能删除的数，此时窗口是满足答案的最小情况
                // 窗口接着向右侧扩张后，左侧必然会出现可以删除的数
                while (cnt == m && hash[chars[j]] > 0) hash[chars[j++]]--;

                if (cnt == m)
                    if (res.equals("") || res.length() > i - j + 1) {
                        res = s.substring(j, i + 1);
                        // res和目标串长度相同，无冗余直接返回
                        if (res.length() == t.length()) break;
                    }
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    /**
     * 滑动窗口，参考 LeetCode 101 中的解法
     * <p>
     * 思路：
     * 设置一个窗口，首先移动窗口右侧使得当前子串满足目标
     * 然后移动窗口左侧缩小当前子串，让当前子串最小满足目标，并比较记录最小边界
     * 之后重复这个动作，输出最小边界窗口里的字符串即可
     * <p>
     * 难点：
     * 如何判断当前子串是否满足目标
     * 解决方式：
     * 设置一个hash表保存目标串中的每种字符需要的数量
     * (由于是ASCII字符，设置一个128长度数组也可，再维护一个布林数组存放目标字符存在标识)，
     * 窗口滑动的过程中动态加减表中字符对应的数值
     * 再设置一个数量标识，每满足一次字符（该字符对应的数值<=0），标识加一，
     * 当标识等于目标串的长度时，当前窗口就满足目标
     */
    class Solution2 {

        public String minWindow(String s, String t) {

            int[] lack = new int[128]; // 达成目标串每个字符缺少的数量
            boolean[] flag = new boolean[128]; // 字符是否存在于目标串中

            // 根据 t 初始化 lack 和 flag
            for (char c : t.toCharArray()) {
                lack[c]++;
                flag[c] = true;
            }

            char[] src = s.toCharArray();
            int l = 0; // 窗口左指针
            int cnt = 0; // 已达成目标的字符数
            int minl = 0, minr = src.length; // 满足目标的最小窗口的左右边界
            for (int r = 0; r < src.length; r++) {
                char rch = src[r];
                // 不是目标串中的字符跳过
                if (!flag[rch]) {
                    continue;
                }
                if (lack[rch]-- > 0) {
                    cnt++;
                }
                // t中的所有字符都已满足，准备移动左侧，缩小范围找到当前最短子串
                while (cnt == t.length()) {
                    // 向右移动左窗口使得某一个字符数量不符合目标子串
                    char lch = src[l];
                    if (flag[lch] && ++lack[lch] > 0) {
                        cnt--;
                        // 当前子串长度小于之前的最小值
                        if (r - l < minr - minl) {
                            minl = l;
                            minr = r;
                        }
                    }
                    l++;
                }
                // 如果窗口和目标串长度相同，说明没有冗余，直接返回即可
                if (minr + 1 - minl == t.length()) {
                    break;
                }
            }
            return minr == s.length() ? "" : s.substring(minl, minr + 1);
        }

    }

}
    
