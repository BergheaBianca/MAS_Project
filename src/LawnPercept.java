public class LawnPercept {

    private boolean grass;
    private boolean obstacle;

    public LawnPercept(LawnState state, LawnMowerAgent agent) {

        int x, y, dir;

        Position pos = state.getPosition(agent);
        x = pos.x;
        y = pos.y;
        dir = pos.dir;

        if(state.hasGrass(x,y)){
            this.grass = true;
        }
        else{
            this.grass = false;
        }

        int viewX = x + Direction.DELTA_X[dir];
        int viewY = y + Direction.DELTA_Y[dir];
        if (state.isObstacle(viewX, viewY))
            this.obstacle = true;
        else
            this.obstacle = false;
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
