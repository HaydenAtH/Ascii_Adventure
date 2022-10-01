package StoryService;

import OutputService.StoryOutput;

public class Util {

    public static void newNode(String nodeName, StoryOutput nodeOutput, StoryOutput branchOutput, StoryNode origin, String branchName){
        StoryNode newNode = new StoryNode(nodeName, nodeOutput);
        newBranch(branchOutput, origin, newNode, branchName);
    }

    public static void newBranch(StoryOutput output, StoryNode node1, StoryNode node2, String name){
        StoryBranch mergeBranch = new StoryBranch(node1, node2, output, name);
    }
}
