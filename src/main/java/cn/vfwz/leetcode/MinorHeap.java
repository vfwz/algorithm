package cn.vfwz.leetcode;

import java.util.Arrays;

public class MinorHeap {

    public static void main(String[] args) {

        int[] heap = {9, 3, 7, 6, 5, 1, 10, 2, 0};

        int lastRootPos = (heap.length - 1) / 2 % 2 == 0 ? (heap.length - 1) / 2 - 1 : (heap.length - 1);
        transfer(heap, lastRootPos);

        System.out.println(Arrays.toString(heap));

    }

    private static void transfer(int[] heap, int pos) {
        if (pos < 0) {
            return;
        }
        int leftIndex = 2 * pos + 1;
        int rightIndex = leftIndex + 1;
        int rootVal = heap[pos];
        int leftVal = leftIndex > heap.length - 1 ? Integer.MAX_VALUE : heap[leftIndex];
        int rightVal = rightIndex > heap.length - 1 ? Integer.MAX_VALUE : heap[rightIndex];
        if (rootVal > rightVal) {
            swap(heap, pos, rightIndex);
            rootVal = rightVal;
        }
        if (rootVal > leftVal) {
            swap(heap, pos, leftIndex);
        }
        transfer(heap, pos - 1);

    }

    private static void swap(int[] heap, int pos, int index) {
        int tmp = heap[pos];
        heap[pos] = heap[index];
        heap[index] = tmp;
    }

}
