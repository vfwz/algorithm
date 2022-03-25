package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * author: vfwz
 * date: 2022-03-25 22:10:58
 * title: [208]实现 Trie (前缀树)
 */
@Slf4j
public class LC208_ImplementTriePrefixTree {

    @Test
    public void testSolution() {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.search("apple"); // 返回 True
        trie.search("app"); // 返回 False
        trie.startsWith("app"); // 返回 True
        trie.insert("app");
        trie.search("app"); // 返回 True
        trie.insert("apk");
        trie.insert("apkk");
        trie.insert("appl");
        trie.insert("apple");
        System.out.println(trie);
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方式二：TrieNode节点
     * leetcode官方题解，优化search和startWith方法，合并重复逻辑
     * 优化：用hash表代替数组实现children，稍微节省一些空间，其实也没节省多少，HashMap底层还是数组
     */
    class Trie {
        boolean isEnd = false;
        Map<Character, Trie> children;

        public Trie() {
            children = new HashMap<>();
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = (char) (word.charAt(i) - 'a');
                if (node.children.get(ch) == null) node.children.put(ch, new Trie());
                node = node.children.get(ch);
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            Trie node = searchPrefix(prefix);
            return node != null;
        }

        private Trie searchPrefix(String prefix) {
            Trie node = this;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = (char) (prefix.charAt(i) - 'a');
                if (node.children.get(ch) == null) return null;
                node = node.children.get(ch);
            }
            return node;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    /**
     * 方式一：二维数组
     */

    class Trie1 {
        /* 使用静态常量优化，创建多个对象时重复利用静态常量
        private static final int NUM = 100009;
        private static final int[][] trieArray = new int[NUM][26];
        private static final int[] words = new int[NUM];
        private static int index = 0; // 记录当前trieArray使用到的索引
         */


        private int NUM = 100009;
        private int[][] trieArray = new int[NUM][26];
        private int[] words = new int[NUM];
        private int index = 0; // 记录当前trieArray使用到的索引

        public Trie1() {
            for (int i = index; i >= 0; i--) {
                Arrays.fill(trieArray[i], 0);
                words[i] = 0;
            }
            index = 0;
        }

        public void insert(String word) {
            int p = 0;
            for (int i = 0; i < word.length(); i++) {
                int ch = word.charAt(i) - 'a';
                if (trieArray[p][ch] == 0) trieArray[p][ch] = ++index;
                p = trieArray[p][ch];
            }
            words[p]++;
        }

        public boolean search(String word) {
            int p = 0;
            for (int i = 0; i < word.length(); i++) {
                int ch = word.charAt(i) - 'a';
                if (trieArray[p][ch] == 0) return false;
                p = trieArray[p][ch];
            }
            return words[p] > 0;
        }

        public boolean startsWith(String prefix) {
            int p = 0;
            for (int i = 0; i < prefix.length(); i++) {
                int ch = prefix.charAt(i) - 'a';
                if (trieArray[p][ch] == 0) return false;
                p = trieArray[p][ch];
            }
            return true;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(100);
            sb.append("trieArray: \n{\n");
            for (int i = 0; i <= index; i++) {
                int[] trie = trieArray[i];
                sb.append(i).append(":")
                        .append("word=").append(words[i]).append(",")
                        .append(trieToString(trie)).append("\n");
            }
            sb.append("} \n");

            sb.append("index=").append(index);

            return sb.toString();
        }

        private String trieToString(int[] trie) {
            StringBuilder sb = new StringBuilder(200);
            sb.append("[");
            for (int i = 0; i < trie.length; i++) {
                if (trie[i] == 0) continue;
                sb.append((char) (i + 'a')).append("|").append(trie[i]).append(",");
            }
            // 删除末位的 ','
            if (sb.charAt(sb.length() - 1) == ',') sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            return sb.toString();
        }
    }

}
   