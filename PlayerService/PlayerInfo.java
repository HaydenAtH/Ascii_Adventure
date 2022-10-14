package PlayerService;

import Renderer.Image;
import StoryService.StoryNode;

public class PlayerInfo {
    protected static int hp = 20; // Default HP
    protected static int damage = 5; // Could later be replaced with a weapon datatype

    protected static int actionPoints = 10; // Costs action points to attack/heal
    protected static int totalHP = 20;

    protected static int wisdom;
    protected static int strength;
    protected static int agility;
    protected static int endurance;
    protected static int intelligence;

    protected static Image playerPortrait = new Image("H:\\TextBasedStory\\src\\Renderer\\Images\\rsz_knighthelmettemp.jpg", 0.75, null, true);

    protected static StoryNode currentNode;

    // Query Functions

    public static int getDamage() {
        return damage;
    }

    public static Image getPlayerPortrait() {
        return playerPortrait;
    }

    public static int getHp() {
        return hp;
    }

    public static StoryNode getCurrentNode() {
        return currentNode;
    }

    public static int getTotalHP() {
        return totalHP;
    }

    public static int getActionPoints(){
        return actionPoints;
    }

    //Declarative/Modification Functions


    public static void dealDamage(int dmg){
        PlayerInfo.hp -= dmg;
    }

    public static void modifyDamage(int dmgMod){
        PlayerInfo.damage += dmgMod;
    }

    public static void modifyActionPoints(int apMod){
        PlayerInfo.actionPoints += apMod;
    }

    public static void healHP(int healHP){
        if (PlayerInfo.hp + healHP < totalHP){
            PlayerInfo.hp += healHP;
        }else {
            System.out.println("Cannot Overheal");
        }

    }

    public static void setHp(int hp) {
        PlayerInfo.hp = hp;
    }

    public static void setDamage(int damage) {
        PlayerInfo.damage = damage;
    }

    public static void setCurrentNode(StoryNode currentNode) {
        PlayerInfo.currentNode = currentNode;
    }
}
