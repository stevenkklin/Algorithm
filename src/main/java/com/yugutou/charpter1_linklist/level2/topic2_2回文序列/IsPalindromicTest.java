package com.yugutou.charpter1_linklist.level2.topic2_2回文序列;

import java.util.Stack;

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
//        }d
        int testMethod = 4;
        boolean result = false;
        switch (testMethod) {
            case 1://方法1：通过双指针的方式来判断
                result = isPalindromeByTwoPoints(node);
                break;
            case 2: //方法2：全部压栈
                result = isPalindromeByAllStack(node);
                break;
            case 3: //方法2：全部压栈
                result = isPalindromeByHalfStack(node);
                break;
            case 4: //方法4：通过递归来实现
                result = isPalindromeByRe(node);
                break;
        }
        System.out.println("结果：" + result);
    }

    /**
     * 通过递归来实现
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeByRe(ListNode head) {
        temp = head;
        return check(head);
    }

    public static boolean check(ListNode head) {
        if (head == null) {
            return true;
        }
        boolean res = check(head.next) && (head.val == temp.val);
        temp = temp.next;
        return res;
    }


    /**
     *
     * 全部的数据压栈，一半数据弹栈
     *
     * @param head 链表头节点
     * @return 是否是回文链表
     */
    public static boolean isPalindromeByHalfStack(ListNode head) {

        // 空链表或者一个节点则为回文链表
        if (head == null || head.next == null) {
            return true;
        }

        ListNode cur = head;
        // 链表压栈,并计算长度
        Stack<ListNode> stack = new Stack<>();
        int len = 0;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
            len ++;
        }
        // len长度除以2
        len >>= 1;
        cur = head;
        // 弹栈一半
        while (len > 0) {
            if (cur.val != stack.pop().val) {
                return false;
            }
            cur = cur.next;
            len --;
        }

        return true;
    }

    /**
     * 通过全部结点压栈判断是否是回文链表
     * @param head 链表头节点
     * @return 是否是回文链表
     */
    public static boolean isPalindromeByAllStack(ListNode head) {
        // 空链表或者只有一个节点代表是回文链表
        if (head == null || head.next == null) {
            return true;
        }
        // 将链表全部压栈
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        // 一边遍历链表一边弹栈进行比较
        // 重置当前指针
        cur = head;
        while (cur != null) {
            if (cur.val != stack.pop().val) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    /**
     * 双指针判断回文链表
     * @param head 链表头节点
     * @return 是否是回文链表
     */
    public static boolean isPalindromeByTwoPoints(ListNode head) {
        // 判断是否是空链表或者单个节点，则代表是回文链表表
        if (head == null || head.next == null) {
            return true;
        }
        // 定义两个指针方便链表反转，定义快慢双指针方便找到链表中间元素
        ListNode pre = head, prepre = null;
        ListNode fast = head, slow = head;
        // 遍历链表移动快慢指针，顺便利用pre和prepre指针进行链表反转
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
            pre.next = prepre;
            prepre = pre;
        }

        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            if (slow.val != pre.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
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


