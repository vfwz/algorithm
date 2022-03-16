package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;

import java.util.*;

import cn.vfwz.leetcode.util.*;

/**
 * author: vfwz
 * date: 2022-03-16 10:05:52
 * title: [432]全 O(1) 的数据结构
 * 20220316 每日一题
 */
@Slf4j
public class LC432_AllOoneDataStructure {

    @Test
    public void testSolution() {
        AllOne allOne = new AllOne();
        allOne.inc("hello");
        printDoubleLinkedList(allOne.head, allOne.tail);
        allOne.inc("hello");
        System.out.println("allOne.getMaxKey() = " + allOne.getMaxKey());
        printDoubleLinkedList(allOne.head, allOne.tail);
        System.out.println("allOne.getMinKey() = " + allOne.getMinKey());
        allOne.inc("leet");
        printDoubleLinkedList(allOne.head, allOne.tail);
        System.out.println("allOne.getMaxKey() = " + allOne.getMaxKey());
        System.out.println("allOne.getMinKey() = " + allOne.getMinKey());
    }

    void printDoubleLinkedList(DoubleLinkedList head, DoubleLinkedList tail) {
        while (head != null) {
            System.out.print(head.val + "[" + head.count + "]");
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println();
        Deque<String> stack = new LinkedList<>();
        while (tail != null) {
            stack.push(tail.val + "[" + tail.count + "]");
            if (tail.prev != null) {
                stack.push(" <- ");
            }
            tail = tail.prev;
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
        System.out.println();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方法一：使用 Hash表 和 双链表
     * 思路：
     * Hash表保存 String -> Node 的关系便于快速查找节点
     * 双链表可以做到快速的插入和删除节点
     */
    class AllOne {

        // 双向链表保存值和计数，并保持按计数升序排列
        final DoubleLinkedList head, tail;
        // Hash表保存链表节点快速访问
        final Map<String, DoubleLinkedList> nodeMap;

        public AllOne() {
            head = new DoubleLinkedList("", Integer.MIN_VALUE);
            tail = new DoubleLinkedList("", Integer.MAX_VALUE);
            head.next = tail;
            tail.prev = head;
            nodeMap = new HashMap<>();
        }

        public void inc(String key) {
            DoubleLinkedList node = nodeMap.get(key);
            // 节点不存在则新建一个并插入在头节点
            if (node == null) {
                node = new DoubleLinkedList(key, 1);
                swap(node, head);
                nodeMap.put(key, node);
            }
            // 否则更新计数并向后移动节点保证链表顺序
            else {
                node.count += 1;
                while (node.count > node.next.count) {
                    swap(node, node.next);
                }
            }
        }

        public void dec(String key) {
            DoubleLinkedList node = nodeMap.get(key);
            if (node != null) {
                node.count -= 1;
                // 计数为0，删除该节点
                if (node.count == 0) {
                    DoubleLinkedList np = node.prev;
                    np.next = node.next;
                    node.next.prev = np;
                    nodeMap.remove(node.val);
                }
                // 否则更新计数并向前移动节点保证链表顺序
                else {
                    while (node.count < node.prev.count) {
                        swap(node.prev, node);
                    }
                }
            }
        }

        public String getMaxKey() {
            return tail.prev.val;
        }

        public String getMinKey() {
            return head.next.val;
        }

        // 交换双向节点的位置
        private void swap(DoubleLinkedList a, DoubleLinkedList b) {
            a.next = b.next;
            b.next.prev = a;
            b.next = a;
            b.prev = a.prev;
            if (a.prev != null) a.prev.next = b;
            a.prev = b;
        }
    }

    class DoubleLinkedList {
        DoubleLinkedList prev, next;
        String val;
        int count;

        DoubleLinkedList(int count) {
            this.count = count;
        }

        DoubleLinkedList(String val, int count) {
            this.val = val;
            this.count = count;
        }
    }

    /**
     * Your AllOne object will be instantiated and called as such:
     * AllOne obj = new AllOne();
     * obj.inc(key);
     * obj.dec(key);
     * String param_3 = obj.getMaxKey();
     * String param_4 = obj.getMinKey();
     */
    //leetcode submit region end(Prohibit modification and deletion)


}
    
