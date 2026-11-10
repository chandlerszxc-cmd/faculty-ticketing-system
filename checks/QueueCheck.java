public class QueueCheck {
    // Check empty/full, duplicates, FIFO and wraparound. Fixed test time/space O(1).
    public static void main(String[] args) {
        CircularQueue q = new CircularQueue(3);
        assert q.isEmpty() && q.dequeue() == null && q.front() == null && q.rear() == null;
        Ticket a = new Ticket(1, new char[]{'A'}, new char[]{'I'}, new char[]{'X'});
        Ticket b = new Ticket(2, new char[]{'B'}, new char[]{'I'}, new char[]{'Y'});
        Ticket c = new Ticket(3, new char[]{'C'}, new char[]{'I'}, new char[]{'Z'});
        assert q.enqueue(a);
        assert !q.enqueue(a);
        assert q.enqueue(b) && q.enqueue(c) && q.isFull();
        assert !q.enqueue(a);
        assert q.dequeue() == a;
        assert q.enqueue(a);
        assert q.front() == b && q.rear() == a && q.count() == 3;
        assert q.search(3) == c && q.search(99) == null;
        assert q.dequeue() == b && q.dequeue() == c && q.dequeue() == a;
        assert q.isEmpty() && !q.isFull() && q.search(1) == null;
        System.out.println("Queue checks passed.");
    }
}
