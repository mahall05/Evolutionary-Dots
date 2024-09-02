package IO;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import Core.Main;

public class Window extends Canvas{
    public static final int WIDTH = 1500, HEIGHT = WIDTH / 16 * 9;

    private JFrame frame;

    public Window(String title, Main main){
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.add(main);
        frame.pack();
        //frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        //frame.setMinimumSize(new Dimension(WIDTH,HEIGHT));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        main.start();
    }
    
}
