package StoryService;

import OutputService.StoryOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//TODO Finish Story Node Class
public class StoryNode {
    protected Scanner scanner = new Scanner(System.in);
    String name;
    ArrayList<StoryBranch> sockets = new ArrayList<StoryBranch>();

    StoryOutput output;

    public StoryNode(String name, StoryOutput output){
        this.name = name;
        this.output = output;
        this.output.setParentNode(this);
    }

    public void activate() throws IOException {
        this.output.activate();
        printOptions();
        String input = scanner.next();
        makeDecision(input);

    }

    // Input a string and cycle through sockets to check if it's a valid decision
    public boolean isValidOption(String option){
        return true;
    }

    //Makes the decision based on string input
    public void makeDecision(String decision){

    }

    public void printOptions(){

    }

    // Query Functions


    public StoryOutput getOutput() {
        return output;
    }

    public ArrayList<StoryBranch> getSockets() {
        return sockets;
    }

    public String getName() {
        return name;
    }

    // Declarative/Additive Functions

    public void setName(String name) {
        this.name = name;
    }

    public void setOutput(StoryOutput output) {
        this.output = output;
    }

    public void addBranch(StoryBranch newBranch){
        this.sockets.add(newBranch);
    }
}
