package cn.vfwz.leetcode.util;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNodeUtil {

    /**
     * Leetcode的数组不是满二叉树，是改进过的二叉树层序遍历结果，用下面方式会报错
     */
    public static TreeNode generateTreeFromArrayErr(Integer[] array) {
        if (array.length == 0) {
            return new TreeNode();
        } else {
            TreeNode[] treeNodes = new TreeNode[array.length];
            TreeNode node = new TreeNode(array[0]);
            treeNodes[0] = node;
            for (int i = 1; i < array.length; i++) {
                TreeNode child = null;
                if (array[i] != null) {
                    child = new TreeNode(array[i]);
                }
                // 偶数下标右孩子
                if (i % 2 == 0) {
                    treeNodes[(i - 1) / 2].right = child;
                }
                // 基数下标左孩子
                else {
                    treeNodes[(i - 1) / 2].left = child;
                }
                treeNodes[i] = child;
            }
            return treeNodes[0];
        }
    }

    public static TreeNode generateTreeFromArray(Integer[] array) {
        if (array.length == 0) {
            return new TreeNode();
        } else {
            Queue<TreeNode> nextLayer = new LinkedList<>();
            TreeNode root = new TreeNode(array[0]);
            nextLayer.offer(root);
            int i = 1;
            while (!nextLayer.isEmpty()) {
                TreeNode pollNode = nextLayer.poll();
                if(pollNode == null) continue;

                if (i == array.length) break;
                Integer cur = array[i++];
                pollNode.left = null == cur ? null : new TreeNode(cur);

                if (i == array.length) break;
                cur = array[i++];
                pollNode.right = null == cur ? null : new TreeNode(cur);
                nextLayer.offer(pollNode.left);
                nextLayer.offer(pollNode.right);
            }
            return root;
        }
    }


    // 用于获得树的层数
    private static int getTreeDepth(TreeNode root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }

    private static void writeArray(TreeNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.val);

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }


    public static String visualizeBinaryTree(TreeNode root) {
        if (root == null) {
            return "EMPTY!";
        }
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(root, 0, arrayWidth / 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        StringBuilder sb = new StringBuilder();
        for (String[] line : res) {
            for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
