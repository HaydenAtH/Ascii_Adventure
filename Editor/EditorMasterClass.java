package Editor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class EditorMasterClass {

    // Currently just placeholder
    
    public static void execute(){
        // Create a frame
        JFrame frame = new JFrame("Node Editor");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the x, y, width and height properties


        Container contentPane = frame.getContentPane();

        // Add a close button
        JButton closeButton = new JButton("Close");
        contentPane.add(closeButton);
        closeButton.setLocation(100, 100);

        frame.setBounds(50, 50, 200, 200);

        frame.setVisible(true);
    }
}
