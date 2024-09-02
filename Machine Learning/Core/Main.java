package Core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JFrame;

import IO.Window;
import Population.Body;
import Population.Population;

public class Main extends Canvas implements Runnable{
    private static Window window;

    private boolean running = false;

    private Population population;

    public static void main(String[] args){
        window = new Window("Simulation", new Main());
    }

    public Main(){
        setPreferredSize(new Dimension(Window.WIDTH,Window.HEIGHT));
        population = new Population(100);
    }

    public void start(){
        running = true;
        new Thread(this).start();
    }

    @Override
    public void run() { // The game loop
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
            }
        }
    }

    public void tick(){
        population.tick();
    }
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs==null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
            g.setColor(Color.RED);
            g.fillRect(0, 0, getWidth(), getHeight());

            population.render(g);

            g.dispose();
            bs.show();
    }

    public static int randomInt(int minInclusive, int maxInclusive){
        int range = maxInclusive - minInclusive + 1;
        int rng = (int)(Math.random() * range) + minInclusive;
        return rng;
    }

    public static double randomDouble(double minInclusive, double maxInclusive){
        double range = maxInclusive - minInclusive + 1;
        double rng = (Math.random() * range) + minInclusive;
        return rng;
    }
    
}
