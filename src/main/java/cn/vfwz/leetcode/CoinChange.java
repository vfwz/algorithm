package cn.vfwz.leetcode;


import java.util.Arrays;

/*
给你 k 种面值的硬币，面值分别为 c1, c2 ... ck，每种硬币的数量无限，再给一个总金额 amount，
问你最少需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1 。
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {3, 7, 8};
        int amount = 11;
        int ret = coinChange(coins, amount);


        System.out.println(ret);
    }

    public static int coinChange(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }

        int subMin = -1;
        for (int coin : coins) {
            int sub = coinChange(coins, amount - coin);
            if (sub == -1) continue;
            subMin = subMin==-1?sub:Math.min(subMin, sub);
        }
        return subMin==-1?subMin:subMin+1;
    }
}
