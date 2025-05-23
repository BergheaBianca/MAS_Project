public class SendMessage extends Action {

    private Message m;

    public SendMessage(Message m) {
        this.m = m;
    }

    @Override
    public LawnState execute(LawnMowerAgent sender, LawnState state) {
        state.addMessage(m);
        return state;
    }

    @Override
    public String toString() {
        return "SendMessage from " + m.senderId + ": " + m.toString();
    }
}
