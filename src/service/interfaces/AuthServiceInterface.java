package service.interfaces;

import model.User;

public interface AuthServiceInterface {

    User login(String username, String password);

    void register(String username, String password);
}
