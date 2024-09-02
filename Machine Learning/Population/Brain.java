package Population;

import java.awt.geom.Point2D;

import Core.Main;
import Core.Point;

public class Brain {
    public Point[] steps;
    public int moveSpeed=10;

    public Brain(int steps){
        this.steps = new Point[steps];

        for(int i = 0; i < steps; i++){
            this.steps[i] = new Point(Main.randomInt(-moveSpeed,moveSpeed), Main.randomInt(-moveSpeed,moveSpeed));
        }
    }

    public Brain(Point[] steps){
        this.steps=new Point[steps.length];
        for(int i = 0; i < steps.length; i++){
            this.steps[i] = steps[i];
        }
    }

    public Point[] getSteps(){
        return steps;
    }

    public void mutate(double rate){
        for(int i = 0; i < steps.length; i++){
            double rand = Main.randomDouble(0.0, 1.0);
            if(rand<rate) steps[i] = new Point(Main.randomInt(-moveSpeed,moveSpeed), Main.randomInt(-moveSpeed,moveSpeed));
        }
    }
}
