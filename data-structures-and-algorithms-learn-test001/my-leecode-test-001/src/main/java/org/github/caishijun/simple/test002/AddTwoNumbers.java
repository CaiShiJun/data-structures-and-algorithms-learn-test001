package org.github.caishijun.simple.test002;

/*
2. 两数相加

给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807

来源网址：https://leetcode-cn.com/problems/add-two-numbers/
 */

import java.util.LinkedList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class AddTwoNumbers {

    // start 自己实现的答案
    public static ListNode addTwoNumbersMyAnswer(ListNode l1, ListNode l2) {
        int num1 = listNodeToIntMyAnswer(l1);
        int num2 = listNodeToIntMyAnswer(l2);

        return intToListNodeMyAnswer(num1 + num2,true);
    }

    // 读取 ListNode 链表处理返回成数字
    public static int listNodeToIntMyAnswer(ListNode listNode) {
        int temp = 0;
        LinkedList<Integer> linkedList = new LinkedList<>();
        while (listNode != null) {
            linkedList.add(listNode.val);
            listNode = listNode.next;
        }
        for (int i = 0; linkedList.size() != 0; i++) {
            temp += linkedList.removeLast() * Math.pow(10, i);
        }
        return temp;
    }

    /**
     * 将 int 类型数字处理返回为链表
     * @param residueNum    被处理 int 型整数
     * @param isReverse     是否反转
     * @return
     */
    public static ListNode intToListNodeMyAnswer(int residueNum,boolean isReverse) {
        if(isReverse == true){
            residueNum = reverseNum(residueNum);
        }
        int putNum = 0;
        ListNode result = null;
        for (int i = 0; residueNum != 0; i++) {
            putNum = residueNum % 10;
            residueNum = residueNum / 10;
            if (result == null) {
                result = new ListNode(putNum);
            } else {
                result = new ListNode(putNum, result);
            }
        }
        return result;
    }

    //整数反转倒序输出 1234567 ----> 7654321
    public static int reverseNum(int num) {
        int temp = 0;       //temp作为存储的值
        while (num != 0) {
            temp = temp * 10 + num % 10;
            num /= 10;
        }
        return temp;
    }
    // end 自己实现的答案


    //官方题解
    public static ListNode addTwoNumbersOfficialAnswer_001(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3, null)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4, null)));

        long startNanoTime = System.nanoTime();
        //ListNode result = addTwoNumbersMyAnswer(l1, l2);        // endNanoTime - startNanoTime:242166
        ListNode result = addTwoNumbersOfficialAnswer_001(l1, l2);      //endNanoTime - startNanoTime:8877
        long endNanoTime = System.nanoTime();

        System.out.println("endNanoTime - startNanoTime:" + (endNanoTime - startNanoTime));
        System.out.println("result:" + result);
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    ListNode(int x, ListNode next) {
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