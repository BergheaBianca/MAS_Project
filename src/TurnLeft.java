public class TurnLeft extends Action{
    @Override
    public LawnState execute(LawnMowerAgent a, LawnState s) {
        int dir, newDir;
        Position pos = s.getPosition(a);
        dir = pos.dir;

        newDir = dir - 1;
        if (newDir < 0)
            newDir = 3;
        s.updatePostion(a, new Position(pos.x, pos.y, newDir));

        return s;
    }

    @Override
    public String toString() {
        return "TURN LEFT";
    }
}
