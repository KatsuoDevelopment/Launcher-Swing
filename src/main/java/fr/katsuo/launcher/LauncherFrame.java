package fr.katsuo.launcher;

import fr.katsuo.launcher.ui.LauncherPanel;
import fr.katsuo.launcher.utils.Constants;
import fr.katsuo.launcher.utils.MouseUtils;
import fr.katsuo.launcher.utils.logger.ELogger;
import fr.katsuo.launcher.utils.logger.Logger;

import javax.swing.*;
import java.awt.*;

public class LauncherFrame extends JFrame {
    private static LauncherFrame instance;
    private final LauncherPanel panel;
    private final Logger logger = new Logger(Constants.name);

    public LauncherFrame() {
        this.setTitle("Launcher de test");

        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setContentPane(panel = new LauncherPanel());
        this.pack();
        this.setSize(550, 650);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        MouseUtils mouse = new MouseUtils(this);
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            instance = new LauncherFrame();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            getInstance().logger.log(ELogger.ERROR, e.getMessage());
        }
    }

    public static LauncherFrame getInstance() {
        return instance;
    }

    public LauncherPanel getPanel() {
        return panel;
    }

    public Logger getLogger() {
        return logger;
    }
}
