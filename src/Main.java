public class Main {
    private static boolean endOfInput = false;

    // Read a bounded ASCII line manually, draining excess input. Time O(L), space O(limit).
    private static char[] readText(String prompt, int limit) throws Exception {
        while (true) {
            System.out.print(prompt);
            char[] buffer = new char[limit];
            int length = 0;
            boolean invalid = false;
            int value;
            while ((value = System.in.read()) != -1 && value != 10) {
                if (value == 13) continue;
                if (value < 32 || value > 126 || length == limit) invalid = true;
                else buffer[length++] = (char) value;
            }
            if (value == -1) { endOfInput = true; return null; }
            int start = 0;
            while (start < length && buffer[start] == ' ') start++;
            while (length > start && buffer[length - 1] == ' ') length--;
            if (invalid || start == length) {
                System.out.print("Enter nonempty English letters/numbers/punctuation, up to ");
                System.out.print(limit); System.out.println(" characters.");
                continue;
            }
            char[] result = new char[length - start];
            for (int i = 0; i < result.length; i++) result[i] = buffer[start + i];
            return result;
        }
    }

    // Parse digits manually with range/overflow validation. Time O(L), space O(L).
    private static int readNumber(String prompt, int minimum, int maximum) throws Exception {
        while (true) {
            char[] text = readText(prompt, 10);
            if (endOfInput) return -1;
            int number = 0;
            boolean valid = true;
            for (int i = 0; i < text.length; i++) {
                int digit = text[i] - '0';
                if (digit < 0 || digit > 9 || number > (maximum - digit) / 10) {
                    valid = false; break;
                }
                number = number * 10 + digit;
            }
            if (valid && number >= minimum && number <= maximum) return number;
            System.out.print("Invalid input. Enter a whole number from ");
            System.out.print(minimum); System.out.print(" to "); System.out.println(maximum);
        }
    }

    // Load five sample records. Fixed startup work and storage O(1).
    private static void seed(CircularQueue queue) {
        queue.enqueue(new Ticket(101, new char[]{'A','n','a'}, new char[]{'I','T'}, new char[]{'P','C',' ','i','s','s','u','e'}));
        queue.enqueue(new Ticket(102, new char[]{'B','e','n'}, new char[]{'I','T'}, new char[]{'W','i','F','i'}));
        queue.enqueue(new Ticket(103, new char[]{'C','a','r','a'}, new char[]{'R','o','o','m'}, new char[]{'L','i','g','h','t'}));
        queue.enqueue(new Ticket(104, new char[]{'D','a','n'}, new char[]{'I','T'}, new char[]{'P','r','i','n','t','e','r'}));
        queue.enqueue(new Ticket(105, new char[]{'E','v','a'}, new char[]{'R','o','o','m'}, new char[]{'F','a','n'}));
    }

    // Menu loop; complexity depends on selected operation. Queue storage O(C * L).
    public static void main(String[] args) throws Exception {
        CircularQueue queue = new CircularQueue(10);
        seed(queue);
        while (!endOfInput) {
            System.out.println("\n=== Faculty Helpdesk Team | Faculty Ticketing System ===");
            System.out.println("1. Add ticket\n2. Serve next ticket\n3. Search ticket\n4. Display queue");
            System.out.println("5. View front\n6. View rear\n7. Count tickets\n8. Check empty\n9. Check full\n0. Exit");
            int choice = readNumber("Choice: ", 0, 9);
            if (endOfInput || choice == 0) break;
            if (choice == 1) {
                if (queue.isFull()) { System.out.println("Queue is full. Capacity: 10. Time: O(1)"); continue; }
                int id = readNumber("Ticket ID (1-999999): ", 1, 999999);
                if (endOfInput) break;
                if (queue.search(id) != null) { System.out.println("Duplicate ticket ID. Time: O(n)"); continue; }
                char[] faculty = readText("Faculty name: ", 60);
                if (endOfInput) break;
                char[] category = readText("Category: ", 30);
                if (endOfInput) break;
                char[] details = readText("Request details: ", 120);
                if (endOfInput) break;
                queue.enqueue(new Ticket(id, faculty, category, details));
                System.out.println("Ticket added. Time: O(n), including duplicate validation.");
            } else if (choice == 2 || choice == 5 || choice == 6) {
                Ticket ticket = choice == 2 ? queue.dequeue() : (choice == 5 ? queue.front() : queue.rear());
                if (ticket == null) System.out.println("Queue is empty. No ticket available.");
                else { if (choice == 2) System.out.println("Served and removed:"); ticket.display(); }
                System.out.println("Time: O(1) for queue operation; O(L) to print ticket.");
            } else if (choice == 3) {
                int id = readNumber("Ticket ID to search: ", 1, 999999);
                if (endOfInput) break;
                Ticket ticket = queue.search(id);
                if (ticket == null) System.out.println("Ticket ID not found in pending queue.");
                else ticket.display();
                System.out.println("Time: O(n) search; O(L) to print a match.");
            } else if (choice == 4) {
                queue.display(); System.out.println("Time: O(n * L); O(n) with bounded ticket text.");
            } else {
                if (choice == 7) { System.out.print("Pending tickets: "); System.out.println(queue.count()); }
                if (choice == 8) System.out.println(queue.isEmpty() ? "Queue is empty." : "Queue is not empty.");
                if (choice == 9) System.out.println(queue.isFull() ? "Queue is full." : "Queue has available space.");
                System.out.println("Time: O(1)");
            }
        }
        System.out.println("Goodbye. Time: O(1)");
    }
}
