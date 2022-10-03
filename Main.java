import OutputService.*;
import Renderer.*;
import StoryService.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    static boolean MMBypass = true;

    public static void main(String[] args) throws IOException, InterruptedException {
        if (MMBypass == false){
            ModuleService.mainMenu();
        }else{
            ModuleService.dialogueDemo();
        }
    }
}
