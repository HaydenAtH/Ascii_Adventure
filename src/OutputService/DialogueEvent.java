package OutputService;

import Renderer.Image;
import StoryService.StoryNode;

public class DialogueEvent extends StoryOutput {
    StoryNode lastNode;

    public DialogueEvent(String name, String textOutput, Image img) {
        super(name, textOutput, img);
    }

    @Override
    public void activate() {

    }
}
