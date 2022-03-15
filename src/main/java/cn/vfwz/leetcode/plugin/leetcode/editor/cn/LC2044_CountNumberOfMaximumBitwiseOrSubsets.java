package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * author: vfwz
 * date: 2022-03-15 23:07:10
 * title: [2044]统计按位或能得到最大值的子集数目
 */
@Slf4j
public class LC2044_CountNumberOfMaximumBitwiseOrSubsets {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方法一：DFS
     */
    class Solution {
        public int countMaxOrSubsets(int[] nums) {

            // 按位或的最大值
            int maxOr = 0;
            for (int num : nums) {
                maxOr |= num;
            }
            return dfs(nums, 0, 0, maxOr);

        }

        int dfs(int[] nums, int index, int curVal, int max) {
            // 剪枝，对于当前已经达到maxOr的情况，剩下的元素任意排列都可以满足条件
            if (curVal == max) {
                return 1 << (nums.length - index);
            } else if (index == nums.length) {
                return 0;
            }
            return dfs(nums, index + 1, curVal, max)  // 向后遍历，不带当前索引位置的值
                    + dfs(nums, index + 1, curVal | nums[index], max); // 向后遍历，带上当前索引位置的值
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
   