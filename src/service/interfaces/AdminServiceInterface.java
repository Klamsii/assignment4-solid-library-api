package service.interfaces;

import java.util.List;
import model.User;

public interface AdminServiceInterface {

    List<User> getAllUsers();

    void deleteUser(String username);

    void promoteToAdmin(String username);

    void demoteToUser(String username);
}

