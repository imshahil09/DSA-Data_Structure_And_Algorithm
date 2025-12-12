// Online Java Compiler
// Implementation of Doubly Linked List (DLL) with various operations
// Each method has its Time Complexity (TC) and Space Complexity (SC) explained

/*
Operations:
1. makeDLLFromArray(int[] arr) → Create DLL from array      | TC: O(n), SC: O(1)
2. insertAtFirst(Node head, int x) → Insert at beginning    | TC: O(1), SC: O(1)
3. insertAtEnd(Node head, int x) → Insert at end            | TC: O(n), SC: O(1)
4. insertAtPos(Node head, int x, int pos) → Insert at pos   | TC: O(n), SC: O(1)
5. deleteAtFirst(Node head) → Delete from beginning         | TC: O(1), SC: O(1)
6. deleteAtEnd(Node head) → Delete from end                 | TC: O(n), SC: O(1)
7. deleteAtPos(Node head, int pos) → Delete at pos          | TC: O(n), SC: O(1)
8. getLength(Node head) → Length of DLL                     | TC: O(n), SC: O(1)
9. prinDLL(Node head) → Print DLL                           | TC: O(n), SC: O(1)
10. reverseDLL(Node head) → Reverse DLL in-place            | TC: O(n), SC: O(1)
*/

class Main {
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5};
        Node head = makeDLLFromArray(arr);

        System.out.print("Original DLL: ");
        prinDLL(head);

        head = reverseDLL(head); // Reverse DLL
        System.out.print("\nReversed DLL: ");
        prinDLL(head);
    }

    // Create DLL from array
    // TC: O(n), SC: O(1)
    public static Node makeDLLFromArray(int[] arr){
        if(arr.length == 0){
            return new Node(-1);
        }
        Node head = new Node(arr[0]);
        Node temp = head;
        for(int i = 1; i < arr.length; i++){
            Node newNode = new Node(arr[i]);
            temp.next = newNode;
            newNode.prev = temp;
            temp = temp.next;
        }
        return head;
    }

    // Insert at the beginning of DLL
    // TC: O(1), SC: O(1)
    public static Node insertAtFirst(Node head,int x){
        if(head == null){
            return new Node(x);
        }
        Node newNode  = new Node(x);
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
        return head;
    }

    // Insert at the end of DLL
    // TC: O(n), SC: O(1)
    public static Node insertAtEnd(Node head,int x){
        if(head == null){
            return new Node(x);
        }
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        Node newNode = new Node(x);
        temp.next = newNode;
        newNode.prev = temp;
        return head;
    }

    // Insert at a given position (1-based index)
    // TC: O(n), SC: O(1)
    public static Node insertAtPos(Node head,int x,int pos){
        if(pos == 1){
            return insertAtFirst(head,x);
        }
        int len = getLength(head);
        if(len+1 == pos){
            return insertAtEnd(head,x);
        }
        Node temp = head;
        Node prev = null;
        while(pos != 1){
            prev = temp;
            temp = temp.next;
            pos--;
        }
        Node newNode = new Node(x);
        prev.next = newNode;
        newNode.prev = prev;
        newNode.next = temp;
        temp.prev = newNode;
        return head;
    }

    // Delete from beginning of DLL
    // TC: O(1), SC: O(1)
    public static Node deleteAtFirst(Node head){
        if(head == null){
            return null;
        }
        Node temp = head;
        head = head.next;
        if(head != null){
            temp.next = null;
            head.prev = null;
            return head;
        }
        return null;
    }

    // Delete from end of DLL
    // TC: O(n), SC: O(1)
    public static Node deleteAtEnd(Node head){
        if(head == null){
            return null;
        }
        if(head.next == null){
            return null;
        }
        Node temp = head;
        Node prev = null;
        while(temp.next != null){
            prev = temp;
            temp = temp.next;
        }
        prev.next = null;
        temp.prev = null;
        return head;
    }

    // Delete from given position (1-based index)
    // TC: O(n), SC: O(1)
    public static Node deleteAtPos(Node head,int pos){
        if(head == null){
            return null;
        }
        if(pos == 1){
            return deleteAtFirst(head);
        }
        int len = getLength(head);
        if(pos == len){
            return deleteAtEnd(head);
        }
        Node temp = head;
        Node prev = null;
        while(pos != 1){
            prev = temp;
            temp = temp.next;
            pos--;
        }
        prev.next = temp.next;
        temp.next.prev = prev;
        temp.next = null;
        temp.prev = null;
        return head;
    }

    // Get length of DLL
    // TC: O(n), SC: O(1)
    public static int getLength(Node head){
        if(head == null){
            return 0;
        }
        Node temp = head;
        int len = 0;
        while(temp != null){
            temp = temp.next;
            len++;
        }
        return len;
    }

    // Print DLL
    // TC: O(n), SC: O(1)
    public static void prinDLL(Node head){
        if(head == null){
            System.out.print("DLL is Empty");
            return;
        }
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + "<->");
            temp = temp.next;
        }
    }

    // Reverse DLL in-place
    // TC: O(n), SC: O(1)
    public static Node reverseDLL(Node head){
        if(head == null || head.next == null){
            return head;
        }
        Node current = head;
        Node prev = null;
        while(current != null){
            // Swap next and prev for each node
            prev = current.prev;
            current.prev = current.next;
            current.next = prev;
            current = current.prev; // move to next node (previously current.next)
        }
        // At the end, prev will be pointing to new head
        return prev.prev;
    }
}

// Node class for DLL
class Node{
    int data;
    Node next;
    Node prev;

    public Node(int  data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
