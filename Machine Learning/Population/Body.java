package Population;
import java.awt.Color;
import java.awt.Graphics;

public class Body{
    private int x,y;
    private Brain brain;
    private int step=0;

    public Body(int x, int y){
        this.x=x;
        this.y=y;
        brain = new Brain(100);
    }

    public void tick(){
        x+=brain.steps[step].x;
        y+=brain.steps[step].y;
        step++;
    }
    public void render(Graphics g){
        g.setColor(Color.BLACK);
        g.fillOval(x,y,7,7);
    }
}