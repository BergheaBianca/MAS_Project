public class Message {

    private String type;

    private int x;

    private int y;

    private String senderId;

    public Message(String type, int x, int y, String senderId) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.senderId = senderId;
    }

    String getType(){
        if(type == "mowed"){
            return "mow";
        }
        else{
            return "move";
        }
    }

    Position getPosition(){
        return new Position(this.x, this.y);
    }

    public String toString(){
        if(this.type == "mowed"){
            return "Mowed (" + this.x + ", "  + this.y + ")";
        }
        else{
            return "Moving to (" + this.x + ", " + this.y + ")";
        }
    }
}
