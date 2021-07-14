package com.github.adityagarde.rest.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static Integer idCounter = 4;

    static {
        users.add(new User(1, "Aditya1", new Date()));
        users.add(new User(2, "Aditya2", new Date()));
        users.add(new User(3, "Aditya3", new Date()));
        users.add(new User(4, "Aditya4", new Date()));
    }

    public List<User> fetchAll() {
        return users;
    }

    public User save(User user) {
        user.setId(++idCounter);
        users.add(user);
        return user;
    }

    public User findById(Integer id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }

        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            User thisUser = iterator.next();
            if (thisUser.getId() == id) {
                iterator.remove();
                return thisUser;
            }
        }

        return null;
    }
}
