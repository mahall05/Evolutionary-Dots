package Population;
import java.awt.Color;
import java.awt.Graphics;

import Core.Point;

public class Body{
    public int x,y,size=7;
    private Brain brain;
    private int step=0;
    private boolean alive;
    private boolean success;
    private boolean endOfLife;

    public Body(Point pos){
        this.x=pos.x;
        this.y=pos.y;
        brain = new Brain(500);
        alive=true;
        success=false;
        endOfLife=false;
    }

    public void tick(){
        if(alive && !endOfLife && !success){
            x+=brain.steps[step].x;
            y+=brain.steps[step].y;
            step++;
            if(step >= brain.size()-1) endOfLife=true;
        }
    }
    public void render(Graphics g){
        g.setColor(Color.BLACK);
        g.fillOval(x,y,size,size);
    }

    public void kill(){
        alive=false;
    }

    public boolean isActive(){
        return alive && !success && !endOfLife;
    }
}