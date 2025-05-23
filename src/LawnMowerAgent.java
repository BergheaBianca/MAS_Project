import java.util.List;

public class LawnMowerAgent{
    private String id;

    private boolean grass;

    private boolean obstacle;

    protected Position position;

    List<Message> receivedMessages;

    public LawnMowerAgent(String id, Position position, List<Message> receivedMessages) {
        this.id = id;
        this.position = position;
        this.receivedMessages = receivedMessages;
    }

    void see(LawnPercept p){
        this.grass = p.seeGrass();
        this.obstacle = p.seeObstacle();
    }

    Action selectAction() {
        if (this.grass) {
            return new MowGrass();
        }

//        }
        return null;
    }

    String getId(){
        return this.id;
    }

    void handleMessage(Message m){

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
}
