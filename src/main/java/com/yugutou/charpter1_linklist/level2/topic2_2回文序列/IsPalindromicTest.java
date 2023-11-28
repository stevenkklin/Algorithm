package com.yugutou.charpter1_linklist.level2.topic2_2回文序列;

/**
 * @author Steven
 * @create 2023-11-28-21:50
 */
public class IsPalindromicTest {
    static ListNode temp;
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 4, 3, 2, 1};
        ListNode node = initLinkedList(a);
//        while (node != null) {
//            System.out.print(node.val + "->");
//            node = node.next;
//        }
        int testMethod = 1;
        boolean result = false;
        switch (testMethod) {
            case 1://方法1：通过双指针的方式来判断
                result = isPalindromeByTwoPoints(node);
                break;
        }
    }

    /**
     * 双指针判断回文链表
     * @param node 链表头节点
     * @return 是否是回文链表
     */
    public static boolean isPalindromeByTwoPoints(ListNode node) {
        // 判断是否是空链表或者单个节点
        if (node == null || node.next == null) {
            return true;
        }

        return true;
    }

    public static ListNode initLinkedList(int[] a) {
        ListNode head = null;
        ListNode cur = null;
        for (int i = 0; i < a.length; i++) {
            if (i == 0) {
                head = new ListNode(a[i]);
                cur = head;
            } else {
                cur.next = new ListNode(a[i]);
                cur = cur.next;
            }
        }
        return head;
    }

    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}


