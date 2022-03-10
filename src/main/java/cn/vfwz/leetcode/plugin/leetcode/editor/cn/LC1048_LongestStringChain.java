package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * author: vfwz
 * date: 2022-03-10 09:43:03
 * title: [1048]最长字符串链
 */
@Slf4j
public class LC1048_LongestStringChain {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        String[] words = {"a", "b", "ba", "bca", "bda", "bdca"};
        System.out.println(solution.longestStrChain(words));
        words = new String[]{"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"};
        System.out.println(solution.longestStrChain(words));
        words = new String[]{"abcd", "dbqca"};
        System.out.println(solution.longestStrChain(words));
        words = new String[]{"qyssedya","pabouk","mjwdrbqwp","vylodpmwp","nfyqeowa","pu","paboukc","qssedya","lopmw","nfyqowa","vlodpmw","mwdrqwp","opmw","qsda","neo","qyssedhyac","pmw","lodpmw","mjwdrqwp","eo","nfqwa","pabuk","nfyqwa","qssdya","qsdya","qyssedhya","pabu","nqwa","pabqoukc","pbu","mw","vlodpmwp","x","xr"};
        System.out.println(solution.longestStrChain(words));

    }


    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 思路：回溯算法
     * 解题：
     * 1. 将字符串数组按照长度升序排列
     * 2. 从前向后选择，回溯条件：后续选项的长度大于当前值长度超过1位，或者后续选项都不是当前值的后身
     */
    class Solution {
        public int longestStrChain(String[] words) {
            // 将字符串数组按照长度升序排列
//            Arrays.sort(words, (a,b) -> a.length() - b.length());
            Arrays.sort(words, Comparator.comparingInt(String::length));

            Map<String, Integer> dpTable = dpTable(words);
            System.out.println(dpTable);

            int max = 0;
            for (Integer value : dpTable.values()) {
                max = Math.max(max, value);
            }

            return max;
        }

        private Map<String, Integer> dpTable(String[] words) {
            // 当前字符串做为串链结尾能形成的串的最长长度
            Map<String, Integer> dpTable = new HashMap<>();
            for (String word : words) {
                int max = 0;
                List<String> lenList = Arrays.stream(words).filter(s -> s.length() == word.length() - 1).collect(Collectors.toList());
                for (String str : lenList) {
                    if (isPredecessor(str, word)) {
                        max = Math.max(max, dpTable.getOrDefault(str, 0));
                    }
                }
                dpTable.put(word, max + 1);
            }
            return dpTable;
        }

        String removeIndexAt(String src, int index) {
            return src.substring(0, index) + src.substring(index + 1);
        }

        // 判断字符串a是否是字符串b的前身
        // 将a,b字符串都转为字符数组，根据a的长度遍历，对应元素不同，则a不是b的前身
        private boolean isPredecessor(String a, String b) {
            if (b.length() != a.length() + 1) {
                return false;
            }
            char[] charsA = a.toCharArray();
            char[] charsB = b.toCharArray();
            for (int i = 0, j = 0; i < charsA.length && j < charsB.length; i++, j++) {
                if (charsA[i] != charsB[j]) {
                    // 按序只允许有一个不相同
                    if (j - --i > 1) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
    
