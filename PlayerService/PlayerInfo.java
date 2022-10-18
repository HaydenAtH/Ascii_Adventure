package PlayerService;

import Renderer.Image;
import StoryService.StoryNode;
import org.w3c.dom.Attr;

import java.util.Locale;

public class PlayerInfo {
    protected static int hp = 20; // Default HP
    protected static int damage = 5; // Could later be replaced with a weapon datatype

    protected static int actionPoints = 10; // Costs action points to attack/heal
    protected static int totalHP = 20;

    protected static String name = null;

    protected static Attribute wisdom = new Attribute("wisdom", 10);
    protected static Attribute strength = new Attribute("strength", 10);
    protected static Attribute agility = new Attribute("agility", 10);
    protected static Attribute endurance = new Attribute("endurance", 10);
    protected static Attribute intelligence = new Attribute("intelligence", 10);;

    protected static Attribute[] playerAttributes = {wisdom, strength, agility, endurance, intelligence};

    protected static int attackModifier = 0;
    protected static int actionPointsModifier = 0;

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

    public static String getName() {
        return name;
    }

    public static Attribute findAttributeByName(String name){
        for (int i = 0; i < playerAttributes.length; i++){
            if (playerAttributes[i].getName().equals(name.toLowerCase(Locale.ROOT))){
                return playerAttributes[i];
            }
        }
        return null;
    }

    public static Attribute findAttributeByValue(int value){
        for (int i = 0; i < playerAttributes.length; i++){
            if (playerAttributes[i].getValue() == value){
                return playerAttributes[i];
            }
        }
        return null;
    }

    public static Attribute findAttribute(Attribute atb){
        for (int i = 0; i < playerAttributes.length; i++){
            if (playerAttributes[i] == atb){
                return playerAttributes[i];
            }
        }
        return null;
    }

    //Declarative/Modification Functions

   public static void modifyAttribute(String name, int modVal){
        findAttributeByName(name).modValue(modVal);
   }

    public static void modifyAttribute(Attribute atb, int modVal){
        findAttribute(atb).modValue(modVal);
    }

    public static void setName(String nwName) {
        name = nwName;
    }

    public static void dealDamage(int dmg){
        PlayerInfo.hp -= dmg;
    }

    public static void modifyDamage(int dmgMod){
        PlayerInfo.damage += dmgMod;
    }

    public static void modifyActionPoints(int apMod){
        PlayerInfo.actionPoints += apMod;
    }

    // 1 Success
    // -1 Cannot Overheal
    public static int healHP(int healHP){
        if (PlayerInfo.hp + healHP < totalHP){
            PlayerInfo.hp += healHP;
            return 1;
        }else {
            return -1;
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

    // Util

    public static void printPlayerAtts(){
        System.out.println("Strength     | " + strength.getValue());
        System.out.println("Agility      | " + agility.getValue());
        System.out.println("Endurance    | " + endurance.getValue());
        System.out.println("Wisdom       | " + wisdom.getValue());
        System.out.println("Intelligence | " + intelligence.getValue());
    }
}
