public class MoveForward extends Action{
    public MoveForward(){}

    public LawnState execute(LawnMowerAgent a, LawnState s) {

        Position pos;
        int x, y, dir, newX, newY;

        pos = s.getPosition(a);

        x = pos.x;
        y = pos.y;
        dir = pos.dir;

        if (dir >= 0 && dir < 4) {
            newX = x + Direction.DELTA_X[dir];
            newY = y + Direction.DELTA_Y[dir];
        } else {
            System.out.println("ERROR - Invalid direction for agent.");
            newX = x;
            newY = y;
        }

        if (s.inBounds(newX, newY)) {
            s.updatePostion(a, new Position(newX, newY, dir));
        }

        return s;
    }

    public String toString() {
        return "MOVE FORWARD";
    }
}
