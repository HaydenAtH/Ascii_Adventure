package StoryService;

import OutputService.StoryOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

//TODO Finish Story Node Class
public class StoryNode {
    protected Scanner scanner = new Scanner(System.in);
    String name;
    ArrayList<StoryBranch> sockets = new ArrayList<StoryBranch>();

    StoryOutput output;
    protected boolean validInput = false;

    public StoryNode(String name, StoryOutput output){
        this.name = name;
        this.output = output;
        this.output.setParentNode(this);
    }

    public void activate() throws IOException {
        this.output.activate();
        printOptions();
        int h = 0;

        while (validInput == false){
            h++;

            String input = scanner.nextLine();

            if (true){
                if (Objects.equals(input, "debug")){
                    System.out.println("Debug Values Below: ");
                    System.out.println("> " + this.getName());
                    System.out.println("> " + this.getSockets());
                    System.out.println("> " + this.getOutput());

                }

                if (isValidOption(input) != -1){
                    validInput = true;
                    makeDecision(input);
                }else if (h % 3 == 0){
                    System.out.println("> Invalid Input, Please Retry");
                }
            }
        }



    }

    // Input a string and cycle through sockets to check if it's a valid decision
    public int isValidOption(String option){
        for (int i = 0; i < sockets.size(); i++){
            if (option.equals(sockets.get(i).getName())){
                return i;
            }
        }

        return -1;
    }

    //Makes the decision based on string input
    public void makeDecision(String decision) throws IOException {
        if (isValidOption(decision) != -1){
            sockets.get(isValidOption(decision)).activate();
        }
    }

    public void printOptions() {
        for (int z = 0; z < sockets.size(); z++){
            System.out.println(" ");
            System.out.println("> " + "Option " + (z + 1) + ": " + sockets.get(z).getName());
        }
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
