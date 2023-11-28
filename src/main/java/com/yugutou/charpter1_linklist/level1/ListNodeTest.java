package com.yugutou.charpter1_linklist.level1;

/**
 * @author Steven
 * @create 2023-11-26-22:43
 */
public class ListNodeTest {

    public int val;
    public ListNode next;

    public ListNodeTest(int x) {
        val = x;
        next = null;
    }

    public static void main(String[] args) {
        ListNodeTest node = new ListNodeTest(1);
    }

}
