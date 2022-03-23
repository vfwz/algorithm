package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

import cn.vfwz.leetcode.util.*;

/**
 * author: vfwz
 * date: 2022-03-23 00:32:29
 * title: [440]字典序的第K小数字
 */
@Slf4j
public class LC440_KThSmallestInLexicographicalOrder {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        System.out.println(solution.findKthNumber(7747794, 5857460));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * 方法：前缀树（字典树）Trie
     *
     * 思路：
     * 设置一个方法，getCount(prefix, n) 查找所有前缀是prefix,且小于n的数的个数
     * 遍历字典树，
     * 当前前缀够找到目标，则寻找其孩子节点（prefix*10)，只跳过当前prefix这一个数
     * 当前前缀不够找到目标，则寻找其下一个兄弟节点（prefix+1)，跳过了所有前缀为prefix的数
     * 最终找到第k个数，并输出prefix即为目标
     *
     * 参考题解： https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/solution/ben-ti-shi-shang-zui-wan-zheng-ju-ti-de-shou-mo-sh/
     */
    class Solution {
        public int findKthNumber(int n, int k) {
            int prev = 1; // 指示已经越过的数
            int prefix = 1; // 前缀
            while(prev != k) {
                int count = getCount(prefix, n);
                // 第k位的数在前缀 prefix 的数之中，向字典树的子节点去找
                if(count + prev > k) {
                    prefix *= 10;
                    prev++; // 只跳过一个数， 比如2前缀太广了，下一次去寻找20前缀的，只跳过了数2
                }
                // 第k位的数在前缀是 prefix 的数之后，向字典树的后兄弟节点去找
                else if(count + prev <= k) {
                    prefix++;
                    prev += count; // 跳过所有当前前缀的数，比如2前缀的数不够，下次应该去寻找3前缀的，跳过了所有2前缀的数，也就是前面的count

                }
            }
            return prefix;
        }

        /**
         * 方法逻辑：如需要查找 [1, 256] 以 2 为前缀的数的数量
         * 初始 cur = 2, next = 3, count_1 = 3 - 2 = 1 个数
         * 第二轮 cur = 20, next = 30, count_2 = 30 - 20 = 10 个数
         * 第三轮 cur = 200, next = 300, count_3 = Math.min(300, 256+1) - 200 = 57 个数
         *
         * 共计 1 + 10 + 57 = 68个数
         *
         * @param prefix 数字前缀
         * @param n      最大值
         * @return 在 [1, n] 范围内， 以prefix为前缀的数的数量
         */
        int getCount(int prefix, int n) {
            int cur = prefix;
            int next = prefix + 1;
            int count = 0;
            while (cur <= n) {
                count += Math.min(n + 1, next) - cur;
                cur *= 10;
                next *= 10;
            }
            return count;
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 方法：大顶堆
     * 思路：维护一个大顶堆，排序规则为字典序倒序
     * 对于[1,n]中的数遍历入堆，
     * 堆中只保留k个数，
     * 小于k个直接入堆，
     * 大于k个和堆顶元素比较，较小的入堆
     * <p>
     * ！！！ 提交运行超时，测试用例 7747794 5857460
     * 时间复杂度和空间复杂度都不能接受
     */
    class Solution1 {
        public int findKthNumber(int n, int k) {
            Queue<String> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

            for (int i = 1; i <= n; i++) {
                String s = String.valueOf(i);
                // 少于k个数直接进堆
                if (maxHeap.size() < k) {
                    maxHeap.offer(s);
                }
                // 否则需要小于最大的数才进堆
                else if (s.compareTo(maxHeap.peek()) < 0) {
                    maxHeap.poll();
                    maxHeap.offer(s);
                }
            }

            return Integer.parseInt(maxHeap.peek());
        }
    }


}
    
