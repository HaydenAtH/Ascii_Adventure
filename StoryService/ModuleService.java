package StoryService;

import OutputService.DialogueEvent;
import OutputService.Event;
import Renderer.AsciiRenderer;
import Renderer.Image;

import java.io.IOException;
import java.util.Scanner;

// ModuleService is a storage area for experiences where I write all the nodes/connections and can be easily activated/called from main
public class ModuleService {

    Image prlEarings = new Image("H:\\TextBasedStory\\src\\Renderer\\Images\\pearlEarings.jpg", 1.05, null, false);
    Image monaLisa = new Image("H:\\TextBasedStory\\src\\Renderer\\Images\\monaLisa.jpg", 0.75, null, false);

    public static void mainMenu() throws InterruptedException, IOException {
        Image logo = new Image("H:\\TextBasedStory\\src\\Renderer\\Images\\Logo.jpg", 1, null, true);
        boolean mmValidInput = false;
        Scanner scnr = new Scanner(System.in);

        System.out.println("> Without further ado... Welcome to");
        Thread.sleep(1000);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
        AsciiRenderer.render(logo);
        System.out.println(" ");
        System.out.println(" ");
        Thread.sleep(1000);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("> Please pick a story demo to play!");
        System.out.println("> Each story lasts ~10 - 20 minutes but use the command :quit to quit at any time");

        while (mmValidInput == false){
            String input = scnr.nextLine();

            if (input.equals("Demo Level")){
                mmValidInput = true;
                ModuleService.gateDemo();
            }
        }
    }
    public static void gateDemo() throws IOException {
        Event originEvent = new Event("OriginEvent", "You walk towards the large wooden gate, aged by rain, snow and bloodshed. The thick, snowy hills surround you with tundra and large alpine trees block the sun", null);
        StoryNode originNode = new StoryNode("OriginNode", originEvent);

        Event gate_branch_open = new Event("Open The Gate", "With all your might you push the large wooden gate open revealing...", null);
        Event node_event_descTown = new Event("NodeEvent", "A bustling town, filled with traders, peasants, knights, and adventurers all living amongst each other", null);
        Util.newNode("NextNode", node_event_descTown, gate_branch_open, originNode, "Open The Gate");

        Event gate_branch_investigate = new Event("Investigate", "You thoroughly investigate the area", null);
        Event node_event_investigationDesc = new Event("NodeEvent", "You discover a trap, you are unsure if it is for animals.. or people", null);
        Util.newNode("NextNode2", node_event_investigationDesc, gate_branch_investigate, originNode, "Investigate");

        originNode.activate();
    }

    public static void dialogueDemo() throws IOException {
        Event originEvent = new Event("OriginEvent", "Frank: Hello!", null);
        StoryNode originNode = new StoryNode("OriginNode", originEvent);

        Event dialogue_branch_greeting1 = new Event("Bonjour", "Frank Nods", null);
        DialogueEvent dialogue_node_greeting = new DialogueEvent("NodeEvent", "So how have you been?", null);
        StoryNode dialogue1Node = Util.newNode("NextNode", dialogue_node_greeting, dialogue_branch_greeting1, originNode, "Bonjour");

        Event dialogue_branch_greeting2 = new Event("Bonjour", "Frank Nods", null);
        Util.newBranch(dialogue_branch_greeting2, originNode, dialogue1Node, "Hey!");

        Event dialogue_branch_greeting3 = new Event("Bonjour", "Frank Nods", null);
        Util.newBranch(dialogue_branch_greeting3, dialogue1Node, originNode, "Hey!");

        originNode.activate();
    }

    //Util.newNode("NewNode", test, );
}
