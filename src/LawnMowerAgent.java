import java.util.List;

public class LawnMowerAgent implements  Runnable{
    private String id;

    private boolean grass;

    private boolean obstacle;

    protected Position position;

    private LawnEnvironment env;

    private boolean running = true;

    public LawnMowerAgent(String id, Position position) {
        this.id = id;
        this.position = position;
    }

    public void run() {
        while (running) {
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

            try {
                Thread.sleep(100); // simulate thinking time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        running = false;
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
        List<Message> recentMessages = state.getGlobalMessages();

        Position pos = this.position;
        int dir = pos.dir;

        int nextX = pos.x + Direction.DELTA_X[dir];
        int nextY = pos.y + Direction.DELTA_Y[dir];

        if (LawnState.inBounds(nextX, nextY) && !this.obstacle) {
            boolean targetedByOther = false;

            // Check if another agent is moving to that tile
            for (Message m : recentMessages) {
                if (!m.getSenderId().equals(this.id) &&
                        m.getPosition().x == nextX &&
                        m.getPosition().y == nextY &&
                        m.getType().equals("move")) {
                    targetedByOther = true;
                    break;
                }
            }

            if (!targetedByOther) {
                return new MoveForward();
            }
        }

        return new TurnRight();
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
