package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class UserUtil {

    public static final List<User> user = Arrays.asList(
            new User("user1", "email1", "password1", Role.ADMIN),
            new User("user3", "email3", "password3", Role.USER),
            new User("user2", "email2", "password2", Role.ADMIN)

    );

    public static List<User> getAll() {
        user.sort(Comparator.comparing(User::getName));
        return user;
    }
}
