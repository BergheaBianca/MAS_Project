import java.util.Map;

public class LawnState{
    private Map<LawnMowerAgent, Position> agentPositions;

    private static int nrAgents;

    private int height;

    private int width;

    private static int grassTiles = 15;

    //map tile types
    private static final int CLEAR = 0;

    private static final int GRASS = 1;

    private static final int OBSTACLE = 2;

    //map
    private static int[][] defaultMap = {
            { OBSTACLE, GRASS, GRASS, GRASS, GRASS },
            { GRASS, GRASS, GRASS, OBSTACLE, GRASS },
            { GRASS, GRASS, OBSTACLE,  GRASS, OBSTACLE },
            { GRASS, GRASS, GRASS, OBSTACLE, OBSTACLE },
            { GRASS, OBSTACLE, OBSTACLE, OBSTACLE, OBSTACLE } };

    public LawnState(int nrAgents, Map<LawnMowerAgent, Position> agentPositions){

        this.nrAgents = nrAgents;
        this.agentPositions = agentPositions;
    }


    static LawnState getInitState(){
        LawnState s;

        s = new LawnState(0, null);
        s.width = defaultMap.length;
        s.height = defaultMap[0].length;
//        s.agentPositions = initAgentPos(nrAgents);
        return s;
    }

//    static Map<LawnMowerAgent, Position> initAgentPos(int nrAgents){
//
//    }

    void display(){
        for (Map.Entry<LawnMowerAgent, Position> entry : this.agentPositions.entrySet()) {
            LawnMowerAgent agent = entry.getKey();
            Position pos = entry.getValue();
            System.out.println("Agent " + agent + " is at position (" + pos.x + ", " + pos.y + ")");
        }
    }

    boolean isOccupied(int x, int y){
        for (Position pos : this.agentPositions.values()) {
            if (pos.x == x && pos.y == y) {
                return true;
            }
        }
        return false;
    }

    void updatePostion(LawnMowerAgent a, Position p){
        this.agentPositions.put(a, p);
    }

    Position getPosition(LawnMowerAgent a){
        return this.agentPositions.get(a);
    }

    void removeGrass(int x, int y){
        if(defaultMap[x][y] == GRASS){
            defaultMap[x][y] = CLEAR;
            grassTiles --;
        }
    }

    boolean hasGrass(int x, int y){
        if(defaultMap[x][y] == GRASS){
            return true;
        }
        return false;
    }

    boolean isObstacle(int x, int y){
        if(defaultMap[x][y] == OBSTACLE){
            return true;
        }
        return false;
    }

    boolean inBounds(int x, int y) {

        if (x >= 0 && x < width && y >= 0 && y < height) {
            return true;
        }
        return false;
    }

    int getGrassTiles(){
        return grassTiles;
    }

    int getAgentDir(){  return 0; }

    public boolean hasObstacle(int x, int y) {
        return false;
    }

    public void setAgentDir(int newDir) {
    }
}