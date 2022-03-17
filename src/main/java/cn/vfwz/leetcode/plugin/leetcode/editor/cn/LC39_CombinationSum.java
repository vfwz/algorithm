package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author: vfwz
 * date: 2022-03-09 10:29:22
 */
@Slf4j
public class LC39_CombinationSum {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        solution.combinationSum(null, 1);
        log.warn("log something {}", "hello");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /** 方法一: 回溯算法DFS
     *  思路：构造DFS穷举回溯方法
     */
    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);

            List<List<Integer>> res = new ArrayList<>();
            List<Integer> selected = new ArrayList<>();

            dfs(candidates, 0, selected, 0, target, res);

            return res;
        }

        void dfs(int[] candidates, int start, List<Integer> selected, int sum, int target, List<List<Integer>> res) {
            if (start == candidates.length) return;

            // 达到目标值
            if (sum == target) {
                res.add(new ArrayList<>(selected));
            }
            // 未达到目标值
            else if (sum < target) {

                // 选择当前索引值
                selected.add(candidates[start]);
                sum += candidates[start];
                dfs(candidates, start, selected, sum, target, res);
                sum -= candidates[start];
                selected.remove(selected.size() - 1);

                // 跳过当前索引值
                dfs(candidates, start + 1, selected, sum, target, res);
            }

        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
    
