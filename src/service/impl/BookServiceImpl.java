package service.impl;

import dto.BookWithAuthor;
import exception.InvalidInputException;
import exception.ResourceNotFoundException;
import model.Author;
import model.BookBase;
import repository.interfaces.AuthorRepositoryInterface;
import repository.interfaces.BookRepositoryInterface;
import service.interfaces.BookServiceInterface;

import java.util.List;

public class BookServiceImpl implements BookServiceInterface {
    @Override
    public List<BookWithAuthor> getAllBooksWithAuthors() {
        return bookRepo.findAllWithAuthors();
    }


    private final BookRepositoryInterface bookRepo;
    private final AuthorRepositoryInterface authorRepo;

    public BookServiceImpl(BookRepositoryInterface bookRepo,
                           AuthorRepositoryInterface authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    @Override
    public void addBook(BookBase book, String authorName) {

        if (book == null) {
            throw new InvalidInputException("Book is null");
        }
        if (authorName == null || authorName.isBlank()) {
            throw new InvalidInputException("Author name is empty");
        }

        int authorId = authorRepo.findIdByName(authorName)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Author not found")
                );

        book.setAuthor(new Author(authorId, authorName));
        bookRepo.create(book);
    }

    @Override
    public List<BookBase> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public BookWithAuthor getBookById(int id) {
        return bookRepo.findByIdWithAuthor(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found")
                );
    }

    @Override
    public void deleteBook(int id) {
        bookRepo.delete(id);
    }
}
