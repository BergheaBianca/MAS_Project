import java.util.List;

public class LawnEnvironment {
    private LawnState state;
    public LawnEnvironment() {}

    LawnState currentState(){
        return this.state;
    }

    void setInitialState(LawnState s){
        this.state = s;
    }

    LawnPercept getPercept(LawnMowerAgent a){
        LawnPercept p;

        p = new LawnPercept(this.state, a);
        System.out.println("Percept: " + p.toString());
        return p;

    }
    void updateState(LawnMowerAgent a, Action act){

        this.state = act.execute(a, this.state);
    }
}
