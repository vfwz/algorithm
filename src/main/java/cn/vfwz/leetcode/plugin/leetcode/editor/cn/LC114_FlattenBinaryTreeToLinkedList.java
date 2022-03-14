package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import cn.vfwz.leetcode.util.TreeNode;
import cn.vfwz.leetcode.util.TreeNodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * author: vfwz
 * date: 2022-03-11 10:19:23
 * title: [114]二叉树展开为链表
 */
@Slf4j
public class LC114_FlattenBinaryTreeToLinkedList {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        Integer[] tree = new Integer[]{1, 2, 3, 4, null, 5, 6};
//        int[] tree = {1, 2, 3};
        TreeNode root = TreeNodeUtil.generateTreeFromArray(tree);
        System.out.println(root);

        solution.flatten(root);
        System.out.println("=== after flattern ===");
        System.out.println(root);
        System.out.println("=== result ===");
        printResult(root);

    }

    public void printResult(TreeNode root) {
        if(root == null) {
            System.out.print("null, ");
            return;
        }
        System.out.print(root.val + ", ");
        if(root.left == null && root.right == null) {
            return;
        }
        printResult(root.left);
        printResult(root.right);
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
        public void flatten(TreeNode root) {
            if (root == null) return;
            flatten(root.left);
            flatten(root.right);
            // 暂时保存右树根
            TreeNode right = root.right;

            // 根右节点指向左树根
            root.right = root.left;
            // 清空左树
            root.left = null;
            // 找到链表的尾
            while (root.right != null) {
                root = root.right;
            }
            // 在尾部接上右树
            root.right = right;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
    
