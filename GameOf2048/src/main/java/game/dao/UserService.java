package game.dao;

import game.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository ur;

    public User create(String name) {
        User user = new User(name);
        return ur.save(user);
    }

    public User updateName(String oldName, String newName) {
        User user = ur.findByUsername(oldName);
        user.setUsername(newName);
        return ur.save(user);
    }
}
