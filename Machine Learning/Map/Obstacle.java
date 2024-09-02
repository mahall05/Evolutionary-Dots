package Map;
import java.awt.Color;
import java.awt.Graphics;

public class Obstacle{
    public int x,y,width,height;
    public Obstacle(int x, int y, int width, int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    public void render(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(x,y,width,height);
    }
}