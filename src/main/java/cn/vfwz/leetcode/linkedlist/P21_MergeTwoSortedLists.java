package cn.vfwz.leetcode.linkedlist;

import cn.vfwz.leetcode.util.ListNode;

/**
 * <a href="https://leetcode-cn.com/problems/merge-two-sorted-lists/">
 * 题目链接
 * </a>
 */
public class P21_MergeTwoSortedLists {


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode newList = new ListNode();
        ListNode current = newList;
        while (list1.next != null || list2.next != null) {
            if (list1.next == null) {
                current.next = list2;
                break;
            }
            if (list2.next == null) {
                current.next = list1;
                break;
            }
            if (list1.val > list2.val) {
                current.next = list2;
                current = current.next;
                list2 = list2.next;
            } else {
                current.next = list1;
                current = current.next;
                list1 = list1.next;
            }
        }
        return newList.next;
    }

    public ListNode mergeTwoListsRecursion(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if(list1.val > list2.val) {
            list2.next = mergeTwoListsRecursion(list1, list2.next);
            return list2;
        } else {
            list1.next = mergeTwoListsRecursion(list1.next, list2);
            return list1;
        }
    }

}
