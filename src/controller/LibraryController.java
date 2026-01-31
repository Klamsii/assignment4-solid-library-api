package controller;

import dto.BookWithAuthor;
import model.BookBase;
import service.interfaces.BookServiceInterface;

public class LibraryController {

    private final BookServiceInterface service;

    public LibraryController(BookServiceInterface service) {
        this.service = service;
    }

    public void addBook(BookBase book, String authorName) {
        service.addBook(book, authorName);
    }

    public void showAllBooks() {
        service.getAllBooks()
                .forEach(b -> System.out.println(b.getDisplayInfo()));
    }
    public void showAllBooksTable() {

        System.out.printf(
                "%-5s | %-25s | %-10s | %-20s%n",
                "ID", "BOOK NAME", "FORMAT", "AUTHOR"
        );
        System.out.println("--------------------------------------------------------------");

        service.getAllBooksWithAuthors().forEach(b ->
                System.out.printf(
                        "%-5d | %-25s | %-10s | %-20s%n",
                        b.getBookId(),
                        b.getBookName(),
                        b.getFormat(),
                        b.getAuthorName()
                )
        );
    }


    public void showBookById(int id) {
        System.out.println(service.getBookById(id));
    }

    public void showPrintedBooks() {
        service.getAllBooksWithAuthors().stream()
                .filter(b -> "PRINTED".equalsIgnoreCase(b.getFormat()))
                .forEach(System.out::println);
    }


    public void showEBooks() {
        service.getAllBooksWithAuthors().stream()
                .filter(b -> "EBOOK".equalsIgnoreCase(b.getFormat()))
                .forEach(System.out::println);
    }
    public void showBookCountByAuthor() {

        service.getAllBooksWithAuthors().stream()
                .collect(
                        java.util.stream.Collectors.groupingBy(
                                BookWithAuthor::getAuthorName,
                                java.util.stream.Collectors.counting()
                        )
                )
                .forEach((author, count) ->
                        System.out.println(author + " → " + count + " books")
                );
    }
    public void sortBooksByAuthor() {
        service.getAllBooksWithAuthors().stream()
                .sorted(
                        java.util.Comparator.comparing(BookWithAuthor::getAuthorName)
                )
                .forEach(System.out::println);
    }
    public void sortBooksByName() {
        service.getAllBooksWithAuthors().stream()
                .sorted(
                        java.util.Comparator.comparing(BookWithAuthor::getBookName)
                )
                .forEach(System.out::println);
    }



    public void deleteBook(int id) {
        service.deleteBook(id);
    }
}
