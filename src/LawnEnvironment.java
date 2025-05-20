import java.util.List;

public class LawnEnvironment {
    private LawnState state;
    private List<LawnMowerAgent> agents;

    public LawnEnvironment(LawnState state, List<LawnMowerAgent> agents) {
        this.state = state;
        this.agents = agents;
    }

    LawnState currentState(){
        return this.state;
    }

    void setinitialState(LawnState s){
        this.state = s;
    }

    void addAgent(LawnMowerAgent a){
        this.agents.add(a);
    }

    LawnPercept getPercept(LawnMowerAgent a){
        LawnPercept p;

        if(this.state instanceof LawnState){
            p = new LawnPercept(this.state, a);
            System.out.println("Percept: " + p.toString());
            return p;
        }
        else {
            System.out.println("ERROR - state is not a VacuumState object.");
            return null;
        }

    }

    void updateState(LawnMowerAgent a, Action act){
        this.state = act.execute(a, this.state);
    }
}
