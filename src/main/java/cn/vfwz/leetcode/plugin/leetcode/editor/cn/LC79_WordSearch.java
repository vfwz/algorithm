package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * author: vfwz
 * date: 2022-03-20 20:05:20
 * title: [79]单词搜索
 */
@Slf4j
public class LC79_WordSearch {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "G";

        System.out.println(solution.exist(board, word));

         board = new char[][]{{'a'}};
         word = "a";

        System.out.println(solution.exist(board, word));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 回溯算法
     * 思路：回溯
     * 注意： 剪枝，找到就直接返回结果，其他路径就不用搜索了
     * 字符不能重复使用，需要设置一个visited数组
     */
    class Solution {
        public boolean exist(char[][] board, String word) {

            boolean[][] visited = new boolean[board.length][board[0].length];
            for (int vert = 0; vert < board.length; vert++) {
                for (int hori = 0; hori < board[0].length; hori++) {
                    if (backtrack(board, hori, vert, 0, word.toCharArray(), visited)) return true;
                }
            }
            return false;
        }

        boolean backtrack(char[][] board, int hori, int vert, int nextCharIndex, char[] chars, boolean[][] visited) {
            // 所有的目标字符都访问到了
            if (nextCharIndex == chars.length) {
                return true;
            }
            // 判断是否越界
            if (vert < 0 || vert >= board.length || hori < 0 || hori >= board[0].length) {
                return false;
            }

            // 当前元素没有使用过，并且就是下一个目标字符
            if (!visited[vert][hori] && board[vert][hori] == chars[nextCharIndex]) {

                // 已经使用过的给个标记
                visited[vert][hori] = true;
                nextCharIndex++;

                // 有一边找到了就不继续找了

                // 左边的元素
                if (backtrack(board, hori - 1, vert, nextCharIndex, chars, visited)) return true;

                // 右边的元素
                if (backtrack(board, hori + 1, vert, nextCharIndex, chars, visited)) return true;

                // 上边的元素
                if (backtrack(board, hori, vert - 1, nextCharIndex, chars, visited)) return true;

                // 下边的元素
                if (backtrack(board, hori, vert + 1, nextCharIndex, chars, visited)) return true;

                // 回溯使用标记
                visited[vert][hori] = false;
            }

            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
   