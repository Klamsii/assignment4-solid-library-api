package repository.interfaces;

import dto.BookWithAuthor;
import model.BookBase;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryInterface extends CrudRepository<BookBase> {

    Optional<BookWithAuthor> findByIdWithAuthor(int id);

    List<BookWithAuthor> findAllWithAuthors();
}

