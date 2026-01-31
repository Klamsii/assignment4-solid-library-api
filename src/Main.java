import controller.LibraryController;
import model.BookBase;
import model.EBook;
import model.PrintedBook;
import model.User;
import repository.impl.AuthorRepositoryJdbc;
import repository.impl.BookRepositoryJdbc;
import repository.impl.UserRepositoryJdbc;
import service.impl.AdminServiceImpl;
import service.impl.AuthServiceImpl;
import service.impl.BookServiceImpl;
import service.interfaces.AdminServiceInterface;
import service.interfaces.AuthServiceInterface;
import service.interfaces.BookServiceInterface;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // === Repositories ===
        var bookRepo = new BookRepositoryJdbc();
        var authorRepo = new AuthorRepositoryJdbc();
        var userRepo = new UserRepositoryJdbc();

        // === Services ===
        BookServiceInterface bookService =
                new BookServiceImpl(bookRepo, authorRepo);

        AuthServiceInterface authService =
                new AuthServiceImpl(userRepo);

        AdminServiceInterface adminService =
                new AdminServiceImpl(userRepo);

        // === Controller ===
        LibraryController controller =
                new LibraryController(bookService);

        User currentUser = null;

        // ================= AUTH MENU =================
        while (currentUser == null) {
            System.out.println("""
                ===== Authentication =====
                1. Login
                2. Registration
                0. Exit
                ==========================
                """);

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Username: ");
                        String username = scanner.nextLine();

                        System.out.print("Password: ");
                        String password = scanner.nextLine();

                        currentUser = authService.login(username, password);
                        System.out.println("Welcome, " + currentUser.getUsername());
                    }

                    case 2 -> {
                        System.out.print("Choose username: ");
                        String username = scanner.nextLine();

                        System.out.print("Choose password: ");
                        String password = scanner.nextLine();

                        authService.register(username, password);
                        System.out.println("Registration successful. Please login.");
                    }

                    case 0 -> {
                        System.out.println("Goodbye!");
                        return;
                    }

                    default -> System.out.println("Invalid option");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // ================= MAIN MENU =================
        if ("ADMIN".equals(currentUser.getRole())) {
            adminMenu(scanner, controller, adminService);
        } else {
            userMenu(scanner, controller);
        }
    }

    // ================= USER MENU =================
    private static void userMenu(Scanner scanner, LibraryController controller) {
        while (true) {
            System.out.println("""
                ===== USER MENU =====
                1. Show all books (table)
                2. Show book by ID
                3. Show only PRINTED books
                4. Show only EBOOK books
                5. Show book count by author
                6. Sort books by author
                7. Sort books by book name
                0. Logout
                =====================
                """);

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> controller.showAllBooksTable();
                case 2 -> {
                    System.out.print("Book ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    controller.showBookById(id);
                }
                case 3 -> controller.showPrintedBooks();
                case 4 -> controller.showEBooks();
                case 5 -> controller.showBookCountByAuthor();
                case 6 -> controller.sortBooksByAuthor();
                case 7 -> controller.sortBooksByName();
                case 0 -> {
                    System.out.println("Logged out.");
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    // ================= ADMIN MENU =================
    private static void adminMenu(Scanner scanner,
                                  LibraryController controller,
                                  AdminServiceInterface adminService) {

        while (true) {
            System.out.println("""
                ===== ADMIN MENU =====
                1. Show all books (table)
                2. Show book by ID
                3. Add book
                4. Remove book
                5. Show only PRINTED books
                6. Show only EBOOK books
                7. Show book count by author
                8. Sort books by author
                9. Sort books by book name
                10. Show all users
                11. Delete user
                12. Promote user to ADMIN
                13. Demote user to USER
                0. Logout
            """);


            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1 -> controller.showAllBooksTable();

                case 2 -> {
                    System.out.print("Book ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    controller.showBookById(id);
                }

                case 3 -> {
                    System.out.print("Book name: ");
                    String bookName = scanner.nextLine();

                    System.out.print("Author name: ");
                    String authorName = scanner.nextLine();

                    System.out.print("Format (1 = EBOOK, 2 = PRINTED): ");
                    int format = scanner.nextInt();
                    scanner.nextLine();

                    BookBase book;
                    if (format == 2) {
                        System.out.print("Pages: ");
                        int pages = scanner.nextInt();
                        scanner.nextLine();
                        book = new PrintedBook(0, bookName, pages);
                    } else {
                        book = new EBook(0, bookName, 1.0);
                    }

                    controller.addBook(book, authorName);
                    System.out.println("Book added.");
                }

                case 4 -> {
                    System.out.print("Book ID to remove: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    controller.deleteBook(id);
                    System.out.println("Book removed.");
                }

                case 5 -> controller.showPrintedBooks();
                case 6 -> controller.showEBooks();
                case 7 -> controller.showBookCountByAuthor();
                case 8 -> controller.sortBooksByAuthor();
                case 9 -> controller.sortBooksByName();

                case 10 -> {
                    adminService.getAllUsers()
                            .forEach(u ->
                                    System.out.println(
                                            u.getUsername() + " | " + u.getRole()
                                    )
                            );
                }

                case 11 -> {
                    System.out.print("Username to delete: ");
                    String username = scanner.nextLine();
                    adminService.deleteUser(username);
                    System.out.println("User deleted.");
                }

                case 12 -> {
                    System.out.print("Username to promote: ");
                    String username = scanner.nextLine();
                    adminService.promoteToAdmin(username);
                    System.out.println("User promoted to ADMIN");
                }

                case 13 -> {
                    System.out.print("Username to demote: ");
                    String username = scanner.nextLine();
                    adminService.demoteToUser(username);
                    System.out.println("User demoted to USER");
                }


                case 0 -> {
                    System.out.println("Logged out.");
                    return;
                }

                default -> System.out.println("Invalid option");
            }
        }
    }
}
