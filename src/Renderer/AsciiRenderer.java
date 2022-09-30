package Renderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.HashMap;

import javax.imageio.ImageIO;
import java.util.ArrayList;

public class AsciiRenderer {


	/*
	 *
	 * [Implementation Syntax]
	 *
            Renderer.Image img = new Renderer.Image("/home/cabox/workspace/GraphicsTesting/Images/monaLisa.jpg", 0.95, null, false);
        try {
            // Path of the File, Color Sensitivity Factor, Text array overwrite, inverse
            render(img);
        } catch (IOException e) {
            e.printStackTrace();

        }

     */






    static HashMap<Vector2, Vector3> pixelMap = new HashMap<Vector2, Vector3>();
    static HashMap<Vector2, Boolean> displayMap = new HashMap<Vector2, Boolean>();

    static ArrayList<Vector2> coordList = new ArrayList<Vector2>();

    static int avgR = 0;
    static int avgG = 0;
    static int avgB = 0;
    static int avgColor = 0;

    static String[] characterArray = {"a", "3", "$", "B", "%", "&", "@", "]", "[", "?", "d", "h", "c", "Y", "X", ")", "(", "#", "C", "J", "j", "!", "1", "0"};

    public static String getRandomStr(String[] array){
        int x =  (int) (Math.random() * array.length - 1);

        return array[x];
    }

    // [Renders an Image object]
    public static void render(Image img) throws IOException{
        String imagePath = img.fileName;
        BufferedImage myPicture = ImageIO.read(new File(imagePath));

        double colorFilterModifier = img.modifier;
        boolean inverse = img.inverse;
        String[] textOverride = img.textOverride;

        int width = myPicture.getWidth();
        int height =  myPicture.getHeight();

        // Recording all present pixels of an image and their A-RGB values

        for (int i = 0; i < height; i++){
            for (int z = 0; z < width; z++){
                int p = myPicture.getRGB(z, i);

                int r = (p>>16) & 0xff;
                int g = (p>>8) & 0xff;
                int b = p & 0xff;

                avgR += r;
                avgG += g;
                avgB += b;

                // V is the pixel being recorded

                Vector2 v = new Vector2(z, i);
                Vector3 c = new Vector3(r, g, b);
                coordList.add(v);

                pixelMap.put(v, c);
                //System.out.println("Logged Pixel: " + z  + ", " + i);
            }
        }

        /*

        // TODO image downscaling method (Once I figure out how to do that lol)

        int crntX = 0;
        int crntY = 0;

        for (int y = 0; y < height; y++){
            if (y % 2 != 0){
                crntY += 1;
                for (int x = 0; x < width; x++){
                    if (x % 2 != 0) {
                        crntX += 1;

                    }
                }
            }

        }

         */

        avgColor = (avgR + avgB + avgG) / (width * height);

        // Writing to display map
        for (int i = 0; i < coordList.size(); i++){

            Vector3 j = pixelMap.get(coordList.get(i));

            if (j != null){
                if ((j.x + j.y + j.z) > (avgColor * colorFilterModifier)){
                    displayMap.put(coordList.get(i), !inverse);
                    System.out.println(coordList.get(i) + " Written to display map as True");
                }else{
                    displayMap.put(coordList.get(i), inverse);
                    System.out.println(coordList.get(i) + " Written to display map as False");
                }
            }
        }

        // Displaying Relevant pixels as a product of getRandomStr on characterArray
        String[] arr;

        if (textOverride == null){
            arr = characterArray;
        }else{
            arr = textOverride;
        }

        for (int i = 0; i < coordList.size(); i++){
            if (displayMap.get(coordList.get(i)) == true){
                String text = getRandomStr(arr);
                System.out.print(text);
            }else{
                System.out.print(" ");
            }

            if (i % width == 0){
                System.out.println(" ");
            }
        }
    }
}