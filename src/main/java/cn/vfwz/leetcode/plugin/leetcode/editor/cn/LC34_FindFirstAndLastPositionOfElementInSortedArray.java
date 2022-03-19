package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * author: vfwz
 * date: 2022-03-19 12:36:39
 * title: [34]在排序数组中查找元素的第一个和最后一个位置
 */
@Slf4j
public class LC34_FindFirstAndLastPositionOfElementInSortedArray {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方法二： o(logn) 二分查找法
     */
    class Solution {
        public int[] searchRange(int[] nums, int target) {

            return new int[]{binarySearchWithBound(nums, target, true),
                    binarySearchWithBound(nums, target, false)};

        }

        /**
         * 查找边界的二分查找
         *
         * @param nums   升序数组
         * @param target 目标函数
         * @param toLeft 是否找左边界
         * @return 索引
         */
        private int binarySearchWithBound(int[] nums, int target, boolean toLeft) {

            int left = 0, right = nums.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    if (toLeft) { // 寻找左边界
                        right = mid;
                    } else { // 寻找右边界
                        left = mid + 1;
                    }
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid;
                }
            }

            /**
             * 几种情况：
             * 1. nums里所有元素都大于target
             *      left和right最后会停在 0 处
             * 2. nums里所有元素都小于target
             *      left和right最后会停在 target.length 处
             * 3. target在0处
             *      left 和 right 停在 0 处
             * 4. target在nums.length-1处
             *      left 和 right 停在 target.length 处
             * 5， target在 (0, nums.length-1) 之间
             *      a. 查找左边界时，left和right停在左边界上
             *      b. 查找右边界时，left和right停在右边界+1上
             */
            if (toLeft) {
                return left != nums.length && nums[left] == target
                        ? left
                        : -1;
            } else {
                return right != 0 && nums[right - 1] == target
                        ? right - 1
                        : -1;
            }

        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 方法一： o(n)从前向后法
     */
    class Solution1 {
        public int[] searchRange(int[] nums, int target) {
            int first = -1, last = -1;
            boolean flag = false; // first已找到
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    first = flag ? first : i;
                    last = i;
                    flag = true;
                }
            }
            return new int[]{first, last};
        }
    }
}
    
