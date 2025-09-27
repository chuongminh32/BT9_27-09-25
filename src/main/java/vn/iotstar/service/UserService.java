package vn.iotstar.service;

import vn.iotstar.entity.User;
import vn.iotstar.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepo;
    public UserService(UserRepository userRepo) { this.userRepo = userRepo; }

    public List<User> all() { return userRepo.findAll(); }
    public Optional<User> one(Long id) { return userRepo.findById(id); }
    public User save(User u) { return userRepo.save(u); }
    public boolean delete(Long id) {
        if (!userRepo.existsById(id)) return false;
        userRepo.deleteById(id); return true;
    }
}