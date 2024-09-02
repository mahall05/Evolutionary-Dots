package Map;

import java.awt.Color;
import java.awt.Graphics;

public class Goal extends Obstacle{

    public Goal(int x, int y, int width, int height) {
        super(x, y, width, height);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void render(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x,y,width,height);
    }
    
}
