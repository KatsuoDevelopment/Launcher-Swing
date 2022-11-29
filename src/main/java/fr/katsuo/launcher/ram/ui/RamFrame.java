package fr.katsuo.launcher.ram.ui;

import fr.katsuo.launcher.utils.MouseUtils;

import javax.swing.*;

public class RamFrame extends JFrame {

    private final RamPanel panel;

    public RamFrame() {
        this.setTitle("Ram Panel");

        this.setUndecorated(true);
        this.setContentPane(panel = new RamPanel());
        this.setSize(300, 200);

        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        MouseUtils mouse = new MouseUtils(this);
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);

        this.setVisible(true);
    }

    public RamPanel getPanel() {
        return panel;
    }
}
