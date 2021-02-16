package com.example.restful.web.services.user.inmemory;

import com.example.restful.web.services.exception.UserNotFoundException;
import com.example.restful.web.services.user.data.Role;
import com.example.restful.web.services.user.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class UserDaoService {
    private static Map<Integer, User> userMap = new HashMap<>();

    @Autowired
    private UserFactory userFactory;

    @PostConstruct
    void init() {
        userMap.put(1, new User(1, "Adam", userFactory.generateRandomString(), Role.ADMIN, new Date()));
        userMap.put(2, new User(2, "Eve", userFactory.generateRandomString(), Role.MODERATOR, new Date()));
        userMap.put(3, new User(3, "Jack", userFactory.generateRandomString(), Role.SERGEANT, new Date()));
    }

    public List<User> retrieveAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    public User retrieveUser(int id) {
        User user = userMap.get(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("User with id: %d, is not found", id));
        }
        return user;
    }

    public User upsertUser(User user) {
        userFactory.updateUser(user);
        userMap.put(user.getId(), user);
        return user;
    }

    public void deleteUser(int id) {
        User user = userMap.get(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("User with id: %d, is not found", id));
        }
        userMap.remove(id);
    }
}
