import java.util.LinkedList;
import java.util.Scanner;

class PhoneBookEntry {
    // Initiators
    private String name;
    private String phoneNumber;
    private String email;

    // Constructor
    public PhoneBookEntry(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }
}

public class PhoneBook {
    private LinkedList<PhoneBookEntry> phoneBookEntries;

    public PhoneBook() {
        phoneBookEntries = new LinkedList<>();
    }

    public void addEntry(PhoneBookEntry entry) {
        phoneBookEntries.addFirst(entry);
    }

    public void deleteEntry(PhoneBookEntry entry) {
        phoneBookEntries.remove(entry);
    }

    // Finders
    public PhoneBookEntry findEntryByName(String name) {
        for (PhoneBookEntry entry : phoneBookEntries) {
            if (entry.getName().equals(name)) {
                return entry;
            }
        }
        return null;
    }

    public PhoneBookEntry findEntryByPhoneNumber(String phoneNumber) {
        for (PhoneBookEntry entry : phoneBookEntries) {
            if (entry.getPhoneNumber().equals(phoneNumber)) {
                return entry;
            }
        }
        return null;
    }

    public PhoneBookEntry findEntryByEmail(String email) {
        for (PhoneBookEntry entry : phoneBookEntries) {
            if (entry.getEmail().equals(email)) {
                return entry;
            }
        }
        return null;
    }

    // Print List Method
    public void printList() {
        if (phoneBookEntries.isEmpty()) {
            System.out.println("Your phone book is empty \uD83D\uDE41");
        } else {
            System.out.println();
            System.out.println();
            System.out.println("                                               Phone Book Entries:");
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            for (PhoneBookEntry entry : phoneBookEntries) {
                System.out.printf("%-40s %-40s %-20s\n", "Name: " + entry.getName(), "Phone Number: " + entry.getPhoneNumber(), "Email: " + entry.getEmail());
            }
        }
    }

    // Search Methods
    public void searchNameOrNumber(String searchTerm) {
        PhoneBookEntry result = findEntryByName(searchTerm);
        if (result != null) {
            System.out.println();
            System.out.printf("%-40s %-40s %-20s\n", "Name: " + result.getName(), "Phone Number: " + result.getPhoneNumber(), "Email: " + result.getEmail());
        } else {
            result = findEntryByPhoneNumber(searchTerm);
            if (result != null) {
                System.out.println();
                System.out.printf("%-40s %-40s %-20s\n", "Name: " + result.getName(), "Phone Number: " + result.getPhoneNumber(), "Email: " + result.getEmail());
            } else {
                System.out.println("\u274C Entry not found.");
            }
        }
    }

    public void searchEmail(String searchE) {
        PhoneBookEntry res = findEntryByEmail(searchE);
        if (res != null) {
            System.out.println();
            System.out.printf("%-40s %-40s %-20s\n", "Name: " + res.getName(), "Phone Number: " + res.getPhoneNumber(), "Email: " + res.getEmail());
        } else {
            System.out.println("\u274C Entry not found.");
        }
    }

    // Main Code
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("************************************");
            System.out.println("(A)dd");
            System.out.println("(D)elete");
            System.out.println("(E)mail Search");
            System.out.println("(P)rint List");
            System.out.println("(S)earch Number/Name");
            System.out.println("(Q)uit");
            System.out.println("************************************");
            System.out.print("Enter Command: ");
            String command = scanner.nextLine().toUpperCase();

            switch (command) {
                case "A": // uses input validation
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    if (name.length() < 1 || name.length() > 35) {
                        System.out.println("\u274C Please enter a name between 1 and 35 characters.");
                        break;
                    }
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    if (!phoneNumber.matches("\\d{9,12}")) {
                        System.out.println("\u274C Phone number must be between 9 and 12 digits.");
                        break;
                    }
                    System.out.print("Enter email address: ");
                    String email = scanner.nextLine();
                    if (!email.contains("@")) {
                        System.out.println("\u274C Please enter a valid email address.");
                        break;
                    }
                    PhoneBookEntry entry = new PhoneBookEntry(name, phoneNumber, email);
                    phoneBook.addEntry(entry);
                    System.out.println("\u2705 Entry added!");
                    break;
                case "D":
                    System.out.print("Enter name or phone number to delete: ");
                    String deleteTerm = scanner.nextLine();
                    PhoneBookEntry deleteEntry = phoneBook.findEntryByName(deleteTerm);
                    if (deleteEntry == null) {
                        deleteEntry = phoneBook.findEntryByPhoneNumber(deleteTerm);
                    }
                    if (deleteEntry != null) {
                        phoneBook.deleteEntry(deleteEntry);
                        System.out.println("\u2705 Entry deleted!");
                    } else {
                        System.out.println("\u274C Entry not found.");
                    }
                    break;
                case "E":
                    System.out.print("Enter email to search: ");
                    String searchE = scanner.nextLine();
                    phoneBook.searchEmail(searchE);
                    break;
                case "P":
                    phoneBook.printList();
                    break;
                case "S":
                    System.out.print("Enter name or phone number to search: ");
                    String searchTerm = scanner.nextLine();
                    phoneBook.searchNameOrNumber(searchTerm);
                    break;
                case "Q":
                    System.out.println("\u2705 Exiting phone book...");
                    System.exit(0);
                default:
                    System.out.println("\u274C Invalid command.");
            }
        }
    }
}

