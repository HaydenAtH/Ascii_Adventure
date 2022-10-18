package StoryService;

import OutputService.StoryOutput;
import PlayerService.Attribute;
import PlayerService.PlayerInfo;

import java.io.IOException;

public class StoryBranch {
    StoryNode origin;
    StoryNode endpoint;
    String name;
    StoryOutput output;

    Attribute requirementAtt;
    int requirementVal;

    public StoryBranch(StoryNode origin, StoryNode endpoint, StoryOutput output, String name){
        this.origin = origin;
        this.endpoint = endpoint;
        this.output = output;
        this.name = name;

        this.origin.addBranch(this);
    }

    public void activate() throws IOException, InterruptedException {
        if (this.output != null){
            this.output.activate();
        }

        this.endpoint.setTriggeredBranch(this);
        this.endpoint.activate();
    }

    // 1 For removed
    // 0 For kept

    public int checkReqStatus(){
        if (this.requirementAtt != null && this.requirementVal > 0){
            if (PlayerInfo.findAttribute(this.requirementAtt) != null){
                if (PlayerInfo.findAttribute(this.requirementAtt).getValue() < requirementVal){
                    this.origin.removeBranch(this);
                    return 1;
                }
            }
        }
        return 0;
    }

    // Declarative Statements

    public void setOutput(StoryOutput output) {
        this.output = output;
    }

    public void setEndpoint(StoryNode endpoint) {
        this.endpoint = endpoint;
    }

    public void setOrigin(StoryNode origin) {
        this.origin = origin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRequirementAtt(Attribute requirementAtt) {
        this.requirementAtt = requirementAtt;
    }

    public void setRequirementVal(int requirementVal) {
        this.requirementVal = requirementVal;
    }

    // Query Statements


    public String getName() {
        return name;
    }

    public StoryOutput getOutput() {
        return output;
    }

    public StoryNode getEndpoint() {
        return endpoint;
    }

    public StoryNode getOrigin() {
        return origin;
    }

    public Attribute getRequirementAtt() {
        return requirementAtt;
    }

    public int getRequirementVal() {
        return requirementVal;
    }
}
