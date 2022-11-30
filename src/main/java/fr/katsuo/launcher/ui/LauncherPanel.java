package fr.katsuo.launcher.ui;


import fr.katsuo.launcher.LauncherFrame;
import fr.katsuo.launcher.auth.Authenticate;
import fr.katsuo.launcher.launch.Arguments;
import fr.katsuo.launcher.launch.Launch;
import fr.katsuo.launcher.options.ui.RamFrame;
import fr.katsuo.launcher.secret.Token;
import fr.katsuo.launcher.ui.custom.CustomButton;
import fr.katsuo.launcher.utils.Constants;
import fr.katsuo.launcher.utils.Update;
import fr.katsuo.launcher.utils.logger.ELogger;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LauncherPanel extends JPanel implements ActionListener {

    private Image background = new ImageIcon(getClass().getResource("/background.png")).getImage();;
    private Image imgPlay = new ImageIcon(getClass().getResource("/play.png")).getImage();;
    private Image imgPlayH = new ImageIcon(getClass().getResource("/play_hover.png")).getImage();;
    private Image imgSettings = new ImageIcon(getClass().getResource("/settings.png")).getImage();;
    private Image imgSettingsH = new ImageIcon(getClass().getResource("/settings_hover.png")).getImage();;
    private Image imgClose = new ImageIcon(getClass().getResource("/close.png")).getImage();;
    private Image imgCloseH = new ImageIcon(getClass().getResource("/close_hover.png")).getImage();;
    private Image imgHide = new ImageIcon(getClass().getResource("/hide.png")).getImage();;
    private Image imgHideH = new ImageIcon(getClass().getResource("/hide_hover.png")).getImage();;

    private final JTextField usernameField = new JTextField(Token.TOKEN_MAIL);
    private final JPasswordField passwordField = new JPasswordField(Token.TOKEN_PASS);

    private CustomButton playButton = new CustomButton(imgPlay, imgPlayH);
    private CustomButton settingsButton = new CustomButton(imgSettings, imgSettingsH);
    private CustomButton closeButton = new CustomButton(imgClose, imgCloseH);
    private CustomButton hideButton = new CustomButton(imgHide, imgHideH);

    private final Authenticate authenticate = new Authenticate();
    private RamFrame frame;

    public LauncherPanel() {
        this.setLayout(null);
        this.setOpaque(false);

        usernameField.setBounds(60, 160, 430, 50);
        usernameField.setForeground(Color.WHITE);
        usernameField.setOpaque(false);
        usernameField.setBorder(null);
        this.add(usernameField);

        passwordField.setBounds(60, 290, 430, 50);
        passwordField.setForeground(Color.WHITE);
        passwordField.setOpaque(false);
        passwordField.setBorder(null);
        this.add(passwordField);

        playButton.setBounds(15,490);
        playButton.addActionListener(this);
        this.add(playButton);

        settingsButton.setBounds(285, 490);
        settingsButton.addActionListener(this);
        this.add(settingsButton);

        closeButton.setBounds(510, 16);
        closeButton.addActionListener(this);
        this.add(closeButton);

        hideButton.setBounds(480, 11);
        hideButton.addActionListener(this);
        this.add(hideButton);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (playButton.equals(e.getSource())) {
            System.out.println("test");
            setFieldsEnabled(false);
            if (usernameField.getText().replaceAll(" ", "").length() == 0 || passwordField.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "Tous les champs n'ont pas été remplis", Constants.name + " | Erreur", JOptionPane.ERROR_MESSAGE);
                LauncherFrame.getInstance().getLogger().log(ELogger.ERROR, "Tous les champs n'ont pas été remplis");
                setFieldsEnabled(true);
                return;
            }

            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        authenticate.authenticateMicrosoft(usernameField.getText(), passwordField.getText());
                    } catch (MicrosoftAuthenticationException e) {
                        JOptionPane.showMessageDialog(LauncherPanel.this, "Erreur : " + e.getMessage(), Constants.name + " | Erreur", JOptionPane.ERROR_MESSAGE);
                        LauncherFrame.getInstance().getLogger().log(ELogger.ERROR, e.getMessage());
                        setFieldsEnabled(true);
                        return;
                    }
                    try {
                        Update.update();
                        LauncherFrame.getInstance().getLogger().log(ELogger.INFO, "Game Successfully Updated");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(LauncherPanel.this, "Erreur : " + e.getMessage(), Constants.name + " | Erreur", JOptionPane.ERROR_MESSAGE);
                        LauncherFrame.getInstance().getLogger().log(ELogger.ERROR, e.getMessage());
                        setFieldsEnabled(true);
                        return;
                    }

                    Arguments arguments = new Arguments(authenticate.getResult().getProfile().getName(), authenticate.getResult().getProfile().getId(), authenticate.getResult().getAccessToken());
                    LauncherFrame.getInstance().getLogger().log(ELogger.INFO, "Game Successfully Launch");
                    Launch launch = new Launch(arguments.getVMArguments(), arguments.getArguments());


                }
            };
            t.start();


        } else if (closeButton.equals(e.getSource())) {
            System.exit(0);
        } else if (hideButton.equals(e.getSource())){
            LauncherFrame.getInstance().setState(JFrame.ICONIFIED);
        }else if (settingsButton.equals(e.getSource())) {
            Thread ram = new Thread() {
                @Override
                public void run() {
                    frame = new RamFrame();
                }
            };
            ram.start();
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
        playButton.setEnabled(enabled);
    }

    public RamFrame getFrame() {
        return frame;
    }
}
