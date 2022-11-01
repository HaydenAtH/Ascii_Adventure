package StoryService;

import CombatService.Enemy;
import OutputService.DialogueEvent;
import OutputService.Encounter;
import OutputService.Event;
import PlayerService.PlayerInfo;
import Renderer.AsciiRenderer;
import Renderer.Image;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Scanner;

// ModuleService is a storage area for experiences where I write all the nodes/connections and can be easily activated/called from main
public class ModuleService {

    static Image prlEarings = new Image("H:\\TextBasedStory\\src\\Renderer\\Images\\pearlEarings.jpg", 1.05, null, false);
    static Image monaLisa = new Image("H:\\TextBasedStory\\src\\Renderer\\Images\\monaLisa.jpg", 0.75, null, false);
    static Image prologueMountains = new Image("H:\\TextBasedStory\\src\\Renderer\\Images\\PrologueMountain.jpg", 0.95, null, true);
    static Scanner scnr = new Scanner(System.in);

    static boolean firstLoaded = false;

    public static void mainMenu() throws InterruptedException, IOException {
        Image logo = new Image("H:\\TextBasedStory\\src\\Renderer\\Images\\Logo.jpg", 1, null, true);
        boolean mmValidInput = false;


        Thread.sleep(1000);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
        if (firstLoaded == false){
            AsciiRenderer.render(logo);
            firstLoaded = true;
        }

        System.out.println(" ");
        System.out.println(" ");
        Thread.sleep(1000);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("> Please pick a story demo to play!");
        System.out.println("> Each story lasts ~10 - 20 minutes but use the command 'quit' to return to the main menu at any time");
        System.out.println("> The 'help' command can also be used to view a list of available commands at any time");
        System.out.println(" ");
        System.out.println("> Character Creator [CC]");
        System.out.println("> Tutorial/Main Story [MS]");
        System.out.println("> Gate Demo [DEBUG]");

        while (mmValidInput == false){
            String input = scnr.nextLine();

            if (input.equals("Demo Level")){
                mmValidInput = true;
                ModuleService.gateDemo();
            }else if(input.equals("Character Creator") || input.equals("CC")){
                mmValidInput = true;
                ModuleService.characterCreator();
            }else if (input.equals("MS")){
                mmValidInput = true;
                ModuleService.tutorial();
            }
        }
    }

    public static void characterCreator() throws IOException, InterruptedException {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");

        if (PlayerInfo.getName() == null){
            System.out.println("Before you edit your character's attributes, give your warrior, mage, or assassin a friendly or not so friendly name");
            String nwName = scnr.nextLine();
            PlayerInfo.setName(nwName);
            System.out.println("Alright.. a little weird, but no judgement");
            System.out.println("Now that your mighty warrior has a name lets adjust what makes them tall, thin, strong, weak, and everything in between");
            System.out.println("----------------------------------------------------------------------------");
        }

        System.out.println("Welcome, time to create your mighty adventurer, please use the commands below to edit your attributes");
        System.out.println("Here's some ground rules: ");
        System.out.println("> Use they keyword 'add' to use available points to increase an attribute's value for example: 'add strength 4'");
        System.out.println("> Use they keyword 'remove' to remove points from an attribute and make those points available for another attribute for example: 'remove wisdom 3'");
        System.out.println("> Each attribute can ONLY GO AS HIGH AS 20");
        System.out.println("> When you are happy with your character's attributes use the keyword 'DONE' to end character creation and return you to the main menu to start the game");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("> You have 12 available points to work with");
        System.out.println(" ATTRIBUTES ");
        PlayerInfo.printPlayerAtts();

        boolean ccComplete = false;
        int availablePoints = 12;

        while (ccComplete == false){


            String keyword = scnr.next();

            if (keyword.equals("DONE")){
                ModuleService.mainMenu();
                ccComplete = true;
            }

            String attribute = scnr.next();
            int points = (int) scnr.nextInt();

            boolean modified = false;

            if (keyword.equals("remove")){
                points = -points;
            }else {
                points = Math.abs(points);
            }

            if (PlayerInfo.findAttributeByName(attribute) != null){
                if ((PlayerInfo.findAttributeByName(attribute).getValue() + points) <= 20 && (PlayerInfo.findAttributeByName(attribute).getValue() + points) >= 0){
                    PlayerInfo.modifyAttribute(attribute, points);
                    modified = true;
                }else{
                    System.out.println("> Attributes may not exceed 20 points in value and must be at least 0");
                }
            }

            if (modified){
                if ((availablePoints + -points) >= 0){
                    availablePoints += -points;
                    System.out.println(attribute.toUpperCase() + " Modified by " + points);
                    System.out.println("You have " + availablePoints + " available points remaining");
                    System.out.println("------------------------------------------------------------------------------");
                    PlayerInfo.printPlayerAtts();
                }else {
                    System.out.println("> Not enough available points");
                }
            }
        }

    }
    public static void gateDemo() throws IOException, InterruptedException {
        Event originEvent = new Event("OriginEvent", "You walk towards the large wooden gate, aged by rain, snow and bloodshed. The thick, snowy hills surround you with tundra and /nl/ large alpine trees block the sun", null);
        StoryNode originNode = new StoryNode("OriginNode", originEvent);

        Event gate_branch_open = new Event("Open The Gate", "With all your might you push the large wooden gate open revealing...", null);
        Event node_event_descTown = new Event("NodeEvent", "A bustling town, filled with traders, peasants, knights, and adventurers all living amongst each other", null);
        Util.newNode("NextNode", node_event_descTown, gate_branch_open, originNode, "Open The Gate",null, -1);

        Event gate_branch_investigate = new Event("Investigate", "You thoroughly investigate the area", null);
        Event node_event_investigationDesc = new Event("NodeEvent", "You discover a trap, you are unsure if it is for animals.. or people", null);
        Util.newNode("NextNode2", node_event_investigationDesc, gate_branch_investigate, originNode, "Investigate", PlayerInfo.findAttributeByName("agility"), 4);

        originNode.activate();
    }

