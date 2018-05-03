package domaincontrol;

public class Player {
    private String id;

    public Player(String username) {
        id = username;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
