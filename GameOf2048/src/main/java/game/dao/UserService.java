package game.dao;

import game.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository ur;

    public User create(String name) {
        User user = new User(name);
        User savedUser = ur.save(user);
        return savedUser;
    }

    public User updateName(Long id, String newName) {
        User user = ur.findByUsername(newName);
        if (user !=  null) {
            return user;
        }
        user = ur.findById(id).get();
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

    public List<User> getFiveBest() {
        List<User> users = ur.findAll();
        users.sort((a, b) -> b.getHighscore() - a.getHighscore());
        return users.stream().limit(5).collect(Collectors.toList());
    }

    public int getAllSize() {
        return ur.findAll().size();
    }
}
