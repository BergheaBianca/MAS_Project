import java.util.List;
import java.util.Scanner;
public class LawnSimulation{

    private LawnEnvironment env;

    public LawnSimulation(LawnEnvironment env){
        this.env = env;
    }

    void start(LawnState initState){
        this.env.setInitialState(initState);
        this.env.currentState().display();

        while (!isComplete()) {

        }
        System.out.println("END of simulation");
    }

    boolean isComplete(){

        return this.env.currentState().getGrassTiles() == 0;

    }

    public static void main(String[] args) {

        System.out.println("The Grass Mowing World Agent Test");
        System.out.println("-----------------------------------");
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of agents: ");
        int nrAgents = scanner.nextInt();

        LawnEnvironment env = new LawnEnvironment();
        LawnState initState = LawnState.getInitState(nrAgents);
        LawnSimulation sim = new LawnSimulation(env);

        sim.start(initState);
    }
}