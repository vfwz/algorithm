package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * author: vfwz
 * date: 2022-03-12 12:17:15
 * title: [46]全排列
 */
@Slf4j
public class LC46_Permutations {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        solution.permute(new int[]{1, 2 ,3});
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 方法思路：子问题，拿到首节点，对剩下的部分进行全排列，将首节点插入到全排列的各个位置得到最终的全排列
         */
        public List<List<Integer>> permute(int[] nums) {

            List<Integer> numsList = new ArrayList<>();
            for (int num : nums) {
                numsList.add(num);
            }
            return permute(numsList);
        }

        public List<List<Integer>> permute(List<Integer> nums) {

            List<List<Integer>> res = new ArrayList<>();
            if(nums.size() == 1) {
                res.add(nums);
                return res;
            } else {
                Integer e = nums.get(0);
                // 子数组的全排列
                List<List<Integer>> permute = permute(nums.subList(1, nums.size()));
                // 通过子数组的全排列得到当前数组的全排列
                for (List<Integer> elem : permute) {
                    for (int j = 0; j <= elem.size(); j++) {
                        ArrayList<Integer> tmp = new ArrayList<>(elem);
                        tmp.add(j, e);
                        res.add(tmp);
                    }
                }
            }
            return res;
        }


    }
    //leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        /**
         * 方法思路：使用回溯算法
         */
        public List<List<Integer>> permute(int[] nums) {

            List<List<Integer>> results = new ArrayList<>();
            List<Integer> selected = new ArrayList<>();
            List<Integer> unselected = new ArrayList<>();
            for (int num : nums) {
                unselected.add(num);
            }
            backtrack(selected, unselected, results);
            return results;
        }

        private void backtrack(List<Integer> selected, List<Integer> unselected, List<List<Integer>> results) {
            System.out.println(selected + " / " + unselected);
            // 结束条件
            // ==1 减少一次尾递归，==0更符合直觉，代码也少点
            if(unselected.size() == 1) {
                selected.add(unselected.get(0));
                System.out.println(selected + " / " + unselected);
                results.add(new ArrayList<>(selected));
                selected.remove(unselected.get(0));
                return;
            }
            // 此处需要克隆一个列表，防止出现ConcurrentModificationException
            List<Integer> temp = new ArrayList<>(unselected);
            for (Integer unsel : temp) {
                // 做选择
                selected.add(unsel);
                unselected.remove(unsel);
                // 递归回溯
                backtrack(selected, unselected, results);
                // 撤销选择
                unselected.add(unsel);
                selected.remove(unsel);
            }

        }
    }
}
   