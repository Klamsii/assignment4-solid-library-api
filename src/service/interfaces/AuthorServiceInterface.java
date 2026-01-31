package service.interfaces;

import model.Author;

public interface AuthorServiceInterface {

    void createAuthor(String name);

    Author getAuthorById(int id);

    int getOrCreateAuthorId(String name);

    Author findFirstAuthorWithMultipleBooks();
}
