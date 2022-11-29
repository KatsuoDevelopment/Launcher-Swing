package fr.katsuo.launcher.ram.ui;

import fr.katsuo.launcher.ram.Ram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RamPanel extends JPanel implements ActionListener {

    private final JSlider slider = new JSlider(1, 10, 1);
    private final JButton button = new JButton("Save");

    public RamPanel(){
        setLayout(null);

        slider.setBounds(20, 20, 200, 60);
        slider.setMajorTickSpacing(1);

        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        this.add(slider);

        button.setBounds(20, 100, 100, 20);
        button.addActionListener(this);
        this.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (button.equals(e.getSource())) {
            Ram ram = new Ram();
            ram.save(String.valueOf(this.slider.getValue()));
        }
    }
}
