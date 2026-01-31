package service.impl;

import model.User;
import repository.interfaces.UserRepositoryInterface;
import service.interfaces.AdminServiceInterface;

import java.util.List;

public class AdminServiceImpl implements AdminServiceInterface {

    private final UserRepositoryInterface userRepo;

    public AdminServiceImpl(UserRepositoryInterface userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void promoteToAdmin(String username) {
        userRepo.updateRole(username, "ADMIN");
    }

    @Override
    public void demoteToUser(String username) {
        userRepo.updateRole(username, "USER");
    }


    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void deleteUser(String username) {
        userRepo.deleteByUsername(username);
    }
}
