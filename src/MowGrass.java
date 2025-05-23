public class MowGrass extends Action{

    public MowGrass(){}
    public LawnState execute(LawnMowerAgent a, LawnState s) {

        Position pos;

        pos = s.getPosition(a);

        s.removeGrass(pos.x, pos.y);
        return s;
    }

    public String toString() {
        return "MOW GRASS";
    }
}