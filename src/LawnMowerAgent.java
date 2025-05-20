import java.util.List;

public class LawnMowerAgent{
    private String id;

    private boolean grass;

    private Position position;

    private boolean obstacle;

    private List<Message> receivedMessages;

    public LawnMowerAgent(String id, boolean grass, boolean obstacle, Position position, List<Message> receivedMessages) {
        this.id = id;
        this.grass = grass;
        this.obstacle = obstacle;
        this.position = position;
        this.receivedMessages = receivedMessages;
    }

    void see(LawnPercept p){
        if(p.seeGrass()){
            this.grass = true;
        }
        else{
            this.grass = false;
        }

        if(p.seeObstacle()){
            this.obstacle = true;
        }
        else{
            this.obstacle = false;
        }
    }

    Action selectAction() {
        if (this.grass) {
            return new MowGrass();
        }
        int[] directions = {0, 1, 2, 3};

        for (int dir : directions) {
            Position target = null;
            Action moveAction = null;

            switch (dir) {
                case 0: // Up
                    target = new Position(position.x - 1, position.y);
                    moveAction = new TurnUp();
                    break;
                case 1: // Down
                    target = new Position(position.x + 1, position.y);
                    moveAction = new TurnDown();
                    break;
                case 2: // Left
                    target = new Position(position.x, position.y - 1);
                    moveAction = new TurnLeft();
                    break;
                case 3: // Right
                    target = new Position(position.x, position.y + 1);
                    moveAction = new TurnRight();
                    break;
            }

            if (isOkToMove(target)) {
                return moveAction;
            }
        }
        return null;
    }

    String getId(){
        return this.id;
    }

    void handleMessage(Message m){

    }

    boolean isOkToMove(Position p){
        for (Message m : this.receivedMessages) {
            if (m.getType().equals("move")) {
                Position target = m.getPosition();
                if (target.equals(p)) {
                    return false;
                }
            }
        }

        return true;
    }
}
