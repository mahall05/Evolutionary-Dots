package Population;
import java.awt.Graphics;

import Core.Main;
import IO.Window;
import Map.Map;

public class Population {
    private Body[] bodies;

    public Population(int size, Map map){
        bodies = new Body[size];

        for(int i = 0; i < size; i++){
            bodies[i] = new Body(map.getSpawnPoint());
        }
    }

    public void tick(){
        for(int i = 0; i < bodies.length; i++){
            bodies[i].tick();
        }
    }
    public void render(Graphics g){
        for(int i = 0; i < bodies.length; i++){
            bodies[i].render(g);
        }
    }
}
