package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import cn.vfwz.leetcode.util.TreeNode;
import cn.vfwz.leetcode.util.TreeNodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * author: vfwz
 * date: 2022-03-11 14:43:18
 * title: [104]二叉树的最大深度
 */
@Slf4j
public class LC104_MaximumDepthOfBinaryTree {

    @Test
    public void testSolution() {
        Solution solution = new Solution();

        Integer[] array = {3, 9, 20, null, null, 15, 7};
        TreeNode root = TreeNodeUtil.generateTreeFromArray(array);
        System.out.println(root);
        System.out.println(solution.maxDepth(root));
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
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return Math.max(maxDepth(root.left), maxDepth((root.right))) + 1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
    
