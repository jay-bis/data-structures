package lab4;

class LinkedStack implements Stack {
	// reference to the top of the Stack
	private ListNode top;
    
    public LinkedStack() {
        // initialize empty LinkedStack
        top = null;
    }
    
    @Override
    public void push(Object d) {
        ListNode old_top = top;
        top = new ListNode(d);
        top.next = old_top;
    }
    
    @Override
    public Object pop() {
        Object pop_return = top.data;
        top = top.next;
        return pop_return;
    }
    
    @Override
    public boolean isEmpty() {
        return top == null;
    }
    
    

	// you must use ListNode objects in LinkedStack
	private class ListNode {
			public Object data;
			public ListNode next;

			public ListNode(Object d) {
					this.data = d;
					this.next = null;
			}
	}

}
