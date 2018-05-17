package presentationcontrol;

public class RankingRow {
    private String name;
    private Integer time;

    public RankingRow(String name, Integer time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public Integer getTime() {
        return time;
    }
}
