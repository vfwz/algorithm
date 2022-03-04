package cn.vfwz.leetcode;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;

/*
转盘锁， leetcode 752
 */
public class WheelLock {

    public static void main(String[] args) {
//        String[] deadEnds = {"0201", "0101", "0102", "1212", "2002"};
//        String target = "0202";

        String[] deadEnds = {"8887", "8889", "8878", "8898", "8788", "8988", "7888", "0888"};
        String target = "8888";

        System.out.println(solution(deadEnds, target));
//        System.out.println(addOne("0000", 0));
//        System.out.println(addOne("0000", 1));
//        System.out.println(addOne("0000", 2));
//        System.out.println(addOne("0000", 3));
//        System.out.println(addOne("9999", 0));
//        System.out.println(addOne("9999", 3));

    }

    private static int solution(String[] deadEnds, String target) {
        List<String> deadEndsList = Arrays.asList(deadEnds);
        Queue<String> queue = new LinkedList<>();
        String start = "0000";
        List<String> visted = new ArrayList<>();
        int ret = 0;

        if (start.equals(target)) {
            return 0;
        }
        queue.offer(start);
        visted.add(start);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                String cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    for (int j = -1; j < 2; j+=2) {
                        String s = add(cur, j, i);
                        if(visted.contains(s)) {
                           continue;
                        }
                        if (!deadEndsList.contains(s)) {
                            System.out.println(s);
                            if (s.equals(target)) {
                                return ret + 1;
                            }
                            queue.offer(s);
                            visted.add(s);
                        } else {
                            break;
                        }
                    }
                }
            }
            ret++;
        }

        return -1;
    }

    private static String add(String src, int num, int position) {
        char c = src.charAt(position);
        int cInt = Character.getNumericValue(c);
        String c2 = Integer.toString((cInt + num + 10) % 10);

        return src.substring(0, position) + c2 + src.substring(position + 1);
    }


}
