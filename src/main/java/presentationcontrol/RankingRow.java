package presentationcontrol;

public class RankingRow {
    private String name;
    private String time;

    public RankingRow(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }
}
