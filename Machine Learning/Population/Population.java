package Population;
import java.awt.Graphics;

import Core.Main;
import IO.Window;
import Map.Map;

public class Population {
    private Body[] bodies;
    private Map map;
    private int generation;
    private final double MUT_RATE=0.05;

    public Population(int size, Map map){
        bodies = new Body[size];
        this.map=map;

        for(int i = 0; i < size; i++){
            bodies[i] = new Body(map.getSpawnPoint());
        }
        generation=0;
    }
    public Population(int generation, Body[] past, Map map){
        bodies=new Body[past.length];
        int b=0;
        double f=0;
        double fitnessSum=0.0;
        for(int i = 0; i < past.length; i++){
            fitnessSum+=past[i].getFitness();
            if(past[i].getFitness()>f){
                b=i;
                f=past[i].getFitness();
            }
        }
        bodies[0] = new Body(map.getSpawnPoint(), past[b].getBrain());
        bodies[0].bestFit=true;

        for(int i = 1; i < bodies.length; i++){
            double runningTotal=0.0;
            double rand = Main.randomDouble(0.0, fitnessSum);
            for(int j = 0; j < past.length; j++){
                if(rand<=runningTotal+past[j].getFitness()){
                    bodies[i] = new Body(map.getSpawnPoint(), past[j].getBrain());
                }else{
                    runningTotal += past[j].getFitness();
                }
            }
        }

        for(int i = 0; i < bodies.length; i++){
            if(bodies[i]==null){
                bodies[i] = new Body(map.getSpawnPoint());
            }
        }
        //System.out.println("Test");
        for(int i = 1; i < bodies.length; i++){
            bodies[i].getBrain().mutate(MUT_RATE);
        }
    }

    public void calcFitness(){
        for(Body b : bodies){
            double fitness=0;
            
            fitness = fitness + (b.success()?10000-b.step:0) - map.goalDistance(b) - (!b.alive()?100:0);
            b.setFitness(fitness);
        }
        double lowest=Double.MAX_VALUE;
        for(Body b : bodies){
            if(b.getFitness()<lowest) lowest=b.getFitness();
        }
        for(Body b : bodies){
            b.setFitness(b.getFitness()-lowest);
        }
    }

    public void tick(){
        for(int i = 0; i < bodies.length; i++){
            bodies[i].tick();
            if(map.deathCollision(bodies[i])) bodies[i].kill();
            if(map.goalCollision(bodies[i])) bodies[i].win();
        }

        int active=0;
        for(int i = 0; i < bodies.length; i++){
            if(bodies[i].alive() && !bodies[i].success() && !bodies[i].done()){
                active++;
            }
        }
        if(active==0){
            calcFitness();
            new Population(this.generation+1, bodies, map);
        }
    }
    public void render(Graphics g){
        for(int i = 0; i < bodies.length; i++){
            bodies[i].render(g);
        }
    }
}
