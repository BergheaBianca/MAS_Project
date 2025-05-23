public class TurnRight extends Action{

    public TurnRight(){}

    public LawnState execute(LawnMowerAgent a, LawnState s) {
        int dir, newDir;
        Position pos = s.getPosition(a);
        dir = pos.dir;

        newDir = dir + 1;
        if (newDir > 3)
            newDir = 0;
        s.updatePostion(a, new Position(pos.x, pos.y, newDir));

        return s;
    }

    public String toString() {
        return "TURN RIGHT";
    }
}