package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * author: vfwz
 * date: 2022-03-22 21:42:30
 * title: [51]N 皇后
 */
@Slf4j
public class LC51_NQueens {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        System.out.println(solution.solveNQueens(4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        int N;
        List<List<String>> solves;

        public List<List<String>> solveNQueens(int n) {
            N = n;
            solves = new ArrayList<>();
            // 初始化棋盘
            char[][] board = new char[n][n];
            for (char[] line : board) {
                Arrays.fill(line, '.');
            }

            backtrack(board, 0);
            return solves;
        }

        void backtrack(char[][] board, int row) {
            if (row == N) {
                solves.add(boardToList(board));
                return; // 这里不写return也可以。当row==N时下面的check()都不会过。
            }

            for (int i = 0; i < N; i++) {
                if (!check(board, row, i)) continue;
                board[row][i] = 'Q';
                backtrack(board, row + 1);
                board[row][i] = '.';
            }
        }

        // 检查当前位置[row][col]放置queen是否合法
        boolean check(char[][] board, int row, int col) {
            for (int tRow = row - 1, backstep = 1; tRow >= 0; tRow--, backstep++) {
                int tCol = col;
                // 判断正上方是否有Q
                if (board[tRow][tCol] == 'Q') return false;

                // 判断左上方是否有Q
                tCol = col - backstep;
                if (tCol >= 0 && board[tRow][tCol] == 'Q') return false;

                // 判断右上方是否有Q
                tCol = col + backstep;
                if (tCol < N && board[tRow][tCol] == 'Q') return false;
            }
            return true;
        }

        List<String> boardToList(char[][] board) {
            List<String> listBoard = new ArrayList<>();
            for (char[] chars : board) {
                listBoard.add(new String(chars));
            }
            return listBoard;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
    
