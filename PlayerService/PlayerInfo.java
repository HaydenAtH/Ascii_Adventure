package PlayerService;

import Renderer.Image;
import StoryService.StoryNode;

public class PlayerInfo {
    protected static int hp = 20; // Default HP
    protected static int damage = 5; // Could later be replaced with a weapon datatype

    protected static int actionPoints = 10; // Costs action points to attack/heal
    protected static int totalHP = 20;

    protected static String name = null;

    protected static int wisdom = 10;
    protected static int strength = 10;
    protected static int agility = 10;
    protected static int endurance = 10;
    protected static int intelligence = 10;

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

    public static int getAgility() {
        return agility;
    }

    public static int getEndurance() {
        return endurance;
    }

    public static int getIntelligence() {
        return intelligence;
    }

    public static int getStrength() {
        return strength;
    }

    public static int getWisdom() {
        return wisdom;
    }

    //Declarative/Modification Functions

    public static void modStrength(int mod){
        strength += mod;
        attackModifier = Math.round(strength/10);
    }

    public static void modAgility(int mod){
        agility += mod;
    }

    public static void modEndurance(int mod){
        endurance += mod;
        actionPointsModifier = Math.round(endurance/10);
        actionPoints = 10 + actionPointsModifier;
    }

    public static void modWisdom(int mod){
        wisdom += mod;
    }

    public static void modIntelligence(int mod){
        intelligence += mod;
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
        System.out.println("Strength     | " + strength);
        System.out.println("Agility      | " + agility);
        System.out.println("Endurance    | " + endurance);
        System.out.println("Wisdom       | " + wisdom);
        System.out.println("Intelligence | " + intelligence);
    }
}
