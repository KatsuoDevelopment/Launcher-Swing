package fr.katsuo.launcher.ui.custom;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CustomButton extends JComponent {

    private final BufferedImage image;

    public CustomButton(BufferedImage image) {
        this.image = image;
        this.repaint();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("test de function");
        if (image != null) {
            g.drawImage(image, 0, 0, this);
            System.out.println("test2");
        } else {
            System.out.println("test");
        }
    }
}
