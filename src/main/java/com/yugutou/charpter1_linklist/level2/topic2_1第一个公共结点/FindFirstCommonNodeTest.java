package com.yugutou.charpter1_linklist.level2.topic2_1第一个公共结点;


import java.util.*;

/**
 * @author Steven
 * @create 2023-11-28-1:02
 */
public class FindFirstCommonNodeTest {


    public static void main(String[] args) {
        ListNode[] headers = initList();

        // la 为 1 2 3 4 5
        // lb 为 11 22 4 5
        ListNode la = headers[0];
        ListNode lb = headers[1];
        while (la != null) {
            System.out.print(la.val + " ");
            la = la.next;
        }
        System.out.println();
        while (lb != null) {
            System.out.print(lb.val + " ");
            lb = lb.next;
        }
        la = headers[0];
        lb = headers[1];

        int testMethod  = 5;
        ListNode node = new ListNode(0);
        switch (testMethod) {
            case 1:
                node = findFirstCommonNodeByMap(la, lb);
                break;
            case 2:
                node = findFirstCommonNodeBySet(la, lb);
                break;
            case 3:
                node = findFirstCommonNodeByStack(la, lb);
                break;
            case 4:
                node = findFirstCommonNodeByCombine(la, lb);
                break;
            case 5:
                node = findFirstCommonNodeBySub(la, lb);
                break;
        }
        System.out.println();
        System.out.println("commonNode:" + node.val);

    }

    /**
     * 通过差值比较来寻找第一个公共节点
     * @param la 第一个链表的头节点
     * @param lb 第二个链表的头节点
     * @return 两个链表的第一个公共节点
     */
    private static ListNode findFirstCommonNodeBySub(ListNode la, ListNode lb) {
        // 判空如果有空链表代表没有公共节点，返回null即可
        if (la == null || lb == null) {
            return null;
        }
        // 获得两个链表的长度
        int l1 = 0;
        int l2 = 0;
        ListNode first = la;
        ListNode second = lb;
        // 获得第一个链表la的长度
        while (first != null) {
            ++l1;
            first = first.next;
        }
        // 获得第二个链表lb的长度
        while (second != null) {
            ++l2;
            second = second.next;
        }
        // 重置两个指针
        first = la;
        second = lb;
        // 定义差值
        int sub = l1 - l2 > 0 ? l1 - l2 : l2 - l1;
        // 判断哪个链表更长，长的那个链表先遍历差值长度
        if (l1 > l2) {
            while (sub > 0) {
                first = first.next;
                sub --;
            }
        }
        if (l1 < l2) {
            while (sub > 0) {
                second = second.next;
                sub --;
            }
        }
        // 然后两个链表同时遍历，找到第一个相同的节点即为第一个公共节点
        while (first != second) {
            first = first.next;
            second = second.next;
        }
        return first;
    }


    /**
     * 通过序列拼接找到第一个公共节点
     * @param la 第一个链表的头节点
     * @param lb 第二个链表的头节点
     * @return 第一个公共节点
     */
    private static ListNode findFirstCommonNodeByCombine(ListNode la, ListNode lb) {
        // 判空 如果有一个链表为空则说明 没有公共节点 返回null
        if (la == null || lb == null) {
            return null;
        }
        // 拼接两个链表，如果有公共节点的化，通过从前往后遍历的第一个相同节点就是第一个公共节点
        // such as : 1234 abc1234
        // 拼接后： 1234abc1234
        //         abc12341234
        // 可以发现从前往后遍历的第一个相同的节点就是两个链表的公共节点

        // 从前往后遍历两个链表并比较
        ListNode first = la;
        ListNode second = lb;
        while (first != second) {
            first = first.next;
            second = second.next;
            if (first != second) {
                if (first == null) {
                    first = lb;
                }
                if (second == null) {
                    second = la;
                }
            }
        }

        return first;
    }

