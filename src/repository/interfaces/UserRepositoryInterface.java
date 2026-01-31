package repository.interfaces;

import model.User;
import java.util.Optional;
import java.util.List;

public interface UserRepositoryInterface {

    Optional<User> findByUsername(String username);

    void create(User user);

    List<User> findAll();

    void deleteByUsername(String username);

    void updateRole(String username, String role);
}

