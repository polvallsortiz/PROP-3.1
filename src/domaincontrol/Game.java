package domaincontrol;

public class Game {
    Player player;

    public Game(String username) {
        player = new Player(username);
    }

    public Player getPlayer() {
        return player;
    }
}
