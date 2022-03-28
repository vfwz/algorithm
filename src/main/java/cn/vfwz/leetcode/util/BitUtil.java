package cn.vfwz.leetcode.util;

public class BitUtil {

    public static String toBitString(int i) {
        StringBuilder sb = new StringBuilder(32);
        for (int j = 0; j < 32; j++) {
            sb.insert(0, i & 1);
            i >>= 1;
        }
        return sb.toString();
    }

}
