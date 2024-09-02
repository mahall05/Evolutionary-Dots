package Population;
import java.awt.Color;
import java.awt.Graphics;

import Core.Point;

public class Body{
    public int x,y,size=7;
    private Brain brain;
    public int step=0;
    private boolean alive;
    private boolean success;
    private boolean done;
    public boolean bestFit=false;
    private double fitness=0.0;

    public Body(Point pos){
        this.x=pos.x;
        this.y=pos.y;
        brain = new Brain(100);
        alive=true;
        success=false;
        done=false;
    }
    public Body(Point pos, Brain brain){
        this.x=pos.x;
        this.y=pos.y;
        this.brain=new Brain(brain.getSteps());
        alive=true;
        success=false;
        done=false;
    }

    public void tick(){
        if(alive && !success && !done){
            x+=brain.steps[step].x;
            y+=brain.steps[step].y;
            step++;
            if(step==brain.getSize()-1) done=true;
        }
    }
    public void render(Graphics g){
        g.setColor(bestFit?Color.GREEN:Color.BLACK);
        g.fillOval(x,y,size,size);
    }

    public void kill(){
        alive=false;
    }
    public void win(){
        success=true;
    }
    public boolean success(){
        return success;
    }
    public boolean alive(){
        return alive;
    }
    public void setFitness(double fit){
        fitness=fit;
    }
    public double getFitness(){
        return fitness;
    }
    public Brain getBrain(){
        return brain;
    }
    public boolean done(){
        return done;
    }
}