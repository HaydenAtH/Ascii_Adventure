package OutputService;
import Renderer.AsciiRenderer;
import Renderer.Image;
import StoryService.StoryBranch;
import StoryService.StoryNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StoryOutput {


    protected String name;
    protected String textOutput;
    protected Image img;

    protected StoryNode parentNode;

    protected ArrayList<Integer> tabPoints = new ArrayList<Integer>();



    public StoryOutput(String name, String textOutput, Image img) {
        this.name = name;
        this.textOutput = textOutput;
        this.img = img;
    }

    public void printOptions(){

    }

    public void activate() throws IOException {
        String str = this.textOutput;
        Scanner strScanner = new Scanner(this.textOutput);

        //System.out.println(this.textOutput);

        // Mega unoptimized code ahead

        AsciiRenderer.render(img);

        for (int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '/' && str.charAt(i + 1) == 'n' && str.charAt(i + 2) == 'l'){
                System.out.println("");
                i += 4;
            }
            System.out.print(str.charAt(i));
        }
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
