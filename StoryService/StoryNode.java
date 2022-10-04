package StoryService;

import CombatService.Util;
import OutputService.Encounter;
import OutputService.StoryOutput;
import PlayerService.PlayerInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

//TODO Finish Story Node Class
public class StoryNode {
    protected Scanner scanner = new Scanner(System.in);
    String name;
    ArrayList<StoryBranch> sockets = new ArrayList<StoryBranch>();

    protected StoryBranch triggeredBranch; // Used for tracing back the previous node that was triggered

    String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H"};

    StoryOutput output;
    protected boolean validInput = false;

    public StoryNode(String name, StoryOutput output){
        this.name = name;
        this.output = output;
        this.output.setParentNode(this);
    }

    public void activate() throws IOException, InterruptedException {
        PlayerInfo.setCurrentNode(this);
        validInput = false;
        this.output.activate();
        printOptions();
        int h = 0;

        //TODO Add combat loop

        //Warning: Some placeholder code present

        if (this.output.getClass().getName().equals("StoryOutput.Encounter") == false){
            while (validInput == false){
                h++;

                String input = scanner.nextLine();

                if (true){
                    if (Objects.equals(input, "debug")){
                        System.out.println("Debug Values Below: ");
                        System.out.println("> " + this.getName());
                        System.out.println("> " + this.getSockets());
                        System.out.println("> " + this.getOutput());
                    } else if(Objects.equals(input, "quit")){
                        ModuleService.mainMenu();
                    }

                    if (isValidOption(input) != -1){
                        validInput = true;
                        makeDecision(input);
                    }else {
                        System.out.println("> Invalid Input, Please Retry");
                    }
                }
            }
        }else {
            boolean combatActive = true;

            while (combatActive == true){
                int t = 0; //Turn Counter
                while (validInput == false){
                    if (t % 2 == 0){
                        // Player Turn
                        Util.printCombatOptions();

                        String inp = scanner.nextLine();
                        int x = 0;

                        if (inp.equals("Attack")){
                            x = Util.playerAttack((Encounter) this.output);
                        }else if (inp.equals("Heal")){
                            x = Util.playerHeal();
                        }else if (inp.equals("Wait")){
                            x = Util.playerWait();
                        }

                        if (x == -1){
                            System.out.println("> Insufficient Stamina");
                        }else if (x == 1){
                            validInput = true;
                        }else if (x == 2){
                            combatActive = false;
                        }
                    }else{
                        //Enemy Turn
                        Util.enemyTurn((Encounter) this.output);
                    }
                }
                t++;
                validInput = false;
            }
        }
    }

    // Input a string and cycle through sockets to check if it's a valid decision
    public int isValidOption(String option){
        for (int i = 0; i < sockets.size(); i++){
            if (option.equals(sockets.get(i).getName()) || option.equals(letters[i])){
                return i;
            }
        }

        return -1;
    }

    //Makes the decision based on string input
    public void makeDecision(String decision) throws IOException, InterruptedException {
        sockets.get(isValidOption(decision)).activate();
    }

    public void printOptions() {
        System.out.print("--------------------------------------------------------------------------------------");

        if (this.output.getClass().getName().equals("OutputService.DialogueEvent") && !socketsHas("Back")){
            StoryBranch traceBackBranch = new StoryBranch(this, triggeredBranch.getOrigin(), null, "Back");
            sockets.add(traceBackBranch);
        }

        for (int z = 0; z < sockets.size(); z++){
            System.out.println(" ");
            System.out.println("> " + "Option " + (letters[z]) + ": " + sockets.get(z).getName());
        }
    }



    public StoryNode getPreviousNode(){
        if (this.triggeredBranch != null){
            return this.triggeredBranch.origin;
        }else{
            System.out.println("Err: Triggered Branch is Null");
        }
        return null;
    }

    public boolean socketsHas(String inp){
        for (int i = 0; i < sockets.size(); i++){
            if (Objects.equals(sockets.get(i).getName(), inp)){
                return true;
            }
        }
        return false;
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

    public StoryBranch getTriggeredBranch() {
        return triggeredBranch;
    }

    // Declarative/Additive Functions

    public void setName(String name) {
        this.name = name;
    }

    public void setOutput(StoryOutput output) {
        this.output = output;
    }

    public void setTriggeredBranch(StoryBranch triggeredBranch) {
        this.triggeredBranch = triggeredBranch;
    }

    public void addBranch(StoryBranch newBranch){
        this.sockets.add(newBranch);
    }
}
