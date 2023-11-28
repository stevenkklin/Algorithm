package com.yugutou.charpter1_linklist.level1;

/**
 * @author Steven
 * @create 2023-11-26-21:17
 */
public class BasicLinkListTest {
    static class Node {
        // 节点的数据
        final int data;
        // 指向下一个节点的指针
        Node next;
        // 构造方法
        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        // 头部添加节点1
        Node head = new Node(1);
        System.out.println("头部添加节点1：" + BasicLinkListTest.toString(head));
        System.out.println("链表长度为：" + getLength(head));

        // 尾部添加节点2
        Node node = new Node(2);
        head = BasicLinkListTest.insertNode(head, node, 2);
        System.out.println("尾部添加节点2：" + BasicLinkListTest.toString(head));
        System.out.println("链表长度为：" + getLength(head));

        // 中间添加节点3
        node = new Node(3);
        head = BasicLinkListTest.insertNode(head, node, 2);
        System.out.println("中间添加节点3：" + BasicLinkListTest.toString(head));

        // 中间添加节点4
        node = new Node(4);
        head = BasicLinkListTest.insertNode(head, node, 2);
        System.out.println("中间添加节点4：" + BasicLinkListTest.toString(head));

        // 删除中间节点2
        head = BasicLinkListTest.deleteNode(head, 2);
        System.out.println("删除中间节点2:" + BasicLinkListTest.toString(head));

        // 删除头部节点1
        head = BasicLinkListTest.deleteNode(head, 1);
        System.out.println("删除头部节点1:" + BasicLinkListTest.toString(head));
    }

    /**
     * 返回链表长度
     * @param head 头节点
     * @return 链表长度
     */
    public static int getLength(Node head) {
        int length = 0;
        Node node = head;
        while (node != null) {
            length ++;
            node = node.next;
        }
        return length;
    }

    /**
     *
     * 链表插入
     *
     * @param head 链表头节点
     * @param insertNode 待插入节点
     * @param position  待插入位置，取值从2开始
     * @return
     */
    public static Node insertNode(Node head, Node insertNode, int position) {
        int size = getLength(head);
        if (position < 1 || position > size + 1) {
            System.out.println("位置参数越界");
            return head;
        }
        // 如果head为空直接当作头节点插入
        if (head == null) {
            head = insertNode;
            return head;
        }


        // 找到插入位置的前一个节点
        Node curNode = head;
        int count = 1;
        while (count < position - 1) {
            curNode = curNode.next;
            count ++;
        }

        // 将新节点链接到链表中
        insertNode.next = curNode.next;
        curNode.next = insertNode;

        // 返回插入节点后链表的头节点
        return head;
    }


    /**
     *
     * 删除节点
     *
     * @param head 链表头节点
     * @param position 删除节点位置，取值从1开始
     * @return 删除后链表的头节点
     */
    public static Node deleteNode(Node head, int position) {
        if (head == null) {
            return null;
        }
        int size = getLength(head);
        if (position < 1 || position > size) {
            System.out.println("输入参数有误");
            return head;
        }

        if (position == 1) {
            head = head.next;
        } else {
            Node curNode = head;
            int count = 1;
            while (count < position - 1) {
                curNode = curNode.next;
                count ++;
            }

            curNode.next = curNode.next.next;
        }
        return head;
    }

    public static String toString(Node head) {
        Node curNode = head;
        StringBuffer sb = new StringBuffer();
        while (curNode != null) {
            sb.append(curNode.data + "\t");
            curNode = curNode.next;
        }
        return sb.toString();
    }
}
