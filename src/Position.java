public class Position{

    protected int x;

    protected int y;


    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    boolean equals(Position p){
        return this.x == p.x && this.y == p.y;
    }

    public String toString(){
        return "Position (" + this.x + ", " + this.y + ")";
    }
}
