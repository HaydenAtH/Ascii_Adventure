package StoryService;

import OutputService.StoryOutput;
import PlayerService.Attribute;

import java.util.ArrayList;

public class Util {

    public static StoryNode newNode(String nodeName, StoryOutput nodeOutput, StoryOutput branchOutput, StoryNode origin, String branchName, Attribute skillCheckAtt, int skillCheckVal){
        StoryNode newNode = new StoryNode(nodeName, nodeOutput);
        StoryBranch branch = newBranch(branchOutput, origin, newNode, branchName);
        if (skillCheckAtt != null && skillCheckVal > 0){
            branch.setRequirementAtt(skillCheckAtt);
            branch.setRequirementVal(skillCheckVal);
        }
        return newNode;
    }

    public static StoryBranch newBranch(StoryOutput output, StoryNode node1, StoryNode node2, String name){
        StoryBranch mergeBranch = new StoryBranch(node1, node2, output, name);
        return mergeBranch;
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
    public void static deubgCommand(String command){
        if (command == ":DebugNode"){

        }else if(command == ":DebugPlayer"){

        }else if(command == ":DebugSetPlayerAtt"){

        }
    }

     */

    /*
    public static StoryNode findNode(String name, StoryNode origin){

    }
     */
}
