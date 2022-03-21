package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import cn.vfwz.leetcode.util.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * author: vfwz
 * date: 2022-03-21 09:48:34
 * title: [653]两数之和 IV - 输入 BST
 */
@Slf4j
public class LC653_TwoSumIvInputIsABst {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        TreeNode root = new TreeNode("5,3,6,2,4,null,7");
        root.printSelf();
        int target = 13;
        System.out.println(solution.findTarget(root, target));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方法二: 官方解, hashset + DFS
     */
    class Solution {

        Set<Integer> traversed = new HashSet<>();
        public boolean findTarget(TreeNode root, int k) {
            if (root == null) {
                return false;
            }
            if (traversed.contains(k - root.val)) {
                return true;
            }
            traversed.add(root.val);

            return findTarget(root.left, k) || findTarget(root.right, k);
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 方法一：遍历+搜索树
     * 时间复杂度 O(nlogn), 太高了...
     */
    class Solution1 {
        public boolean findTarget(TreeNode root, int k) {
            // 找到最大值和最小值，快速判断节点是否超出树的范围
            int min = minValue(root);
            int max = maxValue(root);

            // 先序遍历的非递归实现
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (node == null) continue;

                // 查找当前值能否和树中的其他元素组成目标
                int nodeValue = node.val;
                int part = k - nodeValue;

                // 另一部分等于自身或者超出二叉搜索树的范围
                if (part != node.val
                        && part >= min && part <= max
                        && findPart(root, part)) {
                    return true;
                }
                stack.push(node.right);
                stack.push(node.left);
            }

            return false;
        }


        boolean findPart(TreeNode root, int part) {
            if (root == null) {
                return false;
            }
            if (part == root.val) {
                return true;
            } else if (part < root.val) {
                return findPart(root.left, part);
            } else {
                return findPart(root.right, part);
            }
        }

        int minValue(TreeNode root) {
            int min = Integer.MIN_VALUE;
            while (root.left != null) {
                min = root.left.val;
                root = root.left;
            }
            return min;
        }

        int maxValue(TreeNode root) {
            int max = Integer.MAX_VALUE;
            while (root.right != null) {
                max = root.right.val;
                root = root.right;
            }
            return max;
        }
    }
}
