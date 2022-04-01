package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

/**
 * author: vfwz
 * date: 2022-04-01 10:22:59
 * title: [954]二倍数对数组
 */
@Slf4j
public class LC954_ArrayOfDoubledPairs {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        System.out.println(solution.canReorderDoubled(new int[]{-5, -2})); // false
        System.out.println(solution.canReorderDoubled(new int[]{-5, -3})); // false
        System.out.println(solution.canReorderDoubled(new int[]{3, 1, 3, 6})); // false
        System.out.println(solution.canReorderDoubled(new int[]{-3, -1, -3, -6})); // false
        System.out.println(solution.canReorderDoubled(new int[]{4, -2, 2, -4})); // true
        System.out.println(solution.canReorderDoubled(new int[]{-1, 3, 1, 3, 6, 2, -2, 6, 12, 6})); // true
        System.out.println(solution.canReorderDoubled(new int[]{6, -3, 8, -4, 3, 0, 4, -6, 3, 0, 4, -6, 6, -3, 0, 0, -2, 4})); // false
//        int[] arr = {-2, -2, 0, 0, 2, 2};
//        System.out.println(solution.binarySearchWithBound(arr, 0, arr.length, -3, true));
//        System.out.println(solution.binarySearchWithBound(arr, 0, arr.length, -3, false));
//        System.out.println(solution.binarySearchWithBound(arr, 0, arr.length, -2, true));
//        System.out.println(solution.binarySearchWithBound(arr, 0, arr.length, -2, false));
//        System.out.println(solution.binarySearchWithBound(arr, 0, arr.length, 0, true));
//        System.out.println(solution.binarySearchWithBound(arr, 0, arr.length, 0, false));
//        System.out.println(solution.binarySearchWithBound(arr, 0, arr.length, 1, true));
//        System.out.println(solution.binarySearchWithBound(arr, 0, arr.length, 1, false));
//        System.out.println(solution.binarySearchWithBound(arr, 0, arr.length, 2, true));
//        System.out.println(solution.binarySearchWithBound(arr, 0, arr.length, 2, false));
//        System.out.println(solution.binarySearchWithBound(arr, 0, arr.length, 3, true));
//        System.out.println(solution.binarySearchWithBound(arr, 0, arr.length, 3, false));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方法：优化方法一，加上二分查找优化
     * 时间复杂度 O(nlogn)
     */
    class Solution {
        public boolean canReorderDoubled(int[] arr) {
            HashSet<Integer> used = new HashSet<>();
            Arrays.sort(arr);
            int leftZeroIndex = Math.abs(binarySearchWithBound(arr, 0, arr.length, 0, true));
            int rightZeroIndex = arr[0] > 0 ? -1 : Math.abs(binarySearchWithBound(arr, 0, arr.length, 0, false));

            // 处理小于0的一侧
            int idx = leftZeroIndex;
            for (int i = leftZeroIndex - 1; i >= 0; i--) {
                if (used.contains(i)) {
                    continue;
                }
                used.add(i);
                idx = binarySearchWithBound(arr, 0, idx, 2 * arr[i], false);
                if (idx < 0 || arr[idx] != 2 * arr[i]) { // 兼容rightBound==0但是未找到的情况
                    return false;
                }
                used.add(idx);
            }

            // 处理大于于0的一侧
            idx = rightZeroIndex;
            for (int i = rightZeroIndex + 1; i < arr.length; i++) {
                if (used.contains(i)) {
                    continue;
                }
                used.add(i);
                idx = binarySearchWithBound(arr, idx + 1, arr.length, 2 * arr[i], true);
                if (idx < 0) {
                    return false;
                }
                used.add(idx);
            }

            return true;
        }

        int binarySearchWithBound(int[] arr, int fromIndex, int endIndex, int key, boolean leftBound) {
            int left = fromIndex, right = endIndex;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (arr[mid] == key) {
                    if (leftBound) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                } else if (arr[mid] > key) {
                    right = mid;
                } else if (arr[mid] < key) {
                    left = mid + 1;
                }
            }
            if (leftBound) {
                if (left == endIndex || arr[left] != key) {
                    return -left;
                }
                return left;
            } else {
                if (right == 0 || arr[right - 1] != key) {
                    return -Math.abs((right - 1));
                }
                return right - 1;
            }
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


    /**
     * 方法：排序，HashSet
     * 思路：将原数组按照绝对值排序，从前向后找当前元素的两倍值，len/2元素能找到返回true
     * 时间复杂度 o(n2)
     * 20220401 每日一题
     */
    class Solution1 {
        public boolean canReorderDoubled(int[] arr) {
            HashSet<Integer> used = new HashSet<>();
            arr = Arrays.stream(arr).boxed().sorted((i1, i2) -> {
                if (Math.abs(i1) == Math.abs(i2)) {
                    return i1 - i2;
                }
                return Math.abs(i1) - Math.abs(i2);
            }).mapToInt(Integer::intValue).toArray();
            int idx = 0;
            for (int i = 0, j = 0; j < arr.length / 2; i++) {
                if (used.contains(i)) {
                    continue;
                }
                used.add(i);
                idx = findNum(arr, Math.max(i, idx) + 1, 2 * arr[i]);
                if (idx < 0) {
                    return false;
                }
                used.add(idx);
                j++;
            }
            return true;
        }

        int findNum(int[] arr, int fromIndex, int key) {
            if (fromIndex == arr.length) return -1;
            for (int k = fromIndex; k < arr.length; k++) {
                if (arr[k] == key) {
                    return k;
                }
            }
            return -1;
        }
    }

}
    
