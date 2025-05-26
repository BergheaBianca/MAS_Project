import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LawnMowerAgent implements  Runnable{
    private String id;

    private boolean grass;

    private boolean obstacle;

    protected Position position;

    private LawnEnvironment env;

    private boolean running = true;

    private Set<Position> visitedPositions = new HashSet<>();


    public LawnMowerAgent(String id, Position position) {
        this.id = id;
        this.position = position;
    }

    public void run() {

        running = true;
        // initial position
        visitedPositions.add(new Position(position.x, position.y, position.dir));

        while (running) {

            if (env.currentState().getGrassTiles() == 0) {
                running = false;
                break;
            }

            LawnPercept p = env.getPercept(this);
            see(p);
            Action action = selectAction();
            env.updateState(this, action);

            String msgType = null;
            if (action instanceof MoveForward) {
                msgType = "move";
            } else if (action instanceof MowGrass) {
                msgType = "mowed";
            }

            if (msgType != null) {
                Message msg = new Message(msgType, position.x, position.y, id);
                Action sendMsg = new SendMessage(msg);
                env.updateState(this, sendMsg);
            }

            if (action instanceof MoveForward) {
                visitedPositions.add(new Position(position.x, position.y, position.dir));
            }

            try {
                Thread.sleep(100); // simulate thinking time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    void see(LawnPercept p){
        this.grass = p.seeGrass();
        this.obstacle = p.seeObstacle();
    }

    Action selectAction() {
        if (this.grass) {
            return new MowGrass();
        }

        LawnState state = env.currentState();
        List<Message> messages = state.getGlobalMessages();

        List<Position> options = new ArrayList<>();

        for (int dir = 0; dir < 4; dir++) {
            int nx = position.x + Direction.DELTA_X[dir];
            int ny = position.y + Direction.DELTA_Y[dir];
            Position np = new Position(nx, ny, dir);

            if (!LawnState.inBounds(nx, ny)) continue;
            if (LawnState.isObstacle(nx, ny)) continue;

            boolean blocked = false;
            for (Message m : messages) {
                if (!m.getSenderId().equals(this.id) &&
                        m.getPosition().x == nx &&
                        m.getPosition().y == ny &&
                        m.getType().equals("move")) {
                    blocked = true;
                    break;
                }
            }

            if (!blocked) {
                options.add(np);
            }
        }

        // First try unvisited, then fallback to any direction
        for (Position opt : options) {
            if (!this.visited(opt) && opt.dir == position.dir) {
                return new MoveForward();
            }
        }

        for (Position opt : options) {
            if (!this.visited(opt)) {
                int turnSteps = (opt.dir - position.dir + 4) % 4;
                if (turnSteps == 1) return new TurnRight();
                else return new TurnLeft();
            }
        }

        // Allow revisits if stuck
        for (Position opt : options) {
            if (opt.dir == position.dir) {
                return new MoveForward();
            }
        }

        if (!options.isEmpty()) {
            int newDir = options.get(0).dir;
            int turnSteps = (newDir - position.dir + 4) % 4;
            if (turnSteps == 1) return new TurnRight();
            else return new TurnLeft();
        }

        return new TurnRight(); // fallback
    }


    private boolean visited(Position p){
        for(Position visitedPos : this.visitedPositions){
            if(visitedPos.x == p.x && visitedPos.y == p.y){
                return true;
            }
        }
        return false;
    }

    String getId(){
        return this.id;
    }

    boolean equals(LawnMowerAgent a){
        return this.getId() == a.getId();
    }

    Position getPosition(){
        return this.position;
    }

    void setPosition(Position p){
        this.position = p;
    }

    public void setEnvironment(LawnEnvironment env) {
        this.env = env;
    }
}
