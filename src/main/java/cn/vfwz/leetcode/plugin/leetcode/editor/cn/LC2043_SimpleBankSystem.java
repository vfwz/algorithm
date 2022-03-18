package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

import cn.vfwz.leetcode.util.*;

/**
 * author: vfwz
 * date: 2022-03-18 09:51:00
 * title: [2043]简易银行系统
 */
@Slf4j
public class LC2043_SimpleBankSystem {

    @Test
    public void testSolution() {
//        Solution solution = new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路：CRUD题，注意转账时候的事务性
     * 20220318 每日一题
     */
    class Bank {

        private long[] balance;
        private int size;

        public Bank(long[] balance) {
            this.balance = balance;
            this.size = balance.length;
        }

        public boolean transfer(int account1, int account2, long money) {
            if (isAccountExist(account1)
                    && isAccountExist(account2)) {
                if(withdraw(account1, money)) {
                    return deposit(account2, money);
                }
            }

            return false;
        }

        public boolean deposit(int account, long money) {
            if (isAccountExist(account)) {
                balance[account - 1] += money;
                return true;
            }
            return false;
        }

        public boolean withdraw(int account, long money) {
            if (isAccountExist(account) && money <= balance[account - 1]) {
                balance[account - 1] -= money;
                return true;
            }
            return false;
        }

        // 账户是否存在
        private boolean isAccountExist(int account) {
            return account >= 1 && account <= size;
        }

    }

    /**
     * Your Bank object will be instantiated and called as such:
     * Bank obj = new Bank(balance);
     * boolean param_1 = obj.transfer(account1,account2,money);
     * boolean param_2 = obj.deposit(account,money);
     * boolean param_3 = obj.withdraw(account,money);
     */
    //leetcode submit region end(Prohibit modification and deletion)


}
    
