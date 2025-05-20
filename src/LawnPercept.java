public class LawnPercept {

    private boolean grass;
    private boolean obstacle;

    public LawnPercept(LawnState state, LawnMowerAgent agent) {

        int x, y;

        Position pos = state.getPosition(agent);
        x = pos.x;
        y = pos.y;

        if(state.hasGrass(x,y)){
            this.grass = true;
        }
        else{
            this.grass = false;
        }

        if(state.hasObstacle(x,y)){
            this.obstacle = true;
        }
        else{
            this.obstacle = false;
        }
    }

    boolean seeGrass(){
        return this.grass;
    }

    boolean seeObstacle(){
        return this.obstacle;
    }


    public String toString(){
        if(this.grass){
            return "Grass";
        }
        if(this.obstacle){
            return "Obstacle";
        }
        return "Clear";
    }
}
