package cn.vfwz.leetcode.solution;

import org.junit.Test;

import java.util.Arrays;

public class P34_FirstAndLastInArray {

    public int[] c_nums;

    @Test
    public void solution() {
        int[] nums = {1, 5, 7, 7, 8, 8, 8, 10};
        int target = 8;
        System.out.println(Arrays.toString(searchRange(nums, target)));
    }

    public int[] searchRange(int[] nums, int target) {
        int[] output = new int[2];
        Arrays.fill(output, -1);
        c_nums = nums;

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                output[0] = binarySearchLeftBound(left, mid, target);
                output[1] = binarySearchRightBound(mid, right, target);
                return output;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return output;
    }

    public int binarySearchLeftBound(int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (c_nums[mid] == target) {
                right = mid - 1;
            } else if (c_nums[mid] > target) {
                right = mid - 1;
            } else if (c_nums[mid] < target) {
                left = mid + 1;
            }
        }
        return left;
    }

    public int binarySearchRightBound(int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (c_nums[mid] == target) {
                left = mid + 1;
            } else if (c_nums[mid] > target) {
                right = mid - 1;
            } else if (c_nums[mid] < target) {
                left = mid + 1;
            }
        }
        return right;
    }

}


