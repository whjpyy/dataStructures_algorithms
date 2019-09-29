package leetcode;

import java.util.List;

/**
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 * @author carl.z.chen
 * @Date 2019/9/29
 */
public class Solution203 {

    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
        }

        ListNode(int[] arr){
            if (arr == null || arr.length == 0){
                throw new IllegalArgumentException("array is empty.");
            }

            this.val = arr[0];

            ListNode cur = this;
            for (int i = 1; i < arr.length; i++) {
                cur.next = new ListNode(arr[i]);
                cur = cur.next;
            }
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();

            ListNode cur = this;
            while (cur != null){
                res.append(cur.val);
                cur = cur.next;
            }

            res.append("NULL");
            return res.toString();
        }
    }

    public ListNode removeElements(ListNode head, int val) {

        while (head != null && head.val == val){
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        if(head == null){
            return null;
        }

        ListNode prev = head;
        while (prev.next != null){
            if(prev.next.val == val){
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            }else {
                prev = prev.next;
            }
        }
        return head;
    }

    /**
     * 使用虚拟头结点
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements2(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null){
            if(prev.next.val == val){
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            }else {
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6};
        ListNode listNode = new ListNode(arr);
        System.out.println(listNode);

        new Solution203().removeElements2(listNode, 6);
    }
}
