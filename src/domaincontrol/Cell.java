package domaincontrol;

public class Cell {
    private int id;
    private boolean accessible;
    private int number;

    public Cell(int id, boolean accessible, int number){
        this.id = id;
        this.accessible = accessible;
        this.number = number;
    }

    public int getId(){
        return id;
    }

    public boolean getAccessible(){
        return accessible;
    }

    public int getNumber(){
        return number;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setAccessible(boolean accessible){
        this.accessible = accessible;
    }

    public void setNumber(int number){
        this.number = number;
    }
}
