package Population;
import java.awt.Color;
import java.awt.Graphics;

import Core.Point;

public class Body{
    public int x,y,size=7;
    private Brain brain;
    private int step=0;
    private boolean alive;

    public Body(Point pos){
        this.x=pos.x;
        this.y=pos.y;
        brain = new Brain(500);
        alive=true;
    }

    public void tick(){
        if(alive){
            x+=brain.steps[step].x;
            y+=brain.steps[step].y;
            step++;
        }
    }
    public void render(Graphics g){
        g.setColor(Color.BLACK);
        g.fillOval(x,y,size,size);
    }

    public void kill(){
        alive=false;
    }
}