    /**
     * 利用栈查找两个链表的第一个公共节点
     * @param la 第一个链表的头节点
     * @param lb 第二个链表的头节点
     * @return 第一个公共节点
     */
    private static ListNode findFirstCommonNodeByStack(ListNode la, ListNode lb) {
        // 判空 如果有任意一个链表为空,则代表没有公共节点,返回null
        if (la == null || lb == null) {
            return null;
        }

        // 将两个链表全部压入栈，同时进行弹栈。第一个不相等的前一个就是两个链表的第一个公共节点
        // 初始化两个栈
        Stack<ListNode> firstStack = new Stack<>();
        Stack<ListNode> secondStack = new Stack<>();
        // 第一个链表压栈
        while (la != null) {
            firstStack.push(la);
            la = la.next;
        }
        // 第二个链表压栈
        while (lb != null) {
            secondStack.push(lb);
            lb = lb.next;
        }
        ListNode preFirst = null;
        // 同时pop查看第一个不相等的元素
        while (firstStack.peek() != null || secondStack.peek() != null)  {
            ListNode first = firstStack.pop();
            ListNode second = secondStack.pop();
            if (first != second) {
                return preFirst;
            }
            preFirst = first;
        }

        return null;
    }

    /**
     * 通过集合辅助查找
     * @param la 链表1头节点
     * @param lb 链表2头节点
     * @return 第一个公共节点
     */
    private static ListNode findFirstCommonNodeBySet(ListNode la, ListNode lb) {
        // 如果俩个链表有一个为空，说明没有公共节点 直接返回null
        if (la == null || lb == null) {
            return null;
        }
        // 将一个链表存入集合
        Set<ListNode> hash = new HashSet<>();
        while (la != null) {
            hash.add(la);
            la = la.next;
        }
        // 另一个链表与集合中对比查看有无相同节点
        while (lb != null) {
            if (hash.contains(lb)) {
                return lb;
            }
            lb = lb.next;
        }
        return null;
    }

    /**
     * 通过Hash辅助找到第一个公共节点
     * @param la 第一个链表头节点
     * @param lb 第二个链表头节点
     * @return 一个公共节点
     */
    private static ListNode findFirstCommonNodeByMap(ListNode la, ListNode lb) {

        // 有一个链表为空,说明没有公共节点，直接返回null
        if (la == null || lb == null) {
            return null;
        }

        // 将一个链表存入Hash中
        Map<ListNode, Object> hash = new HashMap<>();
        while (la != null) {
            hash.put(la, null);
            la = la.next;
        }

        // 另一个链表遍历并与Hash中比较来尝试找到第一个公共节点
        while (lb != null) {
            if (hash.containsKey(lb)) {
                return lb;
            }
            lb = lb.next;
        }

        return null;
    }


    /**
     * 初始化链表
     * @return 两个链表头节点组成的数组
     */
    private static ListNode[] initList() {
        ListNode[] headers = new ListNode[2];
        // 初始化两个头节点
        headers[0] = new ListNode(1);
        headers[1] = new ListNode(11);
        // 创建两个头节点指针方便返回
        ListNode dummy1 = headers[0];
        ListNode dummy2 = headers[1];
        // 初始化各个需要的节点
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node22 = new ListNode(22);
        ListNode node6 = new ListNode(6);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        // 1 -> 2 -> 3
        headers[0].next = node2;
        headers[0] = headers[0].next;
        headers[0].next = node3;
        headers[0] = headers[0].next;
        // 11 -> 22
        headers[1].next = node22;
        headers[1] = headers[1].next;
        // 设置公共尾链
        // 1 -> 2 -> 3 -> 4 -> 5
        // 11 -> 22 -> 4 -> 5
        headers[0].next = node6;
        headers[1].next = node6;
        headers[0] = headers[0].next;
        headers[1] = headers[1].next;
        headers[0].next = node4;
        headers[1].next = node4;
        headers[0] = headers[0].next;
        headers[1] = headers[1].next;
        headers[0].next = node5;
        headers[1].next = node5;
        headers[0] = headers[0].next;
        headers[1] = headers[1].next;
        // 返回构造两条链表的头节点数组
        return new ListNode[]{dummy1, dummy2};
    }

    /**
     * 定义链表节点结构
     */
    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
