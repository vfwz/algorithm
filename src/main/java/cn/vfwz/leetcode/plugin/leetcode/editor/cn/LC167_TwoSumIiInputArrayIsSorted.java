package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * author: vfwz
 * date: 2022-03-16 22:14:32
 * title: [167]两数之和 II - 输入有序数组
 */
@Slf4j
public class LC167_TwoSumIiInputArrayIsSorted {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方法二：双指针 + 二分查找优化
     * 思路：left指针从左向右遍历，right指针从右向左遍历，
     * 计算所指数的和，大于target，右指针左移，小于target，左指针右移
     * 使用二分查找扩大指针每次缩放的步数
     */
    class Solution {
        public int[] twoSum(int[] numbers, int target) {
            int left = 0;
            int right = findRight(numbers, target - numbers[0]);
            while (left < right) {
                int sum = numbers[left] + numbers[right];
                    if (sum < target) { // 小于目标值，左指针右移
                    left = findLeft(numbers, target - numbers[right]);
                } else if (sum > target) { // 大于目标值，右指针左移
                    right = findRight(numbers, target - numbers[left]);
                } else {
                    return new int[]{left + 1, right + 1};
                }
            }
            return null;
        }

        // 增加个二分查找进行优化每次调整的left或right的位置

        // 使用左边界的二分查找，寻找下一次的左指针
        int findLeft(int[] numbers, int target) {
            int left = 0;
            int right = numbers.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (numbers[mid] >= target) {
                    right = mid;
                } else if (numbers[mid] < target) {
                    left = mid + 1;
                }
            }

            return left;
        }

        // 使用右边界的二分查找，寻找下一次的右指针
        int findRight(int[] numbers, int target) {
            int left = 0;
            int right = numbers.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (numbers[mid] > target) {
                    right = mid;
                } else if (numbers[mid] <= target) {
                    left = mid + 1;
                }
            }

            return right - 1;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 方法一：双指针
     * 思路：left指针从左向右遍历，right指针从右向左遍历，
     * 计算所指数的和，大于target，右指针左移，小于target，左指针右移
     */
    class Solution1 {
        public int[] twoSum(int[] numbers, int target) {
            int left = 0;
            int right = numbers.length - 1;
            while (left < right) {
                int sum = numbers[left] + numbers[right];
                if (sum < target) { // 小于目标值，左指针右移
                    left++;
                } else if (sum > target) { // 大于目标值，右指针左移
                    right--;
                } else {
                    return new int[]{left + 1, right + 1};
                }
            }
            return null;
        }
    }

}
   