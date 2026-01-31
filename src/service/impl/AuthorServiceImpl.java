package service.impl;

import exception.InvalidInputException;
import exception.ResourceNotFoundException;
import model.Author;
import repository.interfaces.AuthorRepositoryInterface;
import service.interfaces.AuthorServiceInterface;

public class AuthorServiceImpl implements AuthorServiceInterface {

    private final AuthorRepositoryInterface authorRepo;

    public AuthorServiceImpl(AuthorRepositoryInterface authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public void createAuthor(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidInputException("Author name cannot be empty");
        }

        authorRepo.create(new Author(0, name));
    }

    @Override
    public Author getAuthorById(int id) {
        return authorRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Author not found")
                );
    }

    @Override
    public int getOrCreateAuthorId(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidInputException("Author name cannot be empty");
        }

        return authorRepo.findIdByName(name)
                .orElseGet(() -> {
                    authorRepo.create(new Author(0, name));
                    return authorRepo.findIdByName(name)
                            .orElseThrow(() ->
                                    new RuntimeException("Failed to create author")
                            );
                });
    }

    @Override
    public Author findFirstAuthorWithMultipleBooks() {
        return authorRepo.findFirstAuthorWithMultipleBooks()
                .orElse(null);
    }
}
