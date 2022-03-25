package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * author: vfwz
 * date: 2022-03-24 11:29:09
 * title: [661]图片平滑器
 */
@Slf4j
public class LC661_ImageSmoother {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int M, N;

        public int[][] imageSmoother(int[][] img) {
            M = img.length;
            N = img[0].length;
            int[][] res = new int[M][N];

            for (int i = 0; i < img.length; i++) {
                for (int j = 0; j < img[i].length; j++) {
                    res[i][j] = smooth(img, i, j);
                }
            }

            return res;
        }

        // 对 img[i][j]位置进行平滑处理
        int smooth(int[][] img, int i, int j) {
            int total = 0;
            int num = 0;
            for (int i1 = i - 1; i1 <= i + 1; i1++) {
                for (int j1 = j - 1; j1 <= j + 1; j1++) {
                    if (i1 >= 0 && i1 < M && j1 >= 0 && j1 < N) {
                        total += img[i1][j1];
                        num++;
                    }
                }
            }
            return total / num;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        int M, N;

        public int[][] imageSmoother(int[][] img) {
            M = img.length;
            N = img[0].length;
            int[][] res = new int[M][N];

            for (int i = 0; i < img.length; i++) {
                for (int j = 0; j < img[i].length; j++) {
                    res[i][j] = smooth(img, i, j);
                }
            }

            return res;
        }

        // 对 img[i][j]位置进行平滑处理
        int smooth(int[][] img, int i, int j) {
            int total = getValue(img, i - 1, j - 1) + getValue(img, i - 1, j) + getValue(img, i - 1, j + 1)
                    + getValue(img, i, j - 1) + getValue(img, i, j) + getValue(img, i, j + 1)
                    + getValue(img, i + 1, j - 1) + getValue(img, i + 1, j) + getValue(img, i + 1, j + 1);

            int num = 9; // 默认9个
            if ((i == 0 || i == M - 1) && (j == 0 || j == N - 1)) { // 四个角落
                if (M == 1 && N == 1) { // 只有一个格子
                    num = 1;
                } else if (M == 1 || N == 1) { // 只有一行或者一列
                    num = 2;
                } else {
                    num = 4;
                }
            } else if ((i > 0 && i < M - 1) && (j == 0 || j == N - 1)) { // 左右边缘
                num = N == 1 ? 3 : 6; // 只有一列是3个格子
            } else if ((i == 0 || i == M - 1) && (j > 0 && j < N - 1)) { //上下边缘
                num = M == 1 ? 3 : 6; // 只有一行是3个格子
            }

            return total / num;
        }

        /**
         * 获取img[i][j]位置的值，如果超出范围则返回0
         */
        int getValue(int[][] img, int i, int j) {
            if (i < 0 || i >= M) {
                return 0;
            }
            if (j < 0 || j >= N) {
                return 0;
            }
            return img[i][j];
        }

    }


}
    
