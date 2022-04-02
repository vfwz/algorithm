package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * author: vfwz
 * date: 2022-04-02 11:42:39
 * title: [420]强密码检验器
 */
@Slf4j
public class LC420_StrongPasswordChecker {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
//        System.out.println(solution.strongPasswordChecker("a"));
//        System.out.println(solution.strongPasswordChecker("aaa"));
//        System.out.println(solution.strongPasswordChecker("aA1"));
//        System.out.println(solution.strongPasswordChecker("aaaAAA"));
//        System.out.println(solution.strongPasswordChecker("......"));
        System.out.println(solution.strongPasswordChecker(".............................."));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 20220402 每日一题
     * 这是一道麻烦而又没啥意义的题。
     * 考虑到此题并未涉及主流数据结构和算法，属于偏门题目，收藏+cv并不过分。
     * 作者：AC_OIer
     * 链接：https://leetcode-cn.com/problems/strong-password-checker/solution/by-ac_oier-unp5/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    class Solution {
        public int strongPasswordChecker(String password) {
            char[] cs = password.toCharArray();
            int n = cs.length;
            int A = 0, B = 0, C = 0;
            for (char c : cs) {
                if (c >= 'a' && c <= 'z') A = 1;
                else if (c >= '0' && c <= '9') B = 1;
                else if (c >= 'A' && c <= 'Z') C = 1;
            }
            int m = A + B + C;
            if (n < 6) {
                return Math.max(6 - n, 3 - m);
            } else if (n <= 20) {
                int tot = 0;
                for (int i = 0; i < n; ) {
                    int j = i;
                    while (j < n && cs[j] == cs[i]) j++;
                    int cnt = j - i;
                    if (cnt >= 3) tot += cnt / 3;
                    i = j;
                }
                return Math.max(tot, 3 - m);
            } else {
                int tot = 0;
                int[] cnts = new int[3];
                for (int i = 0; i < n; ) {
                    int j = i;
                    while (j < n && cs[j] == cs[i]) j++;
                    int cnt = j - i;
                    if (cnt >= 3) {
                        tot += cnt / 3;
                        cnts[cnt % 3]++;
                    }
                    i = j;
                }
                int base = n - 20, cur = base;
                for (int i = 0; i < 3; i++) {
                    if (i == 2) cnts[i] = tot;
                    if (cnts[i] != 0 && cur != 0) {
                        int t = Math.min(cnts[i] * (i + 1), cur);
                        cur -= t;
                        tot -= t / (i + 1);
                    }
                }
                return base + Math.max(tot, 3 - m);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
    
