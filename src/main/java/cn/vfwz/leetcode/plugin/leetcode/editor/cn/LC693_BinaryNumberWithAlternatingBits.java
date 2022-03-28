package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

import cn.vfwz.leetcode.util.*;

/**
 * author: vfwz
 * date: 2022-03-28 10:05:54
 * title: [693]交替位二进制数
 */
@Slf4j
public class LC693_BinaryNumberWithAlternatingBits {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
//        System.out.println(BitUtil.toBitString(Integer.MAX_VALUE));
//        System.out.println(BitUtil.toBitString(Integer.MIN_VALUE));
//        System.out.println(BitUtil.toBitString(-1));
//        System.out.println(BitUtil.toBitString(1));
//        System.out.println(BitUtil.toBitString(5));
//        System.out.println(BitUtil.toBitString(11));
        System.out.println(solution.hasAlternatingBits(5));
        System.out.println(solution.hasAlternatingBits(7));
        System.out.println(solution.hasAlternatingBits(21));
        System.out.println(solution.hasAlternatingBits(Integer.MAX_VALUE));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方法二：巧用异或
     * 原数右移一位，还是位交错的数
     * 再和原数异或得到 00..00011..111 位形式的数
     * 将其加1得到     00..00100..000
     * 再和自己按位与必得到0
     */
    class Solution {
        public boolean hasAlternatingBits(int n) {
            int a = (n >> 1) ^ n;
            return (a & (a + 1)) == 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    /**
     * 方法一：位运算
     * 与1 得末尾bit位
     * 右移1位出去末尾位
     */
    class Solution1 {
        public boolean hasAlternatingBits(int n) {
            int beforeBit = n & 1;
            n >>= 1;
            while (n > 0) {
                int curBit = n & 1;
                if (curBit == beforeBit) {
                    return false;
                }
                beforeBit = curBit;
                n >>= 1;
            }
            return true;
        }
    }


}
    
