package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * author: vfwz
 * date: 2022-03-19 18:55:18
 * title: [81]搜索旋转排序数组 II
 */
@Slf4j
public class LC81_SearchInRotatedSortedArrayIi {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int target = 1;
        System.out.println(solution.search(nums, target));
        nums = new int[]{3, 5, 1};
        target = 1;
        System.out.println(solution.search(nums, target));
        nums = new int[]{2, 5, 6, 0, 0, 1, 2};
        target = 0;
        System.out.println(solution.search(nums, target));
        nums = new int[]{1, 3};
        target = 2;
        System.out.println(solution.search(nums, target));
        nums = new int[]{3, 1};
        target = 0;
        System.out.println(solution.search(nums, target));
        nums = new int[]{3, 1, 1};
        target = 0;
        System.out.println(solution.search(nums, target));

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方法：二分查找
     * 思路：有序的环形数组，从中间分开，一定有一个区间满足有序性的，分情况讨论即可
     * 数组至少有一半区是有序的
     * 由于判断有序是靠左右边界的值，因此要保证左右边界的值不想等
     */
    class Solution {
        public boolean search(int[] nums, int target) {
            return binarySearch(nums, target, 0, nums.length);
        }

        private boolean binarySearch(int[] nums, int target, int left, int right) {
            if (left >= right) return false;

            // 如果左右两边元素值和中间的都相同，就无法判断哪边是递增区间，直接缩小搜索区间就好
            while (left < right && nums[left] == nums[right - 1]) {
                int val = nums[left];
                if (val == target) return true;
                while (left < right && val == nums[left]) left++;
                while (left < right && val == nums[right - 1]) right--;
            }

            // 二分查找
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (target == nums[mid]) return true;
                // 至少有一半区是有序的
                if (nums[left] <= nums[mid]) { // 左半区有序
                    if (target < nums[mid] && target >= nums[left]) { // target 在左半区值域内
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                } else if (nums[mid] <= nums[right - 1]) {// 右半区有序
                    if (target > nums[mid] && target <= nums[right - 1]) { // target 在右半区值域内
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
            }
            return false;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

}
   