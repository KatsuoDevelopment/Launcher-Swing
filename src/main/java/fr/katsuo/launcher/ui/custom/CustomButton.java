package fr.katsuo.launcher.ui.custom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomButton extends JButton implements MouseListener {

    private final Image image;
    private final Image hoveredImage;
    private boolean hovered = false;


    public CustomButton(Image image, Image hoveredImage) {
        this.hoveredImage = hoveredImage;
        this.image = image;
        this.addMouseListener(this);
        this.setSize(this.image.getWidth(this) + 1, this.image.getHeight(this));
        this.setBorder(null);
        this.setContentAreaFilled(false);
        this.setBackground(new Color(0, 0, 0, 0));
    }

    public CustomButton(Image image) {
        this(image, null);
    }

    public void setBounds(int x, int y) {
        super.setBounds(x, y, this.getWidth(), this.getHeight());
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //if (this.isEnabled() && e.getButton() == 1) new ButtonEventHandler().clickEvent(this);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.hovered = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.hovered = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), this);
        if (this.hovered) {
            if (this.hoveredImage == null) {
                g.setColor(new Color(255, 255, 255, 60));
                g.fillRect(0, 0, this.getWidth(), this.getWidth());
            } else {
                g.drawImage(this.hoveredImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }
    }
}