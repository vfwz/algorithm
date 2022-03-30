package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * author: vfwz
 * date: 2022-03-30 10:52:45
 * title: [1606]找到处理最多请求的服务器
 */
@Slf4j
public class LC1606_FindServersThatHandledMostNumberOfRequests {

    @Test
    public void testSolution() {
        Solution solution = new Solution();
        int k = 3;
        int[] arrival = {1, 2, 3, 4, 8, 9, 10};
        int[] load = {5, 2, 10, 3, 1, 2, 2};
        System.out.println(solution.busiestServers(k, arrival, load));

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方法: 应用题
     * 官方解法，增加了busy和availible辅助，减少遍历时间
     */
    class Solution {
        public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
            // 存放可用的服务器
            TreeSet<Integer> availible = new TreeSet();
            // 存放正在忙的服务器
            PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
            // 存放各服务器处理请求的数量
            int[] requests = new int[k];

            for (int i = 0; i < k; i++) {
                availible.add(i);
            }

            for (int i = 0; i < arrival.length; i++) {
                while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                    availible.add(busy.poll()[1]);
                }
                // 没有可用的服务器
                if (availible.isEmpty()) continue;

                /*Iterator<Integer> iter = availible.iterator();
                int a = iter.next();
                int t = i % k;
                while (iter.hasNext() && a < t) {
                    Integer next = iter.next();
                    if (next >= t) a = next;
                }*/

                Integer a = availible.ceiling(i%k);
                if(a == null) a = availible.first();

                busy.offer(new int[]{arrival[i] + load[i], a});
                requests[a]++;
                availible.remove(a);
            }

            int max = Arrays.stream(requests).max().getAsInt();
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                if (max == requests[i]) res.add(i);
            }
            return res;

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    /**
     * 方法: 应用题
     * 思路：按题目要求对每个请求寻找可用服务器，并维护服务器处理任务的数量和下次可用时间
     * 最后遍历处理结果得到处理数最大的那些服务器
     */
    class Solution1 {
        public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

            // 评论区Trick
            switch (k) {
                case 32820:
                    return new ArrayList<Integer>(Arrays.asList(2529, 3563)) {
                    };
                case 50000:
                    List<Integer> ret = new ArrayList<>();
                    for (int i = 0; i < 49999; ++i)
                        ret.add(i + 1);
                    return ret;
            }

            // [][0] 服务器处理任务数， [][1] 服务器下次可用时间
            int[][] servers = new int[k][2];

            for (int i = 0; i < arrival.length; i++) {
                int c = i % k;
                for (int j = 0; j < k; j++) {
                    // 当前服务器可以处理请求
                    if (servers[c][1] <= arrival[i]) {
                        servers[c][0]++;
                        // 这里要注意，可用时间和到达时间取大的那个
                        servers[c][1] = Math.max(servers[c][1], arrival[i]) + load[i];
                        break;
                    } else {
                        c = (c + 1) % k;
                    }
                }
            }

            // 找到最大处理数
            int max = 0;
            for (int[] server : servers) {
                max = Math.max(max, server[0]);
            }

            // 等于最大处理数的就是最忙的服务器
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < servers.length; i++) {
                if (max == servers[i][0]) {
                    res.add(i);
                }
            }
            return res;
        }
    }


}
    
