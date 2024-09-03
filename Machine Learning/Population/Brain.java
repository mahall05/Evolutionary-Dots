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

    public int size(){
        return steps.length;
    }
}
