package CombatService;

import OutputService.Encounter;
import PlayerService.PlayerInfo;

import java.util.Scanner;

public class Util {
    static Scanner scanner = new Scanner(System.in);

    // Returns -1 on insufficient AP
    // Returns 1 on success
    // Returns 2 to signify the end of combat/death of the enemy

    // Handles the checks of AP, HP, etc. and deals out damage if applicable, returning a completion code
    public static int playerAttack(Encounter output){
        if (PlayerInfo.getHp() > 0 && PlayerInfo.getActionPoints() >= 5){
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
        if (PlayerInfo.getHp() > 0 && PlayerInfo.getActionPoints() > 3){
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

    public static int enemyTurn(Encounter output){
        Enemy enmy = output.getEnemy();
        int x = 0;

        if (enmy.getEnemyHP() > 0){
            if (enmy.getEnemyHP() < (enmy.getTotalEnemyHP() / 2)){
                x = Util.enemyHeal(enmy);
            }else if(enmy.getEnemyAP() < 4){
                x = Util.enemyWait(enmy);
            }else {
                if (Util.enemyAttack(enmy) == )
            }
            Util.enemyAttack(enmy);
        }
    }

    public static void printCombatOptions() {
        String input = scanner.nextLine();

        System.out.print("--------------------------------------------------------------------------------------");
        // Player Turn
        System.out.println("> It's Your Turn!");
        System.out.println("> Here are your options:");
        System.out.println("> Attack: " + PlayerInfo.getDamage() + " Damage");
        System.out.println("> Heal: 5 Hit Points | Costs 5 Action Points");
        System.out.println("> Wait: +5 Action Points");
    }
}
