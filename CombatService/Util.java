package CombatService;

import OutputService.Encounter;
import PlayerService.PlayerInfo;
import Renderer.AsciiRenderer;

import java.io.IOException;
import java.util.Scanner;

public class Util {
    static Scanner scanner = new Scanner(System.in);

    // Returns -1 on insufficient AP
    // Returns 1 on success
    // Returns 2 to signify the end of combat/death of the enemy

    // Handles the checks of AP, HP, etc. and deals out damage if applicable, returning a completion code
    public static int playerAttack(Encounter output){
        if (PlayerInfo.getActionPoints() >= 5){
            output.getEnemy().dealDamage(PlayerInfo.getDamage());
            PlayerInfo.modifyActionPoints(-5);
            if (output.getEnemy().update() == 0){
                return 2;
            }else{
                return 1;
            }
        }
        return -1;
    }

    public static int playerWait(){
        if (PlayerInfo.getHp() > 0){
            PlayerInfo.modifyActionPoints(5);
            return 1;
        }
        return -1;
    }

    public static int playerHeal(){
        if (PlayerInfo.getActionPoints() > 3){
            PlayerInfo.healHP(5);
            PlayerInfo.modifyActionPoints(-3);
            return 1;
        }
        return -1;
    }

    // Returns 1 when an attack was performed on the player but they are still alive
    // Returns 2 when the attack killed the player
    // Returns -1 when enemy is dead
    public static int enemyAttack(Enemy enemy){
        if (enemy.getEnemyHP() > 0){
            PlayerInfo.dealDamage(enemy.getEnemyDamage());
            enemy.getEnemyHP();

            if (PlayerInfo.getHp() > 0){
                return 1;
            }else {
                return 2;
            }
        }
        return -1;
    }

    // 1 for success
    // -1 for lack of AP
    public static int enemyHeal(Enemy enemy){
        if (enemy.getEnemyAP() > 3){
            enemy.heal(3);
            return 1;
        }else {
            return -1;
        }
    }
    // 1 For success
    // -1 for dead
    public static int enemyWait(Enemy enemy){
        if (enemy.getEnemyHP() > 0){
            enemy.addEnemyAP(5);
            return 1;
        }else {
            return -1;
        }
    }

    public static void printResponse(){

    }

    // Return 1 for successful attack
    // Return 2 for successful heal
    // Return 3 for successful wait

    // Return -1 for player death
    // Return -2 for enemy death

    //TODO add additional player feedback and clarify turns and displays
    public static int enemyTurn(Encounter output) throws InterruptedException {
        System.out.println("The " + output.getEnemy().getName() + " is making their move");
        Thread.sleep(3000);
        Enemy enmy = output.getEnemy();
        int x = 0;

        if (enmy.getEnemyHP() > 0){
            if (enmy.getEnemyHP() < (enmy.getTotalEnemyHP() / 2)){
                x = Util.enemyHeal(enmy);

                if (x == 1){
                    //Success
                    return 2;
                }else if (x == -1){
                    //Not enough AP
                    x = Util.enemyWait(enmy);

                    if (x == 1){
                        //Success
                        return 3;
                    }
                }
            }else if(enmy.getEnemyAP() < 4){
                x = Util.enemyWait(enmy);

                if (x == 1){
                    //Success
                    return 3;
                }
            }else {
                x = Util.enemyAttack(enmy);

                if (x == 1){
                    //Successful attack
                    return 1;
                }else if (x == 2){
                    //Player dead
                    return -1;
                }
            }
            Util.enemyAttack(enmy);
        }
        return -2;
    }

    public static void printEnemyStats(Enemy enemy) throws IOException {
        AsciiRenderer.render(enemy.getImg());
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println(" ");
        System.out.println("Enemy Stats <");
        System.out.println(" ");
        System.out.println("Enemy HP <");
        for (int i = 0; i < enemy.getTotalEnemyHP(); i++){
            if (i <= enemy.getEnemyHP()){
                System.out.print("I");
            }else {
                System.out.print(".");
            }
        }
        System.out.print("| " + enemy.getEnemyHP() + "/" + enemy.getTotalEnemyHP() + " <");
        System.out.println(" ");

        System.out.println("Enemy AP <");
        for (int i = 0; i < enemy.getEnemyAP(); i++){
            System.out.print("I");
        }
        System.out.println("| " + enemy.getEnemyAP() + " <");
        System.out.println(" ");
        System.out.println("--------------------------------------------------------------------------------------");
    }

    public static void printPlayerStats(){
        System.out.println("Your Stats <");
        System.out.println(" ");
        System.out.println("Your HP <");
        for (int i = 0; i < PlayerInfo.getTotalHP(); i++){
            if (i <= PlayerInfo.getHp()){
                System.out.print("I");
            }else {
                System.out.print(".");
            }
        }
        System.out.print("| " + PlayerInfo.getHp() + "/" + PlayerInfo.getTotalHP() + " <");
        System.out.println(" ");

        System.out.println("Your AP <");
        for (int i = 0; i < PlayerInfo.getActionPoints(); i++){
            System.out.print("I");
        }
        System.out.println("| " + PlayerInfo.getActionPoints() + " <");
        System.out.println(" ");
        System.out.println("--------------------------------------------------------------------------------------");
    }

    public static void printCombatOptions() {
        // Player Turn
        System.out.println("> Your Turn!");
        System.out.println(" ");
        System.out.println("> Combat Options:");
        System.out.println("> Attack: " + PlayerInfo.getDamage() + " Damage");
        System.out.println("> Heal: 5 Hit Points | Costs 5 Action Points");
        System.out.println("> Wait: +5 Action Points");
    }
}
