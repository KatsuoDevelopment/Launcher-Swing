package fr.katsuo.launcher;


import fr.katsuo.launcher.auth.Authenticate;
import fr.katsuo.launcher.launch.Arguments;
import fr.katsuo.launcher.launch.Launch;
import fr.katsuo.launcher.utils.Update;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LauncherPanel extends JPanel implements ActionListener {

    private BufferedImage background;
    private final JTextField usernameField = new JTextField(Constants.email);
    private final JPasswordField passwordField = new JPasswordField(Constants.password);

    private final JButton play = new JButton("Jouer");
    private final JButton close = new JButton("X");
    private final JButton settings = new JButton("Ram");

    private final Authenticate authenticate = new Authenticate();

    public LauncherPanel() {
        this.setLayout(null);
        this.setOpaque(false);

        try {
            background = ImageIO.read(getClass().getResource("/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        usernameField.setBounds(300, 350, 200, 30);
        this.add(usernameField);

        passwordField.setBounds(300, 400, 200, 30);
        this.add(passwordField);

        play.setBounds(375, 180, 150, 30);
        play.addActionListener(this);
        this.add(play);

        close.setBounds(150, 230, 40, 40);
        close.addActionListener(this);
        this.add(close);

        settings.setBounds(70, 230, 40, 40);
        settings.addActionListener(this);
        this.add(settings);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (play.equals(e.getSource())) {
            setFieldsEnabled(false);

            if (usernameField.getText().replaceAll(" ", "").length() == 0 || passwordField.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Rempli les champs", "Erreur zeubi", JOptionPane.ERROR_MESSAGE);
                setFieldsEnabled(true);
            }

            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        authenticate.authenticateMicrosoft(usernameField.getText(), passwordField.getText());
                    } catch (MicrosoftAuthenticationException e) {
                        JOptionPane.showMessageDialog(LauncherPanel.this, "Erreur de connexion", "Erreur zeubi : " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
                        setFieldsEnabled(true);
                        return;
                    }
                    try {
                        Update.update();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(LauncherPanel.this, "Erreur de connexion", "Erreur zeubi : " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
                        setFieldsEnabled(true);
                    }

                    Arguments arguments = new Arguments(authenticate.getResult().getProfile().getName(), authenticate.getResult().getProfile().getId(), authenticate.getResult().getAccessToken());

                    Launch launch = new Launch(arguments.getVMArguments(), arguments.getArguments());

                }
            };
            t.start();


        } else if (close.equals(e.getSource())) {
            System.exit(0);
        } else if (settings.equals(e.getSource())) {
            Thread t = new Thread(){
                @Override
                public void run() {

                }
            };
            t.start();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }

    private void setFieldsEnabled(boolean enabled) {
        usernameField.setEnabled(enabled);
        passwordField.setEnabled(enabled);
        play.setEnabled(enabled);
    }

}
