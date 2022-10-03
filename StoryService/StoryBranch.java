package StoryService;

import OutputService.StoryOutput;

import java.io.IOException;

public class StoryBranch {
    StoryNode origin;
    StoryNode endpoint;
    String name;
    StoryOutput output;

    public StoryBranch(StoryNode origin, StoryNode endpoint, StoryOutput output, String name){
        this.origin = origin;
        this.endpoint = endpoint;
        this.output = output;
        this.name = name;

        this.origin.addBranch(this);
    }

    public void activate() throws IOException {
        if (this.output != null){
            this.output.activate();
        }

        this.endpoint.setTriggeredBranch(this);
        this.endpoint.activate();
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
}