    public static void dialogueDemo() throws IOException, InterruptedException {
        Event originEvent = new Event("OriginEvent", "Frank: Hello!", null);
        StoryNode originNode = new StoryNode("OriginNode", originEvent);

        Event dialogue_branch_greeting1 = new Event("Bonjour", "Frank Nods", null);
        DialogueEvent dialogue_node_greeting = new DialogueEvent("NodeEvent", "So how have you been?", null);
        StoryNode dialogue1Node = Util.newNode("NextNode", dialogue_node_greeting, dialogue_branch_greeting1, originNode, "Bonjour", null, -1);

        Event dialogue_branch_greeting2 = new Event("Bonjour", "Frank Nods", null);
        Util.newBranch(dialogue_branch_greeting2, originNode, dialogue1Node, "Hey!");

        Event dialogue_branch_greeting3 = new Event("Bonjour", "Frank Nods", null);
        Util.newBranch(dialogue_branch_greeting3, dialogue1Node, originNode, "Hey!");

        originNode.activate();
    }

    public static void combatDemo() throws IOException, InterruptedException {

        Enemy enemy = new Enemy(10, 5, 10, "Skeleton", null);
        Encounter enctr = new Encounter("EncounterNode", "A Skeleton jumps you!", null);
        enctr.setEnemy(enemy);

        StoryNode originNode = new StoryNode("OriginNode", enctr);
        originNode.activate();
    }

    public static void tutorial() throws IOException, InterruptedException {
        System.out.println("The Beautiful world of Allor, vibrant trees, vast mountains and various species, \nas grand and marvelous as they are dangerous.");
        Thread.sleep(4000);
        AsciiRenderer.render(prologueMountains);
        System.out.println("----------------------------------------");
        System.out.println("The main rulers of this land are the Elves and Humans, rivaled by blood and decades of torment between the two civilizations");
        System.out.println("You, " + PlayerInfo.getName() + ", are an agent of the Elvish government, and as you'll soon find out, you have been given an extremely \n important mission to eliminate the High King of the humans in his mountainside estate");

        Thread.sleep(5000);

        System.out.println("So wake up agent, there's a job to do . . . . .");

        Thread.sleep(2000);

        System.out.println("'Wake up " + PlayerInfo.getName() + " training's today', the instructor says, an unhappy look on his face \n while you climb out of your bed, late for training");
        System.out.println("You've been training for multiple months, just for one mission, to eliminate the human high king, your training is \n elaborate and dangerous but the day has almost arrived. You are set to execute the mission tomorrow at \n at the crack of dawn");

        Thread.sleep(7000);

        Event originEvent = new Event("OriginEvent", "You walk onto the training field, multiple wooden structures dot the large warehouse, all of them dented excessively \n You have been quite familiar with these in your training, they were built to look like human structures. \n You see your instructor and approach him, he turns towards you ", null);
        StoryNode originNode = new StoryNode("OriginNode", originEvent);

        Event greet1_branch_event = new Event("Hello sir", "You greet him formally and coldly", null);
        Event greet1_node_event = new Event("NodeEvent", "He is taken aback, 'Have we not known each other long enough for you to stop calling me sir?' ", null);
        StoryNode tutorialGreeting = Util.newNode("NextNode", greet1_node_event, greet1_branch_event, originNode, "Open The Gate",null, -1);
    }

    //Util.newNode("NewNode", test, );
}
