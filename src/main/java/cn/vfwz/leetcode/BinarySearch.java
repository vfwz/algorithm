package cn.vfwz.leetcode;

import org.junit.Test;

public class BinarySearch {

    @Test
    public void testBinarySearch() {
        int[] source = {0, 1, 2, 3, 4, 5, 6};

        System.out.println(binarySearch(source, -1));
        for (int element : source) {
            System.out.println(binarySearch(source, element));
        }
        System.out.println(binarySearch(source, source[source.length - 1] + 1));

    }

    @Test
    public void testBinarySearch2() {
        int[] source = {0, 1, 2, 3, 4, 5, 6};

        System.out.println(binarySearch2(source, -1));
        for (int element : source) {
            System.out.println(binarySearch2(source, element));
        }
        System.out.println(binarySearch2(source, source[source.length - 1] + 1));

    }

    @Test
    public void testBinarySearchLeftBound() {
        int[] source = {0, 0, 0, 1, 1, 1, 3, 3, 3};

        System.out.println(binarySearchLeftBound(source, -1));
        for (int element : source) {
            System.out.println(binarySearchLeftBound(source, element));
        }
        System.out.println(binarySearchLeftBound(source, source[source.length - 1] + 1));
        System.out.println(binarySearchLeftBound(source, 2));

    }

    @Test
    public void testBinarySearchRightBound() {
        int[] source = {0, 0, 0, 1, 1, 1, 3, 3, 3};

        System.out.println(binarySearchRightBound(source, -1));
        for (int element : source) {
            System.out.println(binarySearchRightBound(source, element));
        }
        System.out.println(binarySearchRightBound(source, source[source.length - 1] + 1));
        System.out.println(binarySearchRightBound(source, 2));



    }

    /** 左闭右开的二分查找
     * right的初始值是arr.length
     * 由于是左闭右开，left = right 就是终止条件，因此while条件为 left < right，相等时需要跳出
     * 当 source[mid] < target 时， target在数组右半区，由于source[mid]已经检查过且左区间闭合，left就跳过mid，因此left = mid + 1
     * 当 source[mid] > target 时，target在左半区，由于右区间开放，因此取 right = mid，这样mid也不会包含在下次数组里
     */
    public static int binarySearch(int[] source, int target) {
        int left = 0, right = source.length;
        int i = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            printIndent(i++, "left:" + left + ", right:" + right + ", mid:" + mid + ", source[mid]:" + source[mid] + "  target:" + target);
            if (source[mid] == target) {
                return mid;
            } else if (source[mid] < target) {
                left = mid + 1;
            } else if (source[mid] > target) {
                right = mid;
            }
        }

        return -1;
    }

    /** 左闭右闭的二分查找
     * right的初始值是 arr.length-1
     * 由于是左闭右闭，left > right 就是终止条件，因此while条件为 left <= right，相等时数组中唯一的元素还需要检查
     * 当 source[mid] < target 时， target在数组右半区，由于source[mid]已经检查过且左区间闭合，left就跳过mid，因此left = mid + 1
     * 当 source[mid] > target 时，target在左半区，由于右区间闭合，因此取 right = mid - 1
     */
    public static int binarySearch2(int[] source, int target) {
        int left = 0, right = source.length - 1;
        int i = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            printIndent(i++, "left:" + left + ", right:" + right + ", mid:" + mid + ", source[mid]:" + source[mid] + "  target:" + target);
            if (source[mid] == target) {
                return mid;
            } else if (source[mid] < target) {
                left = mid + 1;
            } else if (source[mid] > target) {
                right = mid - 1;
            }
        }

        return -1;
    }

    /** 最左匹配的二分查找
     */
    public static int binarySearchLeftBound(int[] source, int target) {
        int left = 0, right = source.length;
        int i = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            printIndent(i++, "left:" + left + ", right:" + right + ", mid:" + mid + ", source[mid]:" + source[mid] + "  target:" + target);
            if (source[mid] == target) {
                right = mid;
            } else if (source[mid] < target) {
                left = mid + 1;
            } else if (source[mid] > target) {
                right = mid;
            }
        }

        if(left == source.length) {
            return -1;
        }

        return source[left] == target ? left: -1;
    }

    /** 最右匹配的二分查找
     */
    public static int binarySearchRightBound(int[] source, int target) {
        int left = 0, right = source.length;
        int i = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            printIndent(i++, "left:" + left + ", right:" + right + ", mid:" + mid + ", source[mid]:" + source[mid] + "  target:" + target);
            if (source[mid] == target) {
                left = mid + 1;
            } else if (source[mid] < target) {
                left = mid + 1;
            } else if (source[mid] > target) {
                right = mid;
            }
        }
        if(right == 0) {
            return -1;
        }

        return source[right-1]==target?right-1:-1;
    }


    public static void printIndent(int i, String msg) {
        for (int j = 0; j < i; j++) {
//            System.out.print("\t");
        }
        System.out.println(msg);
    }


}
