package Population;
import java.awt.Graphics;

import Core.Main;
import IO.Window;
import Map.Map;

public class Population {
    private Body[] bodies;
    private Map map;

    public Population(int size, Map map){
        bodies = new Body[size];
        this.map=map;

        for(int i = 0; i < size; i++){
            bodies[i] = new Body(map.getSpawnPoint());
        }
    }

    public void tick(){
        for(int i = 0; i < bodies.length; i++){
            bodies[i].tick();
            if(map.deathCollision(bodies[i])) bodies[i].kill();
        }
    }
    public void render(Graphics g){
        for(int i = 0; i < bodies.length; i++){
            bodies[i].render(g);
        }
    }
}
