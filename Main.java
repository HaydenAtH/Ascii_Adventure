import OutputService.*;
import Renderer.*;
import StoryService.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Image prlEarings = new Image("H:\\TextBasedStory\\src\\Renderer\\Images\\pearlEarings.jpg", 1.05, null, false);
        Image monaLisa = new Image("H:\\TextBasedStory\\src\\Renderer\\Images\\monaLisa.jpg", 0.75, null, false);

        Event originEvent = new Event("OriginEvent", "You walk towards the large wooden gate, aged by rain, snow and bloodshed. The thick, snowy hills surround you with tundra and large alpine trees block the sun", null);
        StoryNode originNode = new StoryNode("OriginNode", originEvent);

        // PLAYER AT GATE DEMO

        Event gate_branch_open = new Event("Open The Gate", "With all your might you push the large wooden gate open revealing...", null);
        Event node_event_descTown = new Event("NodeEvent", "A bustling town, filled with traders, peasants, knights, and adventurers all living amongst each other", null);
        Util.newNode("NextNode", gate_branch_open, node_event_descTown, originNode, "Open The Gate");

        Event gate_branch_investigate = new Event("Investigate", "You thoroughly investigate the area", null);
        Event node_event_investigationDesc = new Event("NodeEvent", "You discover a trap, you are unsure if it is for animals.. or people", null);
        Util.newNode("NextNode2", node_event_investigationDesc, gate_branch_investigate, originNode, "Investigate");

        originNode.activate();

        //Util.newNode("NewNode", test, );
    }
}
