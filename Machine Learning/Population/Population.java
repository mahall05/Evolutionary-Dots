package Population;
import java.awt.Graphics;

import Core.Main;
import IO.Window;
import Map.Map;

public class Population {
    private Map map;
    private Generation activeGen;
    private Generation pastGen;
    private int populationSize;
    private int generation=1;

    public Population(int size, Map map){
        this.map=map;
        populationSize=size;
        activeGen=new Generation(populationSize);
    }

    public void tick(){
        activeGen.tick();
        if(activeGen.finished()){
            activeGen.calcFitness();
            newGeneration();
        }
    }
    public void render(Graphics g){
        activeGen.render(g);
    }
    private void newGeneration(){
        if(pastGen!=null) pastGen.destroy();
        pastGen=activeGen;
        activeGen=new Generation(populationSize, pastGen);
        generation++;
    }

    private class Generation{
        private Body[] bodies;

        public Generation(int size){
            bodies = new Body[size];

            for(int i = 0; i < size; i++){
                bodies[i] = new Body(map.getSpawnPoint());
            }
        }
        public Generation(int size, Generation gen){
            bodies = new Body[size];
            bodies[0]=new Body(map.getSpawnPoint(), gen.getBestFit().getBrain());

            for(int i = 1; i < size; i++){
                double sum = 0.0;
                double rand = Main.randomDouble(0.0, gen.getFitSum());
                for(int j = 0; j < bodies.length; j++){
                    sum+=gen.getBodies()[j].getFitness();
                    if(rand<=sum){
                        
                    }
                }
            }
        }
        public void destroy(){
            for(int i = 0; i < bodies.length; i++){
                bodies[i]=null;
            }
        }

        public void tick(){
            for(int i = 0; i < bodies.length; i++){
                bodies[i].tick();
                if(map.deathCollision(bodies[i])) bodies[i].kill();
            }
        }
        public void render(Graphics g){
            bodies[0].render(g, generation==1?false:true);
            for(int i = 1; i < bodies.length; i++){
                bodies[i].render(g);
            }
        }

        public boolean finished(){
            for(int i = 0; i < bodies.length; i++){
                if(bodies[i].isActive()){
                    return false;
                }
            }
            return true;
        }
        public Body[] getBodies(){
            return bodies;
        }

        public void calcFitness(){
            for(Body b : bodies){ 
                double fitness=0; 
                 
                fitness = fitness + (b.success()?10000-b.getStep():0) - map.goalDistance(b) - (!b.alive()?100:0); 
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
        public Body getBestFit(){
            int j = -1;
            double fit = Double.MIN_VALUE;
            for(int i = 0; i < bodies.length; i++){
                if(bodies[i].getFitness()>fit){
                    j=i;
                    fit=bodies[i].getFitness();
                }
            }
            return bodies[j];
        }
        public double getFitSum(){
            double sum = 0;
            for(int i = 0; i < bodies.length; i++){
                sum+=bodies[i].getFitness();
            }
            return sum;
        }
    }
}
