package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import cn.vfwz.leetcode.util.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * author: vfwz
 * date: 2022-03-11 14:00:29
 * title: [226]翻转二叉树
 */
@Slf4j
public class LC226_InvertBinaryTree {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
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
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            invertTree(root.left);
            invertTree(root.right);
            swap(root);
            return root;
        }

        // 交换左右子节点
        private void swap(TreeNode node) {
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
    
