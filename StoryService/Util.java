package StoryService;

import OutputService.StoryOutput;

import java.util.ArrayList;

public class Util {

    public static StoryNode newNode(String nodeName, StoryOutput nodeOutput, StoryOutput branchOutput, StoryNode origin, String branchName){
        StoryNode newNode = new StoryNode(nodeName, nodeOutput);
        newBranch(branchOutput, origin, newNode, branchName);
        return newNode;
    }

    public static void newBranch(StoryOutput output, StoryNode node1, StoryNode node2, String name){
        StoryBranch mergeBranch = new StoryBranch(node1, node2, output, name);
    }

    static ArrayList<StoryNode> arr = new ArrayList<StoryNode>();
    public static ArrayList<StoryNode> trace(StoryNode node){
        if (node.triggeredBranch != null){
            arr.add(node);
            trace(node.triggeredBranch.origin);
        }else {
            return arr;
        }
        return null;
    }

    /*
    public static StoryNode findNode(String name, StoryNode origin){

    }
     */
}
