package hw3;

public class LinkedList implements List {
        /* sentinel node, keeps track of start of linkedlist */
    private ListNode head;
	/* The current number of elements in the list */
    private int size;
    

    /* Part 1:
     * create an empty list
     */
	public LinkedList() {
            size = 0;
            // this ListNode will never be used to store data, JUST to reference start of LinkedList
            head = new ListNode(-1);
	}
        
    /* DO NOT CHANGE THIS METHOD
     * This method returns the size variable, which 
     * should be updated in other methods.  
     */
        @Override
        public int size(){
            return this.size;
        }

    /* Part 4:
     * remove ith element from the list and return it
     *
     * assume that the ith element exists
     *
     * Example
     * before: [ 100, 200, 300 ]
     * remove(1)
     * after:  [ 100, 300 ]
     * returns 200
     */
        @Override
	public Object remove(int i) {
            Object old = get(i);
            ListNode cur = head;
            for (int x = -1; x < i-1; x++) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
            size--;
            return old;
	}

    /* Part 1:
     * add d to the end of the list
     *
     * Example
     * before: [ 100, 200 ]
     * add(300)
     * after:  [ 100, 200, 300 ]
     */
        @Override
	public void add(Object d) {
            ListNode cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = new ListNode(d);
            size++;
	}

    /* Part 2:
     * return the ith element
     *
     * assume that the ith element exists
     *
     * Example
     * before: [ 100, 200, 300 ]
     * get(2)
     * after:  [ 100, 200, 300 ]
     * returns 300
     */
        @Override
	public Object get(int i) {
             // still use 0 indexing
            ListNode cur = head;
            for (int x = -1; x<i; x++) {
                cur = cur.next;
            }
            return cur.data;
	}
        
    /* Part 3:
     * replace the ith element with d and return it
     *   
     * assume that the ith element exists 
     * 
     * Example
     * before: [ 100, 200, 300 ]
     * replace(1, 400)
     * after:  [ 100, 400, 300 ]
     * returns 200   
     */
        @Override
        public Object replace(int i, Object d){
            ListNode n = new ListNode(d);
            ListNode cur = head;
            Object old = get(i);
            for (int x = -1; x<i-1; x++) {
                cur = cur.next;
            }
            ListNode ahead = cur.next.next;
            cur.next = n;
            n.next = ahead;
            return old;
        }
        
    /* Part 5:
     * move the ith element so that it is now the jth element 
     *
     * assume that the ith and jth elements exist
     *
     * Example
     * before: [ 100, 200, 300 ]
     * move(1, 0)
     * after:  [ 200, 100, 300 ]
     *
     * Example
     * before: [ 100, 200, 300, 400 ]
     * move(1,3)
     * after: [ 100, 300, 400, 200 ]
     */
        @Override
	public void move(int i, int j) {
            ListNode curi = head; ListNode curi_1 = head;
            ListNode curj = head; ListNode curj_1 = head;
            for (int x = -1; x < i; x++) {
                curi = curi.next;
                if (x < i-1) curi_1 = curi_1.next;
            }
            for (int x = -1; x < j; x++) {
                curj = curj.next;
                if (x < j-1) curj_1 = curj_1.next;
            }
            ListNode curi_plus1 = curi.next;
            if (i > j) {
                curj_1.next = curi;
                curi_1.next = curi.next;
                curi.next = curj;
            } else {
                curi.next = curj.next;
                curj.next = curi;
                curi_1.next = curi_plus1;
            }
            
	}
	
    /* Part 1:
     * Returns an array containing the elements of the list in order
     * Do not copy the Object data; just copy the Object references.
     * 
     * Example
     * before: [ 100, 200, 300 ]
     * toArray()
     * after: [ 100, 200, 300 ]
     * returns { 100, 200, 300 } 
     */
        @Override
	public Object[] toArray() {
		int sizeA = 0;
		/* get the actual size of the list */
		Object[] r = new Object[size];
		/* copy the Objects in the list in order into the array r */
                ListNode cur = head;
                while (cur.next != null) {
                    r[sizeA] = cur.next.data;
                    sizeA++;
                    cur = cur.next;
                }
		
		return r;
	}

    // you do not need to modify the ListNode class
    private class ListNode {
		private Object data;
		private ListNode next;

		public ListNode(Object data) {
			this.data = data;
			this.next = null;
		}
		
		public Object getData() {
				return data;
		}

		public void setData(Object data) {
				this.data = data;
		}
		
		public void setNext(ListNode next) {
				this.next = next;
		}
		
		public ListNode getNext() {
				return next;
		}
	}

}
