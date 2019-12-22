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
        User savedUser = ur.save(user);
        return savedUser;
    }

    public User updateName(String oldName, String newName) {
        User user = ur.findByUsername(newName);
        if (user !=  null) {
            return user;
        }
        user = ur.findByUsername(oldName);
        user.setUsername(newName);
        return ur.save(user);
    }

    public User addGame(Long id, int score, boolean gameWon) {
        User user = ur.findById(id).get();
        if (gameWon) {
            user.setGamesWon(user.getGamesWon() + 1);
        }
        if (score > user.getHighscore()) {
            user.setHighscore(score);
        }
        String userHistory = user.getHistory();
        String gameStatus = gameWon ? "W" : "L";
        userHistory += (gameStatus + score + ";");
        user.setHistory(userHistory);
        return ur.save(user);
    }

    public int getAllSize() {
        return ur.findAll().size();
    }
}
