import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LawnState{
    private List<LawnMowerAgent> agents;

    private List<Message> globalMessages = new ArrayList<>();;

    private static int height;

    private static int width;

    private static int grassTiles = 15;

    //map tile types
    private static final int CLEAR = 0;

    private static final int GRASS = 1;

    private static final int OBSTACLE = 2;

    //map
    private static int[][] map;
    public LawnState(){

    }
    static LawnState getInitState(int nrAgents){
        LawnState s;

        s = new LawnState();
        s.width = nrAgents;
        s.height = nrAgents;
        s.map = initMap(s.width, s.height, 0.3);
        s.agents = initAgents(nrAgents, s.map);
        return s;
    }

    static int[][] initMap(int mapWidth, int mapHeight, double obstacleRatio) {
        int[][] initMap = new int[mapWidth][mapHeight];

        Random rand = new Random();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double r = rand.nextDouble();
                if (r < obstacleRatio) {
                    initMap[i][j] = OBSTACLE;
                } else {
                    initMap[i][j] = GRASS;
                    grassTiles++;
                }
            }
        }

        return initMap;
    }

    static List<LawnMowerAgent> initAgents(int nrAgents, int[][] map){
        List<LawnMowerAgent> initialAgents = new ArrayList<>();
        Random rand = new Random();

        int width = map.length;
        int height = map[0].length;

        int attempts = 0;
        int maxAttempts = 1000;
        int id = 0;

        while (id < nrAgents && attempts < maxAttempts) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);

            if (map[x][y] != OBSTACLE && !positionTaken(initialAgents, x, y) && inBounds(x, y)) {
                Position pos = new Position(x, y, 0);
                LawnMowerAgent agent = new LawnMowerAgent(String.valueOf(id), pos, new ArrayList<>());
                initialAgents.add(agent);
                id +=1;
            }

            attempts++;
        }

        if (id < nrAgents-1) {
            throw new RuntimeException("Could not place all agents within the max attempts limit.");
        }

        return initialAgents;
    }

    private static boolean positionTaken(List<LawnMowerAgent> agents, int x, int y) {
        for (LawnMowerAgent a : agents) {
            if (a.position.x == x && a.position.y == y) {
                return true;
            }
        }
        return false;
    }


    public List<LawnMowerAgent> getAgents(){
        return this.agents;
    }

    public void addMessage(Message m) {
        globalMessages.add(m);
    }

    public List<Message> getGlobalMessages() {
        return globalMessages;
    }

    void updatePostion(LawnMowerAgent agent, Position p){
        for(LawnMowerAgent a : this.agents){
            if(agent.equals(a)){
                a.setPosition(p);
            }
        }
    }

    Position getPosition(LawnMowerAgent agent){
        for(LawnMowerAgent a : this.agents){
            if(agent.equals(a)){
               return a.getPosition();
            }
        }
        return null;
    }

    void removeGrass(int x, int y){
        if(map[x][y] == GRASS){
            map[x][y] = CLEAR;
            grassTiles --;
        }
    }

    boolean hasGrass(int x, int y){
        if(map[x][y] == GRASS){
            return true;
        }
        return false;
    }

    boolean isObstacle(int x, int y){
        if(map[x][y] == OBSTACLE){
            return true;
        }
        return false;
    }

    static boolean inBounds(int x, int y) {

        if (x >= 0 && x < width && y >= 0 && y < height) {
            return true;
        }
        return false;
    }

    int getGrassTiles(){

        return grassTiles;
    }

    public void display() {
        System.out.println();

        // Column headers
        for (int i = 0; i < width; i++)
            System.out.print("  " + i);
        System.out.println();

        // Top border
        System.out.print(" ");
        for (int i = 0; i < width; i++)
            System.out.print("+--");
        System.out.println("+");

        for (int j = 0; j < height; j++) {
            System.out.print(j + "|");
            for (int i = 0; i < width; i++) {
                boolean agentHere = false;

                // Check if any agent is at (j, i)
                for (LawnMowerAgent agent : this.agents) {
                    Position pos = agent.getPosition();
                    if (pos.x == i && pos.y == j) {
                        System.out.print("A");
                        agentHere = true;
                        break;
                    }
                }

                if (!agentHere) {
                    if (hasGrass(i, j))
                        System.out.print("*");
                    else if (isObstacle(i, j))
                        System.out.print("#");
                    else
                        System.out.print(" ");
                }

                System.out.print(" |");
            }

            System.out.println();

            // Row border
            System.out.print(" +");
            for (int i = 1; i < width; i++)
                System.out.print("--+");
            System.out.println("--+");
        }

        // Print agent positions and prompt
        System.out.println();
        for (LawnMowerAgent agent : this.agents) {
            Position pos = agent.getPosition();
            System.out.println("Agent " + agent.getId() + " at (" + pos.x + "," + pos.y + ")");
        }

        System.out.println("\nPress RETURN to continue.");

        try {
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            console.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}