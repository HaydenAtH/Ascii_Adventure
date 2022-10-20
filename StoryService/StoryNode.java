package StoryService;

import CombatService.Enemy;
import CombatService.Util;
import OutputService.Encounter;
import OutputService.StoryOutput;
import PlayerService.PlayerInfo;
import Renderer.AsciiRenderer;
import com.sun.tools.jconsole.JConsolePlugin;

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

        //Warning: Some placeholder code present

        if (!this.output.getClass().getName().equals("OutputService.Encounter")){

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
                    } else if(Objects.equals(input, "character")){
                        System.out.println(PlayerInfo.getName());
                        System.out.println("-----------------");
                        Util.printPlayerStats();
                        PlayerInfo.printPlayerAtts();
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
            Encounter evnt = (Encounter) this.output;
            Enemy enemy = evnt.getEnemy();
            int t = 0; //Turn Counter

            while (combatActive == true){
                boolean turnPosted = false;
                while (validInput == false){
                    if (t % 2 == 0){
                        Thread.sleep(2000);
                        // Player Turn
                        if (turnPosted == false){
                            //TODO Fix rendering bugs/duplicates during fighting
                            //AsciiRenderer.render(PlayerInfo.getPlayerPortrait());
                            System.out.println(" ");
                            Util.printCombatOptions();
                            System.out.println("---------------------------------------------");
                            Util.printPlayerStats();
                            turnPosted = true;
                        }

                        String inp = scanner.nextLine();
                        int x = 0;

                        if (inp.equals("Attack")){
                            x = Util.playerAttack((Encounter) this.output);

                            if (x == -1){
                                System.out.println("> Insufficient Stamina");
                            }else if (x == 1){
                                validInput = true;
                                //Util.printEnemyStats(evnt.getEnemy());
                                System.out.println("Attacked " + evnt.getEnemy().getName() + " For " + PlayerInfo.getDamage() + " Damage");
                                //System.out.println("---------------------------------------------");
                            }else if (x == 2){
                                System.out.println("Enemy Slain!");
                                validInput = true;
                                combatActive = false;
                            }

                        }else if (inp.equals("Heal")){
                            x = Util.playerHeal();

                            if (x == -1){
                                System.out.println("> Insufficient Stamina");
                            }else if (x == 1){
                                validInput = true;
                                System.out.println("---------------------------------------------");
                                //Util.printEnemyStats(evnt.getEnemy());
                            }
                        }else if (inp.equals("Wait")){
                            x = Util.playerWait();

                            if (x == -1){
                                //Player Dead
                                combatActive = false;
                            }else if (x == 1){
                                validInput = true;
                                System.out.println("---------------------------------------------");
                                //Util.printEnemyStats(evnt.getEnemy());
                            }
                        }


                    }else{
                        //Enemy Turn

                        // Return 1 for successful attack
                        // Return 2 for successful heal
                        // Return 3 for successful wait

                        // Return -1 for player death
                        // Return -2 for enemy death

                        //TODO Fix display issues here
                        //TODO render enemy img

                        int x = Util.enemyTurn((Encounter) this.output);

                        if (x == 1){
                            System.out.println("> " + enemy.getName() + " Attacked You For " + enemy.getEnemyDamage() + " Damage!");
                        } else if(x == 2){
                            System.out.println("> " + enemy.getName() + " healed themselves for 3 health");
                        } else if(x == 3){
                            System.out.println("> " + enemy.getName() + " waited and earned 3 AP back");
                        }else if (x == -1){

                        }

                        Util.printEnemyStats(enemy);
                        validInput = true;
                    }
                }
                t++;
                validInput = false;
            }
            this.sockets.get(0).activate();
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

        boolean backAdded = false;

        for (int z = 0; z < sockets.size(); z++){
            if (sockets.get(z).getName().equals("Back") && backAdded == false){
                System.out.println(" ");
                System.out.println("> " + "Option " + (letters[z]) + ": " + sockets.get(z).getName());
                backAdded = true;
                return;
            }

            if (sockets.get(z).checkReqStatus() == 0){
                if (sockets.get(z).getRequirementAtt() != null){
                    System.out.println(" ");
                    System.out.println("> " + "Option " + (letters[z]) + " [" + sockets.get(z).getRequirementAtt().getName().toUpperCase() + "]" + ": " + sockets.get(z).getName());
                }else {
                    System.out.println(" ");
                    System.out.println("> " + "Option " + (letters[z]) + ": " + sockets.get(z).getName());
                }
            }
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
            if (sockets.get(i).getName().equals(inp)){
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

    public void removeBranch(StoryBranch branch){
        sockets.remove(branch);
    }
}
