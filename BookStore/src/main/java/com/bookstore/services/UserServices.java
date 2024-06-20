package com.bookstore.services;

import com.bookstore.entity.User;
import com.bookstore.repository.IRoleRepository;
import com.bookstore.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    public void save(User user) {
        userRepository.save(user);

        // Get userId by username
        Long userId = userRepository.getUserIdByUsername(user.getUsername());

        // Get roleId by name ("USER" role assumed here)
        Long roleId = roleRepository.getRoleIdByName("USER");

        // Check if roleId and userId are valid (not 0)
        if (roleId != null && roleId != 0 && userId != null && userId != 0) {
            // Add role to user
            userRepository.addRoleToUser(userId, roleId);
        } else {
            // Handle case where roleId or userId is not found
            throw new RuntimeException("Could not add role to user. Role or user not found.");
        }
    }

}
