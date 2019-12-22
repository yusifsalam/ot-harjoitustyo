package game.dao;

import game.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository ur;

    public User create(String name) {
        User user = new User(name);
        User savedUser = ur.save(user);
        return savedUser;
    }

    public User updateName(String oldName, String newName) {
        User user = ur.findByUsername(oldName);
        user.setUsername(newName);
        return ur.save(user);
    }

    public User addGame(Long id, int score, boolean gameWon) {
        User user = ur.getOne(id);
        if (gameWon) {
            user.setGamesWon(user.getGamesWon() + 1);
        }
        if (score > user.getHighscore()) {
            user.setHighscore(score);
        }
        List<Integer> userHistory = user.getHistory();
        userHistory.add(score);
        user.setHistory(userHistory);
        return ur.save(user);
    }

    public int getSize() {
        return ur.findAll().size();
    }
}
