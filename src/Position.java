public class Position{

    protected int x;

    protected int y;

    protected int dir;


    public Position(int x, int y, int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    boolean equals(Position p){
        return this.x == p.x && this.y == p.y;
    }

    public String toString(){
        return "Position (" + this.x + ", " + this.y + ") facing " + Direction.toString(this.dir);
    }
}
