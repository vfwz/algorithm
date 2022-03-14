package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * author: vfwz
 * date: 2022-03-14 16:56:25
 * title: [1]两数之和
 */
@Slf4j
public class LC1_TwoSum {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.twoSum(new int[]{3, 2, 4}, 6)));
        System.out.println(Arrays.toString(solution.twoSum(new int[]{3, 3}, 6)));

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方法一： 双指针缩小区间
     * 修正：返回的是下标，原数组重排序后得不到
     */
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            // 使用map保存遍历过的元素和下标
            Map<Integer, Integer> traversed = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int diff = target - nums[i];
                if(traversed.containsKey(diff)) {
                    return new int[]{traversed.get(diff), i};
                }
                traversed.put(nums[i], i);
            }
            return null;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
    
