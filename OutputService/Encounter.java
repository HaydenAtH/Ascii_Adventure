package OutputService;

import CombatService.Enemy;
import Renderer.Image;

public class Encounter extends StoryOutput {
    protected Enemy enemy = new Enemy(10, 10, 10);

    public Encounter(String name, String textOutput, Image img) {
        super(name, textOutput, img);
    }

    public void setEnemy(int ap, int dmg, int hp){
        this.enemy.setEnemyAP(ap);
        this.enemy.setEnemyDamage(dmg);
        this.enemy.setEnemyHP(hp);
    }

    public void setEnemy(Enemy inpEnemy){
        this.enemy.setEnemyAP(inpEnemy.getEnemyAP());
        this.enemy.setEnemyDamage(inpEnemy.getEnemyDamage());
        this.enemy.setEnemyHP(inpEnemy.getEnemyHP());
    }

    public Enemy getEnemy(){
        return this.enemy;
    }
}
