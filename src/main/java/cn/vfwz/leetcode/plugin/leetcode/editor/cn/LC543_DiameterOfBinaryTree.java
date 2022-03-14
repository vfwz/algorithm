package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

import cn.vfwz.leetcode.util.*;

/**
 * author: vfwz
 * date: 2022-03-11 15:25:57
 * title: [543]二叉树的直径
 */
@Slf4j
public class LC543_DiameterOfBinaryTree {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        // Integer[] nodes = {1, 2, 3, 4, 5, 6};
        Integer[] nodes = {4, -7, -3, null, null, -9, -3, 9, -7, -4, null, 6, null, -6, -6, null, null, 0, 6, 5, null, 9, null, null, -1, -4, null, null, null, -2};
        TreeNode treeNode = TreeNodeUtil.generateTreeFromArray(nodes);
        System.out.println(treeNode);
        System.out.println(solution.diameterOfBinaryTree(treeNode));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        /**
         * 方法一改进：
         * 使用全局变量记录最大直径,
         * 在计算MaxDepth的过程中，在后序位置计算子树深度和，更新最大直径
         */
        int maxDiameter = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            maxDepth(root);
            return maxDiameter;
        }

        // 求节点的最大深度
        private int maxDepth(TreeNode node) {
            if (node == null) {
                return 0;
            }
            int leftDepth = maxDepth(node.left);
            int rightDepth = maxDepth(node.right);
            maxDiameter = Math.max(maxDiameter, leftDepth+rightDepth);
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution1 {
        /**
         * 思路：最长直径是左边的最大深度+右边的最大深度
         * 思路修正: 子树的直径可能比上述的深度和大，需要考虑到
         */
        public int diameterOfBinaryTree(TreeNode root) {
            if (root == null) return 0;
            int depthSum = maxDepth(root.right) + maxDepth(root.left);
            int maxChildDiameter = Math.max(diameterOfBinaryTree(root.right), diameterOfBinaryTree(root.left));
            return Math.max(depthSum, maxChildDiameter);
        }

        // 求节点的最大深度
        private int maxDepth(TreeNode node) {
            return node == null ?
                    0 :
                    Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
        }
    }


}
    
