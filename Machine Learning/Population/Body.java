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
    private double fitness =0;

    public Body(Point pos){
        this(pos, new Brain(500));
    }
    public Body(Point pos, Brain brain){
        this.x=pos.x;
        this.y=pos.y;
        this.brain = brain;
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
    public void render(Graphics g, boolean best){
        g.setColor(best?Color.GREEN:Color.BLACK);
        g.fillOval(x,y,size,size);
    }
    public void render(Graphics g){
        render(g, false);
    }

    public void kill(){
        alive=false;
    }

    public boolean isActive(){
        return alive && !success && !endOfLife;
    }

    public Brain getBrain(){
        return brain;
    }

    public boolean alive(){
        return alive;
    }

    public double getFitness(){
        return fitness;
    }
    public void setFitness(double fitness){
        this.fitness=fitness;
    }
    public boolean success(){
        return success;
    }
    public int getStep(){
        return step;
    }
}