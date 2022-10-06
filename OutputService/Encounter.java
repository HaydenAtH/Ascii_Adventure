package OutputService;

import CombatService.Enemy;
import Renderer.Image;

public class Encounter extends StoryOutput {
    protected Enemy enemy;

    public Encounter(String name, String textOutput, Image img) {
        super(name, textOutput, img);
    }

    public void setEnemy(Enemy enemy){
        this.enemy = enemy;
    }

    public Enemy getEnemy(){
        return this.enemy;
    }
}
