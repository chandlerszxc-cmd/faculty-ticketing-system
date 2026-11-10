public class CircularQueue {
    private Ticket[] items;
    private int frontIndex = 0;
    private int size = 0;

    // Allocate fixed storage. Time O(capacity), space O(capacity).
    public CircularQueue(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("Capacity must be positive.");
        items = new Ticket[capacity];
    }

    // Check empty state. Time O(1), auxiliary space O(1).
    public boolean isEmpty() { return size == 0; }

    // Check full state. Time O(1), auxiliary space O(1).
    public boolean isFull() { return size == items.length; }

    // Count pending records. Time O(1), auxiliary space O(1).
    public int count() { return size; }

    // Search active slots only. Time O(n), auxiliary space O(1).
    public Ticket search(int id) {
        for (int i = 0; i < size; i++) {
            Ticket ticket = items[(frontIndex + i) % items.length];
            if (ticket.id == id) return ticket;
        }
        return null;
    }

    // Validate and insert at rear. Time O(n) including duplicate search; space O(1).
    public boolean enqueue(Ticket ticket) {
        if (ticket == null || ticket.id < 1 || isFull() || search(ticket.id) != null) return false;
        items[(frontIndex + size) % items.length] = ticket;
        size++;
        return true;
    }

    // Remove the oldest ticket. Time O(1), auxiliary space O(1).
    public Ticket dequeue() {
        if (isEmpty()) return null;
        Ticket result = items[frontIndex];
        items[frontIndex] = null;
        frontIndex = (frontIndex + 1) % items.length;
        size--;
        return result;
    }

    // Inspect oldest ticket. Time O(1), auxiliary space O(1).
    public Ticket front() { return isEmpty() ? null : items[frontIndex]; }

    // Inspect newest ticket. Time O(1), auxiliary space O(1).
    public Ticket rear() { return isEmpty() ? null : items[(frontIndex + size - 1) % items.length]; }

    // Show logical order and physical indexes. Time O(n * L), space O(1).
    public void display() {
        if (isEmpty()) { System.out.println("Queue is empty. No pending tickets."); return; }
        System.out.println("FRONT");
        for (int i = 0; i < size; i++) {
            int index = (frontIndex + i) % items.length;
            System.out.print("[Slot "); System.out.print(index); System.out.print("] ");
            items[index].display();
            if (i < size - 1) System.out.println("    |");
            if (i < size - 1) System.out.println("    v");
        }
        System.out.println("REAR (next slot wraps around the fixed array)");
    }
}
