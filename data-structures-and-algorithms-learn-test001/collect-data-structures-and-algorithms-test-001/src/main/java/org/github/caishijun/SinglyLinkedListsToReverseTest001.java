package org.github.caishijun;

/*
单链表反转（递归和非递归） (Java)

实现思路：把每一个节点的 next 节点设置为该节点的上一个节点。
 */

//链表定义
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
    ListNode(int x,ListNode next) {
        val = x;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
            "val=" + val +
            ", next=" + next +
            '}';
    }
}

public class SinglyLinkedListsToReverseTest001 {

    //非递归实现很简单，只需要遍历一遍链表，在遍历过程中，把遍历的节点一次插入到头部。
    /**
     * @param head  开始反转的节点
     * @return  返回反转链表之后的第一个节点，也就是反转链表之前的最后一个节点。
     */
    public static ListNode nonRecursiveReverseList(ListNode head) {
        ListNode prev = null;   //当前节点的上一个节点。
        while (head != null) {
            ListNode tmp = head.next;       //临时保存当前节点的下一个节点 next ，作为下一次遍历的节点。
            head.next = prev;   //把当前节点的上一个节点设置为下一个节点。
            prev = head;        //把当前节点保存到 prev 变量中，作为下一次遍历节点的上一个节点。
            head = tmp;         //把临时保存当前节点的下一个节点 next ，作为下一次遍历的节点。
        }
        return prev;
    }

    //递归实现：翻转head->为首的链表， 然后head变为尾部节点
    /**
     * @param head  开始反转的节点
     * @return  返回反转链表之后的第一个节点，也就是反转链表之前的最后一个节点。
     */
    public static ListNode recursiveReverseList(ListNode head) {
        if(head==null||head.next ==null){       //如果参数为null或者遍历到了最后一个节点则直接返回该参数。
            return head;
        }
        ListNode prev = recursiveReverseList(head.next);        //该行代码会逐层递归直到倒数第二个节点
        head.next.next = head;      //从倒数第二个节点开始，将当前节点设置到下一个节点的 next 变量中，并将当前节点的 next 变量设置为 null ，逐层向上递归返回最后一个节点。
        head.next = null;
        return prev;
    }

    public static void main(String[] args) {
        //1,2,3,4,5,6,7……
        ListNode firsListNode = new ListNode(1,
                                    new ListNode(2,
                                        new ListNode(3,
                                            new ListNode(4,
                                                new ListNode(5,
                                                    new ListNode(6,
                                                        new ListNode(7,
                                                            new ListNode( 8,
                                                                new ListNode(9,
                                                                    new ListNode(10,
                                                                        new ListNode(11,
                                                                            new ListNode(12,
                                                                                new ListNode(13,null)))))))))))));

        ListNode nonRecursiveReverseListResult = nonRecursiveReverseList(firsListNode);
        System.out.println("nonRecursiveReverseList 返回反转链表之后的第一个节点 : " + nonRecursiveReverseListResult);

        ListNode recursiveReverseListResult = nonRecursiveReverseList(nonRecursiveReverseListResult);
        System.out.println("recursiveReverseList 返回反转链表之后的第一个节点 : " + recursiveReverseListResult);
    }
}