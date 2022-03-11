package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import cn.vfwz.leetcode.util.TreeNode;
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
        int[] tree = {1, 2, 3, 4, -1, 5, 6};
//        int[] tree = {1, 2, 3};
        TreeNode root = generateTreeFromArr(tree);
        System.out.println(root);

        solution.flatten(root);
        System.out.println("=== after flattern ===");
        System.out.println(root);
        System.out.println("=== result ===");
        printResult(root);

    }

    public void printResult(TreeNode root) {
        if(root == null) {
            System.out.print(", null");
            return;
        }
        System.out.print(", " + root.val);
        printResult(root.left);
        printResult(root.right);
    }

    private TreeNode generateTreeFromArr(int[] treeArr) {
        TreeNode[] treeNodes = new TreeNode[treeArr.length];
        TreeNode node = new TreeNode(treeArr[0]);
        treeNodes[0] = node;
        for (int i = 1; i < treeArr.length; i++) {
            TreeNode child = null;
            if (treeArr[i] != -1) {
                child = new TreeNode(treeArr[i]);
            }
            if (i % 2 == 0) { // 偶数右孩子
                treeNodes[(i - 1) / 2].right = child;
            } else {
                treeNodes[(i - 1) / 2].left = child;
            }
            treeNodes[i] = child;
        }
        return treeNodes[0];
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
    
