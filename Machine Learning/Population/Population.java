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

    public Population(int size, Map map){
        this.map=map;
        populationSize=size;
        activeGen = new Generation(size);
    }

    public void tick(){
        activeGen.tick();
        if(activeGen.finished()){
            newGeneration();
        }
    }
    public void render(Graphics g){
        activeGen.render(g);
    }
    private void newGeneration(){
        if(pastGen!=null) pastGen.destroy();
        pastGen=activeGen;
        activeGen=new Generation(populationSize);
    }

    private class Generation{
        private Body[] bodies;

        public Generation(int size){
            bodies = new Body[size];

            for(int i = 0; i < size; i++){
                bodies[i] = new Body(map.getSpawnPoint());
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
            for(int i = 0; i < bodies.length; i++){
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
    }
}
