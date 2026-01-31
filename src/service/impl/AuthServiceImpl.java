package service.impl;

import exception.InvalidInputException;
import exception.ResourceNotFoundException;
import model.User;
import repository.interfaces.UserRepositoryInterface;
import service.interfaces.AuthServiceInterface;

public class AuthServiceImpl implements AuthServiceInterface {

    private final UserRepositoryInterface userRepo;

    public AuthServiceImpl(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User login(String username, String password) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new InvalidInputException("Wrong password");
        }

        return user;
    }

    @Override
    public void register(String username, String password) {
        if (username.isBlank() || password.isBlank()) {
            throw new InvalidInputException("Empty fields");
        }

        userRepo.create(new User(0, username, password, "USER"));
    }
}
