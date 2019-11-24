package game.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private int highscore;
    private List<Integer> history;

    public User(String username) {
        this.username = username;
        this.highscore = 0;
        this.history = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username;
    }
}
