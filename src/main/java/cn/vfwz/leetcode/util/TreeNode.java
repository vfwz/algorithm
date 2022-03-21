package cn.vfwz.leetcode.util;


// Definition for a binary tree node.
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    /**
     * 根据leetcode样式的二叉树输入构造二叉树
     * @param input 形如 [1,2,3,null,null,4] 的输入，同leetcode形式
     */
    public TreeNode(String input) {
        if(input.startsWith("[") && input.endsWith("]")) {
            input = input.substring(1, input.length() - 1);
        }
        String[] split = input.split(",");
        Integer[] ints = new Integer[split.length];
        for (int i = 0; i < split.length; i++) {
            ints[i] = "null".equals(split[i]) ? null : Integer.parseInt(split[i]);
        }
        TreeNode root = TreeNodeUtil.generateTreeFromArray(ints);
        this.val = root.val;
        this.left = root.left;
        this.right = root.right;
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 输出可视化的二叉树样式
     * @return 可视化的二叉树样式
     */
    @Override
    public String toString() {
        return TreeNodeUtil.visualizeBinaryTree(this);
    }

    public void printSelf() {
        System.out.println(this.toString());
    }

}
