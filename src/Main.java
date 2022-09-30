import OutputService.*;
import Renderer.*;
import StoryService.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Image prlEarings = new Image("H:\\TextBasedStory\\src\\Renderer\\Images\\pearlEarings.jpg", 1.05, null, false);
        Image monaLisa = new Image("H:\\TextBasedStory\\src\\Renderer\\Images\\monaLisa.jpg", 0.75, null, false);

        Event test = new Event("TestEvent", "TestTESTtest", monaLisa);
        StoryNode originNode = new StoryNode("OriginNode", test);

        //Util.newNode("NewNode", test, );
    }
}
