package fr.katsuo.launcher.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseUtils extends MouseAdapter {

    private Point click;
    private JFrame frame;

    public MouseUtils(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (click != null) {
            Point draggedPoint = MouseInfo.getPointerInfo().getLocation();
            frame.setLocation(new Point((int) draggedPoint.getX()
                    - (int) click.getX(), (int) draggedPoint
                    .getY() - (int) click.getY()));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        click = e.getPoint();
    }
}
