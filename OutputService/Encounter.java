package OutputService;

import Renderer.Image;

public class Encounter extends StoryOutput {
    protected int enemyHP;
    protected int enemyAP;
    protected int enemyDamage;

    public Encounter(String name, String textOutput, Image img) {
        super(name, textOutput, img);
    }

    @Override
    public void activate() {

    }

    public int getEnemyAP() {
        return enemyAP;
    }

    public int getEnemyDamage() {
        return enemyDamage;
    }

    public int getEnemyHP() {
        return enemyHP;
    }

    public void setEnemyHP(){
        this.enemyHP = enemyHP;
    }

    public void setEnemyAP(int enemyAP) {
        this.enemyAP = enemyAP;
    }
}
