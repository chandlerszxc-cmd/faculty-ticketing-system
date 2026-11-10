public class Ticket {
    public int id;
    public char[] faculty;
    public char[] category;
    public char[] details;

    // Store one record. Time O(1), auxiliary space O(1).
    public Ticket(int id, char[] faculty, char[] category, char[] details) {
        this.id = id;
        this.faculty = faculty;
        this.category = category;
        this.details = details;
    }

    // Print record fields. Time O(L), auxiliary space O(1); L is text length.
    public void display() {
        System.out.print("Ticket #"); System.out.print(id);
        System.out.print(" | Faculty: "); System.out.print(faculty);
        System.out.print(" | Category: "); System.out.print(category);
        System.out.print(" | Details: "); System.out.println(details);
    }
}
