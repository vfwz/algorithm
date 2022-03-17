package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

import cn.vfwz.leetcode.util.*;

/**
 * author: vfwz
 * date: 2022-03-16 17:46:47
 * title: [435]无重叠区间
 */
@Slf4j
public class LC435_NonOverlappingIntervals {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        int[][] intervals = {{3,4},{2,3},{1,3},{1,2},};
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        StringBuilder sb = new StringBuilder();
        Arrays.stream(intervals).forEach(a -> sb.append(Arrays.toString(a)).append(", "));
        System.out.println(sb);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方法一：贪心算法
     * 思路：贪心策略，让保留下来的区间右侧尽可能的小，从而给右侧让出更多空间
     * 按区间右侧升序排列，然后遍历数组移除重叠的区间
     */
    class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {

            Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

            int[] cur = intervals[0];
            int res = 0;
            for (int i = 1; i < intervals.length; i++) {
                if(cur[1] > intervals[i][0]) {
                    res++;
                } else {
                    cur = intervals[i];
                }
            }

            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
    
