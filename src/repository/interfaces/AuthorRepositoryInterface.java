package repository.interfaces;

import model.Author;

import java.util.Optional;

public interface AuthorRepositoryInterface extends CrudRepository<Author> {

    Optional<Integer> findIdByName(String name);

    Optional<Author> findFirstAuthorWithMultipleBooks();
}
