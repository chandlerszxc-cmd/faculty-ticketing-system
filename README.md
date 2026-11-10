# Faculty Helpdesk Ticket Queue

A Java console application using a manually implemented, fixed-capacity circular queue.

## Members

- Avriel Jane Pariñas
- Chandler Noguera
- Jirah May Dulatre Inot
- Josh Belmonte
- Kelly Gumabay
- Kurt Robrigado
- Nicole Valois Braganaza
- Princess Nicolae Accad
- Sean Cedric Rapiz

## Purpose and scope

Manage pending faculty support requests in first-in, first-out order. Each ticket contains a numeric ID, faculty name, category, and details. Serving a ticket removes it from the pending queue. Records exist only during the session; restarting restores the five sample records. Capacity is 10 tickets.

The provisional group label in the menu is Faculty Helpdesk Team. Replace it with your assigned group name if required. The section and group number have not yet been supplied.

## Manual implementation

No imports, external packages, Scanner, collections, Arrays helpers, parsing helpers, or String manipulation methods are used. Ticket storage uses a plain Ticket array with manually maintained front and size values. Input is read byte by byte; digits are converted manually. Java's automatically available java.lang types and System.in/System.out are still necessary for Java execution and console input/output. Plain arrays are the storage underlying our own queue, not a built-in queue implementation.

Interactive text accepts printable ASCII only, with lengths of 60 for faculty names, 30 for categories, and 120 for details. Accented member names are preserved in this README. Ticket IDs must be 1 through 999999 and unique among pending tickets. A served ID may be reused.

## Compile and run

Requires a JDK (Java 8 or later) installed on the classroom computer. No additional libraries are required. From this project folder:

```text
javac -d out src/Ticket.java src/CircularQueue.java src/Main.java
java -cp out Main
```

## Menu

| Option | Operation | Worst-case time, excluding input/output |
|---|---|---|
| 1 | Add ticket / enqueue with duplicate checking | O(n) |
| 2 | Serve next / dequeue | O(1) |
| 3 | Search ticket ID | O(n) |
| 4 | Display queue with FRONT, REAR, slot indexes and links | O(n) for bounded fields |
| 5 | View front | O(1) |
| 6 | View rear | O(1) |
| 7 | Count tickets | O(1) |
| 8 | Check empty | O(1) |
| 9 | Check full | O(1) |
| 0 | Exit | O(1) |

Here n is the number of pending tickets, C is capacity, and L is ticket text length. Queue operations use O(1) auxiliary space. Queue storage is O(C + nL), or O(C) for bounded fields. Reading a field uses O(L) time and space. Printing a ticket costs O(L); displaying all tickets costs O(nL). The insertion itself is O(1), but duplicate validation makes the complete enqueue O(n). Failed operations can finish sooner.

## Why circular queue?

FIFO serves older requests first. Circular indexing reuses freed array slots without shifting remaining tickets. A stack would serve the newest request first. A linked queue also supports FIFO but requires node links and does not naturally demonstrate a fixed full-capacity condition. Our array queue makes both full and empty cases easy to demonstrate.

Logical example: FRONT [101] -> [102] -> [103] REAR.
Physical insertion slot: (frontIndex + size) % capacity.
After removal: frontIndex = (frontIndex + 1) % capacity.

## Verification

An assertion-based queue check is included. Run:

```text
javac -d out src/Ticket.java src/CircularQueue.java checks/QueueCheck.java
java -ea -cp out QueueCheck
```

Also demonstrate invalid menu input, duplicate ID 101, missing ID 999, blank text, overlong text, filling all 10 slots, serving until empty, and enqueueing after removals to show wraparound.

Verified on November 10, 2026 using the local JDK 27: compilation passed, the assertion-based queue checks passed, and the console display/exit flow ran successfully. The project uses Java 8-compatible syntax; older JDK versions have not been checked here.

## Documentation references

- [Member assignments and deadlines](docs/documentation-workplan.md)
- [Word documentation draft](docs/documentation-draft.docx)

The draft contains pending sections and missing group details. Use the source files to write the report and capture screenshots from your own program run.

To download all files from GitHub, choose **Code > Download ZIP**, extract it, then open the project folder in VS Code. Read `Main.java` for the console menu, `CircularQueue.java` for queue operations, and `Ticket.java` for the record fields.

## Submission checklist

Source and README are supplied. The guidelines also require a 5-8 page PDF report with a problem description, structure justification and diagram, time/space complexity table, at least six screenshots from your actual running program, and truthful member contributions. Prepare the 10-minute live demonstration of every menu option. These screenshots, contribution assignments, and PDF report are not included in this source package.

Before submission, replace the provisional group label as needed, confirm that another group has not selected the same structure/theme, and name the final compressed folder Section_GroupNo_ProjectTitle.
