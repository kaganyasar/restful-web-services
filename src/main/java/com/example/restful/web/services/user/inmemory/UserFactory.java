package com.example.restful.web.services.user.inmemory;

import com.example.restful.web.services.user.data.Role;
import com.example.restful.web.services.user.data.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class UserFactory {
    private static int usersCount = 3;

    private static final int leftLimit = 97; // letter 'a'
    private static final int rightLimit = 122; // letter 'z'
    private static final int targetStringLength = 10;
    private static final Random random = new Random();

    public void updateUser(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        if (user.getName() == null) {
            user.setName(generateRandomString());
        }
        if (user.getPassword() == null) {
            user.setPassword(generateRandomString());
        }
        if (user.getRole() == null) {
            user.setRole(Role.PRIVATE);
        }
        if (user.getBirthDate() == null) {
            user.setBirthDate(new Date());
        }
    }

    public String generateRandomString() {
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
