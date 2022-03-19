package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import cn.vfwz.leetcode.util.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * author: vfwz
 * date: 2022-03-19 09:37:00
 * title: [606]根据二叉树创建字符串
 */
@Slf4j
public class LC606_ConstructStringFromBinaryTree {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        TreeNode treeNode = new TreeNode("[1,2,3,null,null,4]");
        System.out.println(treeNode);
        System.out.println(solution.tree2str(treeNode));

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 先序遍历
     */
    class Solution {
        public String tree2str(TreeNode root) {
            StringBuilder res = new StringBuilder();
            preOrder(res, root, true);
            res.deleteCharAt(0);
            res.deleteCharAt(res.length() - 1);
            return res.toString();
        }

        /**
         * @param res           结果字符串
         * @param node          当前节点
         * @param needPrintNull 空串是否需要打印
         */
        void preOrder(StringBuilder res, TreeNode node, boolean needPrintNull) {
            if (node == null) {
                res.append(needPrintNull ? "()" : "");
                return;
            }
            res.append("(");
            res.append(node.val);
            // 只有当子节点至少一个不为空时才需要打印
            if (node.left != null || node.right != null) {
                preOrder(res, node.left, true);
                preOrder(res, node.right, false);
            }
            res.append(")");
        }

    }
    //leetcode submit region end(Prohibit modification and deletion)


}
    
