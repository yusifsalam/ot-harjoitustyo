package game.dao;

import game.domain.User;

public class UserService {

    public void create(String name) {
        User user = new User(name);
    }
}
