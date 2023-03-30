package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * author: vfwz
 * date: 2023-03-30 10:36:04
 * title: [1637]两点之间不包含任何点的最宽垂直区域
 */
@Slf4j
public class LC1637_WidestVerticalAreaBetweenTwoPointsContainingNoPoints {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * java8 stream方式处理， 一行搞定， 用时有点长
     * 执行用时： 55 ms , 在所有 Java 提交中击败了 12.23% 的用户
     */
    class Solution {
        public int maxWidthOfVerticalArea(int[][] points) {
            return Arrays.stream(points)
                    .sorted(Comparator.comparingInt(a -> a[0]))
                    .reduce(new int[]{0, Integer.MAX_VALUE},
                            (result, item) -> {
                                result[0] = Integer.max(result[0], item[0] - result[1]);
                                result[1] = item[0];
                                return result;
                            })[0];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
    
