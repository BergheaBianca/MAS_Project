import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class LawnSimulation{

    private LawnEnvironment env;

    public LawnSimulation(LawnEnvironment env){
        this.env = env;
    }

    void start(LawnState initState){
        this.env.setInitialState(initState);
        List<Thread> threads = new ArrayList<>();

        // Start threads for all agents
        for (LawnMowerAgent agent : initState.getAgents()) {
            agent.setEnvironment(env); // If not passed in constructor
            Thread t = new Thread(agent);
            threads.add(t);
            t.start();
        }

        while (!isComplete()) {
            env.currentState().display();

            try {
                Thread.sleep(500); // display every 500ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Stop all agents
        for (LawnMowerAgent agent : initState.getAgents()) {
            agent.stop();
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