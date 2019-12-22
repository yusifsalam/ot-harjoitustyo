package game;

import game.dao.UserRepository;
import game.dao.UserService;
import game.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureDataJpa
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private int num;

    @Before
    public void setup() {
        Random r = new Random(100000000);
        num = r.nextInt();
        user = userService.create("Pekka"+num);
    }

    @Test
    public void userCreationWorks() {
        User inRepo = userRepository.findById(user.getId()).get();
        assertEquals(user.getUsername(), inRepo.getUsername());
    }

    @Test
    public void canUpdateUsername() {
        userService.updateName(user.getId(), "dingding");
        User inRepo = userRepository.findByUsername("dingding");
        assertNotNull(inRepo);
    }

    @Test
    public void canAddGame() {
        user = userService.addGame(user.getId(), 2000, true);
        assertEquals(1, user.getGamesWon());
    }

    @Test
    public void highScoresWork() {
        user = userService.addGame(user.getId(), 2000, true);
        User toka = userService.create("Jukka"+num);
        toka = userService.addGame(toka.getId(), 10000, true);
        List<User> scores = userService.getFiveBest();
        assertEquals(10000, scores.get(0).getHighscore());
        assertEquals(2000, scores.get(1).getHighscore());
    }


}
