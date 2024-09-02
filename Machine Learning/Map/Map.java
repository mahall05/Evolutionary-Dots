package Map;
import java.awt.Graphics;
import java.util.ArrayList;

import Core.Point;

public class Map {
    private ArrayList<Obstacle> obs;
    private Point spawnPoint;

    public Map(){
        obs = new ArrayList<Obstacle>();
        spawnPoint=new Point(0,0);
    }
    public Map(Point spawnPoint,Obstacle... obs){
        this.spawnPoint=spawnPoint;
        for(int i = 0; i < obs.length; i++){
            this.obs.add(obs[i]);
        }
    }
    
    public void render(Graphics g){
        for(Obstacle o : obs){
            o.render(g);
        }
    }

    public Point getSpawnPoint(){
        return spawnPoint;
    }
}
