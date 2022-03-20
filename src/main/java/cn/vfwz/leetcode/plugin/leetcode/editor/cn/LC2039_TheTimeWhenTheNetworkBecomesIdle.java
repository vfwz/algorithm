package cn.vfwz.leetcode.plugin.leetcode.editor.cn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * author: vfwz
 * date: 2022-03-20 01:19:04
 * title: [2039]网络空闲的时刻
 * 20220320 每日一题
 */
@Slf4j
public class LC2039_TheTimeWhenTheNetworkBecomesIdle {

    @Test
    public void testSolution() {
        Solution solution = new Solution();

        int[][] edges = {{5, 7}, {15, 18}, {12, 6}, {5, 1}, {11, 17}, {3, 9}, {6, 11}, {14, 7}, {19, 13}, {13, 3}, {4, 12}, {9, 15}, {2, 10}, {18, 4}, {5, 14}, {17, 5}, {16, 2}, {7, 1}, {0, 16}, {10, 19}, {1, 8}};
        int[] patience = {0, 2, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1};

        // System.out.println(solution.networkBecomesIdle(edges, patience));

        edges = new int[][]{{3, 8}, {4, 13}, {0, 7}, {0, 4}, {1, 8}, {14, 1}, {7, 2}, {13, 10}, {9, 11}, {12, 14}, {0, 6}, {2, 12}, {11, 5}, {6, 9}, {10, 3}};
        patience = new int[]{0, 3, 2, 1, 5, 1, 5, 5, 3, 1, 2, 2, 2, 2, 1};

        System.out.println(solution.networkBecomesIdle(edges, patience));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 方法：BFS
     * 思路：首先获得所有数据服务器到主服务器的距离
     * 然后计算每个数据服务器最后一次收到数据的时间，找到最大值，就可以得到何时空闲了
     */
    class Solution {
        public int networkBecomesIdle(int[][] edges, int[] patience) {

            // 根据edges数组得到网络的连接关系
            Map<Integer, List<Integer>> conn = new HashMap<>();
            for (int[] edge : edges) {
                List<Integer> neighbors = conn.computeIfAbsent(edge[0], k -> new ArrayList<>());
                neighbors.add(edge[1]);
                List<Integer> neighbors2 = conn.computeIfAbsent(edge[1], k -> new ArrayList<>());
                neighbors2.add(edge[0]);
            }
            // System.out.println(conn);

            // 各数据服务器到主服务器的距离
            int[] dist = new int[patience.length];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[0] = 0;
            calcDist(dist, conn);
            AtomicInteger index = new AtomicInteger(0);
            // Arrays.stream(dist).
            //         forEach(d -> System.out.print(index.get() + "=[d" + d + ", p" + patience[index.getAndIncrement()] + "], "));
            // System.out.println();

            // 计算数据服务器占用网络的最长时间
            int maxBusy = 0;
            for (int i = 1; i < patience.length; i++) {
                int pi = patience[i];
                int di = dist[i];

                // 如果数据服务器的patience大于等于2倍距离，该服务器占用网络的时间就是 2*di
                // 如果数据服务器的patience小于2倍距离，该服务器占用网络的时间就是最后一个请求的返回时间

                // 最后一个请求发出的时间，在第一个数据返回的过程中，每隔pi秒都会有数据重发，
                // (2 * di - 1)/pi 表示一共重发的次数，再乘以pi即是最后一次重发的时间
                int lastReq = (2 * di - 1)/pi * pi;
                // 最后一个请求收到的时间
                int recLastRep = lastReq + 2 * di;
                maxBusy = Math.max(maxBusy, recLastRep);
            }

            return maxBusy + 1;
        }

        // BFS计算各数据服务器到主服务器的距离
        private void calcDist(int[] dist, Map<Integer, List<Integer>> conn) {
            boolean[] visited = new boolean[dist.length];
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(0); // master node

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int server = queue.poll();
                    List<Integer> neighbors = conn.get(server);
                    for (Integer neighbor : neighbors) {
                        dist[neighbor] = Math.min(dist[server] + 1, dist[neighbor]);
                        if (!visited[neighbor]) queue.offer(neighbor);
                    }
                    visited[server] = true;
                }
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
   