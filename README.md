# cs2114-project1-group46
A console-based budget calculator written in Java that tracks monthly income, fixed expenses, and logged transactions to show remaining flexible funds and spending breakdowns.

---

## Requirements

* **Java JDK:** Java 11 or Java 17
* **IDE:** Eclipse IDE for Java Developers
* **Libraries:** CS2-Support library

---

## Setup & Running in Eclipse

**Import the project:**
   * Open Eclipse -> **File** -> **Import...**
   * Select **Existing Projects into Workspace** and hit **Next**.
   * Browse to the project folder and click **Finish**.

**Add CS2-Support to the Build Path:**
   * Right-click the project folder -> **Build Path** -> **Configure Build Path...**
   * Select the **Projects** tab, click **Add...**, and select `CS2-Support`.
   * Click **Apply and Close**.

**Run the console app:**
   * Open `src/CS2114Project1Group46/Main.java`.
   * Right-click the editor -> **Run As** -> **Java Application**.
   * Enter your salary, fixed costs, and expenses directly into the Eclipse Console.

**Run unit tests:**
   * Right-click the `CS2114Project1Group46` package in the Package Explorer.
   * Select **Run As** -> **JUnit Test**.

---

## Running via Terminal

To compile and run directly from the command line without Eclipse:

```bash
# Compile all Java files into a bin directory
javac -d bin src/CS2114Project1Group46/*.java

# Run Main
java -cp bin CS2114Project1Group46.Main
