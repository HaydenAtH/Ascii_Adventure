package OutputService;
import Renderer.AsciiRenderer;
import Renderer.Image;
import StoryService.StoryBranch;
import StoryService.StoryNode;

import java.io.IOException;
import java.util.ArrayList;

public class StoryOutput {


    protected String name;
    protected String textOutput;
    protected Image img;

    protected StoryNode parentNode;



    public StoryOutput(String name, String textOutput, Image img) {
        this.name = name;
        this.textOutput = textOutput;
        this.img = img;
    }

    public void printOptions(){

    }


    //TODO Add exception for no image (Allow no image to be shown)
    public void activate() throws IOException {
        System.out.println("> " + this.textOutput);
        AsciiRenderer.render(img);
    }



    public void setImg(Image img){
        this.img = img;
    }

    public void setParentNode(StoryNode parentNode){
        this.parentNode = parentNode;
    }

    // Query Functions

    public String getName(){
        return this.name;
    }

    public String getTextOutput() {
        return this.textOutput;
    }

    public Image getImg() {
        return this.img;
    }



}
