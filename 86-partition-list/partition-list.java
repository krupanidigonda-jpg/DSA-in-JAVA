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
    public ListNode partition(ListNode head, int x) {
        ListNode head1=null;
        ListNode head2=null;
        ListNode temp=head;
        ListNode t1=null;
        ListNode t2=null;

        while(temp!=null){
            ListNode newnode=new ListNode(temp.val);

            if(temp.val<x){
                if(head1==null){
                    head1=newnode;
                    t1=newnode;
                }
                else{
                    t1.next=newnode;
                    t1=newnode;
                }
            }
            else{
                if(head2==null){
                    head2=newnode;
                    t2=newnode;
                }
                else{
                    t2.next=newnode;
                    t2=newnode;
                }
            }
            temp=temp.next;
        }

        if(head1==null){
            return head2;
        }

        t1.next=head2;
        return head1;
    }
}