package CombatService;

public class Enemy {
    protected int enemyAP;
    protected int enemyDamage;
    protected int enemyHP;

    protected int totalEnemyHP;

    public Enemy(int enemyAP, int enemyDamage, int enemyHP){
        this.enemyAP = enemyAP;
        this.enemyDamage = enemyDamage;
        this.enemyHP = enemyHP;
        this.totalEnemyHP = enemyHP;
    }

    // Check if dead
    public int update(){
        if (this.enemyHP >= 0){
            return 0;
        }else {
            return 1;
        }
    }

    // Query Functions

    public int getEnemyAP() {
        return enemyAP;
    }

    public int getEnemyDamage() {
        return enemyDamage;
    }

    public int getEnemyHP() {
        return enemyHP;
    }

    public int getTotalEnemyHP(){
        return totalEnemyHP;
    }

    //Declarative/Modification Statements

    public void setEnemyHP(int enemyHP){
        this.enemyHP = enemyHP;
    }

    public void setEnemyAP(int enemyAP) {
        this.enemyAP = enemyAP;
    }

    public void addEnemyAP(int addAP){
        this.enemyAP += addAP;
    }

    public void setEnemyDamage(int enemyDamage) {
        this.enemyDamage = enemyDamage;
    }

    public void dealDamage(int dmg){
        enemyHP -= dmg;
    }

    public void heal(int modHP){
        enemyHP += modHP;
    }
}
