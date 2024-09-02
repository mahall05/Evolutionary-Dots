package Map;
import java.awt.Graphics;
import java.util.ArrayList;

import Core.Point;
import IO.Window;
import Population.Body;

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

    public boolean deathCollision(Body body){
        for(int i = 0; i < obs.size(); i++){
            Obstacle o = obs.get(i);
            if((body.x>o.x && body.x<(o.x+o.width) && body.y>o.y && body.y<(o.y+o.height)) ||
               ((body.x+body.size)>o.x && (body.x+body.size)<(o.x+o.width) && body.y>o.y && body.y<(o.y+o.height)) ||
               (body.x>o.x && body.x<(o.x+o.width) && (body.y+body.size)>o.y && (body.y+body.size)<(o.y+o.height)) ||
               ((body.x+body.size)>o.x && (body.x+body.size)<(o.x+o.width) && (body.y+body.size)>o.y && (body.y+body.size)<(o.y+o.height))){
                return true;
            }
        }
        if(body.x<0 || body.x>Window.WIDTH || body.y<0 || body.y>Window.HEIGHT){
            return true;
        }
        return false;
    }
    public boolean goalCollision(Body body){
        if(body.x>goal.x && body.x<(goal.x+goal.width) && body.y>goal.y && body.y<(goal.y+goal.height)){
            return true;
        }
        return false;
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
