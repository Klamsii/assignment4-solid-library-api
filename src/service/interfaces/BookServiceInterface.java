package service.interfaces;

import dto.BookWithAuthor;
import model.BookBase;

import java.util.List;

public interface BookServiceInterface {

    void addBook(BookBase book, String authorName);

    List<BookBase> getAllBooks();

    List<BookWithAuthor> getAllBooksWithAuthors();

    BookWithAuthor getBookById(int id);

    void deleteBook(int id);
}
