package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class UserUtil {

    public static final List<User> user = Arrays.asList(
            new User (1, "5name", "email5", "5password", 1500, true, new HashSet<>(Arrays.asList(Role.ADMIN))),
            new User (1, "4name", "email4", "4password", 1400, false, new HashSet<>(Arrays.asList(Role.USER))),
            new User (1, "3name", "email3", "3password", 1300, true, new HashSet<>(Arrays.asList(Role.ADMIN, Role.USER))),
            new User (1, "2name", "email2", "2password", 1200, false, new HashSet<>(Arrays.asList(Role.USER))),
            new User (1, "1name", "email1", "1password", 1100, true, new HashSet<>(Arrays.asList(Role.ADMIN)))
    );



}
