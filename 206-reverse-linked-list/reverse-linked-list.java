/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode newhead=null;
        ListNode temp=head;

        while(temp!=null){
            ListNode newNode=new ListNode(temp.val);

            if(newhead==null){
                newhead=newNode;
            }

            else{
                newNode.next=newhead;
                newhead=newNode;
            }
            temp=temp.next;
        }
        return newhead;
    }
}