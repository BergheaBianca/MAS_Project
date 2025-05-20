import LawnMowerAgent.LawnMowerAgent;
import LawnEnvironment.LawnEnvironment;
import LawnState.LawnState;

public class LawnSimulation{

    private List<LawnMowerAgent> agents;

    private LawnEnvironment env;

    public LawnSimulation(List<LawnMowerAgent>agents, LawnEnvironment env){
        this.agents = agents;
        this.env = env;
    }

    void start(LawnState initState){

    }

    boolean isComplete(){

    }
}