package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author: vfwz
 * date: 2022-03-14 11:24:08
 * title: [15]三数之和
 */
@Slf4j
public class LC15_ThreeSum {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        int[] nums = {0, 0, 0, 0, 0, 0, 0};
        System.out.println(solution.threeSum(nums));
//
        nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(solution.threeSum(nums));
//
        nums = new int[]{6, -5, -6, -1, -2, 8, -1, 4, -10, -8, -10, -2, -4, -1, -8, -2, 8, 9, -5, -2, -8, -9, -3, -5};
        // 期望结果:[[-10,4,6],[-8,-1,9],[-6,-3,9],[-6,-2,8],[-5,-4,9],[-5,-3,8],[-5,-1,6],[-4,-2,6],[-3,-1,4],[-2,-2,4]]
        System.out.println(solution.threeSum(nums));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<List<Integer>> threeSum(int[] nums) {

            List<List<Integer>> res = new ArrayList<>();
            if (nums.length < 3) {
                return res;
            }

            Arrays.sort(nums);

            // 令结果为 ABC
            // 第一个for循环确定A
            for (int left = 0; left < nums.length - 2; left++) {
                int first = nums[left];
                // 数组是正序排列的，A > 0的情况不可能出现
                if (first > 0) {
                    continue;
                }
                // A重复出现
                if (left > 0 && first == nums[left - 1]) {
                    continue;
                }

                // 第二个for循环确定B, 并查找C
                int end = nums.length - 1;
                for (int mid = left + 1; mid < nums.length - 1; mid++) {
                    int second = nums[mid];
                    // 对于相同的AB, B出现一次后就直接跳过
                    if (mid > left + 1 && second == nums[mid - 1]) {
                        continue;
                    }

                    // 使用二分查找在剩下的元素里查找第三个数字
                    int third = -first - second;
                    // 利用升序数组的特性，在二分查找时缩小下次的区间
                    // A不变， Bnext > B => Cnext < C => Endnext < End
                    end = binarySearchThird(nums, mid + 1, end, third);
                    if (third == nums[end]) {
                        res.add(Arrays.asList(first, second, third));
                    }
                }
            }

            return res;

        }

        // 在B右侧使用 二分查找 第一个大于等于第C的数下标
        private int binarySearchThird(int[] nums, int start, int end, int target) {
            int left = start, right = end;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] >= target) {
                    right = mid;
                }
            }
            return left;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


}
    
