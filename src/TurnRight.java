public class TurnRight extends Action{

    public TurnRight(){}

    public LawnState execute(LawnMowerAgent a, LawnState s) {
        int dir;
        int newDir;
        LawnState state = null;

        if (s instanceof LawnState)
            state = s;
        else
            System.out
                    .println("ERROR - Argument to TurnRight.execute() is not of type LawnState");

        dir = state.getAgentDir();

        newDir = dir + 1;
        if (newDir > 3)
            newDir = 0;
        state.setAgentDir(newDir);

        return state;
    }
}