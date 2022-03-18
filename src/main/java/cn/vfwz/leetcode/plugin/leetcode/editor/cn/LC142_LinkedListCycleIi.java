package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

import cn.vfwz.leetcode.util.*;

/**
 * author: vfwz
 * date: 2022-03-17 20:35:30
 * title: [142]环形链表 II
 */
@Slf4j
public class LC142_LinkedListCycleIi {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
    /**
     * 方法一：双指针
     * 思路：快慢指针
     * 证明：
     * 令 head到环入口距离为 a, 环的的长度为 b
     * 快指针走过的距离为 f, 慢指针走过的距离为 s，
     * 则有 f = 2s
     * 当第一次两者相遇时有 f = s + nb
     * 结合两式得 s = nb
     * 因此在第一次相遇后，慢指针继续走a步，会来到 a + nb处，也就是环的入口处
     * 此时只要设置一个指针从 head 处和慢指针一起走这a步，两者相遇时就是环的入口
     */
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            ListNode slow = head, fast = head;
            do {
                // fast 每次向前两步
                if (fast == null) return null;
                fast = fast.next;
                if (fast == null) return null;
                fast = fast.next;

                // slow 一次向前一步
                slow = slow.next;
            } while (slow != fast);

            // 存在环，将fast指向头结点
            fast = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
    
