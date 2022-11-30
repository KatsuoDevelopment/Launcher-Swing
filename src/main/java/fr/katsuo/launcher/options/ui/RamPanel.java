package fr.katsuo.launcher.options.ui;

import fr.katsuo.launcher.LauncherFrame;
import fr.katsuo.launcher.options.EOptions;
import fr.katsuo.launcher.options.Options;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RamPanel extends JPanel implements ActionListener {

    private final Options ram = new Options();
    private final JSlider slider = new JSlider(1, 10, Integer.parseInt(ram.getOptions(EOptions.RAM_SIZE)));
    private final JComboBox<String> window_size = new JComboBox<>();
    private final JButton button = new JButton("Save");

    public RamPanel() {
        setLayout(null);

        slider.setBounds(20, 20, 200, 60);
        slider.setMajorTickSpacing(1);

        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        this.add(slider);

        window_size.setBounds(150, 100, 100, 20);
        window_size.addItem("800X600");
        window_size.addItem("1024X768");
        window_size.addItem("1280X1024");
        window_size.addItem("1366X768");
        window_size.addItem("1920X1080");
        window_size.addItem("2560X1440");
        window_size.setSelectedIndex(this.getSelect(window_size, ram.getOptions(EOptions.WINDOW_SIZE)));
        this.add(window_size);

        //Check box for fullscreen to do

        button.setBounds(20, 100, 100, 20);
        button.addActionListener(this);
        this.add(button);
    }

    private int getSelect(JComboBox<String> comboBox, String optionsValue) {
        int valueCombo = 0;
        int size = comboBox.getItemCount();
        for (int v = 0; v < size; v++) {
            String item = comboBox.getItemAt(v);
            if (item.equals(optionsValue)) {
                valueCombo = v;
            }
        }
        return valueCombo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (button.equals(e.getSource())) {
            Options ram = new Options();
            ram.save(EOptions.RAM_SIZE, String.valueOf(this.slider.getValue()));
            ram.save(EOptions.WINDOW_SIZE, this.window_size.getSelectedItem().toString());
            LauncherFrame.getInstance().getPanel().getFrame().setVisible(false);
        }
    }
}
