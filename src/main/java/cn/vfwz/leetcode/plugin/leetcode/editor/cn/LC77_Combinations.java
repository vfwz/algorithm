package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

import cn.vfwz.leetcode.util.*;

/**
 * author: vfwz
 * date: 2022-03-22 20:30:22
 * title: [77]组合
 */
@Slf4j
public class LC77_Combinations {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> combine = new ArrayList<>();
            backtrack(res, combine, 1, n, k);
            return res;
        }

        public void backtrack(List<List<Integer>> res, List<Integer> combine, int cur, int n, int k) {
            // 当前组合已经达到足够数量的元素
            if(combine.size() == k) {
                res.add(new ArrayList<>(combine));
                return;
            }

            // 剪枝，超出给定范围或者剩下的数字不够用了
            // k - combine.size() 是当前组合还差几个数字满员
            // n - cur + 1 是还有几个数字没用
            if(cur > n || k - combine.size() > n - cur + 1) {
                return;
            }

            // 回溯，一种是带上当前数字
            combine.add(cur);
            backtrack(res, combine, cur + 1, n, k);
            combine.remove(combine.size() - 1);

            // 一种是不带上当前数字
            backtrack(res, combine, cur + 1, n, k);
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


}
    
