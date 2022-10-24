import OutputService.*;
import Renderer.*;
import StoryService.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static boolean MMBypass = false;


    public static void main(String[] args) throws IOException, InterruptedException {
        if (MMBypass == false){
            System.out.println("> Without further ado... Welcome to");
            ModuleService.mainMenu();
        }else{
            ModuleService.combatDemo();
        }
    }

}
