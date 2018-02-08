package lab4;

class LinkedQueue implements Queue {
	ListNode first;
    
    public LinkedQueue() {
        // intialize empty queue
        first = null;
    }
    
    @Override
    public void add(Object d) {
        ListNode n = new ListNode(d);
        if (first == null) {
            first = n;
        } else {
            ListNode cur = first;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = n;
        }
    } 
    
    @Override
    public Object pop() {
        ListNode old_first = first;
        first = first.next;
        return old_first.data;
    }
    
    @Override
    public boolean isEmpty() {
        return first == null;
    }
    
    

	// you must use ListNode objects in LinkedQueue
	private class ListNode {
			public Object data;
			public ListNode next;

			public ListNode(Object d) {
					this.data = d;
					this.next = null;
			}
			
	}

}
