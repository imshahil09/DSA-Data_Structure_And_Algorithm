class Main {
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5};
        Node head = makeLinkedList(arr);
        printLinkedList(insertAtFirst(head,70)); // Example run
    }

    // ✅ Convert array to linked list
    // TC: O(n), SC: O(1)
    public static Node makeLinkedList(int[] arr){
        if (arr == null || arr.length == 0) return null; // edge case: empty array
        Node head = new Node(arr[0]);
        Node temp = head;
        for(int i=1;i<arr.length;i++){
            temp.next = new Node(arr[i]);
            temp = temp.next;
        }
        return head;
    }

    // ✅ Insert at the beginning
    // TC: O(1), SC: O(1)
    public static Node insertAtFirst(Node head,int val){
        Node temp = new Node(val);
        temp.next = head; // works even if head is null
        return temp;
    }

    // ✅ Insert at the end
    // TC: O(n), SC: O(1)
    public Node insertAtEnd(Node head, int x) {
        if(head == null){ 
            return new Node(x); // edge case: empty list
        }
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = new Node(x);
        return head;
    }

    // ✅ Insert at a given position (1-indexed)
    // TC: O(n), SC: O(1)
    public Node insertPos(Node head, int pos, int val) {
        if(pos <= 0){ // invalid position
            System.out.println("Invalid position!");
            return head;
        }

        if(pos == 1){
            return insertAtFirst(head,val);
        }

        int len = getLength(head);
        if(pos > len+1){ // invalid position
            System.out.println("Position out of range!");
            return head;
        }

        if(len+1 == pos){
            return insertAtEnd(head,val);
        }

        Node temp = head;
        while(pos-2 != 0){
            temp = temp.next;
            pos--;
        }
        Node newNode = new Node(val);
        newNode.next = temp.next;
        temp.next = newNode;
        return head;
    }

    // ✅ Search for a key
    // TC: O(n), SC: O(1)
    public boolean searchKey(Node head, int key) {
        if(head == null) return false; // edge case: empty list
        Node temp = head;
        while(temp != null){
            if(temp.data == key){
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // ✅ Remove last node
    // TC: O(n), SC: O(1)
    public Node removeLastNode(Node head) {
        if(head == null) return null; // empty list
        if(head.next == null) return null; // single node → remove = empty list
        Node temp = head;
        Node prev = null;
        while(temp.next != null){
            prev = temp;
            temp = temp.next;
        }
        prev.next = null;
        return head;
    }

    // ✅ Delete node at position x (1-indexed)
    // TC: O(n), SC: O(1)
    Node deleteNode(Node head, int x) {
        if(head == null) return null; // empty list
        if(x <= 0){ 
            System.out.println("Invalid position!");
            return head;
        }
        if(x == 1){ // delete head
            return head.next;
        }

        int len = getLength(head);
        if(x > len){ // out of bounds
            System.out.println("Position out of range!");
            return head;
        }

        Node temp = head;
        while(x-2 > 0){
            temp = temp.next;
            x--;
        }
        temp.next = temp.next.next; // safely skips node
        return head;
    }

    // ✅ Print linked list
    // TC: O(n), SC: O(1)
    public static void printLinkedList(Node head){
        if(head == null){
            System.out.println("List is empty!");
            return;
        }
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data+"->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // ✅ Get length of linked list
    // TC: O(n), SC: O(1)
    public static int getLength(Node head){
        int len = 0;
        Node temp = head;
        while(temp != null){
            temp = temp.next;
            len++;
        }
        return len;
    }

    // ✅ Get Nth node from start (1-indexed)
    // TC: O(n), SC: O(1)
    public int GetNthFromStart(Node head, int index) {
        if(head == null){
            return -1;
        }
        int pos = 1;
        Node temp = head;
        while(pos != index && temp != null){
            temp = temp.next;
            pos++;
        }
        if(pos == index){
            return temp.data;
        }
        return -1;
    }

    // ✅ Get Kth node from last
    // TC: O(n), SC: O(1)
    int getKthFromLast(Node head, int k) {
        if(head == null){
            return -1;
        }
        int len = getLength(head);
        if(k > len){
            return -1;
        }
        int indexFromStart = len-k+1;
        Node temp = head;
        while(indexFromStart != 1){
            temp = temp.next;
            indexFromStart--;
        }
        return temp.data;
    }

    // ✅ Delete every Kth node
    // TC: O(n), SC: O(1)
    Node deleteK(Node head, int k) {
        if(head == null){
            return null;
        }
        Node temp = head;
        Node prev = null;
        while(temp != null && temp.next != null){
            int pos = 1;
            while(pos != k && temp != null){
                prev = temp;
                temp = temp.next;
                pos++;
            }
            if(temp == null){
                break;
            }
            prev.next = temp.next;
            temp = temp.next;
        }
        return head;
    }
}

class Node {
    int data;
    Node next;
    public Node(int data,Node next){
        this.data = data;
        this.next = next;
    }
    public Node(int data){
        this.data = data;
        this.next = null;
    }
}
