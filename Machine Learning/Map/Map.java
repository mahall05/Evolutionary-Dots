package Map;
import java.awt.Graphics;
import java.util.ArrayList;

import Core.Point;

public class Map {
    private ArrayList<Obstacle> obs;
    private Point spawnPoint;
    private Goal goal;

    public Map(Point spawnPoint, Goal goal, Obstacle... obs){
        this.spawnPoint=spawnPoint;
        this.goal=goal;
        this.obs = new ArrayList<Obstacle>();
        for(int i = 0; i < obs.length; i++){
            this.obs.add(obs[i]);
        }
    }
    public Map(Point spawnPoint){
        this(spawnPoint, null);
    }
    public Map(){
        this(new Point(0,0));
    }
    
    public void render(Graphics g){
        for(Obstacle o : obs){
            o.render(g);
        }
        goal.render(g);
    }

    public Point getSpawnPoint(){
        return spawnPoint;
    }
}
