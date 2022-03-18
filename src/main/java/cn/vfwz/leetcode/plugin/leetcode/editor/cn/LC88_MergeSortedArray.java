package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * author: vfwz
 * date: 2022-03-17 14:51:16
 * title: [88]合并两个有序数组
 */
@Slf4j
public class LC88_MergeSortedArray {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        solution.merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方法一：双指针
     * 思路：防止在归并的过程中nums1里的元素频繁的整体后移，
     * 可以将nums1中的元素先移动到数组右侧位置
     * 思考：其实nums1不用整体右移动，从后向前倒序合并就好了...
     */
    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            // 先将nums1里的有效元素移动到数组右侧
            for (int i = m - 1, j = nums1.length - 1; i >= 0; i--, j--) {
                nums1[j] = nums1[i];
            }

            for (int i = n, j = 0, k = 0; k < m + n; k++) {
                // j == n 表示nums2里的元素已经全部放置完毕了
                if (j == n) break;
                // i < m+n 防止nums1里的元素放置完毕导致越界异常
                if (i < m + n && nums2[j] > nums1[i]) {
                    nums1[k] = nums1[i++];
                } else {
                    nums1[k] = nums2[j++];
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
    
