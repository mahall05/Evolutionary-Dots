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
            this(size);
            bodies[0]=new Body(map.getSpawnPoint(), gen.getBodies()[0].getBrain());
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
    }
}
