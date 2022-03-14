package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

import cn.vfwz.leetcode.util.*;

/**
 * author: vfwz
 * date: 2022-03-11 17:44:04
 * title: [144]二叉树的前序遍历
 */
@Slf4j
public class LC144_BinaryTreePreorderTraversal {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方法二
     * 思路：前序遍历的迭代写法
     */
    class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            ArrayList<Integer> result = new ArrayList<>();
            preOrder(root, result);
            return result;
        }

        public void preOrder(TreeNode root, List<Integer> res) {
            if(root == null) return;
            Deque<TreeNode> stack = new ArrayDeque<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                res.add(node.val);
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    /**
     * 方法一
     * 思路：前序遍历的递归写法
     */
    class Solution1 {
        public List<Integer> preorderTraversal(TreeNode root) {
            ArrayList<Integer> result = new ArrayList<>();
            preOrder(root, result);
            return result;
        }

        public void preOrder(TreeNode root, List<Integer> res) {
            if (root == null) return;
            res.add(root.val);
            preOrder(root.left, res);
            preOrder(root.right, res);
        }
    }

}
    
