package Renderer;

public class Image {
    protected String fileName;
    protected double modifier;
    protected String[] textOverride;
    protected boolean inverse;

    public Image(String fileName, double modifier, String[] textOverride, boolean inverse){
        this.fileName = fileName;
        this.modifier = modifier;
        this.textOverride = textOverride;
        this.inverse = inverse;
    }


}
