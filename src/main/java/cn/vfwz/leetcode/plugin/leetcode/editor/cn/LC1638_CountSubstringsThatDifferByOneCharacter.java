package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * author: vfwz
 * date: 2023-03-27 11:21:13
 * title: [1638]统计只差一个字符的子串数目
 */
@Slf4j
public class LC1638_CountSubstringsThatDifferByOneCharacter {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        log.info("{}", solution.countSubstrings("ab", "bb"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * 题目规模较小，暴力解法，执行用时 7ms
     */
    class Solution {
        public int countSubstrings(String s, String t) {
            int result = 0;
            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j < t.length(); j++) {
                    int k = i, l = j, dif = 0;
                    while (k < s.length() && l < t.length()) {
                        if (s.charAt(k++) != t.charAt(l++)) {
                            dif++;
                        }
                        // 只差一个字符的子串满足条件，继续看后面的字符
                        if (dif == 1) {
                            result++;
                        }
                        // 差一个字符以上的子串，跳出比较
                        else if (dif > 1) {
                            break;
                        }
                    }
                }
            }
            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
    
