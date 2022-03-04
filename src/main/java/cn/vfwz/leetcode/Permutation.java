package cn.vfwz.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Permutation {

    public static void main(String[] args) {
        Integer[] nums = {1, 2, 3, 4};

        List<Integer> selected = new ArrayList(nums.length);
        List<Integer> unselect = new ArrayList(nums.length);
        Collections.addAll(unselect, nums);

        permutation(selected, unselect);
    }

    public static void permutation(List<Integer> selected, List<Integer> unselect) {
        if(unselect.size() == 0) {
            System.out.println(Arrays.toString(selected.toArray()));
            return;
        }
        ArrayList<Integer> copy = new ArrayList<>(unselect);
        for (Integer num : copy) {
            unselect.remove(num);
            selected.add(num);
            permutation(selected, unselect);
            selected.remove(num);
            unselect.add(num);
        }
    }
}
