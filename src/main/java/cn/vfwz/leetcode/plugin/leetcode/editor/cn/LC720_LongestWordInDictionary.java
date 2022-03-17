package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * author: vfwz
 * date: 2022-03-17 09:38:16
 * title: [720]词典中最长的单词
 * 20220317 每日一题
 */
@Slf4j
public class LC720_LongestWordInDictionary {

    @Test
    public void testSolution() {
        Solution1 solution = new Solution1();
        // 期望结果:"lemdqgqwumegvfexckxgiadwlc" 这个案例用本地电脑跑DFS用时94.255s
        String[] input = {"sqmhtpcg", "hzzpjoossvzgyg", "sqmhtpcgdsdfqgxsjgqizl", "ncghfsccrgqkafh", "sq", "uiuwyorgmesm", "xtwmnu", "ix", "japawosnoiobnrfjrsbr", "bqwkb", "japawosnoio", "xtwmnup", "hzzpjoossvzgygxhgs", "wve", "yizslwlpn", "ixl", "sqmhtp", "as", "zfzpuxghsfme", "kcudnbvfu", "e", "momqxn", "ixlfvbvdzwndvfi", "uwp", "yizslwlp", "ixlfvbvdzwnd", "qdowgpmd", "japawosnoiob", "qdowgpmdjcowemk", "yizslwlpndzjih", "qdowgpmdjc", "ovly", "tpktncgau", "tpktncgausglcelnpn", "ixlfvbv", "kcudnbvfugbntkdgjs", "uiuwyorgme", "c", "xtwmnupebcuzb", "sqmhtpcgdsdfqgxsjgqi", "ixlfvbvdzwn", "japawosnoiobnrfjrsbrc", "d", "sqmhtpcgdsdfqgxs", "uiuwyorgmesml", "vg", "xtwmnupebcuz", "uiuwy", "xtwmnupe", "hzzpjoossvzgygxhgsiy", "ixlfv", "uiuwyorg", "yizslwlpnd", "qdowgpmdj", "lemdqgqwumegvfe", "r", "sqmhtpcgds", "ncghfsccrgqka", "kcudnbvfugb", "asglpcpgctpdcehw", "tpkt", "momqxnl", "x", "ncghfs", "asglp", "hzzpjo", "tp", "zfzpu", "momq", "yizslwlpndzjihonf", "uw", "erk", "fu", "ahx", "uiuwyo", "fukwsv", "ncghf", "ah", "gslbknctqw", "wvep", "a", "p", "qdowgpmdjcowemkacwzox", "zfzp", "ahxm", "pgd", "yizslwlpndzjihonftl", "kc", "xtwmn", "xtwmnupebcuzbgn", "lemdqg", "k", "yizslwlpndzjihon", "hzz", "hzzpjoossvzgygxhgsiyduo", "ct", "xtwm", "m", "lemdqgqwumegvf", "u", "o", "l", "erkufy", "ncgh", "lemdqgqwumegvfexckxgiadwlc", "erkufypwnuh", "tpktnc", "tpktncgaus", "er", "ncghfsccrgqk", "ixlfvbvdzwndvf", "q", "zfzpuxgh", "sqmhtpc", "yizslwl", "uiuwyorgm", "mo", "ixlfvbvd", "ctguxtac", "bqw", "zfzpux", "kcudnbvfugbntkdgjst", "japawosnoiobnrfjrsb", "kcudnbvf", "qdow", "uiuwyor", "gs", "bqwk", "lemdqgqwumegvfexckx", "yizslwlpndzjiho", "hzzpj", "gslbknctqwk", "hzzpjoossvzgygxh", "sqmhtpcgdsdfqgx", "tpktncga", "xtwmnupebcuzbg", "erkufypwnu", "japawos", "ncghfsccr", "ctgu", "tpk", "tpktncgausglceln", "zfzpuxghsfmeq", "xt", "tpktncgausglce", "kcudnb", "kcudnbvfugbn", "zf", "qdowgpmdjcowemka", "ixlf", "ixlfvb", "qdowgpmdjcowemkacw", "tpktncgausglcel", "yiz", "asglpcpgctpd", "yizslwlpndz", "ctgux", "lemdqgq", "qdowg", "pg", "asg", "f", "ovl", "japawo", "sqmhtpcgdsdfqgxsjg", "t", "sqmhtpcgdsdfqgxsj", "gslbk", "hzzpjoossvzgy", "qd", "kcudnbv", "asglpcpgctpdcehwte", "gslbknct", "sqmhtpcgdsd", "zfzpuxghsf", "y", "kcudnbvfugbntkdgjstx", "z", "gslbknctq", "hzzpjoo", "sqmhtpcgdsdfqgxsjgq", "bqwkbp", "v", "japaw", "yizslwlpndzjihonftlel", "le", "ixlfvbvdzw", "qdowgpmdjcowemkacwzoxuk", "aq", "japawosnoiobnrf", "yizslwlpndzjihonftle", "ncghfsccrgqkafho", "gslbkn", "kcud", "xtwmnupeb", "ctguxta", "erku", "qdo", "japawosnoiobn", "tpktncgausglcelnpno", "ncghfsccrgq", "erkufypwnuhd", "kcudnbvfugbntkdgj", "ixlfvbvdzwndv", "ixlfvbvdz", "erkuf", "momqx", "ui", "asglpcpgct", "ja", "lem", "japawosnoiobnr", "vgirdgjs", "kcudnbvfugbntkd", "kcudnbvfugbntk", "asglpcp", "sqmhtpcgdsdfqgxsjgqiz", "ncghfscc", "xtw", "rj", "qdowgpmdjcowemkac", "kcudnbvfug", "fuk", "tpktncgausglc", "yizslwlpndzjihonft", "gsl", "hzzpjoos", "lemdqgqwumegvfexckxgiadwl", "ctg", "h", "hzzpjoossvzgygxhgsi", "w", "yizsl", "qv", "uiu", "hzzpjoossvzgygx", "zfzpuxg", "lemdqgqwumegvfex", "lemdqgqwu", "dd", "yizslw", "kcu", "qdowgpmdjcowem", "qdowgpmdjcowemkacwzoxu", "vgir", "asglpcpgctpdceh", "hzzpjoossvzgygxhgsiyduod", "yizslwlpndzji", "wvepj", "fukws", "hzzpjoossvzgygxhgsiyd", "b", "momqxnldh", "kcudnbvfugbntkdg", "qdowgpmdjcowe", "asglpcpgc", "lemdqgqw", "lemdqgqwumeg", "erkufypwn", "qdowgpmdjcow", "zfzpuxghsfmeqb", "sqmhtpcgdsdf", "ncg", "gslbknc", "lemdq", "tpktncgausg", "ncghfsc", "vgirdg", "lemdqgqwume", "qdowgpmdjcowemkacwzo", "qdowgpmdjcowemkacwz", "tpktncgausglcelnp", "sqmht", "lemdqgqwumegv", "ncghfsccrgqkaf", "japawosnoi", "fukw", "rju", "hzzpjoossvzgygxhgsiyduodp", "g", "japawosnoiobnrfj", "zfz", "aqfmw", "kcudnbvfugbnt", "zfzpuxghs", "s", "nc", "lemdqgqwumegvfexckxg", "xtwmnupebcu", "tpktncg", "vgirdgj", "hzzpjooss", "ncghfsccrgqkafhoa", "lemdqgqwumegvfexckxgiadw", "mom", "ov", "ctguxt", "japawosno", "japawosnoiobnrfjrs", "lemdqgqwumegvfexckxgia", "gslb", "tpktn", "asglpcpgctpdcehwt", "japawosnoiobnrfjr", "xtwmnupebc", "lemd", "sqmhtpcgdsdfq", "bq", "sqmh", "tpktncgausgl", "uiuwyorgmes", "yizslwlpndzj", "yizs", "qdowgpm", "qdowgpmdjcowemkacwzoxukg", "hz", "bqwkbpq", "zfzpuxghsfm", "asglpc", "ncghfsccrg", "momqxnld", "lemdqgqwumegvfexckxgi", "japawosnoiobnrfjrsbrcu", "qdowgpmdjco", "lemdqgqwumegvfexckxgiad", "ixlfvbvdzwndvfir", "aqf", "asglpcpgctpdc", "j", "erkufypw", "vgird", "asglpcpgctp", "hzzpjoossvzgygxhgsiydu", "kcudn", "wv", "lemdqgqwum", "hzzpjoossv", "vgirdgjszo", "sqmhtpcgd", "sqm", "vgirdgjsz", "sqmhtpcgdsdfqg", "asglpcpgctpdce", "yi", "jap", "lemdqgqwumegvfexc", "uiuw", "asgl", "hzzp", "lemdqgqwumegvfexck", "japawosnoiobnrfjrsbrcuz", "asglpcpg", "hzzpjoossvzgygxhg", "i", "n", "qdowgp", "vgi", "hzzpjoossvz", "japawosn", "japa", "hzzpjoossvzg", "erkufyp"};
//        String[] input = {"yo", "ew", "fc", "zrc", "yodn", "fcm", "qm", "qmo", "fcmz", "z", "ewq", "yod", "ewqz", "y"};
//        String[] input = {"rac", "rs", "ra", "on", "r", "otif", "o", "onpdu", "rsf", "rs", "ot", "oti", "racy", "onpd"};

        Arrays.sort(input, String::compareTo);

        Arrays.stream(input).forEach(System.out::println);

        long start = System.currentTimeMillis();
        log.warn("start time: [{}]", System.currentTimeMillis());
        System.out.println(solution.longestWord(input));
        log.warn("end time: [{}], cost: [{}]ms", System.currentTimeMillis(), System.currentTimeMillis() - start);

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方法二：排序 + Hash表保存前缀
     */
    class Solution {
        public String longestWord(String[] words) {
            String longestWord = "";
            // 按照字母长度从小到大排序
            Arrays.sort(words, String::compareTo);
            // Hash表保存合法的单词, 可以换做HashSet, 不过HashSet的底层也是HashMap也是一样的
            HashMap<String, String> prefixMap = new HashMap<>();
            for (String word : words) {
                if (word.length() == 1 || prefixMap.containsKey(word.substring(0, word.length() - 1))) {
                    // prefix进行保存
                    prefixMap.put(word, word);
                    // 相同前缀，长度大的才会替换，也就是前缀相同，长度相同的单词，字典序后面的不会替换前面的
                    if (word.length() > longestWord.length()) {
                        longestWord = word;
                    }
                }
            }
            return longestWord;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    /**
     * 方法一：DFS
     * 运行超时了ORZ
     */
    class Solution1 {

        String longestWord = "";

        public String longestWord(String[] words) {
            // 按照字母长度从小到大排序
            Arrays.sort(words, Comparator.comparingInt(String::length));
            dfs(words, 0, "");
            return longestWord;
        }

        void dfs(String[] words, int index, String curStr) {
            for (int i = index; i < words.length; i++) {
                String word = words[i];
                // 剪枝，每次只向前遍历比当前大一个长度的数据
                if (word.length() == curStr.length()) {
                    continue;
                } else if (word.length() - 1 > curStr.length()) {
                    break;
                }

                // 隐含条件 word.length() - 1 == curStr.length()
                if (word.startsWith(curStr)) { // 判断curStr是word的前缀
                    if (word.length() > longestWord.length() ||  // 当前值比最大值的长度更长
                            (word.length() == longestWord.length() && word.compareTo(longestWord) < 0)) { // 当前值和最大值长度相同但是字典序更小
                        longestWord = word;
                    }
                    dfs(words, i + 1, curStr); // 继续向前寻找前缀是curStr的单词
                    dfs(words, i + 1, word); // 向前寻找前缀是word的单词
                }
            }
        }

    }

}
    
